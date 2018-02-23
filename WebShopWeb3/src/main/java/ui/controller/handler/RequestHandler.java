package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.ShopService;

public abstract class RequestHandler {
	
	private ShopService service;
	

	
	public ShopService getService() {
		return service;
	}


	public void setService(ShopService service) {
		this.service = service;
	}


	public abstract void HandleRequest(HttpSession session, Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
