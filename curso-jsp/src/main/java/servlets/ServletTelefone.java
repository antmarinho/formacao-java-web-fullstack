package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.DAOTelefoneRepository;
import dao.DAOUserRepository;


@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletGenericUtil {
	
	private static final long serialVersionUID = 1L;
	
	private DAOUserRepository dao = new DAOUserRepository();
	private DAOTelefoneRepository daot = new DAOTelefoneRepository();
       
   
    public ServletTelefone() {}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
		
			String acao = request.getParameter("acao");
			
			if(acao != null && !acao.isEmpty() && acao.equals("excluir")) {
				
				String idFone = request.getParameter("id");
				
				daot.deletar(Long.parseLong(idFone));
				
				String userPai = request.getParameter("user");
				
				ModelLogin ml = dao.pesquisarId(Long.parseLong(userPai));

				List<ModelTelefone> fones = daot.listaFone(ml.getId());
				
				request.setAttribute("fones", fones);
				request.setAttribute("msg", "Telefone Excluido");
				request.setAttribute("ml", ml);
				
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				
				return;
				
			}
			
			String idUser = request.getParameter("idUser");
			
			if(idUser != null && idUser.isEmpty()) {
			
					
				ModelLogin ml = dao.pesquisarId(Long.parseLong(idUser));
				
				List<ModelTelefone> fones = daot.listaFone(ml.getId());
				
				request.setAttribute("fones", fones);
				request.setAttribute("ml", ml);
				
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
		
		String user_pai = request.getParameter("id");
		String numero = request.getParameter("numero");
		
		try {
			
				ModelTelefone fone = new ModelTelefone();
			
				fone.setNumero(numero);
				fone.setUser_id(dao.pesquisarId(Long.parseLong(user_pai)));
				fone.setUser_cad(super.getUserLogadoObj(request));
				
				daot.salvar(fone);
				
				List<ModelTelefone> fones = daot.listaFone(Long.parseLong(user_pai));
				
				ModelLogin ml = dao.pesquisarId(Long.parseLong(user_pai));
				
				request.setAttribute("ml", ml);
				request.setAttribute("fones", fones);
				request.setAttribute("msg", "Salvo com sucesso");
				
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				
			
		} catch (NumberFormatException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
