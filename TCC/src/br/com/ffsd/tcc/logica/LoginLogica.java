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
import br.com.ffsd.tcc.dao.FornecedorDao;
import br.com.ffsd.tcc.dao.GrupoProdutoDao;
import br.com.ffsd.tcc.dao.PedidoDao;
import br.com.ffsd.tcc.dao.ProdutoDao;
import br.com.ffsd.tcc.dao.StatusPedidoDao;
import br.com.ffsd.tcc.dao.UnidadeDeMedidaDao;
import br.com.ffsd.tcc.dao.UsuarioDao;
import br.com.ffsd.tcc.modelo.Cliente;
import br.com.ffsd.tcc.modelo.Email;
import br.com.ffsd.tcc.modelo.Endereco;
import br.com.ffsd.tcc.modelo.Fornecedor;
import br.com.ffsd.tcc.modelo.GrupoProduto;
import br.com.ffsd.tcc.modelo.Pedido;
import br.com.ffsd.tcc.modelo.Produto;
import br.com.ffsd.tcc.modelo.StatusPedido;
import br.com.ffsd.tcc.modelo.UnidadeDeMedida;
import br.com.ffsd.tcc.modelo.Usuario;

public class LoginLogica implements Logica {

	@SuppressWarnings("unused")
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		Connection conexao = (Connection) req.getAttribute("conexao");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		UsuarioDao usuarioDao = new UsuarioDao(conexao);
		Usuario usuario = usuarioDao.verificaUsuario(login);
		if (usuario == null || !usuario.getSenha().equals(senha)) {
			req.setAttribute("msg", "Login ou senha incorretos");
			return "login-cadastro.jsp";
		}
		req.setAttribute("usuario", usuario);
		String tipoUsuario = usuario.getTipoUsuario();
		int idUsuario = usuario.getId();
		HttpSession session = req.getSession();
		session.setAttribute("usuario", usuario);

		if (tipoUsuario.equals("Cliente")) {
			// pego o id do Cliente logado através do seu id de Usuario
			ClienteUsuarioDao clienteUsuarioDao = new ClienteUsuarioDao(conexao);
			int idCliente = clienteUsuarioDao.getIdCliente(idUsuario);
			// recebo uma lista com os dados do cliente e coloco num
			// setAttribute para receber na jsp
			ClienteDao clienteDao = new ClienteDao(conexao);
			Cliente cliente = clienteDao.getClienteById(idCliente);
			req.setAttribute("cliente", cliente);

			// pego o id do Email atraves do id do cliente
			ClienteEmailDao clienteEmailDao = new ClienteEmailDao(conexao);
			int idEmail = clienteEmailDao.getIdEmail(idCliente);
			// recebo uma lista com os dados do email e coloco num setAttribute
			// para receber na jsp
			EmailDao emailDao = new EmailDao(conexao);
			Email email = emailDao.getEmailById(idEmail);
			req.setAttribute("email", email);

			EnderecoDao enderecoDao = new EnderecoDao(conexao);
			List<Endereco> endereco = enderecoDao
					.getListaByIdCliente(idCliente);
			req.setAttribute("endereco", endereco);

			PedidoDao pedidoDao = new PedidoDao(conexao);
			List<Pedido> pedidos = pedidoDao.getListaByCliente(idCliente);
			req.setAttribute("pedido", pedidos);

		} else if (tipoUsuario.equals("Adm")) {

			ClienteDao clienteDao = new ClienteDao(conexao);
			List<Cliente> clientes = clienteDao.getLista();
			req.setAttribute("clientes", clientes);

			ProdutoDao produtoDao = new ProdutoDao(conexao);
			List<Produto> produtos = produtoDao.getLista();
			req.setAttribute("produtos", produtos);
			
			FornecedorDao fornecedorDao = new FornecedorDao(conexao);
			List<Fornecedor> fornecedores = fornecedorDao.getLista();
			req.setAttribute("fornecedores", fornecedores);

			GrupoProdutoDao grupoProdutoDao = new GrupoProdutoDao(conexao);
			List<GrupoProduto> gruposProduto = grupoProdutoDao.getLista();
			req.setAttribute("gruposProduto", gruposProduto);

			UnidadeDeMedidaDao unidadeDeMedidaDao = new UnidadeDeMedidaDao(
					conexao);
			List<UnidadeDeMedida> unidadesDeMedida = unidadeDeMedidaDao
					.getLista();
			req.setAttribute("unidadesDeMedida", unidadesDeMedida);

			PedidoDao pedidoDao = new PedidoDao(conexao);
			List<Pedido> pedidos = pedidoDao.getLista();
			req.setAttribute("pedidos", pedidos);

			StatusPedidoDao statusPedidoDao = new StatusPedidoDao(conexao);
			List<StatusPedido> status = statusPedidoDao.getLista();
			req.setAttribute("status", status);

		}
		if (usuario != null) {
			return "logado/" + tipoUsuario + ".jsp";
		} else {
			return "index.jsp";
		}
	}

}
