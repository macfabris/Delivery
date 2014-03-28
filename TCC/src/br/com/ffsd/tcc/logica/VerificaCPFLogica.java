package br.com.ffsd.tcc.logica;

import java.io.PrintWriter;
import java.sql.Connection;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;



import br.com.ffsd.tcc.dao.ClienteDao;

public class VerificaCPFLogica implements Logica {

	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		Connection conexao = (Connection) req.getAttribute("conexao");
		ClienteDao clienteDao  = new ClienteDao(conexao);
		int qtdRegistros = clienteDao.verificaCPF(req.getParameter("cpf"));
		JSONObject json = new JSONObject();
		json.put("qtd", qtdRegistros);
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.print(json);
		out.close();
		return "cadastro-cliente.jsp";
	}

}

