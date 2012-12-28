package org.zkoss.todoZK;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.zkoss.todoZK.dao.AbstractDB;

public class InitServer extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		String type = config.getInitParameter("type");
		AbstractDB.getInstance(type);
	}
}