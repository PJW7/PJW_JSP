package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.AdminDAO;

public class AdminGetImageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		int m_num = Integer.parseInt(request.getParameter("m_num"));
		
		AdminDAO dao = AdminDAO.getInstance();
		String res = dao.getImage(m_num);
		
		PrintWriter out = response.getWriter();
		out.println(res);
		
		return null;
	}

}
