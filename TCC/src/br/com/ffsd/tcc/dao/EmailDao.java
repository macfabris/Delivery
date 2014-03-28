package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ffsd.tcc.modelo.Email;

public class EmailDao {
	Connection conexao = null;
	String sql = null;
	PreparedStatement stmt = null;

	public EmailDao(Connection conexao) {
		this.conexao = conexao;
	}

	public int adiciona(Email email) throws SQLException, IOException {

		sql = "Insert into email(descricao) values(?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, email.getDescricao());
			stmt.execute();
			System.out.println("Email adicionado com sucesso");
			sql = "select max(id) from email";
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int idEmail = rs.getInt(1);
			return idEmail;
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

	public Email getEmailById(int id) throws IOException {

		sql = "SELECT id,descricao from email where id = ?  ";

		Email email = new Email();
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				email.setId(rs.getInt("id"));
				email.setDescricao(rs.getString("descricao"));
			}
			return email;
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

	public void altera(Email email) throws SQLException {

		sql = "Update email set descricao=? where id = ? ";

		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, email.getDescricao());
			stmt.setInt(2, email.getId());

			stmt.execute();
			System.out.println("Email alterado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Não foi possível alterar " + email.getId()
					+ " " + e.getMessage());
			conexao.rollback();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}

		}

	}
	
	public int verificaEmail(String mail) {
		List<Email> emails = new ArrayList<Email>();
		sql = "SELECT descricao from email where descricao = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, mail);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Email email = new Email();
				email.setDescricao(rs.getString("descricao"));
				emails.add(email);
			}
			return emails.size();
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
