package com.fav.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fav.model.FavJDBCDAO;
import com.fav.model.FavVO;
import com.fav.model.FavDAO_interface;

public class FavJDBCDAO implements FavDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "easyfood";
	String passwd = "easyfood";
	
	private static final String INSERT_STMT = 
		"INSERT INTO FAVORITE (MEM_NO,STR_NO) VALUES(?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT MEM_NO,STR_NO FROM FAVORITE  order by MEM_NO";
	private static final String GET_ONE_STMT = 
		"SELECT STR_NO FROM FAVORITE  where MEM_NO = ?";
	private static final String DELETE = 
		"DELETE FROM FAVORITE where MEM_NO=? and STR_NO = ?";
	@Override
	public void insert(FavVO favVO){
		Connection con= null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, favVO.getMem_no());
			pstmt.setString(2, favVO.getStr_no());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally{
			if (pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	
	}
	
	@Override
	public void delete(String mem_no,String str_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,mem_no);
			pstmt.setString(2,str_no);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		
	}


	@Override
	public List<FavVO> findByPrimaryKey(String mem_no) {
		
		List<FavVO> list = new ArrayList<FavVO>();
		FavVO favVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				favVO = new FavVO();
				favVO.setStr_no(rs.getString("str_no"));
				
				list.add(favVO); // Store the row in the list
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
	public List<FavVO> getAll() {
		List<FavVO> list = new ArrayList<FavVO>();
		FavVO favVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				favVO = new FavVO();
				favVO.setMem_no(rs.getString("mem_no"));
				favVO.setStr_no(rs.getString("str_no"));
				
				list.add(favVO); // Store the row in the list
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
	
	public static void main(String[] args) {

		FavJDBCDAO dao = new FavJDBCDAO();
//
//		 
		FavVO FavVO1 = new FavVO();
		FavVO1.setMem_no("MEM_0006");
		FavVO1.setStr_no("STR_0012");
		
		dao.insert(FavVO1);
//
//		// 修改
//		FavVO FavVO2 = new FavVO();
//		FavVO2.setFav_no("FAV_0008");
//		FavVO2.setMem_no("MEM_0004");
//		FavVO2.setStr_no("STR_0001");
////		
//		dao.update(FavVO2);
//
		// 刪除
//		dao.delete("MEM_0006","STR_0011");
//
//		// 查詢
		List<FavVO> list = dao.findByPrimaryKey("MEM_0006");
		for (FavVO favVO3 : list) {			
		System.out.print(favVO3.getStr_no() + ",");
		System.out.println("---------------------");
		}
// 查詢
		List<FavVO> list2 = dao.getAll();
		for (FavVO aFav : list2) {
			System.out.print(aFav.getMem_no() + ",");
			System.out.print(aFav.getStr_no() + ",");			
			System.out.println();
		}
	}
		
}
	