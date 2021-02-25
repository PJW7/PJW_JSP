package com.ssc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Ad_MoDAO {
	Connection con = null;          // DB 연결 객체.
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;            // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	String sql = null;              // 쿼리문을 저장할 객체.
	
	
	
	
	// 싱글톤 방식으로 객체를 만들어 보자.
	// 1. 싱글톤 객체를 만들기 위해서는 우선적으로 접근지정자는 private
	//    으로 선언을 해야 함.
	// 2. 정적 멤버로 선언을 해야 함. - static으로 선언을 한다는 의미.
	private static Ad_MoDAO instance = null;
	
	// 3. 외부에서 객체생성을 못하게 접근을 제어. - private 생성자를 만듬.
	private Ad_MoDAO() {  }
	
	// 4. 생성자 대신에 싱글턴 객체를 return을 해 주는 getInstance()
	//    메서드를 만들어서 접근하게 하는 방법
	public static Ad_MoDAO getInstance() {
		if(instance == null) {
			instance = new Ad_MoDAO();
		}
		return instance;
	}
	
	
	// DB 연결 작업 진행하는 메서드. - DBCP 방식으로 연결 진행.
	public void openConn() {
		
		try {
			// 1. JNDI 서버 객체 생성
			InitialContext ic = new InitialContext();
			
			// 2. lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds =
					(DataSource)ic.lookup("java:comp/env/jdbc/myoracle");
			
			// 3. DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}  // openConn() 메서드 end
	
	
	// 연결된 객체 종료하는 메서드
	public void closeConn(ResultSet rs,
			PreparedStatement pstmt, Connection con) {
		
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}  // closeConn() 메서드 end
	
	public int getListCount() {
		
		int count = 0;
		
		try {
			openConn();
			sql = "select count(*) from ssc_reserv";
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
public List<Ad_MoDTO> getAd_MoList(int page, int rowsize) {
		
		List<Ad_MoDTO> list = new ArrayList<Ad_MoDTO>();
		
		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);
		
		// 해당 페이지의 끝 번호
		int endNo = (page * rowsize);
		
		try {
			openConn();
			sql = "select * "
					+ " from (select re.* ,row_number() over(order by re_num desc) rnum from ssc_reserv re) re" + 
					" left join ssc_room r on r.r_num = re.r_num_fk " + 
					" left join ssc_user u on u.u_num = re.u_num_fk " + 
					" left join ssc_theater t on t.t_num = re.t_num_fk " + 
					" left join ssc_movie_info i on i.m_num = re.m_num "
					+ " where rnum >= ? and rnum <= ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Ad_MoDTO dto = new Ad_MoDTO();
				dto.setRe_movie_name(rs.getString("m_name"));;
				dto.setRe_num(rs.getInt("re_num"));
				dto.setRe_theater_name(rs.getString("t_name"));
				dto.setRe_room_name(rs.getString("r_name"));
				dto.setRe_user_name(rs.getString("u_name"));
				dto.setRe_seat_name(rs.getString("s_num"));
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
		sql = "select * from ssc_user "
				+ " where u_num = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, u_num);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {	
				sql = "delete from user "
						+ " where u_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, u_num);
				result = pstmt.executeUpdate();
			}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		closeConn(rs, pstmt, con);
	}
	return result;
} 

}


