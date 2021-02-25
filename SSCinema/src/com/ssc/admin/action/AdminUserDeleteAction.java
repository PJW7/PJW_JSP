package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.UserDAO;
import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;

public class AdminUserDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
			int ad_us_num =
				Integer.parseInt(request.getParameter("user_no"));
	
			
		
			
			UserDAO dao = UserDAO.getInstance();
			int res = dao.deletAd_Us(ad_us_num);
			
			
			ActionForward forward = new ActionForward();
			PrintWriter out = response.getWriter();
			
			if(res > 0) {
				forward.setRedirect(true);
				forward.setPath("admin_user_list.do");
			}else {
				out.println("<script>");
				out.println("alert('유저 삭제 실패~~~')");
				out.println("history.back()");
				out.println("</script>");
			}
			
			return forward;

		}
	}
