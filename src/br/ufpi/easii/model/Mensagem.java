/**
 * 
 */
package br.ufpi.easii.model;

import java.io.Serializable;


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
	public Mensagem(Long id, String ipRemetente, String ipDestino, Integer ttl,
			String dados) {
		super();
		this.id = id;
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
	
	
}
