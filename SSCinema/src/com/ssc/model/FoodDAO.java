package com.ssc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class FoodDAO {

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
	private static FoodDAO instance = null;
	
	// 3. 외부에서 객체생성을 못하게 접근을 제어. - private 생성자를 만듬.
	private FoodDAO() {  }
	
	// 4. 생성자 대신에 싱글턴 객체를 return을 해 주는 getInstance()
	//    메서드를 만들어서 접근하게 하는 방법
	public static FoodDAO getInstance() {
		if(instance == null) {
			instance = new FoodDAO();
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
	
	public List<FoodDTO> getFoodList(){
		List<FoodDTO> list = new ArrayList<FoodDTO>();
		try {
			openConn();
			sql = "select * from ssc_food order by f_num desc";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FoodDTO dto = new FoodDTO();
				dto.setF_num(rs.getInt("f_num"));
				dto.setF_name(rs.getString("f_name"));
				dto.setF_price(rs.getInt("f_price"));
				dto.setF_image(rs.getString("f_image"));
				
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}
	public FoodDTO getFoodCont(int num){
		FoodDTO dto = new FoodDTO();
		try {
			openConn();
			sql = "select * from ssc_food where f_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setF_num(rs.getInt("f_num"));
				dto.setF_name(rs.getString("f_name"));
				dto.setF_price(rs.getInt("f_price"));
				dto.setF_image(rs.getString("f_image"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}

	public int deleteFood(int f_num) {
		int result = 0,count = 0;
		try {
			openConn();
			sql = "select count(*) from ssc_food_buy where f_num_fk = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, f_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
				if (count > 0) {
					result = -1;
				}else {
					sql = "delete from ssc_food where f_num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, f_num);
					result = pstmt.executeUpdate();
				}
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}

	public int addFood(FoodDTO dto) {
		int result = 0;

		try {
			openConn();
			sql = "insert into ssc_food values(ssc_food_seq.nextval, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getF_name());
			pstmt.setInt(2, dto.getF_price());
			pstmt.setString(3, dto.getF_image());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}
}
