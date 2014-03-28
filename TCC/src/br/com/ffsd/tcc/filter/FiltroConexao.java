package br.com.ffsd.tcc.filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.ffsd.tcc.singleton.BDSingleton;



public class FiltroConexao implements Filter {

	public void destroy() {
		

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		Connection conexao = null;
		try {
			conexao = BDSingleton.getInstancia().getConexao();
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		req.setAttribute("conexao", conexao);
		
		chain.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {
		

	}

}
