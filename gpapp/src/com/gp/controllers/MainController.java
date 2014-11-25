package com.gp.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.gp.users.UserDTO;
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
		

		String action = request.getParameter("action");
		JSONObject responseJSON = new JSONObject();
		
		responseJSON.put("status", "OK");
		

		switch (action) {
		case REGISTER_ACTION:
			UserFactory factory = new UserFactory();							//TODO implement
			UserDTO user = null;
			String option = request.getParameter("options");
			String fullName = request.getParameter("full-name");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String passConfirm = request.getParameter("password-confirm");
			if(!Helper.isValid(option, fullName, username, email, password, passConfirm)){
				System.out.println("ERROR IN INPUT");
				responseJSON.put("status", "ERROR");
				responseJSON.put("message", "Missing attribute");
				break;
			}
			if(!password.equals(passConfirm)){
				responseJSON.put("status", "ERROR");
				responseJSON.put("message", "The passwords didn't match.");
				break;
			}
			if(option.equals("opt-biz")){
				String bizName = request.getParameter("bname");
				user = factory.createBusinessUser(bizName, fullName, username, email, password);
			}
			else if(option.equals("opt-biz")){
				user = factory.createConsumerUser(fullName, username, email, password);
				responseJSON.put("message", "Missing attributes");
			}
			responseJSON.put("message", "The account was created");	
			
			
			request.getSession().setAttribute("user", user);
			break;  //break case
			
			
			
			
			
			
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
