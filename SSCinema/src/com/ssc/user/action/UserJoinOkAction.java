package com.ssc.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.UserDAO;
import com.ssc.model.UserDTO;

public class UserJoinOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name").trim();
		String id = request.getParameter("id").trim();
		String phone = request.getParameter("tel1")+"-"+
				request.getParameter("tel2").trim()+"-"+
				request.getParameter("tel3").trim();
		String pwd = request.getParameter("pwd").trim();
		String pwdC = request.getParameter("pwdConfirm").trim();
		
		UserDAO dao = UserDAO.getInstance();
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		//id 중복 다시 체크
		if(dao.idOverCheck(id)) {//중복x
			if(pwd.equals(pwdC)) { //비밀번호 확인
				UserDTO dto = new UserDTO();
				dto.setU_id(id);
				dto.setU_pwd(pwd);
				dto.setU_name(name);
				dto.setU_phone(phone);
				
				int res = dao.insertUser(dto);
				if(res>0) {
					forward.setRedirect(true);
					forward.setPath("ssc_user_main.do?id="+id);
				}
			}else {//비밀번호 확인 실패
				out.println("<script>");
				out.println("alert('비밀번호를 다시 확인해주십시오.')");
				out.println("history.back()");
				out.println("</script>");
			}
		}else {//중복됨
			out.println("<script>");
			out.println("alert('아이디를 다시 확인해주십시오.')");
			out.println("history.back()");
			out.println("</script>");
		}

		return forward;
	}

}
