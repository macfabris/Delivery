package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ffsd.tcc.modelo.Usuario;


public class UsuarioDao {
	Connection conexao = null;
	String sql = null;
	PreparedStatement stmt = null;

	public UsuarioDao(Connection conexao)  {
		this.conexao = conexao;
	}

	public int adiciona(Usuario usuario) throws SQLException, IOException {

		sql = "Insert into usuario(login,senha,tipoUsuario) values(?,?,?)";
		try {
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getTipoUsuario());
			stmt.execute();
			System.out.println("Usuario adicionado com sucesso");
			
			sql = "select max(id) from usuario";
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int idUsuario = rs.getInt(1);
			return idUsuario;
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println("Não foi possivel adicionar" + e.getMessage());
			conexao.rollback();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
		return 0;
	}

	public Usuario verificaUsuario(String login) {
		sql = "SELECT id,tipoUsuario,login,senha from usuario where login = ? ";
		Usuario usuario = new Usuario();
		try {
		
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			if (rs.first()) {
				usuario.setId(rs.getInt("id"));
				usuario.setTipoUsuario(rs.getString("tipoUsuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
			}
			return usuario;
		} catch (SQLException e) {
			System.out.println("Erro ao Listar" + e.getMessage());
		} finally {
			try {
				stmt.close();
			
			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
		return null;

	}

	public void altera(Usuario usuario) throws SQLException{
		
		sql = "Update usuario set senha=? where id = ? ";
		
		try {
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getSenha());
			stmt.setInt(2, usuario.getId());
			
			stmt.execute();
			System.out.println("Usuario alterado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Não foi possível alterar : "+ e.getMessage());
			conexao.rollback();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}

		}

	}
	public int getUsuarioById(String login) {
		
		sql = "SELECT id from usuario where login = ? ";
		Usuario usuario = new Usuario();
		int idUsuario = 0;
		try {
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, login);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				usuario.setId(rs.getInt("id"));
				idUsuario = usuario.getId();
			}
			return idUsuario;
		} catch (SQLException e) {
			System.out.println("Erro ao Listar" + e.getMessage());
		} finally {
			try {
				stmt.close();
				
			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
		return 0;

	}
	public Usuario verificaUsuarioByLogin(String login) {
		sql = "SELECT id,tipoUsuario,login,senha from usuario where login = ?";
		Usuario usuario = new Usuario();
		try {
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				usuario.setId(rs.getInt("id"));
				usuario.setTipoUsuario(rs.getString("tipoUsuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
			}
			return usuario;
		} catch (SQLException e) {
			System.out.println("Erro ao Listar" + e.getMessage());
		} finally {
			try {
				stmt.close();
				
			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
		return null;

	}

	public int verificaLogin(String login) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		sql = "SELECT login from usuario where login= ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setLogin(rs.getString("login"));
				usuarios.add(usuario);
			}
			return usuarios.size();
		} catch (SQLException e) {
			System.out.println("Erro ao Listar" + e.getMessage());
		} finally {
			try {
				stmt.close();
			
			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
		return 0;
	}
}
