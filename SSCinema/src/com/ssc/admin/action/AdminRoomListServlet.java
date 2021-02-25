//AdminRoomListServlet

package com.ssc.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.AdminDAO;
import com.ssc.model.RoomDTO;

/**
 * Servlet implementation class AdminRoomListServlet
 */
@WebServlet("/admin_get_room_list.do")
public class AdminRoomListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminRoomListServlet() {}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		AdminDAO dao = AdminDAO.getInstance();
		int tnum = 0;
		if(request.getParameter("tnum") != "") {
			tnum = Integer.parseInt(request.getParameter("tnum"));
		}
		List<RoomDTO> list = dao.getRoom();

		String arr = "";		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getT_num_fk() == tnum) {
				arr += "<option value='"+list.get(i).getR_num()+"'>"+list.get(i).getR_name()+
							"("+list.get(i).getR_num()+")</option>";
			}
		}
		
		response.getWriter().println(arr);
		
	}

}
