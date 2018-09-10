package br.eriquim.homerun.service;

import java.sql.SQLException;
import java.util.List;

import br.eriquim.homerun.dao.ModeloDAO;
import br.eriquim.homerun.dom.Modelo;

public class ModeloServiceImpl {
	
	ModeloDAO modeloDao;
	
	
	public List<Modelo> findAll() throws SQLException {
		List<Modelo> retorno;
		modeloDao = new ModeloDAO();
		try {
			retorno = modeloDao.getAll();
		} finally {
			modeloDao.close();
		}
		return retorno;
	}
	
	public void inserir(Modelo modelo) throws SQLException {
		modeloDao = new ModeloDAO();
		try {
			modeloDao.save(modelo);
		} finally {
			modeloDao.close();
		}
	}
	
	public void update(Modelo modelo) throws SQLException {
		modeloDao = new ModeloDAO();
		try {
			modeloDao.update(modelo);
		} finally {
			modeloDao.close();
		}
	}

	public void remove(Modelo modelo) throws SQLException {
		modeloDao = new ModeloDAO();
		try {
			modeloDao.remove(modelo);
		} finally {
			modeloDao.close();
		}
	}
	
	public Modelo findById(Integer id) throws SQLException {
		modeloDao = new ModeloDAO();
		try {
			return modeloDao.findByPrimaryKey(id);
		} finally {
			modeloDao.close();
		}
	}

}
