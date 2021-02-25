package com.ssc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;





public class NoticeDAO {
	
		
	Connection con = null;          // DB 연결 객체.
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;            // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	String sql = null;              // 쿼리문을 저장할 객체.
	
	// 싱글톤 방식으로 객체를 만들어 보자.
	// 1. 싱글톤 객체를 만들기 위해서는 우선적으로 접근지정자는 private
	//    으로 선언을 해야 함.
	// 2. 정적 멤버로 선언을 해야 함. - static으로 선언을 한다는 의미.
	private static NoticeDAO instance = null;
	
	// 3. 외부에서 객체생성을 못하게 접근을 제어. - private 생성자를 만듬.
	private NoticeDAO() {  }
	
	// 4. 생성자 대신에 싱글턴 객체를 return을 해 주는 getInstance()
	//    메서드를 만들어서 접근하게 하는 방법
	public static NoticeDAO getInstance() {
		if(instance == null) {
			instance = new NoticeDAO();
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
	
	
	// N 테이블의 전체 게시물의 수를 조회하는 메서드
	public int getListCount() {
		int count = 0;
		
		try {
			openConn();
			sql = "select count(*) from ssc_notice";
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
	}  // getListCount() 메서드 end

	
	
	public List<NoticeDTO> getNoticeList(int page, int rowsize) {
		List<NoticeDTO> list = new ArrayList<NoticeDTO>();
		
		
		int startNo = (page * rowsize) - (rowsize - 1);
		
		
		int endNo = (page * rowsize);
		
		try {
			openConn();
			sql = "select * from "
					+ " (select b.*, row_number() "
					+ " over(order by b.n_num desc) rnum "
					+ " from ssc_notice b) "
					+ " where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startNo);
				pstmt.setInt(2, endNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setN_num(rs.getInt("n_num"));
				dto.setN_title(rs.getString("n_title"));
				dto.setN_cont(rs.getString("n_cont"));
				dto.setN_hit(rs.getInt("n_hit"));
				dto.setN_date(rs.getString("n_date"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}  
	
	
	
	public int insertnotice(NoticeDTO dto) {
		int result = 0;
		
		try {
			openConn();
			sql = "insert into ssc_notice "
				+ " values(ssc_notice_seq.nextval,?,?, "
				+ " default,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getN_title());
			pstmt.setString(2, dto.getN_cont());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}  
	
	public void noticeHit(int n_num) {
		
		try {
			openConn();
			sql = "update ssc_notice set "
					+ " n_hit = n_hit + 1 "
					+ " where n_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, n_num);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
	}  
	
	public NoticeDTO getCont(int n_num) {
		NoticeDTO dto = new NoticeDTO();
		
		try {
			openConn();
			sql = "select * from ssc_notice "
					+ " where n_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, n_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setN_num(rs.getInt("n_num"));
				dto.setN_title(rs.getString("n_title"));
				dto.setN_cont(rs.getString("n_cont"));
				dto.setN_hit(rs.getInt("n_hit"));
				dto.setN_date(rs.getString("n_date"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}  // getCont() 메서드 end
	
	
	
	public int updateNotice(NoticeDTO dto) {
		int result = 0;
	try {
		openConn();
		sql = "update ssc_notice set n_title = ?, "
				+ " n_cont = ? where n_num = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dto.getN_title());
		pstmt.setString(2, dto.getN_cont());
		pstmt.setInt(3, dto.getN_num());
		
		result = pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	finally {
		closeConn(rs, pstmt, con);
	}
	return result;
		
			
	
	}
	
	public int deleteNotice(int n_num) {
		int result = 0;

		try {
			openConn();
			sql = "select * from ssc_notice where n_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, n_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
					sql = "delete from ssc_notice where n_num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, n_num);
					result = pstmt.executeUpdate();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	} 
	
	
	
	
	
}

