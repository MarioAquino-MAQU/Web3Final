package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.ShopService;

public class DeleteProductHandler extends RequestHandler {
	
	private ShopService service;
	
	public DeleteProductHandler() {
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

		int productId = Integer.parseInt(request.getParameter("id"));
		service.deleteProduct(productId);
		request.setAttribute("actionn", request.getParameter("action"));
		response.addCookie(cookie);
		request.setAttribute("kleur", cookie.getValue());
		response.sendRedirect("Controller?action=products");
		
	}

}
