package me.tunsi.activiti;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.cmd.GetDeploymentProcessDiagramCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.task.Task;

@WebServlet(name = "graphServlet", urlPatterns = { "/graph" })
public class GraphServlet extends BaseActivitiServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4015687201533794095L;

	@Override
	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String processDefinitionId = req.getParameter("processDefinitionId");
		String processInstanceId = req.getParameter("processInstanceId");
		String taskId = req.getParameter("taskId");

		resp.setContentType("image/png");

		Command<InputStream> cmd = null;

		if (processDefinitionId != null) {
			cmd = new GetDeploymentProcessDiagramCmd(processDefinitionId);
		}

		if (processInstanceId != null) {
			cmd = new ProcessInstanceDiagramCmd(processInstanceId);
		}

		if (taskId != null) {
			Task task = getTaskService().createTaskQuery().taskId(taskId).singleResult();
			cmd = new ProcessInstanceDiagramCmd(task.getProcessInstanceId());
		}

		OutputStream out = null;
		try {
			out = resp.getOutputStream();

			if (cmd != null) {
				InputStream is = getManagementService().executeCommand(cmd);

				int len = 0;
				byte[] b = new byte[1024];
				while ((len = is.read(b, 0, 1024)) != -1) {
					out.write(b, 0, len);
				}
			}

			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
