package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.ffsd.tcc.modelo.ClienteEmail;

	public class ClienteEmailDao {
		Connection conexao = null;
		String sql = null;
		PreparedStatement stmt = null;
		
		public ClienteEmailDao(Connection conexao)  {
			this.conexao = conexao;
		}
		public void adiciona(ClienteEmail objClienteEmail) throws SQLException, IOException {

			sql = "Insert into clienteemail(IdCliente,IdEmail) values(?,?)";
			try {
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, objClienteEmail.getIdCliente());
				stmt.setInt(2, objClienteEmail.getIdEmail());
				stmt.execute();
				System.out.println("ClienteEmail adicionado com sucesso");
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
		public int getIdEmail(int id) throws IOException {
			
			sql = "SELECT idEmail from clienteemail where idCliente = ?  ";
	 
			int idEmail = 0;
			
			try {
				
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					ClienteEmail clienteEmail = new ClienteEmail();
					clienteEmail.setIdEmail(rs.getInt("idEmail"));
					idEmail = clienteEmail.getIdEmail();
					}
				return idEmail;
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


