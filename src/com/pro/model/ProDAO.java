package com.pro.model;

import java.sql.Connection;
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

public class ProDAO implements ProDAO_interface{
	
	private static DataSource ds = null;
		static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT1 = 
		"INSERT INTO PROMOTION (PRO_NO,PRO_STR,PRO_END,STR_NO,PRO_CAT,PRO_MON,PRO_DIS) VALUES('PRO_'||LPAD(TO_CHAR(PRO_SQ.NEXTVAL),4,'0'), ?, ?, ?, ?, ?, ?)";	
	private static final String INSERT_STMT2 = 
		"INSERT INTO PROMOTION (PRO_NO,PRO_STR,PRO_END,STR_NO,PRO_CAT,PRO_DIS,DCLA_NO1,DCLA_NO2) VALUES('PRO_'||LPAD(TO_CHAR(PRO_SQ.NEXTVAL),4,'0'), ?, ?, ?, ?, ?, ?,?)";	
	private static final String GET_ALL_STMT = 
		"SELECT PRO_NO,PRO_STR,PRO_END,STR_NO,PRO_CAT,PRO_MON,PRO_DIS,DCLA_NO1,DCLA_NO2 FROM PROMOTION  order by PRO_NO";
	private static final String GET_ONE_STR_NOW = 
		"SELECT PRO_NO,PRO_STR,PRO_END,STR_NO,PRO_CAT,PRO_MON,PRO_DIS,DCLA_NO1,DCLA_NO2 FROM PROMOTION where STR_NO = ? and PRO_STR <= SYSDATE and PRO_END >= SYSDATE";
	private static final String UPDATE = 
		"UPDATE PROMOTION set PRO_END= ?  where PRO_NO = ?";
	
	private static final String GET_ONE_STR_ALL =
	"SELECT PRO_NO,PRO_STR,PRO_END,STR_NO,PRO_CAT,PRO_MON,PRO_DIS,DCLA_NO1,DCLA_NO2 FROM PROMOTION where STR_NO = ?  and PRO_END >= SYSDATE order by PRO_STR";
	
	@Override
	public void insert1(ProVO proVO) {
		Connection con= null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT1);
			
			
			pstmt.setDate(1, proVO.getPro_str());
			pstmt.setDate(2, proVO.getPro_end());
			pstmt.setString(3, proVO.getStr_no());
			pstmt.setString(4, proVO.getPro_cat());
			pstmt.setInt(5, proVO.getPro_mon());
			pstmt.setDouble(6, proVO.getPro_dis());
			
			pstmt.executeUpdate();
			
		}  catch (SQLException e) {
			
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
	public void insert2(ProVO proVO) {
		Connection con= null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT2);
			
			
			pstmt.setDate(1, proVO.getPro_str());
			pstmt.setDate(2, proVO.getPro_end());
			pstmt.setString(3, proVO.getStr_no());
			pstmt.setString(4, proVO.getPro_cat());
			pstmt.setDouble(5, proVO.getPro_dis());
			pstmt.setString(6, proVO.getDcla_no1());
			pstmt.setString(7, proVO.getDcla_no2());
			
			pstmt.executeUpdate();
			
		}  catch (SQLException e) {
			
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
	public void update(ProVO proVO) {
		Connection con= null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setDate(1, proVO.getPro_end());
			pstmt.setString(2, proVO.getPro_no());

			
			pstmt.executeUpdate();
			
		}  catch (SQLException e) {
			
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
	public ProVO findByPrimaryKey(String pro_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProVO> getAll() {
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO proVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				proVO = new ProVO();
				proVO.setPro_no(rs.getString("pro_no"));
				proVO.setPro_str(rs.getDate("pro_str"));
				proVO.setPro_end(rs.getDate("pro_end"));
				proVO.setPro_no(rs.getString("pro_no"));
				proVO.setPro_cat(rs.getString("pro_cat"));
				proVO.setPro_mon(Objects.isNull(rs.getInt("pro_mon")) ? 0 : rs.getInt("pro_mon"));
				proVO.setPro_dis(rs.getDouble("pro_dis"));
				proVO.setDcla_no1(Objects.isNull(rs.getString("dcla_no1")) ? "" : rs.getString("dcla_no1") );
				proVO.setDcla_no2(Objects.isNull(rs.getString("dcla_no2")) ? "" : rs.getString("dcla_no2"));
				
				
				list.add(proVO); // Store the row in the list
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
	public List<ProVO> getStrPro(String str_no) {
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO proVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STR_ALL);
			pstmt.setString(1, str_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				proVO = new ProVO();
				proVO.setPro_no(rs.getString("pro_no"));
				proVO.setPro_str(rs.getDate("pro_str"));
				proVO.setPro_end(rs.getDate("pro_end"));
				proVO.setPro_no(rs.getString("pro_no"));
				proVO.setPro_cat(rs.getString("pro_cat"));
				proVO.setPro_mon(Objects.isNull(rs.getInt("pro_mon")) ? 0 : rs.getInt("pro_mon"));
				proVO.setPro_dis(rs.getDouble("pro_dis"));
				proVO.setDcla_no1(Objects.isNull(rs.getString("dcla_no1")) ? "" : rs.getString("dcla_no1") );
				proVO.setDcla_no2(Objects.isNull(rs.getString("dcla_no2")) ? "" : rs.getString("dcla_no2"));
				
				
				list.add(proVO); // Store the row in the list
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
