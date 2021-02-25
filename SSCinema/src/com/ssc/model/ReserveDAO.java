package com.ssc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ReserveDAO {

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
	private static ReserveDAO instance = null;
	
	// 3. 외부에서 객체생성을 못하게 접근을 제어. - private 생성자를 만듬.
	private ReserveDAO() {  }
	
	// 4. 생성자 대신에 싱글턴 객체를 return을 해 주는 getInstance()
	//    메서드를 만들어서 접근하게 하는 방법
	public static ReserveDAO getInstance() {
		if(instance == null) {
			instance = new ReserveDAO();
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

	// 상영번호에 해당하는 예약리스트를 가져오는 메서드
	public List<ReserveDTO> reservedSeatList(int p_num) {
		List<ReserveDTO> list = new ArrayList<ReserveDTO>();
		
		try {
			openConn();
			sql = "select * from ssc_reserv where p_num_fk = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p_num);
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
				dto.setRow_num(rs.getString("row_num"));
				dto.setS_name(rs.getString("s_name"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	} // reservedSeatList() end

	public int insertReservation(ReserveDTO redto) {
		int result = 0;

		try {
			openConn();
			sql = "insert into ssc_reserv values(ssc_reserv_seq.nextval,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, redto.getP_num_fk());
			pstmt.setInt(2, redto.getM_num());
			pstmt.setInt(3, redto.getU_num_fk());
			pstmt.setInt(4, redto.getR_num_fk());
			pstmt.setInt(5, redto.getS_num());
			pstmt.setInt(6, redto.getT_num_fk());
			pstmt.setString(7, redto.getS_name());
			pstmt.setString(8, redto.getRow_num());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
