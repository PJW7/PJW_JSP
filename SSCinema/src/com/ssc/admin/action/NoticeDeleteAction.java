package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.NoticeDAO;

public class NoticeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int n_num =
				Integer.parseInt(request.getParameter("num"));
	
		
		
			
			NoticeDAO dao = NoticeDAO.getInstance();
			int res = dao.deleteNotice(n_num);
			
			
			ActionForward forward = new ActionForward();
			PrintWriter out = response.getWriter();
			
			if(res > 0) {
				forward.setRedirect(true);
				forward.setPath("admin_notice_list.do");
			}else {
				out.println("<script>");
				out.println("alert('공지사항 삭제 실패~~~')");
				out.println("history.back()");
				out.println("</script>");
			}
			
			return forward;

		}

	
	}


