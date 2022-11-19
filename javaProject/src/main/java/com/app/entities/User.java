package com.app.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "user_id")
	private int id;
	private String username;

	private String password;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List <DatabaseFile> file;
//	private int photo_id;

	public User(int id, String username, String password, List<DatabaseFile> file) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.file = file;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<DatabaseFile> getFile() {
		return file;
	}

	public void setFile(List<DatabaseFile> file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, username=%s, password=%s, file=%s]", id, username, password, file);
	}

 
	
	
	

}
