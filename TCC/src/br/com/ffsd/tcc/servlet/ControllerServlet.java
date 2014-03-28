package br.com.ffsd.tcc.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.com.ffsd.tcc.logica.Logica;

@SuppressWarnings("serial")
public class ControllerServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Entrei no doGet");
		executarLogica(req, resp);
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Entrei no doPost");
		executarLogica(req, resp);
	}


	@SuppressWarnings("rawtypes")
	protected void executarLogica(HttpServletRequest req,
			HttpServletResponse resp) {

		String parametro;
		String nomeDaClasse = null;
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			String path = "C:/workspace2/TCC1/WebContent/img";
			String foto = null;
			DiskFileItemFactory fileupload = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(fileupload);
			List list;
			try {
				sfu.setSizeMax(2 * 1024 * 1024); // a imagem tamanho de 2Mb
				list = sfu.parseRequest(req);
				FileItem logicaFI = (FileItem) list.get(0);
				String logicaParametro = (logicaFI.getString());
				parametro = logicaParametro;
				nomeDaClasse = "br.com.ffsd.tcc.logica." + parametro;
				Iterator it = list.iterator();
				while (it.hasNext()) {
					FileItem item = (FileItem) it.next();
					System.out.println("2");
					if (!item.isFormField()) {
						System.out.println("3");
						foto = item.getName();
						if ((foto != null) & (!foto.equals(""))) {
							System.out.println("4");
							foto = (new File(foto)).getName();
							// gravo a imagem
							item.write(new File(path + "/" + foto));

							// pego os parametros para enviar via
							// setAtributte
							// para a logica
							if (parametro.equals("AdicionaProdutoLogica")) {
								FileItem produtoFI = (FileItem) list.get(1);
								String produto = (produtoFI.getString());
								System.out.println("Servlet Produto" + produto);
								req.setAttribute("produto", produto);
								FileItem descricaoFI = (FileItem) list.get(2);
								String descricao = (descricaoFI.getString());
								req.setAttribute("descricao", descricao);
								FileItem pcustoFI = (FileItem) list.get(3);
								double pcusto = Double.parseDouble((pcustoFI.getString()));
								req.setAttribute("pcusto", pcusto);
								FileItem porcentFI = (FileItem) list.get(4);
								double porcent = Double.parseDouble((porcentFI.getString()));
								req.setAttribute("porcent", porcent);
								FileItem dataCadastroFI = (FileItem) list.get(5);
								String dataCadastroTexto = dataCadastroFI.getString();
								Calendar dataCadastro = Calendar.getInstance();
								try {
									java.util.Date data = new SimpleDateFormat(
											"dd/MM/yyyy")
											.parse(dataCadastroTexto);
									dataCadastro.setTime(data);
									req.setAttribute("dataCadastro",
											dataCadastro);
								} catch (java.text.ParseException e) {
									e.printStackTrace();
								}
								FileItem grupoProdutoFI = (FileItem) list.get(6);
								int grupoProduto = Integer
										.parseInt(grupoProdutoFI.getString());
								req.setAttribute("grupoProduto", grupoProduto);
								FileItem unidadeDeMedidaFI = (FileItem) list.get(7);
								int unidadeDeMedida = Integer.parseInt(unidadeDeMedidaFI.getString());
								req.setAttribute("unidadeDeMedida",unidadeDeMedida);
								FileItem estoqueFI = (FileItem) list.get(9);
								int estoque = Integer.parseInt(estoqueFI.getString());
								req.setAttribute("estoque",estoque);
								FileItem fornecedorFI = (FileItem) list.get(8);
								int fornecedor = Integer.parseInt(fornecedorFI.getString());
								req.setAttribute("fornecedor",fornecedor);
								req.setAttribute("foto", foto);
								// fim dos set'sAtributte's
							}

						}// fechou if foto
					}// fechou if formfield
				}// fechou while
			} catch (Exception e) {
				System.out.println("Erro : " + e.getMessage());
			}
		} else {
			parametro = req.getParameter("logica");
			nomeDaClasse = "br.com.ffsd.tcc.logica." + parametro;
		}
		try {
			Class classe = Class.forName(nomeDaClasse);
			Logica logica = (Logica) classe.newInstance();
			System.out.println("Logica: " + logica);
			String pagina = logica.executa(req, resp);
			System.out.println("Pagina: " + pagina);
			if(!resp.isCommitted()){
				RequestDispatcher rd = req.getRequestDispatcher(pagina);
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			System.out.println("A lógica de negocios causou a exceção"
					+ e.getLocalizedMessage());
		}
	}

}
