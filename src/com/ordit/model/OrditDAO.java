package com.ordit.model;

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

public class OrditDAO implements OrditDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ordit (ord_no,dish_no,ordit_qua,ordit_pri) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT ord_no,dish_no,ordit_qua,ordit_pri FROM ordit order by ord_no,dish_no";
	private static final String GET_ONE_STMT = "SELECT ord_no,dish_no,ordit_qua,ordit_pri FROM ordit where ord_no = ? and dish_no = ?";
	private static final String DELETE = "DELETE FROM ordit where ord_no = ? and dish_no = ?";
	private static final String UPDATE = "UPDATE ordit set ordit_qua = ?, ordit_pri = ? where ord_no = ? and dish_no = ?";
	private static final String GEY_ALL_BY_STORE = "SELECT * FROM ORDIT WHERE ORD_NO = ? ORDER BY DISH_NO";
	private static final String GET_ALL_STMT_MONTH ="select e.ORD_NO,d.DISH_NO,ORDIT_QUA from ORD e join ORDIT d on(e.ORD_NO=d.ORD_NO) where ORD_DATE > add_months(sysdate,-1)";
	private static final String GET_ALL_STMT_WEEK ="select e.ORD_NO,d.DISH_NO,ORDIT_QUA from ORD e join ORDIT d on(e.ORD_NO=d.ORD_NO) where ORD_DATE > (sysdate-7)";
	private static final String GET_ONE_DISHCLASS01_ALL ="select e.DISH_NO,d.ORDIT_QUA from DISH e join ORDIT d on(e.DISH_NO=d.DISH_NO) where DCLA_NO = 'DCLA_0001'";
	private static final String GET_ONE_DISHCLASS02_ALL ="select e.DISH_NO,d.ORDIT_QUA from DISH e join ORDIT d on(e.DISH_NO=d.DISH_NO) where DCLA_NO = 'DCLA_0002'";
	private static final String GET_ONE_DISHCLASS03_ALL ="select e.DISH_NO,d.ORDIT_QUA from DISH e join ORDIT d on(e.DISH_NO=d.DISH_NO) where DCLA_NO = 'DCLA_0003'";
	private static final String GET_ONE_DISHCLASS05_ALL ="select e.DISH_NO,d.ORDIT_QUA from DISH e join ORDIT d on(e.DISH_NO=d.DISH_NO) where DCLA_NO = 'DCLA_0005'";
	
	@Override
	public void insert(OrditVO orditVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orditVO.getOrd_no());
			pstmt.setString(2, orditVO.getDish_no());
			pstmt.setInt(3, orditVO.getOrdit_qua());
			pstmt.setDouble(4, orditVO.getOrdit_pri());
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void update(OrditVO orditVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orditVO.getOrdit_qua());
			pstmt.setDouble(2, orditVO.getOrdit_pri());
			pstmt.setString(3, orditVO.getOrd_no());
			pstmt.setString(4, orditVO.getDish_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void delete(String ord_no, String dish_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ord_no);
			pstmt.setString(2, dish_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public OrditVO findByPrimaryKey(String ord_no, String dish_no) {
		OrditVO orditVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ord_no);
			pstmt.setString(2, dish_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				orditVO = new OrditVO();
				orditVO.setOrd_no(rs.getString("ord_no"));
				orditVO.setDish_no(rs.getString("dish_no"));
				orditVO.setOrdit_qua(rs.getInt("ordit_qua"));
				orditVO.setOrdit_pri(rs.getDouble("ordit_pri"));
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return orditVO;
	}

	@Override
	public List<OrditVO> getAll() {
		List<OrditVO> list = new ArrayList<OrditVO>();
		OrditVO orditVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				orditVO = new OrditVO();
				orditVO.setOrd_no(rs.getString("ord_no"));
				orditVO.setDish_no(rs.getString("dish_no"));
				orditVO.setOrdit_qua(rs.getInt("ordit_qua"));
				orditVO.setOrdit_pri(rs.getDouble("ordit_pri"));
				list.add(orditVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public List<OrditVO> getAllyStr(String ord_no) {
		
		List<OrditVO> list = new ArrayList<OrditVO>();
		OrditVO orditVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GEY_ALL_BY_STORE);
			pstmt.setString(1, ord_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				orditVO = new OrditVO();
				orditVO.setOrd_no(rs.getString("ORD_NO"));
				orditVO.setDish_no(rs.getString("DISH_NO"));
				orditVO.setOrdit_qua(rs.getInt("ORDIT_QUA"));
				orditVO.setOrdit_pri(rs.getDouble("ORDIT_PRI"));
				list.add(orditVO);	
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {		
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
		OrditDAO dao = new OrditDAO();

		// 新增
		// OrditVO orditVO1 = new OrditVO();
		// orditVO1.setOrd_no("ORD_0009");
		// orditVO1.setDish_no("DISH_0007");
		// orditVO1.setOrdit_qua(1);
		// orditVO1.setOrdit_pri(240);
		// dao.insert(orditVO1);
		//
		// 修改
		// OrditVO orditVO2 = new OrditVO();
		// orditVO2.setOrdit_qua(2);
		// orditVO2.setOrdit_pri(480);
		// orditVO2.setOrd_no("ORD_0009");
		// orditVO2.setDish_no("DISH_0007");
		// dao.update(orditVO2);

		// // �R��
		// dao.delete("ORD_0009","DISH_0007");

		// 查詢
		// OrditVO orditVO3 = dao.findByPrimaryKey("ORD_0003", "DISH_0005");
		// System.out.print(orditVO3.getOrd_no() + ",");
		// System.out.print(orditVO3.getDish_no() + ",");
		// System.out.print(orditVO3.getOrdit_qua() + ",");
		// System.out.print(orditVO3.getOrdit_pri() + ",");
		//
		// System.out.println("---------------------");

		// 查全部
		// List<OrditVO> list = dao.getAll();
		// for (OrditVO aOrdit : list) {
		// System.out.print(aOrdit.getOrd_no() + ",");
		// System.out.print(aOrdit.getDish_no() + ",");
		// System.out.print(aOrdit.getOrdit_qua() + ",");
		// System.out.print(aOrdit.getOrdit_pri() + ",");
		// System.out.println();
		// }

	}

	@Override
	public List<OrditVO> getAll_month() {
		List<OrditVO> list = new ArrayList<OrditVO>();
		OrditVO orditVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_MONTH);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				orditVO = new OrditVO();
				orditVO.setOrd_no(rs.getString("ord_no"));
				orditVO.setDish_no(rs.getString("dish_no"));
				orditVO.setOrdit_qua(rs.getInt("ordit_qua"));
				list.add(orditVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public List<OrditVO> getDish_class01_all() {
		List<OrditVO> list = new ArrayList<OrditVO>();
		OrditVO orditVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_DISHCLASS01_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				orditVO = new OrditVO();
				orditVO.setDish_no(rs.getString("dish_no"));
				orditVO.setOrdit_qua(rs.getInt("ordit_qua"));
				list.add(orditVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public List<OrditVO> getDish_class02_all() {
		List<OrditVO> list = new ArrayList<OrditVO>();
		OrditVO orditVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_DISHCLASS02_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				orditVO = new OrditVO();
				orditVO.setDish_no(rs.getString("dish_no"));
				orditVO.setOrdit_qua(rs.getInt("ordit_qua"));
				list.add(orditVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public List<OrditVO> getDish_class03_all() {
		List<OrditVO> list = new ArrayList<OrditVO>();
		OrditVO orditVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_DISHCLASS03_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				orditVO = new OrditVO();
				orditVO.setDish_no(rs.getString("dish_no"));
				orditVO.setOrdit_qua(rs.getInt("ordit_qua"));
				list.add(orditVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public List<OrditVO> getDish_class05_all() {
		List<OrditVO> list = new ArrayList<OrditVO>();
		OrditVO orditVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_DISHCLASS05_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				orditVO = new OrditVO();
				orditVO.setDish_no(rs.getString("dish_no"));
				orditVO.setOrdit_qua(rs.getInt("ordit_qua"));
				list.add(orditVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public List<OrditVO> getAll_week() {
		List<OrditVO> list = new ArrayList<OrditVO>();
		OrditVO orditVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_WEEK);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				orditVO = new OrditVO();
				orditVO.setOrd_no(rs.getString("ord_no"));
				orditVO.setDish_no(rs.getString("dish_no"));
				orditVO.setOrdit_qua(rs.getInt("ordit_qua"));
				list.add(orditVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
