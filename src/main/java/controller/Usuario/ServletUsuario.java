package controller.Usuario;

import java.io.IOException;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import dao.UsuarioDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario.Usuario;

@WebServlet("/usuario")
public class ServletUsuario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extrair do request
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senhaTxt = request.getParameter("senha");
        String corDeFundo = request.getParameter("corDeFundo");
        String corDeFonte = request.getParameter("corDeFonte");

        // Gerar senha Hash
        String senhaHash = BCrypt.hashpw(senhaTxt, BCrypt.gensalt());

        // Salvar no BD
        Usuario usuario = new Usuario(nome, login, senhaHash, corDeFundo, corDeFonte);
        UsuarioDao usuarioDao = new UsuarioDao();

        try{
            usuarioDao.inserir(usuario);
            response.sendRedirect("index.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    
}
