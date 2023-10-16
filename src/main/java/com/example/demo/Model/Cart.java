package com.example.demo.Model;

import java.util.List;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	
	@OneToOne
	@JoinColumn(name = "usersId")
	private User user;

	 @OneToMany
	    @JoinColumn(name = "cart_id") // Define the appropriate foreign key column name
	    private List<Order> orders; // Define a collection of orders

	  
	 
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
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
	public Integer getPdId() {return null;
	}
	
	
	
	
}