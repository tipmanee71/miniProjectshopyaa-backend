package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Reponsitory.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void registerUser(User user) {
		// Implement user registration logic here.
		userRepository.save(user);
	}



}
