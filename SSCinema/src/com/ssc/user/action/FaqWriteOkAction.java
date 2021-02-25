package com.ssc.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.FaqDAO;
import com.ssc.model.FaqDTO;

public class FaqWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 작성한 데이터들을 db에 저장하는 작업
		String q_writer = request.getParameter("writer").trim();
		String q_title = request.getParameter("title").trim();
		String q_content = request.getParameter("content").trim();
		String q_pwd = request.getParameter("pwd").trim();
		
		FaqDTO dto = new FaqDTO();
		dto.setQ_writer(q_writer);
		dto.setQ_title(q_title);
		dto.setQ_cont(q_content);
		dto.setQ_pwd(q_pwd);
		
		FaqDAO dao = FaqDAO.getInstance();
		int res = dao.insertFaq(dto);
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("user_faq_list.do");
		}else {
			out.println("<script>");
			out.println("alert('게시물 추가 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
