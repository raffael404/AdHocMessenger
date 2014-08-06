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
import br.ufpi.easii.model.TextMessage;
import br.ufpi.easii.model.routingTable.Registro;
import br.ufpi.easii.model.routingTable.SyncroMessage;
import br.ufpi.easii.model.routingTable.TabelaDeRoteamento;

//Mudar o nome de ReceiveMessage para MessageReceiver
//Lançar a thread recebedor de mensagens fora do cliente
//Tirar a lista de mensagens recebidas do cliente e colocar no recebedor de mensagens

public class Client {
	private InetAddress groupIP;
	private MulticastSocket multicastSocket;
	private DatagramSocket UDPsocket;
	private List<Contato> contatos;
	private TabelaDeRoteamento routingTable;
	private Contato meuHost;
	
	public Client(Contato meuHost) throws UnknownHostException, IOException {
		groupIP = InetAddress.getByName("224.225.226.227");
		multicastSocket = new MulticastSocket(5000);
		multicastSocket.joinGroup(groupIP);
		UDPsocket = new DatagramSocket(5001);
		contatos = new ArrayList<Contato>();
		this.meuHost = meuHost;
		routingTable = new TabelaDeRoteamento();
	}
	
	public void sendMulticastMessage(SyncroMessage mensagem) throws Exception{
		byte[] dados = SerializationUtils.serialize((Serializable) mensagem);
		DatagramPacket datagram = new DatagramPacket(dados, dados.length, groupIP, 5000);
		multicastSocket.send(datagram);
	}
	
	public void sendUDPMessage(TextMessage message) throws IOException{
		byte data[] = SerializationUtils.serialize((Serializable) message);
		InetAddress destiny = InetAddress.getByName(routingTable.findExitByName(message.getDestino().getNome()).getIp()); 
		DatagramPacket sendPacket = new DatagramPacket(data, data.length, destiny, 5001);
		UDPsocket.send(sendPacket);
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
	 * @param contato
	 * @return
	 */
	public boolean estaNaListaDeContatos(Contato contato){
		for (Contato item : contatos) {
			if(item.equals(contato))
				return true;
		}
		return false;
	}
	
	/**
	 * @param contactName
	 * @return
	 */
	public Contato findByName(String contactName){
		for (Registro register : routingTable.getRegistros()) {
			if(register.getDestino().getNome().equalsIgnoreCase(contactName))
				return register.getDestino();
		}
		return null;
	}

	/**
	 * @return the meuHost
	 */
	public Contato getMeuHost() {
		return meuHost;
	}
	
	
}
