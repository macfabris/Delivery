package br.com.ffsd.tcc.logica;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ffsd.tcc.dao.ClienteUsuarioDao;
import br.com.ffsd.tcc.dao.ItensPedidoDao;
import br.com.ffsd.tcc.dao.PedidoDao;
import br.com.ffsd.tcc.dao.ProdutoDao;
import br.com.ffsd.tcc.modelo.ItensPedido;
import br.com.ffsd.tcc.modelo.Produto;
import br.com.ffsd.tcc.modelo.Usuario;
import br.com.ffsd.tcc.modelo.Pedido;

public class FinalizarCompraLogica implements Logica {

	@SuppressWarnings("unchecked")
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession sessao = req.getSession();
		Connection conexao = (Connection) req.getAttribute("conexao");
		
		Usuario usuario = (Usuario)sessao.getAttribute("usuario");
		int idUsuario = usuario.getId();
		ClienteUsuarioDao clienteUsuarioDao = new ClienteUsuarioDao(conexao);
		Pedido pedido = new Pedido();
		int idCliente = clienteUsuarioDao.getIdCliente(idUsuario);	
		pedido.getCliente().setId(idCliente);
		double totalPedido = (Double)sessao.getAttribute("totalPedido");
		pedido.setValor(totalPedido);
		String dataPedidoTexto = req.getParameter("dataPedido");
		Calendar dataPedido = Calendar.getInstance();
		try{
			java.util.Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataPedidoTexto);
			dataPedido.setTime(data);
			pedido.setDataPedido(dataPedido);

		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		pedido.getEndereco().setId(Integer.parseInt(req.getParameter("idEndereco")));
		pedido.getFormasDePagamento().setId(Integer.parseInt(req.getParameter("idfPagamento")));
		pedido.getStatusPedido().setId(1);
		PedidoDao pedidoDao = new PedidoDao(conexao);
		int idPedido = pedidoDao.adiciona(pedido);
		ArrayList<ItensPedido> list = (ArrayList<ItensPedido>) sessao.getAttribute("itensPedido"); 
		ArrayList<ItensPedido> i = new ArrayList<ItensPedido>();
		for(ItensPedido j : list){
			Produto produto = new Produto();
			produto.setId(j.getIdProduto());
			produto.setEstoque(j.getQuantidade());
			ProdutoDao produtoDao = new ProdutoDao(conexao);
			produtoDao.retiraEstoque(produto);
			ItensPedido itensPedido = new ItensPedido();
			itensPedido.setIdProduto(j.getIdProduto());
			itensPedido.setIdPedido(idPedido);
			itensPedido.setQuantidade(j.getQuantidade());
			itensPedido.setPreco(j.getPreco());
			itensPedido.setTotal(j.getTotal());
			list.remove(i);
			i.add(itensPedido);
		}
		
		ItensPedidoDao IPDao = new ItensPedidoDao(conexao);
		IPDao.adiciona(i);
		
		sessao.removeAttribute("totalPedido");
		sessao.removeAttribute("itensPedido");
		
		System.out.println(i);
		return "index.jsp";
	}
}
