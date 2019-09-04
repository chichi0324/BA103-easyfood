package com.adm.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class AdmJDBCDAO implements AdmDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "easyfood";
	String passwd = "easyfood";
	
	private static final String INSERT_STMT = "INSERT INTO ADMINISTRATOR (ADM_NO,ADM_ACC,ADM_PAS,ADM_NAME,ADM_ADRS,ADM_PHO,ADM_POS,ADM_MAIL) VALUES ('ADM_'||LPAD(TO_CHAR(FEA_SQ.NEXTVAL),4,'0'), ?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT ADM_NO, ADM_ACC, ADM_PAS, ADM_NAME, ADM_ADRS, ADM_PHO, ADM_POS, ADM_MAIL FROM ADMINISTRATOR";
	private static final String GET_ONE_STMT = "SELECT ADM_NO, ADM_ACC, ADM_PAS, ADM_NAME, ADM_ADRS, ADM_PHO, ADM_POS, ADM_MAIL FROM ADMINISTRATOR where ADM_NO = ?";
	private static final String GET_ONE_BYACC = "SELECT ADM_NO, ADM_ACC, ADM_PAS, ADM_NAME, ADM_ADRS, ADM_PHO, ADM_POS, ADM_MAIL FROM ADMINISTRATOR where ADM_ACC = ?";
	private static final String GET_ONE_BYNAME = "SELECT ADM_NO, ADM_ACC, ADM_PAS, ADM_NAME, ADM_ADRS, ADM_PHO, ADM_POS, ADM_MAIL FROM ADMINISTRATOR where ADM_NAME = ?";
