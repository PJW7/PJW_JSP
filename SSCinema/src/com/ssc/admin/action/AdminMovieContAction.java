package com.ssc.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.MovieInfoDAO;
import com.ssc.model.MovieInfoDTO;

public class AdminMovieContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int m_num = Integer.parseInt(request.getParameter("no"));
		
		MovieInfoDAO dao = MovieInfoDAO.getInstance();
		MovieInfoDTO cont = dao.getMovieCont(m_num);
		
		request.setAttribute("movie", cont);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_movie_cont.jsp");
		
		return forward;
	}

}
