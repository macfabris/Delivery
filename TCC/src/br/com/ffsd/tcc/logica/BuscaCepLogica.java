package br.com.ffsd.tcc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ffsd.tcc.service.CepWebService;

public class BuscaCepLogica implements Logica {
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String pagina = null;
		try {
	        String cep = req.getParameter("cep");
	        pagina = req.getParameter("pagina");


	        CepWebService cepWebService = new CepWebService(cep);
	            
	        if (cepWebService.getResultado()==1) {
	            req.setAttribute("endereco",cepWebService);
	        }
	        else {
	           if(pagina.equals("cadastro-cliente.jsp")){
	        	   req.setAttribute("msgCep", "CEP não encontrado");
	        	   return "login-cadastro.jsp";
	           }else if (pagina.equals("conf-cadastro-cliente-endereco.jsp")){
	        	   req.setAttribute("msgCep", "CEP não encontrado");
	        	   return "cadastro-cliente-endereco.jsp";
	           }
	        }            
	    }catch (Exception ex) {
	        ex.printStackTrace();
	    }
		return pagina;
	}  
}    
