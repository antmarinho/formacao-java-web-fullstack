package servlets;

import java.io.IOException;

import dao.DAOUserRepository;
import dao.DaoLoginRepository;
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
	
	private DaoLoginRepository daoLogin = new DaoLoginRepository();
    private DAOUserRepository daoUser = new DAOUserRepository();
 
    public ServletLogin() {}

    // recebe os dados pela url em parametros
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			
			request.getSession().invalidate();
			
			RequestDispatcher redir = request.getRequestDispatcher("/index.jsp");
			redir.forward(request, response);
			
		} else {
			
			doPost(request, response);
		}
	
	}

	// recebe os dados enviados por um formulario
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("login");
		String pass = request.getParameter("senha");
		String url = request.getParameter("url");
		
		try {
		
				if(user != null && pass != null && !user.isEmpty() && !pass.isEmpty()) {
					
					ModelLogin mLogin = new ModelLogin();
					
					mLogin.setPass(pass);
					mLogin.setUser(user);
					
					//simulando login
					if(daoLogin.validarAutenticacao(mLogin)) {
						
						mLogin = daoUser.pesquisarLogado(user);
						
						request.getSession().setAttribute("user", mLogin.getUser());
						request.getSession().setAttribute("isAdmin", mLogin.getUseradmin());
					
						if(url == null || url.equals("null")) {
							url = "principal/principal.jsp";
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
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			RequestDispatcher redir = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redir.forward(request, response);
		}
		
	}

}
