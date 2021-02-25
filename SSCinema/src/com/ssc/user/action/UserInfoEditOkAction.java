package com.ssc.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.UserDAO;
import com.ssc.model.UserDTO;

public class UserInfoEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//수정된 정보 DB에 업데이트
		int num = Integer.parseInt(request.getParameter("num"));
		String id = request.getParameter("id").trim();
		String tel = request.getParameter("tel1")+"-"+
				request.getParameter("tel2").trim()+"-"+
				request.getParameter("tel3").trim();
		String pwd = request.getParameter("pwd").trim();
		String newPwd = "";
		if(request.getParameter("pwdNew").trim() != null) {
			newPwd = request.getParameter("pwdNew").trim();
		}
		
		UserDAO dao = UserDAO.getInstance();
		UserDTO dto = new UserDTO();
		dto.setU_id(id);
		dto.setU_phone(tel);
		dto.setU_pwd(newPwd);
		dto.setU_num(num);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		int res = 0;
		
		if(dao.pwdCheck(num, pwd) && newPwd.equals("")) {
			res = dao.updateInfo(dto); //비밀번호 변경 안하는 경우
		}else if(dao.pwdCheck(num, pwd) && !newPwd.equals("")) {
			res = dao.updateInfo_pwd(dto); //비밀번호 변경하는 경우
		}else if(!dao.pwdCheck(num, newPwd)) {
			res = -1;
		}
		
		if(res>0) {
			forward.setRedirect(true);
			forward.setPath("user_info.do");
		}else if(res == -1){
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('회원정보 수정에 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
