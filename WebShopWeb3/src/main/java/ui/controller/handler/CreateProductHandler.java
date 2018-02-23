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
import domain.Product;
import domain.ShopService;

public class CreateProductHandler extends RequestHandler {

	private ShopService service;
	
	public CreateProductHandler() {
		// TODO Auto-generated constructor stub
	}

	public ShopService getService() {
		return service;
	}

	public void setService(ShopService service) {
		this.service = service;
	}

	private void verwerkPrice(Product p, HttpServletRequest request, List<String> fouten) {
		try{
			String price = request.getParameter("price");
			request.setAttribute("price", price);
			p.setPrice(price);
		}catch(DomainException exc){
			fouten.add(exc.getMessage());
		}
		
	}

	private void verwerkDescription(Product p, HttpServletRequest request, List<String> fouten) {
		try{
			String description = request.getParameter("description");
			request.setAttribute("description", description);
			p.setDescription(description);
		}catch(DomainException exc){
			fouten.add(exc.getMessage());
		}
		
	}

	private void verwerkName(Product p, HttpServletRequest request, List<String> fouten) {
		try{
			String name = request.getParameter("name");
			request.setAttribute("name", name);
			p.setName(name);
		}
		catch(DomainException exc){
			fouten.add(exc.getMessage());
		}
		
	}

	@Override
	public void HandleRequest(HttpSession session, Cookie cookie, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Product p = new Product();
		List<String>fouten = new ArrayList<>();
		verwerkName(p, request, fouten);
		verwerkDescription(p, request, fouten);
		verwerkPrice(p, request, fouten);
		if(fouten.size() == 0){
			service.addProduct(p);
			request.setAttribute("actionn", request.getParameter("action"));
			response.addCookie(cookie);
			request.setAttribute("kleur", cookie.getValue());
			response.sendRedirect("Controller?action=products");
		}
		else{
			request.setAttribute("fouten", fouten);
			request.setAttribute("actionn", request.getParameter("action"));
			response.addCookie(cookie);
			request.setAttribute("kleur", cookie.getValue());
			request.getRequestDispatcher("addProduct.jsp").forward(request, response);
		}
		
	}
}
