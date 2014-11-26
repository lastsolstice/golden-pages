package com.gp.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;

import com.gp.users.UserDAO;
import com.gp.users.UserDTO;
import com.gp.users.UserDTO.ColumnName;
import com.gp.users.UserFactory;
import com.gp.util.Helper;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static final String REGISTER_ACTION = "REGISTER";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String hey = request.getParameter("options");
		System.out.println("option: " + hey);

		String action = request.getParameter("action");
		JSONObject responseJSON = new JSONObject();

		responseJSON.put("status", "OK");

		switch (action) {
		case REGISTER_ACTION:
			if (request.getSession().getAttribute("user") != null) {
				System.out.println("User already exist");
				responseJSON.put("status", "ERROR");
				responseJSON.put("message",
						"Trying to create a duplicate account");
				break;
			}
			UserFactory factory = new UserFactory("jdbc/gpappdb"); 
			UserDTO user = new UserDTO();
			String option = request.getParameter("options");
			user.setFullName(request.getParameter("full-name"));
			user.setUsername(request.getParameter("username"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			String passConfirm = request.getParameter("password-confirm");
			if (!Helper.isValid(option, user.getEmail(), user.getFullName(),
					user.getPassword(), user.getUsername(), passConfirm)) {
				System.out.println("ERROR IN INPUT");
				responseJSON.put("status", "ERROR");
				responseJSON.put("message", "Missing attribute");
				break;
			}
			if (!user.getPassword().equals(passConfirm)) {
				responseJSON.put("status", "ERROR");
				responseJSON.put("message", "The passwords didn't match.");
				break;
			}
			String rawPass = user.getPassword();
			user.setPassword(DigestUtils.md5Hex(rawPass));
			try {
				UserDAO userDAO = new UserDAO("jdbc/gpappdb");
				if (userDAO.existAs(user.getUsername(), ColumnName.USERNAME)
						|| userDAO.existAs(user.getEmail(), ColumnName.EMAIL)) {
					responseJSON.put("status", "ERROR");
					responseJSON.put("message",
							"The username or email is already taken");
					break;
				}

				System.out.println("option: " + option);
				if (option.equals("opt-biz")) {
					String bizName = request.getParameter("bname");
					factory.createBusinessUser(bizName, user);
				} else if (option.equals("opt-cons")) {
					factory.createConsumerUser(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
				responseJSON.put("status", "ERROR");
				responseJSON.put("message", "SQL problem");
				break;
			}
			responseJSON.put("message", "The account was created");
			responseJSON.put("action", "reload");
			request.getSession().setAttribute("user", user);
			break; // break case

		default:
			System.out.println("Error");
			break;
		}

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.println(responseJSON);
		out.flush();

	}
	
	
	

}
