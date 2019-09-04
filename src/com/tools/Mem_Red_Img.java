package com.tools;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class Mem_Red_Img extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String no="";
			String no_value="";
			String img="";
			String table="";
			if(req.getParameter("str_no")!=null){
				no_value=req.getParameter("str_no");
				no="STR_NO";
				img="STR_IMG";
				table="STORE";
			}else if(req.getParameter("stoca_no")!=null){
				no_value=req.getParameter("stoca_no");
				no="STOCA_NO";
				img="STOCA_IMG";
				table="STORECATEGORY";
			}else if(req.getParameter("adv_no")!=null){
				no_value=req.getParameter("adv_no");
				no="ADV_NO";
				img="ADV_TXT";
				table="ADVERTISING";
			}else if(req.getParameter("mem_no")!=null){
				no_value=req.getParameter("mem_no");
				no="MEM_NO";
				img="MEM_IMG";
				table="MEMBER";
			}else if(req.getParameter("dish_no")!=null){
				no_value=req.getParameter("dish_no");
				no="DISH_NO";
				img="DISH_IMG";
				table="DISH";
			}
			
			ResultSet rs = stmt.executeQuery(
				"SELECT "+img+" FROM "+table+" WHERE "+no+" = '"+no_value+"' ");
			
			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(img));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
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
		} 
		catch (Exception e) {
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
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			
			
		}
	}
}
