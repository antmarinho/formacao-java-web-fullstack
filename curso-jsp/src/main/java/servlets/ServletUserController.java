package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUserRepository;

@MultipartConfig
public class ServletUserController extends ServletGenericUtil {
	
	private static final long serialVersionUID = 1L;
	
    private DAOUserRepository dao = new DAOUserRepository();   
   
    public ServletUserController() {}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");
		
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
			
				String idUser = request.getParameter("id");
			
				dao.deletar(idUser);
				
				List<ModelLogin> users = dao.consultaAll(super.getUserLogado(request));
				request.setAttribute("mLogins",users);
				
				request.setAttribute("msg", "Excluido com sucesso!");
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {
					
					String idUser = request.getParameter("id");
				
					dao.deletar(idUser);
					
					response.getWriter().write("Excluido com sucesso!");
					
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarAjax")) {
				
				String nome = request.getParameter("nome");
			
				List<ModelLogin> usersJson = dao.consultaUsuarioList(nome,super.getUserLogado(request));
				
				ObjectMapper mapper = new ObjectMapper();
				
				String json = mapper.writeValueAsString(usersJson);
				
				response.getWriter().write(json);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("editar")) {
				
				String id = request.getParameter("id");
				
				ModelLogin ml = dao.pesquisarId(id,super.getUserLogado(request));
				
				List<ModelLogin> users = dao.consultaAll(super.getUserLogado(request));
				request.setAttribute("mLogins",users);
				
				request.setAttribute("msg", "Usuario em edicao");
				request.setAttribute("mLogin", ml);
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				
				List<ModelLogin> users = dao.consultaAll(super.getUserLogado(request));
				
				request.setAttribute("msg", "Usuarios carregados");
				request.setAttribute("mLogins",users);
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {
				
				String id = request.getParameter("id");
				
				ModelLogin ml = dao.pesquisarId(id, super.getUserLogado(request));
				
				if(ml.getFotoUser() != null && !ml.getFotoUser().isEmpty()) {
					
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + ml.getExtensaoFoto());
					response.getOutputStream().write(new Base64().decodeBase64(ml.getFotoUser().split("\\,")[1]));
					
				}
				
			}
			
			else {
				
					List<ModelLogin> users = dao.consultaAll(super.getUserLogado(request));
					request.setAttribute("mLogins",users);
					request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			RequestDispatcher redir = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redir.forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
				
				String msg = "Operacao realizada com sucesso";
			
				String id = request.getParameter("id");
				String nome = request.getParameter("nome");
				String email = request.getParameter("email");
				String user = request.getParameter("user");
				String pass = request.getParameter("pass");
				String perfil = request.getParameter("perfil");
				String sexo = request.getParameter("sexo");
				
				ModelLogin mLogin = new ModelLogin();
				
				mLogin.setEmail(email);
				mLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
				mLogin.setNome(nome);
				mLogin.setPass(pass);
				mLogin.setUser(user);
				mLogin.setPerfil(perfil);
				mLogin.setSexo(sexo);
				
				
				
				if(ServletFileUpload.isMultipartContent((javax.servlet.http.HttpServletRequest) request)) {
					
					Part part = request.getPart("filefoto");
					
					if(part.getSize() > 0) {
						
						byte[] foto = IOUtils.toByteArray(part.getInputStream());
						
						String imagem = "data:image/"+ part.getContentType().split("\\/")[1] + ";base64," +  new org.apache.tomcat.util.codec.binary.Base64().encodeBase64String(foto);
						
						mLogin.setFotoUser(imagem);
						mLogin.setExtensaoFoto(part.getContentType().split("\\/")[1]);
						
						
					}
					
				}
				
				if(dao.validarLogin(mLogin.getUser()) && mLogin.getId() == null)
					msg = "Ja existe usuario com msm login, informe outro login";
				else {
					
					if(mLogin.isNovo())
						msg = "Salvo com sucesso";
					else
						msg = "atualizado com sucesso";
					
					mLogin = dao.salvar(mLogin,super.getUserLogado(request));
				}
				
				List<ModelLogin> users = dao.consultaAll(super.getUserLogado(request));
				request.setAttribute("mLogins",users);
				
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
