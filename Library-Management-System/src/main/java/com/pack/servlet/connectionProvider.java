package com.pack.servlet;

import java.sql.Connection;
import java.sql.DriverManager;

public final class connectionProvider {
private connectionProvider() {
		
	}

public static Connection getConnection () {
	Connection con = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","Anynomous");
		
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return con;
}
}
