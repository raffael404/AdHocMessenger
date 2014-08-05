package br.ufpi.easii.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.ufpi.easii.model.Contato;
import br.ufpi.easii.model.TextMessage;
import br.ufpi.easii.model.routingTable.SyncroMessage;
import br.ufpi.easii.system.Client;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TelaPrincipal {

	public JFrame frmAdHocMessenger;
	private JTextField textField;
	private JTextArea textArea;
	DefaultListModel listModel;
	private JList list;
	private Client client;


	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public TelaPrincipal(Contato meuHost) throws UnknownHostException, IOException {
		initialize(meuHost);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	
	private void initialize(final Contato meuHost) throws UnknownHostException, IOException {
		
		frmAdHocMessenger = new JFrame();
		frmAdHocMessenger.setTitle("Ad Hoc Messenger");
		frmAdHocMessenger.setBounds(100, 100, 634, 416);
		frmAdHocMessenger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdHocMessenger.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Conversa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		panel.setBounds(10, 11, 353, 355);
		frmAdHocMessenger.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 25, 304, 270);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		textField = new JTextField();
		textField.setBounds(21, 306, 204, 36);
		panel.add(textField);
		
		textField.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(248, 306, 77, 36);
		panel.add(btnEnviar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Lista de Contatos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(373, 182, 235, 153);
		frmAdHocMessenger.getContentPane().add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, "name_3303133816133");
		
		
		listModel = new DefaultListModel();
		client = new Client(textArea, "", listModel, meuHost);
		
		try {
			client.updateContactList();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		list = new JList(listModel);
		scrollPane_1.setViewportView(list);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SyncroMessage mensagem = new SyncroMessage(client.getMeuHost(), client.getRoutingTable());
				try {
					//listModel.clear();
					client.sendMulticastMessage(mensagem);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAtualizar.setBounds(519, 343, 89, 23);
		frmAdHocMessenger.getContentPane().add(btnAtualizar);
		
		JPanel panelRoteamento = new JPanel();
		panelRoteamento.setBorder(new TitledBorder(null, "Tabela de Roteamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRoteamento.setBounds(373, 11, 235, 160);
		frmAdHocMessenger.getContentPane().add(panelRoteamento);
		panelRoteamento.setLayout(null);
		
		JScrollPane scrollPaneRoteamento = new JScrollPane();
		scrollPaneRoteamento.setBounds(10, 22, 215, 127);
		panelRoteamento.add(scrollPaneRoteamento);
		
		JTextArea textAreaRoteamento = new JTextArea();
		scrollPaneRoteamento.setViewportView(textAreaRoteamento);
		
		
		btnEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = textField.getText();
				textField.setText(null);
				try {
					String nome = (String) list.getSelectedValue();
					Contato contato = client.findByName(nome);
					TextMessage mensagem = new TextMessage(meuHost, contato, texto);
					textArea.setText(textArea.getText()+"\n Para "+mensagem.getDestino().getNome() + ": " + mensagem.getDados().trim());
					client.sendUDPMessage(mensagem);
//					scrollPane.getViewport().setViewPosition(new Point(0, scrollPane.getVerticalScrollBar().getMaximum()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n'){
					String texto = textField.getText();
					textField.setText(null);
					try {
						String nome = (String) list.getSelectedValue();
						Contato contato = client.findByName(nome);
						TextMessage mensagem = new TextMessage(meuHost, contato, texto);
						textArea.setText(textArea.getText()+"\n Para "+mensagem.getDestino().getNome()+ ": " + mensagem.getDados().trim());
						client.sendUDPMessage(mensagem);
//						scrollPane.getViewport().setViewPosition(new Point(0, scrollPane.getVerticalScrollBar().getMaximum()));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			
		});
		
		try {
			client.sendMulticastMessage(new SyncroMessage(meuHost, client.getRoutingTable()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
