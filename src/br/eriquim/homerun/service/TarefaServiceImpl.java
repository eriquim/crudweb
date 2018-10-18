package br.eriquim.homerun.service;

import java.sql.SQLException;
import java.util.List;

import br.eriquim.homerun.dao.TarefaDAO;
import br.eriquim.homerun.dom.Tarefa;
import br.eriquim.homerun.exception.BussinessException;

public class TarefaServiceImpl {
	
	TarefaDAO tarefaDAO;
	
	
	public List<Tarefa> findAll() throws SQLException {
		List<Tarefa> retorno;
		tarefaDAO = new TarefaDAO();
		try {
			retorno = tarefaDAO.getAll();
		} finally {
			tarefaDAO.close();
		}
		return retorno;
	}
	
	public void inserir(Tarefa tarefa) throws BussinessException {
		tarefaDAO = new TarefaDAO();
		try{
			tarefa.validate();
			tarefaDAO.save(tarefa);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BussinessException("Erro ao realizar o insert na base.");
		} finally {
			tarefaDAO.close();
		}
	}
	
	public void update(Tarefa tarefa) throws BussinessException {
		tarefaDAO = new TarefaDAO();
		try {
			tarefa.validate();
			tarefaDAO.update(tarefa);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BussinessException("Erro ao realizar o update na base.");
		} finally {
			tarefaDAO.close();
		}
	}

	public void remove(Tarefa tarefa) throws SQLException {
		tarefaDAO = new TarefaDAO();
		try {
			tarefaDAO.remove(tarefa);
		} finally {
			tarefaDAO.close();
		}	
	}
	
	public Tarefa findById( Integer id) throws SQLException {
		tarefaDAO = new TarefaDAO();
		try {
			return tarefaDAO.findByPrimaryKey(id);
		} finally {
			tarefaDAO.close();
		}
	}
}
