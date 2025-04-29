package com.pack.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        PrintWriter pw = res.getWriter();
       
        res.setContentType("text/html");
        
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
        
        Connection con;
        try {
        	
        	con =connectionProvider.getConnection(); 
    		PreparedStatement ps = con.prepareStatement("INSERT INTO BOOKDATA(BOOKNAME,BOOKEDITION,BOOKPRICE) VALUES(?,?,?)");
            ps.setString(1, bookName);
            ps.setString(2, bookEdition);
            ps.setFloat(3, bookPrice);
            int count = ps.executeUpdate();
            if (count == 1) {
                pw.println("<h2>Record Is Registered Sucessfully</h2>");
            } else {
                pw.println("<h2>Record not Registered Sucessfully");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h2>");
        }
        pw.println("<a href='home.html'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='bookList'>Book List</a>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}