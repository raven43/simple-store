package com.raven43.simplestore.controller;

import com.raven43.simplestore.model.*;
import com.raven43.simplestore.repo.CommentRepo;
import com.raven43.simplestore.repo.TopicRepo;
import com.raven43.simplestore.service.CommentService;
import com.raven43.simplestore.service.ItemService;
import com.raven43.simplestore.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/store")
public class ViewController {

    private static final Logger log = LoggerFactory.getLogger(ViewController.class);

    private final ItemService itemService;

    private final TopicRepo topicRepo;
    private final CommentRepo commentRepo;
    private final CommentService commentService;

    private final ShopService shopService;

    @Autowired
    public ViewController(ItemService itemService,
                          TopicRepo topicRepo,
                          CommentRepo commentRepo,
                          CommentService commentService,
                          ShopService shopService) {
        this.itemService = itemService;
        this.topicRepo = topicRepo;
        this.commentRepo = commentRepo;
        this.commentService = commentService;
        this.shopService = shopService;
    }

    @GetMapping
    public String main(Model model) {
        model.addAttribute("categories", itemService.getCategories());
        return "view/main";
    }

    @GetMapping("/{category}")
    public String getCategory(
            @PathVariable String category,
            Pageable pageable,
            Model model) {
        Page<Item> page = itemService.getPage(category, pageable);
        model.addAttribute("category", category);
        model.addAttribute("page", page);

        return "view/category";
    }

    @GetMapping("/item/{id}")
    public String getItem(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            Model model) {
        boolean isItemInShopCart = false;
        Set<Order> set = shopService.getShopCart(user);
        Item item = itemService.findById(id);
        for (Order order : set) {
            if (order.getItem().equals(item)) {
                isItemInShopCart = true;
            }
        }

        model.addAttribute("item", item);
        model.addAttribute("inSC", isItemInShopCart);
        return "view/item";
    }


    @GetMapping("/item/{id}/comments")
    public String getItemComments(
            @PathVariable Long id,
            Pageable pageable,
            Model model) {
        Item item = itemService.findById(id);
        Page<Comment> page = commentRepo.getByTopic(item.getTopic(), pageable);
        model.addAttribute("page", page);
        model.addAttribute("entity", item);
        model.addAttribute("type", "store/item");
        return "view/topic";
    }

    @PostMapping("/item/{id}/comments")
    public String postItemComment(
            @PathVariable Long id,
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            Pageable pageable,
            Model model) {
        Item item = itemService.findById(id);

        if (item.getTopic() == null) {
            item.setTopic(topicRepo.save(new Topic()));
        }

        Comment comment = new Comment(user, item.getTopic(), text);
        commentRepo.save(comment);
        log.info(comment.toString());

        Page<Comment> page = commentRepo.getByTopic(item.getTopic(), pageable);
        model.addAttribute("entity", item);
        model.addAttribute("page", page);
        model.addAttribute("type", "store/item");
        return "view/topic";
    }
}
