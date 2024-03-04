package com.sddevops.librarybookingsystem.eclipse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static final String URL_BASE = "http://localhost:8080/LibraryBookingSystem/";

	private static final String DB_URL = "jdbc:mysql://localhost:3306/library_booking";
	private static final String DB_USERNAME = "wesley";
	private static final String DB_PASSWORD = "wes2011";


	private final Logger logger = LogManager.getLogger(this.getClass());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaseServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
		catch (Exception ex) {
			getLogger().error(ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doGet(request, response);
		}
		catch (Exception ex) {
			getLogger().error(ex.getMessage());
		}
	}

	protected Connection getDbo() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (Exception ex) {
			getLogger().error(ex.getMessage());
		}

		return connection;
	}

	protected Logger getLogger() {
		return logger;
	}

}
