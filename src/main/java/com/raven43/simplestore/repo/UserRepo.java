package com.raven43.simplestore.repo;

import com.raven43.simplestore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends PagingAndSortingRepository<User,Long> {

    User findByUsername(String usernaame);

    boolean existsByUsername(String username);

    Page<User> findByUsernameContains(String s,Pageable pageable);
}
