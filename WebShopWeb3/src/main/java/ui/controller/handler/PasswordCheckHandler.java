package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Person;
import domain.ShopService;

public class PasswordCheckHandler extends RequestHandler {
	
	private ShopService service;
	
	public PasswordCheckHandler() {
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

		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String salt = service.getPerson(userid).getSalt();
		Person temp = new Person();
		temp.setSalt(salt);
		temp.setPassWordHashed(password);
		if(temp.getPassword().equals(service.getPerson(userid).getPassword())){
			String result = "Het passwoord is correct";
			request.setAttribute("result", result);
		}
		else{
			String result = "Het passwoord is NIET correct";
			request.setAttribute("result", result);
		}
		request.setAttribute("actionn", request.getParameter("action"));
		response.addCookie(cookie);
		request.setAttribute("kleur", cookie.getValue());
		request.getRequestDispatcher("pwResult.jsp").forward(request, response);
		
	}

}
