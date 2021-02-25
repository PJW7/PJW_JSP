package com.ssc.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.UserDAO;
import com.ssc.model.UserReceiptDAO;

public class UserFoodReceiptAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		UserDAO uDao = UserDAO.getInstance();
		int u_num = uDao.getUserInfo(id).getU_num();
		UserReceiptDAO dao = UserReceiptDAO.getInstance();

		//페이징 작업
		int rowsize = 6;
		int block = 10;
		int totalRecord = 0;
		int allPage = 0;
		int page = 0;

		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}else {
			page = 1;
		}
		totalRecord = dao.getFoodBuyCount(u_num);
	
		
		int startNo = (page*rowsize)-(rowsize-1);
		int endNo = (page*rowsize);
		int startBlock = (((page-1)/block)*block)+1;
		int endBlock = (((page-1)/block)*block)+block;
		allPage = (int)Math.ceil(totalRecord/(double)rowsize);

		if(endBlock>allPage) {
			endBlock = allPage;
		}
		
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("fBuyList", dao.getFoodBuyReceipt(u_num,startNo,endNo));

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_food_receipt.jsp");

		return forward;
	}

}
