package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario.Usuario;

public class UsuarioDao {
    private Connection con;
	
	public UsuarioDao() {
		con = ConnectionFactory.getConnection();
	}
	
	public void inserir(Usuario usuario) throws SQLException {
		String sql = "insert into usuario(nome,login, senha, cor_de_fundo, cor_de_fonte) values(?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1,usuario.getNome());
        stmt.setString(2,usuario.getLogin());
		stmt.setString(3,usuario.getSenha());
        stmt.setString(4,usuario.getCorDeFundo());
        stmt.setString(5,usuario.getCorDeFonte());
		stmt.execute();
		stmt.close();
		con.close();
    }

    public Usuario buscarPorLogin(String login) throws SQLException {

        String sql = "SELECT * FROM Usuario WHERE login = ?";
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, login);

        Usuario usuario = null;
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha")); 
            usuario.setCorDeFundo(rs.getString("cor_de_fundo"));
            usuario.setCorDeFonte(rs.getString("cor_de_fonte"));
            return usuario;
        }

        return null;
    }

}
