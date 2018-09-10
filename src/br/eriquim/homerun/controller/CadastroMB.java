/**
 * 
 */
package br.eriquim.homerun.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

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
	
	private void addSucessoMsg(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem, 
        		mensagem));
	}
	private void addErrorMsg (String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,mensagem, 
				mensagem));
	}
	
	
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
			addErrorMsg("Erro ao iniciar cadastro de Marca");
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
			addErrorMsg("Erro ao iniciar o cadastro de Modelo.");
			e.printStackTrace();
		}
		return CRUD_MODELO;
	}
	
	public String salvarMarca() {
		try {
			if(marca.getId()==0) {
				marcaService.inserir(marca);
				addSucessoMsg("Marca inserida com sucesso.");
			} else {
				marcaService.update(marca);
				addSucessoMsg("Marca alterarda com sucesso.");
			}
		} catch (SQLException e) {
			addErrorMsg("Erro ao inserir ou alterar marca.");
			e.printStackTrace();
		}
		return cadastroMarca();
	}
	
	public String salvarModelo() {
		try {
			if(modelo.getId()==0) {
				modeloService.inserir(modelo);
				addSucessoMsg("Modelo inserido com sucesso.");
			} else {
				modeloService.update(modelo);
				addSucessoMsg("Modelo alterado com sucesso.");
			}
		} catch (SQLException e) {
			addErrorMsg("Erro ao inserir ou alterar modelo.");
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
			dtModelMarcas = new ListDataModel<Marca>(listaMarcas);
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
			dtModelModelos = new ListDataModel<Modelo>(listaModelos);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return CRUD_MODELO;
	}

	public String removerMarca() {
		Marca marcaAux = dtModelMarcas.getRowData();
		try {
			marcaService.remove(marcaAux);
			addSucessoMsg("Marca removida com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return cadastroMarca();
	}
	
	public String removerModelo() {
		Modelo modeloAux = dtModelModelos.getRowData();
		try {
			modeloService.remove(modeloAux);
			addSucessoMsg("Modelo removido com sucesso");
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
