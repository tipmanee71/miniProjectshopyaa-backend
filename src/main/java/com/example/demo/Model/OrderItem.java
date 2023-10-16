package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  orderItemId;
    private Integer quantity;
    
    
//    @ManyToOne
//    @JoinColumn(name = "pd_id")
//    private Product product;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

  

    public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}


//    public void setProduct(Product product) {
//        this.product = product;
//    }

    public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Order getOrder() {
		return order;
	}

	public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public void setOrder(Order order) {
        this.order = order;
    }

	public void setCartsId(Integer cartsId) {
		// TODO Auto-generated method stub
		
	}

	public void setCartsQty(Integer cartsQty) {
		// TODO Auto-generated method stub
		
	}

	public Integer getCartsQty() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getCartsId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setProduct(Product product) {
		// TODO Auto-generated method stub
		
	}
}
