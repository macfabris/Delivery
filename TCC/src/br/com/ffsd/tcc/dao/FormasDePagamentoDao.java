package br.com.ffsd.tcc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.ffsd.tcc.modelo.FormasDePagamento;


public class FormasDePagamentoDao {
	Connection conexao = null;
	String sql = null;
	PreparedStatement stmt = null;
	
	public FormasDePagamentoDao(Connection conexao)  {
		this.conexao = conexao;
	}
	public List<FormasDePagamento> getLista() throws IOException {
		
		sql = "SELECT * from formasdepagamento";
		List<FormasDePagamento> fPagamentos = new ArrayList<FormasDePagamento>();
		try {
			
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FormasDePagamento fPagamento = new FormasDePagamento();
				fPagamento.setId(rs.getInt("id"));
				fPagamento.setDescricao(rs.getString("descricao"));
				fPagamentos.add(fPagamento);
				}
			return fPagamentos;
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
