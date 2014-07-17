package br.ufpi.easii.system;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

import javax.swing.JTextArea;

import org.apache.commons.lang3.SerializationUtils;

import br.ufpi.easii.model.Mensagem;


public class ReceiveMessage implements Runnable{
	private MulticastSocket socket;
	private JTextArea textArea;
	private byte[] buffer;
	private DatagramPacket received;
	private String sentence;
	private Mensagem message;
	
	public ReceiveMessage(MulticastSocket socket, JTextArea textArea) throws IOException{
		this.socket = socket;
		this.textArea = textArea;
		buffer = new byte[2000];
	}
	
	@Override
	public void run() {
		
		while(true){
			received = new DatagramPacket(buffer, buffer.length);
			buffer = new byte[2000];
			try {
				socket.setSoTimeout(120000);
				socket.receive(received);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			sentence = new String(received.getData());
			message = new Mensagem((Mensagem) SerializationUtils.deserialize(received.getData()));
			sentence = message.getDados();
			textArea.setText(textArea.getText()+"\n"+received.getAddress().getHostAddress()+ ": " + sentence.trim());
		}
	}
//	String sentence;
//	BufferedReader inFromServer;
//	JTextArea textArea;
//	
//	public ReceiveMessage(Socket clientSocket, JTextArea textArea) throws IOException{
//		this.textArea = textArea;
//		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//	}
//	
//	@Override
//	public void run() {
//		try {
//			while((sentence = inFromServer.readLine())!=null){
//				textArea.setText(textArea.getText()+"\n"+sentence);
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
