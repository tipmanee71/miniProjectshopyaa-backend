package com.example.demo.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems = new ArrayList<>();

	  public Integer getOrderId() {
	        return orderId;
	    }

	    public void setOrderId(Integer orderId) {
	        this.orderId = orderId;
	    }

	    public Date getOrderDate() {
	        return orderDate;
	    }

	    public void setOrderDate(Date orderDate) {
	        this.orderDate = orderDate;
	    }

	    public List<OrderItem> getOrderItems() {
	        return orderItems;
	    }

	    public void setOrderItems(List<OrderItem> orderItems) {
	        this.orderItems = orderItems;
	    }

	    public void addOrderItem(OrderItem orderItem) {
	        orderItem.setOrder(this);
	        this.orderItems.add(orderItem);
	    }

	    public void removeOrderItem(OrderItem orderItem) {
	        orderItem.setOrder(null);
	        this.orderItems.remove(orderItem);
	    }


}
