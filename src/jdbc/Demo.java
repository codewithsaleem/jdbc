package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args)
    {
 	   String url="jdbc:mysql://localhost:3306/youtube";
       String un="root";
       String pwd="saleem";
       
       Statement stmt = null;
       ResultSet rs = null;
       Connection con = null;
       PreparedStatement pstmt = null;
       
 	   try{
 		   Class.forName("com.mysql.cj.jdbc.Driver");
 		   System.out.println("Driver loaded successfully");
 		   
 		   con=DriverManager.getConnection(url, un, pwd);
 		   System.out.println("Connection established");
 		   
 		   String query="insert into user (id,name,password) values (?,?,?)";
 		  
 		   pstmt = con.prepareStatement(query);
 		   Scanner sc=new Scanner(System.in);
 		   System.out.println("Enter the number of rows to be inserted");
           int n=sc.nextInt();
           
           con.setAutoCommit(false);
           
           for(int i=1;i<=n;i++)
           {
        	   int id = sc.nextInt();
        	   String name = sc.next();
        	   String password = sc.next();
        	   pstmt.setInt(1, id);
     		   pstmt.setString(2, name);
     		   pstmt.setString(3, password);
     		   pstmt.execute();
     		   System.out.println("Query executed");
           }
           
           con.commit();
 	   }            
 	   catch(ClassNotFoundException e)
 	   {
 		   e.printStackTrace();
 	   }
 	   catch(SQLException e)
 	   {
 		   e.printStackTrace();
 	   }
 	   try{
 		   
 		   pstmt.close();
 		   con.close();
 	   }
 	   catch(SQLException e) {
 		   e.printStackTrace();
 	   }
    }
}
