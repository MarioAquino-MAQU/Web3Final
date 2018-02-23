package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.DomainException;
import domain.Product;
import domain.ShopService;

public class AddToCartHandler extends RequestHandler {
	
	private ShopService service;
	
	public AddToCartHandler() {
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
		Map<Product, Integer> cart;
		int id = Integer.parseInt(request.getParameter("productId"));
		Integer count = null;
		try{
			count = Integer.parseInt(request.getParameter("count"));
		}catch(Exception ex){
			throw new DomainException(ex.getMessage());
		}
		
		if(count <= 0){
			request.setAttribute("error", "U kunt geen negatieve hoeveelheden toevoegen aan uw wagen, ga terug naar producten om verder te werken");
			request.setAttribute("actionn", request.getParameter("action"));
			response.addCookie(cookie);
			request.setAttribute("kleur", cookie.getValue());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		if(session.getAttribute("cart") != null){
			cart = (Map<Product, Integer>) session.getAttribute("cart");
			if(cart.get(id) != null){
				cart.put(service.getProduct(id), cart.get(service.getProduct(id)) + count);
			}else{
				cart.put(service.getProduct(id), count);
			}
			
		}
		else{
			cart = new HashMap<>();
			cart.put(service.getProduct(id), count);
		}
		
		request.setAttribute("actionn", request.getParameter("action"));
		response.addCookie(cookie);
		request.setAttribute("kleur", cookie.getValue());
		
		session.setAttribute("cart", cart);
		session.setAttribute("cartsize", countItems(cart));
		response.sendRedirect("Controller?action=products");

	}
	
	private int countItems(Map<Product, Integer> lijst){
		int result = 0;
		for(Product key: lijst.keySet()){
			result += lijst.get(key);
		}
		return result;
	}

}
