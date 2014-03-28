package br.com.ffsd.tcc.logica;


import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ffsd.tcc.dao.ClienteDao;
import br.com.ffsd.tcc.dao.ClienteEmailDao;


import br.com.ffsd.tcc.dao.EmailDao;
import br.com.ffsd.tcc.dao.EnderecoDao;
import br.com.ffsd.tcc.dao.PedidoDao;
import br.com.ffsd.tcc.dao.UsuarioDao;
import br.com.ffsd.tcc.modelo.Cliente;
import br.com.ffsd.tcc.modelo.Email;
import br.com.ffsd.tcc.modelo.Endereco;
import br.com.ffsd.tcc.modelo.Pedido;
import br.com.ffsd.tcc.modelo.Usuario;


public class AlteraClienteLogica implements Logica {

	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Connection conexao = (Connection) req.getAttribute("conexao");
		
		Cliente cliente = new Cliente();
		cliente.setId(Integer.parseInt(req.getParameter("idCliente")));
		cliente.setNome(req.getParameter("nome"));
			
		ClienteDao clienteDao = new ClienteDao(conexao);
		clienteDao.altera(cliente);
		
		Email email = new Email();
		email.setId(Integer.parseInt(req.getParameter("idEmail")));
		email.setDescricao(req.getParameter("email"));
		
		EmailDao emailDao = new EmailDao(conexao);
		emailDao.altera(email);
		
		Usuario usuario = new Usuario();
		usuario.setId(Integer.parseInt(req.getParameter("idUsuario")));
		usuario.setSenha(req.getParameter("senha"));
		
		UsuarioDao usuarioDao = new UsuarioDao(conexao);
		usuarioDao.altera(usuario);
		
		int idCliente =Integer.parseInt(req.getParameter("idCliente"));
	
		//recebo uma lista com os dados do cliente e coloco num setAttribute para receber na jsp 
		 clienteDao = new ClienteDao(conexao);
		 cliente = clienteDao.getClienteById(idCliente);
		req.setAttribute("cliente", cliente);
		
		//pego o id do Email atraves do id do cliente
		ClienteEmailDao clienteEmailDao = new ClienteEmailDao(conexao);
		int idEmail = clienteEmailDao.getIdEmail(idCliente);
		//recebo uma lista com os dados do email e coloco num setAttribute para receber na jsp 
		
		email = emailDao.getEmailById(idEmail);
		req.setAttribute("email", email);
		

		EnderecoDao enderecoDao= new EnderecoDao(conexao);
		List<Endereco> endereco = enderecoDao.getListaByIdCliente(idCliente);
		req.setAttribute("endereco", endereco);
		PedidoDao pedidoDao = new PedidoDao(conexao);
		List<Pedido> listPedidos = pedidoDao.getListaByCliente(Integer.parseInt(req.getParameter("idCliente")));
		req.setAttribute("pedido", listPedidos);
				
		return "Cliente.jsp";
	}
}