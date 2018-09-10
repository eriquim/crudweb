package br.eriquim.homerun.dom;

import java.io.Serializable;

public class Modelo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4770571406554985036L;
	
	
	private int id;
	private String nome;
	private int ano;
	private Marca marca;
	
	public Modelo() {
		marca = new Marca();
	}
	
	public int getId() {
		return id;
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
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
}
