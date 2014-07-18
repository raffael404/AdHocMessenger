/**
 * 
 */
package br.ufpi.easii.model;

import java.io.Serializable;
import java.util.Random;

import javax.annotation.Generated;


/**
 * @author Ronyerison
 *
 */
public class Mensagem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1740383703572006268L;
	
	private Long id;
	private Contato remetente;
	private Contato destino;
	private Integer ttl;
	private String dados;
	private boolean syn;
	
	/**
	 * @param remetente
	 * @param destino
	 * @param ttl
	 * @param dados
	 * @param syn
	 */
	public Mensagem(Contato remetente, Contato destino, Integer ttl,
			String dados, boolean syn) {
		super();
		this.id = new Random().nextLong();
		this.remetente = remetente;
		this.destino = destino;
		this.ttl = ttl;
		this.dados = dados;
		this.syn = syn;
	}
	
	/**
	 * @param mensagem
	 */
	public Mensagem(Mensagem mensagem) {
		this.id = mensagem.id;
		this.remetente = mensagem.remetente;
		this.destino = mensagem.destino;
		this.ttl = mensagem.ttl;
		this.dados = mensagem.dados;
		this.syn = mensagem.syn;
	}

	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the remetente
	 */
	public Contato getRemetente() {
		return remetente;
	}

	/**
	 * @param remetente the remetente to set
	 */
	public void setRemetente(Contato remetente) {
		this.remetente = remetente;
	}

	/**
	 * @return the destino
	 */
	public Contato getDestino() {
		return destino;
	}

	/**
	 * @param destino the destino to set
	 */
	public void setDestino(Contato destino) {
		this.destino = destino;
	}

	/**
	 * @return the ttl
	 */
	public Integer getTtl() {
		return ttl;
	}

	/**
	 * @param ttl the ttl to set
	 */
	public void setTtl(Integer ttl) {
		this.ttl = ttl;
	}

	/**
	 * @return the dados
	 */
	public String getDados() {
		return dados;
	}

	/**
	 * @param dados the dados to set
	 */
	public void setDados(String dados) {
		this.dados = dados;
	}

	
	
	/**
	 * @return the syn
	 */
	public boolean isSyn() {
		return syn;
	}

	/**
	 * @param syn the syn to set
	 */
	public void setSyn(boolean syn) {
		this.syn = syn;
	}

	public void decrementaTtl(){
		this.ttl = this.ttl - 1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Mensagem other = (Mensagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
