/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author giuli
 */
public class Conexion {
    
    public Connection getConnection() {//throws SQLException, ClassNotFoundException

		//String conString="jdbc:"+type+"://"+dir+":"+port+"/"+database;
		//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conn = null;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/alquiler_peliculas?serverTimezone=UTC", "root", "sodastereo2662");
			 //Statement stmt = conn.createStatement();
			 
		} catch(ClassNotFoundException e){
			System.out.print(e.getMessage());
			e.printStackTrace();
		}  catch(SQLException e){
			System.out.print(e.getMessage());
			e.printStackTrace();
		}		
		
		return conn;
		//Statement stmt = con.createStatement();

	}
    
}
