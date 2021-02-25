package com.ssc.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;

public class FaqDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int q_no = 
				Integer.parseInt(request.getParameter("no"));
			
			request.setAttribute("no", q_no);
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("admin/admin_faq_delete.jsp");
			
			return forward;
	}

}
