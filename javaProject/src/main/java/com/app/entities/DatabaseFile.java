package com.app.entities;

import org.hibernate.annotations.GenericGenerator;

import java.util.Arrays;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class DatabaseFile {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    private int id;
	private String fileName;
	private String fileType;
	private int code;
	private int userId;


	@Lob
	private byte[] data;

	public DatabaseFile() {

	}

	public DatabaseFile( String fileName, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	public int getId() {
		return id;
	}

//	public DatabaseFile(String fileName, String fileType, int userId, byte[] data) {
//		this.fileName = fileName;
//		this.fileType = fileType;
//		this.userId = userId;
//		this.data = data;
//	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("DatabaseFile [id=%s, fileName=%s, fileType=%s, code=%s, userId=%s, data=%s]", id,
				fileName, fileType, code, userId, Arrays.toString(data));
	}

	

	
}