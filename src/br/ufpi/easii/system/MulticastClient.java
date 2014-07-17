package br.ufpi.easii.system;

import java.io.*;
import java.net.*;

import javax.swing.JTextArea;

import org.apache.commons.lang3.SerializationUtils;

import br.ufpi.easii.model.Mensagem;

public class MulticastClient {
	private InetAddress groupIP;
	private MulticastSocket socket;
	
	public MulticastClient(JTextArea textArea, String IP) throws UnknownHostException, IOException {
		groupIP = InetAddress.getByName("224.225.226.227");
		socket = new MulticastSocket(5000);
		socket.joinGroup(groupIP);
		new Thread(new ReceiveMessage(socket, textArea)).start();		
	}
	
	public void enviaMensagem(Mensagem mensagem) throws Exception{
		byte[] dados = SerializationUtils.serialize((Serializable) mensagem);
		DatagramPacket datagram = new DatagramPacket(dados, dados.length, groupIP, 5000);
		socket.send(datagram);
	}
	
}
