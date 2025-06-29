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
                    listarCursos(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Erro de banco de dados no GET", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        int duracao_semestres = Integer.parseInt(request.getParameter("duracao_semestres"));

        Curso curso = new Curso();
        curso.setNome(nome);
        curso.setDuracaoSemestres(duracao_semestres);

        CursoDao curDao = new CursoDao();
        try {
            curDao.inserir(curso);
            
            response.sendRedirect(request.getContextPath() + "/cursos?acao=listar&sucesso=true");
        } catch (SQLException e) {
            throw new ServletException("Erro ao inserir curso.", e);
        }
    }
    

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            CursoDao curDao = new CursoDao();
            curDao.excluir(id);
            
            response.sendRedirect(request.getContextPath() + "/cursos?acao=listar&excluido=true");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do curso inválido.");
        } catch (SQLException e) {
            throw new ServletException("Erro ao excluir curso.", e);
        }
    }

    // --- MÉTODOS PARA ORGANIZAR O CÓDIGO ---

    private void listarCursos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String filtroNome = request.getParameter("q");

        CursoDao curDao = new CursoDao();
        List<Curso> cursos = curDao.listarComFiltros(filtroNome); 

        request.setAttribute("listaDeCursos", cursos);
        
        RequestDispatcher rd = request.getRequestDispatcher("Curso/relatorioCurso.jsp");
        rd.forward(request, response);
    }
    
    private void exibirFormulario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
      
        RequestDispatcher rd = request.getRequestDispatcher("Curso/cadastroCurso.jsp");
        rd.forward(request, response);
    }

	
}
