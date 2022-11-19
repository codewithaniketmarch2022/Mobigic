package com.app.daos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.DatabaseFile;



@Repository
public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, Integer> {

	
	 @Query("SELECT f FROM DatabaseFile f WHERE f.userId=?1")
      List<DatabaseFile>  findfileByUserId(int userId);
	
	@Modifying
	@Query("DELETE DatabaseFile f  WHERE f.id=?1")
	public void deleteFile(int id);
	
	

}