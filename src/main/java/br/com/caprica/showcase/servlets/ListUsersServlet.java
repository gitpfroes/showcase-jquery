package br.com.caprica.showcase.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.caprica.showcase.dao.UserDAO;
import br.com.caprica.showcase.pojo.User;

/**
 * Servlet implementation class ListUsers
 */
@WebServlet("/listusers")
public class ListUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Ideally, you would have a framework like Spring doing dependency injection here.
		List<User> users = new UserDAO().listUsers();
		
		//Converting the list to json using google's gson library
		Gson gson = new Gson();
		String json = gson.toJson(users);
		System.out.println(json);
		
		//Print the json string as response
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
	}

}
