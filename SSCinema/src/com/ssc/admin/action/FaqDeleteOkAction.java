package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.FaqDAO;
import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;

public class FaqDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String q_pwd = request.getParameter("pwd").trim();
		
		int q_no = Integer.parseInt(request.getParameter("q_no"));
		
		FaqDAO dao = FaqDAO.getInstance();
		int res = dao.deleteFaq(q_no, q_pwd);
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_faq_list.do");
		}else if(res == -1) {
			out.println("<script>");
			out.println("alert('비밀번호 오류. 확인해 주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('게시물 삭제 실패~~~')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
