package com.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 7 * 1024 * 1024, maxRequestSize = 5 * 7 * 1024 * 1024)
public class UploadPicture{
	//JDBC部份
	private static final String URL = "jdbc:oracle:thin:@192.168.99.100:49161:XE";
	private static final String USER = "easyfood";
	private static final String PASSWORD = "easyfood";
	private static final String SQL = "UPDATE STORE SET STR_IMG = ? " + " WHERE STR_NO = ?";

	


	public static void updatePicture(String Str_no,String path){
		
		

	
	
		//------------------(以下)JDBC部份------------------------
		

		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);
	   //------------------(以上)JDBC部份------------------------

                
				//------------------(以下)JDBC部份------------------------
				// setBlob
				
				pstmt.setString(2, Str_no);
				byte[] pic = getPictureByteArray(path);
				pstmt.setBytes(1, pic);
				pstmt.addBatch();
				
				System.out.println("SQL之"+path+"新增成功");
				
			
			
		pstmt.executeBatch();
		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
		
			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
		//------------------(以上)JDBC部份------------------------
	}
	
	
	
	// 使用byte[]方式 (JDBC部份)
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}  
		baos.close();
		fis.close();

		return baos.toByteArray();  
	}
}
