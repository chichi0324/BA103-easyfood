package com.blog.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.blog.model.BlogVO;
import com.blre.model.BlreVO;
import com.ra.model.RaVO;
import com.tools.tools;

public class BlogJDBCDAO implements BlogDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "easyfood";
	String passwd = "easyfood";
	
	private static final String INSERT_STMT = "INSERT INTO blog (bl_no,bl_name,bl_con,bl_date,mem_no,str_no) VALUES ('BL_'||LPAD(TO_CHAR(BL_SQ.NEXTVAL),4,'0'), ?, ?,?,?,?)";
	
	private static final String GET_ALL_STMT = "SELECT bl_no,bl_name,bl_con,bl_date,mem_no,str_no FROM blog";
	private static final String GET_ONE_STMT = "SELECT bl_no,bl_name,bl_con,bl_date,mem_no,str_no FROM blog where bl_no = ? ";
	private static final String GET_ALL_BYMEM = "SELECT bl_no,bl_name,bl_con,bl_date,mem_no,str_no FROM blog where mem_no = ? ";
	private static final String GET_BLREs_ByBLOG_STMT = "SELECT blre_no,blre_con,bl_no,mem_no,blre_date FROM BLOGRESPONSE where bl_no = ? order by blre_date desc";
	private static final String GET_RAs_ByBLOG_STMT = "SELECT ra_no,bl_no,ra_res,ra_rev FROM REPORTARTICLE where bl_no = ? order by ra_no";
	
	private static final String GET_BLRE_COUNT_ByBLOG ="SELECT B.MEM_NO, B.BL_NO,B.BL_NAME ,COUNT(*) FROM BLOG B JOIN BLOGRESPONSE R ON B.BL_NO=R.BL_NO WHERE B.BL_NO=? GROUP BY B.MEM_NO  ,B.BL_NO,B.BL_NAME";

	private static final String GET_BLOG_ByTIME ="SELECT * FROM BLOG WHERE BL_DATE BETWEEN ? AND ? ";

	private static final String GET_BLOG_ByKEYWORD ="SELECT * FROM BLOG WHERE BL_NAME LIKE ?";

	private static final String GET_BLOG_BySTORE ="SELECT * FROM BLOG WHERE STR_NO= ? ";
	
	private static final String DELETE_BLRE = "DELETE FROM BLOGRESPONSE where bl_no = ?";
	private static final String DELETE_BA = "DELETE FROM REPORTARTICLE where bl_no = ?";
	private static final String DELETE_BLOG = "DELETE FROM blog where bl_no = ?";	
	
	private static final String UPDATE = "UPDATE blog set bl_name=?,bl_con=?,bl_date=?,mem_no=?,str_no=? where bl_no= ?";
	
	

	@Override
	public void insert(BlogVO blogVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
					
			pstmt.setString(1, blogVO.getBl_name());
			pstmt.setClob(2, tools.stringToClob(blogVO.getBl_con(), con));						
			pstmt.setTimestamp(3, blogVO.getBl_date());
			pstmt.setString(4, blogVO.getMem_no());
			pstmt.setString(5, blogVO.getStr_no());

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
	public void update(BlogVO blogVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, blogVO.getBl_name());			
			pstmt.setClob(2, tools.stringToClob(blogVO.getBl_con(), con));
			pstmt.setTimestamp(3, blogVO.getBl_date());
			pstmt.setString(4, blogVO.getMem_no());
			pstmt.setString(5, blogVO.getStr_no());
	
			pstmt.setString(6, blogVO.getBl_no());
			
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
	public void delete(String bl_no) {
		int updateCount_BLREs = 0;
		int updateCount_BAs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_BLRE);
			pstmt.setString(1, bl_no);
			updateCount_BLREs = pstmt.executeUpdate();

			pstmt = con.prepareStatement(DELETE_BA);
			pstmt.setString(1, bl_no);
			updateCount_BAs = pstmt.executeUpdate();

			pstmt = con.prepareStatement(DELETE_BLOG);
			pstmt.setString(1, bl_no);
			pstmt.executeUpdate();


			con.commit();
			con.setAutoCommit(true);
			System.out.println("文章編號為" + bl_no + "刪除時,共有" + updateCount_BLREs
					+ "個文章回應和"+updateCount_BAs+"個文章檢舉被刪除");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {

					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public BlogVO findByPrimaryKey(String bl_no) {

		BlogVO blogVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bl_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				blogVO = new BlogVO();				
				blogVO.setBl_no(rs.getString(1));
				blogVO.setBl_name(rs.getString(2));				
				blogVO.setBl_con(tools.clobToString(rs.getClob(3)));
//				blogVO.setBl_con(rs.getString("bl_con"));
				blogVO.setBl_date(rs.getTimestamp(4));
				blogVO.setMem_no(rs.getString(5));
				blogVO.setStr_no(rs.getString(6));
				
//				blogVO = new BlogVO();				
//				blogVO.setBl_no(rs.getString("bl_no"));
//				blogVO.setBl_name(rs.getString("bl_name"));				
//				blogVO.setBl_con(readString(rs.getClob("bl_con")));
////				blogVO.setBl_con(rs.getString("bl_con"));
//				blogVO.setBl_date(rs.getDate("bl_date"));
//				blogVO.setMem_no(rs.getString("mem_no"));
//				blogVO.setStr_no(rs.getString("str_no"));
				
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
		} catch (IOException e) {
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
		return blogVO;
	}
	
	@Override
	public BlogVO findBLRECount(String bl_no) {

		BlogVO blogVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BLRE_COUNT_ByBLOG);

			pstmt.setString(1, bl_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				blogVO = new BlogVO();	
				blogVO.setMem_no(rs.getString(1));
				blogVO.setBl_no(rs.getString(2));
				blogVO.setBl_name(rs.getString(3));				
				blogVO.setBlre_count(rs.getString(4));
				
				
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
		return blogVO;
	}
	
	
	@Override
	public List<BlogVO> findByMember(String mem_no) {

		List<BlogVO> list = new ArrayList<BlogVO>();
		BlogVO blogVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BYMEM);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				blogVO = new BlogVO();				
				blogVO.setBl_no(rs.getString(1));
				blogVO.setBl_name(rs.getString(2));				
				blogVO.setBl_con(tools.clobToString(rs.getClob(3)));
//				blogVO.setBl_con(rs.getString("bl_con"));
				blogVO.setBl_date(rs.getTimestamp(4));
				blogVO.setMem_no(rs.getString(5));
				blogVO.setStr_no(rs.getString(6));
				list.add(blogVO);
				
//				blogVO = new BlogVO();				
//				blogVO.setBl_no(rs.getString("bl_no"));
//				blogVO.setBl_name(rs.getString("bl_name"));				
//				blogVO.setBl_con(readString(rs.getClob("bl_con")));
////				blogVO.setBl_con(rs.getString("bl_con"));
//				blogVO.setBl_date(rs.getDate("bl_date"));
//				blogVO.setMem_no(rs.getString("mem_no"));
//				blogVO.setStr_no(rs.getString("str_no"));
				
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
		} catch (IOException e) {
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
	public List<BlogVO> findByTime(Timestamp timestamp1,Timestamp timestamp2) {

		List<BlogVO> list = new ArrayList<BlogVO>();
		BlogVO blogVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BLOG_ByTIME);

			pstmt.setTimestamp(1,timestamp1);
			pstmt.setTimestamp(2,timestamp2);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				blogVO = new BlogVO();				
				blogVO.setBl_no(rs.getString(1));
				blogVO.setBl_name(rs.getString(2));				
				blogVO.setBl_con(tools.clobToString(rs.getClob(3)));
//				blogVO.setBl_con(rs.getString("bl_con"));
				blogVO.setBl_date(rs.getTimestamp(4));
				blogVO.setMem_no(rs.getString(5));
				blogVO.setStr_no(rs.getString(6));
				list.add(blogVO);
				
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
		} catch (IOException e) {
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
	public List<BlogVO> findByKeyword(String keyword) {

		List<BlogVO> list = new ArrayList<BlogVO>();
		BlogVO blogVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BLOG_ByKEYWORD);

			pstmt.setString(1,"%"+keyword+"%");

			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				blogVO = new BlogVO();				
				blogVO.setBl_no(rs.getString(1));
				blogVO.setBl_name(rs.getString(2));				
				blogVO.setBl_con(tools.clobToString(rs.getClob(3)));
//				blogVO.setBl_con(rs.getString("bl_con"));
				blogVO.setBl_date(rs.getTimestamp(4));
				blogVO.setMem_no(rs.getString(5));
				blogVO.setStr_no(rs.getString(6));
				list.add(blogVO);
				
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
		} catch (IOException e) {
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
	public List<BlogVO> findByStore(String str_no) {

		List<BlogVO> list = new ArrayList<BlogVO>();
		BlogVO blogVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BLOG_BySTORE);

			pstmt.setString(1,str_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				blogVO = new BlogVO();				
				blogVO.setBl_no(rs.getString(1));
				blogVO.setBl_name(rs.getString(2));				
				blogVO.setBl_con(rs.getString("bl_con"));
				blogVO.setBl_date(rs.getTimestamp(4));
				blogVO.setMem_no(rs.getString(5));
				blogVO.setStr_no(rs.getString(6));
				list.add(blogVO);
				
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
	public List<BlogVO> getAll() {
		List<BlogVO> list = new ArrayList<BlogVO>();
		BlogVO blogVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				blogVO = new BlogVO();
				blogVO.setBl_no(rs.getString(1));
				blogVO.setBl_name(rs.getString(2));							   
				blogVO.setBl_con(rs.getString(3));
				blogVO.setBl_date(rs.getTimestamp(4));
				blogVO.setMem_no(rs.getString(5));
				blogVO.setStr_no(rs.getString(6));
				
//				blogVO = new BlogVO();
//				blogVO.setBl_no(rs.getString("bl_no"));
//				blogVO.setBl_name(rs.getString("bl_name"));
//				
//				blogVO.setBl_con(readString(rs.getClob("bl_con"));
////				blogVO.setBl_con(rs.getString("bl_con"));
//				blogVO.setBl_date(rs.getDate("bl_date"));
//				blogVO.setMem_no(rs.getString("mem_no"));
//				blogVO.setStr_no(rs.getString("str_no"));
				list.add(blogVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Set<BlreVO> getBLREsByBlog(String bl_no) {
		Set<BlreVO> set = new LinkedHashSet<BlreVO>();
		BlreVO blreVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BLREs_ByBLOG_STMT);
			pstmt.setString(1, bl_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				blreVO = new BlreVO();
				blreVO.setBlre_no(rs.getString(1));
				blreVO.setBlre_con(tools.clobToString(rs.getClob(2)));				
//				blreVO.setBlre_con(rs.getString(2));
				blreVO.setBl_no(rs.getString(3));
				blreVO.setMem_no(rs.getString(4));
				blreVO.setBlre_date(rs.getTimestamp(5));
				set.add(blreVO); // Store the row in the vector
				
//				blreVO = new BlreVO();
//				blreVO.setBlre_no(rs.getString("blre_no"));
//				
//				clob = rs.getClob("blre_con");
//				blreVO.setBlre_con(readString(clob));
////				blreVO.setBlre_con(rs.getString("blre_con"));
//				blreVO.setBl_no(rs.getString("bl_no"));
//				blreVO.setMem_no(rs.getString("mem_no"));
//				blreVO.setBlre_date(rs.getDate("blre_date"));
//				set.add(blreVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (IOException e) {
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
		return set;
	}
	
	@Override
	public Set<RaVO> getRAsByBlog(String bl_no) {
		Set<RaVO> set = new LinkedHashSet<RaVO>();
		RaVO raVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_RAs_ByBLOG_STMT);
			pstmt.setString(1, bl_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				raVO = new RaVO();
				raVO.setRa_no(rs.getString("ra_no"));				
				raVO.setBl_no(rs.getString("bl_no"));
				
				raVO.setRa_res(tools.clobToString(rs.getClob("ra_res")));
//				raVO.setRa_res(rs.getString("ra_res"));
				raVO.setRa_rev(rs.getString("ra_rev"));
				set.add(raVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (IOException e) {
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
		return set;
	}
	
	public List<String> getRAstasByBlog(String bl_no){
		return null;
	};
	

}
