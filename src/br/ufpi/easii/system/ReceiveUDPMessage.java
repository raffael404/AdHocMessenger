package br.ufpi.easii.system;

import java.io.IOException;
import java.net.DatagramPacket;

import javax.swing.JTextArea;

import org.apache.commons.lang3.SerializationUtils;

import br.ufpi.easii.model.TextMessage;

/**
 * Esta classe � respons�vel pelo recebimento das mensagens de texto enviadas na aplica��o,
 * exibe as mensagens na tela e encaminha as mensagens caso o n� n�o seja o destino final.
 * @author Ronyerison
 *
 */
public class ReceiveUDPMessage implements Runnable {
	private JTextArea textArea;
	private byte[] buffer;
	private Client client;

	/**
	 * @param client - N� que est� recebendo as mensagens.
	 * @param textArea - Local onde ser�o exibidas as mensagens de texto.
	 * @throws IOException
	 */
	public ReceiveUDPMessage(Client client, JTextArea textArea)
			throws IOException {
		this.client = client;
		this.textArea = textArea;
		buffer = new byte[2000];
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			DatagramPacket received = new DatagramPacket(buffer, buffer.length);
			buffer = new byte[2000];
			try {
				client.getUDPsocket().receive(received);
			} catch (IOException e) {
				e.printStackTrace();
			}

			TextMessage message = (TextMessage)SerializationUtils.deserialize(received.getData());

			try {
				if (message.getDestino().equals(client.getMeuHost())) {
					String sentence = message.getDados();
					textArea.setText(textArea.getText() + "\n"
							+ message.getRemetente().getNome() + ": "
							+ sentence.trim());
				} else {
					textArea.setText(textArea.getText() + "\n Mensagem Recebida de "
							+ message.getRemetente().getNome() + " e encaminhada para " + message.getDestino().getNome()
							+ ": \n\t (" + message.getDados().trim() + ")");
					client.sendUDPMessage(message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
