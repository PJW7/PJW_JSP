package com.ssc.user.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.ReserveDAO;
import com.ssc.model.ReserveDTO;
import com.ssc.model.ReservedSeatDTO;
import com.ssc.model.SeatDAO;
import com.ssc.model.SeatDTO;

public class SeatListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 예약 페이지에서 넘어온 데이터과 해당 상영일정에 대한 좌석 정보들을
		// 좌석 페이지에 넘겨주는 작업
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		int t_num = Integer.parseInt(request.getParameter("t_num"));
		int m_num = Integer.parseInt(request.getParameter("m_num"));
		
		// 해당 상영번호로 좌석정보 가져오기
		SeatDAO sdao = SeatDAO.getInstance();
		List<SeatDTO> slist = sdao.playingSeatList(p_num);
		
		// 열번호 갯수 가져오기
		int scount = sdao.getSeatCont(p_num);
		
		// 예약된 좌석이 있는지 리스트 가져오기
		ReserveDAO rdao = ReserveDAO.getInstance();
		List<ReserveDTO> rlist = rdao.reservedSeatList(p_num);
		
		// 전체 좌석중 예약리스트와 일치하는 좌석 번호가 있으면 status를 x
		// 없으면 o


		// 일단 모두 예약 가능한 전체좌석 만들기
		List<ReservedSeatDTO> temp = new ArrayList<ReservedSeatDTO>();
		for(int i=0; i<slist.size();i++) {
			ReservedSeatDTO rsdto = new ReservedSeatDTO();
			rsdto.setS_num(slist.get(i).getS_num());
			rsdto.setRow_num(slist.get(i).getRow_num());
			rsdto.setS_name(slist.get(i).getS_name());
			rsdto.setStatus("O");
			
			temp.add(rsdto);
		}
		/*for(int i=0;i<temp.size();i++) {
			System.out.println(temp.get(i).getS_name()+">>>"+ temp.get(i).getStatus());
		}*/
		//예약된 좌석번호에 해당하는 부분은 변경해주자
		for(int i=0;i<temp.size();i++) {
			for(int j=0;j<rlist.size();j++) {
				System.out.println("temp >>>" + temp.get(i).getS_name() +"rlist >>>" + rlist.get(j).getS_name());
				if(rlist.get(j).getS_name().equals(temp.get(i).getS_name())) {
					temp.get(i).setStatus("X");
				}
			}
		}
		
		
		//확인용
		/*for(int i=0;i<rlist.size();i++) {
			System.out.println("rlist "+rlist.get(i).getS_name()+">>>"+ rlist.get(i).getS_name());
		}
		
		
		for(int i=0;i<temp.size();i++) {
			System.out.println(temp.get(i).getS_name()+">>>"+ temp.get(i).getStatus());
		}*/
		/*System.out.println(scount);*/
		
		
		request.setAttribute("p_num", p_num);
		request.setAttribute("t_num", t_num);
		request.setAttribute("m_num", m_num);
		request.setAttribute("seatlist", slist);
		request.setAttribute("reservedlist", rlist);
		request.setAttribute("temp", temp);
		request.setAttribute("scount", scount);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_seat_list.jsp");
		return forward;
	}

}
