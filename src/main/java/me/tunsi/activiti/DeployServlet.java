package me.tunsi.activiti;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "deployServlet", urlPatterns = { "/deploy" })
public class DeployServlet extends BaseActivitiServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8079908746371218707L;

	@Override
	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String xml = req.getParameter("xml");
		getRepositoryService().createDeployment()
				.addInputStream("process-" + UUID.randomUUID().toString() + ".bpmn20.xml", new ByteArrayInputStream(xml.getBytes("UTF-8"))).deploy();
		resp.sendRedirect("/index.jsp");
	}
}
