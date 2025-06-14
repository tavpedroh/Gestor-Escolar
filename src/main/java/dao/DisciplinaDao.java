package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Curso.Curso;
import model.Disciplina.Disciplina;
import model.Disciplina.RelatorioDisciplinaDTO;

public class DisciplinaDao {
    private Connection con;
    
    public DisciplinaDao() {
        con = ConnectionFactory.getConnection();
    }

    public void inserir(Disciplina disciplina) throws SQLException {
		String sql = " insert into Disciplina(nome, carga_horaria, curso_id) values(?,?,?) ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1,disciplina.getNome());
		stmt.setInt(2,disciplina.getCargaHoraria());
		stmt.setInt(3, disciplina.getCurso().getId());
		stmt.execute();
		stmt.close();
		con.close();
	}


	public void alterar(Disciplina disciplina) throws SQLException {
		String sql = " update Disciplina set nome = ?, carga_horaria = ?, curso_id = ? where id = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, disciplina.getNome());
		stmt.setInt(2, disciplina.getCargaHoraria());
		stmt.setInt(3, disciplina.getCurso().getId());
		stmt.setInt(4, disciplina.getId());
		stmt.execute();
		stmt.close();
		con.close();
	}


	public void excluir(int id) throws SQLException {
		String sql = " delete from Disciplina where id = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		con.close();
	}

	
	public List<Disciplina> listarUm(String query) throws SQLException {
		String sql = " select id, nome, carga_horaria, curso_id from Disciplina ";
		if (query != null && !query.isEmpty()) {
			sql += " where nome like ? ";
		}
		PreparedStatement stmt = con.prepareStatement(sql);
		
		if (query != null && !query.isEmpty()) {
			stmt.setString(1, "%" + query + "%");
		}
		
		ResultSet rs = stmt.executeQuery();
		
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		Disciplina disciplina = null;
		
		while (rs.next()) {
			disciplina = new Disciplina();
			disciplina.setId(rs.getInt("id"));
			disciplina.setNome(rs.getString("nome"));
			disciplina.setCargaHoraria(rs.getInt("carga_horaria"));

			Curso curso = new Curso();
			curso.setId(rs.getInt("curso_id"));
			disciplina.setCurso(curso);

			disciplinas.add(disciplina);
		}
		
		stmt.close();
		con.close();
		
		return disciplinas;
	}

	public Disciplina listar (int id) throws SQLException {
		String sql = " select id, nome, carga_horaria, curso_id from Disciplina where id = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		Disciplina disciplina = null;

		if (rs.next()) {
			disciplina = new Disciplina();
			disciplina.setId(rs.getInt("id"));
			disciplina.setNome(rs.getString("nome"));
			disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
			
			Curso curso = new Curso();
			curso.setId(rs.getInt("curso_id"));
			disciplina.setCurso(curso);
		}
		stmt.close();
		con.close();
		return disciplina;
	}

    public List<Disciplina> listarTodos() throws SQLException{
		String sql = " select id, nome from Disciplina ";
		PreparedStatement stmt = con.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();

		Disciplina disciplina = null;
		while (rs.next()) {
			disciplina = new Disciplina();
			disciplina.setId(rs.getInt("id"));
			disciplina.setNome(rs.getString("nome"));
			disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
			disciplina.getCurso().setId(rs.getInt("curso_id"));
			disciplinas.add(disciplina);
		}
		stmt.close();
		con.close();
		return disciplinas;
	}


	public List<RelatorioDisciplinaDTO> relatorioDisciplina(Integer cursoId, String query) throws SQLException {
		List<RelatorioDisciplinaDTO> lista = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder(
			" SELECT d.id AS id_disciplina, d.nome AS nome_disciplina, c.nome AS nome_curso " +
			" FROM Curso c JOIN Disciplina d ON (d.curso_id = c.id) WHERE 1=1 "
		);

		if (cursoId != null) sql.append(" AND c.id = ? ");
		if (query != null) sql.append(" AND d.nome like ? ");

		sql.append(" GROUP BY d.nome");
		PreparedStatement stmt = con.prepareStatement(sql.toString());

		int index = 1;
		if (cursoId != null) stmt.setInt(index++, cursoId);
		if (query != null) stmt.setString(index++, "%" + query + "%");


		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			lista.add(new RelatorioDisciplinaDTO(
				rs.getInt("id_disciplina"),
				rs.getString("nome_disciplina"),
				rs.getString("nome_curso")
			));
		}

		stmt.close();
		con.close();
		
		return lista;
	}

}
