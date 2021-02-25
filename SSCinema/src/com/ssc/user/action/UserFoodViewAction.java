package com.ssc.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssc.controller.Action;
import com.ssc.controller.ActionForward;
import com.ssc.model.FoodDAO;

public class UserFoodViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		FoodDAO dao = FoodDAO.getInstance();
		request.setAttribute("food", dao.getFoodCont(num));
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_food_view.jsp");
		
		return forward;
	}

}
