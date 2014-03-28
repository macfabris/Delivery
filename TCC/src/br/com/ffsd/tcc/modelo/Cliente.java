package br.com.ffsd.tcc.modelo;

import java.util.Calendar;

public class Cliente {
	private int id;
	private String nome,cpf;
	private Calendar dataCadastro,dataNascimento;
	public StatusCliente statusCliente = new  StatusCliente();

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getId() {
		return id;
	}
	public StatusCliente getStatusCliente() {
		return statusCliente;
	}
	public void setStatusCliente(StatusCliente statusCliente) {
		this.statusCliente = statusCliente;
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
	public Calendar getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
