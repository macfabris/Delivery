package br.com.ffsd.tcc.logica;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import br.com.ffsd.tcc.dao.UsuarioDao;

public class VerificaUsuarioLogica implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Connection conexao = (Connection) req.getAttribute("conexao");
		UsuarioDao usuarioDao  = new UsuarioDao(conexao);
		int qtdRegistros = usuarioDao.verificaLogin(req.getParameter("usuario"));
		System.out.println("Usuario ; "+qtdRegistros);
		JSONObject json = new JSONObject();
		json.put("qtd", qtdRegistros);
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.print(json);
		out.close();
		return "cadastro-cliente.jsp";
	}

}
