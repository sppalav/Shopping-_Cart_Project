package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.*;
import com.dao.*;


public class RegisterDao {
	
	private Connection con;
	private String q;
	private PreparedStatement pst;
	
	
	
public RegisterDao(Connection con) {
	
		this.con = con;
	}




public boolean insertregister(Register model) {
		
		boolean result =false;
		try{
			
			q="insert into users (name,email,password)values(?,?,?)";
			
			pst=this.con.prepareStatement(q);
		    pst.setString(1,model.getName());
		    pst.setString(2,model.getEmail());
		    pst.setString(3,model.getPassword());
			pst.executeUpdate();
	
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return result;

}
}
