/**
 * 
 */
package br.ufpi.easii.model;


/**
 * Esta classe corresponde as mensagens de texto que serão enviadas e recebidas pelos clientes.
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
		
	/**
	 * @param remetente - cliente que está enviando a mensagem
	 * @param destino - destinatário da mensagem
	 * @param dados - carga util da mensagem
	 */
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
