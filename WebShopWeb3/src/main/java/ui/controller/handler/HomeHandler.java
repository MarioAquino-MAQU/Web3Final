package ui.controller.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeHandler extends RequestHandler {
	
	public HomeHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void HandleRequest(HttpSession session, Cookie cookie, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String destination = "index.jsp";
		
		request.setAttribute("actionn", request.getParameter("action"));
		response.addCookie(cookie);
		request.setAttribute("kleur", cookie.getValue());
		request.getRequestDispatcher(destination).forward(request, response);
		
	}

}
