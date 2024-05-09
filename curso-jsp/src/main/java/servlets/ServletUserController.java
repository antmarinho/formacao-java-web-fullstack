package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

import dao.DAOUserRepository;


public class ServletUserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    private DAOUserRepository dao = new DAOUserRepository();   
   
    public ServletUserController() {}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
				
				String msg = "Operacao realizada com sucesso";
			
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
				
				if(dao.validarLogin(mLogin.getUser()) && mLogin.getId() == null)
					msg = "Ja existe usuario com msm login, informe outro login";
				else {
					
					if(mLogin.isNovo())
						msg = "Salvo com sucesso";
					else
						msg = "atualizado com sucesso";
					
					mLogin = dao.salvar(mLogin);
				}
				
				
				request.setAttribute("msg", msg);
				request.setAttribute("mLogin", mLogin);
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			RequestDispatcher redir = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redir.forward(request, response);
		}
			
	}

}
