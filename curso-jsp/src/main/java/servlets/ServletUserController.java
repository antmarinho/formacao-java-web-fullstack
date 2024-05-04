package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;


public class ServletUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletUserController() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		ModelLogin mLogin = new ModelLogin();
		
		mLogin.setEmail(email);
		mLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
		mLogin.setNome(nome);
		mLogin.setPass(pass);
		mLogin.setUser(user);
		
		request.setAttribute("mLogin", mLogin);
		
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		
	}

}
