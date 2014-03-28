package br.com.ffsd.tcc.service;

import java.sql.SQLException;

import br.com.ffsd.tcc.dao.ClienteDao;
import br.com.ffsd.tcc.dao.ClienteEmailDao;
import br.com.ffsd.tcc.dao.ClienteEnderecoDao;
import br.com.ffsd.tcc.dao.ClienteUsuarioDao;
import br.com.ffsd.tcc.dao.EmailDao;
import br.com.ffsd.tcc.dao.EnderecoDao;
import br.com.ffsd.tcc.dao.UsuarioDao;
import br.com.ffsd.tcc.modelo.Cliente;
import br.com.ffsd.tcc.modelo.ClienteEmail;
import br.com.ffsd.tcc.modelo.ClienteEndereco;
import br.com.ffsd.tcc.modelo.ClienteUsuario;

import br.com.ffsd.tcc.modelo.Email;
import br.com.ffsd.tcc.modelo.Endereco;
import br.com.ffsd.tcc.modelo.Usuario;

import java.sql.Connection;

public class ClienteService {
	Connection conexao;

	private Cliente objCliente;
	private Email objEmail;
	private Endereco objEnd;
	private Usuario objUsuario;

	public ClienteService(Cliente objCliente, Email objEmail,
			Endereco objEndereco, Usuario objUsuario,Connection conexao)  {
		this.objCliente = objCliente;
		this.objEmail = objEmail;
		this.objEnd = objEndereco;
		this.objUsuario = objUsuario;
		this.conexao = conexao ;

	}

	public boolean Adiciona() throws SQLException {
		try {
			conexao.setAutoCommit(false);
			// salva no bd o cliente
			ClienteDao clienteDao = new ClienteDao(conexao);
			int idCliente = clienteDao.adiciona(this.objCliente);
			System.out.println("TESTE");

			// salva no bd o endereco
			EnderecoDao enderecoDao = new EnderecoDao(conexao);
			int idEndereco = enderecoDao.adiciona(this.objEnd);

			// salva no bd o email
			EmailDao emailDao = new EmailDao(conexao);
			int idEmail = emailDao.adiciona(this.objEmail);

			// salva no bd o usuario
			UsuarioDao usuarioDao = new UsuarioDao(conexao);
			int idUsuario = usuarioDao.adiciona(this.objUsuario);

			// intancia e preenche o objeto CLIENTEENDERECO
			ClienteEndereco clienteEndereco = new ClienteEndereco();
			clienteEndereco.setIdCliente(idCliente);
			clienteEndereco.setIdEndereco(idEndereco);

			// salva na tabela relacional CLIENTEENDERECO os id's respectivos
			ClienteEnderecoDao objClienteEnderecoDao = new ClienteEnderecoDao(conexao);
			objClienteEnderecoDao.adiciona(clienteEndereco);

			// intancia e preenche o objeto CLIENTEEMAIL
			ClienteEmail clienteEmail = new ClienteEmail();
			clienteEmail.setIdCliente(idCliente);
			clienteEmail.setIdEmail(idEmail);

			// salva na tabela relacional CLIENTEEMAIL os id's respectivos
			ClienteEmailDao clienteEmailDao = new ClienteEmailDao(conexao);
			clienteEmailDao.adiciona(clienteEmail);
			
			// intancia e preenche o objeto CLIENTEUSUARIO
			ClienteUsuario clienteUsuario = new ClienteUsuario();
			clienteUsuario.setIdCliente(idCliente);
			clienteUsuario.setIdUsuario(idUsuario);
			
			// salva na tabela relacional CLIENTEUSUARIO os id's respectivos
			ClienteUsuarioDao clienteUsuarioDao = new ClienteUsuarioDao(conexao);
			clienteUsuarioDao.adiciona(clienteUsuario);
			
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
