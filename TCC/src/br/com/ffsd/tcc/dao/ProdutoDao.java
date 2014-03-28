package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.com.ffsd.tcc.modelo.Produto;
import br.com.ffsd.tcc.singleton.BDSingleton;

public class ProdutoDao {
	Connection conexao = null;
	String sql = null;
	PreparedStatement stmt = null;

	public ProdutoDao(Connection conexao) {
		this.conexao = conexao;
	}
	
	public ProdutoDao() {
		try {
			this.conexao = (Connection) BDSingleton.getInstancia().getConexao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void adiciona(Produto produto) throws SQLException, IOException {

		sql = "Insert into produto(nome,descricao,pcCusto,PorcenLucro,datacadastro,unidadedemedida,grupoproduto,diretorio,estoque,idfornecedor) values(?,?,?,?,?,?,?,?,?,?)";
		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
			stmt.setDouble(3, produto.getPcCusto());
			stmt.setDouble(4, produto.getPorcentLucro());
			java.sql.Date dataParaGravar = new java.sql.Date(produto
					.getDataCadastro().getTimeInMillis());
			stmt.setDate(5, dataParaGravar);
			stmt.setInt(6, produto.getUnidadeDeMedida().getId());
			stmt.setInt(7, produto.getGrupoProduto().getId());
			stmt.setString(8, produto.getDiretorio());
			stmt.setInt(9, produto.getEstoque());
			stmt.setInt(10,produto.getFornecedor().getId());
			stmt.execute();
			System.out.println("Projeto adicionado com sucesso");
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

		this.sql = "DELETE FROM produto WHERE id=?";
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

	public void altera(Produto produto) throws SQLException {
		sql = "Update produto set nome = ?,descricao = ?,pcCusto = ? ,PorcenLucro = ?, datacadastro = ?, grupoproduto = ?,unidadedemedida = ? where id = ? ";
		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
			stmt.setDouble(3, produto.getPcCusto());
			stmt.setDouble(4, produto.getPorcentLucro());
			stmt.setDate(5, new java.sql.Date(produto.getDataCadastro()
					.getTimeInMillis()));
			stmt.setInt(6, produto.grupoProduto.getId());
			stmt.setInt(7, produto.unidadeDeMedida.getId());
			stmt.setInt(8, produto.getId());
			;
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

	public List<Produto> getListaProdutoById(int id) throws IOException {
		sql = "SELECT produto.id,produto.nome,produto.diretorio,produto.descricao,produto.pcCusto,produto.porcenLucro,((produto.pcCusto * produto.porcenLucro/100) + produto.pcCusto),produto.dataCadastro,unidadedemedida.id,unidadedemedida.descricao,grupoproduto.id,grupoproduto.descricao from produto inner join Unidadedemedida unidadedemedida on produto.unidadedemedida = unidadedemedida.id inner join Grupoproduto grupoproduto on produto.grupoproduto = grupoproduto.id WHERE produto.id = ? ";
		List<Produto> produtos = new ArrayList<Produto>();

		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("produto.id"));
				produto.setNome(rs.getString("produto.nome"));
				produto.setDescricao(rs.getString("produto.descricao"));
				produto.setPcCusto(rs.getDouble("produto.pcCusto"));
				produto.setPorcentLucro(rs.getDouble("produto.porcenLucro"));
				produto.setValor(rs.getDouble("((produto.pcCusto * produto.porcenLucro/100) + produto.pcCusto)"));
				produto.setDiretorio(rs.getString("produto.diretorio"));
				java.sql.Date dataSql = rs.getDate("produto.dataCadastro");
				Calendar DataEmCalendar = Calendar.getInstance();
				DataEmCalendar.setTime(dataSql);
				produto.setDataCadastro(DataEmCalendar);
				produto.getUnidadeDeMedida().setId(
						rs.getInt("unidadedemedida.id"));
				produto.getUnidadeDeMedida().setDescricao(
						rs.getString("unidadedemedida.descricao"));
				produto.getGrupoProduto().setId(rs.getInt("grupoproduto.id"));
				produto.getGrupoProduto().setDescricao(
						rs.getString("grupoproduto.descricao"));
				produtos.add(produto);
			}
			return produtos;
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

	public List<Produto> getLista() throws IOException {
		sql = "SELECT produto.estoque,fornecedor.nome,produto.id,produto.diretorio,produto.nome,produto.descricao,produto.pcCusto,produto.porcenLucro,((produto.pcCusto * produto.porcenLucro/100) + produto.pcCusto),produto.dataCadastro,unidadedemedida.id,unidadedemedida.descricao,grupoproduto.id,grupoproduto.descricao from produto inner join Unidadedemedida unidadedemedida on produto.unidadedemedida = unidadedemedida.id inner join Grupoproduto grupoproduto on produto.grupoproduto = grupoproduto.id inner join Fornecedor fornecedor on produto.idfornecedor = fornecedor.idfornecedor";
		List<Produto> produtos = new ArrayList<Produto>();

		try {

			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("produto.id"));
				produto.setNome(rs.getString("produto.nome"));
				produto.setDiretorio(rs.getString("produto.diretorio"));
				produto.setDescricao(rs.getString("produto.descricao"));
				produto.setPcCusto(rs.getDouble("produto.pcCusto"));
				produto.setPorcentLucro(rs.getDouble("produto.porcenLucro"));
				produto.setValor(rs.getDouble("((produto.pcCusto * produto.porcenLucro/100) + produto.pcCusto)"));
				System.out.println(produto.getValor());
				produto.setEstoque(rs.getInt("produto.estoque"));
				produto.getFornecedor().setNome(rs.getString("fornecedor.nome"));
				java.sql.Date dataSql = rs.getDate("produto.dataCadastro");
				Calendar DataEmCalendar = Calendar.getInstance();
				DataEmCalendar.setTime(dataSql);
				produto.setDataCadastro(DataEmCalendar);
				produto.getUnidadeDeMedida().setId(
						rs.getInt("unidadedemedida.id"));
				produto.getUnidadeDeMedida().setDescricao(
						rs.getString("unidadedemedida.descricao"));
				produto.getGrupoProduto().setId(rs.getInt("grupoproduto.id"));
				produto.getGrupoProduto().setDescricao(
						rs.getString("grupoproduto.descricao"));
				produtos.add(produto);
			}
			return produtos;
		} catch (SQLException e) {
			System.out.println("Erro ao Listar Produto : " + e.getMessage());
		} finally {
			try {
				stmt.close();

			} catch (SQLException e) {
				System.out.println("Não foi possivel liberar os recursos");
			}
		}
		return null;

	}

	public List<Produto> getListaProdutoByIdGrupoProduto(int id)
			throws IOException {
		sql = "SELECT produto.id,produto.diretorio,produto.nome,produto.descricao,produto.pcCusto,produto.porcenLucro,produto.datacadastro,produto.unidadedemedida,produto.grupoproduto,grupoproduto.descricao FROM produto JOIN grupoproduto ON produto.grupoproduto = grupoproduto.id WHERE produto.grupoproduto =?";

		List<Produto> produtos = new ArrayList<Produto>();

		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("produto.id"));
				produto.setNome(rs.getString("produto.nome"));
				produto.setDiretorio(rs.getString("produto.diretorio"));
				produto.setDescricao(rs.getString("produto.descricao"));
				produto.setPcCusto(rs.getDouble("produto.pcCusto"));
				produto.setPorcentLucro(rs.getDouble("produto.porcenLucro"));
				java.sql.Date dataSql = rs.getDate("produto.dataCadastro");
				Calendar DataEmCalendar = Calendar.getInstance();
				DataEmCalendar.setTime(dataSql);
				produto.setDataCadastro(DataEmCalendar);
				produto.getUnidadeDeMedida().setId(
						rs.getInt("produto.unidadedemedida"));
				produto.getGrupoProduto().setId(
						rs.getInt("produto.grupoproduto"));
				produto.getGrupoProduto().setDescricao(
						rs.getString("grupoproduto.descricao"));
				produtos.add(produto);
			}
			return produtos;
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

