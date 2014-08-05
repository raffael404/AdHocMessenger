/**
 * 
 */
package br.ufpi.easii.model.routingTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.ufpi.easii.model.Contato;

/**
 * @author Ronyerison
 *
 */
public class TabelaDeRoteamento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7826347453199082304L;
	private List<Registro> registros;
	
	/**
	 * 
	 */
	public TabelaDeRoteamento() {
		this.registros = new ArrayList<Registro>();
	}
	
	public void addRegistro(Registro registro){
		this.registros.add(registro);
	}
	
	public void removeRegistro(Registro registro){
		this.registros.remove(registro);
	}
	
	public Registro encontrarRegistro(Contato contato){
		for (Registro registro : this.registros) {
			if(registro.getDestino().equals(contato)){
				return registro;
			}
		}
		return null;
	}
	
	public boolean isOnTable(Contato contato){
		for (Registro registro : registros) {
			if(registro.getDestino().equals(contato))
				return true;
		}
		return false;
	}

	/**
	 * @return the registros
	 */
	public List<Registro> getRegistros() {
		return registros;
	}
	
	public Contato findExitByName(String name){
		for (Registro registro : registros) {
			if(registro.getDestino().getNome().equalsIgnoreCase(name))
				return registro.getSaida();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TabelaDeRoteamento [registros=" + registros.toString() + "]";
	}
}
