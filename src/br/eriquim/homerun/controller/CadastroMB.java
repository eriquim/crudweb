/**
 * 
 */
package br.eriquim.homerun.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import br.eriquim.homerun.dao.MarcaDAO;
import br.eriquim.homerun.dao.ModeloDAO;
import br.eriquim.homerun.dom.Marca;
import br.eriquim.homerun.dom.Modelo;
import br.eriquim.homerun.service.MarcaServiceImpl;
import br.eriquim.homerun.service.ModeloServiceImpl;

/**
 * @author eriquim
 *
 */
@SessionScoped
@ManagedBean(name="cadastroMB")
public class CadastroMB {
	public static final String INICIO = "index";
	public static final String CRUD_MODELO = "modelo";
	public static final String CRUD_MARCA = "marca";
	
	public Modelo modelo;
	
	public List<Modelo> listaModelos;
	
	public DataModel<Modelo> dtModelModelos;
	
	public Marca marca;
	
	public List<Marca> listaMarcas;

	public DataModel<Marca> dtModelMarcas;
	
	public ModeloServiceImpl modeloService;
	
	public MarcaServiceImpl marcaService;
	
	
	public CadastroMB() {
		reset();
		modeloService = new ModeloServiceImpl();
		marcaService = new MarcaServiceImpl();
	}
	
	private void reset() {
		modelo = new Modelo();
		marca = new Marca();
		listaModelos = null;
		listaMarcas = null;
	}
	
	public String inicio() {
		reset();
		return INICIO;
	}
	
	public String cadastroMarca() {
		reset();
		try {
			marca = new Marca();
			listaMarcas =marcaService.findAll();
			dtModelMarcas = new ListDataModel<Marca>(listaMarcas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CRUD_MARCA;
	}
	
	public String cadastroModelo() {
		reset();
		try {
			modelo = new Modelo();
			listaModelos = modeloService.findAll();
			dtModelModelos = new ListDataModel<Modelo>(listaModelos);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CRUD_MODELO;
	}
	
	public String salvarMarca() {
		try {
			if(marca.getId()==0) {
				marcaService.inserir(marca);
			} else {
				marcaService.update(marca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cadastroMarca();
	}
	
	public String salvarModelo() {
		try {
			if(modelo.getId()==0) {
				modeloService.inserir(modelo);
			} else {
				modeloService.update(modelo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cadastroModelo();
	}
	
	public String editarMarca(ActionEvent evt) {
		reset();
		Marca marcaAux = dtModelMarcas.getRowData();
		try {
			marca = marcaService.findById(marcaAux.getId());
			listaMarcas = marcaService.findAll();
			dtModelMarcas = new ListDataModel(listaMarcas);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return CRUD_MARCA;
	}
	
	public String editarModelo(ActionEvent evt) {
		reset();
		Modelo modeloAux = dtModelModelos.getRowData();
		try {
			modelo = modeloService.findById(modeloAux.getId());
			listaModelos = modeloService.findAll();
			dtModelModelos = new ListDataModel(listaModelos);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return CRUD_MODELO;
	}

	public String removerMarca() {
		Marca marcaAux = dtModelMarcas.getRowData();
		try {
			marcaService.remove(marcaAux);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return cadastroMarca();
	}
	
	public String removerModelo() {
		Modelo modeloAux = dtModelModelos.getRowData();
		try {
			modeloService.remove(modeloAux);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cadastroModelo();
	}
	
	public List<SelectItem> getAllMarcasSelectItem(){
		List<SelectItem> retorno = new ArrayList<SelectItem>();
		try {
			for (Marca marca : marcaService.findAll()) {
				SelectItem selectItem = new SelectItem(marca.getId(),marca.getNome());
				retorno.add(selectItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public List<Modelo> getListaModelos() {
		return listaModelos;
	}

	public void setListaModelos(List<Modelo> listaModelos) {
		this.listaModelos = listaModelos;
	}

	public DataModel<Modelo> getDtModelModelos() {
		return dtModelModelos;
	}

	public void setDtModelModelos(DataModel<Modelo> dtModelModelos) {
		this.dtModelModelos = dtModelModelos;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Marca> getListaMarcas() {
		return listaMarcas;
	}

	public void setListaMarcas(List<Marca> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}

	public DataModel<Marca> getDtModelMarcas() {
		return dtModelMarcas;
	}

	public void setDtModelMarcas(DataModel<Marca> dtModelMarcas) {
		this.dtModelMarcas = dtModelMarcas;
	}
}
