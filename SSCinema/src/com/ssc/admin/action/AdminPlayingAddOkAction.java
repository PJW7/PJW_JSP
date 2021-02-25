package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.AdminDAO;

public class AdminPlayingAddOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int m_num = Integer.parseInt(request.getParameter("m_num"));
		int t_num = Integer.parseInt(request.getParameter("t_num"));
		int r_num = Integer.parseInt(request.getParameter("r_num"));
		String p_date = request.getParameter("date_y") + "-" + request.getParameter("date_m") + "-" + request.getParameter("date_d") + " " + request.getParameter("date_h") + ":" + request.getParameter("date_min");
		
		AdminDAO dao = AdminDAO.getInstance();
		int check = dao.addPlaying(m_num, t_num, r_num, p_date);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			forward.setRedirect(false);
			forward.setPath("admin/admin_main.jsp");
		} else {
			out.println("<script>");
			out.println("alert('상영영화 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
