package com.raven43.simplestore.repo;

import com.raven43.simplestore.model.Order;
import com.raven43.simplestore.model.Item;
import com.raven43.simplestore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderRepo extends JpaRepository<Order,Long> {

    Set<Order> getByUserAndStatus(User user, Order.Status status);

    Page<Order> getByStatus(Order.Status status, Pageable pageable);

    Order getByUserAndStatusAndItem(User user, Order.Status status, Item item);

}
