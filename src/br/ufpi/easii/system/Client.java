package br.ufpi.easii.system;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import br.ufpi.easii.model.Contato;
import br.ufpi.easii.model.SyncroMessage;
import br.ufpi.easii.model.TextMessage;
import br.ufpi.easii.model.routingTable.Registro;
import br.ufpi.easii.model.routingTable.TabelaDeRoteamento;


/**
 * Esta classe representa o nó conectado na aplicação. Nele existe o IP do grupo que participa,
 * o MulticastSocket que é responsável pelo envio e recebimento das mensagens de controle da aplicação,
 * o UDPSocket responsável pelo envio e recebimento das mensagens de texto,
 * uma lista de contatos conectados, e uma Tabela de Roteamento. 
 * @author Ronyerison
 *
 */
public class Client {
	private InetAddress groupIP;
	private MulticastSocket multicastSocket;
	private DatagramSocket UDPsocket;
	private List<Contato> contatos;
	private TabelaDeRoteamento routingTable;
	private Contato meuHost;
	
	/**
	 * @param meuHost - Endereço de IP e Nome do contato.
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public Client(Contato meuHost) throws UnknownHostException, IOException {
		groupIP = InetAddress.getByName("224.225.226.227");
		multicastSocket = new MulticastSocket(5000);
		multicastSocket.joinGroup(groupIP);
		UDPsocket = new DatagramSocket(5001);
		contatos = new ArrayList<Contato>();
		this.meuHost = meuHost;
		routingTable = new TabelaDeRoteamento();
	}
	
	/**
	 * Método que monta o datagrama UDP e envia a mensagem de Sincronização com a Tabela de Roteamento.
	 * @param mensagem - Objeto SyncroMessage que será enviado pelo datagrama UDP.
	 * @throws Exception
	 */
	public void sendMulticastMessage(SyncroMessage mensagem) throws Exception{
		byte[] dados = SerializationUtils.serialize((Serializable) mensagem);
		DatagramPacket datagram = new DatagramPacket(dados, dados.length, groupIP, 5000);
		multicastSocket.send(datagram);
	}
	
	/**
	 * Método que monta o datagrama UDP e envia a mensagem de texto.
	 * @param message - Objeto TextMessage que será enviado pelo datagrama UDP.
	 * @throws IOException
	 */
	public void sendUDPMessage(TextMessage message) throws IOException{
		byte data[] = SerializationUtils.serialize((Serializable) message);
		InetAddress destiny = InetAddress.getByName(routingTable.findExitByName(message.getDestino().getNome()).getIp()); 
		DatagramPacket sendPacket = new DatagramPacket(data, data.length, destiny, 5001);
		UDPsocket.send(sendPacket);
	}

	
	/**
	 * @param contato - contato que é buscado na lista de contatos
	 * @return verdadeiro se o contato está na lista e falso se não.
	 */
	public boolean estaNaListaDeContatos(Contato contato){
		for (Contato item : contatos) {
			if(item.equals(contato))
				return true;
		}
		return false;
	}
	
	/**
	 * Método que busca o contato pelo nome na tabela de Roteamento do CLiente.
	 * @param contactName - nome do contato que será buscado
	 * @return Contato destino da mensagem
	 */
	public Contato findByName(String contactName){
		for (Registro register : routingTable.getRegistros()) {
			if(register.getDestino().getNome().equalsIgnoreCase(contactName))
				return register.getDestino();
		}
		return null;
	}

	/**
	 * @return the routingTable
	 */
	public TabelaDeRoteamento getRoutingTable() {
		return routingTable;
	}
	
	/**
	 * @return the groupIP
	 */
	public InetAddress getGroupIP() {
		return groupIP;
	}
	
	/**
	 * @return the socket
	 */
	public MulticastSocket getMulticastSocket() {
		return multicastSocket;
	}
	
	/**
	 * @return the UDPsocket
	 */
	public DatagramSocket getUDPsocket() {
		return UDPsocket;
	}
	
	/**
	 * @return the contatos
	 */
	public List<Contato> getContatos() {
		return contatos;
	}
	
	/**
	 * @return the meuHost
	 */
	public Contato getMeuHost() {
		return meuHost;
	}
	
	
}
