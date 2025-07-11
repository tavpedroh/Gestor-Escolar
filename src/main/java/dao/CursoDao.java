package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Curso.Curso;
import model.Curso.RelatorioCursoDTO;

public class CursoDao {
    private Connection con;
    
    public CursoDao() {
        con = ConnectionFactory.getConnection();
    }

    public void inserir(Curso curso) throws SQLException {

		String sql = " insert into Curso (nome, duracao_semestres) values (?, ?) ";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1,curso.getNome());
		stmt.setInt(2,curso.getDuracaoSemestres());

		stmt.execute();
		stmt.close();
		con.close();
	}


	public void alterar(Curso curso) throws SQLException {

		String sql = " update Curso set nome = ?, duracao_semestres = ? where id = ? ";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, curso.getNome());
		stmt.setInt(2, curso.getDuracaoSemestres());
		stmt.setInt(3, curso.getId());

		stmt.execute();
		stmt.close();
		con.close();
	}


	public void excluir(int id) throws SQLException {

		String sql = " delete from Curso where id = ? ";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setInt(1, id);

		stmt.execute();
		stmt.close();
		con.close();
	}


	public List<Curso> listar(String query) throws SQLException {
		StringBuilder sql = new StringBuilder(" select id, nome, duracao_semestres from Curso where 1=1 ");
		if (query != null && !query.isEmpty()) {
			sql.append(" AND nome like ? ");
		}
		PreparedStatement stmt = con.prepareStatement(sql.toString());
		
		if (query != null && !query.isEmpty()) {
			stmt.setString(1, "%" + query + "%");
		}
		
		ResultSet rs = stmt.executeQuery();
		
		List<Curso> cursos = new ArrayList<Curso>();
		
		while (rs.next()) {
			Curso curso = new Curso();
			curso.setId(rs.getInt("id"));
			curso.setNome(rs.getString("nome"));
			curso.setDuracaoSemestres(rs.getInt("duracao_semestres"));
			cursos.add(curso);
		}
		
		stmt.close();
		con.close();
		
		return cursos;
	}

	public Curso listarUm(int id) throws SQLException {
		String sql = " select id, nome, duracao_semestres from Curso where id = ? ";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		Curso curso = null;

		if (rs.next()) {
			curso = new Curso();
			curso.setId(rs.getInt("id"));
			curso.setNome(rs.getString("nome"));
			curso.setDuracaoSemestres(rs.getInt("duracao_semestres"));
		}
		stmt.close();
		con.close();
		return curso;
	}

    public List<Curso> listarTodos() throws SQLException{
		String sql = " select id, nome from Curso ";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Curso> cursos = new ArrayList<Curso>();
		Curso curso = null;

		while (rs.next()) {
			curso = new Curso();
			curso.setId(rs.getInt("id"));
			curso.setNome(rs.getString("nome"));
			//curso.setDuracaoSemestres(rs.getInt("duracao_semestres"));
			cursos.add(curso);
		}
		stmt.close();
		con.close();
		return cursos;
	}

	public List<Curso> listarComFiltros(String filtroNome) throws SQLException {
		List<Curso> lista = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder(
			" SELECT id, nome, duracao_semestres FROM Curso WHERE 1=1 " 
		);

		if (filtroNome != null && !filtroNome.trim().isEmpty()) {
			sql.append(" AND nome LIKE ? ");
		}

		PreparedStatement stmt = con.prepareStatement(sql.toString());
		
		int paramIndex = 1;

		if (filtroNome != null && !filtroNome.trim().isEmpty()) {
			stmt.setString(paramIndex++, "%" + filtroNome + "%");
		}
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Curso curso = new Curso();
			curso.setId(rs.getInt("id"));
			curso.setNome(rs.getString("nome"));
			curso.setDuracaoSemestres(rs.getInt("duracao_semestres"));

			lista.add(curso);
		}
		
		rs.close();
		stmt.close();
		return lista;
	}


	public List<RelatorioCursoDTO> relatorioCurso(String query) throws SQLException {
		List<RelatorioCursoDTO> lista = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder(
			" SELECT c.id AS id_curso, c.nome AS nome_curso, COUNT(a.id) AS quantidade_aluno, COUNT(d.id) AS quantidade_disciplina " +
			" FROM Curso c LEFT JOIN Aluno a ON a.curso_id = c.id LEFT JOIN Disciplina d ON d.curso_id = c.id WHERE 1=1 "
		);
		
		boolean temQuery = query != null && !query.isEmpty();

		if (temQuery) {
			int tamanho = query.length();
			sql.append(" AND substring(c.nome,1,").append(tamanho).append(") = ? ");
		}

		sql.append(" GROUP BY c.id");

		PreparedStatement stmt = con.prepareStatement(sql.toString());

		if (temQuery) {
			stmt.setString(1, query);
		}

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			lista.add(new RelatorioCursoDTO(
				rs.getInt("id_curso"),
				rs.getString("nome_curso"),
				rs.getInt("quantidade_aluno"),
				rs.getInt("quantidade_disciplina")
			));
		}

		stmt.close();
		con.close();
		
		return lista;
	}


	//Test

	public int inserir2(Curso curso) throws SQLException {
		String sql = " insert into Curso (nome, duracao_semestres) values (?, ?) ";

		PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

		stmt.setString(1, curso.getNome());
		stmt.setInt(2, curso.getDuracaoSemestres());

		stmt.execute();
		
		ResultSet rs = stmt.getGeneratedKeys();
		int idGerado = -1;
		if (rs.next()) {
			idGerado = rs.getInt(1);
		}
		stmt.close();
		con.close();
		
		return idGerado;
	}
}
