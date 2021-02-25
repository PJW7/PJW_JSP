package com.ssc.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.NoticeDAO;
import com.ssc.model.NoticeDTO;
import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;



public class NoticeEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글번호에 해당하는 상세내역을 수정폼 페이지로 전달하는 클래스.
		int n_num = 
				Integer.parseInt(request.getParameter("num"));
			
			NoticeDAO dao = NoticeDAO.getInstance();
			NoticeDTO dto = dao.getCont(n_num);
			
			// 해당 dto 객체를 view page로 이동.
			request.setAttribute("edit", dto);
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("admin/admin_notice_edit.jsp");
			
			return forward;
		}

}
