package com.ssc.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.AdminDAO;

public class AdminSeatAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		AdminDAO dao = AdminDAO.getInstance();
		request.setAttribute("theaterList", dao.getTheater());
		request.setAttribute("roomList", dao.getRoom());
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_seat_add.jsp");
		
		return forward;
	}

}
