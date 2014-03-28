package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.ffsd.tcc.modelo.GrupoProduto;
import br.com.ffsd.tcc.singleton.BDSingleton;

public class GrupoProdutoDao {
	Connection conexao = null;
	String sql = null;
	PreparedStatement stmt = null;

	public GrupoProdutoDao(Connection conexao) {
		this.conexao = conexao;
	}
	
	public GrupoProdutoDao()
	{
		try {
			this.conexao = (Connection) BDSingleton.getInstancia().getConexao();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public void adiciona(GrupoProduto grupoProduto) throws SQLException,
			IOException {

		sql = "Insert into grupoproduto(descricao) values(?)";
		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, grupoProduto.getDescricao());
			stmt.execute();
			System.out.println("Grupo Produto adicionado com sucesso");
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

	public void remove(int id) {

		this.sql = "DELETE FROM grupoproduto WHERE id=?";
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

	public void altera(GrupoProduto grupoProduto) throws SQLException {
		sql = "Update grupoproduto set descricao = ? where id = ? ";
		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, grupoProduto.getDescricao());
			stmt.setInt(2, grupoProduto.getId());

			stmt.execute();
			System.out.println("Produto alterado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Não foi possível alterar : " + e.getMessage());
			conexao.rollback();
		} finally {
			try {
				stmt.close();

			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}

		}
	}

	public List<GrupoProduto> getLista() throws IOException {
		sql = "SELECT * from grupoproduto";
		List<GrupoProduto> gprodutos = new ArrayList<GrupoProduto>();
		try {

			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				GrupoProduto gproduto = new GrupoProduto();
				gproduto.setId(rs.getInt("id"));
				gproduto.setDescricao(rs.getString("descricao"));
				gprodutos.add(gproduto);
			}
			return gprodutos;
		} catch (SQLException e) {
			System.out.println("Erro ao Listar Grupo Produto : " + e.getMessage());
		} finally {
			try {
				stmt.close();

			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
		return null;

	}
}
