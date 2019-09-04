package com.adv.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class AdvJDBCDAO implements AdvDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "easyfood";
	String passwd = "easyfood";
	
	private static final String INSERT_STMT = 
			"INSERT INTO ADVERTISING (ADV_NO,STR_NO,ADV_STR,ADV_END,ADV_TXT,ADV_STA) VALUES('ADV_'||LPAD(TO_CHAR(ADV_SQ.NEXTVAL),4,'0'),?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT ADV_NO,STR_NO,ADV_STR,ADV_END,ADV_TXT,ADV_STA FROM ADVERTISING  order by ADV_NO";
		private static final String GET_ONE_STMT = 
			"SELECT ADV_NO,STR_NO,ADV_STR,ADV_END,ADV_TXT,ADV_STA FROM ADVERTISING  where ADV_NO = ?";
		private static final String DELETE = 
			"DELETE FROM ADVERTISING where ADV_NO = ?";
		private static final String UPDATE = 
			"UPDATE ADVERTISING set ADV_STA=? where ADV_NO = ?";
		private static final String GET_ALL_STR_AUDIT =
			"SELECT ADV_NO,STR_NO,ADV_STR,ADV_END,ADV_TXT,ADV_STA FROM ADVERTISING  where ADV_STA = '待審核'";	
		
	
		@Override
		public void insert(AdvVO advVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				
				pstmt.setString(1,advVO.getStr_no());
				pstmt.setDate(2,advVO.getAdv_str());
				pstmt.setDate(3,advVO.getAdv_end());
				pstmt.setBytes(4,advVO.getAdv_txt());
				pstmt.setString(5,advVO.getAdv_sta());
				
				pstmt.executeUpdate();
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	
		@Override
		public void update(AdvVO advVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, advVO.getAdv_sta());
				pstmt.setString(2, advVO.getAdv_no());
				
				pstmt.executeUpdate();
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
		@Override
		public void delete(String adv_no) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1,adv_no);
				
				pstmt.executeUpdate();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
		@Override
		public AdvVO findByPrimaryKey(String adv_no) {
			
				
				AdvVO advVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
		try {		
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setString(1, adv_no);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
				advVO = new AdvVO();
				advVO.setAdv_no(rs.getString("adv_no"));
				advVO.setStr_no(rs.getString("str_no"));
				advVO.setAdv_str(rs.getDate("adv_end"));
				advVO.setAdv_end(rs.getDate("adv_str"));
				advVO.setAdv_txt(rs.getBytes("adv_txt"));
				advVO.setAdv_sta(rs.getString("adv_sta"));
			}	
				
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
			
			return advVO;
		}
	
		@Override
		public List<AdvVO> getAll() {
			
			List<AdvVO> list =  new ArrayList<AdvVO>();
			AdvVO advVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
	try {		
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
	
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
			advVO = new AdvVO();
			advVO.setAdv_no(rs.getString("adv_no"));
			advVO.setStr_no(rs.getString("str_no"));
			advVO.setAdv_end(rs.getDate("adv_end"));
			advVO.setAdv_str(rs.getDate("adv_str"));
			advVO.setAdv_txt(rs.getBytes("adv_txt"));
			advVO.setAdv_sta(rs.getString("adv_sta"));
			list.add(advVO);
		}	
			
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
			
			return list;
		}
		
		@Override
		public List<AdvVO> getAllStrAudit() {
			List<AdvVO> list =  new ArrayList<AdvVO>();
			AdvVO advVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
	try {		
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement( GET_ALL_STR_AUDIT);
			
	
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
			advVO = new AdvVO();
			advVO.setAdv_no(rs.getString("adv_no"));
			advVO.setStr_no(rs.getString("str_no"));
			advVO.setAdv_end(rs.getDate("adv_end"));
			advVO.setAdv_str(rs.getDate("adv_str"));
			advVO.setAdv_txt(rs.getBytes("adv_txt"));
			advVO.setAdv_sta(rs.getString("adv_sta"));
			list.add(advVO);
		}	
			
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
			
			return list;
		}
		
		
		public static void main(String[] args) throws IOException {
			
			AdvJDBCDAO dao = new AdvJDBCDAO();

//--------------新增-------------------------
//			AdvVO advVO1 = new AdvVO();
//			
//			advVO1.setStr_no("STR_0001");
//			advVO1.setAdv_str(java.sql.Date.valueOf("2017-10-01"));
//			advVO1.setAdv_end(java.sql.Date.valueOf("2017-11-01"));
//			advVO1.setAdv_txt(getPictureByteArray("D://me.jpg"));
//			advVO1.setAdv_sta("未審核");
//			
//			dao.insert(advVO1);

			
//--------------修改不通過------------------		
//			AdvVO advVO2= new AdvVO();
//			
//			advVO2.setAdv_sta("不通過");
//			advVO2.setAdv_no("ADV_0001");
//			
//			dao.update(advVO2);

//--------------查詢單一-----------------			
//			AdvVO advVO3 = dao.findByPrimaryKey("ADV_0003");
//			System.out.print(advVO3.getAdv_no() + ",");
//			System.out.print(advVO3.getStr_no() + ",");
//			System.out.print(advVO3.getAdv_str() + ",");
//			System.out.print(advVO3.getAdv_end() + ",");
//			System.out.print(advVO3.getAdv_txt() + ",");
//			System.out.print(advVO3.getAdv_sta());
//			
//---------------查詢全部-------------			
			List<AdvVO> list = dao.getAllStrAudit();
			for (AdvVO aadv : list) {
				System.out.print(aadv.getAdv_no() + ",");
				System.out.print(aadv.getStr_no() + ",");
				System.out.print(aadv.getAdv_str() + ",");
				System.out.print(aadv.getAdv_end() + ",");
				System.out.print(aadv.getAdv_txt() + ",");
				System.out.print(aadv.getAdv_sta());
			}
//----------------刪除-----------------			
//			dao.delete("ADV_0002");
//			
		}

		private static byte[] getPictureByteArray(String path) throws IOException {
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

		@Override
		public List<AdvVO> getAllStrAdv(String str_no) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<AdvVO> getAllStrAuditOk() {
			// TODO Auto-generated method stub
			return null;
		}


}
