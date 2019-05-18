package com.raven43.simplestore.controller;

import com.raven43.simplestore.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/seller")
public class SellerController {

    private final ShopService shopService;

    @Autowired
    public SellerController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public ModelAndView main(
            Pageable pageable
    ) {
        return new ModelAndView("seller/main")
                .addObject("page", shopService.openedOrders(pageable));
    }
}
