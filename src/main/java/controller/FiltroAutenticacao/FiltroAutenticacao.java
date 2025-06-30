package controller.FiltroAutenticacao;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.annotation.WebFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Usuario.Usuario;

import java.io.IOException;


@WebFilter("/*")
public class FiltroAutenticacao implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String metodo = req.getMethod();
        HttpSession session = req.getSession(false);

        Usuario usuarioLogado = (session != null) ? (Usuario) session.getAttribute("usuarioLogado") : null;
        boolean logado = (usuarioLogado != null);

        boolean acessoLivre =
            uri.endsWith(".js") ||
            uri.endsWith(".css") ||
            uri.endsWith("index.jsp") ||
            uri.endsWith("/login") ||
            uri.endsWith("/Usuario/cadastroUsuario.jsp") ||
            (uri.endsWith("/usuario") && metodo.equalsIgnoreCase("POST"));

        if (logado || acessoLivre) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("/gestor_escolar/index.jsp");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}


