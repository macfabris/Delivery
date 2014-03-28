package br.com.ffsd.tcc.service;


import java.net.URL;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class CepWebService {
    
    private String estado = "";
    private String cidade = "";
    private String bairro = "";
    private String tipo_logradouro = "";
    private String logradouro = "";
        
    private int resultado = 0;
    private String resultado_txt = "";    
    
    /** Creates a new instance of CepWebService */
    @SuppressWarnings({"rawtypes" })
	public CepWebService(String cep) {
        try {
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");

            Document document = getDocumento(url);

            Element root = document.getRootElement();

            for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                
                if (element.getQualifiedName().equals("uf"))
                    setEstado(element.getText());                
                
                if (element.getQualifiedName().equals("cidade"))
                    setCidade(element.getText());                
                
                if (element.getQualifiedName().equals("bairro"))
                    setBairro(element.getText());                
                
                if (element.getQualifiedName().equals("tipo_logradouro"))
                    setTipo_logradouro(element.getText());                
                
                if (element.getQualifiedName().equals("logradouro"))
                    setLogradouro(element.getText());                
                
                if (element.getQualifiedName().equals("resultado"))
                    setResultado(Integer.parseInt(element.getText()));                
                
                if (element.getQualifiedName().equals("resultado_txt"))
                    setResultado_txt(element.getText());                                
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    } 
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTipo_logradouro() {
        return tipo_logradouro;
    }

    public void setTipo_logradouro(String tipo_logradouro) {
        this.tipo_logradouro = tipo_logradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public String getResultado_txt() {
        return resultado_txt;
    }

    public void setResultado_txt(String resultado_txt) {
        this.resultado_txt = resultado_txt;
    }
    
}
