package me.tunsi.modeler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.tunsi.activiti.BaseActivitiServlet;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@WebServlet(name = "modelEditorJsonServlet", urlPatterns = "/service/model/1/json")
public class ModelEditorJsonServlet extends BaseActivitiServlet implements ModelDataJsonConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1164665305243542843L;

	@Override
	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode modelNode = null;

		String modelId = "1";
		Model model = getRepositoryService().getModel(modelId);

		if (model != null) {
			if (StringUtils.isEmpty(model.getMetaInfo())) {
				modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
			} else {
				modelNode = objectMapper.createObjectNode();
				modelNode.put(MODEL_NAME, model.getName());
			}
			modelNode.put(MODEL_ID, model.getId());
			ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(new String(getRepositoryService().getModelEditorSource(model.getId()), "utf-8"));
			modelNode.put("model", editorJsonNode);
		}

		resp.setContentType("application/json");
		resp.getWriter().write(objectMapper.writeValueAsString(modelNode));
	}

}
