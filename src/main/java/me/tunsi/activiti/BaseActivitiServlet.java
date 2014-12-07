package me.tunsi.activiti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.tunsi.web.BaseServlet;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class BaseActivitiServlet extends BaseServlet {

	private static final long serialVersionUID = 8075503249799515863L;

	private ApplicationContext _applicationContext;

	private ProcessEngine _processEngine;

	@Override
	protected void doInternal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doInit(req, resp);
		doService(req, resp);
	}

	protected void doInit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		_applicationContext = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
		_processEngine = (ProcessEngine) _applicationContext.getBean("processEngine");

	}

	public ApplicationContext getApplicationContext() {
		return _applicationContext;
	}

	public ProcessEngine getProcessEngine() {
		return _processEngine;
	}

	public RepositoryService getRepositoryService() {
		return getProcessEngine().getRepositoryService();
	}

	public RuntimeService getRuntimeService() {
		return getProcessEngine().getRuntimeService();
	}

	public TaskService getTaskService() {
		return getProcessEngine().getTaskService();
	}

	public HistoryService getHistoryService() {
		return getProcessEngine().getHistoryService();
	}

	public ManagementService getManagementService() {
		return getProcessEngine().getManagementService();
	}

	public FormService getFormService() {
		return getProcessEngine().getFormService();
	}

	abstract protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
