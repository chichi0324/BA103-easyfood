package com.pro.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProJDBCDAO implements ProDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "easyfood";
	String passwd = "easyfood";
	
	private static final String INSERT_STMT1 = 
		"INSERT INTO PROMOTION (PRO_NO,PRO_STR,PRO_END,STR_NO,PRO_CAT,PRO_MON,PRO_DIS) VALUES('PRO_'||LPAD(TO_CHAR(PRO_SQ.NEXTVAL),4,'0'), ?, ?, ?, ?, ?, ?)";
	
	private static final String INSERT_STMT2 = 
			"INSERT INTO PROMOTION (PRO_NO,PRO_STR,PRO_END,STR_NO,PRO_CAT,PRO_DIS,DCLA_NO1,DCLA_NO2) VALUES('PRO_'||LPAD(TO_CHAR(PRO_SQ.NEXTVAL),4,'0'), ?, ?, ?, ?, ?, ?,?)";
	
	private static final String GET_ALL_STMT = 
		"SELECT PRO_NO,PRO_STR,PRO_END,STR_NO,PRO_CAT,PRO_MON,PRO_DIS,DCLA_NO1,DCLA_NO2 FROM PROMOTION  order by PRO_NO";
	private static final String GET_ONE_STMT = 
		"SELECT PRO_NO,PRO_STR,PRO_END,STR_NO,PRO_CAT,PRO_MON,PRO_DIS,DCLA_NO1,DCLA_NO2 FROM PROMOTION where PRO_NO = ?";
	private static final String UPDATE = 
			"UPDATE PROMOTION set PRO_END= ?  where PRO_NO = ?";
	private static final String GET_ONE_STR = 
			"SELECT PRO_NO,PRO_STR,PRO_END,STR_NO,PRO_CAT,PRO_MON,PRO_DIS,DCLA_NO1,DCLA_NO2 FROM PROMOTION where STR_NO = ?";
	
	@Override
	public void insert1(ProVO proVO) {
		Connection con= null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT1);
			
			pstmt.setDate(1, proVO.getPro_str());
			pstmt.setDate(2, proVO.getPro_end());
			pstmt.setString(3, proVO.getStr_no());
			pstmt.setString(4, proVO.getPro_cat());
			pstmt.setDouble(5, proVO.getPro_mon());
			pstmt.setDouble(6, proVO.getPro_dis());

			
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
	public void insert2(ProVO proVO) {
		Connection con= null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT2);
			
			pstmt.setDate(1, proVO.getPro_str());
			pstmt.setDate(2, proVO.getPro_end());
			pstmt.setString(3, proVO.getStr_no());
			pstmt.setString(4, proVO.getPro_cat());
			pstmt.setDouble(5, proVO.getPro_dis());
			pstmt.setString(6, proVO.getDcla_no1());
			pstmt.setString(7, proVO.getDcla_no2());

			
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
	public ProVO findByPrimaryKey(String pro_no) {
		
		ProVO proVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,pro_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				proVO = new ProVO();
				proVO.setPro_no(rs.getString("pro_no"));
				proVO.setPro_str(rs.getDate("pro_str"));
				proVO.setPro_end(rs.getDate("pro_end"));
				proVO.setStr_no(rs.getString("str_no"));
				proVO.setPro_cat(rs.getString("pro_cat"));
				proVO.setPro_mon(rs.getInt("pro_mon"));
				proVO.setPro_dis(rs.getDouble("pro_dis"));
				proVO.setDcla_no1(rs.getString("dcla_no1"));
				proVO.setDcla_no2(rs.getString("dcla_no2"));
				
								
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (SQLException e) {
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
		
		
		
		
		return proVO;
	}
	
	@Override
	public void update(ProVO proVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setDate(1, proVO.getPro_end());
			pstmt.setString(2, proVO.getPro_no());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
			
		
	}

	@Override
	public List<ProVO> getAll() {
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO proVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				proVO = new ProVO();
				proVO.setPro_no(rs.getString("pro_no"));
				proVO.setPro_str(rs.getDate("pro_str"));
				proVO.setPro_end(rs.getDate("pro_end"));
				proVO.setStr_no(rs.getString("str_no"));
				proVO.setPro_cat(rs.getString("pro_cat"));
				proVO.setPro_mon(rs.getInt("pro_mon"));
				proVO.setPro_dis(rs.getDouble("pro_dis"));
				proVO.setDcla_no1(rs.getString("dcla_no1"));
				proVO.setDcla_no2(rs.getString("dcla_no2"));
				list.add(proVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
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
		
		ProJDBCDAO dao = new ProJDBCDAO();
	    //新增 方案1	
//		ProVO ProVO1 = new ProVO();
//		ProVO1.setPro_str(java.sql.Date.valueOf("2017-09-01"));
//		ProVO1.setPro_end(java.sql.Date.valueOf("2018-09-01"));
//		ProVO1.setStr_no("STR_0002");
//		ProVO1.setPro_cat("0001");
//		ProVO1.setPro_mon(800);
//		ProVO1.setPro_dis(0.62);
//	
//		dao.insert1(ProVO1);
		//新增 方案2
//		ProVO ProVO1 = new ProVO();
//		ProVO1.setPro_str(java.sql.Date.valueOf("2017-09-01"));
//		ProVO1.setPro_end(java.sql.Date.valueOf("2018-09-01"));
//		ProVO1.setStr_no("STR_0002");
//		ProVO1.setPro_cat("0002");
//		ProVO1.setPro_dis(0.62);
//		ProVO1.setDcla_no1("DCLA_0001");
//		ProVO1.setDcla_no2("DCLA_0002");
////		
//		dao.insert2(ProVO1);
		
		
		
		
		
//		// 修改
		ProVO ProVO2 = new ProVO();
		ProVO2.setPro_no("PRO_0005");
		ProVO2.setPro_end(java.sql.Date.valueOf("2018-10-01"));
		
		
		dao.update(ProVO2);
//
//		
//
		// 查詢
//		ProVO proVO3 = dao.findByPrimaryKey("PRO_0001");
//		System.out.print(proVO3.getPro_no() + ",");
//		System.out.print(proVO3.getPro_str() + ",");
//		System.out.print(proVO3.getPro_end() + ",");
//		System.out.print(proVO3.getStr_no() + ",");
//		System.out.print(proVO3.getPro_cat() + ",");
//		System.out.print(proVO3.getPro_mon() + ",");
//		System.out.print(proVO3.getPro_dis() + ",");
//		System.out.print(proVO3.getDcla_no1() + ",");
//		System.out.print(proVO3.getDcla_no2() + ",");
//		System.out.println("---------------------");

		// 查詢
		List<ProVO> list = dao.getStrPro("STR_0003");
		for (ProVO aPro : list) {
		System.out.print(aPro.getPro_no() + ",");
		System.out.print(aPro.getPro_str() + ",");
		System.out.print(aPro.getPro_end() + ",");

		System.out.print(aPro.getPro_cat() + ",");
		System.out.print(aPro.getPro_mon() + ",");
		System.out.print(aPro.getPro_dis() + ",");
		System.out.print(aPro.getDcla_no1() + ",");
		System.out.print(aPro.getDcla_no2() + ",");
		System.out.println("---------------------");			
			System.out.println();
		}
	}

	@Override
	public List<ProVO> getStrPro(String str_no) {
		
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO proVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STR);
			pstmt.setString(1, str_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				proVO = new ProVO();
				proVO.setPro_no(rs.getString("pro_no"));
				proVO.setPro_str(rs.getDate("pro_str"));
				proVO.setPro_end(rs.getDate("pro_end"));
				proVO.setStr_no(rs.getString("str_no"));
				proVO.setPro_cat(rs.getString("pro_cat"));
				proVO.setPro_mon(rs.getInt("pro_mon"));
				proVO.setPro_dis(rs.getDouble("pro_dis"));
				proVO.setDcla_no1(rs.getString("dcla_no1"));
				proVO.setDcla_no2(rs.getString("dcla_no2"));
				list.add(proVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
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
