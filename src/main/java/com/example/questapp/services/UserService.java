package com.example.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.questapp.entities.User;
import com.example.questapp.repos.UserRepository;

@Service
public class UserService {
	UserRepository userRepository;
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	public List<User> getAll() {
		return userRepository.findAll();
	}
	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}
	public User getOneUser(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}
	public User updateOneUser(Long userId, User newUser) {
		Optional<User> user=userRepository.findById(userId);
		if(user.isPresent()) {
			User foundUser=user.get();
			foundUser.setUserName(newUser.getUserName());
			foundUser.setPassword(newUser.getPassword());
			userRepository.save(foundUser);
			return foundUser;
		}
		return null;
	}
	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
		
	}
	
}
