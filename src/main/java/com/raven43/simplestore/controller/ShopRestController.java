package com.raven43.simplestore.controller;

import com.raven43.simplestore.model.Order;
import com.raven43.simplestore.model.User;
import com.raven43.simplestore.service.ShopService;
import com.raven43.simplestore.repo.ItemRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/rest/shop")
public class ShopRestController {

    private final Logger log = LoggerFactory.getLogger(ShopRestController.class);

    private final ItemRepo itemRepo;
    private final ShopService shopService;

    @Autowired
    public ShopRestController(
            ItemRepo itemRepo,
            ShopService shopService
    ) {
        this.itemRepo = itemRepo;
        this.shopService = shopService;
    }

    @GetMapping("/cart")
    public Set<Order> shopCart(
            @AuthenticationPrincipal User user
    ) {
        return shopService.getShopCart(user);
    }

    @PostMapping("/add")
    public void addToShopCart(
            @AuthenticationPrincipal User user,
            @RequestParam Long itemId,
            @RequestParam(required = false, defaultValue = "1") int number
    ) {
        shopService.add(user, itemId, number);
    }

    @PostMapping("/delete")
    public void deleteFromShopCart(
            @AuthenticationPrincipal User user,
            @RequestParam Long positionId
    ) {
        log.info(user.toString()+" delete position : "+positionId);
        shopService.delete(positionId);
    }


}
