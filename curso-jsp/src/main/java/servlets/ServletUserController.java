package servlets;

import dto.DTOGraficoSalarioUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;
import utils.ReportUtil;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
				
				request.setAttribute("totalPagina", dao.totalPagina(this.getUserLogado(request)));
				
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
				
				response.addHeader("totalPagina", "" + dao.consultaUsuarioListTotalPaginaPaginacao(nome, super.getUserLogado(request)));
				response.getWriter().write(json);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarAjaxPage")) {
				
				String nome = request.getParameter("nome");
				String pagina = request.getParameter("pagina");
			
				List<ModelLogin> usersJson = dao.consultaUsuarioListOffset(nome,super.getUserLogado(request),Integer.parseInt(pagina));
				
				ObjectMapper mapper = new ObjectMapper();
				
				String json = mapper.writeValueAsString(usersJson);
				
				response.addHeader("totalPagina", "" + dao.consultaUsuarioListTotalPaginaPaginacao(nome, super.getUserLogado(request)));
				response.getWriter().write(json);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("editar")) {
				
				String id = request.getParameter("id");
				
				ModelLogin ml = dao.pesquisarId(id,super.getUserLogado(request));
				
				List<ModelLogin> users = dao.consultaAll(super.getUserLogado(request));
				request.setAttribute("mLogins",users);
				
				request.setAttribute("msg", "Usuario em edicao");
				request.setAttribute("mLogin", ml);
				
				request.setAttribute("totalPagina", dao.totalPagina(this.getUserLogado(request)));
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				
				List<ModelLogin> users = dao.consultaAll(super.getUserLogado(request));
				
				request.setAttribute("msg", "Usuarios carregados");
				request.setAttribute("mLogins",users);
				
				request.setAttribute("totalPagina", dao.totalPagina(this.getUserLogado(request)));
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {
				
				String id = request.getParameter("id");
				
				ModelLogin ml = dao.pesquisarId(id, super.getUserLogado(request));
				
				if(ml.getFotoUser() != null && !ml.getFotoUser().isEmpty()) {
					
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + ml.getExtensaoFoto());
					response.getOutputStream().write(new Base64().decodeBase64(ml.getFotoUser().split("\\,")[1]));
					
				}
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				
				Integer offser = Integer.parseInt(request.getParameter("pagina"));
				
				List<ModelLogin> ml = dao.consultaAllPaginada(this.getUserLogado(request), offser);
				
				request.setAttribute("mLogins",ml);
				
				request.setAttribute("totalPagina", dao.totalPagina(this.getUserLogado(request)));
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorio")) {
				
				String dataInicial = request.getParameter("dataIni");
				String dataFinal = request.getParameter("dataF");
				
				if(dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty())
					
					request.setAttribute("listaUser", dao.consultaAllRelatorio(super.getUserLogado(request)));
					
				 else 
					
					request.setAttribute("listaUser", dao.consultaAllRelatorio(super.getUserLogado(request),dataInicial,dataFinal));
				
				
				request.setAttribute("dataIni", dataInicial);
				request.setAttribute("dataF", dataFinal);
				request.getRequestDispatcher("principal/rel-user.jsp").forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioPDF") || acao.equalsIgnoreCase("imprimirRelatorioExcel")) {
				
				String dataInicial = request.getParameter("dataIni");
				String dataFinal = request.getParameter("dataF");
				
				List<ModelLogin> ml = null;
				
				if(dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty())
					
					ml = dao.consultaAllRelatorio(super.getUserLogado(request));
					
				 else 
					
					ml = dao.consultaAllRelatorio(super.getUserLogado(request),dataInicial,dataFinal);
	
				HashMap<String, Object> params = new HashMap<String, Object>();
				
				params.put("PARAM_SUB_REPOR", request.getServletContext().getRealPath("relatorio") + File.separator);
				 
				byte[] relatorio = null;
				
				String extensao = "";
				
				//sem sub relatorio
				//relatorio = new ReportUtil().geraRelatorioPDF(ml,"rel-user-jsp", request.getServletContext());
				
				//com sub relatorio
				if(acao.equalsIgnoreCase("imprimirRelatorioPDF")) {
					
					relatorio = new ReportUtil().geraRelatorioPDF(ml,"rel-user-jsp",params, request.getServletContext());
					
					extensao = "pdf";
					
				} else
					
					if(acao.equalsIgnoreCase("imprimirRelatorioExcel")) {
						
						relatorio = new ReportUtil().geraRelatorioExcel(ml,"rel-user-jsp",params, request.getServletContext());
						
						extensao = "xls";
						
					}
				
				response.setHeader("Content-Disposition", "attachment;filename=arquivo." + extensao);
				response.getOutputStream().write(relatorio);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("grafico")) {

                String dataInicial = request.getParameter("dataIni");
                String dataFinal = request.getParameter("dataF");

                if(dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {

                    DTOGraficoSalarioUser dto = dao.montarGraficoMediaSalario(super.getUserLogado(request));

                    ObjectMapper mapper = new ObjectMapper();

                    String json = mapper.writeValueAsString(dto);

                    response.getWriter().write(json);

                }

                else{



                }

            } else {
				
                List<ModelLogin> users = dao.consultaAll(super.getUserLogado(request));
                request.setAttribute("mLogins",users);
					
                request.setAttribute("totalPagina", dao.totalPagina(this.getUserLogado(request)));
					
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			RequestDispatcher redir = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redir.forward(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				String cep = request.getParameter("cep");
				String endereco = request.getParameter("endereco");
				String bairro = request.getParameter("bairro");
				String cidade = request.getParameter("localidade");
				String estado = request.getParameter("uf");
				String complemento = request.getParameter("complemento");
				String numero = request.getParameter("numero");
				String dtnsc = request.getParameter("dtnsc");
				String renda = request.getParameter("renda");
				
				renda = renda.split("\\ ")[1].replaceAll("\\.","").replaceAll("\\,", ".");
				
				ModelLogin mLogin = new ModelLogin();
				
				mLogin.setEmail(email);
				mLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
				mLogin.setNome(nome);
				mLogin.setPass(pass);
				mLogin.setUser(user);
				mLogin.setPerfil(perfil);
				mLogin.setSexo(sexo);
				mLogin.setBairro(bairro);
				mLogin.setCep(cep);
				mLogin.setLogradouro(endereco);
				mLogin.setLocalidade(cidade);
				mLogin.setUf(estado);
				mLogin.setComplemento(complemento);
				mLogin.setNumero(numero);
				mLogin.setDtnsc(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd-mm-yyyy").parse(dtnsc))));
				mLogin.setRenda(Double.parseDouble(renda));
				
				
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
				
				request.setAttribute("totalPagina", dao.totalPagina(this.getUserLogado(request)));
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			RequestDispatcher redir = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redir.forward(request, response);
		}
			
	}

}
