package com.ssc.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.UserDAO;
import com.ssc.model.UserReceiptDAO;

public class UserReserCancelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int reNum = Integer.parseInt(request.getParameter("renum"));
		
		UserReceiptDAO dao = UserReceiptDAO.getInstance();
		UserDAO udao = UserDAO.getInstance();
		udao.subMovieMile(reNum);
		int res = dao.reserveCancel(reNum);

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("user_reserve_receipt.do");
		
		return forward;
	}

}
