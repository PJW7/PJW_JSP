package com.ssc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class AdminDAO {

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
	private static AdminDAO instance = null;
	
	// 3. 외부에서 객체생성을 못하게 접근을 제어. - private 생성자를 만듬.
	private AdminDAO() {  }
	
	// 4. 생성자 대신에 싱글턴 객체를 return을 해 주는 getInstance()
	//    메서드를 만들어서 접근하게 하는 방법
	public static AdminDAO getInstance() {
		if(instance == null) {
			instance = new AdminDAO();
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
	
	public int addTheater(String name) {
		
		int result = 0;
		
		try {
			openConn();
			sql = "insert into ssc_theater values(ssc_theater_seq.nextval, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}
	
	public List<TheaterDTO> getTheater() {
		
		List<TheaterDTO> list = new ArrayList<TheaterDTO>();
		
		try {
			openConn();
			sql = "select * from ssc_theater order by t_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TheaterDTO dto = new TheaterDTO();
				dto.setT_num(rs.getInt("t_num"));
				dto.setT_name(rs.getString("t_name"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}
	
	public List<RoomDTO> getRoom() {
		
		List<RoomDTO> list = new ArrayList<RoomDTO>();
		
		try {
			openConn();
			sql = "select * from ssc_room order by r_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RoomDTO dto = new RoomDTO();
				dto.setR_num(rs.getInt("r_num"));
				dto.setT_num_fk(rs.getInt("t_num_fk"));
				dto.setR_name(rs.getString("r_name"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}
	
	public List<MovieInfoDTO> getMovie() {
		
		List<MovieInfoDTO> list = new ArrayList<MovieInfoDTO>();
		
		try {
			openConn();
			sql = "select * from ssc_movie_info order by m_num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}
	
	public int addRoom(int t_num, String r_name) {
		
		int result = 0;
		
		try {
			openConn();
			sql = "insert into ssc_room values(ssc_room_seq.nextval, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, t_num);
			pstmt.setString(2, r_name);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}

	public int addMovie(MovieInfoDTO dto) {
		int result = 0;
		
		try {
			openConn();
			sql = "insert into ssc_movie_info values(ssc_movie_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getM_name());
			pstmt.setString(2, dto.getM_cont());
			pstmt.setString(3, dto.getM_image());
			pstmt.setString(4, dto.getM_director());
			pstmt.setString(5, dto.getM_actor());
			pstmt.setString(6, dto.getM_genre());
			pstmt.setInt(7, dto.getM_time());
			pstmt.setString(8, dto.getM_playdate());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}
	
	public int addSeat(int rNum, int qty, char rows) {
		int result = 0, count = 0;
		try {
			openConn();
/*			for(int i=0; i<qty; i++) {
				sql = "select max(s_num) from ssc_seat";
				pstmt = con.prepareCall(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) count = rs.getInt(1) + 1;
				
				sql = "insert into ssc_seat values(?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rNum);
				pstmt.setInt(2, count);
				result = pstmt.executeUpdate();
			}*/
			for(char i='A';i<=rows;i++) {
				String row = i+"";
				sql = "select max(s_num) from ssc_seat where row_num = ? and r_num_fk = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, row);
				pstmt.setInt(2, rNum);
				rs = pstmt.executeQuery();
				if(rs.next()) count = rs.getInt(1) + 1;
				for(int j=count;j<=qty;j++) {
					sql = "insert into ssc_seat values(?,?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, rNum);
					pstmt.setInt(2, j);
					pstmt.setString(3, row);
					pstmt.setString(4, row+j);
					result = pstmt.executeUpdate();
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	public String getSub(int t_num) {
		
		String result = "";
		
		try {
			openConn();
			sql = "select * from ssc_room where t_num_fk = ? order by r_num";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, t_num);
			rs = pstmt.executeQuery();
			
			result += "<options>";
			
			while (rs.next()) {
				result += "<option>";
				result += "<r_num>" + rs.getInt("r_num") + "</r_num>";
				result += "<r_name>" + rs.getString("r_name") + "</r_name>";
				result += "</option>";
			}
			
			result += "</options>";
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}
	
	public String getImage(int m_num) {
		
		String result = "";
		
		try {
			openConn();
			sql = "select * from ssc_movie_info where m_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();
			
			result += "<m_info>";
			
			while (rs.next()) {
				result += "<m_image>" + rs.getString("m_image") + "</m_image>";
			}

			result += "</m_info>";
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}
	
	public int addPlaying(int m_num, int t_num, int r_num, String p_date) {
		
		int result = 0;
		
		try {
			openConn();
			sql = "insert into ssc_playing_info values(ssc_playing_seq.nextval, ?, ?, ?, to_date(?, 'YYYY/MM/DD HH24:MI:SS'))";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			pstmt.setInt(2, t_num);
			pstmt.setInt(3, r_num);
			pstmt.setString(4, p_date);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}
	
	public int getListCount(String table_name) {
		
		int count = 0;
		
		try {
			openConn();
			sql = "select count(*) from " + table_name;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return count;
	}
	
	public List<TheaterDTO> getTheaterList(int page, int rowsize) {
		
		List<TheaterDTO> list = new ArrayList<TheaterDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = page * rowsize;
		
		try {
			openConn();
			sql = "select * from (select b.*, row_number() over(order by t_num desc) rnum from ssc_theater b) where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TheaterDTO dto = new TheaterDTO();
				dto.setT_num(rs.getInt("t_num"));
				dto.setT_name(rs.getString("t_name"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}
	
	public List<RoomDTO> getRoomList(int page, int rowsize) {
		
		List<RoomDTO> list = new ArrayList<RoomDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = page * rowsize;
		
		try {
			openConn();
			sql = "select * from (select b.*, row_number() over(order by r_num desc) rnum from ssc_room b) where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RoomDTO dto = new RoomDTO();
				dto.setR_num(rs.getInt("r_num"));
				dto.setT_num_fk(rs.getInt("t_num_fk"));
				dto.setR_name(rs.getString("r_name"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}
	
	public List<MovieInfoDTO> getMovieList(int page, int rowsize) {
		
		List<MovieInfoDTO> list = new ArrayList<MovieInfoDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = page * rowsize;
		
		try {
			openConn();
			sql = "select * from (select b.*, row_number() over(order by m_num desc) rnum from ssc_movie_info b) where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}
	
	public List<PlayingInfoDTO> getPlayingList(int page, int rowsize) {
		
		List<PlayingInfoDTO> list = new ArrayList<PlayingInfoDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = page * rowsize;
		
		try {
			openConn();
			sql = "select * from (select b.*, row_number() over(order by p_num desc) rnum from ssc_playing_info b) where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PlayingInfoDTO dto = new PlayingInfoDTO();
				dto.setP_num(rs.getInt("p_num"));
				dto.setM_num_fk(rs.getInt("m_num_fk"));
				dto.setT_num_fk(rs.getInt("t_num_fk"));
				dto.setR_num_fk(rs.getInt("r_num_fk"));
				dto.setP_date(rs.getString("p_date"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}
	
	public int getSearchListCount(String field, String name) {
		
		int count = 0;
		
		try {
			openConn();
			if (field.equals("t_num")) {
				sql = "select count(*) from ssc_theater where t_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("t_name")) {
				sql = "select count(*) from ssc_theater where t_name like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
			} else if (field.equals("r_num")) {
				sql = "select count(*) from ssc_room where r_num like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("r_t_num")) {
				sql = "select count(*) from ssc_room where t_num_fk like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("r_name")) {
				sql = "select count(*) from ssc_room where r_name like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
			} else if (field.equals("m_num")) {
				sql = "select count(*) from ssc_movie_info where m_num like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("m_name")) {
				sql = "select count(*) from ssc_movie_info where m_name like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
			} else if (field.equals("m_director")) {
				sql = "select count(*) from ssc_movie_info where m_director like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
			} else if (field.equals("p_num")) {
				sql = "select count(*) from ssc_playing_info where p_num like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("p_m_num")) {
				sql = "select count(*) from ssc_playing_info where m_num_fk like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("p_t_num")) {
				sql = "select count(*) from ssc_playing_info where t_num_fk like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("p_r_num")) {
				sql = "select count(*) from ssc_playing_info where r_num_fk like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return count;
	}
	
	public List<TheaterDTO> getSearchTheaterList(String field, String name, int page, int rowsize) {
		
		List<TheaterDTO> theaterList = new ArrayList<TheaterDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = page * rowsize;
		
		try {
			openConn();
			if (field.equals("t_num")) {
				sql = "select * from (select b.*, row_number() over(order by t_num desc) rnum from ssc_theater b where t_num = ?) where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("t_name")) {
				sql = "select * from (select b.*, row_number() over(order by t_num desc) rnum from ssc_theater b where t_name like ?) where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
			}
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo); 
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TheaterDTO dto = new TheaterDTO();
				dto.setT_num(rs.getInt("t_num"));
				dto.setT_name(rs.getString("t_name"));
				
				theaterList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return theaterList;
	}
	
	public List<RoomDTO> getSearchRoomList(String field, String name, int page, int rowsize) {
		
		List<RoomDTO> roomList = new ArrayList<RoomDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = page * rowsize;
		
		try {
			openConn();
			if (field.equals("r_num")) {
				sql = "select * from (select b.*, row_number() over(order by r_num desc) rnum from ssc_room b where r_num = ?) where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("r_t_num")) {
				sql = "select * from (select b.*, row_number() over(order by r_num desc) rnum from ssc_room b where t_num_fk = ?) where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("r_name")) {
				sql = "select * from (select b.*, row_number() over(order by r_num desc) rnum from ssc_room b where r_name like ?) where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
			}
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo); 
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RoomDTO dto = new RoomDTO();
				dto.setR_num(rs.getInt("r_num"));
				dto.setT_num_fk(rs.getInt("t_num_fk"));
				dto.setR_name(rs.getString("r_name"));
				
				roomList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return roomList;
	}
	
	public List<MovieInfoDTO> getSearchMovieList(String field, String name, int page, int rowsize) {
		
		List<MovieInfoDTO> movieList = new ArrayList<MovieInfoDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = page * rowsize;
		
		try {
			openConn();
			if (field.equals("m_num")) {
				sql = "select * from (select b.*, row_number() over(order by m_num desc) rnum from ssc_movie_info b where m_num = ?) where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("m_name")) {
				sql = "select * from (select b.*, row_number() over(order by m_num desc) rnum from ssc_movie_info b where m_name like ?) where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
			} else if (field.equals("m_director")) {
				sql = "select * from (select b.*, row_number() over(order by m_num desc) rnum from ssc_movie_info b where m_director like ?) where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
			}
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo); 
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
				
				movieList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return movieList;
	}
	
	public List<PlayingInfoDTO> getSearchPlayingList(String field, String name, int page, int rowsize) {
		
		List<PlayingInfoDTO> playingList = new ArrayList<PlayingInfoDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = page * rowsize;
		
		try {
			openConn();
			if (field.equals("p_num")) {
				sql = "select * from (select b.*, row_number() over(order by p_num desc) rnum from ssc_playing_info b where p_num = ?) where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("p_m_num")) {
				sql = "select * from (select b.*, row_number() over(order by p_num desc) rnum from ssc_playing_info b where m_num_fk = ?) where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("p_t_num")) {
				sql = "select * from (select b.*, row_number() over(order by p_num desc) rnum from ssc_playing_info b where t_num_fk = ?) where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else if (field.equals("p_r_num")) {
				sql = "select * from (select b.*, row_number() over(order by p_num desc) rnum from ssc_playing_info b where r_num_fk = ?) where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
			} 
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo); 
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PlayingInfoDTO dto = new PlayingInfoDTO();
				dto.setP_num(rs.getInt("p_num"));
				dto.setM_num_fk(rs.getInt("m_num_fk"));
				dto.setT_num_fk(rs.getInt("t_num_fk"));
				dto.setR_num_fk(rs.getInt("r_num_fk"));
				dto.setP_date(rs.getString("p_date"));
				
				playingList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return playingList;
	}

	public int adminCheck(String id, String pwd) {
		int result = 0;

		try {
			openConn();
			sql = "select * from ssc_admin where a_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(pwd.equals(rs.getString("a_pwd"))) {
					result = 1;
				}else {
					result = -1;
				}
			}else {
				result = -2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// open 객체 닫는 메서드 호출
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}
	public int deleteData(int num, String name) {
		
		int result = 0,count = 0;
		
		try {
			openConn();
			if (name.equals("t")) sql = "select count(*) from ssc_room where t_num_fk = ?";
			else if (name.equals("r")) sql = "select count(*) from ssc_playing_info where r_num_fk = ?";
			else if (name.equals("p")) sql = "select count(*) from ssc_reserv where p_num_fk = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			if(count > 0) {
				result = -1;
			}else {
				if (name.equals("t")) sql = "delete from ssc_theater where t_num = ?";
				else if (name.equals("r")) sql = "delete from ssc_seat where r_num_fk = ?";
				else if (name.equals("p")) sql = "delete from ssc_playing_info where p_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				result = pstmt.executeUpdate();
				if(name.equals("r")) {
					sql = "delete from ssc_room where r_num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					result = pstmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
		
	}

	public void deleteSeat(int num) {
		
		try {
			openConn();
			sql = "delete from ssc_seat where r_num_fk = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
	}
}
