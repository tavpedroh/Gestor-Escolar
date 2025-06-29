package controller.Curso.Json;

import java.io.BufferedReader;
import java.io.IOException;

import java.sql.SQLException;

import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Curso.Curso;
import dao.CursoDao;


@WebServlet("/cursos-json")
public class CursoServlet extends HttpServlet {

    // Inserção via JSON (POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BufferedReader reader = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();

        String linha;
        while ((linha = reader.readLine()) != null) {
            jsonBuilder.append(linha);
        }

        String json = jsonBuilder.toString();
        Gson gson = new Gson();

        Curso curso = gson.fromJson(json, Curso.class);
        CursoDao curDao = new CursoDao();

        try {
            curDao.inserir(curso);
            response.setContentType("text/plain");
            response.getWriter().write("Curso inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao inserir curso.");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String q = request.getParameter("q");

        CursoDao curDao = new CursoDao();
        List<Curso> cursos;

        try {
            
            cursos = curDao.listar(q);

            Gson gson = new Gson();
            String json = gson.toJson(cursos);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar cursos.");
        }
    }


}

