package com.ssc.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.UserDAO;

public class UserInfoAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//DB에서 유저 정보 조회후 페이지이동
		UserDAO dao = UserDAO.getInstance();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		request.setAttribute("userInfo", dao.getUserInfo(id));
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_info.jsp");
		
		return forward;
	}

}
