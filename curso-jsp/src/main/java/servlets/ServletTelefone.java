package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.DAOUserRepository;


@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletGenericUtil {
	
	private static final long serialVersionUID = 1L;
	
	private DAOUserRepository dao = new DAOUserRepository();
       
   
    public ServletTelefone() {}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
		
			String idUser = request.getParameter("idUser");
			
			if(idUser != null && idUser.isEmpty()) {
			
					
				ModelLogin ml = dao.pesquisarId(Long.parseLong(idUser));
					
				request.setAttribute("user", ml);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
					
				
			} else {
				
				List<ModelLogin> users = dao.consultaAll(super.getUserLogado(request));
				request.setAttribute("mLogins",users);
				
				request.setAttribute("totalPagina", dao.totalPagina(this.getUserLogado(request)));
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
