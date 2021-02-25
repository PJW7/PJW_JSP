package com.ssc.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.FaqDAO;
import com.ssc.model.FaqDTO;
import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;

public class FaqEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int q_no = Integer.parseInt(request.getParameter("no"));
		
		FaqDAO dao =  FaqDAO.getInstance();
		FaqDTO dto = dao.getCount(q_no);
		
		request.setAttribute("edit", dto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_faq_edit.jsp");
		return forward;
	}

}
