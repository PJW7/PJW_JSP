package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.NoticeDAO;
import com.ssc.model.NoticeDTO;
import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;

public class NoticeWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 게시글 작성폼 창에서 넘어온 데이터들을 DB에 저장하는 클래스
		
		
		String n_title = request.getParameter("title").trim();
		String n_content = request.getParameter("content").trim();
		
		
		NoticeDTO dto = new NoticeDTO();
		dto.setN_title(n_title);
		dto.setN_cont(n_content);
		
		
		NoticeDAO dao = NoticeDAO.getInstance();
		int res = dao.insertnotice(dto);
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('게시물 추가 ')");
			out.println("</script>");
			forward.setRedirect(true);
			forward.setPath("admin_notice_list.do");
		}else {  // 게시글 추가 실패
			out.println("<script>");
			out.println("alert('게시물 추가 실패')");
			out.println("history.back()");
			out.println("</script>");
			forward.setRedirect(false);
			forward.setPath("admin/admin_notice_write.jsp");
		}
		
		return forward;
	}

}
