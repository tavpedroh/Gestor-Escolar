package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Aluno.Aluno;
import model.Aluno.RelatorioAlunoDTO;
import model.Curso.Curso;


public class AlunoDao {
        private Connection con;
    
    public AlunoDao() {
        con = ConnectionFactory.getConnection();
    }

	// CRUD

    public void inserir(Aluno aluno) throws SQLException {
		String sql = " insert into Aluno(nome, data_ingresso, curso_id) values(?, ?, ?) ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1,aluno.getNome());
		stmt.setDate(2, java.sql.Date.valueOf(aluno.getDataIngresso()));
		stmt.setInt(3, aluno.getCurso().getId());
		stmt.execute();
		stmt.close();
		con.close();
	}


	public void alterar(Aluno aluno) throws SQLException {
		String sql = " update Aluno set nome = ?, data_ingresso = ?, curso_id = ? where id = ? ";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, aluno.getNome());
		stmt.setDate(2, java.sql.Date.valueOf(aluno.getDataIngresso()));
		stmt.setInt(3, aluno.getCurso().getId());
		stmt.setInt(4, aluno.getId());
		
		stmt.execute();
		stmt.close();
		con.close();
	}


	public void excluir(int id) throws SQLException {
		
		String sql = " delete from Aluno where id = ? ";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, id);
		
		stmt.execute();
		stmt.close();

		con.close();
	}


	public Aluno buscarPorId(int id) throws SQLException {
		String sql = "SELECT id, nome, data_ingresso, curso_id FROM Aluno WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			Aluno aluno = new Aluno();
			aluno.setId(rs.getInt("id"));
			aluno.setNome(rs.getString("nome"));
			aluno.setDataIngresso(rs.getDate("data_ingresso").toLocalDate());

			Curso curso = new Curso();
			curso.setId(rs.getInt("curso_id"));
			aluno.setCurso(curso);

			stmt.close();
			return aluno;
		}

		stmt.close();
		return null;
	}


	// Listar
	public List<Aluno> listarUm(String query) throws SQLException {
		String sql = " select id, nome, data_ingresso, curso_id from Aluno ";
		if (query != null && !query.isEmpty()) {
			sql += " where nome like ? ";
		}
		PreparedStatement stmt = con.prepareStatement(sql);
		
		if (query != null && !query.isEmpty()) {
			stmt.setString(1, "%" + query + "%");
		}
		
		ResultSet rs = stmt.executeQuery();
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno aluno = null;
		Curso curso = new Curso();

		while (rs.next()) {
			aluno = new Aluno();
			
			aluno.setId(rs.getInt("id"));
			aluno.setNome(rs.getString("nome"));

			java.sql.Date sqlDate = rs.getDate("data_ingresso");
			aluno.setDataIngresso(sqlDate.toLocalDate());

			curso.setId(rs.getInt("curso_id"));
			aluno.setCurso(curso);

			alunos.add(aluno);
		}
		stmt.close();
		con.close();
		return alunos;
	}

	public Aluno listar(Integer id) throws SQLException {
		String sql = " select id, nome, data_ingresso, curso_id from Aluno where id = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		CursoDao curDao = new CursoDao();
		Aluno aluno = null;
		if (rs.next()) {
			aluno = new Aluno();
			aluno.setId(rs.getInt("id"));
			aluno.setNome(rs.getString("nome"));

			java.sql.Date sqlDate = rs.getDate("data_ingresso");
			aluno.setDataIngresso(sqlDate.toLocalDate());
			
			aluno.setCurso(curDao.listar(rs.getInt("curso_id")));
		}
		stmt.close();
		con.close();
		return aluno;
	}

    public List<Aluno> listarTodos() throws SQLException{
		String sql = " select a.id as aluno_id,\n" + //
						"    a.nome as aluno_nome,\n" + //
						"    a.data_ingresso,\n" + //
						"    c.id as curso_id,\n" + //
						"    c.nome as curso_nome from Aluno a join Curso c ON a.curso_id = c.id where 1=1";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Aluno> alunos = new ArrayList<Aluno>();

		while (rs.next()) {
			Aluno aluno = new Aluno();
			aluno.setId(rs.getInt("aluno_id"));
			aluno.setNome(rs.getString("aluno_nome"));
			aluno.setDataIngresso(rs.getDate("data_ingresso").toLocalDate());

			Curso curso = new Curso();
			curso.setId(rs.getInt("curso_id"));
			curso.setNome(rs.getString("curso_nome"));

			aluno.setCurso(curso);

			alunos.add(aluno);
		}

		stmt.close();
		con.close();
		return alunos;
	}

	
	public List<Aluno> listarComFiltros(String filtroNome, String filtroIdCurso) throws SQLException {
		List<Aluno> lista = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder(
			"SELECT a.id as aluno_id, a.nome as aluno_nome, a.data_ingresso, " +
			"c.id as curso_id, c.nome as curso_nome " +
			"FROM Aluno a JOIN Curso c ON a.curso_id = c.id WHERE 1=1 "
		);

		if (filtroNome != null && !filtroNome.trim().isEmpty()) {
			sql.append(" AND a.nome LIKE ? ");
		}

		if (filtroIdCurso != null && !filtroIdCurso.trim().isEmpty()) {
			sql.append(" AND c.id = ? ");
		}

		PreparedStatement stmt = con.prepareStatement(sql.toString());
		
		int paramIndex = 1;

		if (filtroNome != null && !filtroNome.trim().isEmpty()) {
			stmt.setString(paramIndex++, "%" + filtroNome + "%");
		}
		if (filtroIdCurso != null && !filtroIdCurso.trim().isEmpty()) {
			stmt.setInt(paramIndex++, Integer.parseInt(filtroIdCurso));
		}
		
		ResultSet rs = stmt.executeQuery();
		

		while(rs.next()){
			Aluno aluno = new Aluno();
			aluno.setId(rs.getInt("aluno_id"));
			aluno.setNome(rs.getString("aluno_nome"));
			aluno.setDataIngresso(rs.getDate("data_ingresso").toLocalDate());

			Curso curso = new Curso();
			curso.setId(rs.getInt("curso_id"));
			curso.setNome(rs.getString("curso_nome"));

			aluno.setCurso(curso);

			lista.add(aluno);
		}
		
		rs.close();
		stmt.close();
		return lista;
	}

	
	// Relatorio
	public List<RelatorioAlunoDTO> relatorioAluno(String query, Integer cursoId) throws SQLException{
		List<RelatorioAlunoDTO> lista = new ArrayList<>();

		StringBuilder sql = new StringBuilder(
			"SELECT a.id AS id, a.nome AS nome_aluno, c.nome AS nome_curso " +
			"FROM Curso c JOIN Aluno a ON (a.curso_id = c.id) WHERE 1=1 "
		);
		if (query != null && !query.isEmpty()) sql.append(" AND a.nome LIKE ? ");

		if (cursoId != null) sql.append(" AND c.id = ? ");

		sql.append(" ORDER BY a.nome ");

		PreparedStatement stmt = con.prepareStatement(sql.toString());

		int index = 1;
		if (query != null && !query.isEmpty()) stmt.setString(index++, "%" + query + "%");

		if (cursoId != null) stmt.setInt(index++, cursoId);

		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			lista.add(new RelatorioAlunoDTO(
				rs.getInt("id"),
				rs.getString("nome_aluno"),
				rs.getString("nome_curso")
			));
		}

		stmt.close();
		con.close();
		
		return lista;	
	}


}
