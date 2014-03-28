package br.com.ffsd.tcc.logica;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ffsd.tcc.dao.ClienteDao;
import br.com.ffsd.tcc.dao.FornecedorDao;
import br.com.ffsd.tcc.dao.GrupoProdutoDao;
import br.com.ffsd.tcc.dao.PedidoDao;
import br.com.ffsd.tcc.dao.ProdutoDao;
import br.com.ffsd.tcc.dao.StatusPedidoDao;
import br.com.ffsd.tcc.dao.UnidadeDeMedidaDao;
import br.com.ffsd.tcc.modelo.Cliente;
import br.com.ffsd.tcc.modelo.Email;
import br.com.ffsd.tcc.modelo.Fornecedor;
import br.com.ffsd.tcc.modelo.GrupoProduto;
import br.com.ffsd.tcc.modelo.Pedido;
import br.com.ffsd.tcc.modelo.Produto;
import br.com.ffsd.tcc.modelo.StatusPedido;
import br.com.ffsd.tcc.modelo.UnidadeDeMedida;
import br.com.ffsd.tcc.service.FornecedorService;

public class AdicionaFornecedorLogica implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
		Connection conexao = (Connection) req.getAttribute("conexao");
		System.out.println(conexao);
		
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome(req.getParameter("nome"));
		
		Email email = new Email();
		email.setDescricao(req.getParameter("email"));
		
		FornecedorService fornecedorService = new FornecedorService(fornecedor, email);
		boolean sucesso = fornecedorService.Adiciona();
		req.setAttribute("sucesso", sucesso);
		
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
		
		UnidadeDeMedidaDao unidadeDeMedidaDao = new UnidadeDeMedidaDao(conexao);
		List<UnidadeDeMedida> unidadesDeMedida = unidadeDeMedidaDao.getLista();
		req.setAttribute("unidadesDeMedida", unidadesDeMedida);
			
		PedidoDao pedidoDao = new PedidoDao(conexao);
		List<Pedido> pedidos = pedidoDao.getLista();
		req.setAttribute("pedidos", pedidos);
		
		StatusPedidoDao statusPedidoDao = new StatusPedidoDao(conexao);
		List<StatusPedido> status = statusPedidoDao.getLista();
		req.setAttribute("status", status);
		
		return "Adm.jsp";

	}

}
