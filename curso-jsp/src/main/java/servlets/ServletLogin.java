package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet(urlPatterns = {"/principal/ServletLogin"})
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
 
    public ServletLogin() {
       
        
    }

    // recebe os dados pela url em parametros
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	// recebe os dados enviados por um formulario
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("login");
		String pass = request.getParameter("senha");
		String url = request.getParameter("url");
		
		if(user != null && pass != null && !user.isEmpty() && !pass.isEmpty()) {
			
			ModelLogin mLogin = new ModelLogin();
			
			mLogin.setPass(pass);
			mLogin.setUser(user);
			
			//simulando login
			if(mLogin.getUser().equalsIgnoreCase("admin") && mLogin.getPass().equalsIgnoreCase("admin")) {
				
				request.getSession().setAttribute("user", mLogin.getUser());
				
				if(url == null || url.equals("null")) {
					url = "/principal/principal.jsp";
				}
				
				RequestDispatcher redir = request.getRequestDispatcher(url);
				redir.forward(request, response);
				
			} else {
				
				RequestDispatcher redir = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe o login e senha corretamente");
				redir.forward(request, response);
			}
			
		} else {
			
			RequestDispatcher redir = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente");
			redir.forward(request, response);
		}
		
		
	}

}
