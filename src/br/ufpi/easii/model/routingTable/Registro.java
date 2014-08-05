/**
 * 
 */
package br.ufpi.easii.model.routingTable;

import java.io.Serializable;

import br.ufpi.easii.model.Contato;

/**
 * @author Ronyerison
 *
 */
public class Registro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2583830507448317710L;
	private Contato destino;
	private Contato saida;
	private Integer saltos;
	
	/**
	 * @param destino
	 * @param saida
	 * @param saltos
	 */
	public Registro(Contato destino, Contato saida, Integer saltos) {
		super();
		this.destino = destino;
		this.saida = saida;
		this.saltos = saltos;
	}

	/**
	 * @return the destino
	 */
	public Contato getDestino() {
		return destino;
	}

	/**
	 * @return the saida
	 */
	public Contato getSaida() {
		return saida;
	}
	
	/**
	 * @return the saltos
	 */
	public Integer getSaltos() {
		return saltos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Registro [destino=" + destino.toString() + ", saida=" + saida.toString()
				+ ", saltos=" + saltos + "]";
	}
	
}
