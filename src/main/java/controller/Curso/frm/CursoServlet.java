package controller.Curso.frm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import java.util.List;

import com.google.gson.Gson;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Curso.Curso;
import dao.CursoDao;


@WebServlet("/curso/*")
public class CursoServlet extends HttpServlet {
        
    public CursoServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String filtro = request.getParameter("filtro");

        List<Curso> lista;
        try {
            lista = new CursoDao().listarUm(filtro);
            Gson gson = new Gson();
            String json = gson.toJson(lista);

            response.setContentType("application/json");
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Erro ao listar");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao ler o corpo da requisição");
            return;
        }

        String json = sb.toString();
        Curso curso = new Gson().fromJson(json, Curso.class);

        try {
            CursoDao cursoDao = new CursoDao();
            cursoDao.inserir(curso);
            response.setStatus(HttpServletResponse.SC_CREATED);
            PrintWriter out = response.getWriter();
            out.print(new Gson().toJson(curso));
            out.flush();
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao inserir curso: " + e.getMessage());
        }
    }


}
