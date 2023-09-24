package com.example.demo.Model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartsId;
	private Integer cartsQty;
	
	@ManyToOne
    @JoinColumn(name = "pd_id")
	 private Product product;


	public Integer getCartsId() {
		return cartsId;
	}
	public void setCartsId(Integer cartsId) {
		this.cartsId = cartsId;
	}
	public Integer getCartsQty() {
		return cartsQty;
	}
	public void setCartsQty(Integer cartsQty) {
		this.cartsQty = cartsQty;
	}
	
	public void setProduct(Product product) {
		this.product = product;
		
	}
	public Product getProduct() {
		return product;
	}
	
	
	
	
}