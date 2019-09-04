package com.dish.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dishclass.model.DclaVO;
import com.stream.tool.ShareTool;

public class DishJDBCDAO implements DishDAO_interface {
	
	private static final String URL = "jdbc:oracle:thin:@10.211.55.3:1521:XE";
	private static final String USER = "test0101";
	private static final String PASSWORD = "test0101";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	private static final String INSERT = "INSERT INTO DISH(DISH_NO, DISH_NAME, DISH_PRI, DCLA_NO, STR_NO, DISH_IMG, DISH_NOTE) "
			+ "VALUES('DISH_'||LPAD(DISH_SQ.NEXTVAL, 4, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE DISH SET DISH_NAME = ?, DISH_PRI = ?, DISH_STA = ?, DISH_IMG = ?, DISH_NOTE = ? WHERE DISH_NO = ?";
	private static final String UPDATE_STATUS = "UPDATE DISH SET DISH_STA = ? WHERE DISH_NO = ?";
	private static final String FIND_BY_DISH_NO = "SELECT * FROM DISH WHERE DISH_NO = ?";
	private static final String FIND_BY_DISH_CLASS = "SELECT * FROM DISH WHERE DCLA_NO = ?";
	private static final String FIND_BY_STORE = "SELECT * FROM DISH WHERE STR_NO = ? ORDER BY DCLA_NO";
	private static final String FIND_BY_PRICE = "SELECT * FROM DISH WHERE DISH_PRI BETWEEN ? AND ?";
	private static final String FIND_BY_AREA 
										= "SELECT D.DISH_NO, D.DISH_NAME, D.DISH_PRI, D.STR_NO, D.DISH_IMG, D.DISH_NOTE, S.STR_NAME,"
										+ "S.STR_COU, S.STR_CITY, S.STR_ADDR, S.STR_LONG, S.STR_LAT FROM DISH D JOIN"
										+ "(SELECT * FROM STORE WHERE STR_COU||STR_CITY||STR_ADDR LIKE ?) S ON D.STR_NO = S.STR_NO";
	private static final String GET_ALL = "SELECT * FROM DISH";
	private static final String FIND_DISHNAME_BY_STORE = "SELECT DISH_NAME FROM DISH WHERE STR_NO = ?";
	private static final String FIND_DISHIMG_BY_STORE = "SELECT DISH_IMG FROM DISH WHERE STR_NO = ?";
	private static final String FIND_BY_STATUS = "SELECT * FROM DISH WHERE DISH_STA = ? ORDER BY STR_NO DESC";
	private static final String GET_ONE_STR = "SELECT DISTINCT DCLA_NO FROM DISH WHERE STR_NO = ?";
	private static final String GET_ALL_BYCLASS_STR = "SELECT * FROM DISH WHERE DCLA_NO=? AND STR_NO=? ";
	private static final String GET_ALL_BYCLASS_STR_STATUS="SELECT * FROM DISH WHERE DCLA_NO=? AND STR_NO=? AND DISH_STA=? ";
	private static final String GET_STR_STATUS = "SELECT * FROM DISH WHERE STR_NO=? AND DISH_STA=? ";
	
	
	@Override
	public void insert(DishVO dishVO) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(INSERT);
			
			state.setString(1, dishVO.getDish_name());
			state.setDouble(2, dishVO.getDish_price());
			state.setString(3, dishVO.getDcla_no());
			state.setString(4, dishVO.getStr_no());
			state.setBytes(5, dishVO.getDish_img());
			state.setString(6, dishVO.getDish_note());
			
			state.executeUpdate();
		
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void update(DishVO dishVO) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(UPDATE);
			
			state.setString(1, dishVO.getDish_name());
			state.setDouble(2, dishVO.getDish_price());
			state.setString(3, dishVO.getDish_status());
			state.setBytes(4, dishVO.getDish_img());
			state.setString(5, dishVO.getDish_note());
			state.setString(6, dishVO.getDish_no());
			
