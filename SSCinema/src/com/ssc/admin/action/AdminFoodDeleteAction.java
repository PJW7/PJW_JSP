package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.FoodDAO;

public class AdminFoodDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int f_num = Integer.parseInt(request.getParameter("no"));
		
		FoodDAO dao = FoodDAO.getInstance();
		int check = dao.deleteFood(f_num);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(check>0) {
			forward.setRedirect(true);
			forward.setPath("admin_food_list.do");
		}else if(check == -1) {
			out.println("<script>");
			out.println("alert('구매내역이 존재합니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
