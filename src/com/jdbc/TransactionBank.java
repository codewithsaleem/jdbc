package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class TransactionBank {
	public static void main(String[] args)
	{
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
			
		}
		catch(Exception e) {
		    e.printStackTrace();
		}
	}

}
