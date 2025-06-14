package controller.Aluno;

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
import model.Aluno.RelatorioAlunoDTO;
import dao.AlunoDao;


@WebServlet("/relatorioAlunos")
public class ServletRelatorioAluno extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String next = request.getParameter("next");
        String query = request.getParameter("q");

        String cursoIdStr = request.getParameter("idCurso");

        Integer cursoId = (cursoIdStr != null && !cursoIdStr.isEmpty()) ? Integer.parseInt(cursoIdStr) : null;
        
        AlunoDao aluDao = new AlunoDao();
        List<RelatorioAlunoDTO> lista = new ArrayList<>();

        try {
            lista = aluDao.relatorioAluno(query, cursoId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        request.setAttribute("relatorioA", lista);
        request.setAttribute("q", query);
        request.setAttribute("idCurso", cursoIdStr);

        RequestDispatcher rd = request.getRequestDispatcher(next);
        rd.forward(request, response);
    }


}
