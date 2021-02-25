package com.ssc.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.FaqDAO;
import com.ssc.model.FaqDTO;

public class FaqReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int faq_no = 
				Integer.parseInt(request.getParameter("no"));
			
			FaqDAO dao = FaqDAO.getInstance();
			FaqDTO dto = dao.getCont(faq_no);
			
			// dto 데이터를 키로 저장하여 답변글 폼 페이지로 이동시키자.
			request.setAttribute("reply", dto);
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("admin/admin_faq_reply.jsp");
			
			return forward;
	}

}
