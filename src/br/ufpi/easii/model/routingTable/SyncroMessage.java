/**
 * 
 */
package br.ufpi.easii.model.routingTable;

import br.ufpi.easii.model.Contato;
import br.ufpi.easii.model.Message;

/**
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
	 * @param remetente
	 * @param destino
	 * @param routingTable
	 */
	public SyncroMessage(Contato remetente, TabelaDeRoteamento routingTable) {
		super(remetente);
		this.routingTable = routingTable;
	}
	
	public TabelaDeRoteamento getRoutingTable() {
		return routingTable;
	}
}
