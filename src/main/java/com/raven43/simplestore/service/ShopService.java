package com.raven43.simplestore.service;

import com.raven43.simplestore.model.Order;
import com.raven43.simplestore.repo.OrderRepo;
import com.raven43.simplestore.repo.UserRepo;
import com.raven43.simplestore.exception.NoSuchItemException;
import com.raven43.simplestore.model.Item;
import com.raven43.simplestore.model.User;
import com.raven43.simplestore.repo.ItemRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class ShopService {

    private final Logger log = LoggerFactory.getLogger(ShopService.class);

    private final OrderRepo orderRepo;
    private final ItemRepo itemRepo;
    private final UserRepo userRepo;


    @Autowired
    public ShopService(
            OrderRepo orderRepo,
            ItemRepo itemRepo,
            UserRepo userRepo
    ) {
        this.orderRepo = orderRepo;
        this.itemRepo = itemRepo;
        this.userRepo = userRepo;
    }


    public Set<Order> getShopCart(User user) {
        return orderRepo.getByUserAndStatus(user, Order.Status.SHOP_CART);
    }

    public void add(User user, Item item, int number) {

        Order existingOrder = orderRepo.getByUserAndStatusAndItem(user, Order.Status.SHOP_CART, item);
        if (existingOrder == null) {
            Order order = new Order();
            order.setUser(user);
            order.setStatus(Order.Status.SHOP_CART);
            order.setItem(item);
            order.setDate(new Date());
            order.setNumber(number);

            orderRepo.saveAndFlush(order);
        } else {
            existingOrder.setNumber(existingOrder.getNumber() + number);
            orderRepo.saveAndFlush(existingOrder);
        }
    }

    public void add(User user, Long itemId, int number) {
        Item item = itemRepo.findById(itemId).orElseThrow(() -> new NoSuchItemException(itemId));
        add(user, item, number);
    }

    public void delete(Long orderId) {
        orderRepo.deleteById(orderId);
    }

    public Page<Order> openedOrders(Pageable pageable) {
        //return orderRepo.getByStatus(Order.Status.OPENED, pageable);
        return orderRepo.findAll(pageable);
    }

}