//	private static final String GET_ADM_STMT = "SELECT ADM_NO , FEA_NO FROM ADMPRIVILEGES where ADM_NO = ?";

	private static final String DELETE = "DELETE FROM ADMINISTRATOR where ADM_NO = ?";
	private static final String DELETE_ADMP = "DELETE FROM ADMPRIVILEGES where ADM_NO = ?";
	private static final String UPDATE = "UPDATE ADMINISTRATOR set ADM_ACC=?, ADM_PAS=?, ADM_NAME=?, ADM_ADRS=?, ADM_PHO=?, ADM_POS=? ,ADM_MAIL=? where ADM_NO = ?";
	private static final String GETByAccName = "SELECT ADM_NO FROM ADMINISTRATOR where ADM_ACC = ? and ADM_PAS = ?";
	
	// 新增----------------------------------------------------------------------------------------
	@Override
	public void insert(AdmVO admVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, admVO.getAdm_acc());
			pstmt.setString(2, admVO.getAdm_pas());
			pstmt.setString(3, admVO.getAdm_name());
			pstmt.setString(4, admVO.getAdm_adrs());
			pstmt.setString(5, admVO.getAdm_pho());
			pstmt.setString(6, admVO.getAdm_pos());
			pstmt.setString(7, admVO.getAdm_mail());
			
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	
	// 修改----------------------------------------------------------------------------------------
	@Override
	public void update(AdmVO admVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, admVO.getAdm_acc());
			pstmt.setString(2, admVO.getAdm_pas());
			pstmt.setString(3, admVO.getAdm_name());
			pstmt.setString(4, admVO.getAdm_adrs());
			pstmt.setString(5, admVO.getAdm_pho());
			pstmt.setString(6, admVO.getAdm_pos());
			pstmt.setString(7, admVO.getAdm_mail());			
			pstmt.setString(8, admVO.getAdm_no());

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
	
	// 刪除----------------------------------------------------------------------------------------
	@Override
	public void delete(String adm_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 先刪除員工
			pstmt = con.prepareStatement(DELETE_ADMP);
			pstmt.setString(1, adm_no);
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(DELETE );
			pstmt.setString(1, adm_no);

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
		
	
	
	// 查詢單筆----------------------------------------------------------------------------------------
	@Override
	public AdmVO findByPrimaryKey(String adm_no) {
		AdmVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, adm_no);
			
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				admVO = new AdmVO();
				admVO.setAdm_no(rs.getString("ADM_NO"));
				admVO.setAdm_acc(rs.getString("ADM_ACC"));
				admVO.setAdm_pas(rs.getString("ADM_PAS"));
				admVO.setAdm_name(rs.getString("ADM_NAME"));
				admVO.setAdm_adrs(rs.getString("ADM_ADRS"));
				admVO.setAdm_pho(rs.getString("ADM_PHO"));
				admVO.setAdm_pos(rs.getString("ADM_POS"));
				admVO.setAdm_pos(rs.getString("ADM_MAIL"));
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
		return admVO;
	}
	
	
	// 查詢全部----------------------------------------------------------------------------------------
	@Override
	public List<AdmVO> getAll() {
		List<AdmVO> list = new ArrayList<AdmVO>();
		AdmVO admVO = null;

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
				admVO = new AdmVO();
				admVO.setAdm_no(rs.getString("ADM_NO"));
				admVO.setAdm_acc(rs.getString("ADM_ACC"));
				admVO.setAdm_pas(rs.getString("ADM_PAS"));
				admVO.setAdm_name(rs.getString("ADM_NAME"));
				admVO.setAdm_adrs(rs.getString("ADM_ADRS"));
				admVO.setAdm_pho(rs.getString("ADM_PHO"));
				admVO.setAdm_pos(rs.getString("ADM_POS"));
				admVO.setAdm_pos(rs.getString("ADM_MAIL"));
				list.add(admVO); // Store the row in the list
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
	
	// 用帳號密碼查詢編號----------------------------------------------------------------------------------------
	@Override
	public AdmVO findByAcc(String adm_acc,String adm_pas) {
		AdmVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETByAccName);

			pstmt.setString(1, adm_acc);
			pstmt.setString(2, adm_pas);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				admVO = new AdmVO();
				admVO.setAdm_no(rs.getString("ADM_NO"));
				
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
		return admVO;
	}
	
	
	
	// 查詢單筆BY ADM_ACC----------------------------------------------------------------------------------------
		@Override
		public AdmVO findByAcc(String adm_acc) {
			AdmVO admVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_BYACC);

				pstmt.setString(1, adm_acc);
				
				

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					admVO = new AdmVO();
					admVO.setAdm_no(rs.getString("ADM_NO"));
					admVO.setAdm_acc(rs.getString("ADM_ACC"));
					admVO.setAdm_pas(rs.getString("ADM_PAS"));
					admVO.setAdm_name(rs.getString("ADM_NAME"));
					admVO.setAdm_adrs(rs.getString("ADM_ADRS"));
					admVO.setAdm_pho(rs.getString("ADM_PHO"));
					admVO.setAdm_pos(rs.getString("ADM_POS"));
					admVO.setAdm_pos(rs.getString("ADM_MAIL"));
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
			return admVO;
		}
		
		
		
		
		// 查詢單筆BY ADM_NAME----------------------------------------------------------------------------------------
				@Override
				public AdmVO findByName(String adm_name) {
					AdmVO admVO = null;
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;

					try {

						
						Class.forName(driver);
						con = DriverManager.getConnection(url, userid, passwd);
						pstmt = con.prepareStatement(GET_ONE_BYNAME);

						pstmt.setString(1, adm_name);
						
						

						rs = pstmt.executeQuery();

						while (rs.next()) {
							// empVo 也稱為 Domain objects
							admVO = new AdmVO();
							admVO.setAdm_no(rs.getString("ADM_NO"));
							admVO.setAdm_acc(rs.getString("ADM_ACC"));
							admVO.setAdm_pas(rs.getString("ADM_PAS"));
							admVO.setAdm_name(rs.getString("ADM_NAME"));
							admVO.setAdm_adrs(rs.getString("ADM_ADRS"));
							admVO.setAdm_pho(rs.getString("ADM_PHO"));
							admVO.setAdm_pos(rs.getString("ADM_POS"));
							admVO.setAdm_pos(rs.getString("ADM_MAIL"));
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
					return admVO;
				}
	
	}

