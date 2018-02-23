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
import domain.Role;
import domain.ShopService;

public class CreateUserHandler extends RequestHandler {
	
	private ShopService service;
	
	public CreateUserHandler() {
		// TODO Auto-generated constructor stub
	}
	

	public ShopService getService() {
		return service;
	}


	public void setService(ShopService service) {
		this.service = service;
	}

	private void verwerkLastName(Person pers, HttpServletRequest request, List<String> fouten) {
		try{
			String lastName = request.getParameter("lastName");
			request.setAttribute("lastName", lastName);
			pers.setLastName(lastName);
		}catch(DomainException exc){
			fouten.add(exc.getMessage());
		}
		
	}

	private void verwerkFirstName(Person pers, HttpServletRequest request, List<String> fouten) {
		try{
			String firstName = request.getParameter("firstName");
			request.setAttribute("firstName", firstName);
			pers.setFirstName(firstName);
		}catch(DomainException exc){
			fouten.add(exc.getMessage());
		}
		
	}

	private void verwerkPassword(Person pers, HttpServletRequest request, List<String> fouten) {
		try{
			String password = request.getParameter("password");
			request.setAttribute("password", password);
			pers.setPassWordHashed(password);
		}catch(DomainException exc){
			fouten.add(exc.getMessage());
		}
		
	}

	private void verwerkEmail(Person pers, HttpServletRequest request, List<String> fouten) {
		try{
			String email = request.getParameter("email");
			request.setAttribute("email", email);
			pers.setEmail(email);
		}catch(DomainException exc){
			fouten.add(exc.getMessage());
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
		
		private void checkZelfdePersoon(Person persoon, HttpServletRequest request, List<String>fouten){
			try{
				for(Person p: service.getPersons()){
					if(p.getUserid().equals(persoon.getUserid())){
						throw new DomainException("User already exists");
					}
				}
			}catch(DomainException ex){
				fouten.add(ex.getMessage());
			}
		}


		@Override
		public void HandleRequest(HttpSession session, Cookie cookie, HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			Person pers = new Person();
			List<String>fouten = new ArrayList<>();
			verwerkUserid(pers, request, fouten);
			verwerkEmail(pers, request, fouten);
			verwerkPassword(pers, request, fouten);
			verwerkFirstName(pers, request, fouten);
			verwerkLastName(pers, request, fouten);
			checkZelfdePersoon(pers, request, fouten);
			if (fouten.size() == 0){
				service.addPerson(pers);
				request.setAttribute("actionn", request.getParameter("action"));
				response.addCookie(cookie);
				request.setAttribute("kleur", cookie.getValue());
				response.sendRedirect("Controller?action=overview");
			}else{
				request.setAttribute("fouten", fouten);
				request.setAttribute("actionn", request.getParameter("action"));
				response.addCookie(cookie);
				request.setAttribute("kleur", cookie.getValue());
				request.getRequestDispatcher("signUp.jsp").forward(request, response);
			}
			
		}

}
