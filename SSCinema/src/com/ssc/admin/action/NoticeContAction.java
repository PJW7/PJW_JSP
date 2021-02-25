package com.ssc.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.NoticeDAO;
import com.ssc.model.NoticeDTO;
import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;



public class NoticeContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 글번호에 해당하는 상세내역을 조회하는 클래스
		int N_num = 
			Integer.parseInt(request.getParameter("num"));
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.noticeHit(N_num);  // 조회수 증가 메서드 호출
		
		NoticeDTO dto = dao.getCont(N_num);  // 상세내역 메서드 호출
		
		// 키로 저장해서 view page로 넘겨 주자.
		request.setAttribute("cont", dto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_notice_cont.jsp");
		
		return forward;
	}

}
