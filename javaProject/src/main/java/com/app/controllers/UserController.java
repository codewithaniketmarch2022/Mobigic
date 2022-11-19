package com.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.daos.DatabaseFileRepository;
import com.app.daos.UserDao;
import com.app.entities.*;

import com.app.entities.DatabaseFile;
import com.app.entities.User;
import com.app.entities.*;
import com.app.services.DatabaseFileService;

import com.app.services.UserServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DatabaseFileService fileStorageService;

	DatabaseFile fileName;
	
	private int userId;

	@Autowired
	DatabaseFileRepository databaseFileRepository;

	User user;

	DatabaseFile databaseFile;

	private int randomNumber;
	

	@PostMapping("/uploadFile")
	public Responsef uploadFile(@RequestParam("file") MultipartFile file) {
	
		fileName = fileStorageService.storeFile(file);
		
        fileName.setUserId(userId);
		
		System.out.println(userId);
		
		databaseFileRepository.save(fileName);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName.getFileName()).toUriString();
		
	

		return new Responsef(fileName.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signIn( @RequestBody Credentials cred) {
		User user = userService.findUserByUsernameAndPassword(cred);
		if (user == null)
			return Response.error("user not found");
		
		userId=user.getId();
		
		return Response.success(user);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody User user) {
		
		try {
			Credentials cred = new Credentials();
			cred.setUsername(user.getUsername());
			cred.setPassword(user.getPassword());

			User users = userService.findUserByUsernameAndPassword(cred);

			User result = userService.saveUser(user);
			
			System.out.println(result.getPassword());
			
			return Response.success(result);
			
		} catch (Exception e) {

			return Response.error("Enter valid  username or username already registered");
		}

	}

	
	@GetMapping("/search")
	public ResponseEntity<?> findUser() {
		List<User> result = new ArrayList<>();
		result = userService.findAllUsers();
		return Response.success(result);
	}


	@GetMapping("/files/{id}")
	public ResponseEntity<List<DatabaseFile>> getFilesByUserId(@PathVariable int id) {
		List<DatabaseFile>files = databaseFileRepository.findfileByUserId(id);
		
		if (files == null) {
			return (ResponseEntity<List<DatabaseFile>>) Response.error("User not exist with id :" + id);
		}

		return ResponseEntity.ok(files);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<User> getFileByUserId(@PathVariable int id) {
		User user = userService.findUserFromdbById(id);
		
		if (user == null) {
			return (ResponseEntity<User>) Response.error("User not exist with id :" + id);
		}

		return ResponseEntity.ok(user);
	}

	// delete User rest api
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable int id) {
		User user = userService.findUserFromdbById(id);
		if (user == null) {
			return (ResponseEntity<Map<String, Boolean>>) Response.error("User not exist with id :" + id);
		}
//					.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

		userDao.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	@DeleteMapping("deleteFile/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteFile(@PathVariable int id) {
		@SuppressWarnings("deprecation")
		DatabaseFile file = databaseFileRepository.getOne(id);
		if (file == null) {
			return (ResponseEntity<Map<String, Boolean>>) Response.error("file doese not exists");
		}
//					.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

		databaseFileRepository.deleteById(file.getId());
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	

//	@PostMapping("/forgotPasswordinit")
//	public ResponseEntity<?> forgotPassword( @RequestBody Credentials cred) throws MessagingException {
//		
//		User user = userService.findUserFromdbByEmail(cred.getEmail());
//		
//		Random random = new Random();   
//		
//		
//		randomNumber = random.nextInt(10000);  
//		
//		System.out.println("random number: "+randomNumber);
//		
//		if (user == null)
//			return Response.error("user not found");
//		
//		emailSenderService.sendSimpleEmail(user.getEmail(), "Dear " + user.getName() + ",\n\n"
//				+ "Your OTP for password Reset is [ " + randomNumber+ " ] .\n"
//				+ "\n" + "Warm Regards,\n" + "RTO Info Group,\n", "Password reset request");
//	
//		return Response.success(user);
//	}
//	
//	
//	@PostMapping("/forgotPasswordprocess")
//	public ResponseEntity<?> forgotPasswordprocessing( @RequestBody Credentials cred) throws MessagingException {
//		
//		User user = userService.findUserFromdbByEmail(cred.getEmail());
//		
//		  if( cred.getOtp()==randomNumber) {
//			  
//			  String rawPassword = cred.getPassword();
//			  
//				String encPassword = passwordEncoder.encode(rawPassword);
//				
//			  user.setPassword(encPassword);
//			  
////			  System.out.println("enc paasword "+encPassword);
//			  
//			  userDao.updateUserPassword(encPassword, user.getId());
//
//				if (user == null)
//					return Response.error("user not found");
//				
//				emailSenderService.sendSimpleEmail(user.getEmail(), "Dear " + user.getName() + ",\n\n"
//						+ "Your password for rto management website is successfully changed.\n"
//						+ "\n" + "Warm Regards,\n" + "RTO Info Group,\n", "Your password have been reset ");
//			
//				
//				randomNumber=0;
//				
//				return Response.success(user);
//			  
//		  }
//		  
//			return Response.error("Please enter valid otp!!!!");
//		  
//		
//
//		
//	}



}
