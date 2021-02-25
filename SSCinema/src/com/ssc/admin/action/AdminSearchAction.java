package com.ssc.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.AdminDAO;
import com.ssc.model.MovieInfoDTO;
import com.ssc.model.PlayingInfoDTO;
import com.ssc.model.RoomDTO;
import com.ssc.model.TheaterDTO;

public class AdminSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String search_field = request.getParameter("search_field");
		String search_name = request.getParameter("search_name");
		String table = request.getParameter("table");
		
		int rowsize = 10;
		int block = 3;
		int totalRecord = 0;
		int allPage = 0;
		int page = 0;
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1;
		}
		
		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = page * rowsize;
		int blockNum =  (int) Math.ceil(page / (double) block);
		int startBlock = (blockNum - 1) * block + 1;
		int endBlock = startBlock + block - 1;
		
		AdminDAO dao = AdminDAO.getInstance();

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		if (table.equals("theater")) {
			totalRecord = dao.getSearchListCount(search_field, search_name);
			List<TheaterDTO> searchList = dao.getSearchTheaterList(search_field, search_name, page, rowsize);
			request.setAttribute("t_list", searchList);
			forward.setPath("admin/admin_theater_search.jsp");
		} else if (table.equals("room")) {
			totalRecord = dao.getSearchListCount(search_field, search_name);
			List<RoomDTO> searchList = dao.getSearchRoomList(search_field, search_name, page, rowsize);
			request.setAttribute("r_list", searchList);
			forward.setPath("admin/admin_room_search.jsp");
		} else if (table.equals("movie")) {
			totalRecord = dao.getSearchListCount(search_field, search_name);
			List<MovieInfoDTO> searchList = dao.getSearchMovieList(search_field, search_name, page, rowsize);
			request.setAttribute("m_list", searchList);
			forward.setPath("admin/admin_movie_search.jsp");
		} else if (table.equals("playing")) {
			totalRecord = dao.getSearchListCount(search_field, search_name);
			List<PlayingInfoDTO> searchList = dao.getSearchPlayingList(search_field, search_name, page, rowsize);
			request.setAttribute("p_list", searchList);
			forward.setPath("admin/admin_playing_search.jsp");
		}
		
		allPage = (int) Math.ceil(totalRecord / (double) rowsize);
		
		if (endBlock > allPage) {
			endBlock = allPage;
		}
		
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("blockNum", blockNum);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("search_field", search_field);
		request.setAttribute("search_name", search_name);
		
		return forward;
	}

}
