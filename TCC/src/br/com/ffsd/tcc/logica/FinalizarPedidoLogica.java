package br.com.ffsd.tcc.logica;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ffsd.tcc.dao.ClienteDao;
import br.com.ffsd.tcc.dao.ClienteUsuarioDao;
import br.com.ffsd.tcc.dao.EnderecoDao;
import br.com.ffsd.tcc.dao.FormasDePagamentoDao;
import br.com.ffsd.tcc.dao.UsuarioDao;
import br.com.ffsd.tcc.modelo.Cliente;
import br.com.ffsd.tcc.modelo.Endereco;
import br.com.ffsd.tcc.modelo.FormasDePagamento;
import br.com.ffsd.tcc.modelo.Usuario;

public class FinalizarPedidoLogica implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Connection conexao = (Connection) req.getAttribute("conexao");
		
		HttpSession sessao = req.getSession();
		Usuario usuario = (Usuario)sessao.getAttribute("usuario");
		String login = usuario.getLogin();
		UsuarioDao  usuarioDao = new UsuarioDao(conexao);
		int idUsuario = usuarioDao.getUsuarioById(login);

		
		ClienteUsuarioDao clienteUsuarioDao = new ClienteUsuarioDao(conexao);
		int idCliente = clienteUsuarioDao.getIdCliente(idUsuario);

		
		ClienteDao clienteDao = new ClienteDao(conexao);
		Cliente cliente = clienteDao.getClienteById(idCliente);
		req.setAttribute("cliente", cliente);
		
		EnderecoDao enderecoDao = new EnderecoDao(conexao);
		List<Endereco> endereco = enderecoDao.getListaByIdCliente(idCliente);
		req.setAttribute("endereco", endereco);
		
		FormasDePagamentoDao formasDePagamentoDao = new FormasDePagamentoDao(conexao);
		List<FormasDePagamento> formasDePagamento = formasDePagamentoDao.getLista();
		req.setAttribute("formasDePagamento", formasDePagamento);
		
		return "logado/finalizar-compra.jsp";
	}

}
