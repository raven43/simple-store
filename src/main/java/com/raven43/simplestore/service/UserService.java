package com.raven43.simplestore.service;

import com.raven43.simplestore.exception.NoSuchUserException;
import com.raven43.simplestore.exception.WrongPasswordException;
import com.raven43.simplestore.model.User;
import com.raven43.simplestore.repo.OrderRepo;
import com.raven43.simplestore.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final FileService fileService;
    private final OrderRepo orderRepo;

    @Autowired
    public UserService(UserRepo userRepo, FileService fileService, OrderRepo orderRepo) {
        this.userRepo = userRepo;
        this.fileService = fileService;
        this.orderRepo = orderRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    public boolean addUser(User user) {
        if (user == null || userRepo.existsByUsername(user.getUsername())) {
            return false;
        }
        if (userRepo.count() == 0)
            user.setAuthorities(Arrays.stream(User.Role.values()).collect(Collectors.toSet()));
        else
            user.setAuthorities(Collections.singleton(User.Role.CLIENT));


        userRepo.save(user);
        //Order order = orderRepo.save(new Order(user));
        return true;
    }

//    public User save(User user) {
//        return userRepo.save(user);
//    }

    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new NoSuchUserException(id));
    }

    public Page<User> getPage(String s, Pageable pageable) {
        return userRepo.findByUsernameContains(s, pageable);
    }

    public Page<User> getPage(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    public User updateAuthorities(User user, Collection<String> authorities) {

        Set<User.Role> roleSet = new HashSet<>();
        for (User.Role r : User.Role.values()) {
            if (authorities.contains(r.name()))
                roleSet.add(r);
        }

        user.setAuthorities(roleSet);
        userRepo.save(user);
        return user;
    }

    public User updateImg(User user, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String imgName = fileService.upload(file);
            user.setImgName(imgName);
        }
        return userRepo.save(user);
    }

    public User updatePassword(User user, String oldPassword, String newPassword) {
        if (!Objects.equals(oldPassword, "")
                && !Objects.equals(newPassword, "")
                && oldPassword != null
                && newPassword != null
        ) {
            if (user.getPassword().equals(oldPassword)) user.setPassword(newPassword);
            else throw new WrongPasswordException();
        }
        return userRepo.save(user);
    }


}
