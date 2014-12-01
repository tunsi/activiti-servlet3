package me.tunsi.webconfig;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ActivitiServletContainerInitializer implements ServletContainerInitializer {

	public void onStartup(Set<Class<?>> arg0, ServletContext servletContext) throws ServletException {

		System.out.println("startup");
	}

}
