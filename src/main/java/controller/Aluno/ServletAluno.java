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
import dao.CursoDao;

@WebServlet("/alunos")
public class ServletAluno extends HttpServlet {

	@Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");
		
		if (method == null || method.isEmpty()) {
        method = request.getMethod();
    	}

        switch (method.toUpperCase()) {
            case "GET":
                doGet(request, response);
                break;
            case "POST":
                doPost(request, response);
                break;
            case "DELETE":
                doDelete(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Método não suportado: " + method);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "listar"; // Ação padrão
        }

        try {
            switch (acao) {
                case "exibirFormularioCadastro":
                    exibirFormulario(request, response);
                    break;
                case "listar":
                default:
                    listarAlunos(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Erro de banco de dados no GET", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        int idCurso = Integer.parseInt(request.getParameter("idCurso"));
        LocalDate data_ingresso = LocalDate.parse(request.getParameter("data_ingresso"));

        Curso curso = new Curso();
        curso.setId(idCurso);
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setDataIngresso(data_ingresso);
        aluno.setCurso(curso);

        AlunoDao aluDao = new AlunoDao();
        try {
            aluDao.inserir(aluno);
            
            response.sendRedirect(request.getContextPath() + "/alunos?acao=listar&sucesso=true");
        } catch (SQLException e) {
            throw new ServletException("Erro ao inserir aluno.", e);
        }
    }
    

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            AlunoDao aluDao = new AlunoDao();
            aluDao.excluir(id);
            
            response.sendRedirect(request.getContextPath() + "/alunos?acao=listar&excluido=true");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do aluno inválido.");
        } catch (SQLException e) {
            throw new ServletException("Erro ao excluir aluno.", e);
        }
    }

    // --- MÉTODOS PARA ORGANIZAR O CÓDIGO ---

    private void listarAlunos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String filtroNome = request.getParameter("q");
        String filtroIdCurso = request.getParameter("idCurso");

        AlunoDao aluDao = new AlunoDao();
        List<Aluno> alunos = aluDao.listarComFiltros(filtroNome, filtroIdCurso); 

        request.setAttribute("listaDeAlunos", alunos);

        CursoDao cursoDao = new CursoDao();
        List<Curso> cursosParaSelect = cursoDao.listarTodos();
        request.setAttribute("cursosParaOSelect", cursosParaSelect);
        
        RequestDispatcher rd = request.getRequestDispatcher("Aluno/relatorioAluno.jsp");
        rd.forward(request, response);
    }
    
    private void exibirFormulario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        CursoDao cursoDao = new CursoDao();
        List<Curso> listaCursos = cursoDao.listarTodos();

        request.setAttribute("cursosParaOSelect", listaCursos);
        
        RequestDispatcher rd = request.getRequestDispatcher("Aluno/cadastroAluno.jsp");
        rd.forward(request, response);
    }

    
}


/*
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
 */