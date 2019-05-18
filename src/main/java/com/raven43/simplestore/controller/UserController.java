package com.raven43.simplestore.controller;

import com.raven43.simplestore.exception.WrongPasswordException;
import com.raven43.simplestore.model.Order;
import com.raven43.simplestore.model.User;
import com.raven43.simplestore.service.FileService;
import com.raven43.simplestore.service.ShopService;
import com.raven43.simplestore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Set;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final FileService fileService;
    private final ShopService shopService;


    @Autowired
    public UserController(
            UserService userService,
            FileService fileService,
            ShopService shopService
    ) {
        this.userService = userService;
        this.fileService = fileService;
        this.shopService = shopService;
    }

    @GetMapping
    public ModelAndView profile(
            @AuthenticationPrincipal User user
    ) {

        Set<Order> set = shopService.getShopCart(user);
        Long summ = 0L;
        for (Order i:set)summ+=i.getCost();
        return new ModelAndView("user/profile")
                .addObject("shopCart", set)
                .addObject("summ", summ);
    }

    @GetMapping("/edit")
    public String profileEdit(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/edit")
    public String profileEditPost(
            @AuthenticationPrincipal User user,
            @RequestParam String login,
            @RequestParam(required = false) String oldPassword,
            @RequestParam(required = false) String newPassword,
            @RequestParam(required = false) MultipartFile file,
            Model model
    ) {

        try {
            user.setUsername(login);
            userService.updatePassword(user, oldPassword, newPassword);
            userService.updateImg(user, file);
        } catch (WrongPasswordException e) {
            model.addAttribute("message", "wrong password");
            model.addAttribute("user", user);
            return "user/edit";
        } catch (IOException e) {
            log.warn("Error while uploading user avatar", e);
            model.addAttribute("message", "Wrong file");
            model.addAttribute("user", user);
            return "user/edit";
        }
        model.addAttribute("user", user);
        return "redirect:/profile";

    }
}
