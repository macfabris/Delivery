package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import br.com.ffsd.tcc.modelo.Cliente;

public class ClienteDao {
	Connection conexao = null;
	String sql = null;
	PreparedStatement stmt = null;

	public ClienteDao(Connection conexao)  {
		this.conexao = conexao;
	}

	public List<Cliente> getLista() throws IOException {

		sql = "SELECT cliente.id,cliente.nome,cliente.cpf,cliente.datanascimento,cliente.datacadastro,statuscliente.descricao from cliente inner join statuscliente on cliente.status = statuscliente.id ";

		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			stmt = conexao.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("cliente.id"));
				cliente.setNome(rs.getString("cliente.nome"));
				cliente.setCpf(rs.getString("cliente.cpf"));
				java.sql.Date dataNascimentoSql = rs.getDate("dataNascimento");
				Calendar DataNascimentoEmCalendar = Calendar.getInstance();
				DataNascimentoEmCalendar.setTime(dataNascimentoSql);
				java.sql.Date dataCadastroSql = rs.getDate("dataCadastro");
				Calendar DataCadastroEmCalendar = Calendar.getInstance();
				DataCadastroEmCalendar.setTime(dataCadastroSql);
				cliente.setDataNascimento(DataNascimentoEmCalendar);
				cliente.setDataCadastro(DataCadastroEmCalendar);
				cliente.statusCliente.setDescricao(rs
						.getString("statuscliente.descricao"));
				clientes.add(cliente);
			}
			return clientes;

		} catch (SQLException e) {
			System.out.println("Erro ao Listar Cliente : " + e.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
		return null;

	}

	public Cliente getClienteById(int id) throws IOException {

		sql = "SELECT cliente.id,cliente.nome,cliente.cpf,cliente.datanascimento,cliente.datacadastro,statuscliente.descricao from cliente inner join statuscliente on cliente.status = statuscliente.id  where cliente.id = ? ";

		Cliente cliente = new Cliente();
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				cliente.setId(rs.getInt("cliente.id"));
				cliente.setNome(rs.getString("cliente.nome"));
				System.out.println(cliente.getNome());
				cliente.setCpf(rs.getString("cliente.cpf"));
				java.sql.Date dataNascimentoSql = rs.getDate("dataNascimento");
				Calendar DataNascimentoEmCalendar = Calendar.getInstance();
				DataNascimentoEmCalendar.setTime(dataNascimentoSql);
				java.sql.Date dataCadastroSql = rs.getDate("dataCadastro");
				Calendar DataCadastroEmCalendar = Calendar.getInstance();
				DataCadastroEmCalendar.setTime(dataCadastroSql);
				cliente.setDataNascimento(DataNascimentoEmCalendar);
				cliente.setDataCadastro(DataCadastroEmCalendar);
			}
			return cliente;
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

	public int adiciona(Cliente cliente) throws SQLException, IOException {
		sql = "Insert into cliente(nome,dataNascimento,dataCadastro,status,cpf) values(?,?,?,?,?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			java.sql.Date dataParaGravarNascimento = new java.sql.Date(cliente
					.getDataNascimento().getTimeInMillis());
			stmt.setDate(2, dataParaGravarNascimento);
			java.sql.Date dataParaGravarCadastro = new java.sql.Date(cliente
					.getDataCadastro().getTimeInMillis());
			stmt.setDate(3, dataParaGravarCadastro);
			stmt.setInt(4, cliente.getStatusCliente().getId());
			stmt.setString(5, cliente.getCpf());
			stmt.execute();
			System.out.println("Cliente adicionado com sucesso");

			sql = "select max(id) from cliente";
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int idCliente = rs.getInt(1);

			return idCliente;

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

	public void altera(Cliente cliente) throws SQLException {

		sql = "Update cliente set nome=? where id = ? ";

		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setInt(2, cliente.getId());

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
	public int verificaCPF(String CPF) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		sql = "SELECT cpf from cliente where cpf = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, CPF);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCpf(rs.getString("cpf"));
				clientes.add(cliente);
			}
			return clientes.size();
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