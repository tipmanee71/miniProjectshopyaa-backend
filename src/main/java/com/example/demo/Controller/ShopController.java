package com.example.demo.Controller;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Cart;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderItem;
import com.example.demo.Model.Product;
import com.example.demo.Reponsitory.CartRepository;
import com.example.demo.Reponsitory.OrderRepository;
import com.example.demo.Reponsitory.ProductReponsitory;

@RestController
@CrossOrigin(origins = "*")
public class ShopController {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductReponsitory productReponsitory;

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

	@GetMapping("/remove-from-cart/{pdId}")
	public String removeFromCart(@PathVariable("pdId") Integer pdId) {
		// Get the cart items for the specified product ID
		List<Cart> cartItemsToRemove = cartRepository.findByProductPdId(pdId);

		// Loop through the cart items and delete them
		for (Cart cartItem : cartItemsToRemove) {
			cartRepository.deleteById(cartItem.getCartsId());
		}

		return "redirect:/cart";
	}

	@GetMapping("/clear-cart")
	public String clearCart() {
		cartRepository.deleteAll(); // Delete all cart items
		return "redirect:/cart";
	}

	@Autowired
	OrderRepository orderRepository;

	@GetMapping("/select-items-for-order")
	public String selectItemsForOrder(Model model) {
		List<Cart> cartItems = cartRepository.findAll();
		model.addAttribute("cartItems", cartItems);
		return "select-items-for-order"; // Create a corresponding HTML template for this view
	}

	@PostMapping("/order-items")
	public String orderSelectedItems(@RequestParam("selectedItems") List<Integer> selectedItems) {
		// สร้างคำสั่งซื้อใหม่และเพิ่มรายการที่เลือกลงไป
		Order order = new Order();

		for (Integer cartItemId : selectedItems) {
			Optional<Cart> cartItemOptional = cartRepository.findById(cartItemId);

			if (cartItemOptional.isPresent()) {
				Cart cartItem = cartItemOptional.get();

				// สร้าง OrderItem จากรายการรถเข็นและเพิ่มลงในคำสั่งซื้อ
				OrderItem orderItem = new OrderItem();
				orderItem.setProduct(cartItem.getProduct());
				orderItem.setQuantity(cartItem.getCartsQty());
				orderItem.setOrder(order);
				order.addOrderItem(orderItem);

				// ลบรายการที่เลือกออกจากรถเข็น
				cartRepository.delete(cartItem);
			}
		}

		// Save the order to the database
		orderRepository.save(order);

		return "redirect:/cart"; // Redirect back to the cart or another appropriate page
	}

}
