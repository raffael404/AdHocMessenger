/**
 * 
 */
package br.ufpi.easii.model;


/**
 * @author Ronyerison
 *
 */
public class TextMessage extends Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = 406250747554768863L;
	private String dados;
	private Contato destino;
		
	public TextMessage(Contato remetente, Contato destino, String dados) {
		super(remetente);
		this.destino = destino;
		this.dados = dados;
	}

	/**
	 * @return the dados
	 */
	public String getDados() {
		return dados;
	}

	/**
	 * @return the destino
	 */
	public Contato getDestino() {
		return destino;
	}
	
}
