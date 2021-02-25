package com.ssc.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.MovieInfoDAO;
import com.ssc.model.MovieInfoDTO;
import com.ssc.model.PlayingInfoDAO;
import com.ssc.model.PlayingInfoDTO;
import com.ssc.model.TheaterDAO;
import com.ssc.model.TheaterDTO;

public class ReservationAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 현재 상영중인 영화 정보를 표시하는 작업
		
		MovieInfoDAO mdao = MovieInfoDAO.getInstance();
		List<MovieInfoDTO> mlist = mdao.playingMovieList();
		
		/*TheaterDAO tdao = TheaterDAO.getInstance();
		List<TheaterDTO> tlist = tdao.getTheaterList();
		
		PlayingInfoDAO pdao = PlayingInfoDAO.getInstance();
		List<PlayingInfoDTO> plist = pdao.getPlayingList();*/
		
		request.setAttribute("movielist", mlist);
		/*request.setAttribute("theaterlist", tlist);
		request.setAttribute("playinglist", plist);*/
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_reservation.jsp");
		return forward;
	}

}
