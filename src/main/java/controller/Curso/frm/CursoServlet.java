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

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo(); // exemplo: /123 ou null
        String query = request.getParameter("q");
        String json;

        // Configura a resposta HTTP e enviar o Json
        PrintWriter out = response.getWriter();

        // Verificando o tipo de requisição (ID ou Nome)
        switch (getRequestType(pathInfo, query)) {
            case "ID":
                String id = pathInfo.substring(1); // remove a barra "/"
                if (isNumeric(id)) {
                    int id2 = Integer.valueOf(id);
                    System.out.println(id);
                    json = buscarUm(id2);
                    out.print(json);
                    out.flush();
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
                    return;
                }
                break;

            case "NOME":
                if (query != null && !query.isEmpty()) {
                    json = buscarVarios(query);
                    out.print(json);
                    out.flush();
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro 'q' ausente ou vazio");
                    return;
                }
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Rota ou parâmetros inválidos");
                return;
        }
    }

    // Método para identificar o tipo de requisição (ID ou Nome)
    private String getRequestType(String pathInfo, String query) {
        if (pathInfo != null && !pathInfo.equals("/") && isNumeric(pathInfo.substring(1))) {
            return "ID"; // Detecção de ID no path
        } else if (query != null && !query.isEmpty()) {
            return "NOME"; // Detecção de parâmetro 'q'
        }
        return "INVALIDO";
    }

    // Método que busca um Curso por ID
    private String buscarUm(int id) {
        CursoDao curDao = new CursoDao();
        Curso Curso = null;
        try {
            Curso = curDao.listar(id); // Passa o ID para o método listarUm
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(Curso);
        return json;
    }

    // Método que busca Cursos por nome (filtro)
    private String buscarVarios(String query) {
        CursoDao curDao = new CursoDao();
        List<Curso> cursos = null;
        try {
            cursos = curDao.listarUm(query); // Método para listar Cursos com o nome passado
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(cursos);
        return json;
    }

    // Verifica se a string é um número inteiro
    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String pathInfo = request.getPathInfo(); // exemplo: /123 ou null
        String idString = pathInfo.substring(1); // remove a barra "/"
        int id;
        if (isNumeric(idString))
            id = Integer.valueOf(idString);
        else {
            response.sendError(400, "Parâmetro 'ID' não numerérico");
            return;
        }
        CursoDao cDao = new CursoDao();
        try {
            cDao.excluir(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Lê o JSON do corpo da requisição
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        Curso Curso = gson.fromJson(reader, Curso.class); 
        // Supõe que o Curso já tenha os atributos id, nome, duracaoSemetre, etc.

        // Atualiza o Curso no banco
        CursoDao cDao = new CursoDao();
        try {
            cDao.alterar(Curso);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
