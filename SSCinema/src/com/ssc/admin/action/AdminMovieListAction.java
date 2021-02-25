package com.ssc.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.AdminDAO;
import com.ssc.model.MovieInfoDTO;

public class AdminMovieListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int rowsize = 10;
		int block = 5;
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
		
		totalRecord = dao.getListCount("ssc_movie_info");
		allPage = (int) Math.ceil(totalRecord / (double) rowsize);
		
		if (endBlock > allPage) {
			endBlock = allPage;
		}
		
		List<MovieInfoDTO> m_list = dao.getMovieList(page, rowsize);
		
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
		request.setAttribute("m_list", m_list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_movie_list.jsp");
		
		return forward;
		
	}

}
