package com.dishclass.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DclaJDBCDAO implements DclaDAO_interface {
	
	private static final String URL = "jdbc:oracle:thin:@10.211.55.3:1521:XE";
	private static final String USER = "easyfood";
	private static final String PASSWORD = "easyfood";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	private static final String INSERT = "INSERT INTO DISHCLASS(DCLA_NO, DCLA_NAME) VALUES('DCLA_'||LPAD(DCLA_SQ.NEXTVAL, 4, '0'), ?)";
	private static final String UPDATE = "UPDATE DISHCLASS SET DCLA_NAME = ? WHERE DCLA_NO = ?";
	private static final String DELETE = "DELETE FROM DISHCLASS WHERE DCLA_NO = ?";
	private static final String GET_ONE = "SELECT DCLA_NO, DCLA_NAME FROM DISHCLASS WHERE DCLA_NO = ?";
	private static final String GET_ALL = "SELECT DCLA_NO, DCLA_NAME FROM DISHCLASS ORDER BY DCLA_NO";
	
	@Override
	public void insert(DclaVO dclaVO) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(INSERT);
			
			state.setString(1, dclaVO.getDcla_name());
			state.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " +ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	@Override
	public void update(DclaVO dclaVO) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(UPDATE);
			
			state.setString(1, dclaVO.getDcla_name());
			state.setString(2, dclaVO.getDcla_no());
			state.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " +ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	@Override
	public void delete(String dcla_no) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(DELETE);
			
			state.setString(1, dcla_no);
			state.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " +ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	@Override
	public DclaVO findByPrimaryKey(String dcla_no) {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DclaVO dclaVO = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(GET_ONE);
			state.setString(1, dcla_no);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dclaVO = new DclaVO();
				dclaVO.setDcla_no(rs.getString("DCLA_NO"));
				dclaVO.setDcla_name(rs.getString("DCLA_NAME"));
			}
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Driver loaded error occrued . " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return dclaVO;
	}
	@Override
	public List<DclaVO> getAll() {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DclaVO dclaVO = null;
		List<DclaVO> dclaList = new ArrayList<>();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(GET_ALL);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dclaVO = new DclaVO();
				dclaVO.setDcla_no(rs.getString("DCLA_NO"));
				dclaVO.setDcla_name(rs.getString("DCLA_NAME"));
				dclaList.add(dclaVO);
			}
			
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Driver loaded error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dclaList;
	}
	
	
	@Override
	public List<DclaVO> getALL() {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DclaVO dclaVO = null;
		List<DclaVO> dclaList = new ArrayList<>();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(GET_ALL);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dclaVO = new DclaVO();
				dclaVO.setDcla_no(rs.getString("DCLA_NO"));
				dclaVO.setDcla_name(rs.getString("DCLA_NAME"));
				dclaList.add(dclaVO);
			}
			
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Driver loaded error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dclaList;
	}
	
	@Override
	public List<DclaVO> getStr_DishClass(String str_no){
		return null;
	}
	
	
	

}
