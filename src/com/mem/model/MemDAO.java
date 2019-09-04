package com.mem.model;

import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;

public class MemDAO implements MemDAO_interface{

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
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
			"INSERT INTO member (mem_no,mem_acc,mem_pw,mem_name,mem_pho,mem_mail,mem_img) VALUES ('MEM_'||LPAD(TO_CHAR(MEM_SQ.NEXTVAL),4,'0'), ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT mem_no,mem_acc,mem_pw,mem_name,mem_pho,mem_mail,mem_vio,mem_stas FROM member order by mem_no";
		private static final String GET_ONE_STMT = 
			"SELECT mem_no,mem_acc,mem_pw,mem_name,mem_pho,mem_mail,mem_vio,mem_stas,mem_img FROM member where mem_no = ?";
		private static final String GET_MEM_NO_STMT =
			"SELECT mem_no FROM member where mem_acc = ?";
		private static final String GET_MEM_ByAcc =
				"SELECT * FROM member where mem_acc = ?";
		private static final String DELETE = 
			"DELETE FROM member where mem_no = ?";
		private static final String UPDATE = 
			"UPDATE member set mem_pw=?, mem_name=?, mem_pho=?, mem_mail=?, mem_vio=?, mem_stas=? where mem_no = ?";
		private static final String UPDATE_MEM_SELF = 
				"UPDATE member set mem_pw=?, mem_name=?, mem_pho=?, mem_mail=?, mem_img = ? where mem_no = ?";
		private static final String GET_VIO_STAS ="select * from member where MEM_VIO=? and MEM_STAS=?";
		private static final String UPDATE_VIO = "UPDATE MEMBER SET MEM_VIO = ? WHERE MEM_NO = ?";
		
		@Override
		public void insert(MemVO memVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, memVO.getMem_acc());
				pstmt.setString(2, memVO.getMem_pw());
				pstmt.setString(3, memVO.getMem_name());
				pstmt.setString(4, memVO.getMem_pho());
				pstmt.setString(5, memVO.getMem_mail());
				pstmt.setBytes(6 , memVO.getMem_img());

				pstmt.executeUpdate();

				// Handle any driver errors
			}  catch (SQLException se) {
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
		public void update(MemVO memVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
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
		
		public void updateMemSelf(MemVO memVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_MEM_SELF);

				pstmt.setString(1, memVO.getMem_pw());
				pstmt.setString(2, memVO.getMem_name());
				pstmt.setString(3, memVO.getMem_pho());
				pstmt.setString(4, memVO.getMem_mail());
				pstmt.setBytes(5 , memVO.getMem_img());
				pstmt.setString(6, memVO.getMem_no());

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
		public void delete(String mem_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, mem_no);

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
		public MemVO findByPrimaryKey(String mem_no) {
			
			MemVO memVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
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
					memVO.setMem_img(rs.getBytes("mem_img"));
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
			return memVO;
		}

		@Override
		public String findMem_noByMem_acc(String mem_acc) {

			String mem_no = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_MEM_NO_STMT);
				pstmt.setString(1, mem_acc);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					// empVo �]�٬� Domain objects
					mem_no = rs.getString("mem_no");
				}

				// Handle any driver errors
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

				con = ds.getConnection();
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

				con = ds.getConnection();
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
					//memVO.setMem_img(Objects.isNull(rs.getBytes("mem_img")) ? "0".getBytes() : rs.getBytes("mem_img"));
					list.add(memVO); // Store the row in the list
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
		public List<MemVO> getVio_Stas(Integer mem_vio,String mem_stas) {
			List<MemVO> list = new ArrayList<MemVO>();
			MemVO memVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_VIO);
				
				pstmt.setInt(1, mem_vio);
				pstmt.setString(2, mem_no);
				
				pstmt.executeUpdate();

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	
}
