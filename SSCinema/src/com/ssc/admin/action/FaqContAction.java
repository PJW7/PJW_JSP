package com.ssc.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.FaqDAO;
import com.ssc.model.FaqDTO;
import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;

public class FaqContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("q_no>>>>>>");
		int q_no = Integer.parseInt(request.getParameter("no"));
		System.out.println("q_no>>>>>>"+q_no);
		FaqDAO dao = FaqDAO.getInstance();
	
		dao.faqHit(q_no);
		FaqDTO dto = dao.getCount(q_no);
		
		request.setAttribute("cont", dto);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_faq_cont.jsp");
		
		return forward;
				
	}

}
