package com.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.daos.UserDao;

//import com.sunbeam.entities.Blog;
import com.app.entities.User;
import com.app.entities.*;


@Transactional
@Service
public class UserServiceImpl {
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public User findUserFromdbByUsername(String username) {
		User user = userDao.findByUsername(username);
		return user;
	}

	public User findUserFromdbById(int userId) {
		User user = userDao.findById(userId);
		return user;
	}

	

	public User findUserByUsernameAndPassword(Credentials cred) {
		User user = userDao.findByUsername(cred.getUsername());
		String rawPassword = cred.getPassword();
		if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
			
			return user;
		}
		return null;
	}
	
	public List<User> findAllUsers(){
		List<User> userList= userDao.findAll();
		return userList;
	}
	
	
	
	public User saveUser(User user) {
		
		User newUser = findUserFromdbByUsername(user.getUsername());
		if (newUser != null) {		
				return null;
		}

		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		
		user.setPassword(encPassword);
		
		
		
			user = userDao.save(user);
			
			return user;

	}
	
	
	
	public int delete(int id) {
		userDao.deleteById(id);
		return 1;	
	}
}
