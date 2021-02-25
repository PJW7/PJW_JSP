package com.ssc.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.UserDAO;
import com.ssc.model.UserReceiptDAO;

public class UserFBuyCancelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bNum = Integer.parseInt(request.getParameter("bnum"));
		
		UserReceiptDAO dao = UserReceiptDAO.getInstance();
		UserDAO udao = UserDAO.getInstance();
		udao.subFoodMile(bNum);
		int res = dao.foodBuyCancel(bNum);

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("user_food_receipt.do");
		
		return forward;
	}

}
