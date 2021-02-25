package com.ssc.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.MovieInfoDAO;
import com.ssc.model.MovieInfoDTO;
import com.ssc.model.MovieReserReceiptDTO;
import com.ssc.model.PlayingInfoDAO;
import com.ssc.model.PlayingInfoDTO;
import com.ssc.model.RoomDAO;
import com.ssc.model.RoomDTO;
import com.ssc.model.TheaterDAO;
import com.ssc.model.TheaterDTO;

public class SeatChoiceAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		int t_num = Integer.parseInt(request.getParameter("t_num"));
		int m_num = Integer.parseInt(request.getParameter("m_num"));
		String s_name = request.getParameter("s_name");
		
		// 상영관 정보 받아오기
		System.out.println(p_num);
		RoomDAO rdao = RoomDAO.getInstance();
		RoomDTO rdto = rdao.getPlayingRoom(p_num);
		
		// 상영 정보 받아오기
		PlayingInfoDAO pdao = PlayingInfoDAO.getInstance();
		PlayingInfoDTO pdto = pdao.getPlayingCont(p_num);
		
		// 영화정보 받아오기
		MovieInfoDAO mdao = MovieInfoDAO.getInstance();
		MovieInfoDTO mdto = mdao.getMovieCont(m_num);
		
		// 극장 정보 받아오기
		TheaterDAO tdao = TheaterDAO.getInstance();
		TheaterDTO tdto = tdao.getTheaterCont(t_num);
		
		MovieReserReceiptDTO info = new MovieReserReceiptDTO();
		info.setM_name(mdto.getM_name());
		info.setM_num(m_num);
		info.setT_name(tdto.getT_name());
		info.setR_name(rdto.getR_name());
		info.setS_name(s_name);
		info.setP_date(pdto.getP_date());
	/*	private int m_num;    m
	private String m_name;    m
	private String m_image;    m
	private int re_num; //예매 번호    
	private String p_date; //상영일자 정보    p
	private int u_num_fk; //사용자 정보         u
	private String r_name; //상영관 정보       r
	private int s_num;	//좌석 정보               s
	private String t_name; //극장 이름         t
	 * 
	 * */
		request.setAttribute("info", info);
		request.setAttribute("p_num", p_num);
		//request.setAttribute("t_num", t_num);
		//request.setAttribute("m_num", m_num);
		//request.setAttribute("s_num", s_num);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_seat_payment.jsp");
		
		return forward;
	}

}
