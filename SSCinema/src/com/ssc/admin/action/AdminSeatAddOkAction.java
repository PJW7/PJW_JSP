package com.ssc.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.AdminDAO;

public class AdminSeatAddOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int theater = Integer.parseInt(request.getParameter("theater"));
		int room = Integer.parseInt(request.getParameter("room"));
		int row = Integer.parseInt(request.getParameter("row")) - 1;
		int qty = Integer.parseInt(request.getParameter("qty"));
		char rows = (char) ('A' + row);
		AdminDAO dao = AdminDAO.getInstance();
		dao.addSeat(room, qty, rows);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_main.jsp");
		
		return forward;
	}

}
