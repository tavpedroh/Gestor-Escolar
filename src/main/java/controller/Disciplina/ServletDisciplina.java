package controller.Disciplina;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Curso.Curso;
import model.Disciplina.Disciplina;
import dao.DisciplinaDao;


@WebServlet("/disciplinas")
public class ServletDisciplina extends HttpServlet {

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
		DisciplinaDao disDao = new DisciplinaDao();
		List<Disciplina> disciplinas = null;

		try {
			disciplinas = disDao.listarUm(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("disciplinas", disciplinas);
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		int carga_horaria = Integer.parseInt(request.getParameter("carga_horaria"));
		int idCurso = Integer.parseInt(request.getParameter("idCurso"));
		
		Curso curso = new Curso();
		curso.setId(idCurso);

		Disciplina disciplina = new Disciplina();
		disciplina.setNome(nome);
		disciplina.setCargaHoraria(carga_horaria);
		disciplina.setCurso(curso);

		DisciplinaDao disDao = new DisciplinaDao();

		try {
			disDao.inserir(disciplina);
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
			DisciplinaDao disDao = new DisciplinaDao();

			try {
				disDao.excluir(id);
				response.sendRedirect(next);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do disciplina não fornecido.");
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String next = request.getParameter("next");
        
		if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
			String nome = request.getParameter("nome");
			int duracao_semetres = Integer.parseInt(request.getParameter("carga_horaria"));
			int idCurso = Integer.parseInt(request.getParameter("idCurso"));

			DisciplinaDao disDao = new DisciplinaDao();
			Disciplina disciplina = new Disciplina();

			disciplina.setId(id);
			disciplina.setNome(nome);
			disciplina.setCargaHoraria(duracao_semetres);

			Curso curso = new Curso();
			curso.setId(idCurso);

			disciplina.setCurso(curso);

			try {
				disDao.alterar(disciplina);
				response.sendRedirect(next);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar curso.");
			}
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do curso não fornecido.");
		}
	}
}
