package com.example.demo.Reponsitory;

import com.example.demo.Model.Cart;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	   List<Cart> findByProductPdId(Integer pdId);


}

