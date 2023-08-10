package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import com.model.*;
public class ProductDao {
	private Connection con;
	private String q;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection con) {
	
		this.con = con;
	}
	
	public List<product>getAllProducts() {
		List<product> products=new ArrayList<product>();
		try {
			q="select * from products";
		    pst=this.con.prepareStatement(q);
		    rs=pst.executeQuery();
		    
		    while(rs.next())
		    {
		    	product row=new product();
		    	row.setId(rs.getInt("id"));
		    	row.setName(rs.getString("name"));
		    	row.setCategory(rs.getString("category"));
		    	row.setPrice(rs.getDouble("price"));
		    	row.setImage(rs.getString("image"));
		    	products.add(row);
		    	
		    }
	}catch(Exception e){
		e.printStackTrace();
		
	}
		return products;
		
	}
	
	public List<cart> getCartProducts(ArrayList<cart> cartlist){
		List<cart>products =new ArrayList<cart>();
		try {
			if(cartlist.size()>0)
			{
				for(cart item:cartlist)
				{
					q="select * from products where id=?";
					pst=this.con.prepareStatement(q);
					pst.setInt(1,item.getId());
					rs=pst.executeQuery();
					while(rs.next()) {
						cart row=new cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
					}
				}
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return products;
		
	}
	
	public List<cart> getCartProducts1(ArrayList<cart>cartlist){
		List<cart>products=new ArrayList<cart>();
		
		try {
			
			if(cartlist.size()>0)
			{
				for(cart item:cartlist)
				{
					q="select * from products where id=?";
					pst=this.con.prepareStatement(q);
					pst.setInt(1, item.getId());
					rs=pst.executeQuery();
					while(rs.next()) {
						cart row =new cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
						
					}
					
					
				}
			}
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return products;
	}
	
	public product getSingleProduct(int id) {
		product row =null;
		try {
			q="select * from products where id=?";
			pst=this.con.prepareStatement(q);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			
			while(rs.next())
			{
				row=new product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return row;
	}
	
	
	
	
	
	public double getTotalCartPrice(ArrayList<cart> cartlist) {
	double sum=0;
	
	try {
		if(cartlist.size()>0)
		{
			for(cart item:cartlist)
			{
		q="select price from products where id=? ";
		this.con.prepareStatement(q);
		pst.setInt(1, item.getId());
		rs=pst.executeQuery();
		
		while(rs.next()) {
			sum+=rs.getDouble("price")*item.getQuantity();
		}
		}
		}
	}catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	return sum;
		
	}
	
}
