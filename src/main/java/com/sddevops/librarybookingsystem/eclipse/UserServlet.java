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
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private static final String SELECT_USER_BY_ID = "select name,password,email,language from userdetails where name =?";
	private static final String SELECT_ALL_USERS = "select * from userdetails ";
	private static final String DELETE_USERS_SQL = "delete from userdetails where name = ?;";
	private static final String UPDATE_USERS_SQL = "update userdetails set name = ?,password= ?, email =?,language =? where name = ?;";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
				case "/UserServlet/delete":
					deleteUser(request, response);
					break;
				case "/UserServlet/edit":
					showEditForm(request, response);
					break;
				case "/UserServlet/update":
					updateUser(request, response);
					break;
				case "/UserServlet/dashboard":
				default:
					listUsers(request, response);
					break;
			}
		} catch (Exception ex) {
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

	// Step 5: listUsers function to connect to the database and retrieve all users
	// records
	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> users = new ArrayList<>();

		try (Connection connection = getDbo();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String language = rs.getString("language");
				users.add(new User(name, password, email, language));
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}

		// Step 5.4: Set the users list into the listUsers attribute to be pass to the
		// userManagement.jsp
		request.setAttribute("listUsers", users);
		request.getRequestDispatcher("/userManagement.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get parameter passed in the URL
		String name = request.getParameter("name");
		User existingUser = new User("", "", "", "");

		// Step 1: Establishing a Connection
		try (Connection connection = getDbo();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setString(1, name);

			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object
			while (rs.next()) {
				name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String language = rs.getString("language");
				existingUser = new User(name, password, email, language);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}

		// Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("user", existingUser);
		request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String oriName = request.getParameter("oriName");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String language = request.getParameter("language");

		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getDbo();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, name);
			statement.setString(2, password);
			statement.setString(3, email);
			statement.setString(4, language);
			statement.setString(5, oriName);

			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new SQLException(e);
		}

		// Step 3: redirect back to UserServlet (note: remember to change the url to
		// your project name)
		response.sendRedirect(URL_BASE + "UserServlet/dashboard");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String name = request.getParameter("name");

		// Step 2: Attempt connection with database and execute delete user SQL query
		try (Connection connection = getDbo();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setString(1, name);
			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new SQLException(e);
		}

		// Step 3: redirect back to UserServlet dashboard (note: remember to change the
		// url to your project name)
		response.sendRedirect(URL_BASE + "UserServlet/dashboard");
	}

}
