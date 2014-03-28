package br.com.ffsd.tcc.modelo;

import java.util.Calendar;

public class Pedido {
	private int id;
	private double valor;
	private Calendar dataPedido;
	Cliente cliente = new Cliente();
	Endereco endereco = new Endereco();
	StatusPedido statusPedido = new StatusPedido();
	FormasDePagamento formasDePagamento = new FormasDePagamento();
	
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Calendar getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Calendar dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public StatusPedido getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}
	public FormasDePagamento getFormasDePagamento() {
		return formasDePagamento;
	}
	public void setFormasDePagamento(FormasDePagamento formasDePagamento) {
		this.formasDePagamento = formasDePagamento;
	}
	
}