package controller.Disciplina;

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

import model.Disciplina.RelatorioDisciplinaDTO;
import dao.DisciplinaDao;


@WebServlet("/relatorioDisciplinas")
public class ServletRelatorioDisciplina extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String next = request.getParameter("next");
        String query = request.getParameter("q");
        
        String cursoIdStr = request.getParameter("cursoId");
        Integer cursoId = (cursoIdStr != null && !cursoIdStr.isEmpty()) ? Integer.parseInt(cursoIdStr) : null;
        
        DisciplinaDao disDao = new DisciplinaDao();
        List<RelatorioDisciplinaDTO> lista = new ArrayList<>();

        try {
            lista = disDao.relatorioDisciplina(cursoId, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("relatorioD", lista);
        RequestDispatcher rd = request.getRequestDispatcher(next);
        rd.forward(request, response);
    }
}
