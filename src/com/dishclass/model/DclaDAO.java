package com.dishclass.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dish.model.DishVO;

public class DclaDAO implements DclaDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "INSERT INTO DISHCLASS(DCLA_NO, DCLA_NAME) VALUES('DCLA_'||LPAD(DCLA_SQ.NEXTVAL, 4, '0'), ?)";
	private static final String UPDATE = "UPDATE DISHCLASS SET DCLA_NAME = ? WHERE DCLA_NO = ?";
	private static final String DELETE = "DELETE FROM DISHCLASS WHERE DCLA_NO = ?";
	private static final String GET_ONE = "SELECT DCLA_NO, DCLA_NAME FROM DISHCLASS WHERE DCLA_NO = ?";
	private static final String GET_ALL = "SELECT * FROM DISHCLASS ORDER BY DCLA_NO DESC";
	private static final String FIND_BY_DISHClass_Str ="SELECT DISTINCT d.DCLA_NO ,c.DCLA_NAME FROM DISH d join  DISHCLASS c on d.DCLA_NO=c.DCLA_NO  WHERE d.STR_NO= ? ";
	
	@Override
	public void insert(DclaVO dclaVO) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			
			con = ds.getConnection();
			state = con.prepareStatement(INSERT);
			
			state.setString(1, dclaVO.getDcla_name());
			state.executeUpdate();
			
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
			con = ds.getConnection();
			state = con.prepareStatement(UPDATE);
			
			state.setString(1, dclaVO.getDcla_name());
			state.setString(2, dclaVO.getDcla_no());
			state.executeUpdate();
			
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
			con = ds.getConnection();
			state = con.prepareStatement(DELETE);
			
			state.setString(1, dcla_no);
			state.executeUpdate();
			
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
			con = ds.getConnection();
			state = con.prepareStatement(GET_ONE);
			state.setString(1, dcla_no);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dclaVO = new DclaVO();
				dclaVO.setDcla_no(rs.getString("DCLA_NO"));
				dclaVO.setDcla_name(rs.getString("DCLA_NAME"));
			}
			
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
			con = ds.getConnection();
			state = con.prepareStatement(GET_ALL);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dclaVO = new DclaVO();
				dclaVO.setDcla_no(rs.getString(1));
				dclaVO.setDcla_name(rs.getString(2));
				dclaList.add(dclaVO);
			}
			
			
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
			con = ds.getConnection();
			state = con.prepareStatement(GET_ALL);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dclaVO = new DclaVO();
				dclaVO.setDcla_no(rs.getString("DCLA_NO"));
				dclaVO.setDcla_name(rs.getString("DCLA_NAME"));
				dclaList.add(dclaVO);
			}
			
			
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
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DclaVO dclaVO = null;
		List<DclaVO> dclaList = new ArrayList<>();
		
		try {
			con = ds.getConnection();
			state = con.prepareStatement(FIND_BY_DISHClass_Str);
			state.setString(1, str_no);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dclaVO = new DclaVO();
				dclaVO.setDcla_no(rs.getString(1));	
				dclaVO.setDcla_name(rs.getString(2));
				dclaList.add(dclaVO);
			}
		

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
}
