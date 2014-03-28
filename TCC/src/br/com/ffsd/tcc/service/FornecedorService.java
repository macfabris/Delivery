package br.com.ffsd.tcc.service;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.ffsd.tcc.dao.EmailDao;
import br.com.ffsd.tcc.dao.FornecedorDao;
import br.com.ffsd.tcc.modelo.Email;
import br.com.ffsd.tcc.modelo.Fornecedor;
import br.com.ffsd.tcc.singleton.BDSingleton;

public class FornecedorService {
	
	Connection conexao;
	
	private Fornecedor fornecedor;
	private Email email;
	
	public FornecedorService(Fornecedor objFornecedor,Email objEmail){
		this.email = objEmail;
		this.fornecedor = objFornecedor;
		
		try {
			conexao = BDSingleton.getInstancia().getConexao();
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
	}
		public boolean Adiciona() throws SQLException {
			try {
				conexao.setAutoCommit(false);
				EmailDao emailDao = new EmailDao(conexao);
				int idEmail = emailDao.adiciona(this.email);
				fornecedor.getEmail().setId(idEmail);
				FornecedorDao fornecedorDao = new  FornecedorDao(conexao);
				fornecedorDao.adiciona(fornecedor);		
				
				conexao.commit();

				return true;
	} catch (Exception e) {
		System.out.println("Entrou no ROLLBACK do service!");
		conexao.rollback();
		return false;
	}finally {
		try {
			conexao.setAutoCommit(true);
		} catch (SQLException e) {
			System.out.println("Não foi possivel liberar os recursos");
		}
	}	
}
}
