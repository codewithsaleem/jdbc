package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class TransactionBank2 {
	public static void main(String[] args) {
	
	String url = "jdbc:mysql://localhost:3306/jdbc";
	String un = "root";
	String pwd = "saleem";
	Connection con = null;
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded successfully");
		
		con = DriverManager.getConnection(url,un,pwd);
		System.out.println("Connection Established");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Account number");
		int acc_num = sc.nextInt();
		System.out.println("Enter Pin number");
		int pin = sc.nextInt();
		
		PreparedStatement pstmt1 = con.prepareStatement("select * from account where " + "acc_num = ? and pin = ? ");
		pstmt1.setInt(1, acc_num);
		pstmt1.setInt(2, pin);
	    
		ResultSet rs1 = pstmt1.executeQuery();
		
		rs1.next();
		String name = rs1.getString(2);
		int bal = rs1.getInt(4);
		
		System.out.println("welcome : "+name);
		System.out.println("avalable balance is : "+bal);
		
		
		System.out.println("---Transfer Details---");
		System.out.println("Enter Account number");
		int bacc_num = sc.nextInt();
		System.out.println("Enter transfer ammount");
		int t_ammount = sc.nextInt();
		
		PreparedStatement pstmt2 = con.prepareStatement("update account set balance = balance + ? where acc_num = ? ");
		
		pstmt2.setInt(1, t_ammount);
		pstmt2.setInt(2, acc_num);
		pstmt2.executeUpdate();
		
		System.out.println("---Incoming Credit Request---");
		
		System.out.println(name + " account no " + acc_num + " wants to transfer " +t_ammount);
		System.out.println("Press Y to recieve");
		System.out.println("Press N to reject");
		
		String choice = sc.next();
		
		if(choice.equals("Y")) {
			   PreparedStatement pstmt3 = con.prepareStatement("update account set balance = balance - ? where acc_num = ? ");
			   pstmt3.setInt(1, t_ammount);
			   pstmt3.setInt(2, acc_num);
			   pstmt3.executeUpdate();
			   
			   PreparedStatement pstmt4 = con.prepareStatement("select * from account where acc_num = ? ");
			   pstmt4.setInt(1, bacc_num);
			   ResultSet rs2 = pstmt4.executeQuery();
			   rs2.next();
			   
			   System.out.println("Updated balance is: "+rs2.getInt(4));
			   
			}
		else {
			PreparedStatement pstmt5 = con.prepareStatement("select * from account " + "where acc_num = ? ");
			pstmt5.setInt(1,acc_num);
			ResultSet rs2 = pstmt5.executeQuery();
			rs2.next();
			System.out.println("Existing balance is: ");
		   }
	    }catch(Exception e) {
	    	    e.printStackTrace();
	    	}
	    }
}
