package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.AdminDAO;

public class AdminRoomAddOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int t_num = Integer.parseInt(request.getParameter("t_num"));
		String r_name = request.getParameter("r_name").trim();
		
		AdminDAO dao = AdminDAO.getInstance();
		int check = dao.addRoom(t_num, r_name);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			forward.setRedirect(false);
			forward.setPath("admin/admin_main.jsp");
		} else {
			out.println("<script>");
			out.println("alert('상영관 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
