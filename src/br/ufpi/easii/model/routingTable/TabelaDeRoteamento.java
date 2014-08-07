/**
 * 
 */
package br.ufpi.easii.model.routingTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.ufpi.easii.model.Contato;

/**
 * Classe que representa a tabela de roteamento dos nós, ela contém uma lista de Registros da tabela.
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
	
	/**
	 * Método que adiciona um registro na tabela.
	 * @param registro
	 */
	public void addRegistro(Registro registro){
		this.registros.add(registro);
	}
	
	/**
	 * Método que remove um registro da tabela.
	 * @param registro
	 */
	public void removeRegistro(Registro registro){
		this.registros.remove(registro);
	}
	
	/**
	 * Método que busca um registro na tabela pelo contato destino
	 * @param contato
	 * @return Registro da Tabela de Roteamento.
	 */
	public Registro encontrarRegistro(Contato contato){
		for (Registro registro : this.registros) {
			if(registro.getDestino().equals(contato)){
				return registro;
			}
		}
		return null;
	}
	
	/**
	 * Método que verifica se um contato está presente na tabela.
	 * @param contato
	 * @return verdadeiro se estiver presente e falso se não.
	 */
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
	
	/**
	 * Método que encontra o contato de saída dado o nome do destinatário da mensagem.
	 * @param name
	 * @return contato de saída.
	 */
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
