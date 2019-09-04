package com.str.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class StrDAO implements StrDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT = "INSERT INTO STORE(STR_NO, STR_NAME, STR_COU, STR_CITY, STR_ADDR, STR_TEL, STR_ATN,"
			+ "STR_PRE, STR_SHIP, STOCA_NO, STR_ACC, STR_PAS, STR_MA, STR_LONG, STR_LAT)"
			+ "VALUES ('STR_'||LPAD(STR_SQ.NEXTVAL, 4, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE STORE SET STR_NAME = ?, STR_COU = ?, STR_CITY = ?, STR_ADDR = ?, STR_TEL = ?," 
			+"STR_ATN = ?, STR_PRE = ?, STR_SHIP = ?, STR_MA = ?, STR_LONG = ?, STR_LAT = ?"
			+"WHERE STR_NO = ?";
	private static final String UPDATE_INFO = "UPDATE STORE SET STR_IMG = ?, STR_NOTE = ? WHERE STR_NO = ?";
	private static final String UPDATE_PAS = "UPDATE STORE SET STR_PAS = ? WHERE STR_NO = ?";
	private static final String UPDATE_STAT = "UPDATE STORE SET STR_STAT = ? WHERE STR_NO = ?";
	private static final String UPDATE_REP = "UPDATE STORE SET STR_REP = ? WHERE STR_NO = ?";
	private static final String GET_ONE = "SELECT * FROM STORE WHERE STR_NO = ?";
	private static final String GET_ALL = "SELECT * FROM STORE ORDER BY STR_NO";
	private static final String FIND_BY_AREA = "SELECT * FROM STORE WHERE STR_COU||STR_CITY||STR_ADDR LIKE ?";
	private static final String FIND_BY_STOCA_NO = "SELECT * FROM STORE WHERE STOCA_NO = ?";
	private static final String FIND_BY_RepStat="select * from store where str_rep=? and str_stat=?";
	private static String FIND_BY_Str_Stat="select * from store where str_stat=?";
	 
	@Override
	public void insert(StrVO strVO) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			con = ds.getConnection();
			state = con.prepareStatement(INSERT);
			
			state.setString(1, strVO.getStr_name());
			state.setString(2, strVO.getStr_cou());
			state.setString(3, strVO.getStr_city());
			state.setString(4, strVO.getStr_addr());
			state.setString(5, strVO.getStr_tel());
			state.setString(6, strVO.getStr_atn());
			state.setInt(7, strVO.getStr_pre());
			state.setString(8, strVO.getStr_ship());
			state.setString(9, strVO.getStoca_no());
			state.setString(10, strVO.getStr_acc());
			state.setString(11, strVO.getStr_pas());
			state.setString(12, strVO.getStr_ma());
			state.setDouble(13, strVO.getStr_long());
			state.setDouble(14, strVO.getStr_lat());
			
			state.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
	}

	@Override
	public void update(StrVO strVO) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			con = ds.getConnection();
			state = con.prepareStatement(UPDATE);
			
			state.setString(1, strVO.getStr_name());
			state.setString(2, strVO.getStr_cou());
			state.setString(3, strVO.getStr_city());
			state.setString(4, strVO.getStr_addr());
			state.setString(5, strVO.getStr_tel());
			state.setString(6, strVO.getStr_atn());
			state.setInt(7, strVO.getStr_pre());
			state.setString(8, strVO.getStr_ship());
			state.setString(9, strVO.getStr_ma());
			state.setDouble(10, strVO.getStr_long());
			state.setDouble(11, strVO.getStr_lat());
			state.setString(12, strVO.getStr_no());
			
			state.execute();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void updateInfo(StrVO strVO) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			con = ds.getConnection();
			state = con.prepareStatement(UPDATE_INFO);
			
			state.setBytes(1, strVO.getStr_img());
			state.setString(2, strVO.getStr_note());
			state.setString(3, strVO.getStr_no());
			
			state.execute();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
				
		}
	}
	
	@Override
	public void updatePas(String str_no, String str_pas) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			con = ds.getConnection();
			state = con.prepareStatement(UPDATE_PAS);
			state.setString(1, str_pas);
			state.setString(2, str_no);
			
			state.execute();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void updateStatus(String str_no, String str_stat) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			con = ds.getConnection();
			state = con.prepareStatement(UPDATE_STAT);
			state.setString(1, str_stat);
			state.setString(2, str_no);
			
			state.execute();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
	}
	
	@Override
	public void updateRep(Integer str_rep, String str_no) {
		
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			con = ds.getConnection();
			state = con.prepareStatement(UPDATE_REP);
			state.setInt(1, str_rep);
			state.setString(2, str_no);
			
			state.execute();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
	}

	@Override
	public StrVO findByPrimaryKey(String str_no) {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		StrVO strVO = null;
		
		try {
			con = ds.getConnection();
			state = con.prepareStatement(GET_ONE);
			state.setString(1, str_no);
			rs = state.executeQuery();

			while(rs.next()) {
				 strVO = new StrVO();
				 strVO.setStr_no(rs.getString(1));
				 strVO.setStr_name(rs.getString(2));
				 strVO.setStr_cou(rs.getString(3));
				 strVO.setStr_city(rs.getString(4));
				 strVO.setStr_addr(rs.getString(5));
				 strVO.setStr_tel(rs.getString(6));
				 strVO.setStr_atn(rs.getString(7));
				 strVO.setStr_pre(rs.getInt(8));
				 strVO.setStr_ship(rs.getString(9));
				 strVO.setStoca_no(rs.getString(10));
				 strVO.setStr_acc(rs.getString(11));
				 strVO.setStr_pas(rs.getString(12));
				 strVO.setStr_img(rs.getBytes(13));
				 strVO.setStr_stat(rs.getString(14));
				 strVO.setStr_ma(rs.getString(15));
				 strVO.setStr_rep(rs.getInt(16));
				 strVO.setStr_long(rs.getDouble(17));
				 strVO.setStr_lat(rs.getDouble(18));
				 strVO.setStr_note(rs.getString(19));
				 
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return strVO;
	}

	@Override
	public List<StrVO> getALl() {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		StrVO strVO = null;
		List<StrVO> strList = new ArrayList<>();
		
		try {
			con = ds.getConnection();
			state = con.prepareStatement(GET_ALL);
			rs = state.executeQuery();
			
			while(rs.next()) {
				 strVO = new StrVO();
				 strVO.setStr_no(rs.getString(1));
				 strVO.setStr_name(rs.getString(2));
				 strVO.setStr_cou(rs.getString(3));
				 strVO.setStr_city(rs.getString(4));
				 strVO.setStr_addr(rs.getString(5));
				 strVO.setStr_tel(rs.getString(6));
				 strVO.setStr_atn(rs.getString(7));
				 strVO.setStr_pre(rs.getInt(8));
				 strVO.setStr_ship(rs.getString(9));
				 strVO.setStoca_no(rs.getString(10));
				 strVO.setStr_acc(rs.getString(11));
				 strVO.setStr_pas(rs.getString(12));
//					byte[] pic = rs.getBytes(13);
//					if(pic != null) {
//						strVO.setStr_img(pic);
//					} else {
//						strVO.setStr_img(ShareTool.sendPicture("C:\\Jenny_WebApp\\neon3_eclipse_workspace\\BA103G3\\WebContent\\easyfood\\front-end\\class\\search\\images\\storeIMG\\noPicture\\nopic.png"));
//					}
				 strVO.setStr_img(Objects.isNull(rs.getBytes(13)) ? "0".getBytes() : rs.getBytes(13));
				 strVO.setStr_stat(rs.getString(14));
				 strVO.setStr_ma(rs.getString(15));
				 strVO.setStr_rep(Objects.isNull(rs.getInt(16))? (Integer)0 :rs.getInt(16));
				 strVO.setStr_long(Objects.isNull(rs.getDouble(17)) ? (Double)0.0 :rs.getDouble(17));
				 strVO.setStr_lat(Objects.isNull(rs.getDouble(18)) ? (Double)0.0 :rs.getDouble(18));
				 strVO.setStr_note(rs.getString(19)==null ? "店家介紹" :rs.getString(19));
				
				strList.add(strVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return strList;
	}
	

	@Override
	public List<StrVO> findByArea(String area) {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		StrVO strVO = null;
		List<StrVO> areaList = new ArrayList<>();
		
		try {
			con = ds.getConnection();
			state = con.prepareStatement(FIND_BY_AREA);
			state.setString(1, "%"+area+"%");
			rs = state.executeQuery();

			while(rs.next()) {
				 strVO = new StrVO();
				 strVO.setStr_no(rs.getString(1));
				 strVO.setStr_name(rs.getString(2));
				 strVO.setStr_cou(rs.getString(3));
				 strVO.setStr_city(rs.getString(4));
				 strVO.setStr_addr(rs.getString(5));
				 strVO.setStr_tel(rs.getString(6));
				 strVO.setStr_atn(rs.getString(7));
				 strVO.setStr_pre(rs.getInt(8));
				 strVO.setStr_ship(rs.getString(9));
				 strVO.setStoca_no(rs.getString(10));
				 strVO.setStr_acc(rs.getString(11));
				 strVO.setStr_pas(rs.getString(12));
//					byte[] pic = rs.getBytes(13);
//					if(pic != null) {
//						strVO.setStr_img(pic);
//					} else {
//						strVO.setStr_img(ShareTool.sendPicture("storeIMG/noPicture/nopic.png"));
//					}
				 strVO.setStr_img(Objects.isNull(rs.getBytes(13)) ? "0".getBytes() : rs.getBytes(13));
				 strVO.setStr_stat(rs.getString(14));
				 strVO.setStr_ma(rs.getString(15));
				 strVO.setStr_rep(Objects.isNull(rs.getInt(16))? (Integer)0 :rs.getInt(16));
				 strVO.setStr_long(Objects.isNull(rs.getDouble(17)) ? (Double)0.0 :rs.getDouble(17));
				 strVO.setStr_lat(Objects.isNull(rs.getDouble(18)) ? (Double)0.0 :rs.getDouble(18));
				 strVO.setStr_note(Objects.isNull(rs.getString(19)) ? "店家介紹" :rs.getString(19));
				 areaList.add(strVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<StrVO> findByStoca(String stoca_no) {
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		StrVO strVO = null;
		List<StrVO> stocaList = new ArrayList<>();
		try {
			con = ds.getConnection();
			state = con.prepareStatement(FIND_BY_STOCA_NO);
			state.setString(1, stoca_no);
			rs = state.executeQuery();

			while(rs.next()) {
				 strVO = new StrVO();
				 strVO.setStr_no(rs.getString(1));
				 strVO.setStr_name(rs.getString(2));
				 strVO.setStr_cou(rs.getString(3));
				 strVO.setStr_city(rs.getString(4));
				 strVO.setStr_addr(rs.getString(5));
				 strVO.setStr_tel(rs.getString(6));
				 strVO.setStr_atn(rs.getString(7));
				 strVO.setStr_pre(rs.getInt(8));
				 strVO.setStr_ship(rs.getString(9));
				 strVO.setStoca_no(rs.getString(10));
				 strVO.setStr_acc(rs.getString(11));
				 strVO.setStr_pas(rs.getString(12));
//					byte[] pic = rs.getBytes(13);
//					if(pic != null) {
//						strVO.setStr_img(pic);
//					} else {
//						strVO.setStr_img(ShareTool.sendPicture("C:\\Jenny_WebApp\\neon3_eclipse_workspace\\BA103G3\\WebContent\\easyfood\\front-end\\class\\search\\images\\storeIMG\\noPicture\\nopic.png"));
//					}
				 strVO.setStr_img(Objects.isNull(rs.getBytes(13)) ? "0".getBytes() : rs.getBytes(13));
				 strVO.setStr_stat(rs.getString(14));
				 strVO.setStr_ma(rs.getString(15));
				 strVO.setStr_rep(Objects.isNull(rs.getInt(16))? (Integer)0 :rs.getInt(16));
				 strVO.setStr_long(Objects.isNull(rs.getDouble(17)) ? (Double)0.0 :rs.getDouble(17));
				 strVO.setStr_lat(Objects.isNull(rs.getDouble(18)) ? (Double)0.0 :rs.getDouble(18));
				 strVO.setStr_note(rs.getString(19)==null ? "店家介紹" :rs.getString(19));
				 stocaList.add(strVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stocaList;
	}
	
	@Override
	public List<StrVO> findByMuti(Map<Integer, List<String>> map) {
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		StrVO strVO = null;
		List<StrVO> areaList = new ArrayList<>();
		
		String address="";
		String ship="";
		String storeType="";
		String keyWord="";
		
		try {
			con = ds.getConnection();
						
			int number=0;
			String and="";
			for(int i=0;i<map.size();i++){
				
				if(map.get(i).size()!=0){				
					if(i==0){
						address="STR_COU||STR_CITY||STR_ADDR LIKE ?";
						number=number+1;
					}
					if(i==1){
						if(number!=0){
							and="AND";
						}else{
							and="";	
						}
						ship= and +" STR_SHIP='"+map.get(1).get(0)+"' ";
						number=number+1;
					}
					if(i==2){
																
						if(number!=0){
							and="AND";
						}else{
							and="";	
						}
				
						String stoca="";
						for(int j=0;j<map.get(2).size();j++){
							stoca=stoca+" '"+map.get(2).get(j)+"', ";
						}
						stoca=stoca.substring(0, stoca.lastIndexOf(",") );
						storeType=and +" STOCA_NO IN ("+stoca+")";
						number=number+1;
					}
					if(i==3){
						if(number!=0){
							and="AND";
						}else{
							and="";	
						}
						keyWord=and +" STR_NAME LIKE ?";
					}
				}
			}
				
			String FIND_BY_MUTI="SELECT * FROM STORE WHERE "+address+" "+ship+" "+storeType+" "+keyWord+" and STR_STAT='營業中'";
			state = con.prepareStatement(FIND_BY_MUTI);
			
			System.out.println(FIND_BY_MUTI);
			
			number=0;
			for(int i=0;i<map.size();i++){
				
				if(map.get(i).size()!=0){				
					if(i==0){
						state.setString(1, "%" + map.get(0).get(0) + "%");
						number=number+1;
						System.out.println("address:"+map.get(0).get(0));
					}
					if(i==3){
						state.setString(1+number, "%" + map.get(3).get(0) + "%");
						System.out.println("keyword:"+map.get(3).get(0));
					}
				}
			}
			
			rs = state.executeQuery();

			while(rs.next()) {
				 strVO = new StrVO();
				 strVO.setStr_no(rs.getString(1));
				 strVO.setStr_name(rs.getString(2));
				 strVO.setStr_cou(rs.getString(3));
				 strVO.setStr_city(rs.getString(4));
				 strVO.setStr_addr(rs.getString(5));
				 strVO.setStr_tel(rs.getString(6));
				 strVO.setStr_atn(rs.getString(7));
				 strVO.setStr_pre(rs.getInt(8));
				 strVO.setStr_ship(rs.getString(9));
				 strVO.setStoca_no(rs.getString(10));
				 strVO.setStr_acc(rs.getString(11));
				 strVO.setStr_pas(rs.getString(12));
//					byte[] pic = rs.getBytes(13);
//					if(pic != null) {
//						strVO.setStr_img(pic);
//					} else {
//						strVO.setStr_img(ShareTool.sendPicture("C:\\Jenny_WebApp\\neon3_eclipse_workspace\\BA103G3\\WebContent\\easyfood\\front-end\\class\\search\\images\\storeIMG\\noPicture\\nopic.png"));
//					}
				 strVO.setStr_img(Objects.isNull(rs.getBytes(13)) ? "0".getBytes() : rs.getBytes(13));
				 strVO.setStr_stat(rs.getString(14));
				 strVO.setStr_ma(rs.getString(15));
				 strVO.setStr_rep(Objects.isNull(rs.getInt(16))? (Integer)0 :rs.getInt(16));
				 strVO.setStr_long(Objects.isNull(rs.getDouble(17)) ? (Double)0.0 :rs.getDouble(17));
				 strVO.setStr_lat(Objects.isNull(rs.getDouble(18)) ? (Double)0.0 :rs.getDouble(18));
				 strVO.setStr_note(rs.getString(19)==null ? "店家介紹" :rs.getString(19));
				 areaList.add(strVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<StrVO> findByRepStat(Integer str_rep,String str_stat){
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		StrVO strVO = null;
		List<StrVO> strList = new ArrayList<>();
		try {
			con = ds.getConnection();
			state = con.prepareStatement(FIND_BY_RepStat);
			state.setInt(1, str_rep);
			state.setString(2, str_stat);
			rs = state.executeQuery();

			while(rs.next()) {
				 strVO = new StrVO();
				 strVO.setStr_no(rs.getString(1));
				 strVO.setStr_name(rs.getString(2));
				 strVO.setStr_cou(rs.getString(3));
				 strVO.setStr_city(rs.getString(4));
				 strVO.setStr_addr(rs.getString(5));
				 strVO.setStr_tel(rs.getString(6));
				 strVO.setStr_atn(rs.getString(7));
				 strVO.setStr_pre(rs.getInt(8));
				 strVO.setStr_ship(rs.getString(9));
				 strVO.setStoca_no(rs.getString(10));
				 strVO.setStr_acc(rs.getString(11));
				 strVO.setStr_pas(rs.getString(12));
//					byte[] pic = rs.getBytes(13);
//					if(pic != null) {
//						strVO.setStr_img(pic);
//					} else {
//						strVO.setStr_img(ShareTool.sendPicture("C:\\Jenny_WebApp\\neon3_eclipse_workspace\\BA103G3\\WebContent\\easyfood\\front-end\\class\\search\\images\\storeIMG\\noPicture\\nopic.png"));
//					}
				 strVO.setStr_img(Objects.isNull(rs.getBytes(13)) ? "0".getBytes() : rs.getBytes(13));
				 strVO.setStr_stat(rs.getString(14));
				 strVO.setStr_ma(rs.getString(15));
				 strVO.setStr_rep(Objects.isNull(rs.getInt(16))? (Integer)0 :rs.getInt(16));
				 strVO.setStr_long(Objects.isNull(rs.getDouble(17)) ? (Double)0.0 :rs.getDouble(17));
				 strVO.setStr_lat(Objects.isNull(rs.getDouble(18)) ? (Double)0.0 :rs.getDouble(18));
				 strVO.setStr_note(rs.getString(19)==null ? "店家介紹" :rs.getString(19));
				
				strList.add(strVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return strList;
	}
	
	@Override
	public List<StrVO> findByStrSta(String str_stat){
		
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		StrVO strVO = null;
		List<StrVO> strList = new ArrayList<>();
		try {
			con = ds.getConnection();
			state = con.prepareStatement(FIND_BY_Str_Stat);

			state.setString(1, str_stat);
			rs = state.executeQuery();

			while(rs.next()) {
				 strVO = new StrVO();
				 strVO.setStr_no(rs.getString(1));
				 strVO.setStr_name(rs.getString(2));
				 strVO.setStr_cou(rs.getString(3));
				 strVO.setStr_city(rs.getString(4));
				 strVO.setStr_addr(rs.getString(5));
				 strVO.setStr_tel(rs.getString(6));
				 strVO.setStr_atn(rs.getString(7));
				 strVO.setStr_pre(rs.getInt(8));
				 strVO.setStr_ship(rs.getString(9));
				 strVO.setStoca_no(rs.getString(10));
				 strVO.setStr_acc(rs.getString(11));
				 strVO.setStr_pas(rs.getString(12));
//					byte[] pic = rs.getBytes(13);
//					if(pic != null) {
//						strVO.setStr_img(pic);
//					} else {
//						strVO.setStr_img(ShareTool.sendPicture("C:\\Jenny_WebApp\\neon3_eclipse_workspace\\BA103G3\\WebContent\\easyfood\\front-end\\class\\search\\images\\storeIMG\\noPicture\\nopic.png"));
//					}
				 strVO.setStr_img(Objects.isNull(rs.getBytes(13)) ? "0".getBytes() : rs.getBytes(13));
				 strVO.setStr_stat(rs.getString(14));
				 strVO.setStr_ma(rs.getString(15));
				 strVO.setStr_rep(Objects.isNull(rs.getInt(16))? (Integer)0 :rs.getInt(16));
				 strVO.setStr_long(Objects.isNull(rs.getDouble(17)) ? (Double)0.0 :rs.getDouble(17));
				 strVO.setStr_lat(Objects.isNull(rs.getDouble(18)) ? (Double)0.0 :rs.getDouble(18));
				 strVO.setStr_note(rs.getString(19)==null ? "店家介紹" :rs.getString(19));
				
				strList.add(strVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return strList;
	}
	

}

