package br.ufpi.easii.system;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import org.apache.commons.lang3.SerializationUtils;

import br.ufpi.easii.model.Mensagem;

public class MulticastClient {
	private InetAddress groupIP;
	private MulticastSocket socket;
	private List<Mensagem> mensagensRecebidas;
	
	public MulticastClient(JTextArea textArea, String IP) throws UnknownHostException, IOException {
		groupIP = InetAddress.getByName("224.225.226.227");
		socket = new MulticastSocket(5000);
		socket.joinGroup(groupIP);
		mensagensRecebidas = new ArrayList<Mensagem>();
		new Thread(new ReceiveMessage(this, textArea)).start();
	}
	
	public void enviaMensagem(Mensagem mensagem) throws Exception{
		byte[] dados = SerializationUtils.serialize((Serializable) mensagem);
		DatagramPacket datagram = new DatagramPacket(dados, dados.length, groupIP, 5000);
		socket.send(datagram);
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
	public MulticastSocket getSocket() {
		return socket;
	}

	/**
	 * @return the mensagensRecebidas
	 */
	public List<Mensagem> getMensagensRecebidas() {
		return mensagensRecebidas;
	}
	
	public void addMensagem(Mensagem mensagem){
		if(mensagensRecebidas.size() >= 10){
			mensagensRecebidas.remove(0);
		}
		mensagensRecebidas.add(mensagem);
	}
	
	public boolean estaNaLista(Mensagem mensagem){
		for (Mensagem item : mensagensRecebidas) {
			if(item.equals(mensagem)){
				return true;
			}
		}
		return false;
	}
	
}
