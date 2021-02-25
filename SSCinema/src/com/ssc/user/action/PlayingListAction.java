package com.ssc.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.PlayingInfoDAO;


@WebServlet("/user_playing_list.do")
public class PlayingListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PlayingListAction() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int t_num = Integer.parseInt(request.getParameter("t_num"));
		int m_num = Integer.parseInt(request.getParameter("m_num"));
		response.setContentType("text/html; charset=UTF-8");

		PlayingInfoDAO dao = PlayingInfoDAO.getInstance();
		String info = dao.theaterPlayingList(t_num,m_num);
		PrintWriter out = response.getWriter();
		out.println(info);
	}

}
