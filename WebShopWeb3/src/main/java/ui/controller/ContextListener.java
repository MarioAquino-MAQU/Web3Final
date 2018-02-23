package ui.controller;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import domain.ShopService;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		try{
		Properties properties = new Properties();
    	Enumeration<String> parameterNames = context.getInitParameterNames();
    	while(parameterNames.hasMoreElements()){
    		String propertyName = parameterNames.nextElement();
    		properties.setProperty(propertyName, context.getInitParameter(propertyName));
    	}
    	
    	ShopService service = new ShopService(properties);
    	
    	
    		String fileName = context.getInitParameter("handler");
    		InputStream input = context.getResourceAsStream("/WEB-INF/" + fileName);
    		Properties prop = new Properties();
    		prop.loadFromXML(input);
    		HandlerFactory factory = new HandlerFactory(prop, service);
    		
    		context.setAttribute("handlerFactory", factory);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
