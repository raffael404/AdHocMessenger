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
	
	@Generated(value = { "" })
	private Long id;
	private String ipRemetente;
	private String ipDestino;
	private Integer ttl;
	private String dados;
	
	/**
	 * @param id
	 * @param ipRemetente
	 * @param ipDestino
	 * @param ttl
	 * @param dados
	 */
	public Mensagem(String ipRemetente, String ipDestino, Integer ttl,
			String dados) {
		super();
		this.id = new Random().nextLong();
		this.ipRemetente = ipRemetente;
		this.ipDestino = ipDestino;
		this.ttl = ttl;
		this.dados = dados;
	}
	
	/**
	 * @param mensagem
	 */
	public Mensagem(Mensagem mensagem) {
		this.id = mensagem.id;
		this.ipRemetente = mensagem.ipRemetente;
		this.ipDestino = mensagem.ipDestino;
		this.ttl = mensagem.ttl;
		this.dados = mensagem.dados;
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
	 * @return the ipRemetente
	 */
	public String getIpRemetente() {
		return ipRemetente;
	}

	/**
	 * @param ipRemetente the ipRemetente to set
	 */
	public void setIpRemetente(String ipRemetente) {
		this.ipRemetente = ipRemetente;
	}

	/**
	 * @return the ipDestino
	 */
	public String getIpDestino() {
		return ipDestino;
	}

	/**
	 * @param ipDestino the ipDestino to set
	 */
	public void setIpDestino(String ipDestino) {
		this.ipDestino = ipDestino;
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
