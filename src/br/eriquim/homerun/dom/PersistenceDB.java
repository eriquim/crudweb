/**
 * 
 */
package br.eriquim.homerun.dom;

import java.io.Serializable;

/**
 * @author eriquim
 *
 */
public abstract class PersistenceDB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9101468767420910835L;
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
