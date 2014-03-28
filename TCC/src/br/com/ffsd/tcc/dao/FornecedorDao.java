package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ffsd.tcc.modelo.Fornecedor;

public class FornecedorDao {
	Connection conexao = null;
	String sql = null;
	PreparedStatement stmt = null;
	
	public FornecedorDao(Connection conexao)  {
		this.conexao = conexao;
	}

	public void adiciona(Fornecedor fornecedor) throws SQLException, IOException {
		sql = "Insert into fornecedor(nome,idEmail) values(?,?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, fornecedor.getNome());
			stmt.setInt(2, fornecedor.getEmail().getId());
			stmt.execute();
			System.out.println("Fornecedor adicionado com sucesso");

			
		} catch (SQLException e) {
			System.out.println("Não foi possivel adicionar" + e.getMessage());
			conexao.rollback();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}

	}

	public void altera(Fornecedor fornecedor) throws SQLException {

		sql = "Update fornecedor set nome=? where idFornecedor = ? ";

		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, fornecedor.getNome());
			stmt.setInt(2, fornecedor.getId());

			stmt.execute();
			System.out.println("Fornecedor alterado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Não foi possível alterar " + e.getMessage());
			conexao.rollback();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}

		}

	}
	
	public List<Fornecedor> getLista() throws IOException {

		sql = "SELECT fornecedor.idfornecedor,fornecedor.nome,email.descricao from fornecedor inner join email on fornecedor.idEmail = email.id ";

		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		try {
			stmt = conexao.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(rs.getInt("fornecedor.idfornecedor"));
				fornecedor.setNome(rs.getString("fornecedor.nome"));
				fornecedor.getEmail().setDescricao(rs.getString("email.descricao"));
				fornecedores.add(fornecedor);
			}
			return fornecedores;
		} catch (SQLException e) {
			System.out.println("Erro ao Listar Fornecedor : " + e.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
		return null;

	}

	public void remove(int id) {

		this.sql = "DELETE FROM fornecedor WHERE idFornecedor=?";
		try {
			this.stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao remover " + e.getMessage());
			throw new RuntimeException("Não foi possivel remover.", e);
		} finally {
			try {
				stmt.close();

			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
	}

		
	}


