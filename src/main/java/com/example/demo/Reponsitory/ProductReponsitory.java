package com.example.demo.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.Model.Product;

public interface ProductReponsitory extends JpaRepository<Product	, Integer> {

}
