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
import br.com.ffsd.tcc.modelo.Fornecedor;
import br.com.ffsd.tcc.modelo.GrupoProduto;
import br.com.ffsd.tcc.modelo.Pedido;
import br.com.ffsd.tcc.modelo.Produto;
import br.com.ffsd.tcc.modelo.StatusPedido;
import br.com.ffsd.tcc.modelo.UnidadeDeMedida;

public class AdicionaEstoqueLogica implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		int idProduto = Integer.parseInt(req.getParameter("idProduto"));
		int quantidadeEstoque = Integer.parseInt(req.getParameter("estoque"));
		double pcCusto = Double.parseDouble(req.getParameter("pcusto"));
		double porcenLucro = Double.parseDouble(req.getParameter("porcent"));
		
		Connection conexao = (Connection) req.getAttribute("conexao");
		ProdutoDao produtoDao = new ProdutoDao(conexao);	
		Produto produto = new Produto();
		produto.setId(idProduto);
		produto.setEstoque(quantidadeEstoque);
		produto.setPcCusto(pcCusto);
		produto.setPorcentLucro(porcenLucro);
		produtoDao.adicionaEstoque(produto);
		
		ClienteDao clienteDao = new ClienteDao(conexao);
		List<Cliente> clientes = clienteDao.getLista();
		req.setAttribute("clientes", clientes);
		
		List<Produto> produtos = produtoDao.getLista();
		req.setAttribute("produtos", produtos);
		
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
		
		FornecedorDao fornecedorDao = new FornecedorDao(conexao);
		List<Fornecedor> fornecedores = fornecedorDao.getLista();
		req.setAttribute("fornecedores", fornecedores);
		
		return "logado/Adm.jsp";
	}

}
