package me.tunsi.modeler;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import me.tunsi.web.BaseServlet;

@WebServlet(name = "pluginServlet", urlPatterns = "/service/editor/plugins")
public class PluginServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4237631603242644804L;

	@Override
	protected void doInternal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream pluginStream = this.getClass().getClassLoader().getResourceAsStream("plugins.xml");
		resp.setContentType("application/xml");
		IOUtils.write(IOUtils.toByteArray(pluginStream), resp.getOutputStream());
	}

}
