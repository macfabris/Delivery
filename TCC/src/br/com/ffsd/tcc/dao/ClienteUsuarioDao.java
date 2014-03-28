package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.ffsd.tcc.modelo.ClienteUsuario;

	public class ClienteUsuarioDao {
		Connection conexao = null;
		String sql = null;
		PreparedStatement stmt = null;
		
		public ClienteUsuarioDao(Connection conexao)  {
			this.conexao = conexao;
		}
		public void adiciona(ClienteUsuario objClienteUsuario) throws SQLException, IOException {
			
			sql = "Insert into clienteusuario(IdCliente,IdUsuario) values(?,?)";
			try {
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, objClienteUsuario.getIdCliente());				
				stmt.setInt(2, objClienteUsuario.getIdUsuario());				
				stmt.execute();
				System.out.println("ClienteUsuario adicionado com sucesso");
			
			} catch (SQLException e) {
				System.out.println("Não foi possivel adicionar " + e.getMessage());
				conexao.rollback();
			} finally {
				try {
					stmt.close();
					
				} catch (SQLException e) {
					System.out.println("Não foi possivel liberar os recursos");
				}
			}
		}
		
		public int getIdCliente(int id) {
			sql = "SELECT idCliente from clienteusuario where idUsuario = ? ";
			int idCliente = 0;
			try {
				
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
				ClienteUsuario clienteUsuario = new ClienteUsuario();
				clienteUsuario.setIdCliente(rs.getInt("idCliente"));
				idCliente = clienteUsuario.getIdCliente();
				}
				return idCliente;
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

