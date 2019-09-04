package com.blre.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.blog.model.BlogVO;
import com.tools.tools;

import oracle.sql.CLOB;


public class BlreJDBCDAO implements BlreDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "easyfood";
	String passwd = "easyfood";
	
	private static final String INSERT_STMT = "INSERT INTO BLOGRESPONSE (blre_no,blre_con,bl_no,mem_no,blre_date) VALUES ('BLRE_'||LPAD(TO_CHAR(BLRE_SQ.NEXTVAL),4,'0'), ?, ?,?,?)";
	
	private static final String GET_ALL_STMT = "SELECT blre_no,blre_con,bl_no,mem_no,blre_date FROM BLOGRESPONSE";
	private static final String GET_ONE_STMT = "SELECT blre_no,blre_con,bl_no,mem_no,blre_date FROM BLOGRESPONSE where blre_no = ? ";
	private static final String GET_ALL_BYMEM = "SELECT blre_no,blre_con,bl_no,mem_no,blre_date FROM BLOGRESPONSE where mem_no = ? order by blre_date desc";
	
	private static final String DELETE= "DELETE FROM BLOGRESPONSE where blre_no = ?";

	private static final String UPDATE = "UPDATE BLOGRESPONSE set blre_con=?,bl_no=?,mem_no=?,blre_date=? where blre_no= ?";

	@Override
	public void insert(BlreVO blreVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setClob(1, new tools().stringToClob(blreVO.getBlre_con(), con));
			pstmt.setString(2, blreVO.getBl_no());
			pstmt.setString(3, blreVO.getMem_no());
			pstmt.setTimestamp(4, blreVO.getBlre_date());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	public void update(BlreVO  blreVO){

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setClob(1, new tools().stringToClob(blreVO.getBlre_con(), con));
			pstmt.setString(2, blreVO.getBl_no());
			pstmt.setString(3, blreVO.getMem_no());
			pstmt.setTimestamp(4, blreVO.getBlre_date());
			pstmt.setString(5, blreVO.getBlre_no());

			pstmt.executeUpdate();


			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	public void delete(String blre_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, blre_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	public BlreVO findByPrimaryKey(String blre_no) {

		BlreVO blreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, blre_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				blreVO = new BlreVO();
				blreVO.setBlre_no(rs.getString(1));
				blreVO.setBlre_con(new tools().clobToString(rs.getClob(2)));
				blreVO.setBl_no(rs.getString(3));
				blreVO.setMem_no(rs.getString(4));
				blreVO.setBlre_date(rs.getTimestamp(5));
				
//				blreVO = new BlreVO();
//				blreVO.setBlre_no(rs.getString("blre_no"));
//				blreVO.setBlre_con(readString(rs.getClob("blre_con")));
//				blreVO.setBl_no(rs.getString("bl_no"));
//				blreVO.setMem_no(rs.getString("mem_no"));
//				blreVO.setBlre_date(rs.getDate("blre_date"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		return blreVO;
	}
	
	@Override
	public List<BlreVO> findByMember(String mem_no) {

		List<BlreVO> list = new ArrayList<BlreVO>();
		BlreVO blreVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BYMEM);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				blreVO = new BlreVO();				
				blreVO.setBlre_no(rs.getString(1));
				blreVO.setBlre_con(new tools().clobToString(rs.getClob(2)));				
				blreVO.setBl_no(rs.getString(3));
				blreVO.setMem_no(rs.getString(4));
				blreVO.setBlre_date(rs.getTimestamp(5));
				list.add(blreVO);
				
//				blogVO = new BlogVO();				
//				blogVO.setBl_no(rs.getString("bl_no"));
//				blogVO.setBl_name(rs.getString("bl_name"));				
//				blogVO.setBl_con(readString(rs.getClob("bl_con")));
////				blogVO.setBl_con(rs.getString("bl_con"));
//				blogVO.setBl_date(rs.getDate("bl_date"));
//				blogVO.setMem_no(rs.getString("mem_no"));
//				blogVO.setStr_no(rs.getString("str_no"));
				
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	public List<BlreVO> getAll() {
		List<BlreVO> list = new ArrayList<BlreVO>();
		BlreVO blreVO = null;

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
				blreVO = new BlreVO();
				blreVO.setBlre_no(rs.getString(1));
				blreVO.setBlre_con(new tools().clobToString(rs.getClob(2)));				
				blreVO.setBl_no(rs.getString(3));
				blreVO.setMem_no(rs.getString(4));
				blreVO.setBlre_date(rs.getTimestamp(5));
				list.add(blreVO); // Store the row in the list
				
//				blreVO = new BlreVO();
//				blreVO.setBlre_no(rs.getString("blre_no"));
//				blreVO.setBlre_con(readString(rs.getClob("blre_con")));				
//				blreVO.setBl_no(rs.getString("bl_no"));
//				blreVO.setMem_no(rs.getString("mem_no"));
//				blreVO.setBlre_date(rs.getDate("blre_date"));
//				list.add(blreVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
