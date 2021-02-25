package com.ssc.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;

public class FaqDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 넘어온 번호에 해당하는 글 삭제하는 페이지에 넘겨주는 작업
		int q_no = Integer.parseInt(request.getParameter("no"));
		
		request.setAttribute("no", q_no);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_faq_delete.jsp");
		return forward;
	}

}
