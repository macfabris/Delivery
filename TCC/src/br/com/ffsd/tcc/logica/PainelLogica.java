package br.com.ffsd.tcc.logica;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ffsd.tcc.dao.ClienteDao;
import br.com.ffsd.tcc.dao.ClienteEmailDao;
import br.com.ffsd.tcc.dao.ClienteUsuarioDao;
import br.com.ffsd.tcc.dao.EmailDao;

import br.com.ffsd.tcc.dao.EnderecoDao;
import br.com.ffsd.tcc.dao.PedidoDao;
import br.com.ffsd.tcc.dao.UsuarioDao;
import br.com.ffsd.tcc.modelo.Cliente;
import br.com.ffsd.tcc.modelo.Email;
import br.com.ffsd.tcc.modelo.Endereco;
import br.com.ffsd.tcc.modelo.Pedido;
import br.com.ffsd.tcc.modelo.Usuario;

public class PainelLogica implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		Connection conexao = (Connection) req.getAttribute("conexao");
		HttpSession sessao = req.getSession();
		Usuario usuarioSession = (Usuario) sessao.getAttribute("usuario");
		String login = usuarioSession.getLogin();
		UsuarioDao usuarioDao = new UsuarioDao(conexao);
		Usuario usuario = usuarioDao.verificaUsuarioByLogin(login);
		int idUsuario = usuario.getId();

		// pego o id do Cliente logado através do seu id de Usuario
		ClienteUsuarioDao clienteUsuarioDao = new ClienteUsuarioDao(conexao);
		int idCliente = clienteUsuarioDao.getIdCliente(idUsuario);
		// recebo uma lista com os dados do cliente e coloco num setAttribute
		// para receber na jsp
		ClienteDao clienteDao = new ClienteDao(conexao);
		Cliente cliente = clienteDao.getClienteById(idCliente);
		req.setAttribute("cliente", cliente);

		// pego o id do Email atraves do id do cliente
		ClienteEmailDao clienteEmailDao = new ClienteEmailDao(conexao);
		int idEmail = clienteEmailDao.getIdEmail(idCliente);
		// recebo uma lista com os dados do email e coloco num setAttribute para
		// receber na jsp
		EmailDao emailDao = new EmailDao(conexao);
		Email email = emailDao.getEmailById(idEmail);
		req.setAttribute("email", email);

		EnderecoDao enderecoDao = new EnderecoDao(conexao);
		List<Endereco> endereco = enderecoDao.getListaByIdCliente(idCliente);
		req.setAttribute("endereco", endereco);

		PedidoDao pedidoDao = new PedidoDao(conexao);
		List<Pedido> listPedidos = pedidoDao.getListaByCliente(idCliente);
		req.setAttribute("pedido", listPedidos);

		return "logado/Cliente.jsp";
	}
}
