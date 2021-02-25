package com.ssc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;



public class FaqDAO {

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
	private static FaqDAO instance = null;
	
	// 3. 외부에서 객체생성을 못하게 접근을 제어. - private 생성자를 만듬.
	private FaqDAO() {  }
	
	// 4. 생성자 대신에 싱글턴 객체를 return을 해 주는 getInstance()
	//    메서드를 만들어서 접근하게 하는 방법
	public static FaqDAO getInstance() {
		if(instance == null) {
			instance = new FaqDAO();
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

	// faq 리스트를 가져오는 메서드
	public List<FaqDTO> getFaqList(int page, int rowsize) {
		List<FaqDTO> list = new ArrayList<FaqDTO>();
		
		// 해당 페이지에서 시작 번호
		int startNo = (page*rowsize)-(rowsize - 1);
				
		// 해당 페이지에서 끝 번호
		int endNo = (page*rowsize);
		
		try {
			openConn();
												// 같은 그룹끼리(원글,답변) / 원글-답변1-답변2-...순서로
			//sql = "select * from jsp_bbs order by board_group desc, board_step asc";
			sql = "select * from"
					+ "(select f.*, row_number() over(order by q_group desc, q_step asc)"
					+ " rnum from ssc_faq f)"
					+ "where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FaqDTO dto = new FaqDTO();
				dto.setQ_no(rs.getInt("q_no"));
				dto.setQ_writer(rs.getString("q_writer"));
				dto.setQ_title(rs.getString("q_title"));
				dto.setQ_cont(rs.getString("q_cont"));
				dto.setQ_pwd(rs.getString("q_pwd"));
				dto.setQ_hit(rs.getInt("q_hit"));
				dto.setQ_date(rs.getString("q_date"));
				dto.setQ_group(rs.getInt("q_group"));
				dto.setQ_step(rs.getInt("q_step"));
				dto.setQ_indent(rs.getInt("q_indent"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}// getFaqList() end
	
	// 테이블의 전체 게시물의 수를 조회하는 메서드
	public int getListCount() {
		int count = 0;

		try {		
			openConn();
			sql = "select count(*) from ssc_faq";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return count;
	} // getListCount() 메서드 end
	
	
	

	// 테이블에 게시글을 추가하는 메서드
	public int insertFaq(FaqDTO dto) {
		int result = 0;
		
		try {
			openConn();
			sql = "insert into ssc_faq values(ssc_faq_seq.nextval,?,?,?,?,default,sysdate,ssc_faq_seq.currval,0,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getQ_writer());
			pstmt.setString(2, dto.getQ_title());
			pstmt.setString(3, dto.getQ_cont());
			pstmt.setString(4, dto.getQ_pwd());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	} // insertFaq() end

	// 테이블의 게시물의 조회수를 증가시키는 메서드
	public void faqHit(int q_no) {

		try {
			openConn();
			sql = "update ssc_faq set q_hit = q_hit + 1 where q_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, q_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // faqHit() end
	
	// jsp_bbs 테이블의 게시물 번호에 해당하는 상세내역을 조회하는 메서드
	public FaqDTO getCont(int q_no) {
		FaqDTO dto = new FaqDTO();
		
		try {
			openConn();
			sql = "select * from ssc_faq where q_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, q_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setQ_no(rs.getInt("q_no"));
				dto.setQ_writer(rs.getString("q_writer"));
				dto.setQ_title(rs.getString("q_title"));
				dto.setQ_cont(rs.getString("q_cont"));
				dto.setQ_pwd(rs.getString("q_pwd"));
				dto.setQ_hit(rs.getInt("q_hit"));
				dto.setQ_date(rs.getString("q_date"));
				dto.setQ_group(rs.getInt("q_group"));
				dto.setQ_step(rs.getInt("q_step"));
				dto.setQ_indent(rs.getInt("q_indent"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		
		return dto;
	} // getCont() end

	public int updateFaq(FaqDTO dto) {
		int result = 0;

		try {
			openConn();
			sql = "select * from ssc_faq where q_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getQ_no());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				if(dto.getQ_pwd().equals(rs.getString("q_pwd"))) {
					sql = "update ssc_faq set q_title = ?, q_cont = ? where q_no = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getQ_title());
					pstmt.setString(2, dto.getQ_cont());
					pstmt.setInt(3, dto.getQ_no());
					result = pstmt.executeUpdate();

				}else {
					result = -1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	} // updateFaq() end

	// 테이블에서 게시글을 삭제하는 메서드
	public int deleteFaq(int q_no, String q_pwd) {
		int result = 0;

		try {
			openConn();
			sql = "select * from ssc_faq where q_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, q_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("q_pwd").equals(q_pwd)) {
					sql = "delete from ssc_faq where q_no = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, q_no);
					result = pstmt.executeUpdate();
				}else { // 비밀번호가 틀린 경우
					result = -1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	} // deleteFaq() end
	
	// 테이블 게시판 글에 step을 하나 증가시키는 메서드
	public void replyUpdate(int q_group, int q_step) {
		
		try {
			openConn();
			// 같은 그룹의 답변들의 step을 하나씩 증가시키는데 원글은 포함하지 않게 한다.
			sql = "update ssc_faq set q_step = q_step + 1 where q_group = ? and q_step > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, q_group);
			pstmt.setInt(2, q_step);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	} //replyUpdate() end

	// 테이블의 게시글 원글에 답변글을 추가하는 메서드
	public int replyFaq(FaqDTO dto) {
		int result = 0;

		try {		
			openConn();
			// group으로 정렬한다. 게시글을 따로 추가하는거지만 그룹은 원글의 그룹과 동일하게 해서 함께 뜨도록 함.
			// indent : 답변글에 대한 들여쓰기. step : 원글이 위에뜨고 답변글은 아래에 뜨게끔.
			sql = "insert into ssc_faq values(ssc_faq_seq.nextval,?,?,?,?,default,sysdate,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getQ_writer());
			pstmt.setString(2, dto.getQ_title());
			pstmt.setString(3, dto.getQ_cont());
			pstmt.setString(4, dto.getQ_pwd());
			pstmt.setInt(5, dto.getQ_group());
			pstmt.setInt(6, dto.getQ_step() + 1); // 정렬시에 답변글이 원글보다 아래에 뜨게 하기 위함
			pstmt.setInt(7, dto.getQ_indent() + 1); // 답변이 들여쓰기 되게 하기 위함.
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // replyFaq() end
	public FaqDTO getCount(int q_no) {
		FaqDTO dto = new FaqDTO();
		
		try {
			openConn();
			sql = "select * from ssc_faq " + " where q_no = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, q_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setQ_no(rs.getInt("q_no"));
				dto.setQ_writer(rs.getString("q_writer"));
				dto.setQ_title(rs.getString("q_title"));
				dto.setQ_cont(rs.getString("q_cont"));
				dto.setQ_pwd(rs.getString("q_pwd"));
				dto.setQ_hit(rs.getInt("q_hit"));
				dto.setQ_date(rs.getString("q_date"));
				dto.setQ_group(rs.getInt("q_group"));
				dto.setQ_step(rs.getInt("q_step"));
				dto.setQ_indent(rs.getInt("q_indent"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		System.out.println(dto);
		return dto;
	}
}
