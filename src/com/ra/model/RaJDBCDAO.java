package com.ra.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.blre.model.BlreVO;
import com.tools.tools;


public class RaJDBCDAO implements RaDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "easyfood";
	String passwd = "easyfood";

	private static final String INSERT_STMT = 
		"INSERT INTO reportarticle (ra_no,bl_no,ra_res,ra_rev) VALUES ('RA_'||LPAD(TO_CHAR(RA_SQ.NEXTVAL),4,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ra_no,bl_no,ra_res,ra_rev FROM reportarticle order by ra_no";
	private static final String GET_ONE_STMT = 
		"SELECT ra_no,bl_no,ra_res,ra_rev FROM reportarticle where ra_no = ?";
	private static final String DELETE = 
		"DELETE FROM reportarticle where ra_no = ?";
	private static final String UPDATE = 
		"UPDATE reportarticle set bl_no=?,ra_res=?,ra_rev=? where ra_no = ?";
	private static final String GET_ByRev ="SELECT * FROM reportarticle where ra_rev = ?"; 
	
	@Override
	public void insert(RaVO raVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, raVO.getBl_no());
			pstmt.setClob(2, new tools().stringToClob(raVO.getRa_res(), con));			
			pstmt.setString(3, raVO.getRa_rev());
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
	public void update(RaVO raVO){

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, raVO.getBl_no());
            pstmt.setClob(2, new tools().stringToClob(raVO.getRa_res(), con));			
			pstmt.setString(3, raVO.getRa_rev());
			pstmt.setString(4, raVO.getRa_no());

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
	public void delete(String ra_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,ra_no);

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
	public RaVO findByPrimaryKey(String ra_no) {

		RaVO raVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ra_no);

			rs = pstmt.executeQuery();
			Clob clob=null;
			
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				raVO = new RaVO();
				raVO.setRa_no(rs.getString("ra_no"));
				raVO.setBl_no(rs.getString("bl_no"));
				
				clob = rs.getClob("ra_res");
				raVO.setRa_res(new tools().clobToString(clob));
				
				raVO.setRa_rev(rs.getString("ra_rev"));
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
		return raVO;
	}
	
	@Override
	public List<RaVO> getAll() {
		List<RaVO> list = new ArrayList<RaVO>();
		RaVO raVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			Clob clob=null;

			while (rs.next()) {

				raVO = new RaVO();
				raVO.setRa_no(rs.getString("ra_no"));
				raVO.setBl_no(rs.getString("bl_no"));
				
				clob = rs.getClob("ra_res");
				raVO.setRa_res(new tools().clobToString(clob));
				
				raVO.setRa_rev(rs.getString("ra_rev"));
				list.add(raVO); // Store the row in the list
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
	public List<RaVO> findByRev(String ra_rev){
		List<RaVO> list = new ArrayList<RaVO>();
		RaVO raVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ByRev);
			
			pstmt.setString(1, ra_rev);

			rs = pstmt.executeQuery();
			Clob clob=null;

			while (rs.next()) {

				raVO = new RaVO();
				raVO.setRa_no(rs.getString("ra_no"));
				raVO.setBl_no(rs.getString("bl_no"));
				
				clob = rs.getClob("ra_res");
				raVO.setRa_res(new tools().clobToString(clob));
				
				raVO.setRa_rev(rs.getString("ra_rev"));
				list.add(raVO); // Store the row in the list
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

