package filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*"}) // intercepta tudo q vier do mapeamento
public class FilterAutenticacao extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;


	public FilterAutenticacao() {}

	// encerra os processos quando o servidor e parado
	// mataria a conexao com o banco
	public void destroy() {
		
	}

	// intercepta as requisicoes e da as respostas no sistema
	// tudo q fizer no sistema vai fazer por aqui
	//validacao de autenticacao
	// commit e rolback no banco etc
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String userLogon = (String) session.getAttribute("user");
		
		String urlAutenticar = req.getServletPath(); // url q esta sendo acessada
		
		//validar se esta logado se n redireciona tela login
		// nao estar logado
		if(userLogon == null && !urlAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
			
			RequestDispatcher redir = request.getRequestDispatcher("/index.jsp?url=" + urlAutenticar);
			request.setAttribute("msg","Por favor realize o login");
			redir.forward(request, response);
			return; // para a execucao e redireciona para o login
		} else {
			chain.doFilter(request, response);
		}
		
	}

	//inicia os processo quando o servidor sobre o projeto
	// iniciar a conexao com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
