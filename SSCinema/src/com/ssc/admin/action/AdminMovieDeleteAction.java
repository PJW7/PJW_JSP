package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.MovieInfoDAO;

public class AdminMovieDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int m_num = Integer.parseInt(request.getParameter("no"));
		
		MovieInfoDAO dao = MovieInfoDAO.getInstance();
		int res = dao.deleteMovie(m_num);
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		if (res > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_movie_list.do");
		}else {
			out.println("<script>");
			out.println("alert('삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
