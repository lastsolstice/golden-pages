package com.gp.users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.codec.digest.DigestUtils;

import com.gp.business.BusinessDAO;
import com.gp.business.BusinessDTO;
import com.gp.util.SQLHandler;

public class UserFactory{
	
	private String context;
	
	public UserFactory(String context){
		this.context = context;
	}
	
	
	// TODO Auto-generated method stub
	public  boolean createBusinessUser(String bizName, UserDTO user) throws Exception{
		user.setType(UserDTO.Type.BIZ_USER);
		
		UserDAO userDAO = new UserDAO(context);
		BusinessDAO businessDAO = new BusinessDAO(context);
		BusinessDTO business = new BusinessDTO();
		business.setBusinessName(bizName);
		business.setDescription("Description Here");
		boolean flag = userDAO.create(user);
		businessDAO.create(business);
		userDAO.addManagesRelation(user,business);
		return flag;
		

		
		
		
		
	}
	// TODO Auto-generated method stub
	public boolean createConsumerUser(UserDTO user) throws Exception{
		user.setType(UserDTO.Type.CONS_USER);
		UserDAO dao = new UserDAO(context);
		return dao.create(user);
	}
	
	
	
	

}
