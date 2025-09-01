package servlets;

import java.io.Serializable;
import java.sql.SQLException;
import dao.DAOUserRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.ModelLogin;

public class ServletGenericUtil extends HttpServlet implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private DAOUserRepository daoUserRepository = new DAOUserRepository();
	
	
	public Long getUserLogado(HttpServletRequest request) throws SQLException {
		
		HttpSession session = request.getSession();
		
		String user = (String) session.getAttribute("user");
		
		return daoUserRepository.pesquisarLogado(user).getId();
		
	}
	
	public ModelLogin getUserLogadoObj(HttpServletRequest request) throws SQLException {
		
		HttpSession session = request.getSession();
		
		String user = (String) session.getAttribute("user");
		
		return daoUserRepository.pesquisarLogado(user);
		
	}

}
