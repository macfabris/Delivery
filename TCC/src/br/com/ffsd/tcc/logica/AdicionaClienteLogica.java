package br.com.ffsd.tcc.logica;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.com.ffsd.tcc.modelo.Cliente;
import br.com.ffsd.tcc.modelo.Email;
import br.com.ffsd.tcc.modelo.Endereco;
import br.com.ffsd.tcc.modelo.Usuario;
import br.com.ffsd.tcc.service.ClienteService;

public class AdicionaClienteLogica implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		
		//intancia e preenche o objeto CLIENTE
		Cliente cliente = new Cliente();
		cliente.setNome(req.getParameter("nome"));
		String dataNascimentoTexto = req.getParameter("dataNascimento");
		System.out.println(dataNascimentoTexto);
		Calendar dataNascimento = Calendar.getInstance();
		try{
			java.util.Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimentoTexto);
			dataNascimento.setTime(data);
			cliente.setDataNascimento(dataNascimento);

		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		String dataCadastroTexto = req.getParameter("dataCadastro");;
		Calendar dataCadastro = Calendar.getInstance();
		try{
			java.util.Date data2 = new SimpleDateFormat("dd/MM/yyyy").parse(dataCadastroTexto);
			dataCadastro.setTime(data2);
			cliente.setDataCadastro(dataCadastro);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		cliente.getStatusCliente().setId(1);
		cliente.getStatusCliente().setDescricao("Ativo");
		String cpf = req.getParameter("cpf");
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		cliente.setCpf(cpf);
		
		//intancia e preenche o objeto ENDEREÇO
		Endereco endereco = new Endereco();
		endereco.setLogradouro(req.getParameter("logradouro"));
		endereco.setNumero(Integer.parseInt(req.getParameter("numero")));
		endereco.setComplemento(req.getParameter("complemento"));
		endereco.setBairro(req.getParameter("bairro"));
		endereco.setCidade(req.getParameter("cidade"));
		endereco.setEstado(req.getParameter("estado"));
		
		System.out.println("SETEI O ENDERECO!!!!");
		
		//intancia e preenche o objeto EMAIL
		Email email =new Email();
		email.setDescricao(req.getParameter("email"));
		
		//intancia e preenche o objeto USUARIO
		Usuario usuario = new Usuario();
		usuario.setLogin(req.getParameter("usuario"));
		usuario.setSenha(req.getParameter("senha"));
		usuario.setTipoUsuario("Cliente");
		
		Connection conexao = (Connection) req.getAttribute("conexao");
		
		ClienteService objCliServ = new ClienteService(cliente,email,endereco,usuario,conexao);
		boolean sucesso = objCliServ.Adiciona();
		req.setAttribute("sucesso", sucesso);
			return "cliente-adicionado.jsp";
			
		
	}

}
