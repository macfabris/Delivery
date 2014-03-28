package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.ffsd.tcc.modelo.ItensPedido;


public class ItensPedidoDao {
	Connection conexao = null;
	String sql = null;
	PreparedStatement stmt = null;

	public ItensPedidoDao(Connection conexao)  {
		this.conexao = conexao;
	}
	
	public List<ItensPedido> getLista() throws IOException {
		sql = "select itenspedido.id,itenspedido.quantidade,produto.nome,itenspedido.preco from itenspedido inner join produto on produto.id = itenspedido.idProduto";
		List<ItensPedido> itensPedido = new ArrayList<ItensPedido>();

		try {			
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ItensPedido itemPedido = new ItensPedido();
				itemPedido.setId(rs.getInt("itenspedido.id"));
				itemPedido.setQuantidade(rs.getInt("itenspedido.quantidade"));
				itemPedido.getProduto().setNome(rs.getString("produto.nome"));
				itemPedido.setPreco(rs.getDouble("itenspedido.preco"));
				itensPedido.add(itemPedido);
			}
			return itensPedido;
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
	
	public List<ItensPedido> getListaByIdPedido(int id) throws IOException {
		sql = "select distinct itenspedido.id,itenspedido.quantidade,produto.nome,itenspedido.preco from itenspedido inner join produto on produto.id = itenspedido.idProduto where idPedido = ? ";
		List<ItensPedido> itensPedido = new ArrayList<ItensPedido>();
		System.out.println("DAO ItensPedido");
		try {
			
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ItensPedido itemPedido = new ItensPedido();
				itemPedido.setId(rs.getInt("itenspedido.id"));
				itemPedido.setQuantidade(rs.getInt("itenspedido.quantidade"));
				itemPedido.getProduto().setNome(rs.getString("produto.nome"));
				itemPedido.setPreco(rs.getDouble("itenspedido.preco"));
				itensPedido.add(itemPedido);
			}
			return itensPedido;
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
	
	public void adiciona(List<ItensPedido> list) throws SQLException,
			IOException {
		sql = "Insert into ItensPedido(IdProduto,IdPedido,Quantidade,Preco, Total) values(?,?,?,?,?)";
		try {
			for (ItensPedido p : list) {

				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, p.getIdProduto());
				stmt.setInt(2, p.getIdPedido());
				stmt.setInt(3, p.getQuantidade());
				stmt.setDouble(4, p.getPreco());
				stmt.setDouble(5, p.getTotal());
				stmt.execute();
				System.out.println("ItensPedido adicionado com sucesso");

			}

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

}
