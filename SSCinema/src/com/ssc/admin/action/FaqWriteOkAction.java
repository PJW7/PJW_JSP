package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.FaqDAO;
import com.ssc.model.FaqDTO;
import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;

public class FaqWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
			out.println("<script>");
			out.println("alert('게시물 추가 성공!!!')");
			out.println("</script>");
			forward.setRedirect(true);
			forward.setPath("admin_faq_list.do");
		}else {  // 게시글 추가 실패
			out.println("<script>");
			out.println("alert('게시물 추가 실패!!!')");
			out.println("</script>");
			forward.setRedirect(false);
			forward.setPath("admin/admin_faq_write.jsp");
		}
		return forward;
		
		
	}

}