	public List<Produto> getListaProdutoByPesquisa(String pesquisa)
			throws IOException {
		sql = "SELECT produto.id,produto.diretorio,produto.nome,produto.descricao,produto.pcCusto,produto.porcenLucro,produto.dataCadastro,unidadedemedida.id,unidadedemedida.descricao,grupoproduto.id,grupoproduto.descricao from produto inner join Unidadedemedida unidadedemedida on produto.unidadedemedida = unidadedemedida.id inner join Grupoproduto grupoproduto on produto.grupoproduto = grupoproduto.id where produto.nome like ?";
		List<Produto> produtos = new ArrayList<Produto>();

		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, "%" + pesquisa + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("produto.id"));
				produto.setNome(rs.getString("produto.nome"));
				produto.setDiretorio(rs.getString("produto.diretorio"));
				produto.setDescricao(rs.getString("produto.descricao"));
				produto.setPcCusto(rs.getDouble("produto.pcCusto"));
				produto.setPorcentLucro(rs.getDouble("produto.porcenLucro"));
				java.sql.Date dataSql = rs.getDate("produto.dataCadastro");
				Calendar DataEmCalendar = Calendar.getInstance();
				DataEmCalendar.setTime(dataSql);
				produto.setDataCadastro(DataEmCalendar);
				produto.getUnidadeDeMedida().setId(
						rs.getInt("unidadedemedida.id"));
				produto.getUnidadeDeMedida().setDescricao(
						rs.getString("unidadedemedida.descricao"));
				produto.getGrupoProduto().setId(rs.getInt("grupoproduto.id"));
				produto.getGrupoProduto().setDescricao(
						rs.getString("grupoproduto.descricao"));
				produtos.add(produto);
			}
			System.out.println(produtos.size());
			return produtos;
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
	
	public void adicionaEstoque(Produto produto) throws SQLException {
		sql = "Update produto set estoque = estoque + ?,pcCusto = ?, porcenLucro = ? where id = ? ";
		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, produto.getEstoque());
			stmt.setInt(4, produto.getId());
			stmt.setDouble(2, produto.getPcCusto());
			stmt.setDouble(3, produto.getPorcentLucro());
			stmt.execute();
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
	
	public void retiraEstoque(Produto produto) throws SQLException {
		sql = "Update produto set estoque = estoque - ?,pcCusto = ?,porcenLucro = ? where id = ? ";
		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, produto.getEstoque());
			stmt.setInt(4, produto.getId());
			stmt.setDouble(2, produto.getPcCusto());
			stmt.setDouble(3, produto.getPorcentLucro());
			stmt.execute();
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
}