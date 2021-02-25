package com.ssc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class UserDAO {

	Connection con = null;          // DB 연결 객체.
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;            // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	String sql = null;              // 쿼리문을 저장할 객체.

	public static final int ADMIN_LOGIN_SUCCESS = 1;
	public static final int ADMIN_LOGIN_PWD_FAIL = -1;
	public static final int ADMIN_LOGIN_NONEXIST = -2;
	// 싱글톤 방식으로 객체를 만들어 보자.
	// 1. 싱글톤 객체를 만들기 위해서는 우선적으로 접근지정자는 private
	//    으로 선언을 해야 함.
	// 2. 정적 멤버로 선언을 해야 함. - static으로 선언을 한다는 의미.
	private static UserDAO instance = null;
	
	// 3. 외부에서 객체생성을 못하게 접근을 제어. - private 생성자를 만듬.
	private UserDAO() {  }
	
	// 4. 생성자 대신에 싱글턴 객체를 return을 해 주는 getInstance()
	//    메서드를 만들어서 접근하게 하는 방법
	public static UserDAO getInstance() {
		if(instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}
	
	// DB 연결 작업 진행하는 메서드. - DBCP 방식으로 연결 진행.
	public void openConn() {

		try {		
			// 1. JNDI 서버 객체 생성
			InitialContext ic = new InitialContext();
			
			// 2. lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/myoracle");
			
			// 3. DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // openConn() 메서드 end
	
	// 연결된 객체 종료하는 메서드
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // closeConn() 메서드 end
	

	public UserDTO getUserInfo(String id) {
		UserDTO dto = new UserDTO();
		try {
			openConn();
			sql = "select * from ssc_user where u_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setU_num(rs.getInt("u_num"));
				dto.setU_id(rs.getString("u_id"));
				dto.setU_name(rs.getString("u_name"));
				dto.setU_pwd(rs.getString("u_pwd"));
				dto.setU_mile(rs.getInt("u_mile"));
				dto.setU_phone(rs.getString("u_phone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}// getUserInfo(String id) END
	
	public UserDTO getUserInfo(int num) {
		UserDTO dto = new UserDTO();
		try {
			openConn();
			sql = "select * from ssc_user where u_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setU_num(rs.getInt("u_num"));
				dto.setU_id(rs.getString("u_id"));
				dto.setU_name(rs.getString("u_name"));
				dto.setU_pwd(rs.getString("u_pwd"));
				dto.setU_mile(rs.getInt("u_mile"));
				dto.setU_phone(rs.getString("u_phone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}// getUserInfo(int num) END
	
	public boolean pwdCheck(int num, String pwd) {
		boolean check = false;
		try {
			openConn();
			sql = "select * from ssc_user where u_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if(rs.next() && pwd.equals(rs.getString("u_pwd"))) {
				check = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return check;
	}
	
	public int updateInfo(UserDTO dto) {
		int result = 0;
		try {
			openConn();
			sql = "update ssc_user set u_id = ?, u_phone = ? where u_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getU_id());
			pstmt.setString(2, dto.getU_phone());
			pstmt.setInt(3, dto.getU_num());
			System.out.println("정보바꾸기 비번제외");
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	public int updateInfo_pwd(UserDTO dto) {
		int result = 0;
		try {
			openConn();
			sql = "update ssc_user set u_id = ?, u_phone = ? , u_pwd = ? where u_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getU_id());
			pstmt.setString(2, dto.getU_phone());
			pstmt.setString(3, dto.getU_pwd());
			pstmt.setInt(4, dto.getU_num());
			System.out.println("정보바꾸기 비번포함");
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	public boolean idOverCheck(String id) {
		boolean check = false;
		try {
			openConn();
			sql = "select u_id from ssc_user where u_id = ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				check = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return check;
	}
	
	public int insertUser(UserDTO dto) {
		int result = 0;
		try {
			openConn();
			sql = "insert into ssc_user values(ssc_user_seq.nextval,?,?,?,default,?,1)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getU_id());
			pstmt.setString(2, dto.getU_name());
			pstmt.setString(3, dto.getU_pwd());
			pstmt.setString(4, dto.getU_phone());
			
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	public int loginCheck(String id, String pwd) {
		int result = 0;
		try {
			openConn();
			sql = "select * from ssc_user where u_id = ? and u_status = 1";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {//아이디있음
				if(pwd.equals(rs.getString("u_pwd"))) {//비번 맞음
					result = 1;
				}else {//비번 틀림
					result = -1;
				}
			}else {//아이디없음
				sql = "select * from ssc_user where u_id = ? and u_status = 0";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				if(!rs.next()) {//아이디없음
					result = -2;
				}else { //탈퇴한 아이디
					result = -3;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	public List<FoodBuyDTO> getUserReceipt(String id){
		List<FoodBuyDTO> list = new ArrayList<FoodBuyDTO>();
		try {
			openConn();
			sql = "select * from ssc_food_buy where u_num_fk = ? order by b_num desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FoodBuyDTO dto = new FoodBuyDTO();
				dto.setB_num(rs.getInt("b_num"));
				dto.setF_num_fk(rs.getInt("f_num_fk"));
				dto.setU_num_fk(rs.getInt("u_num_fk"));
				dto.setB_count(rs.getInt("b_count"));
				dto.setB_price(rs.getInt("b_price"));
				
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}
	
	public List<ReserveDTO> getUserReserve(int num){
		List<ReserveDTO> list = new ArrayList<ReserveDTO>();
		try {
			openConn();
			sql = "select * from ssc_reserv where u_num_fk = ? order by re_num desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReserveDTO dto = new ReserveDTO();
				dto.setRe_num(rs.getInt("re_num"));
				dto.setP_num_fk(rs.getInt("p_num_fk"));
				dto.setM_num(rs.getInt("m_num"));
				dto.setU_num_fk(rs.getInt("u_num_fk"));
				dto.setR_num_fk(rs.getInt("r_num_fk"));
				dto.setS_num(rs.getInt("s_num"));
				dto.setT_num_fk(rs.getInt("t_num_fk"));
				
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}
	
	public int getListCount() {
		
		int count = 0;
		
		try {
			openConn();
			sql = "select count(*) from ssc_user";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return count;
	}
public List<UserDTO> getAd_UsList(int page, int rowsize) {
		
		List<UserDTO> list = new ArrayList<UserDTO>();
		
		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);
		
		// 해당 페이지의 끝 번호
		int endNo = (page * rowsize);
		
		try {
			openConn();
			sql = "select * from "
				+ " (select b.*, row_number() "
				+ " over(order by b.u_num desc) rnum "
				+ " from ssc_user b) "
				+ " where rnum >= ? and rnum <= ? and u_status=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setU_num(rs.getInt("u_num"));
				dto.setU_id(rs.getString("u_id"));
				dto.setU_name(rs.getString("u_name"));
				dto.setU_pwd(rs.getString("u_pwd"));
				dto.setU_mile(rs.getInt("u_mile"));
				dto.setU_phone(rs.getString("u_phone"));
				
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}  // getFaqList() 메서드 end


	public int deletAd_Us(int u_num) {
		int result = 0;
	
		try {
			openConn();
			sql = "update ssc_user set u_status=0"
				+ " where u_num = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, u_num);
		
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}

	public void addMovieMile(int u_num) {

		try {
			openConn();
			sql = "update ssc_user set u_mile = u_mile + 500 where u_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, u_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
	}

	public void addFoodMile(int u_num, int qty, int price) {
		int total = qty*price;
		int point = (int) (total*0.05);
		try {
			openConn();
			sql = "update ssc_user set u_mile = u_mile + ? where u_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setInt(2, u_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
	}

	public void subFoodMile(int b_num) {

		try {
			openConn();
			sql = "select * from ssc_food_buy where b_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int price = (int) (rs.getInt("b_price")*0.05);
				int u_num = rs.getInt("u_num_fk");
				sql = "update ssc_user set u_mile = u_mile - ? where u_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, price);
				pstmt.setInt(2, u_num);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		
	}

	public void subMovieMile(int re_num) {
		try {
			openConn();
			sql = "select * from ssc_reserv where re_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int u_num = rs.getInt("u_num_fk");
				sql = "update ssc_user set u_mile = u_mile - 500 where u_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, u_num);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
	}
	
	
}
