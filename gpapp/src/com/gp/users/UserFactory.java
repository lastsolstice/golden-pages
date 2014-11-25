package com.gp.users;

import java.sql.SQLException;

public class UserFactory {
	
	
	// TODO Auto-generated method stub
	public  void createBusinessUser(String bizName, UserDTO user){
		user.setType(UserDTO.Type.BIZ_USER);
		
		
		
	}
	// TODO Auto-generated method stub
	public void createConsumerUser(UserDTO user) throws Exception{
		UserDAO dao = new UserDAO("jdbc/gpappdb");
		dao.create(user);
	}
	
	
	

}
