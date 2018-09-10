package br.eriquim.homerun.service;

import java.sql.SQLException;
import java.util.List;

import br.eriquim.homerun.dao.MarcaDAO;
import br.eriquim.homerun.dom.Marca;

public class MarcaServiceImpl {
	
	MarcaDAO marcaDao;
	
	
	public List<Marca> findAll() throws SQLException {
		List<Marca> retorno;
		marcaDao = new MarcaDAO();
		try {
			retorno = marcaDao.getAll();
		} finally {
			marcaDao.close();
		}
		return retorno;
	}
	
	public void inserir(Marca marca) throws SQLException {
		marcaDao = new MarcaDAO();
		try{
			marcaDao.save(marca);
		} finally {
			marcaDao.close();
		}
	}
	
	public void update(Marca marca) throws SQLException {
		marcaDao = new MarcaDAO();
		try {
			marcaDao.update(marca);
		} finally {
			marcaDao.close();
		}
	}

	public void remove(Marca marca) throws SQLException {
		marcaDao = new MarcaDAO();
		try {
			marcaDao.remove(marca);
		} finally {
			marcaDao.close();
		}	
	}
	
	public Marca findById( Integer id) throws SQLException {
		marcaDao = new MarcaDAO();
		try {
			return marcaDao.findByPrimaryKey(id);
		} finally {
			marcaDao.close();
		}
	}
}
