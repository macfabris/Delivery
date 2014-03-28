package br.com.ffsd.tcc.logica;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ffsd.tcc.modelo.ItensPedido;

public class AdicionaProdutoCarrinhoLogica implements Logica {

	public static List<ItensPedido> listItensPedidos = new ArrayList<ItensPedido>();

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		int idProduto = Integer.parseInt(req.getParameter("id"));
		int quantidade = Integer.parseInt(req.getParameter("numero"));
		String diretorio = req.getParameter("diretorio");
		double valorProduto = Double.parseDouble((req.getParameter("valorProduto")));
		double total = valorProduto * quantidade;
		String nomeProduto = req.getParameter("nomeProduto");


		ItensPedido itensPedido = new ItensPedido();
		itensPedido.setIdProduto(idProduto);
		itensPedido.setPreco(valorProduto);
		itensPedido.setQuantidade(quantidade);
		itensPedido.setTotal(total);
		itensPedido.getProduto().setNome(nomeProduto);
		itensPedido.getProduto().setDiretorio(diretorio);
		
		listItensPedidos.add(itensPedido);

		HttpSession session = req.getSession();
		double x = (Double) session.getAttribute("totalPedido") == null ? total
				: (Double) session.getAttribute("totalPedido") + total;
		
		session.setAttribute("itensPedido", listItensPedidos);
		session.setAttribute("totalPedido", x);
		

		return "index.jsp";
	}

}
