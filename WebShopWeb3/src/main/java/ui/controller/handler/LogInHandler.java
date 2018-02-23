package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.DomainException;
import domain.Person;
import domain.ShopService;

public class LogInHandler extends RequestHandler {
	
	private ShopService service;
	
	public LogInHandler() {
		// TODO Auto-generated constructor stub
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
		
		String id = request.getParameter("userid");
		String pw = request.getParameter("password");
		List<String>fouten = new ArrayList<>();
		Person temp = new Person();
		verwerkUserid(temp, request, fouten);
		try{
			temp.setSalt(service.getPerson(id).getSalt());
			temp.setPassWordHashed(pw);
		}catch(DomainException ex){
			fouten.add(ex.getMessage());
		}
		if(fouten.size() == 0 && temp.getPassword().equals(service.getPerson(id).getPassword())){
			session.setAttribute("name", service.getPerson(id).getFirstName());
			session.setAttribute("id", service.getPerson(id).getUserid());
			session.setAttribute("role", service.getPerson(id).getRole().name());
			request.setAttribute("lol", session.getAttribute("name"));
			response.addCookie(cookie);
			request.setAttribute("kleur", cookie.getValue());
			response.sendRedirect("Controller?action=");
		}
		else{
			if(fouten.size() == 0){
				fouten.add("Username of passwoord is ongeldig.");
			}
			Boolean result = false;
			request.setAttribute("result", result);
			request.setAttribute("fouten", fouten);
			String doel = "index.jsp";
			request.setAttribute("actionn", request.getParameter("action"));
			response.addCookie(cookie);
			request.setAttribute("kleur", cookie.getValue());
			request.getRequestDispatcher(doel).forward(request, response);
		}

	}
	
	private void verwerkUserid(Person pers, HttpServletRequest request, List<String> fouten) {
		try{
			String userid = request.getParameter("userid");
			request.setAttribute("userid", userid);
			pers.setUserid(userid);
		}catch(DomainException exc){
			fouten.add(exc.getMessage());
		}
		
	}

}
