package br.ufpi.easii.model;

import java.io.Serializable;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1740383703572006268L;
	
	private Contato remetente;
	
	/**
	 * @param remetente
	 * @param destino
	 */
	public Message(Contato remetente) {
		this.remetente = remetente;
	}

	/**
	 * @param mensagem
	 */
	public Message(Message mensagem) {
		this.remetente = mensagem.remetente;
	}

	/**
	 * @return the remetente
	 */
	public Contato getRemetente() {
		return remetente;
	}
}
