package controller.Login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import dao.UsuarioDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Usuario.Usuario;

@WebServlet("/login")
public class ServletLogin extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String senhaTxt = request.getParameter("senha");
        
        UsuarioDao usuDao = new UsuarioDao();
        Usuario usuario =  null;

        try {
            usuario = usuDao.buscarPorLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (usuario != null && BCrypt.checkpw(senhaTxt, usuario.getSenha())) {
            
            String tokenId = UUID.randomUUID().toString();
            request.getSession().setAttribute("tokenId", tokenId);
            usuario.setSenha(null);

            
            Cookie cookieFundo = new Cookie("corFundo", usuario.getCorDeFundo());
            Cookie cookieFonte = new Cookie("corFonte", usuario.getCorDeFonte());

            cookieFundo.setMaxAge(60 * 60);
            cookieFonte.setMaxAge(60 * 60);

            response.addCookie(cookieFundo);
            response.addCookie(cookieFonte);

            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);

            response.sendRedirect(request.getContextPath() + "/menu.jsp");
        } else {
            response.sendRedirect("index.jsp?erro=1");
        }

    } 
}