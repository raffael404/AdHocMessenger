/**
 * 
 */
package br.ufpi.easii.model;

import java.io.Serializable;

/**
 * @author Ronyerison
 *
 */
public class Contato implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2899600604799957245L;
	private String nome;
	private String ip;
	
	/**
	 * @param nome
	 * @param ip
	 */
	public Contato(String nome, String ip) {
		super();
		this.nome = nome;
		this.ip = ip;
	}
	
	
	/**
	 * @param contato
	 */
	public Contato(Contato contato) {
		this.nome = contato.nome;
		this.ip = contato.ip;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
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
		Contato other = (Contato) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		return true;
	}
	
	
	
}
