package com.ssc.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;

import com.ssc.model.FoodDAO;
import com.ssc.model.FoodDTO;


public class AdminFoodAddOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String saveFolder = "E:\\NCS\\workspace(jsp)\\SSCinema\\WebContent\\upload";
		int fileSize = 5 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

		String f_name = multi.getParameter("f_name").trim();
		String f_image = "/upload/" + multi.getFilesystemName("f_image");
		int f_price = Integer.parseInt(multi.getParameter("f_price"));
		
		FoodDTO dto = new FoodDTO();
		dto.setF_name(f_name);
		dto.setF_image(f_image);
		dto.setF_price(f_price);
		
		FoodDAO dao = FoodDAO.getInstance();
		int check = dao.addFood(dto);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_food_list.do");
		} else {
			out.println("<script>");
			out.println("alert('메뉴 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
