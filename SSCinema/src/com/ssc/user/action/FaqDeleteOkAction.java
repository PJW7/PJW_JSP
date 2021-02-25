package com.ssc.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.FaqDAO;

public class FaqDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 넘어온 번호에 해당하는 글을 삭제하는 작업
		int q_no = Integer.parseInt(request.getParameter("no"));
		String q_pwd = request.getParameter("pwd");
		
		FaqDAO dao = FaqDAO.getInstance();
		int res = dao.deleteFaq(q_no,q_pwd);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res>0) {
			forward.setRedirect(true);
			forward.setPath("user_faq_list.do");
		}else if(res==-1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('게시물 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
