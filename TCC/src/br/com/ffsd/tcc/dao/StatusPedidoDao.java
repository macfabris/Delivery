package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.ffsd.tcc.modelo.StatusPedido;

public class StatusPedidoDao {

	Connection conexao = null;
	String sql = null;
	PreparedStatement stmt = null;

	public StatusPedidoDao(Connection conexao) {
		this.conexao = conexao;
	}

	public List<StatusPedido> getLista() throws IOException {
		sql = "SELECT id,descricao from StatusPedido";

		List<StatusPedido> statusPedidos = new ArrayList<StatusPedido>();

		try {

			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StatusPedido statusPedido = new StatusPedido();
				statusPedido.setId(rs.getInt("id"));
				statusPedido.setDescricao(rs.getString("descricao"));
				statusPedidos.add(statusPedido);
			}
			return statusPedidos;
		} catch (SQLException e) {
			System.out.println("Erro ao Listar Status Pedido : " + e.getMessage());
		} finally {
			try {
				stmt.close();

			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
		return null;

	}

	public void altera(int idPedido, int idStatusPedido) throws SQLException {
		sql = "Update pedido set idStatusPedido=? where id = ? ";

		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idStatusPedido);
			stmt.setInt(2, idPedido);

			stmt.execute();
			System.out.println("Cliente alterado com sucesso!");
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
}
