package br.com.ffsd.tcc.logica;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


import br.com.ffsd.tcc.dao.EmailDao;

public class VerificaEmailLogica implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)	throws Exception {
		Connection conexao = (Connection) req.getAttribute("conexao");
		EmailDao emailDao  = new EmailDao(conexao);
		int qtdRegistros = emailDao.verificaEmail(req.getParameter("email"));
		System.out.println("Email ; "+qtdRegistros);
		JSONObject json = new JSONObject();
		json.put("qtd", qtdRegistros);
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.print(json);
		out.close();
		return "cadastro-cliente.jsp";
	}

}
