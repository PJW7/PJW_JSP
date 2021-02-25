package com.ssc.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.PlayingInfoDAO;
import com.ssc.model.PlayingInfoDTO;
import com.ssc.model.ReserveDAO;
import com.ssc.model.ReserveDTO;
import com.ssc.model.SeatDAO;
import com.ssc.model.SeatDTO;
import com.ssc.model.UserDAO;

public class MoviePaymentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		String s_name = request.getParameter("s_name");
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		System.out.println(id);
		
		if(id == null) {
			out.println("<script>");
			out.println("alert('로그인후 이용 가능합니다.')");
			out.println("</script>");
			forward.setRedirect(true);
			forward.setPath("user_login.do");
		}else {
			UserDAO udao = UserDAO.getInstance();
			int u_num = udao.getUserInfo(id).getU_num();
		
			PlayingInfoDAO pdao = PlayingInfoDAO.getInstance();
			PlayingInfoDTO pdto = pdao.getPlayingCont(p_num);
		/*
		 * dto.setP_num(rs.getInt("p_num"));
				dto.setM_num_fk(rs.getInt("m_num_fk"));
				dto.setT_num_fk(rs.getInt("t_num_fk"));
				dto.setR_num_fk(rs.getInt("r_num_fk"));
				dto.setP_date(rs.getString("p_date"));
		 */
			SeatDAO sdao = SeatDAO.getInstance();
			SeatDTO sdto = sdao.getSeat(pdto.getR_num_fk(),s_name);
		
		
			ReserveDTO redto = new ReserveDTO();
			redto.setM_num(pdto.getM_num_fk());
			redto.setP_num_fk(pdto.getP_num());
			redto.setR_num_fk(pdto.getR_num_fk());
			redto.setS_name(s_name);
			redto.setS_num(sdto.getS_num());
			redto.setRow_num(sdto.getRow_num());
			redto.setT_num_fk(pdto.getT_num_fk());
			redto.setU_num_fk(u_num);
			System.out.println("m_num >>>" + pdto.getM_num_fk());
			System.out.println("p_num >>>" + pdto.getP_num());
			System.out.println("R_num >>>" + pdto.getR_num_fk());
			System.out.println("S_name >>>" + s_name);
			System.out.println("t_num >>>" + pdto.getT_num_fk());
			System.out.println("U_num >>>" + u_num);
		
			ReserveDAO redao = ReserveDAO.getInstance();
			int result = redao.insertReservation(redto);

			if(result > 0) {
				udao.addMovieMile(u_num);
			
				forward.setRedirect(false);
				forward.setPath("user/user_payment_ok.jsp");
			}else {
				out.println("<script>");
				out.println("alert('예매 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		}
		return forward;
	}

}
