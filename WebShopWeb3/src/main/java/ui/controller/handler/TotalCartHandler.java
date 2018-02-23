package ui.controller.handler;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Product;
import domain.ShopService;

public class TotalCartHandler extends RequestHandler {
	
	private ShopService service;
	
	public TotalCartHandler() {
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
		
		Map<Product, Integer>bill = (Map<Product, Integer>) session.getAttribute("cart");
		request.setAttribute("cart", bill);
		request.setAttribute("total", berekenTotalePrijs(bill));
		
		request.setAttribute("actionn", request.getParameter("action"));
		response.addCookie(cookie);
		request.setAttribute("kleur", cookie.getValue());
		request.getRequestDispatcher("cart.jsp").forward(request, response);

	}
	
	private double berekenTotalePrijs(Map<Product,Integer> lijst){
		double result = 0;
		for(Product key: lijst.keySet()){
			result += key.getPrice() * lijst.get(key);
		}
		return result;
	}

}
