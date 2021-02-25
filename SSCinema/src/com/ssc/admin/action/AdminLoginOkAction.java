package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.AdminDAO;

public class AdminLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 관리자로그인작업
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		AdminDAO dao = AdminDAO.getInstance();
		int res = dao.adminCheck(id,pwd);
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		if(res > 0 ) {		
			forward.setRedirect(false);
			forward.setPath("admin/admin_main.jsp");
		}else if(res == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('로그인 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