			state.executeUpdate();
			

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());	
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


	@Override
	public DishVO findByDishNo(String dish_no) {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DishVO dishVO = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(FIND_BY_DISH_NO);
			state.setString(1, dish_no);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dishVO = new DishVO();
				dishVO.setDish_no(rs.getString(1));
				dishVO.setDish_name(rs.getString(2));
				dishVO.setDish_price(rs.getDouble(3));
				dishVO.setDcla_no(rs.getString(4));
				dishVO.setStr_no(rs.getString(5));
				dishVO.setDish_status(rs.getString(6));
				
				byte [] pic = rs.getBytes(7);
				if(pic != null) {
					dishVO.setDish_img(pic);
				} else {
					dishVO.setDish_img(ShareTool.sendPicture("images/btn-cross.png"));
				}
				
				String text = rs.getString(8);
				if(text != null) {
					dishVO.setDish_note(text);
				} else {
					dishVO.setDish_note("Wait......");
				}
					
			}
		
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} catch (IOException ie) {
			ie.printStackTrace(System.err);
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return dishVO;
		
	}


	@Override
	public List<DishVO> findByDishClass(String dcla_no) {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DishVO dishVO = null;
		List<DishVO> classList = new ArrayList<>();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(FIND_BY_DISH_CLASS);
			state.setString(1, dcla_no);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dishVO = new DishVO();
				dishVO.setDish_no(rs.getString("DISH_NO"));
				dishVO.setDish_name(rs.getString("DISH_NAME"));
				dishVO.setDish_price(rs.getDouble("DISH_PRI"));
				dishVO.setDcla_no(rs.getString("DCLA_NO"));
				dishVO.setStr_no(rs.getString("STR_NO"));
				
				byte [] pic = rs.getBytes("DISH_IMG");
				if(pic != null) {
					dishVO.setDish_img(pic);
				} else {
					dishVO.setDish_img(ShareTool.sendPicture("images/btn-cross.png"));
				}
				
				String text = rs.getString("DISH_NOTE");
				if(text != null) {
					dishVO.setDish_note(text);
				} else {
					dishVO.setDish_note("Wait......");
				}
				
				
				classList.add(dishVO);
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());	
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} catch (IOException ie) {
			ie.printStackTrace(System.err);
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return classList;
	}

	@Override
	public List<DishVO> findByStore(String str_no) {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DishVO dishVO = null;
		List<DishVO> storeList = new ArrayList<>();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(FIND_BY_STORE);
			state.setString(1, str_no);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dishVO = new DishVO();
				dishVO.setDish_no(rs.getString("DISH_NO"));
				dishVO.setDish_name(rs.getString("DISH_NAME"));
				dishVO.setDish_price(rs.getDouble("DISH_PRI"));
				dishVO.setDcla_no(rs.getString("DCLA_NO"));
				dishVO.setStr_no(rs.getString("STR_NO"));
				dishVO.setDish_status(rs.getString("DISH_STA"));
				dishVO.setDish_img(rs.getBytes("DISH_IMG"));
				dishVO.setDish_note(rs.getString("DISH_NOTE"));
				
				storeList.add(dishVO);
			}
		

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return storeList;
	}
	
	@Override
	public List<DishVO> findByPrice(Double minPrice, Double maxPrice) {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DishVO dishVO = null;
		List<DishVO> priceList = new ArrayList<>();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(FIND_BY_PRICE);
			state.setDouble(1, minPrice);
			state.setDouble(2, maxPrice);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dishVO = new DishVO();
				dishVO.setDish_no(rs.getString("DISH_NO"));
				dishVO.setDish_name(rs.getString("DISH_NAME"));
				dishVO.setDish_price(rs.getDouble("DISH_PRI"));
				dishVO.setDcla_no(rs.getString("DCLA_NO"));
				dishVO.setStr_no(rs.getString("STR_NO"));
				dishVO.setDish_status(rs.getString("DISH_STA"));
				dishVO.setDish_img(rs.getBytes("DISH_IMG"));
				dishVO.setDish_note(rs.getString("DISH_NOTE"));
				
				priceList.add(dishVO);
			}
		

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return priceList;
	}
	
	@Override
	public List<DishVO> findByArea(String area) {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DishVO dishVO = null;
		List<DishVO> areaList = new ArrayList<>();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(FIND_BY_AREA);
			state.setString(1, "%" + area + "%");
			rs = state.executeQuery();
			
			while(rs.next()) {
				dishVO = new DishVO();
				dishVO.setDish_no(rs.getString("DISH_NO"));
				dishVO.setDish_name(rs.getString("DISH_NAME"));
				dishVO.setDish_price(rs.getDouble("DISH_PRI"));
				dishVO.setStr_no(rs.getString("STR_NO"));
				dishVO.setDish_img(rs.getBytes("DISH_IMG"));
				dishVO.setDish_img(ShareTool.sendPicture("images/btn-cross.png"));
				dishVO.setDish_note(rs.getString("DISH_NOTE"));
				areaList.add(dishVO);
				
			}
		

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} catch (IOException ie) {
			ie.printStackTrace(System.err);
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return areaList;
	}

	@Override
	public List<DishVO> getAll() {
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DishVO dishVO = null;
		List<DishVO> allList = new ArrayList<>();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(GET_ALL);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dishVO = new DishVO();
				dishVO.setDish_no(rs.getString("DISH_NO"));
				dishVO.setDish_name(rs.getString("DISH_NAME"));
				dishVO.setDish_price(rs.getDouble("DISH_PRI"));
				dishVO.setDcla_no(rs.getString("DCLA_NO"));
				dishVO.setStr_no(rs.getString("STR_NO"));
				dishVO.setDish_img(rs.getBytes("DISH_IMG"));
				dishVO.setDish_note(rs.getString("DISH_NOTE"));
				allList.add(dishVO);
				
			}
		

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return allList;
	}

	@Override
	public List<String> findDishNameByStore(String str_no) {
			
			Connection con = null;
			PreparedStatement state = null;
			ResultSet rs = null;
			List<String> name = new ArrayList<String>();
			
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				state = con.prepareStatement(FIND_DISHNAME_BY_STORE);
				state.setString(1, str_no);
				rs = state.executeQuery();
				
				while(rs.next()) {
					name.add(rs.getString("DISH_NAME"));	
				}
			
			} catch (ClassNotFoundException ce) {
				throw new RuntimeException("driver loading error occured. " + ce.getMessage());
			} catch (SQLException se) {
				throw new RuntimeException("Database error occured. " + se.getMessage());
			} finally {
				try {
					if(con != null) {
						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return name;
			
		}

	@Override
	public List<DishVO> findDishImgByStore(String str_no) {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		List<DishVO> imgList = new LinkedList<DishVO>();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(FIND_DISHIMG_BY_STORE);
			state.setString(1, str_no);
			rs = state.executeQuery();
			
			while(rs.next()) {
				DishVO dishVO = new DishVO();
				dishVO.setDish_no(rs.getString("DISH_NO"));
				dishVO.setDish_img(rs.getBytes("DISH_IMG"));
			}
		
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return imgList;
	}

	@Override
	public void updateStatus(String dish_no, String dish_status) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(UPDATE_STATUS);
			
			state.setString(1, dish_status);
			state.setString(2, dish_no);
			state.executeUpdate();
			
		
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());	
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void updateImg(DishVO dishVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DishVO> findByStatus(String dish_status) {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DishVO dishVO = null;
		List<DishVO> dishList = new ArrayList<>();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(FIND_BY_STATUS);
			state.setString(1, dish_status);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dishVO = new DishVO();
				dishVO.setDish_no(rs.getString("DISH_NO"));
				dishVO.setDish_name(rs.getString("DISH_NAME"));
				dishVO.setDish_price(rs.getDouble("DISH_PRI"));
				dishVO.setDcla_no(rs.getString("DCLA_NO"));
				dishVO.setStr_no(rs.getString("STR_NO"));
				dishVO.setDish_status(rs.getString("DISH_STA"));
				dishVO.setDish_img(rs.getBytes("DISH_IMG"));
				dishVO.setDish_note(rs.getString("DISH_NOTE"));
				
				dishList.add(dishVO);
			}
		

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return dishList;
	}
	
	public List<DishVO> getDishClassForStr(String str_no) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<DishVO> getALL() {
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DishVO dishVO = null;
		List<DishVO> allList = new ArrayList<>();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(GET_ALL);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dishVO = new DishVO();
				dishVO.setDish_no(rs.getString("DISH_NO"));
				dishVO.setDish_name(rs.getString("DISH_NAME"));
				dishVO.setDish_price(rs.getDouble("DISH_PRI"));
				dishVO.setDcla_no(rs.getString("DCLA_NO"));
				dishVO.setStr_no(rs.getString("STR_NO"));
				dishVO.setDish_img(rs.getBytes("DISH_IMG"));
				dishVO.setDish_note(rs.getString("DISH_NOTE"));
				allList.add(dishVO);
				
			}
		

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return allList;
	}
	
	@Override
	public List<DishVO> findByClassStore(String dcla_no,String str_no) {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DishVO dishVO = null;
		List<DishVO> storeList = new ArrayList<>();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(GET_ALL_BYCLASS_STR);
			state.setString(1, dcla_no);
			state.setString(2, str_no);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dishVO = new DishVO();
				dishVO.setDish_no(rs.getString("DISH_NO"));
				dishVO.setDish_name(rs.getString("DISH_NAME"));
				dishVO.setDish_price(rs.getDouble("DISH_PRI"));
				dishVO.setDcla_no(rs.getString("DCLA_NO"));
				dishVO.setStr_no(rs.getString("STR_NO"));
				dishVO.setDish_status(rs.getString("DISH_STA"));
				dishVO.setDish_img(rs.getBytes("DISH_IMG"));
				dishVO.setDish_note(rs.getString("DISH_NOTE"));
				
				storeList.add(dishVO);
			}
		

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return storeList;
	}
	
	@Override
	public List<DishVO> findByClassStoreStatus(String dcla_no,String str_no,String dish_status){
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DishVO dishVO = null;
		List<DishVO> storeList = new ArrayList<>();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(GET_ALL_BYCLASS_STR_STATUS);
			state.setString(1, dcla_no);
			state.setString(2, str_no);
			state.setString(3, dish_status);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dishVO = new DishVO();
				dishVO.setDish_no(rs.getString("DISH_NO"));
				dishVO.setDish_name(rs.getString("DISH_NAME"));
				dishVO.setDish_price(rs.getDouble("DISH_PRI"));
				dishVO.setDcla_no(rs.getString("DCLA_NO"));
				dishVO.setStr_no(rs.getString("STR_NO"));
				dishVO.setDish_status(rs.getString("DISH_STA"));
				dishVO.setDish_img(rs.getBytes("DISH_IMG"));
				dishVO.setDish_note(rs.getString("DISH_NOTE"));
				
				storeList.add(dishVO);
			}
		

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return storeList;
	}
	
	@Override
	public List<DishVO> getByStrStatus(String str_no,String dish_status){
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		DishVO dishVO = null;
		List<DishVO> storeList = new ArrayList<>();
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			state = con.prepareStatement(GET_STR_STATUS);
			state.setString(1, str_no);
			state.setString(2, dish_status);
			rs = state.executeQuery();
			
			while(rs.next()) {
				dishVO = new DishVO();
				dishVO.setDish_no(rs.getString("DISH_NO"));
				dishVO.setDish_name(rs.getString("DISH_NAME"));
				dishVO.setDish_price(rs.getDouble("DISH_PRI"));
				dishVO.setDcla_no(rs.getString("DCLA_NO"));
				dishVO.setStr_no(rs.getString("STR_NO"));
				dishVO.setDish_status(rs.getString("DISH_STA"));
				dishVO.setDish_img(rs.getBytes("DISH_IMG"));
				dishVO.setDish_note(rs.getString("DISH_NOTE"));
				
				storeList.add(dishVO);
			}
		

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("driver loading error occured. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return storeList;
	}

	@Override
	public DishVO findByDishNo_Str_no(String dish_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DishVO findByDishNo_ForName(String dish_no) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	


}
