package com.ssc.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.UserDAO;
import com.ssc.model.UserDTO;

public class UserLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		request.setAttribute("id", id);
		
		UserDAO dao = UserDAO.getInstance();
		int res = dao.loginCheck(id, pwd);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res == 1) {
			HttpSession session = request.getSession();
			UserDTO dto = dao.getUserInfo(id);
			session.setAttribute("id", dto.getU_id());
			session.setAttribute("name", dto.getU_name());
			
			forward.setRedirect(false);
			forward.setPath("user/user_main.jsp");
		}else if(res == -1) {
			out.println("<script>");
			out.println("alert('비밀번호를 다시 확인해주십시오.')");
			out.println("history.back()");
			out.println("</script>");
		}else if(res == -2) {
			out.println("<script>");
			out.println("alert('아이디를 다시 확인해주십시오.')");
			out.println("history.back()");
			out.println("</script>");
		}else if(res == -3) {
			out.println("<script>");
			out.println("alert('회원 탈퇴한 계정입니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
