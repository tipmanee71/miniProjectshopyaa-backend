package com.example.demo.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.Model.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
    // ...
}
