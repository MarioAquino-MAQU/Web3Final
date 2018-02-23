package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.ShopService;

public class UserOverviewHandler extends RequestHandler {

	private ShopService service;
	public UserOverviewHandler(ShopService service) {
		this.service = service;
	}
	
	public UserOverviewHandler() {
		
	}

	
	public ShopService getService() {
		return service;
	}

	public void setService(ShopService service) {
		this.service = service;
	}

	@Override
	public void HandleRequest(HttpSession session, Cookie cookie, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("Admin")){
			String destination = "personoverview.jsp";
			request.setAttribute("service", service.getPersons());
			request.setAttribute("actionn", request.getParameter("action"));
			response.addCookie(cookie);
			request.setAttribute("kleur", cookie.getValue());
			request.getRequestDispatcher(destination).forward(request, response);
		}
		else{
			request.setAttribute("error", "You're not authorized to do this");
			request.setAttribute("actionn", request.getParameter("action"));
			response.addCookie(cookie);
			request.setAttribute("kleur", cookie.getValue());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
		
	}

}
