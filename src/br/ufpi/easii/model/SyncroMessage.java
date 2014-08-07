/**
 * 
 */
package br.ufpi.easii.model;

import br.ufpi.easii.model.routingTable.TabelaDeRoteamento;

/**
 * Classe que estende da classe Mensagem e ser� a mensagem de sincroniza��o que ser� enviada entre os n�s, 
 * na carga util � enviada a tabela de roteamento.
 * @author Rafael
 *
 */
public class SyncroMessage extends Message{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1623147398801415199L;
	private TabelaDeRoteamento routingTable;
	
	/**
	 * @param remetente - n� que est� enviando a tabela.
	 * @param routingTable - carga util da mensagem de sincroniza��o, objeto do tipo Tabela de Roteamento.
	 */
	public SyncroMessage(Contato remetente, TabelaDeRoteamento routingTable) {
		super(remetente);
		this.routingTable = routingTable;
	}
	
	/**
	 * @return - tabela de roteamento
	 */
	public TabelaDeRoteamento getRoutingTable() {
		return routingTable;
	}
}
