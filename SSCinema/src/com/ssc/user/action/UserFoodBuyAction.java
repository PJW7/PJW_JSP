package com.ssc.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.FoodBuyDAO;
import com.ssc.model.UserDAO;

public class UserFoodBuyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//사용자가 음식구매, DB에 구매내역 등록
		int num = Integer.parseInt(request.getParameter("num"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		int price = Integer.parseInt(request.getParameter("price"));
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(id != null) {
			UserDAO u_dao = UserDAO.getInstance();
			FoodBuyDAO dao = FoodBuyDAO.getInstance();
			int uNum = u_dao.getUserInfo(id).getU_num();
			int res = dao.userFoodBuy(uNum, num, qty, price);
		
			if(res>0) {
				u_dao.addFoodMile(uNum,qty,price);
				forward.setRedirect(true);
				forward.setPath("user_food_list.do");
			}else {
				out.println("<script>");
				out.println("alert('구매 실패.')");
				out.println("history.back()");
				out.println("</script>");
			}
		}else {
			out.println("<script>");
			out.println("alert('로그인후 이용 가능합니다.')");
			out.println("</script>");
			forward.setRedirect(true);
			forward.setPath("user_login.do");
		}
		return forward;
	}

}
