package com.raven43.simplestore.controller;

import com.raven43.simplestore.exception.SomeException;
import com.raven43.simplestore.model.User;
import com.raven43.simplestore.service.ItemService;
import com.raven43.simplestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final UserService userService;
    private final ItemService itemService;

    @Autowired
    public MainController(
            UserService userService,
            ItemService itemService
    ) {
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String index(
            Model model
    ) {
        model.addAttribute("page",itemService.getRandom(4));
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String addNewUser(
            User user,
            Model model
    ) {
        if (userService.addUser(user))
            return "redirect:/login";
        else {
            model.addAttribute("message", "user exist!");
            return "register";
        }
    }

    private static boolean some = true;

    @GetMapping("/some")
    public String some(Model model){
        some = !some;
        if(some)throw new SomeException();
        model.addAttribute("message","no error");
        return "some";
    }

    @ExceptionHandler(SomeException.class)
    public String someException(Model model){
        model.addAttribute("message","error");
        return "some";
    }



}
