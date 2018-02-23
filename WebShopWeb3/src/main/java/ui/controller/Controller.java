package ui.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.jna.platform.win32.WinNT.HANDLEByReference;

import db.DbException;
import db.PersonDb;
import db.PersonDbInMemory;
import domain.DomainException;
import domain.Person;
import domain.Product;
import domain.Role;
import domain.ShopService;
import ui.controller.handler.RequestHandler;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }
    private ShopService service;
    private HandlerFactory factory;
    
    
    @Override
    public void init() throws ServletException{
    	
    	super.init();
    	
    	ServletContext context = getServletContext();
    	
    	factory = (HandlerFactory) context.getAttribute("handlerFactory");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action;
		if(request.getParameter("action") != null){
			action = request.getParameter("action");
		}
		else{
			action = "home";
		}
		
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if(cookies != null){
			for(Cookie c: cookies){
				if(c.getName().equals("color")){
					cookie = c;
				}
			}
		}
		if(cookie == null){
			cookie = new Cookie("color", "geel");
		}
		
		HttpSession session = request.getSession();
		if(session.getAttribute("name") != null){
			session.setAttribute("name", session.getAttribute("name"));
			session.setAttribute("id", session.getAttribute("id"));
			request.setAttribute("lol", session.getAttribute("name"));
			session.setAttribute("role", session.getAttribute("role"));
			session.setAttribute("cart", session.getAttribute("cart"));
			session.setAttribute("cartsize", session.getAttribute("cartsize"));
		}		
		
		try{
			RequestHandler handler = factory.getRequestHandler(action);
			handler.HandleRequest(session, cookie, request, response);
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		/*request.setAttribute("actionn", action);
		response.addCookie(cookie);
		request.setAttribute("kleur", cookie.getValue());*/
	}
}
