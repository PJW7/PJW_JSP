package com.ssc.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.FaqDAO;
import com.ssc.model.FaqDTO;

public class FaqEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 넘어온 번호에 해당하는 글의 내용을 받아오는 작업
		int q_no = Integer.parseInt(request.getParameter("no"));
		
		FaqDAO dao = FaqDAO.getInstance();
		FaqDTO dto = dao.getCont(q_no);
		
		request.setAttribute("edit", dto);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_faq_edit.jsp");
		return forward;
	}

}
