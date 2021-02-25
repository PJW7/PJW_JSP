package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.AdminDAO;

public class AdminDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String name = request.getParameter("name");
		int check = 0;
		
		AdminDAO dao = AdminDAO.getInstance();

		if (name.equals("t")) check = dao.deleteData(num, name);
		else if (name.equals("r")) check = dao.deleteData(num, name);
		else if (name.equals("p")) check = dao.deleteData(num, name);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			forward.setRedirect(true);
			if (name.equals("t")) forward.setPath("admin_theater_list.do");
			else if (name.equals("r")) forward.setPath("admin_room_list.do");
			else if (name.equals("p")) forward.setPath("admin_playing_list.do");
		}else if(check == -1){
			out.println("<script>");
			if (name.equals("t")) out.println("alert('해당 극장의 상영관이 존재합니다.')");
			else if (name.equals("r")) out.println("alert('해당 상영관의 상영일정이 존재합니다.')");
			else if (name.equals("p")) out.println("alert('해당 일정의 예매 내역이 존재합니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}
}
