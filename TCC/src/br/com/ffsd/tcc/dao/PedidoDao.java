package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.com.ffsd.tcc.modelo.Pedido;

public class PedidoDao {
	Connection conexao = null;
	String sql = null;
	PreparedStatement stmt = null;

	public PedidoDao(Connection conexao) {
		this.conexao = conexao;
	}

	public int adiciona(Pedido pedido) throws SQLException, IOException {
		sql = "Insert into pedido(valor,dataPedido,idCliente,idEndereco,idStatusPedido,idFormaDePagamento) values(?,?,?,?,?,?)";
		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, pedido.getValor());
			java.sql.Date dataParaGravar = new java.sql.Date(pedido
					.getDataPedido().getTimeInMillis());
			stmt.setDate(2, dataParaGravar);
			stmt.setInt(3, pedido.getCliente().getId());
			stmt.setInt(4, pedido.getEndereco().getId());
			stmt.setInt(5, pedido.getStatusPedido().getId());
			stmt.setInt(6, pedido.getFormasDePagamento().getId());
			stmt.execute();
			System.out.println("Cliente adicionado com sucesso");

			sql = "select max(id) from pedido";
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int idPedido = rs.getInt(1);

			return idPedido;

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
		return 0;
	}

	public List<Pedido> getLista() throws IOException {
		sql = "select pedido.id,pedido.valor,pedido.datapedido,cliente.nome,endereco.logradouro,endereco.numero,endereco.complemento,endereco.bairro,endereco.cidade,endereco.estado,statuspedido.id,statuspedido.descricao,formasdepagamento.descricao from pedido inner join cliente on cliente.id = pedido.idCliente  inner join endereco on endereco.id = pedido.IdEndereco  inner join statuspedido on statuspedido.id = pedido.idStatusPedido inner join formasdepagamento on formasdepagamento.id = pedido.idFormaDePagamento";
		List<Pedido> pedidos = new ArrayList<Pedido>();

		try {

			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setId(rs.getInt("pedido.id"));
				pedido.setValor(rs.getDouble("pedido.valor"));
				java.sql.Date dataSql = rs.getDate("pedido.datapedido");
				Calendar DataEmCalendar = Calendar.getInstance();
				DataEmCalendar.setTime(dataSql);
				pedido.setDataPedido(DataEmCalendar);
				pedido.getCliente().setNome(rs.getString("cliente.nome"));
				pedido.getEndereco().setLogradouro(
						rs.getString("endereco.logradouro"));
				pedido.getEndereco().setNumero(rs.getInt("endereco.numero"));
				pedido.getEndereco().setComplemento(
						rs.getString("endereco.complemento"));
				pedido.getEndereco().setBairro(rs.getString("endereco.bairro"));
				pedido.getEndereco().setCidade(rs.getString("endereco.cidade"));
				pedido.getEndereco().setEstado(rs.getString("endereco.estado"));
				pedido.getStatusPedido().setDescricao(
						rs.getString("statuspedido.descricao"));
				pedido.getStatusPedido().setId(rs.getInt("statuspedido.id"));
				pedido.getFormasDePagamento().setDescricao(
						rs.getString("formasdepagamento.descricao"));
				pedidos.add(pedido);
			}
			return pedidos;
		} catch (SQLException e) {
			System.out.println("Erro ao Listar Pedido : " + e.getMessage());
		} finally {
			try {
				stmt.close();

			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
		return null;

	}

	public List<Pedido> getListaByCliente(int idCliente) {
		sql = "select distinct pedido.id,pedido.valor,pedido.datapedido,endereco.logradouro,endereco.numero,endereco.complemento,endereco.bairro,endereco.cidade,endereco.estado,formasdepagamento.descricao,statuspedido.descricao from pedido inner join itenspedido on itensPedido.idPedido = pedido.id inner join cliente on pedido.idCliente = cliente.id inner join endereco on endereco.id = pedido.IdEndereco inner join formasdepagamento on pedido.IdFormaDePagamento = formasdepagamento.id inner join statuspedido on pedido.idStatusPedido = statuspedido.id where cliente.id = ?";
		List<Pedido> pedidos = new ArrayList<Pedido>();

		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setId(rs.getInt("pedido.id"));
				pedido.setValor(rs.getDouble("pedido.valor"));
				java.sql.Date dataSql = rs.getDate("pedido.datapedido");
				Calendar DataEmCalendar = Calendar.getInstance();
				DataEmCalendar.setTime(dataSql);
				pedido.setDataPedido(DataEmCalendar);
				pedido.getEndereco().setLogradouro(
						rs.getString("endereco.logradouro"));
				pedido.getEndereco().setNumero(rs.getInt("endereco.numero"));
				pedido.getEndereco().setComplemento(
						rs.getString("endereco.complemento"));
				pedido.getEndereco().setBairro(rs.getString("endereco.bairro"));
				pedido.getEndereco().setCidade(rs.getString("endereco.cidade"));
				pedido.getEndereco().setEstado(rs.getString("endereco.estado"));
				pedido.getStatusPedido().setDescricao(
						rs.getString("statuspedido.descricao"));
				pedido.getFormasDePagamento().setDescricao(
						rs.getString("formasdepagamento.descricao"));
				pedidos.add(pedido);
			}
			return pedidos;
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

	public List<Pedido> getListaByStatus(int idStatusPedido) {
		sql = "select distinct pedido.id,pedido.valor,pedido.datapedido,endereco.logradouro,endereco.numero,endereco.complemento,endereco.bairro,endereco.cidade,endereco.estado,formasdepagamento.descricao,statuspedido.descricao from pedido inner join itenspedido on itensPedido.idPedido = pedido.id inner join cliente on pedido.idCliente = cliente.id inner join endereco on endereco.id = pedido.IdEndereco inner join formasdepagamento on pedido.IdFormaDePagamento = formasdepagamento.id inner join statuspedido on pedido.idStatusPedido = statuspedido.id where pedido.idStatusPedido = ?";
		List<Pedido> pedidos = new ArrayList<Pedido>();

		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idStatusPedido);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setId(rs.getInt("pedido.id"));
				pedido.setValor(rs.getDouble("pedido.valor"));
				java.sql.Date dataSql = rs.getDate("pedido.datapedido");
				Calendar DataEmCalendar = Calendar.getInstance();
				DataEmCalendar.setTime(dataSql);
				pedido.setDataPedido(DataEmCalendar);
				pedido.getEndereco().setLogradouro(
						rs.getString("endereco.logradouro"));
				pedido.getEndereco().setNumero(rs.getInt("endereco.numero"));
				pedido.getEndereco().setComplemento(
						rs.getString("endereco.complemento"));
				pedido.getEndereco().setBairro(rs.getString("endereco.bairro"));
				pedido.getEndereco().setCidade(rs.getString("endereco.cidade"));
				pedido.getEndereco().setEstado(rs.getString("endereco.estado"));
				pedido.getStatusPedido().setDescricao(
						rs.getString("statuspedido.descricao"));
				pedido.getFormasDePagamento().setDescricao(
						rs.getString("formasdepagamento.descricao"));
				pedidos.add(pedido);
			}
			return pedidos;
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
}
