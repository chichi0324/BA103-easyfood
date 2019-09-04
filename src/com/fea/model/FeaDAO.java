package com.fea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class FeaDAO implements FeaDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO FEATURES (FEA_NO,FEA_NAME) VALUES ('FEA_'||LPAD(TO_CHAR(FEA_SQ.NEXTVAL),4,'0'), ?)";
	private static final String GET_ALL_STMT = "SELECT FEA_NO , FEA_NAME FROM FEATURES";
	private static final String GET_ONE_STMT = "SELECT FEA_NO , FEA_NAME FROM FEATURES where FEA_NO = ?";
//	private static final String GET_ADMP_STMT = "SELECT FEA_NO , ADM_NO FROM ADMPRIVILEGES where FEA_NO = ?";

	private static final String DELETE = "DELETE FROM FEATURES where FEA_NO = ?";
	private static final String DELETE_ADMP = "DELETE FROM ADMPRIVILEGES where FEA_NO = ?";
	private static final String UPDATE = "UPDATE FEATURES set FEA_NAME=? where FEA_NO = ?";

	// 新增----------------------------------------------------------------------------------------
	@Override
	public void insert(FeaVO feaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, feaVO.getFea_name());

			pstmt.executeUpdate();

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// 修改----------------------------------------------------------------------------------------
	@Override
	public void update(FeaVO feaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, feaVO.getFea_name());

			pstmt.setString(2, feaVO.getFea_no());

			pstmt.executeUpdate();

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// 刪除----------------------------------------------------------------------------------------
	@Override
	public void delete(String fea_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();

			// 先刪除員工
			pstmt = con.prepareStatement(DELETE_ADMP);
			pstmt.setString(1, fea_no);
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, fea_no);

			pstmt.executeUpdate();

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	
	// 查詢單筆----------------------------------------------------------------------------------------
	@Override
	public FeaVO findByPrimaryKey(String fea_no) {

		FeaVO feaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, fea_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				feaVO = new FeaVO();
				feaVO.setFea_no(rs.getString("fea_no"));
				feaVO.setFea_name(rs.getString("fea_name"));
			}

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return feaVO;
	}

	// 查詢全部----------------------------------------------------------------------------------------
	@Override
	public List<FeaVO> getAll() {
		List<FeaVO> list = new ArrayList<FeaVO>();
		FeaVO feaVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				feaVO = new FeaVO();
				feaVO.setFea_no(rs.getString("Fea_no"));
				feaVO.setFea_name(rs.getString("Fea_name"));
				list.add(feaVO); // Store the row in the list
			}

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
