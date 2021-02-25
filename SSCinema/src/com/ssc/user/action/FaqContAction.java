package com.ssc.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.FaqDAO;
import com.ssc.model.FaqDTO;

public class FaqContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 넘어온 번호에 해당하는 게시글의 상세 내역을 받아오는 작업
		int q_no = Integer.parseInt(request.getParameter("no"));
		
		FaqDAO dao = FaqDAO.getInstance();
		dao.faqHit(q_no);	// 조회수 증가 메서드 호출
		FaqDTO dto = dao.getCont(q_no); // 상세내역 메서드 호출
		
		// 키로 저장해서 view page로 넘겨주자
		request.setAttribute("cont", dto); 
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_faq_cont.jsp");
		
		return forward;
	}

}
