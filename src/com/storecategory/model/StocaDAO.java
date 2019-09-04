package com.storecategory.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StocaDAO implements StocaDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT = "INSERT INTO STORECATEGORY(STOCA_NO, STOCA_NAME, STOCA_IMG, STOCA_NOTE)"
			+ "VALUES('STOCA_'||LPAD(STOCA_SQ.NEXTVAL, 4, '0'), ?, ?, ?)";
	private static final String UPDATE = "UPDATE STORECATEGORY SET STOCA_NAME = ?, STOCA_IMG = ?, STOCA_NOTE = ? WHERE STOCA_NO = ?";
	private static final String UPDATE_IMG = "UPDATE STORECATEGORY SET STOCA_IMG = ? WHERE STOCA_NO = ?";
	private static final String UPDATE_NOIMG = "UPDATE STORECATEGORY SET STOCA_NAME = ?, STOCA_NOTE = ? WHERE STOCA_NO = ?";
	private static final String DELETE = "DELETE FROM STORECATEGORY WHERE STOCA_NO = ?";
	private static final String GET_ONE = "SELECT * FROM STORECATEGORY WHERE STOCA_NO = ?";
	private static final String GET_ALL = "SELECT * FROM STORECATEGORY ORDER BY STOCA_NO DESC";

	@Override
	public void insert(StocaVO stocaVO) {
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			
			con = ds.getConnection();
			state = con.prepareStatement(INSERT);
			
			state.setString(1, stocaVO.getStoca_name());
			state.setBytes(2, stocaVO.getStoca_img());
			state.setString(3, stocaVO.getStoca_note());
			state.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

	@Override
	public void update(StocaVO stocaVO) {
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			
			con = ds.getConnection();
			state = con.prepareStatement(UPDATE);
			
			state.setString(1, stocaVO.getStoca_name());
			state.setBytes(2, stocaVO.getStoca_img());
			state.setString(3, stocaVO.getStoca_note());
			state.setString(4, stocaVO.getStoca_no());	
			state.executeUpdate();
			
		} catch (SQLException ce) {
			throw new RuntimeException("A database error occured. " + ce.getMessage());
			
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
	}

	@Override
	public void delete(String stoca_no) {
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			
			con = ds.getConnection();
			state = con.prepareStatement(DELETE);
			
			state.setString(1, stoca_no);
			state.executeUpdate();
			
		} catch (SQLException ce) {
			throw new RuntimeException("A database error occured. " + ce.getMessage());
			
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
	}


	@Override
	public StocaVO findByPrimaryKey(String stoca_no) {
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		StocaVO stocaVO = null;
		
		try {
			con = ds.getConnection();
			state = con.prepareStatement(GET_ONE);
			
			state.setString(1, stoca_no);
			rs = state.executeQuery();
			
			while(rs.next()) {
				stocaVO = new StocaVO();
				stocaVO.setStoca_no(rs.getString("STOCA_NO"));
				stocaVO.setStoca_name(rs.getString("STOCA_NAME"));
				stocaVO.setStoca_img(rs.getBytes("STOCA_IMG"));
				stocaVO.setStoca_note(rs.getString("STOCA_NOTE"));

			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			} 
		}
		return stocaVO;
	}

	@Override
	public List<StocaVO> getAll() {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		StocaVO stocaVO = null;
		List<StocaVO> stocaList = new ArrayList<>();
		
		try {
			con = ds.getConnection();
			state = con.prepareStatement(GET_ALL);
			rs = state.executeQuery();
			
			while(rs.next()) {
				stocaVO = new StocaVO();
				stocaVO.setStoca_no(rs.getString("STOCA_NO"));
				stocaVO.setStoca_name(rs.getString("STOCA_NAME"));
				stocaVO.setStoca_img(rs.getBytes("STOCA_IMG"));
				stocaVO.setStoca_note(rs.getString("STOCA_NOTE"));
				stocaList.add(stocaVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
		
		return stocaList;
	}

	@Override
	public void update_img(StocaVO stocaVO) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			
			con = ds.getConnection();
			state = con.prepareStatement(UPDATE_IMG);
			state.setBytes(1, stocaVO.getStoca_img());
			state.setString(2, stocaVO.getStoca_no());	
			state.executeUpdate();
			
		} catch (SQLException ce) {
			throw new RuntimeException("A database error occured. " + ce.getMessage());
			
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
		
	}

	@Override
	public void update_noimg(StocaVO stocaVO) {
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			
			con = ds.getConnection();
			state = con.prepareStatement(UPDATE_NOIMG);
			
			state.setString(1, stocaVO.getStoca_name());
			state.setString(2, stocaVO.getStoca_note());
			state.setString(3, stocaVO.getStoca_no());	
			state.executeUpdate();
			
		} catch (SQLException ce) {
			throw new RuntimeException("A database error occured. " + ce.getMessage());
			
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
		
	}
	
	@Override
	public List<StocaVO> getALL() {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		StocaVO stocaVO = null;
		List<StocaVO> stocaList = new ArrayList<>();
		
		try {
			con = ds.getConnection();
			state = con.prepareStatement(GET_ALL);
			rs = state.executeQuery();
			
			while(rs.next()) {
				stocaVO = new StocaVO();
				stocaVO.setStoca_no(rs.getString(1));
				stocaVO.setStoca_name(rs.getString(2));
				stocaVO.setStoca_img(Objects.isNull(rs.getBytes(3)) ? "0".getBytes() : rs.getBytes(3));
				stocaVO.setStoca_note(Objects.isNull(rs.getString(4)) ? "種類介紹" :rs.getString(4));
				stocaList.add(stocaVO);
			}
			
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
		
		return stocaList;
	}

}
