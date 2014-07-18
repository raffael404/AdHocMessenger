package br.ufpi.easii.system;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

import org.apache.commons.lang3.SerializationUtils;

import br.ufpi.easii.model.Mensagem;


public class ReceiveMessage implements Runnable{
	private JTextArea textArea;
	private byte[] buffer;
	private MulticastClient multicastClient;
	
	public ReceiveMessage(MulticastClient multicastClient, JTextArea textArea) throws IOException{
		this.multicastClient = multicastClient;
		this.textArea = textArea;
		buffer = new byte[2000];
	}
	
	@Override
	public void run() {
		
		while(true){
			DatagramPacket received = new DatagramPacket(buffer, buffer.length);
			buffer = new byte[2000];
			try {
				multicastClient.getSocket().receive(received);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Mensagem message = new Mensagem((Mensagem) SerializationUtils.deserialize(received.getData()));
			String sentence = message.getDados();
			
			try {
				if(message.isSyn()){
					if(!multicastClient.estaNaLista(message)){
						multicastClient.enviaMensagem(new Mensagem(multicastClient.getMeuHost(), null, 5, "", true));
						textArea.setText(textArea.getText()+"\n"+message.getRemetente().getNome() + sentence);
						multicastClient.addContato(message.getRemetente());
						multicastClient.addMensagem(message);
					}
					
					if(message.getTtl() > 0){
						message.decrementaTtl();
						multicastClient.enviaMensagem(message);
					}
					
				}else{
					if(message.getDestino().getIp().trim().equalsIgnoreCase(InetAddress.getLocalHost().getHostAddress().trim())){
						if(!multicastClient.estaNaLista(message)){
							textArea.setText(textArea.getText()+"\n"+message.getRemetente().getNome()+ ": " + sentence.trim());
							multicastClient.addMensagem(message);
						}
						
					}else{
						if(message.getTtl() > 0){
							message.decrementaTtl();
							multicastClient.enviaMensagem(message);
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
