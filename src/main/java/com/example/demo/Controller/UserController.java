package com.example.demo.Controller;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.User;
import com.example.demo.Reponsitory.UserRepository;

import com.example.demo.service.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;


    
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        try {
            User newUser = new User(
            	user.getUsersName(),
            	user.getUsersPass(),
            	user.getUsersFirst(),
                user.getUsersLast(),
                user.getUsersEmail(),
                user.getUsersAddress(),
                user.getUsersPhone()
            );
            userService.registerUser(newUser);
            return new ResponseEntity<>("Registration successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String usersEmail, @RequestParam String usersPass) {
//        try {
//            if (userService.authenticateUser(usersEmail, usersPass)) {
//                // User successfully logged in
//                return new ResponseEntity<>("Login successful", HttpStatus.OK);
//            } else {
//                // Login failed
//                return new ResponseEntity<>("Login failed: Invalid credentials", HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody User body) {
        User user = userRepository.findByUsersEmail(body.getUsersEmail());
        
        // Check if the user exists
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found.");
        }

        // Check if the provided password matches the user's password
        if (!user.getUsersPass().equals(body.getUsersPass())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    
    @PutMapping("/edit/{usersId}")
	public ResponseEntity<Object> updateUser(@PathVariable Integer usersId, @RequestBody User body) {

		try {
			Optional<User> user = userRepository.findById(usersId);

			if (user.isPresent()) {
				User userEdit = user.get();
				userEdit.setUsersName(body.getUsersName());
				userEdit.setUsersPass(body.getUsersPass());
				userEdit.setUsersFirst(body.getUsersFirst());
				userEdit.setUsersLast(body.getUsersLast());
				userEdit.setUsersEmail(body.getUsersEmail());
				userEdit.setUsersAddress(body.getUsersAddress());
				userEdit.setUsersPhone(body.getUsersPhone());
				
				  userRepository.save(userEdit);

				return new ResponseEntity<>(userEdit, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Employee not found", HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
    
	@GetMapping("/{usersId}")
	public ResponseEntity<Object> getUserDetail(@PathVariable Integer usersId) {

		try {		
			Optional<User> user = userRepository.findById(usersId);
			if(user.isPresent()) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}else {
				return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}



}