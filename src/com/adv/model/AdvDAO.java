package com.adv.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdvDAO implements AdvDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
		private static final String GET_ALL_STR_ADV = 
			"SELECT ADV_NO,ADV_STR,ADV_END,ADV_TXT,ADV_STA FROM ADVERTISING  where STR_NO = ?";
		private static final String GET_ALL_STR_AUDIT =
			"SELECT ADV_NO,STR_NO,ADV_STR,ADV_END,ADV_TXT,ADV_STA FROM ADVERTISING  where ADV_STA = '待審核'";
		private static final String GET_ALL_STR_AUDIT_OK =
			"SELECT ADV_NO,STR_NO,ADV_STR,ADV_END,ADV_TXT,ADV_STA FROM ADVERTISING  where ADV_STA = '通過'";	
		
	
		@Override
		public void insert(AdvVO advVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				
				pstmt.setString(1,advVO.getStr_no());
				pstmt.setDate(2,advVO.getAdv_str());
				pstmt.setDate(3,advVO.getAdv_end());
				pstmt.setBytes(4,advVO.getAdv_txt());
				pstmt.setString(5,advVO.getAdv_sta());
				
				pstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	
		@Override
		public void update(AdvVO advVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, advVO.getAdv_sta());
				pstmt.setString(2, advVO.getAdv_no());
				
				pstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
		@Override
		public void delete(String adv_no) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();;
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1,adv_no);
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
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
				con = ds.getConnection();
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
			con = ds.getConnection();
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
		public List<AdvVO> getAllStrAdv(String str_no) {
			List<AdvVO> list =  new ArrayList<AdvVO>();
			AdvVO advVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
	try {		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STR_ADV);
			pstmt.setString(1, str_no);
	
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
			advVO = new AdvVO();
			advVO.setAdv_no(rs.getString("adv_no"));
			advVO.setAdv_end(rs.getDate("adv_end"));
			advVO.setAdv_str(rs.getDate("adv_str"));
			advVO.setAdv_txt(rs.getBytes("adv_txt"));
			advVO.setAdv_sta(rs.getString("adv_sta"));
			list.add(advVO);
		}	
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STR_AUDIT);
					
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
		public List<AdvVO> getAllStrAuditOk() {
			List<AdvVO> list =  new ArrayList<AdvVO>();
			AdvVO advVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
	try {		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STR_AUDIT_OK);
					
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
		

		
}
