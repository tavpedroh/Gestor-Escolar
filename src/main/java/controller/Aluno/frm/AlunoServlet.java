package controller.Aluno.frm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import dao.AlunoDao;
import dao.CursoDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Aluno.Aluno;
import model.Curso.Curso;

@WebServlet("/aluno/*")
public class AlunoServlet extends HttpServlet{
    
    public AlunoServlet(){
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

    // Método que busca um aluno por ID
    private String buscarUm(int id) {
        AlunoDao aluDao = new AlunoDao();
        Aluno aluno = null;
        try {
            aluno = aluDao.listar(id); // Passa o ID para o método listarUm
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(aluno);
        return json;
    }

    // Método que busca alunos por nome (filtro)
    private String buscarVarios(String query) {
        AlunoDao aluDao = new AlunoDao();
        List<Aluno> alunos = null;
        try {
            alunos = aluDao.listarUm(query); // Método para listar alunos com o nome passado
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(alunos);
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
        AlunoDao aDao = new AlunoDao();
        try {
            aDao.excluir(id);
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
        Aluno aluno = gson.fromJson(reader, Aluno.class); 
        // Supõe que o Aluno já tenha os atributos id, nome,
        // Busca o curso completa pelo ID recebido no JSON
        CursoDao curDao = new CursoDao();
        try {
            int idCur = aluno.getCurso().getId(); // Supondo que venha assim no JSON
            Curso curso = curDao.listar(idCur);
            aluno.setCurso(curso);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // Atualiza o aluno no banco
        AlunoDao aDao = new AlunoDao();
        try {
            aDao.alterar(aluno);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
