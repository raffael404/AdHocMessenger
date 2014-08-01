/**
 * 
 */
package br.ufpi.easii.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ronyerison
 *
 */
public class TabelaDeRoteamento {
	
	private List<Contato> destinos;
	private List<Contato> nosSaidas;
	
	/**
	 * 
	 */
	public TabelaDeRoteamento() {
		this.destinos = new ArrayList<Contato>();
		this.nosSaidas = new ArrayList<Contato>();
	}
	
	public void addRegistro(Contato destino, Contato noSaida){
		this.destinos.add(destino);
		this.nosSaidas.add(noSaida);
	}
	
	public Contato encontrarSaida(Contato contato){
		for (int i = 0; i < destinos.size(); i++) {
			if(destinos.get(i).equals(contato)){
				return this.nosSaidas.get(i);
			}
		}
		return null;
	}
	
}
