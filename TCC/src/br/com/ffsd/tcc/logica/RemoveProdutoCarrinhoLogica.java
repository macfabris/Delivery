package br.com.ffsd.tcc.logica;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ffsd.tcc.modelo.ItensPedido;



public class RemoveProdutoCarrinhoLogica implements Logica {

	@SuppressWarnings("unchecked")
	public String executa(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		
		HttpSession sessao = req.getSession();

		int posicao = Integer.parseInt(req.getParameter("id")) - 1;
		ArrayList<ItensPedido> list = (ArrayList<ItensPedido>) sessao.getAttribute("itensPedido");     
		double totalPedido = (Double)sessao.getAttribute("totalPedido");
		  
		ItensPedido itensPedido = list.get(posicao);
		totalPedido = totalPedido - itensPedido.getTotal();  
		sessao.setAttribute("totalPedido", totalPedido);
		list.remove(posicao);
		
		return "carrinho.jsp";
	}

}

