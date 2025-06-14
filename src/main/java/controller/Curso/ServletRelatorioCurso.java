package controller.Curso;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Curso.RelatorioCursoDTO;
import dao.CursoDao;

@WebServlet("/relatorioCursos")
public class ServletRelatorioCurso extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String next = request.getParameter("next");
        String query = request.getParameter("q");
        
        
        CursoDao dao = new CursoDao();
        List<RelatorioCursoDTO> lista = new ArrayList<>();

        try {
            lista = dao.relatorioCurso(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("relatorioC", lista);
        RequestDispatcher rd = request.getRequestDispatcher(next);
        rd.forward(request, response);
    }
}
