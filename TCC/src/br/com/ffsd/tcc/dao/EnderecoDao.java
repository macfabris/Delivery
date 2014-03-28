package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ffsd.tcc.modelo.Endereco;


public class EnderecoDao {
	Connection conexao = null;
	String sql = null;
	PreparedStatement stmt = null;

	public EnderecoDao(Connection conexao)  {
		this.conexao = conexao;
	}
	
	public int adiciona(Endereco endereco) throws SQLException, IOException {

		sql = "Insert into endereco(logradouro,numero,complemento,bairro,cidade,estado) values(?,?,?,?,?,?)";
		try {
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, endereco.getLogradouro());
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getComplemento());
			stmt.setString(4, endereco.getBairro());
			stmt.setString(5, endereco.getCidade());
			stmt.setString(6, endereco.getEstado());
			stmt.execute();
			
			sql = "select max(id) from endereco";
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int idEndereco = rs.getInt(1);
			return idEndereco;
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
	
	public List<Endereco> getLista() throws IOException {
			
			sql = "SELECT endereco.id,endereco.logradouro,endereco.numero,endereco.complemento,endereco.bairro,endereco.cidade,endereco.estado " +
					" from endereco " ;
			
			List<Endereco> enderecos = new ArrayList<Endereco>();
			try {
				stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Endereco endereco = new Endereco();
					endereco.setId(rs.getInt("endereco.id"));
					endereco.setLogradouro(rs.getString("endereco.logradouro"));
					endereco.setNumero(rs.getInt("endereco.numero"));
					endereco.setComplemento(rs.getString("endereco.complemento"));
					endereco.setBairro(rs.getString("endereco.bairro"));
					endereco.setCidade(rs.getString("endereco.cidade"));
					endereco.setEstado(rs.getString("endereco.estado"));
					enderecos.add(endereco);
					}
				return enderecos;
			} catch (SQLException ex) {
				System.out.println("Erro ao Listar" + ex.getMessage());
			} finally {
				try {
					stmt.close();
					
				} catch (SQLException exx) {
					System.out.println("Não foi possivel liberar os recursos");
				}
			}
			return null;
	
		}

	public List<Endereco> getListaIdEndereco(int id) throws IOException {
		
		sql = "SELECT endereco.id,endereco.logradouro,endereco.numero,endereco.complemento,endereco.bairro,endereco.cidade,endereco.estado " +
				" from endereco where id = ?" ;
		
		List<Endereco> enderecos = new ArrayList<Endereco>();
		try {
		
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Endereco endereco = new Endereco();
				endereco.setId(rs.getInt("endereco.id"));
				endereco.setLogradouro(rs.getString("endereco.logradouro"));
				endereco.setNumero(rs.getInt("endereco.numero"));
				endereco.setComplemento(rs.getString("endereco.complemento"));
				endereco.setBairro(rs.getString("endereco.bairro"));
				endereco.setCidade(rs.getString("endereco.cidade"));
				endereco.setEstado(rs.getString("endereco.estado"));
				enderecos.add(endereco);
				}
			return enderecos;
		} catch (SQLException ex) {
			System.out.println("Erro ao Listar" + ex.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (SQLException exx) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
		return null;
	
	}

	public List<Endereco> getListaByIdCliente(int id) throws IOException {
		
		sql = "SELECT endereco.id,endereco.logradouro,endereco.numero,endereco.complemento,endereco.bairro,endereco.cidade,endereco.estado from endereco  inner join clienteEndereco on endereco.id = clienteendereco.idEndereco where clienteEndereco.idCliente = ?" ;
		
		List<Endereco> enderecos = new ArrayList<Endereco>();
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Endereco endereco = new Endereco();
				endereco.setId(rs.getInt("endereco.id"));
				endereco.setLogradouro(rs.getString("endereco.logradouro"));
				endereco.setNumero(rs.getInt("endereco.numero"));
				endereco.setComplemento(rs.getString("endereco.complemento"));
				endereco.setBairro(rs.getString("endereco.bairro"));
				endereco.setCidade(rs.getString("endereco.cidade"));
				endereco.setEstado(rs.getString("endereco.estado"));
				enderecos.add(endereco);
				}
			return enderecos;
		} catch (SQLException ex) {
			System.out.println("Erro ao Listar" + ex.getMessage());
		} finally {
			try {
				stmt.close();
				
			} catch (SQLException exx) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
		return null;
	
	}

	public void remove(int id) {

			this.sql = "DELETE FROM endereco WHERE id=?";
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
