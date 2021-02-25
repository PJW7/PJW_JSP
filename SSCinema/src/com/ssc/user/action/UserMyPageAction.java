package com.ssc.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;

public class UserMyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		System.out.println(id);
		if(id == null) {
			out.println("<script>");
			out.println("alert('로그인후 이용 가능합니다.')");
			out.println("</script>");
			forward.setRedirect(true);
			forward.setPath("user_login.do");
		}else {
			forward.setRedirect(false);
			forward.setPath("user/user_my_page.jsp");
		}
		return forward;
	}

}
