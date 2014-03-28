package br.com.ffsd.tcc.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.ffsd.tcc.modelo.ClienteEndereco;


	public class ClienteEnderecoDao {
		Connection conexao = null;
		String sql = null;
		PreparedStatement stmt = null;
		
		public ClienteEnderecoDao(Connection conexao)  {
			this.conexao = conexao;
		}
		
		public void adiciona(ClienteEndereco objClienteEndereco) throws SQLException, IOException {

			sql = "Insert into clienteendereco(IdCliente,IdEndereco) values(?,?)";
			try {
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, objClienteEndereco.getIdCliente());
				stmt.setInt(2, objClienteEndereco.getIdEndereco());
				stmt.execute();
				System.out.println("ClienteEndereco:" + objClienteEndereco.getId()
						+ " adicionado com sucesso");
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
		public int getIdEndereco(int id) throws IOException {
			
			sql = "SELECT idEndereco from clienteendereco where idCliente = ?  ";
	 
			
			int idEndereco = 0;
			try {
				
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					ClienteEndereco clienteEndereco = new ClienteEndereco();
					clienteEndereco.setIdEndereco(rs.getInt("idEndereco"));
					idEndereco = clienteEndereco.getIdEndereco();
					}
				return idEndereco;
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
		public void remove(int id) {

			this.sql = "DELETE FROM clienteendereco WHERE idEndereco=?";
			try {
				
				this.stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.execute();
				
			} catch (SQLException e) {
				System.out.println("Erro ao remover "+e.getMessage());
				throw new RuntimeException("Não foi possivel remover.",e);
			} finally {
				try {
					stmt.close();
					
				} catch (SQLException e) {
					System.out.println("Não foi possivel liberar os recursos");
				}
			}
		}
	}

