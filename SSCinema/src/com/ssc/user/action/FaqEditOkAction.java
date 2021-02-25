package com.ssc.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.FaqDAO;
import com.ssc.model.FaqDTO;

public class FaqEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정된 데이터를 db에 저장하는 작업
		String q_title = request.getParameter("title").trim();
		String q_cont = request.getParameter("content").trim();
		String q_pwd = request.getParameter("pwd").trim();
		
		// 히든으로 넘어온 데이터도 처리해 주자.
		int q_no = Integer.parseInt(request.getParameter("no"));
		FaqDTO dto = new FaqDTO();
		dto.setQ_no(q_no);
		dto.setQ_title(q_title);
		dto.setQ_cont(q_cont);
		dto.setQ_pwd(q_pwd);
		
		FaqDAO dao = FaqDAO.getInstance();
		int res = dao.updateFaq(dto);
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		if(res>0) {
			forward.setRedirect(true);
			forward.setPath("user_faq_cont.do?no="+q_no);
		}else if(res==-1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('게시물 수정 실패.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
