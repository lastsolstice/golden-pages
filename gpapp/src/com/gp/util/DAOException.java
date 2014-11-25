package com.gp.util;

import java.sql.SQLException;

public class DAOException extends SQLException {
	public DAOException(){
		super();
	}
	public DAOException(String message){
		super(message);
	}
}
