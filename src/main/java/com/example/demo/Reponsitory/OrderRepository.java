package com.example.demo.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>  {

}
