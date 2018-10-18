package br.eriquim.homerun.dom;

import br.eriquim.homerun.exception.BussinessException;

public class Tarefa extends PersistenceDB{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3667316394750413117L;
	
	public Tarefa() {}
	
	public Tarefa(String nome, String responsavel, StatusTarefa status) {
		this.nome = nome;
		this.responsavel = responsavel;
		this.statusTarefa = status;
	}
	
	
	private String nome;
	
	private String responsavel;
	
	private StatusTarefa statusTarefa;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public StatusTarefa getStatusTarefa() {
		return statusTarefa;
	}
	public void setStatusTarefa(StatusTarefa statusTarefa) {
		this.statusTarefa = statusTarefa;
	}
	@Override
	public void validate() throws BussinessException {
		if(nome == null || nome.isEmpty())
			throw new BussinessException("Atributo Nome Vazio");
		
	}
	
}
