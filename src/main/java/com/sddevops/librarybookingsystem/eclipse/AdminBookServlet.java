package com.sddevops.librarybookingsystem.eclipse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Servlet implementation class AdminBookServlet
 */
@WebServlet("/AdminBookServlet")
public class AdminBookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminBookServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/AdminBookServlet/delete":
				delete(request, response);
				break;

			case "/AdminBookServlet/new":
			case "/AdminBookServlet/edit":
				showForm(request, response);
				break;

			case "/AdminBookServlet/insert":
				insert(request, response);
				break;

			case "/AdminBookServlet/update":
				update(request, response);
				break;

			case "/AdminBookServlet/publish":
				publish(request, response);
				break;

			case "/AdminBookServlet/dashboard":
			default:
				list(request, response);
				break;
			}
		} catch (Exception ex) {
			getLogger().error(ex.getMessage());
		}
	}

	/**
	 * @Override
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
		}
		catch (Exception ex) {
			getLogger().error(ex.getMessage());
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<Book> records = new ArrayList<>();

		String sql = "SELECT * FROM books ";

		try (Connection connection = getDbo();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

			// Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Process the ResultSet object.
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String isbn = rs.getString("isbn");
				Integer published = rs.getInt("published");
				String created = rs.getString("created");
				String modified = rs.getString("modified");

				records.add(new Book(id, title, description, isbn, published, created, modified));
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}

		// Set attributes in jsp
		request.setAttribute("records", records);
		request.setAttribute("docTitle", "Book Catalogues");
		request.getRequestDispatcher("/adminBooks.jsp").forward(request, response);
	}

	private void showForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// get parameter passed in the URL
		String idStr = request.getParameter("id");
		Integer id = 0;
		if(idStr != null && isNumeric(idStr)) {
			id = Integer.parseInt(idStr);
		}

		Boolean isNew = (id <= 0);
		Book thisRecord = new Book();

		// Establishing a Connection
		if(!isNew) {
			String sql = "SELECT * FROM books WHERE id =? LIMIT 1";

			try (Connection connection = getDbo();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

				preparedStatement.setInt(1, id);

				// Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Process the ResultSet object
				while (rs.next()) {
					id = rs.getInt("id");
					String title = rs.getString("title");
					String description = rs.getString("description");
					String isbn = rs.getString("isbn");
					Integer published = rs.getInt("published");
					String created = rs.getString("created");
					String modified = rs.getString("modified");

					thisRecord = new Book(id, title, description, isbn, published, created, modified);
					break; // get first record only
				}

			} catch (SQLException e) {
				throw new SQLException(e);
			}
		}

		request.setAttribute("isNew", isNew);
		request.setAttribute("id", id);
		request.setAttribute("formAction", isNew? "insert" : "update");
		request.setAttribute("record", thisRecord);
		request.setAttribute("docTitle", "Book Catalogue Form");
		request.getRequestDispatcher("/adminBookForm.jsp").forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Retrieve value from the request
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		Integer published = Integer.parseInt(request.getParameter("published"));

		// Attempt connection with database and execute update user SQL query
		String sql = "INSERT INTO books " + "(title, description, isbn, published) " + "VALUES(?, ?, ?, ?) ";

		try (Connection connection = getDbo();
				PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, title);
			statement.setString(2, description);
			statement.setString(3, isbn);
			statement.setInt(4, published);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		}

		response.sendRedirect(URL_BASE + "AdminBookServlet/dashboard");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Retrieve value from the request
		Integer id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		Integer published = Integer.parseInt(request.getParameter("published"));

		// Attempt connection with database and execute update user SQL query
		String sql = "UPDATE books " + "SET title=?, description=?, isbn=?, published=? " + "WHERE id =?";

		try (Connection connection = getDbo();
				PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, title);
			statement.setString(2, description);
			statement.setString(3, isbn);
			statement.setInt(4, published);
			statement.setInt(5, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		}

		response.sendRedirect(URL_BASE + "AdminBookServlet/dashboard");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Retrieve value from the request
		Integer id = Integer.parseInt(request.getParameter("id"));

		// Attempt connection with database and execute delete user SQL query
		String sql = "DELETE FROM books WHERE id =?";

		try (Connection connection = getDbo();
				PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		}

		response.sendRedirect(URL_BASE + "AdminBookServlet/dashboard");
	}

	private void publish(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Retrieve value from the request
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer published = Integer.parseInt(request.getParameter("published"));

		// Attempt connection with database and execute update user SQL query
		String sql = "UPDATE books " + "SET published=? " + "WHERE id =?";
		try (Connection connection = getDbo();
				PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setInt(1, published == 1? 0 : 1);
			statement.setInt(2, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		}

		response.sendRedirect(URL_BASE + "AdminBookServlet/dashboard");
	}

	private static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
