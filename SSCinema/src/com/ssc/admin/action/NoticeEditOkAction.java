package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.NoticeDAO;
import com.ssc.model.NoticeDTO;
import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;



public class NoticeEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정폼 창에서 넘어온 데이터들을 가지고 DB에서 수정하는 클래스
		
		
		String n_title = request.getParameter("title").trim();
		String n_content = request.getParameter("content").trim();
		
		
		// 히든으로 넘어온 데이터도 처리해 주자.
		int n_num = 
			Integer.parseInt(request.getParameter("n_num"));
		
		NoticeDTO dto = new NoticeDTO();
		dto.setN_num(n_num);
		dto.setN_title(n_title);
		dto.setN_cont(n_content);
		
		
		NoticeDAO dao = NoticeDAO.getInstance();
		int res = dao.updateNotice(dto);
		
		PrintWriter out = response.getWriter();
		
		ActionForward forward = new ActionForward();
		
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_notice_cont.do?num="+n_num);
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
