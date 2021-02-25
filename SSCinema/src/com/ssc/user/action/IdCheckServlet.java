package com.ssc.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.model.UserDAO;

/**
 * Servlet implementation class IdCheckServlet
 */
@WebServlet("/idCheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public IdCheckServlet() {}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("id").trim();
		
		UserDAO dao = UserDAO.getInstance();
		PrintWriter out = response.getWriter();
		
		if(id != "") {
			if(dao.idOverCheck(id)) {
				out.println("<span style='color:green'>");
				out.println("사용 가능한 아이디입니다.");
				out.println("</span>");
			}else {
				out.println("<span style='color:red>");
				out.println("중복된 아이디입니다.");
				out.println("</span>");
			}
		}else {
			out.println("<span style='color:red>");
			out.println("아이디를 입력해주세요.");
			out.println("</span>");
		}
	}

}
