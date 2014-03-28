package br.com.ffsd.tcc.modelo;


import java.util.Calendar;

public class Produto {
	private int id;
	private String nome,descricao,diretorio;
	private Calendar dataCadastro;
	private double pcCusto, porcentLucro;
	private int estoque;
	private double valor;
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();
	public GrupoProduto grupoProduto = new GrupoProduto();
	public Fornecedor fornecedor = new Fornecedor();
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public int getId() {
		return id;
	}
	public UnidadeDeMedida getUnidadeDeMedida() {
		return unidadeDeMedida;
	}
	public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}
	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}
	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Calendar getDataCadastro() {
		return dataCadastro;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}
	public String getDiretorio() {
		return diretorio;
	}
	public void setPcCusto(double pcCusto) {
		this.pcCusto = pcCusto;
	}
	public double getPcCusto() {
		return pcCusto;
	}
	public void setPorcentLucro(double porcentLucro) {
		this.porcentLucro = porcentLucro;
	}
	public double getPorcentLucro() {
		return porcentLucro;
	}
}
