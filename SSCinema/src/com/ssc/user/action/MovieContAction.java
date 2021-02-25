package com.ssc.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.MovieInfoDAO;
import com.ssc.model.MovieInfoDTO;

public class MovieContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 영화소개페이지에서 영화 선택시 영화 상세페이지로 넘어가는 작업
		
		int m_num = Integer.parseInt(request.getParameter("no"));
		
		MovieInfoDAO dao = MovieInfoDAO.getInstance();
		MovieInfoDTO dto = dao.getMovieCont(m_num);
		
		request.setAttribute("content", dto);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_movie_cont.jsp");
		
		return forward;
	}

}
