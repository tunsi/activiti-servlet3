package me.tunsi.modeler;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import me.tunsi.web.BaseServlet;

@WebServlet(name = "editorServlet", urlPatterns = "/service/editor")
public class EditorServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2980813190225716374L;

	@Override
	protected void doInternal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream editorStream = this.getClass().getClassLoader().getResourceAsStream("editor.html");
		resp.setContentType("application/xhtml+xml");
		IOUtils.write(IOUtils.toByteArray(editorStream), resp.getOutputStream());
	}

}
