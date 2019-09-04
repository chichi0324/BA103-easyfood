package com.mem.model;

import java.util.*;

import java.sql.*;

public class MemJDBCDAO implements MemDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "easyfood";
	String passwd = "easyfood";

	private static final String INSERT_STMT = "INSERT INTO member (mem_no,mem_acc,mem_pw,mem_name,mem_pho,mem_mail) VALUES ('MEM_'||LPAD(TO_CHAR(MEM_SQ.NEXTVAL),4,'0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT mem_no,mem_acc,mem_pw,mem_name,mem_pho,mem_mail,mem_vio,mem_stas FROM member order by mem_no";
	private static final String GET_ONE_STMT = "SELECT mem_no,mem_acc,mem_pw,mem_name,mem_pho,mem_mail,mem_vio,mem_stas FROM member where mem_no = ?";
	private static final String GET_MEM_NO_STMT = "SELECT mem_no FROM member where mem_acc = ?";
	private static final String GET_MEM_ByAcc = "SELECT * FROM member where mem_acc = ?";
	private static final String DELETE = "DELETE FROM member where mem_no = ?";
	private static final String UPDATE = "UPDATE member set mem_pw=?, mem_name=?, mem_pho=?, mem_mail=?, mem_vio=?, mem_stas=? where mem_no = ?";
	private static final String UPDATE_MEM_SELF = "UPDATE member set mem_pw=?, mem_name=?, mem_pho=?, mem_mail=? where mem_no = ?";
	private static final String GET_VIO_STAS ="SELECT * FROM member where MEM_VIO=? and MEM_STAS=?";
	private static final String UPDATE_VIO = "UPDATE MEMBER SET MEM_VIO = ? WHERE MEM_NO = ?";
	
	@Override
	public void insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMem_acc());
			pstmt.setString(2, memVO.getMem_pw());
			pstmt.setString(3, memVO.getMem_name());
			pstmt.setString(4, memVO.getMem_pho());
			pstmt.setString(5, memVO.getMem_mail());

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
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMem_pw());
			pstmt.setString(2, memVO.getMem_name());
			pstmt.setString(3, memVO.getMem_pho());
			pstmt.setString(4, memVO.getMem_mail());
			pstmt.setInt(5, memVO.getMem_vio());
			pstmt.setString(6, memVO.getMem_stas());
			pstmt.setString(7, memVO.getMem_no());

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
	public void updateMemSelf(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_MEM_SELF);

			pstmt.setString(1, memVO.getMem_pw());
			pstmt.setString(2, memVO.getMem_name());
			pstmt.setString(3, memVO.getMem_pho());
			pstmt.setString(4, memVO.getMem_mail());
			pstmt.setString(5, memVO.getMem_no());

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
	public void delete(String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_no);

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
	public MemVO findByPrimaryKey(String mem_no) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				memVO = new MemVO();
				memVO.setMem_no(rs.getString("mem_no"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_pho(rs.getString("mem_pho"));
				memVO.setMem_mail(rs.getString("mem_mail"));
				memVO.setMem_vio(rs.getInt("mem_vio"));
				memVO.setMem_stas(rs.getString("mem_stas"));

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
		return memVO;
	}

	@Override
	public String findMem_noByMem_acc(String mem_acc) {

		String mem_no = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEM_NO_STMT);
			pstmt.setString(1, mem_acc);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				mem_no = rs.getString("mem_no");
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
		return mem_no;

	}
	
	@Override
	public MemVO findMem_noByMemAcc(String mem_acc) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEM_ByAcc);
			pstmt.setString(1, mem_acc);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				memVO = new MemVO();
				memVO.setMem_no(rs.getString("mem_no"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_pho(rs.getString("mem_pho"));	
				memVO.setMem_mail(rs.getString("mem_mail"));
				memVO.setMem_vio(rs.getInt("mem_vio"));
				memVO.setMem_stas(rs.getString("mem_stas"));
				memVO.setMem_img(rs.getBytes("mem_img"));
			}

			// Handle any driver errors
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
		return memVO;
	}


	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

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
				memVO = new MemVO();
				memVO.setMem_no(rs.getString("mem_no"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_pho(rs.getString("mem_pho"));
				memVO.setMem_mail(rs.getString("mem_mail"));
				memVO.setMem_vio(rs.getInt("mem_vio"));
				memVO.setMem_stas(rs.getString("mem_stas"));
				list.add(memVO); // Store the row in the list
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
	public List<MemVO> getVio_Stas(Integer mem_vio,String mem_stas) {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_VIO_STAS);
			
			pstmt.setInt(1, mem_vio);
			pstmt.setString(2, mem_stas);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				memVO = new MemVO();
				memVO.setMem_no(rs.getString("mem_no"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_pho(rs.getString("mem_pho"));	
				memVO.setMem_mail(rs.getString("mem_mail"));
				memVO.setMem_vio(rs.getInt("mem_vio"));
				memVO.setMem_stas(rs.getString("mem_stas"));
				memVO.setMem_img(Objects.isNull(rs.getBytes("mem_img")) ? "0".getBytes() : rs.getBytes("mem_img"));
				list.add(memVO); // Store the row in the list
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
	public void updateVio(Integer mem_vio,String mem_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_VIO);
			
			pstmt.setInt(1, mem_vio);
			pstmt.setString(2, mem_no);
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public static void main(String[] args) {

		MemJDBCDAO dao = new MemJDBCDAO();

		// 新增
		 MemVO memVO1 = new MemVO();
		 memVO1.setMem_acc("tiger3");
		 memVO1.setMem_pw("123456");
		 memVO1.setMem_name("吳永志");
		 memVO1.setMem_pho("0978123456");
		 memVO1.setMem_mail("tiger@gmail.com");
		 dao.insert(memVO1);

		// 修改
		// MemVO memVO2 = new MemVO();
		// memVO2.setMem_pw("234561");
		// memVO2.setMem_name("吳永志5");
		// memVO2.setMem_pho("0970654321");
		// memVO2.setMem_mail("cat@gmail.com");
		// memVO2.setMem_vio(2);
		// memVO2.setMem_stas("正常");
		// memVO2.setMem_no("MEM_0007");
		// dao.update(memVO2);

		//會員修改個資料(不包含vio, stas)
//		MemVO memVO4 = new MemVO();
//		memVO4.setMem_pw("234561");
//		memVO4.setMem_name("吳永志5");
//		memVO4.setMem_pho("0970654321");
//		memVO4.setMem_mail("cat@gmail.com");
//		memVO4.setMem_no("MEM_0032");
//		dao.updateMemSelf(memVO4);

		// // �R��
		// dao.delete("MEM_0007");

		// �d��
		// MemVO memVO3 = dao.findByPrimaryKey("MEM_0007");
		// System.out.print(memVO3.getMem_no() + ",");
		// System.out.print(memVO3.getMem_acc() + ",");
		// System.out.print(memVO3.getMem_pw() + ",");
		// System.out.print(memVO3.getMem_name() + ",");
		// System.out.print(memVO3.getMem_pho() + ",");
		// System.out.print(memVO3.getMem_mail()+ ",");
		// System.out.print(memVO3.getMem_vio()+ ",");
		// System.out.println(memVO3.getMem_stas());
		// System.out.println("---------------------");

		// 找mem_no
		// String mem_no = dao.findMem_noByMem_acc("tiger3");
		// System.out.println(mem_no);
		// System.out.println("---------------------");

		// �d�ߥ���
		// List<MemVO> list = dao.getAll();
		// for (MemVO aMem : list) {
		// System.out.print(aMem.getMem_no() + ",");
		// System.out.print(aMem.getMem_acc() + ",");
		// System.out.print(aMem.getMem_pw() + ",");
		// System.out.print(aMem.getMem_name() + ",");
		// System.out.print(aMem.getMem_pho() + ",");
		// System.out.print(aMem.getMem_mail()+ ",");
		// System.out.print(aMem.getMem_vio()+ ",");
		// System.out.print(aMem.getMem_stas());
		// System.out.println();
		// }
		
		
//		 MemVO memVO = dao.findMem_noByMemAcc("CHICHI");
//		 System.out.print(memVO.getMem_no() + ",");
//		 System.out.print(memVO.getMem_acc() + ",");
//		 System.out.print(memVO.getMem_pw() + ",");
//		 System.out.print(memVO.getMem_name() + ",");
//		 System.out.print(memVO.getMem_pho() + ",");
//		 System.out.print(memVO.getMem_mail()+ ",");
//		 System.out.print(memVO.getMem_vio()+ ",");
//		 System.out.print(memVO.getMem_stas());
		
		
		// �d�ߥ���
//		 List<MemVO> list = dao.getVio_Stas(0, "正常");
//		 for (MemVO aMem : list) {
//		 System.out.print(aMem.getMem_no() + ",");
//		 System.out.print(aMem.getMem_acc() + ",");
//		 System.out.print(aMem.getMem_pw() + ",");
//		 System.out.print(aMem.getMem_name() + ",");
//		 System.out.print(aMem.getMem_pho() + ",");
//		 System.out.print(aMem.getMem_mail()+ ",");
//		 System.out.print(aMem.getMem_vio()+ ",");
//		 System.out.print(aMem.getMem_stas());
//		 System.out.println();
//		 }

	}

}
