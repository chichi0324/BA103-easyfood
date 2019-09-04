package com.store.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.Context;
import javax.sql.DataSource;

public class readStorePicture extends HttpServlet {

	Connection con;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			                         throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {

			Statement stmt = con.createStatement();
			String str_no  = req.getParameter("STR_NO");
			
//			System.out.println(str_no);
			
			String myStore = new String(str_no.getBytes("ISO-8859-1"), "Big5");
			ResultSet rs = stmt.executeQuery("SELECT * FROM STORE WHERE STR_NO = '"+ myStore +"'");
			
//			System.out.println(myStore);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("STR_IMG"));
				byte[] buf = new byte[4 * 1024];  // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
			  in.close();
			} else {
				  //res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/NoData/nopic.png");
				byte[] buf =new byte [in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
      rs.close();
      stmt.close();
		} catch (Exception e) {
			  //System.out.println(e);
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
