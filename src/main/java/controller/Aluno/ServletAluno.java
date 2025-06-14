package controller.Aluno;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Aluno.Aluno;
import model.Curso.Curso;
import dao.AlunoDao;

@WebServlet("/alunos")
public class ServletAluno extends HttpServlet {

	@Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");
		
		if (method == null || method.isEmpty()) {
        method = request.getMethod();
    	}

        switch (method) {
            case "GET":
                doGet(request, response);
                break;
            case "POST":
                doPost(request, response);
                break;
            case "DELETE":
                doDelete(request, response);
                break;
            case "PUT":
                doPut(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Método não suportado: " + method);
        }
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String query = request.getParameter("q");
		String next = request.getParameter("next");
		AlunoDao aluDao = new AlunoDao();
		List<Aluno> alunos = null;

		try {
			alunos = aluDao.listarUm(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("Alunos", alunos);
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		int idCurso = Integer.parseInt(request.getParameter("idCurso"));

		String data_ingresso_str = request.getParameter("data_ingresso");
		LocalDate data_ingresso = LocalDate.parse(data_ingresso_str);

		Curso curso = new Curso();
		curso.setId(idCurso);

		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setDataIngresso(data_ingresso);
		aluno.setCurso(curso);

		AlunoDao aluDao = new AlunoDao();

		try {
			aluDao.inserir(aluno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	    
	@Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String next = request.getParameter("next");

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);

            AlunoDao aluDao = new AlunoDao();
			
            try {
                aluDao.excluir(id);
                response.sendRedirect(next);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir aluno.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do aluno não fornecido.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            String nome = request.getParameter("nome");
            int idCurso = Integer.parseInt(request.getParameter("idCurso"));
            String data_ingresso_str = request.getParameter("data_ingresso");
            
            LocalDate data_ingresso = LocalDate.parse(data_ingresso_str);
            
            Curso curso = new Curso();
            curso.setId(idCurso);
            
            Aluno aluno = new Aluno();
            aluno.setId(id);
            aluno.setNome(nome);
            aluno.setDataIngresso(data_ingresso);
            aluno.setCurso(curso);
            
            AlunoDao aluDao = new AlunoDao();
            
            try {
                aluDao.alterar(aluno);
                response.sendRedirect("/gestor_escolar/relatorioAlunos");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar aluno.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do aluno não fornecido.");
        }
    }


}
