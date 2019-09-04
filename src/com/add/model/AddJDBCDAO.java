package com.add.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddJDBCDAO implements AddDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "easyfood";
	String passwd = "easyfood";

	private static final String INSERT_STMT = "INSERT INTO address (add_no,mem_no,add_adds) VALUES ('ADD_'||LPAD(TO_CHAR(ADD_SQ.NEXTVAL),4,'0'), ?, ?)";
	private static final String GET_ALL_STMT = "SELECT add_no,mem_no,add_adds FROM address order by add_no";
	private static final String GET_ONE_STMT = "SELECT add_no,mem_no,add_adds FROM address where add_no = ?";
	private static final String DELETE = "DELETE FROM address where add_no = ?";
	private static final String UPDATE = "UPDATE address set add_adds =? where add_no = ?";

	@Override
	public void insert(AddVO addVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, addVO.getMem_no());
			pstmt.setString(2, addVO.getAdd_adds());
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
	public void update(AddVO addVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, addVO.getAdd_adds());
			pstmt.setString(2, addVO.getAdd_no());
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
	public void delete(String add_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, add_no);

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
	public AddVO findByPrimaryKey(String add_no) {
		AddVO addVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, add_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				addVO = new AddVO();
				addVO.setAdd_no(rs.getString("add_no"));
				addVO.setMem_no(rs.getString("mem_no"));
				addVO.setAdd_adds(rs.getString("add_adds"));

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
		return addVO;
	}

	@Override
	public List<AddVO> getAll() {
		List<AddVO> list = new ArrayList<AddVO>();
		AddVO addVO = null;
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
				addVO = new AddVO();
				addVO.setAdd_no(rs.getString("add_no"));
				addVO.setMem_no(rs.getString("mem_no"));
				addVO.setAdd_adds(rs.getString("add_adds"));
				list.add(addVO); // Store the row in the list
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

	public static void main(String[] args) {

		AddJDBCDAO dao = new AddJDBCDAO();

		// 新增
		// AddVO addVO1 = new AddVO();
		// addVO1.setMem_no("MEM_0003");
		// addVO1.setAdd_adds("桃園市平鎮區金陵路二段81巷5號");
		// dao.insert(addVO1);

		// 修改
		// AddVO addVO2 = new AddVO();
		// addVO2.setAdd_adds("桃園市中壢區九和一街57號");
		// addVO2.setAdd_no("ADD_0007");
		// dao.update(addVO2);

		// // �R��
		// dao.delete("ADD_0007");

		// 查詢
		// AddVO addVO3 = dao.findByPrimaryKey("ADD_0006");
		// System.out.print(addVO3.getAdd_no() + ",");
		// System.out.print(addVO3.getMem_no() + ",");
		// System.out.print(addVO3.getAdd_adds() + ",");
		// System.out.println("---------------------");
		//
		// 查全部
		// List<AddVO> list = dao.getAll();
		// for (AddVO aAdd : list) {
		// System.out.print(aAdd.getAdd_no() + ",");
		// System.out.print(aAdd.getMem_no() + ",");
		// System.out.print(aAdd.getAdd_adds() + ",");
		// System.out.println();
		// }
		//
	}
}
