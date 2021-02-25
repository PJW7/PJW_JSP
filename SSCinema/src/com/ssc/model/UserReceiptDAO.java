package com.ssc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserReceiptDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	private static UserReceiptDAO instance = null;

	private UserReceiptDAO() {  }

	public static UserReceiptDAO getInstance() {
		if(instance == null) {
			instance = new UserReceiptDAO();
		}
		return instance;
	}
	public void openConn() {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds =(DataSource)ic.lookup("java:comp/env/jdbc/myoracle");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  // openConn() 메서드 end

	public void closeConn(ResultSet rs,
			PreparedStatement pstmt, Connection con) {

		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}  // closeConn() 메서드 end

	public List<FoodBuyReceiptDTO> getFoodBuyReceipt(int uNum, int s, int e){
	      List<FoodBuyReceiptDTO> list = new ArrayList<FoodBuyReceiptDTO>();
	      try {
	         openConn();
	         sql = "select * " +
	              "from (select fb.* ,f.* ,row_number() over(order by fb.b_num desc) rnum from ssc_food_buy fb join ssc_food f on f.f_num = fb.f_num_fk where u_num_fk = ?)"+
	              "where rnum >= ? and rnum <= ?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, uNum);
	         pstmt.setInt(2, s);
	         pstmt.setInt(3, e);
	         
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
	            FoodBuyReceiptDTO dto = new FoodBuyReceiptDTO();

	            dto.setF_num(rs.getInt("f_num"));
	            dto.setF_name(rs.getString("f_name"));
	            dto.setF_price(rs.getInt("f_price"));
	            dto.setF_image(rs.getString("f_image"));
	            dto.setB_num(rs.getInt("b_num"));
	            dto.setB_count(rs.getInt("b_count"));
	            dto.setB_price(rs.getInt("b_price"));

	            list.add(dto);
	         }
	      }catch (Exception e1) {
	         e1.printStackTrace();
	      }finally {
	         closeConn(rs, pstmt, con);
	      }
	      return list;
	   }
	
	public List<FoodBuyDTO> getFoodBuyReceiptPage(int uNum,int s, int e){
		List<FoodBuyDTO> list = new ArrayList<FoodBuyDTO>();
		try {
			openConn();
			sql = "select * " + 
					"from (select fb.*, row_number() over(order by fb.b_num desc) rnum from ssc_food_buy fb where u_num_fk = ?) " + 
					"where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, uNum);
			pstmt.setInt(2, s);
			pstmt.setInt(3, e);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FoodBuyDTO dto = new FoodBuyDTO();
				
				list.add(dto);
			}
		}catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}

	public List<MovieReserReceiptDTO> getMovieReserReceipt(int uNum,int page, int rowsize){
		List<MovieReserReceiptDTO> list = new ArrayList<MovieReserReceiptDTO>();
		
		int startNo = (page*rowsize)-(rowsize-1);
		int endNo = (page*rowsize);
		List<ReserveDTO> reList = getMovieReserReceiptPage(uNum ,startNo, endNo);
		try {
			openConn();
			for(int i=0; i<reList.size(); i++) {
				MovieReserReceiptDTO dto = new MovieReserReceiptDTO();
				dto.setRe_num(reList.get(i).getRe_num());
				dto.setU_num_fk(reList.get(i).getU_num_fk());
				//상영 일자 가져오는 쿼리문
				sql =  "select re.re_num,re.s_name, p.p_date, m.m_num, m.m_name, m.m_image, r.r_name, re.s_num, t.t_name " + 
						"from ssc_reserv re "+
						"left outer join ssc_playing_info p on re.p_num_fk = ? and p.m_num_fk = re.m_num and p.t_num_fk = re.t_num_fk and p.r_num_fk = re.r_num_fk " + 
						"left outer join ssc_movie_info m on re.m_num = ? and p.m_num_fk = m.m_num " + 
						"left outer join ssc_room r on re.r_num_fk = ? and r.t_num_fk = ? and r.r_num = re.r_num_fk " + 
						"left outer join ssc_theater t on re.t_num_fk = ? and t.t_num = re.t_num_fk " + 
						"where re.u_num_fk = ? and re.re_num = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, reList.get(i).getP_num_fk());
				pstmt.setInt(2, reList.get(i).getM_num());
				pstmt.setInt(3, reList.get(i).getR_num_fk());
				pstmt.setInt(4, reList.get(i).getT_num_fk());
				pstmt.setInt(5, reList.get(i).getT_num_fk());
				pstmt.setInt(6, reList.get(i).getU_num_fk());
				pstmt.setInt(7, reList.get(i).getRe_num());
				rs = pstmt.executeQuery();
				while(rs.next()){ 
					dto.setP_date(rs.getString("p_date"));
					dto.setM_num(rs.getInt("m_num"));
					dto.setM_name(rs.getString("m_name"));
					dto.setM_image(rs.getString("m_image"));
					dto.setR_name(rs.getString("r_name"));
					dto.setS_name(rs.getString("s_name"));
					dto.setT_name(rs.getString("t_name"));
					
				}
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}
	
	public List<ReserveDTO> getMovieReserReceiptPage(int uNum,int s, int e){
		List<ReserveDTO> list = new ArrayList<ReserveDTO>();
		try {
			openConn();
			sql = "select * " + 
					"from (select re.*, row_number() over(order by re_num desc) rnum from ssc_reserv re where u_num_fk = ?) " + 
					"where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, uNum);
			pstmt.setInt(2, s);
			pstmt.setInt(3, e);
			
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
				dto.setS_name(rs.getString("s_name"));
				dto.setRow_num(rs.getString("row_num"));
				
				list.add(dto);
			}
		}catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}
	
	public int reserveCancel(int reNum) {
		int result = 0;
		try {
			openConn();
			sql = "delete from ssc_reserv where re_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reNum);
			
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	public int foodBuyCancel(int bNum) {
		int result = 0;
		try {
			openConn();
			sql = "delete from ssc_food_buy where b_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	public int getReserveCount(int uNum) {
		int count = 0;
		try {
			openConn();
			sql = "select count(*) from ssc_reserv where u_num_fk = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, uNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return count;
	}
	
	public int getFoodBuyCount(int uNum) {
		int count = 0;
		try {
			openConn();
			sql = "select count(*) from ssc_food_buy where u_num_fk = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, uNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return count;
	}
	
}
