package br.ufpi.easii.system;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

import javax.swing.JTextArea;


public class ReceiveMessage implements Runnable{
	MulticastSocket socket;
	JTextArea textArea;
	byte[] buffer;
	DatagramPacket received;
	String sentence;
	
	public ReceiveMessage(MulticastSocket socket, JTextArea textArea) throws IOException{
		this.socket = socket;
		this.textArea = textArea;
		buffer = new byte[3000];
	}
	
	@Override
	public void run() {
		
		while(true){
			received = new DatagramPacket(buffer, buffer.length);
			try {
				socket.setSoTimeout(120000);
				socket.receive(received);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sentence = new String(received.getData());
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
