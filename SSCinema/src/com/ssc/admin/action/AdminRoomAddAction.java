package com.ssc.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.AdminDAO;
import com.ssc.model.TheaterDTO;

public class AdminRoomAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		AdminDAO dao = AdminDAO.getInstance();
		List<TheaterDTO> t_list = dao.getTheater();
		
		request.setAttribute("t_list", t_list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_room_add.jsp");
		
		return forward;
	}

}
