package br.com.ffsd.tcc.logica;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ffsd.tcc.dao.ItensPedidoDao;
import br.com.ffsd.tcc.dao.StatusPedidoDao;
import br.com.ffsd.tcc.modelo.ItensPedido;
import br.com.ffsd.tcc.modelo.StatusPedido;

public class ItensPedidoLogica implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Connection conexao = (Connection) req.getAttribute("conexao");
		
		int id = Integer.parseInt(req.getParameter("id"));
		int idStatusPedido = Integer.parseInt(req.getParameter("idStatusPedido"));
		ItensPedidoDao itensPedidoDao = new ItensPedidoDao(conexao);
		List<ItensPedido> listItensPedido = itensPedidoDao.getListaByIdPedido(id);
		StatusPedidoDao statusPedidoDao = new StatusPedidoDao(conexao);
		List<StatusPedido> statusPedidos = statusPedidoDao.getLista();
		req.setAttribute("itensPedido", listItensPedido);
		req.setAttribute("idStatusPedidoSelecionado", idStatusPedido);
		req.setAttribute("statusPedido", statusPedidos);
		req.setAttribute("idPedido", id);
		return "logado/admitenspedido.jsp";
	}

}
