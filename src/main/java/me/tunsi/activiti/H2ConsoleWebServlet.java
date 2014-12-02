package me.tunsi.activiti;

import org.h2.server.web.WebServlet;

@javax.servlet.annotation.WebServlet(name = "h2Servlet", urlPatterns = { "/h2" })
public class H2ConsoleWebServlet extends WebServlet {

	private static final long serialVersionUID = -1271811346894207062L;

}
