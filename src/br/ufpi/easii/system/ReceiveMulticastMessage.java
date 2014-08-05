package br.ufpi.easii.system;

import java.io.IOException;
import java.net.DatagramPacket;

import javax.swing.JTextArea;

import org.apache.commons.lang3.SerializationUtils;

import br.ufpi.easii.model.Message;
import br.ufpi.easii.model.TextMessage;
import br.ufpi.easii.model.routingTable.Registro;
import br.ufpi.easii.model.routingTable.SyncroMessage;


public class ReceiveMulticastMessage implements Runnable{
	private JTextArea textArea;
	private byte[] buffer;
	private Client client;
	
	public ReceiveMulticastMessage(Client multicastClient, JTextArea textArea) throws IOException{
		this.client = multicastClient;
		this.textArea = textArea;
		buffer = new byte[2000];
	}
	
	private boolean updateTable(SyncroMessage message) throws Exception{
		boolean updated = false;
		for (Registro register : message.getRoutingTable().getRegistros()) {
			if(client.getRoutingTable().isOnTable(register.getDestino())){
				Registro temp = client.getRoutingTable().encontrarRegistro(register.getDestino());
				if((register.getSaltos()+1) < temp.getSaltos()){
					client.getRoutingTable().removeRegistro(temp);
					client.getRoutingTable().addRegistro(new Registro(register.getDestino(), message.getRemetente(), register.getSaltos()+1));
					updated = true;
				}
			}else{
				if(!register.getDestino().equals(client.getMeuHost())){
					client.getRoutingTable().addRegistro(new Registro(register.getDestino(), message.getRemetente(), register.getSaltos()+1));
					client.updateContactList();
					updated = true;
				}
			}
		}
		return updated;
	}
	
	@Override
	public void run() {
		while(true){
			boolean tableModified = false;
			DatagramPacket received = new DatagramPacket(buffer, buffer.length);
			buffer = new byte[2000];
			try {
				client.getMulticastSocket().receive(received);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Message message = (Message) SerializationUtils.deserialize(received.getData());
			//Como saber o endereço de todos para o primeiro envio?
			try {
				if(message instanceof SyncroMessage){
					if(!client.getRoutingTable().isOnTable(message.getRemetente()) && !message.getRemetente().equals(client.getMeuHost())){
						client.getRoutingTable().addRegistro(new Registro(message.getRemetente(), message.getRemetente(), 1));
						client.updateContactList();
						tableModified = true;
					}else{
						if(client.getRoutingTable().encontrarRegistro(message.getRemetente()).getSaltos() > 1){
							client.getRoutingTable().removeRegistro(client.getRoutingTable().encontrarRegistro(message.getRemetente()));
							client.getRoutingTable().addRegistro(new Registro(message.getRemetente(), message.getRemetente(), 1));
							tableModified = true;
						}
					}
					if(tableModified)
						updateTable((SyncroMessage)message);
					else
						tableModified = updateTable((SyncroMessage)message);
					if(tableModified){
						client.sendMulticastMessage(new SyncroMessage(client.getMeuHost(), client.getRoutingTable()));
					}
					
					
					textArea.setText(textArea.getText()+"\n" + client.getRoutingTable().toString());
					
					//multicastClient.addContato(message.getRemetente());
				}else{
					if(((TextMessage)message).getDestino().equals(client.getMeuHost())){
						String sentence = ((TextMessage)message).getDados();
						textArea.setText(textArea.getText()+"\n"+message.getRemetente().getNome()+ ": " + sentence.trim());
					}else{
						//Reencaminha a mensagem via UDP socket conforme a tabela
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
