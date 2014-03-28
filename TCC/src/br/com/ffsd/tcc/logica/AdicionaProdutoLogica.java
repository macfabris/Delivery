package br.com.ffsd.tcc.logica;

import java.sql.Connection;
import java.util.Calendar;
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

public class AdicionaProdutoLogica implements Logica {

	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		Connection conexao = (Connection) req.getAttribute("conexao");
		
		String produto = (String) req.getAttribute("produto");

		String descricao = (String) req.getAttribute("descricao");

		double pcusto = (Double) req.getAttribute("pcusto");

		double porcent = (Double) req.getAttribute("porcent");
		
		Calendar dataCadastro = (Calendar) req.getAttribute("dataCadastro");

		int grupoProduto = (Integer) req.getAttribute("grupoProduto");

		int unidadeDeMedida = (Integer) req.getAttribute("unidadeDeMedida");
		
		int estoque = (Integer) req.getAttribute("estoque");
		
		int idFornecedor = (Integer) req.getAttribute("fornecedor");

		String foto = (String) req.getAttribute("foto");

		String diretorio = "img/" + foto;

		Produto p = new Produto();

		p.setNome(produto);
		p.setPcCusto(pcusto);
		p.setPorcentLucro(porcent);
		p.setDescricao(descricao);
		p.setDiretorio(diretorio);
		p.setDataCadastro(dataCadastro);
		p.setEstoque(estoque);
		p.grupoProduto.setId(grupoProduto);
		p.unidadeDeMedida.setId(unidadeDeMedida);
		p.fornecedor.setId(idFornecedor);

		ProdutoDao produtoDao = new ProdutoDao(conexao);
		produtoDao.adiciona(p);

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
		

		return "Adm.jsp";
	}

}
