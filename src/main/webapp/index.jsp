<%@page import="org.activiti.engine.task.Task"%>
<%@page import="org.activiti.engine.runtime.ProcessInstance"%>
<%@page import="org.activiti.engine.repository.ProcessDefinition"%>
<%@page import="org.activiti.engine.TaskService"%>
<%@page import="org.activiti.engine.RuntimeService"%>
<%@page import="org.activiti.engine.RepositoryService"%>
<%@page import="org.activiti.engine.ProcessEngine"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
  ProcessEngine engine = (ProcessEngine)ctx.getBean("processEngine");
  RepositoryService repositoryService = engine.getRepositoryService();
  RuntimeService runtimeService = engine.getRuntimeService();
  TaskService taskService = engine.getTaskService();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
  </head>
  <body>
    Index
    
    <a href="/edit.jsp">发布新的流程定义</a> |
    <a href="/editModel">创建新的流程定义</a> |
    <a href="/h2" target="_blank">数据库</a>
    
    <h2>流程定义</h2>
    <table border="1" width="100%">
      <thead>
        <tr><th>key</th><th>name</th><th>操作</th></tr>
      </thead>
      <tbody>
<%
    for(ProcessDefinition processDefinition : repositoryService.createProcessDefinitionQuery().list()) {
%>
        <tr>
          <td><%=processDefinition.getId() %></td>
          <td><%=processDefinition.getName() %></td>
          <td>
            <a href="start?id=<%=processDefinition.getId() %>">start</a>
            <a href="graph?processDefinitionId=<%=processDefinition.getId() %>" target="_blank">graph</a>
          </td>
        </tr>
<%
    }
%>
      </tbody>
    </table>
    
    <hr />
    
    <h2>流程实例</h2>
    <table border="1" width="100%">
      <thead>
        <tr><th>id</th><th>process definition</th><th>操作</th></tr>
      </thead>
      <tbody>
<%
    for(ProcessInstance processInstance : runtimeService.createProcessInstanceQuery().list()) {
%>
        <tr>
          <td><%=processInstance.getId() %></td>
          <td><%=processInstance.getProcessDefinitionId() %></td>
          <td>
            <a href="graph?processInstanceId=<%=processInstance.getId() %>" target="_blank">graph</a>
          </td>
        </tr>
<%
    }
%>
      </tbody>
    </table>
    
    <hr />
    
    <h2>任务</h2>
    <table border="1" width="100%">
      <thead>
        <tr><th>id</th><th>name</th><th>assignee</th><th>操作</th></tr>
      </thead>
      <tbody>
<%
    for(Task task : taskService.createTaskQuery().list()) {
%>
        <tr>
          <td><%=task.getId() %></td>
          <td><%=task.getName() %></td>
          <td><%=task.getAssignee() %></td>
          <td>
            <a href="complete?id=<%=task.getId() %>" target="_blank">complete</a>
            <a href="graph?taskId=<%=task.getId() %>" target="_blank">graph</a>
          </td>
        </tr>
<%
    }
%>
      </tbody>
    </table>
  </body>
</html>