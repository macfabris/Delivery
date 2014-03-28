package br.com.ffsd.tcc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutLogica implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession sessao = req.getSession();
		sessao.invalidate();
		
		return "index.jsp";
	}

}
