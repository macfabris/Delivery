package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.ffsd.tcc.modelo.UnidadeDeMedida;
import br.com.ffsd.tcc.singleton.BDSingleton;

public class UnidadeDeMedidaDao {
	Connection conexao = null;
	String sql = null;
	PreparedStatement stmt = null;

	public UnidadeDeMedidaDao(Connection conexao) {
		this.conexao = conexao;
	}
	public UnidadeDeMedidaDao() {
		try {
			this.conexao = (Connection) BDSingleton.getInstancia().getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void adiciona(UnidadeDeMedida unidadeDeMedida) throws SQLException,
			IOException {

		sql = "Insert into unidadedemedida(descricao) values(?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, unidadeDeMedida.getDescricao());
			stmt.execute();
			System.out.println("Unidade De Medida adicionado com sucesso");
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

		this.sql = "DELETE FROM unidadedemedida WHERE id=?";
		try {

			stmt = conexao.prepareStatement(sql);
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

	public void altera(UnidadeDeMedida unidadeDeMedida) throws SQLException {
		sql = "Update unidadedemedida set descricao = ? where id = ? ";
		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, unidadeDeMedida.getDescricao());
			stmt.setInt(2, unidadeDeMedida.getId());

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

	public List<UnidadeDeMedida> getLista() throws IOException {
		sql = "SELECT * from unidadedemedida";
		List<UnidadeDeMedida> unidadesDeMedida = new ArrayList<UnidadeDeMedida>();
		try {

			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();
				unidadeDeMedida.setId(rs.getInt("id"));
				unidadeDeMedida.setDescricao(rs.getString("descricao"));
				unidadesDeMedida.add(unidadeDeMedida);
			}
			return unidadesDeMedida;
		} catch (SQLException e) {
			System.out.println("Erro ao Listar Unidade de Medida : " + e.getMessage());
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
