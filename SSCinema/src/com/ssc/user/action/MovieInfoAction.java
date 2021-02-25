package com.ssc.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.MovieInfoDAO;
import com.ssc.model.MovieInfoDTO;

public class MovieInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 영화 정보 DB에 있는 데이터들을 보여주는 작업
		MovieInfoDAO dao = MovieInfoDAO.getInstance();
		List<MovieInfoDTO> list = dao.getMovieList();
		
		request.setAttribute("movielist", list);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_movie_info.jsp");
		
		return forward;
	}

}
