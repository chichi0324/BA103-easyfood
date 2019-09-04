package com.rep.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ord.model.OrdJDBCDAO;
import com.ord.model.OrdVO;
import com.tools.tools;

public class RepJDBCDAO implements RepDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "easyfood";
	String passwd = "easyfood";

	private static final String INSERT_STMT = 
		"INSERT INTO report (rep_no,rep_res,ord_no) VALUES ('REP_'||LPAD(TO_CHAR(REP_SQ.NEXTVAL),4,'0'), ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT rep_no,rep_res,rep_rev,ord_no FROM report order by rep_no";
	private static final String GET_ONE_STMT = 
		"SELECT rep_no,rep_res,rep_rev,ord_no FROM report where rep_no = ?";
	private static final String GET_REV_STMT = 
		"SELECT r.rep_no,r.rep_res,r.rep_rev,r.ord_no,o.STR_NO ,o.MEM_NO FROM report r JOIN ord o ON r.ORD_NO=o.ORD_NO where rep_rev = ? ";
	private static final String DELETE = 
		"DELETE FROM report where rep_no = ?";
	private static final String UPDATE = 
		"UPDATE report set rep_res = ?, rep_rev = ? where rep_no = ?";

	
	@Override
	public void insert(RepVO repVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
	
			pstmt.setClob(1, tools.stringToClob(repVO.getRep_res(), con));
			pstmt.setString(2, repVO.getOrd_no());
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

	@Override
	public void update(RepVO repVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
	
			pstmt.setClob(1, tools.stringToClob(repVO.getRep_res(), con));
			pstmt.setString(2, repVO.getRep_rev());
			pstmt.setString(3, repVO.getRep_no());
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

	@Override
	public void delete(String rep_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rep_no);

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
	public RepVO findByPrimaryKey(String rep_no) {
		RepVO repVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rep_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				repVO = new RepVO();
				repVO.setRep_no(rs.getString("rep_no"));
				repVO.setRep_res(rs.getString("rep_res"));
				repVO.setRep_rev(rs.getString("rep_rev"));
				repVO.setOrd_no(rs.getString("ord_no"));
								
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
		return repVO;
	}

	@Override
	public List<RepVO> getAll() {
		List<RepVO> list = new ArrayList<RepVO>();
		RepVO repVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				repVO = new RepVO();
				repVO.setRep_no(rs.getString("rep_no"));
				repVO.setRep_res(rs.getString("rep_res"));
				repVO.setRep_rev(rs.getString("rep_rev"));
				repVO.setOrd_no(rs.getString("ord_no"));
				list.add(repVO); // Store the row in the list
								
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
	public List<RepVO> findByREV(String rep_rev) {
		List<RepVO> list = new ArrayList<RepVO>();
		RepVO repVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_REV_STMT);

			pstmt.setString(1, rep_rev);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				repVO = new RepVO();
				repVO.setRep_no(rs.getString("rep_no"));
				repVO.setRep_res(rs.getString("rep_res"));
				repVO.setRep_rev(rs.getString("rep_rev"));
				repVO.setOrd_no(rs.getString("ord_no"));
				list.add(repVO);				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	public static void main(String[] args) {
		RepJDBCDAO dao = new RepJDBCDAO();
		
		// 新增
//		RepVO repVO1 = new RepVO();
//		repVO1.setRep_res("餐點與圖片差太多");  
//		repVO1.setOrd_no("ORD_0009");
//		dao.insert(repVO1);
//		
		// 修改
		RepVO repVO2 = new RepVO();
		repVO2.setRep_res("啊捏喔");
		repVO2.setRep_rev("通過");
		repVO2.setRep_no("REP_0003");
		dao.update(repVO2);
				
//		// 刪除
//		dao.delete("REP_0002");

		// 查詢
//		RepVO repvo3 = dao.findByPrimaryKey("REP_0002");	
//		System.out.print(repvo3.getRep_no() + ",");
//		System.out.print(repvo3.getRep_res() + ",");
//		System.out.print(repvo3.getRep_rev() + ",");
//		System.out.print(repvo3.getOrd_no() + ",");	
//		System.out.println("---------------------");
//		
		 //查全部
//		List<RepVO> list = dao.getAll();
//		for (RepVO aRep : list) {
//
//			System.out.print(aRep.getRep_no() + ",");
//			System.out.print(aRep.getRep_res() + ",");
//			System.out.print(aRep.getRep_rev() + ",");
//			System.out.print(aRep.getOrd_no() + ",");
//			System.out.println();
//		}
	
	}

}
