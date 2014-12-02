package me.tunsi.activiti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "completeServlet", urlPatterns = { "/complete" })
public class CompleteServlet extends BaseActivitiServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6531663504869785875L;

	@Override
	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getTaskService().complete(req.getParameter("id"));
		resp.sendRedirect("/index.jsp");
	}
}
