package com.admp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.adm.model.AdmVO;





public class AdmpJDBCDAO implements AdmpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "easyfood";
	String passwd = "easyfood";

	private static final String INSERT_STMT = "INSERT INTO ADMPRIVILEGES(FEA_NO, ADM_NO) VALUES(?, ?)";
	private static final String UPDATE_STMT = "UPDATE ADMPRIVILEGES set  FEA_NO=?, where ADM_NO = ?";
	private static final String GET_ALL_STMT = "SELECT FEA_NO , ADM_NO FROM ADMPRIVILEGES";
	private static final String FIND_BY_ADM_NO_STMT = "SELECT * FROM ADMPRIVILEGES where ADM_NO = ?";
	private static final String FIND_BY_FEA_NO_STMT = "SELECT * FROM ADMPRIVILEGES where FEA_NO = ?";

	private static final String DELETE = "DELETE FROM ADMPRIVILEGES where ADM_NO = ?";
	private static final String DELETE_ByFea = "DELETE FROM ADMPRIVILEGES where FEA_NO = ?";
	
	

	// 新增一個功能給一個人----------------------------------------------------------------------------------------
	@Override
	public void insert(AdmpVO admpVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, admpVO.getFea_no());
			pstmt.setString(2, admpVO.getAdm_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(AdmpVO admpVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, admpVO.getFea_no());
			
			pstmt.executeUpdate();

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	


	
//	// 查詢全部----------------------------------------------------------------------------------------
	@Override
	public List<AdmpVO> getAll() {
		List<AdmpVO> list = new ArrayList<AdmpVO>();
		AdmpVO admpVO = null;

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
				admpVO = new AdmpVO();
				admpVO.setFea_no(rs.getString("Fea_no"));
				admpVO.setAdm_no(rs.getString("Adm_no"));
				list.add(admpVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	
	@Override
	public List<AdmpVO> getTwoByADM(String adm_no) {
		
		List<AdmpVO> list = new ArrayList<AdmpVO>();
		AdmpVO admpVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_ADM_NO_STMT);

			pstmt.setString(1, adm_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				// empVo 也稱為 Domain objects
				admpVO = new AdmpVO();
				
				admpVO.setFea_no(rs.getString("FEA_NO"));
				admpVO.setAdm_no(rs.getString("ADM_NO"));
				list.add(admpVO); 
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
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
		public List<AdmpVO> getTwoByFEA(String fea_no) {
			
			List<AdmpVO> list = new ArrayList<AdmpVO>();
			AdmpVO admpVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(FIND_BY_FEA_NO_STMT);

				pstmt.setString(1, fea_no);

				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
					// empVo 也稱為 Domain objects
					admpVO = new AdmpVO();
					
					admpVO.setFea_no(rs.getString("FEA_NO"));
					admpVO.setAdm_no(rs.getString("ADM_NO"));
					list.add(admpVO); 
				}

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				// Clean up JDBC resources
			} catch (ClassNotFoundException e) {
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
		
		// 刪除----------------------------------------------------------------------------------------
		@Override
		public void delete(String adm_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				// 1●設定於 pstm.executeUpdate()之前
				//con.setAutoCommit(false);
				// 先刪除員工
				pstmt = con.prepareStatement(DELETE);
				pstmt.setString(1, adm_no);
				pstmt.executeUpdate();
				

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				// Clean up JDBC resources
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		public void deleteByFea(String fea_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);

				pstmt = con.prepareStatement(DELETE_ByFea);
				pstmt.setString(1, fea_no);
				pstmt.executeUpdate();
				

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				// Clean up JDBC resources
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	// Main方法----------------------------------------------------------------------------------------
	public static void main(String[] args) {

		AdmpJDBCDAO dao = new AdmpJDBCDAO();

		// // 新增
//		 AdmpVO admpVO1 = new AdmpVO();
//		 admpVO1.setFea_no("FEA_0001");
//		 admpVO1.setAdm_no("ADM_0022");
//		 dao.insert(admpVO1);

//		// 刪除
		dao.delete("ADM_0022");

		// 查詢有這個人(管理者編號)有的(功能編號)
//		List<AdmpVO> list = dao.getAllTwo("ADM_0002");
//		for (AdmpVO admpVO : list) {
//			System.out.print(admpVO.getFea_no() + ",");
//			System.out.print(admpVO.getAdm_no() + ",");
//			System.out.println();
//		}
//		System.out.println("---------------------");

//		// 查詢全部
//		List<AdmpVO> list = dao.getAll();
//		for (AdmpVO admpVO : list) {
//			System.out.print(admpVO.getFea_no() + ",");
//			System.out.print(admpVO.getAdm_no() + ",");
//			System.out.println();
//		}
	}

}
