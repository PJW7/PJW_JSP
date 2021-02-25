package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.FaqDAO;
import com.ssc.model.FaqDTO;

public class FaqReplyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String reply_writer = request.getParameter("reply_writer").trim();
		String reply_title = request.getParameter("reply_title").trim();
		String reply_cont = request.getParameter("reply_cont").trim();
		String reply_pwd = request.getParameter("reply_pwd").trim();
		
		int q_no = 
			Integer.parseInt(request.getParameter("q_no"));
		int q_group = 
				Integer.parseInt(request.getParameter("q_group"));
		int q_step = 
				Integer.parseInt(request.getParameter("q_step"));
		int q_indent = 
				Integer.parseInt(request.getParameter("q_indent"));
		
		FaqDTO dto = new FaqDTO();
		dto.setQ_no(q_no);
		dto.setQ_writer(reply_writer);
		dto.setQ_title(reply_title);
		dto.setQ_cont(reply_cont);
		dto.setQ_pwd(reply_pwd);
		dto.setQ_group(q_group);
		dto.setQ_step(q_step);
		dto.setQ_indent(q_indent);
		
		FaqDAO dao = FaqDAO.getInstance();
		// 기존에 답변글의 step을 증가시키는 메서드 호출
		dao.replyUpdate(q_group, q_step);  
		int res = dao.replyFaq(dto);  // 답변글을 DB에 등록하는 메서드 호출
		
		ActionForward forward = new ActionForward();
		
		
		if(res > 0) {  // 답변 글이 정상적으로 DB에 등록이 된 경우
			forward.setRedirect(true);
			forward.setPath("admin_faq_list.do");
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시물 답변 등록 실패~~~')");
			out.println("history.back()");
			out.println("</script>");
		}
	
		return forward;
	}

}
