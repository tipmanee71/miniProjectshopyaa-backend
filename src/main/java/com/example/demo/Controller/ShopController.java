package com.example.demo.Controller;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Cart;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderItem;
import com.example.demo.Model.Product;
import com.example.demo.Reponsitory.CartRepository;
import com.example.demo.Reponsitory.OrderItemRepository;
import com.example.demo.Reponsitory.OrderRepository;
import com.example.demo.Reponsitory.ProductReponsitory;

@RestController
@CrossOrigin(origins = "*")
public class ShopController {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductReponsitory productReponsitory;
	
	@Autowired
	OrderItemRepository orderItemRepository;

	@GetMapping("/products")
	public ResponseEntity<Object> getProducts() {
		try {
			List<Product> products = productReponsitory.findAll();
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/products/{pdId}")
	public ResponseEntity<Object> getProductById(@PathVariable("pdId") Integer pdId) {
	    try {
	        Optional<Product> productOptional = productReponsitory.findById(pdId);

	        if (productOptional.isPresent()) {
	            Product product = productOptional.get();
	            return new ResponseEntity<>(product, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


//	@PostMapping("/add-to-cart")
//	public String addToCart(@RequestParam Integer pdId) {
//	    Optional<Product> productOptional = productReponsitory.findById(pdId);
//
//	    if (productOptional.isPresent()) {
//	        Product product = productOptional.get();
//	        // Add the product to the cart
//	        cart.addProduct(product);
//	    }
//
//	    return "redirect:/products"; // Redirect back to the products page
//	}
	
	@GetMapping("/cart")
	public ResponseEntity<Object> viewCart() {
	    try {
	        List<Cart> cartItems = cartRepository.findAll();
	        
	        // Log the cart items to the console
	        for (Cart cartItem : cartItems) {
	            System.out.println("Cart ID: " + cartItem.getCartsId());
	            System.out.println("Product Name: " + cartItem.getProduct().getPdName());
	            System.out.println("Quantity: " + cartItem.getCartsQty());
	            System.out.println("-------------------------");
	        }

	        // Return cartItems in the ResponseEntity body
	        return new ResponseEntity<>(cartItems, HttpStatus.OK);
	    } catch (Exception e) {
	        // Handle exceptions here
	        e.printStackTrace(); // Log the exception to the console for debugging
	        return new ResponseEntity<>("Failed to retrieve cart items", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	

	
	@PostMapping("/add-to-cart/{pdId}")
	public ResponseEntity<String> addToCart(@PathVariable("pdId") Integer pdId, @RequestParam("quantity") Integer quantity) {
		
		try {
			Optional<Product> productOptional = productReponsitory.findById(pdId);

			if (productOptional.isPresent()) {
				Product product = productOptional.get();
				Cart cartItem = new Cart();
				cartItem.setCartsQty(quantity ); 
				cartItem.setProduct(product);
				cartRepository.save(cartItem);

			} return new ResponseEntity<>("Product added to cart successfully", HttpStatus.OK);
	    } catch (Exception e) {
	    	 return new ResponseEntity<>("Failed to add product to cart", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
	}



	
	@DeleteMapping("/remove/{cartsId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Integer cartsId) {
        Optional<Cart> cartItem = cartRepository.findById(cartsId);
        if (cartItem.isPresent()) {
            cartRepository.delete(cartItem.get());
            return ResponseEntity.ok("Product removed from the cart");
        }
        return ResponseEntity.notFound().build();
    }
	
	


	@GetMapping("/select-items-for-order")
	public String selectItemsForOrder(Model model) {
		List<Cart> cartItems = cartRepository.findAll();
		model.addAttribute("cartItems", cartItems);
		return "select-items-for-order"; // Create a corresponding HTML template for this view
	}
	
	
//
//	   @PostMapping("/create-order-item")
//	    public ResponseEntity<Object> createOrderItem(@RequestBody Map<String, Integer> selectedItems) {
//	        Integer cartsId = selectedItems.get("cartsId");
//	        Integer cartsQty = selectedItems.get("cartsQty");
//
//	        if (cartsId != null && cartsQty != null) {
//	        	
//	            OrderItem orderItem = new OrderItem();
//	            orderItem.setCartsId(cartsId);
//	            orderItem.setCartsQty(cartsQty);
//
//	            // Save the OrderItem to the database
//	            orderItemRepository.save(orderItem);
//
//	            return ResponseEntity.status(HttpStatus.OK).body(orderItem);
//	        } else {
//	        	 return new ResponseEntity<>("Invalid data. Please check cartsId and cartsQty." , HttpStatus.INTERNAL_SERVER_ERROR);
//	        }
//	    }
//	   
	@PostMapping("/create-order-item")
	public ResponseEntity<Object> createOrderItem(@RequestBody List<OrderItem> orderItems) {
	    try {
	        List<OrderItem> createdOrderItems = new ArrayList<>();
	        for (OrderItem orderItem : orderItems) {
	            Integer cartsId = orderItem.getCartsId();
	            Integer cartsQty = orderItem.getCartsQty();

	            // Check if cartsId and cartsQty are not null
	            if (cartsId != null && cartsQty != null) {
	                // Find the cart item by its ID
	                Optional<Cart> cartOptional = cartRepository.findById(cartsId);

	                if (cartOptional.isPresent()) {
	                    Cart cart = cartOptional.get();

	                    // Create an OrderItem
	                    OrderItem newOrderItem = new OrderItem();
	                    newOrderItem.setProduct(cart.getProduct());
	                    newOrderItem.setCartsQty(cartsQty);

	                    // Save the OrderItem to the database
	                    OrderItem savedOrderItem = orderItemRepository.save(newOrderItem);
	                    createdOrderItems.add(savedOrderItem);

	                    // Delete the cart item from the Cart table
	                    cartRepository.delete(cart);
	                } else {
	                    return new ResponseEntity<>("Cart item not found.", HttpStatus.NOT_FOUND);
	                }
	            } else {
	                // Handle the case when cartsId or cartsQty are null
	                // You can choose to skip this order item or handle it differently
	                // For now, we'll just skip it
	                continue;
	            }
	        }

	        return ResponseEntity.status(HttpStatus.OK).body(createdOrderItems);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Failed to create order items.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	
	}

 

