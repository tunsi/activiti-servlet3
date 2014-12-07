package me.tunsi.activiti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.repository.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@WebServlet(name = "editModelServlet", urlPatterns = { "/editModel" })
public class EditModelServlet extends BaseActivitiServlet implements ModelDataJsonConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4255391901071924412L;

	@Override
	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String modelId = req.getParameter("id");
		if (modelId == null) {
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);

			Model modelData = getRepositoryService().newModel();
			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(MODEL_NAME, "test");
			modelObjectNode.put(MODEL_REVISION, 1);
			modelObjectNode.put(MODEL_DESCRIPTION, "test desc");
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName("test");

			getRepositoryService().saveModel(modelData);
			getRepositoryService().addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

			resp.sendRedirect("service/editor?id=" + modelData.getId());
		}
	}

}
