package com.ssc.user.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.TheaterDAO;
import com.ssc.model.TheaterDTO;


@WebServlet("/user_theater_list.do")
public class TheaterListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public TheaterListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int m_num = Integer.parseInt(request.getParameter("m_num"));
		response.setContentType("text/html; charset=UTF-8");
		// 선택된 영화번호가 있는 예매내역의 극장 가져오기
		TheaterDAO dao = TheaterDAO.getInstance();
		String theater = dao.playingTheaterList(m_num);
		
		PrintWriter out = response.getWriter();
		out.println(theater);
	}

}
