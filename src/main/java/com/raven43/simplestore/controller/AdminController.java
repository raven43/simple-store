package com.raven43.simplestore.controller;

import com.raven43.simplestore.model.Item;
import com.raven43.simplestore.model.User;
import com.raven43.simplestore.service.ItemService;
import com.raven43.simplestore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final UserService userService;
    private final ItemService itemService;

    @Autowired
    public AdminController(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping
    public String main() {
        return "admin/main";
    }

    @GetMapping("/item")
    public String getItemEdit(
            @RequestParam(required = false) Long id,
            Model model
    ) {
        if (id!=null)
        model.addAttribute("item", itemService.findById(id));
        model.addAttribute("categories", itemService.getCategories());
        return "admin/item";
    }
    @PostMapping("/item")
    public String postItemEdit(
            @Valid Item item,
            @RequestParam(required = false) MultipartFile file,
            Model model
    ) {
        log.info(item.toString());

        item = itemService.addItem(item);
        try {
            itemService.updateImg(item,file);
            model.addAttribute("message", "Item updated");
        }catch (IOException e){
            model.addAttribute("message", "Wrong file");
        }

        model.addAttribute("item", item);
        model.addAttribute("categories", itemService.getCategories());
        return "admin/item";
    }

    @GetMapping("/users")
    public String getUsers(
            @RequestParam(required = false, defaultValue = "") String str,
            Pageable pageable,
            Model model
    ) {
        model.addAttribute("page", userService.getPage(str, pageable));
        model.addAttribute("str", str);
        return "admin/users";
    }

    @GetMapping("/users/{id}")
    public String getUser(
            @PathVariable Long id,
            Pageable pageable,
            Model model
    ) {
        model.addAttribute("item", userService.findById(id));
        model.addAttribute("authorities", User.Role.values());

        return "admin/user";
    }

    @PostMapping("/users/{id}")
    public String editUser(
            @PathVariable Long id,
            @RequestParam(name = "authorities[]", required = false, defaultValue = "") ArrayList<String> authorities,
            Model model
    ) {

        User user = userService.findById(id);
        user = userService.updateAuthorities(user, authorities);

        model.addAttribute("item", user);
        model.addAttribute("authorities", User.Role.values());
        model.addAttribute("message", "User " + user.getUsername() + " updated");
        return "admin/user";
    }


}
