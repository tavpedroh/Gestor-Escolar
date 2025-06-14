package controller.Curso;

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
import dao.CursoDao;

@WebServlet("/cursos")
public class ServletCurso extends HttpServlet {

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
		CursoDao curDao = new CursoDao();
		List<Curso> cursos = null;

		try {
			cursos = curDao.listarUm(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("cursos", cursos);
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nome = request.getParameter("nome");
		int duracao_semetres = Integer.parseInt(request.getParameter("duracao_semetres"));

		Curso curso = new Curso();
		curso.setNome(nome);
		curso.setDuracaoSemetre(duracao_semetres);

		CursoDao curDao = new CursoDao();

		try {
			curDao.inserir(curso);
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
			CursoDao curDao = new CursoDao();

			try {
				curDao.excluir(id);
				response.sendRedirect(next);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do curso não fornecido.");
		}
	}
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String next = request.getParameter("next");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
			String nome = request.getParameter("nome");
			int duracao_semetres = Integer.parseInt(request.getParameter("duracao_semetres"));

			CursoDao curDao = new CursoDao();
			Curso curso = new Curso();

			curso.setId(id);
			curso.setNome(nome);
			curso.setDuracaoSemetre(duracao_semetres);

			try {
					curDao.alterar(curso);
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
