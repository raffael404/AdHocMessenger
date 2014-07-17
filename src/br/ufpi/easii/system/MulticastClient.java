package br.ufpi.easii.system;

import java.io.*;
import java.net.*;

import javax.swing.JTextArea;

public class MulticastClient {
	private InetAddress groupIP;
	private MulticastSocket socket;
	
	public MulticastClient(JTextArea textArea, String IP) throws UnknownHostException, IOException {
		groupIP = InetAddress.getByName("224.225.226.227");
		socket = new MulticastSocket(5000);
		socket.joinGroup(groupIP);
		new Thread(new ReceiveMessage(socket, textArea)).start();		
	}
	
	public void enviaMensagem(String mensagem) throws Exception{
		DatagramPacket datagram = new DatagramPacket(mensagem.getBytes(), mensagem.length(), groupIP, 5000);
		socket.send(datagram);
	}
	
//	Socket clientSocket;
//	DataOutputStream outToServer;
//	
//	public MulticastClient(JTextArea textArea, String IP) throws UnknownHostException, IOException {
//		clientSocket = new Socket(IP, 5000);
//		new Thread(new ReceiveMessage(clientSocket, textArea)).start();
//		outToServer = new DataOutputStream(clientSocket.getOutputStream());
//	}
//	
//	public void enviaMensagem(String mensagem) throws Exception{
//		outToServer.writeBytes(mensagem + "\n");
//	}
//	
//	public DataOutputStream getOutToServer() {
//		return outToServer;
//	}
}
