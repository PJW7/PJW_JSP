package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.MovieInfoDAO;
import com.ssc.model.MovieInfoDTO;

public class AdminMovieEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String saveFolder = "C:\\NCS\\workspace(jsp)\\SSCinema\\WebContent\\upload";
		int fileSize = 5 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

		int m_num = Integer.parseInt(multi.getParameter("m_num"));
		String m_name = multi.getParameter("m_name").trim();
		String m_cont = multi.getParameter("m_cont").trim();
		String m_new_image = multi.getFilesystemName("m_new_image");
		String m_director = multi.getParameter("m_director").trim();
		String m_actor = multi.getParameter("m_actor").trim();
		String m_genre = multi.getParameter("m_genre").trim();
		int m_time = Integer.parseInt(multi.getParameter("m_time"));
		String m_playdate = multi.getParameter("m_playdate");
		String m_old_image = multi.getParameter("m_old_image");
		String m_image;
		if(m_new_image == null) {
			m_image = m_old_image;
		}else {
			m_image = "/upload/" + m_new_image;
		}
		
		MovieInfoDTO dto = new MovieInfoDTO();
		dto.setM_num(m_num);
		dto.setM_name(m_name);
		dto.setM_cont(m_cont);
		dto.setM_image(m_image);
		dto.setM_director(m_director);
		dto.setM_actor(m_actor);
		dto.setM_genre(m_genre);
		dto.setM_time(m_time);
		dto.setM_playdate(m_playdate);
		
/*		System.out.println(m_name);
		System.out.println(m_name);
		System.out.println(m_name);
		System.out.println(m_name);
		System.out.println(m_name);
		System.out.println(m_name);
		System.out.println(m_name);
		System.out.println(m_name);*/
		
		MovieInfoDAO dao = MovieInfoDAO.getInstance();
		int check = dao.editMovie(dto);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_movie_cont.do?no="+m_num);
		} else {
			out.println("<script>");
			out.println("alert('영화 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
