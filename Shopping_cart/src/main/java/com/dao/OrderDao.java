package com.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.*;
import com.dao.*;

public class OrderDao {


	private Connection con;
	private String q;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public OrderDao(Connection con) {
		
		this.con = con;
	
	}
	
	public boolean insertOrder(Order model) {
		
		boolean result =false;
		try{
			
			q="insert into orders(p_id,u_id,O_quantity,o_date) values(?,?,?,?)";
			
			pst=this.con.prepareStatement(q);
			pst.setInt(1, model.getId());
			pst.setInt(2, model.getUid());
			pst.setInt(3, model.getQuantity());
			pst.setString(4, model.getDate());
			pst.executeUpdate();
			result=true;
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return result;
		
	}
	public List<Order>userOrders(int id)
	{
	List<Order> list=new ArrayList<Order>();
	try {
		q="select * from orders where u_id=? order by orders.o_id desc";
		
		pst=this.con.prepareStatement(q);
		pst.setInt(1, id);
		rs=pst.executeQuery();
		
		while(rs.next())
		{
			Order order=new Order();
			ProductDao pd=new ProductDao(this.con);
			int pId=rs.getInt("p_id");
			
			product product=pd.getSingleProduct(pId);
			order.setId(rs.getInt("o_id"));
			order.setId(pId);
			order.setName(product.getName());
			order.setCategory(product.getCategory());
			order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
			order.setQuantity(rs.getInt("o_quantity"));
			order.setDate(rs.getString("o_date"));
			list.add(order);
			
			
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return list;
		
	}
	
	public void cancelOrder(int id)
	{
		try {
			
			q="delete from orders where p_id=?";
			pst=this.con.prepareStatement(q);
			pst.setInt(1, id);
			pst.executeLargeUpdate();
			System.out.println("cancel order");
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
}
