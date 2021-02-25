package com.ssc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;



public class MovieInfoDAO {

	Connection con = null;          // DB 연결 객체.
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;            // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	String sql = null;              // 쿼리문을 저장할 객체.

	// 싱글톤 방식으로 객체를 만들어 보자.
	// 1. 싱글톤 객체를 만들기 위해서는 우선적으로 접근지정자는 private
	//    으로 선언을 해야 함.
	// 2. 정적 멤버로 선언을 해야 함. - static으로 선언을 한다는 의미.
	private static MovieInfoDAO instance = null;
	
	// 3. 외부에서 객체생성을 못하게 접근을 제어. - private 생성자를 만듬.
	private MovieInfoDAO() {  }
	
	// 4. 생성자 대신에 싱글턴 객체를 return을 해 주는 getInstance()
	//    메서드를 만들어서 접근하게 하는 방법
	public static MovieInfoDAO getInstance() {
		if(instance == null) {
			instance = new MovieInfoDAO();
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

	// 영화 리스트를 가져오는 메서드
	public List<MovieInfoDTO> getMovieList() {
		List<MovieInfoDTO> list = new ArrayList<MovieInfoDTO>();

		try {
			openConn();
			sql = "select * from ssc_movie_info order by m_num desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MovieInfoDTO dto = new MovieInfoDTO();
				dto.setM_num(rs.getInt("m_num"));
				dto.setM_name(rs.getString("m_name"));
				dto.setM_cont(rs.getString("m_cont"));
				dto.setM_image(rs.getString("m_image"));
				dto.setM_director(rs.getString("m_director"));
				dto.setM_actor(rs.getString("m_actor"));
				dto.setM_genre(rs.getString("m_genre"));
				dto.setM_time(rs.getInt("m_time"));
				dto.setM_playdate(rs.getString("m_playdate"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}// getMovieList() end

	// 해당 번호의 영화 정보를 가져오는 메서드
	public MovieInfoDTO getMovieCont(int m_num) {
		MovieInfoDTO dto = new MovieInfoDTO();
		
		try {
			openConn();
			sql = "select * from ssc_movie_info where m_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setM_num(rs.getInt("m_num"));
				dto.setM_name(rs.getString("m_name"));
				dto.setM_cont(rs.getString("m_cont"));
				dto.setM_image(rs.getString("m_image"));
				dto.setM_director(rs.getString("m_director"));
				dto.setM_actor(rs.getString("m_actor"));
				dto.setM_genre(rs.getString("m_genre"));
				dto.setM_time(rs.getInt("m_time"));
				dto.setM_playdate(rs.getString("m_playdate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		
		return dto;
	} //getMovieCont() end
	
	// 현재 상영중인 영화 리스트를 가져오는 메서드
	public List<MovieInfoDTO> playingMovieList() {
		List<MovieInfoDTO> list = new ArrayList<MovieInfoDTO>();

		//select * from ssc_movie_info 
		//where m_num in(select distinct m_num_fk from ssc_playing_info);
		try {
			openConn();
			sql = "select * from ssc_movie_info "
					+ "where m_num in(select distinct m_num_fk from ssc_playing_info) "
					+ "order by m_num desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MovieInfoDTO dto = new MovieInfoDTO();
				dto.setM_num(rs.getInt("m_num"));
				dto.setM_name(rs.getString("m_name"));
				dto.setM_cont(rs.getString("m_cont"));
				dto.setM_image(rs.getString("m_image"));
				dto.setM_director(rs.getString("m_director"));
				dto.setM_actor(rs.getString("m_actor"));
				dto.setM_genre(rs.getString("m_genre"));
				dto.setM_time(rs.getInt("m_time"));
				dto.setM_playdate(rs.getString("m_playdate"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}// getMovieList() end

	public int deleteMovie(int m_num) {
		int result = 0;
		try {
			openConn();
			sql = "delete from ssc_movie_info where m_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}

	public int editMovie(MovieInfoDTO dto) {
		int result = 0;
		
		try {
			openConn();
			sql = "update ssc_movie_info set m_name = ?, m_cont = ?, m_image = ?, m_director = ?, "
					+ "m_actor = ?, m_genre = ?, m_time = ?, m_playdate = ? where m_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getM_name());
			pstmt.setString(2, dto.getM_cont());
			pstmt.setString(3, dto.getM_image());
			pstmt.setString(4, dto.getM_director());
			pstmt.setString(5, dto.getM_actor());
			pstmt.setString(6, dto.getM_genre());
			pstmt.setInt(7, dto.getM_time());
			pstmt.setString(8, dto.getM_playdate());
			pstmt.setInt(9, dto.getM_num());
			
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
