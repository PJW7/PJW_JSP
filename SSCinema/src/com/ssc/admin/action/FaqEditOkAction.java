package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.FaqDAO;
import com.ssc.model.FaqDTO;
import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;

public class FaqEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String q_writer = request.getParameter("writer").trim();
		String q_title = request.getParameter("title").trim();
		String q_content = request.getParameter("content").trim();
		String q_pwd = request.getParameter("pwd").trim();
		
		int q_no = Integer.parseInt(request.getParameter("q_no"));
		
		FaqDTO dto = new FaqDTO();
		dto.setQ_no(q_no);
		dto.setQ_writer(q_writer);
		dto.setQ_title(q_title);
		dto.setQ_cont(q_content);
		dto.setQ_pwd(q_pwd);
		
		FaqDAO dao = FaqDAO.getInstance();
		int res = dao.updateFaq(dto);
		
		PrintWriter out = response.getWriter();
		
		ActionForward forward = new ActionForward();
		
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_faq_cont.do?no="+q_no);			
		}else if(res == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다. 확인 요망')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('게시물 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
		
		
		
	}

}
