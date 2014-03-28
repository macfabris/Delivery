package br.com.ffsd.tcc.logica;


import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import br.com.ffsd.tcc.dao.ClienteDao;
import br.com.ffsd.tcc.dao.ClienteEmailDao;
import br.com.ffsd.tcc.dao.ClienteEnderecoDao;
import br.com.ffsd.tcc.dao.ClienteUsuarioDao;
import br.com.ffsd.tcc.dao.EmailDao;
import br.com.ffsd.tcc.dao.EnderecoDao;
import br.com.ffsd.tcc.dao.PedidoDao;
import br.com.ffsd.tcc.dao.UsuarioDao;
import br.com.ffsd.tcc.modelo.Cliente;
import br.com.ffsd.tcc.modelo.ClienteEndereco;
import br.com.ffsd.tcc.modelo.Email;
import br.com.ffsd.tcc.modelo.Endereco;
import br.com.ffsd.tcc.modelo.Pedido;
import br.com.ffsd.tcc.modelo.Usuario;


public class AdicionaClienteEnderecoLogica implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		
		
		Connection conexao = (Connection) req.getAttribute("conexao");
		HttpSession sessao = req.getSession();
		Usuario usuarioSession = (Usuario)sessao.getAttribute("usuario");
		String login = usuarioSession.getLogin();
		UsuarioDao usuarioDao = new UsuarioDao(conexao);
		Usuario usuario = usuarioDao.verificaUsuarioByLogin(login);
		int idUsuario = usuario.getId();

		ClienteUsuarioDao clienteUsuarioDao = new ClienteUsuarioDao(conexao);
		int idCliente = clienteUsuarioDao.getIdCliente(idUsuario);	
		
		Endereco end = new Endereco();
		end.setLogradouro(req.getParameter("logradouro"));
		end.setNumero(Integer.parseInt(req.getParameter("numero")));
		end.setComplemento(req.getParameter("complemento"));
		end.setBairro(req.getParameter("bairro"));
		end.setCidade(req.getParameter("cidade"));
		end.setEstado(req.getParameter("estado"));
		
		EnderecoDao enderecoDao = new EnderecoDao(conexao);
		int idEndereco = enderecoDao.adiciona(end);
		
		ClienteEndereco clienteEndereco = new ClienteEndereco();
		clienteEndereco.setIdCliente(idCliente);
		clienteEndereco.setIdEndereco(idEndereco);
		
		ClienteEnderecoDao objClienteEnderecoDao = new ClienteEnderecoDao(conexao);
		objClienteEnderecoDao.adiciona(clienteEndereco);
		
		ClienteDao clienteDao = new ClienteDao(conexao);
		Cliente cliente = clienteDao.getClienteById(idCliente);
		req.setAttribute("cliente", cliente);
		
		//pego o id do Email atraves do id do cliente
		ClienteEmailDao clienteEmailDao = new ClienteEmailDao(conexao);
		int idEmail = clienteEmailDao.getIdEmail(idCliente);
		//recebo uma lista com os dados do email e coloco num setAttribute para receber na jsp 
		EmailDao emailDao = new EmailDao(conexao);
		Email email = emailDao.getEmailById(idEmail);
		req.setAttribute("email", email);
		

		List<Endereco> endereco= enderecoDao.getListaByIdCliente(idCliente);
		req.setAttribute("endereco", endereco);
		
		PedidoDao pedidoDao = new PedidoDao(conexao);
		List<Pedido> listPedidos = pedidoDao.getListaByCliente(idCliente);
		req.setAttribute("pedidos", listPedidos);

			
		return "Cliente.jsp";
			
		
	}

}
