package com.ssc.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.MovieInfoDAO;
import com.ssc.model.MovieInfoDTO;

public class AdminMovieEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int m_num = Integer.parseInt(request.getParameter("no"));
		
		MovieInfoDAO dao = MovieInfoDAO.getInstance();
		MovieInfoDTO dto = dao.getMovieCont(m_num);
		
		request.setAttribute("edit", dto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_movie_edit.jsp");
		
		return forward;
	}

}
