package com.rep.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tools.tools;

public class RepDAO implements RepDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
	
			pstmt.setClob(1, tools.stringToClob(repVO.getRep_res(), con));
			pstmt.setString(2, repVO.getOrd_no());
			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
	
			pstmt.setString(1, repVO.getRep_res());
			pstmt.setString(2, repVO.getRep_rev());
			pstmt.setString(3, repVO.getRep_no());
			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rep_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_REV_STMT);

			pstmt.setString(1, rep_rev);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				repVO = new RepVO();
				repVO.setRep_no(rs.getString(1));
				repVO.setRep_res(rs.getString(2));
				repVO.setRep_rev(rs.getString(3));
				repVO.setOrd_no(rs.getString(4));
				repVO.setStr_no(rs.getString(5));
				repVO.setMem_no(rs.getString(6));
				list.add(repVO);				
			}

			// Handle any driver errors
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
	
}
