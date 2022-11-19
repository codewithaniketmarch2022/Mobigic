package com.app.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.User;


@Transactional
public interface UserDao extends JpaRepository<User,Integer>{
	
	User findById(int id);
	
	User findByUsername(String username);
	

//	  @Query ("select u.address,u.mobile_no,u.password from User u WHERE u.id = ?1")
//	  int findIdByUserId(String aadhar,String aadhar,int id);
	  
//	  @Query ("select u.id from User u WHERE u.aadhar_no = ?1")
	  
	  
//	  SELECT f.data FROM rto_management.files f INNER JOIN rto_management.users u ON f.id=u.photo_id WHERE u.user_id=1;
	  
	  
//	  @Query("SELECT s.artistList FROM Song s INNER JOIN s.album al WHERE al.id=:p_albumId")
	  
	  
//	  @Query("SELECT f.data FROM DatabaseFile f INNER JOIN f.User u  ON f.id=u.photo_id WHERE u.id=?1")
//	  byte[]  findPhotoById(int  user_id);
	  
//		@Modifying
//		@Query("UPDATE User u SET u.address=?1,u.mobile_no=?2,u.password=?3  WHERE u.id=?4")
//		public void updateUser(String address, long mobile_no,String password,int id);
	  
//	  @Modifying
//		@Query("UPDATE User u SET u.address=?1,u.mobile_no=?2,u.password=?3,u.status=?4  WHERE u.id=?5")
//		public void updateUser(String address, long mobile_no,String password,String status,int id);
//		
//		
//		@Modifying
//		@Query("UPDATE User u SET u.password=?1  WHERE u.id=?2")
//		public void updateUserPassword(String password,int id);
//		
//		@Modifying
//		@Query("UPDATE User u SET u.status=?1  WHERE u.id=?2")
//		public void updateUserStatus(String status,int id);
//		
//		 @Query("select count(*) from User u")
//			Integer userCount();

}
