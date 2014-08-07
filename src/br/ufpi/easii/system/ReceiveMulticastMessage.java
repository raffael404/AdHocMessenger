package br.ufpi.easii.system;

import java.io.IOException;
import java.net.DatagramPacket;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang3.SerializationUtils;

import br.ufpi.easii.model.Message;
import br.ufpi.easii.model.SyncroMessage;
import br.ufpi.easii.model.routingTable.Registro;

/**
 * Esta classe � uma Thread que � respons�vel pelo recebimento das mensagens de controle que 
 * cont�m o vetor de distancias dos vizinhos, al�m disso realiza as atualiza��es necess�rias 
 * na Tabela de Roteamento e a envia aos seus vizinhos caso exista alguma atualiza��o.
 * @author Ronyerison
 *
 */
@SuppressWarnings("rawtypes")
public class ReceiveMulticastMessage implements Runnable{
	private DefaultTableModel tableModel;
	private DefaultListModel listModel;
	private byte[] buffer;
	private Client client;
	
	/**
	 * @param multicastClient - n� que est� recebendo as mensagens
	 * @param tableModel - tabela que � exibida na tela
	 * @param listModel - lista de contatos exibida na tela para envio de mensagens
	 * @throws IOException
	 */
	public ReceiveMulticastMessage(Client multicastClient, DefaultTableModel tableModel, DefaultListModel listModel) throws IOException{
		this.client = multicastClient;
		this.tableModel = tableModel;
		this.listModel = listModel;
		buffer = new byte[2000];
	}
	
	/**
	 * M�todo respons�vel pela atualiza��o da Tabela de Roteamento do n�.
	 * @param message - mensagem de sincroniza��o contendo a tabela de roteamento do vizinho.
	 * @return - verdadeiro se a tabela foi atualizada e falso se n�o foi.
	 * @throws Exception
	 */
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
					updateContactList();
					updated = true;
				}
			}
		}
		return updated;
	}
	
	/**
	 * M�todo que atualiza a lista de contatos exibida na tela
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void updateContactList() throws Exception{
		listModel.clear();
		for (Registro register : client.getRoutingTable().getRegistros()) {
			listModel.addElement(register.getDestino().getNome());
		}	
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(true){
			boolean tableModified = false;
			DatagramPacket received = new DatagramPacket(buffer, buffer.length);
			buffer = new byte[2000];
			try {
				client.getMulticastSocket().receive(received);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Message message = (Message) SerializationUtils.deserialize(received.getData());
			System.out.println("Tabela recebida de " + message.getRemetente().getNome() +"\n");
			try {
			
				if(!client.getRoutingTable().isOnTable(message.getRemetente()) ){
					if(!message.getRemetente().equals(client.getMeuHost())){
						client.getRoutingTable().addRegistro(new Registro(message.getRemetente(), message.getRemetente(), 1));
						updateContactList();
						tableModified = true;
					}
				}else{
					if(client.getRoutingTable().encontrarRegistro(message.getRemetente()).getSaltos() > 1){
						client.getRoutingTable().removeRegistro(client.getRoutingTable().encontrarRegistro(message.getRemetente()));
						client.getRoutingTable().addRegistro(new Registro(message.getRemetente(), message.getRemetente(), 1));
						tableModified = true;
					}
				}
				
				if(tableModified){
					updateTable((SyncroMessage)message);
				}else{
					tableModified = updateTable((SyncroMessage)message);
				}
				
				if(tableModified){
					client.sendMulticastMessage(new SyncroMessage(client.getMeuHost(), client.getRoutingTable()));
				}
			
				preencherTabela();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * M�todo que preenche a tabela de roteamento exibida na tela.
	 */
	public void preencherTabela(){
		tableModel.setNumRows(0);
		for (Registro registro : client.getRoutingTable().getRegistros()) {
			tableModel.addRow(new Object[]{ registro.getDestino().getNome(), registro.getSaida().getNome(), registro.getSaltos() });
		}
	}
}
