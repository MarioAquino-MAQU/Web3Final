package ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import domain.DomainException;
import domain.ShopService;
import ui.controller.handler.RequestHandler;

public class HandlerFactory {

	private Map<String, RequestHandler> handlers = new HashMap<>();
	
	public HandlerFactory(Properties prop, ShopService service ){
		for(Object key: prop.keySet()){
			RequestHandler handler = null;
			String handlerName = prop.getProperty((String) key);
			try{
				Class<?> handlerClass = Class.forName(handlerName);
				Object handlerObject = handlerClass.newInstance();
				handler = (RequestHandler) handlerObject;
			}catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
				throw new DomainException(e.getMessage());
			}
			handler.setService(service);
			handlers.put((String) key, handler);
		}
	}
	
	public RequestHandler getRequestHandler(String key){
		RequestHandler handler = handlers.get(key);
		if(handler != null){
			return handler;
		}else{
			return handlers.get("home");
		}
		
	}
}
