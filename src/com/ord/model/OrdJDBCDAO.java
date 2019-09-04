package com.ord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ord.model.OrdJDBCDAO;
import com.tools.tools;

public class OrdJDBCDAO implements OrdDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@10.211.55.3:1521:XE";
	String userid = "easyfood";
	String passwd = "easyfood";

	private static final String INSERT_STMT = "INSERT INTO ord (ord_no,mem_no,str_no,ord_type,ord_pri,ord_date) VALUES ('ORD_'||LPAD(TO_CHAR(ORD_SQ.NEXTVAL),4,'0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT ord_no,mem_no,str_no,ord_ev,ord_type,ord_pri,ord_date,ord_stat,ord_eva FROM ord order by ord_no";
	private static final String GET_ONE_STMT = "SELECT ord_no,mem_no,str_no,ord_ev,ord_type,ord_pri,ord_date,ord_stat,ord_eva FROM ord where ord_no = ?";
	private static final String DELETE = "DELETE FROM ord where ord_no = ?";
	private static final String UPDATE = "UPDATE ord set ord_ev=?, ord_eva=? where ord_no = ?";
	private static final String GET_ALL_BY_MEMSELF_STMT = "SELECT ord_no,mem_no,str_no,ord_type,ord_pri,ord_date,ord_stat FROM ord order by ord_no";
	private static final String Get_ALL_BY_STORE = "SELECT * FROM ORD WHERE STR_NO = ? ORDER BY ORD_DATE DESC";
	private static final String GET_INTERVAL = "SELECT * FROM ORD WHERE STR_NO = ? AND ORD_DATE BETWEEN (SELECT TRUNC(SYSDATE, ?) FROM DUAL) AND SYSDATE ORDER BY ORD_NO DESC";
	private static final String GET_ONE_BY_STORE = "SELECT * FROM ORD WHERE STR_NO = ? AND ORD_NO = ?";
	private static final String GET_STATUS = "SELECT * FROM ORD WHERE STR_NO = ? AND ORD_STAT = ? ORDER BY ORD_NO DESC";
	private static final String UPDATE_STATUS = "UPDATE ORD SET ORD_STAT = ? WHERE ORD_NO = ?";
	private static final String GET_ONE_BYSTORE = "SELECT * FROM ORD WHERE STR_NO = ?";
	private static final String GET_Four_ByStar = "SELECT * FROM ord where ord_ev >= 4 order by ord_ev desc";
	
	@Override
	public void insert(OrdVO ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ordVO.getMem_no());
			pstmt.setString(2, ordVO.getStr_no());
			pstmt.setString(3, ordVO.getOrd_type());
			pstmt.setDouble(4, ordVO.getOrd_pri());
			pstmt.setTimestamp(5, ordVO.getOrd_date());

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
	public void update(OrdVO ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ordVO.getOrd_ev());
			pstmt.setClob(2, tools.stringToClob(ordVO.getOrd_eva(),con));
			pstmt.setString(3, ordVO.getOrd_no());
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
	public void delete(String ord_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ord_no);

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
	public OrdVO findByPrimaryKey(String ord_no) {
		OrdVO ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ord_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				ordVO = new OrdVO();
				ordVO.setOrd_no(rs.getString("ord_no"));
				ordVO.setMem_no(rs.getString("mem_no"));
				ordVO.setStr_no(rs.getString("str_no"));
				ordVO.setOrd_ev(rs.getInt("ord_ev"));
				ordVO.setOrd_type(rs.getString("ord_type"));
				ordVO.setOrd_pri(rs.getDouble("ord_pri"));
				ordVO.setOrd_date(rs.getTimestamp("ord_date"));	
				ordVO.setOrd_stat(rs.getString("ord_stat"));
				ordVO.setOrd_eva(rs.getString("ord_eva"));
								
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
		return ordVO;
	}

	@Override
	public List<OrdVO> getAll() {
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				ordVO = new OrdVO();
				ordVO.setOrd_no(rs.getString("ord_no"));
				ordVO.setMem_no(rs.getString("mem_no"));
				ordVO.setStr_no(rs.getString("str_no"));
				ordVO.setOrd_ev(rs.getInt("ord_ev"));
				ordVO.setOrd_type(rs.getString("ord_type"));
				ordVO.setOrd_pri(rs.getDouble("ord_pri"));
				ordVO.setOrd_date(rs.getTimestamp("ord_date"));	
				ordVO.setOrd_stat(rs.getString("ord_stat"));
				ordVO.setOrd_eva(rs.getString("ord_eva"));  //用getString取Clob可能會有問題
				list.add(ordVO); // Store the row in the list
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
	public List<OrdVO> getAll_byMemSelf() {
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);		
			pstmt = con.prepareStatement(GET_ALL_BY_MEMSELF_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				ordVO = new OrdVO();
				ordVO.setOrd_no(rs.getString("ord_no"));
				ordVO.setMem_no(rs.getString("mem_no"));
				ordVO.setStr_no(rs.getString("str_no"));
				ordVO.setOrd_type(rs.getString("ord_type"));
				ordVO.setOrd_pri(rs.getDouble("ord_pri"));
				ordVO.setOrd_date(rs.getTimestamp("ord_date"));	
				ordVO.setOrd_stat(rs.getString("ord_stat"));
				list.add(ordVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (ClassNotFoundException e) {
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
	public List<OrdVO> getAllByStr(String str_no) {
		
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_ALL_BY_STORE);
			pstmt.setString(1, str_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ordVO = new OrdVO();
				ordVO.setOrd_no(rs.getString("ORD_NO"));
				ordVO.setMem_no(rs.getString("MEM_NO"));
				ordVO.setStr_no(rs.getString("STR_NO"));
				ordVO.setOrd_ev(rs.getInt("ORD_EV"));
				ordVO.setOrd_type(rs.getString("ORD_TYPE"));
				ordVO.setOrd_pri(rs.getDouble("ORD_PRI"));
				ordVO.setOrd_date(rs.getTimestamp("ORD_DATE"));	
				ordVO.setOrd_stat(rs.getString("ORD_STAT"));
				ordVO.setOrd_eva(rs.getString("ORD_EVA"));
				list.add(ordVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	
	
	
	@Override
	public List<OrdVO> getIntervalByStr(String str_no, String interval) {
		
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_INTERVAL);
			pstmt.setString(1, str_no);
			pstmt.setString(2, interval);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ordVO = new OrdVO();
				ordVO.setOrd_no(rs.getString("ORD_NO"));
				ordVO.setMem_no(rs.getString("MEM_NO"));
				ordVO.setStr_no(rs.getString("STR_NO"));
				ordVO.setOrd_ev(rs.getInt("ORD_EV"));
				ordVO.setOrd_type(rs.getString("ORD_TYPE"));
				ordVO.setOrd_pri(rs.getDouble("ORD_PRI"));
				ordVO.setOrd_date(rs.getTimestamp("ORD_DATE"));	
				ordVO.setOrd_stat(rs.getString("ORD_STAT"));
				ordVO.setOrd_eva(rs.getString("ORD_EVA"));
				ordVO.setOrd_eva(rs.getString("ORD_ADD"));
				list.add(ordVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	
	
	@Override
	public OrdVO getOneByStr(String ord_no, String str_no) {
		
		OrdVO ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_STORE);
			pstmt.setString(1, str_no);
			pstmt.setString(2, ord_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ordVO = new OrdVO();
				ordVO.setOrd_no(rs.getString("ORD_NO"));
				ordVO.setMem_no(rs.getString("MEM_NO"));
				ordVO.setStr_no(rs.getString("STR_NO"));
				ordVO.setOrd_ev(rs.getInt("ORD_EV"));
				ordVO.setOrd_type(rs.getString("ORD_TYPE"));
				ordVO.setOrd_pri(rs.getDouble("ORD_PRI"));
				ordVO.setOrd_date(rs.getTimestamp("ORD_DATE"));	
				ordVO.setOrd_stat(rs.getString("ORD_STAT"));
				ordVO.setOrd_eva(rs.getString("ORD_EVA"));
				ordVO.setOrd_eva(rs.getString("ORD_ADD"));
				
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return ordVO;
	}	
	
	@Override
	public List<OrdVO> getStatus(String str_no, String ord_stat) {
		
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STATUS);
			pstmt.setString(1, ord_stat);
			pstmt.setString(2, str_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ordVO = new OrdVO();
				ordVO.setOrd_no(rs.getString("ORD_NO"));
				ordVO.setMem_no(rs.getString("MEM_NO"));
				ordVO.setStr_no(rs.getString("STR_NO"));
				ordVO.setOrd_ev(rs.getInt("ORD_EV"));
				ordVO.setOrd_type(rs.getString("ORD_TYPE"));
				ordVO.setOrd_pri(rs.getDouble("ORD_PRI"));
				ordVO.setOrd_date(rs.getTimestamp("ORD_DATE"));	
				ordVO.setOrd_stat(rs.getString("ORD_STAT"));
				ordVO.setOrd_eva(rs.getString("ORD_EVA"));
				ordVO.setOrd_eva(rs.getString("ORD_ADD"));
				list.add(ordVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	@Override
	public void updateStatus(String ord_no, String ord_stat) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, ord_stat);
			pstmt.setString(2, ord_no);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		
	}
	
	
	@Override
	public List<OrdVO> findByStore(String str_no) {
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);		
			pstmt = con.prepareStatement(GET_ONE_BYSTORE);
			
			pstmt.setString(1, str_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects  
				ordVO = new OrdVO();
				ordVO.setOrd_no(rs.getString("ord_no"));
				ordVO.setMem_no(rs.getString("mem_no"));
				ordVO.setStr_no(rs.getString("str_no"));
				ordVO.setOrd_ev(rs.getInt("ord_ev"));
				ordVO.setOrd_type(rs.getString("ord_type"));
				ordVO.setOrd_pri(rs.getDouble("ord_pri"));
				ordVO.setOrd_date(rs.getTimestamp("ord_date"));	
				ordVO.setOrd_stat(rs.getString("ord_stat"));
				ordVO.setOrd_eva(Objects.isNull(rs.getString("ord_eva")) ? "" :rs.getString("ord_eva"));  //用getString取Clob可能會有問題
				list.add(ordVO); // Store the row in the list
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
	
	
	@Override
	public List<OrdVO> getAllByStar() {
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);				
			pstmt = con.prepareStatement(GET_Four_ByStar);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				ordVO = new OrdVO();
				ordVO.setOrd_no(rs.getString("ord_no"));
				ordVO.setMem_no(rs.getString("mem_no"));
				ordVO.setStr_no(rs.getString("str_no"));
				ordVO.setOrd_ev(rs.getInt("ord_ev"));
				ordVO.setOrd_type(rs.getString("ord_type"));
				ordVO.setOrd_pri(rs.getDouble("ord_pri"));
				ordVO.setOrd_date(rs.getTimestamp("ord_date"));	
				ordVO.setOrd_stat(rs.getString("ord_stat"));
				ordVO.setOrd_eva(Objects.isNull(rs.getString("ord_eva")) ? "" :rs.getString("ord_eva"));
				list.add(ordVO); // Store the row in the list
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
		OrdJDBCDAO dao = new OrdJDBCDAO();
		
		// 新增
//		OrdVO ordVO1 = new OrdVO();
//		ordVO1.setMem_no("MEM_0002");
//		ordVO1.setStr_no("STR_0001");
//		ordVO1.setOrd_type("外送");
//		ordVO1.setOrd_pri(750.0);
//		ordVO1.setOrd_date(new tools().nowTimestamp());  // oracle 沒顯示時分
//		dao.insert(ordVO1);
//		
		// 修改
//		OrdVO ordVO2 = new OrdVO();
//		ordVO2.setOrd_ev(10);
//		ordVO2.setOrd_eva("喔依西捏!");
//		ordVO2.setOrd_no("ORD_0010");
//		dao.update(ordVO2);
				
//		// �R��
//		dao.delete("ORD_0008");

		// 查詢
//		OrdVO ordVO3 = dao.findByPrimaryKey("ORD_0010");
//		System.out.print(ordVO3.getOrd_no() + ",");
//		System.out.print(ordVO3.getMem_no() + ",");
//		System.out.print(ordVO3.getStr_no() + ",");
//		System.out.print(ordVO3.getOrd_ev() + ",");
//		System.out.print(ordVO3.getOrd_type() + ",");
//		System.out.print(ordVO3.getOrd_pri() + ",");
//		System.out.print(ordVO3.getOrd_date()+ ",");
//		System.out.print(ordVO3.getOrd_stat()+ ",");
//		System.out.println(ordVO3.getOrd_eva());
//		System.out.println("---------------------");
		
		 //查全部
//		List<OrdVO> list = dao.getAll();
//		for (OrdVO aOrd : list) {
//			System.out.print(aOrd.getOrd_no() + ",");
//			System.out.print(aOrd.getMem_no() + ",");
//			System.out.print(aOrd.getStr_no() + ",");
//			System.out.print(aOrd.getOrd_ev() + ",");
//			System.out.print(aOrd.getOrd_type() + ",");
//			System.out.print(aOrd.getOrd_pri() + ",");
//			System.out.print(aOrd.getOrd_date()+ ",");
//			System.out.print(aOrd.getOrd_stat()+ ",");
//			System.out.println(aOrd.getOrd_eva());
//			System.out.println();
//		}
		
//		List<OrdVO> list = dao.getAll();
//		for (OrdVO aOrd : list) {
//			System.out.print(aOrd.getOrd_no() + ",");
//			System.out.print(aOrd.getMem_no() + ",");
//			System.out.print(aOrd.getStr_no() + ",");
//			System.out.print(aOrd.getOrd_type() + ",");
//			System.out.print(aOrd.getOrd_pri() + ",");
//			System.out.print(aOrd.getOrd_date()+ ",");
//			System.out.print(aOrd.getOrd_stat()+ ",");
//			System.out.println();
//		}
		
		List<OrdVO> list = dao.getIntervalByStr("STR_0071", "DAY");
		for (OrdVO aOrd : list) {
			System.out.print(aOrd.getOrd_no() + ",");
			System.out.print(aOrd.getMem_no() + ",");
			System.out.print(aOrd.getStr_no() + ",");
			System.out.print(aOrd.getOrd_type() + ",");
			System.out.print(aOrd.getOrd_pri() + ",");
			System.out.print(aOrd.getOrd_date()+ ",");
			System.out.print(aOrd.getOrd_stat()+ ",");
			System.out.print(aOrd.getOrd_add()+ ",");
			System.out.println();
		}
		
		
	}

	
	

	










}
