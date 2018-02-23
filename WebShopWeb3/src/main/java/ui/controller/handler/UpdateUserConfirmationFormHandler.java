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

public class UpdateUserConfirmationFormHandler extends RequestHandler {
	
	private ShopService service;
	
	public UpdateUserConfirmationFormHandler() {
		// TODO Auto-generated constructor stub
	}
	

	public ShopService getService() {
		return service;
	}


	public void setService(ShopService service) {
		this.service = service;
	}
	
	private void verwerkRole(Person temp, HttpServletRequest request, List<String> fouten) {
		try{
			String role = request.getParameter("role");
			request.setAttribute("role", role);
			temp.setRole(Role.valueOf(role));
		}catch(DomainException exc){
			fouten.add(exc.getMessage());
		}
		
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


	private void verwerkEmail(Person pers, HttpServletRequest request, List<String> fouten) {
		try{
			String email = request.getParameter("email");
			request.setAttribute("email", email);
			pers.setEmail(email);
		}catch(DomainException exc){
			fouten.add(exc.getMessage());
		}
		
	}

	@Override
	public void HandleRequest(HttpSession session, Cookie cookie, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		List<String>fouten = new ArrayList<>();
		Person temp = service.getPerson(userid);
		if(!temp.getEmail().equals(request.getParameter("email"))){
			verwerkEmail(temp, request, fouten);
		}
		if(!temp.getFirstName().equals(request.getParameter("firstName"))){
			verwerkFirstName(temp, request, fouten);
		}
		if(!temp.getLastName().equals(request.getParameter("lastName"))){
			verwerkLastName(temp, request, fouten);
		}
		
		if(!temp.getRole().equals(Role.valueOf(request.getParameter("role")))){
			verwerkRole(temp, request, fouten);
		}
		if(fouten.size() == 0){
			service.updatePersons(temp);
			
			request.setAttribute("actionn", request.getParameter("action"));
			response.addCookie(cookie);
			request.setAttribute("kleur", cookie.getValue());
			response.sendRedirect("Controller?action=overview");
		}
		else{
			request.setAttribute("fouten", fouten);
			request.setAttribute("actionn", request.getParameter("action"));
			response.addCookie(cookie);
			request.setAttribute("kleur", cookie.getValue());
			request.getRequestDispatcher("updatePerson.jsp").forward(request, response);
		}
		
	}

}
