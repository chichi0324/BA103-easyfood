package com.dish.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.Context;
import javax.sql.DataSource;

public class readDishPicture extends HttpServlet {

	Connection con;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			                         throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {

			Statement stmt = con.createStatement();
			String dish_no  = req.getParameter("DISH_NO");
			String myDish = new String(dish_no.getBytes("ISO-8859-1"), "UTF-8");
			ResultSet rs = stmt.executeQuery("SELECT DISH_IMG FROM DISH WHERE DISH_NO = '"+ myDish +"'");
			
//			System.out.println(myDish);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("DISH_IMG"));
				byte[] buf = new byte[4 * 1024];  // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
			  in.close();
			} else {
				InputStream in = getServletContext().getResourceAsStream("/NoData/nopic.png");
				byte[] buf =new byte [in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/NoData/nopic.png");
			byte[] buf =new byte [in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
			con = ds.getConnection();
		} catch (Exception e) {
			  System.out.println(e.getMessage());
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			  System.out.println(e);
		}
	}
	
	
}
