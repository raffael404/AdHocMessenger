package br.ufpi.easii.system;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

import org.apache.commons.lang3.SerializationUtils;

import br.ufpi.easii.model.Contato;
import br.ufpi.easii.model.Mensagem;
public class MulticastClient {
	private InetAddress groupIP;
	private MulticastSocket socket;
	private List<Mensagem> mensagensRecebidas;
	private List<Contato> contatos;
	
	private Contato meuHost;
	private DefaultListModel listModel;
	
	public MulticastClient(JTextArea textArea, String IP, DefaultListModel listModel, Contato meuHost) throws UnknownHostException, IOException {
		groupIP = InetAddress.getByName("224.225.226.227");
		socket = new MulticastSocket(5000);
		socket.joinGroup(groupIP);
		mensagensRecebidas = new ArrayList<Mensagem>();
		contatos = new ArrayList<Contato>();
		this.meuHost = meuHost;
		this.listModel = listModel;
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
	
	/**
	 * @return the contatos
	 */
	public List<Contato> getContatos() {
		return contatos;
	}

	/**
	 * @param contatos the contatos to set
	 */
	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
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
	
	@SuppressWarnings("unchecked")
	public void addContato(Contato contato) throws Exception{
		if(!contatos.contains(contato)){
			contatos.add(contato);
			listModel.addElement(contato.getNome());
			
			System.out.println(contato.toString());
		}
	}
	
	public Contato findByName(String contactName){
		for (Contato contato : contatos) {
			if (contato.getNome().trim().equalsIgnoreCase(contactName.trim())) {
				return contato;
			}
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
