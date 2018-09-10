package br.eriquim.homerun.dom;

public class Modelo extends PersistenceDB{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4770571406554985036L;
	
	
	private String nome;
	private int ano;
	private Marca marca;
	
	public Modelo() {
		marca = new Marca();
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
