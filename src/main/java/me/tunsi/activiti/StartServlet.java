package me.tunsi.activiti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "startServlet", urlPatterns = { "/start" })
public class StartServlet extends BaseActivitiServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8716014383808971077L;

	@Override
	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getRuntimeService().startProcessInstanceById(req.getParameter("id"));

		resp.sendRedirect("/index.jsp");
	}
}
