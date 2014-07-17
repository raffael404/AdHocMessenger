package br.ufpi.easii.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;

import br.ufpi.easii.model.Mensagem;
import br.ufpi.easii.system.MulticastClient;


public class TelaPrincipal {

	private JFrame frmAdHocMessenger;
	private JTextField textField;
	private JTextArea textArea;
	private MulticastClient multicastClient;
	private JTextField textField_IP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmAdHocMessenger.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public TelaPrincipal() throws UnknownHostException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	private void initialize() throws UnknownHostException, IOException {
		
		frmAdHocMessenger = new JFrame();
		frmAdHocMessenger.setTitle("Ad Hoc Messenger");
		frmAdHocMessenger.setBounds(100, 100, 450, 360);
		frmAdHocMessenger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdHocMessenger.getContentPane().setLayout(null);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(352, 274, 72, 36);
		frmAdHocMessenger.getContentPane().add(btnEnviar);
		
		btnEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = textField.getText();
				textField.setText(null);
				try {
					Mensagem mensagem = new Mensagem(InetAddress.getLocalHost().toString(), textField_IP.getText(), 5, texto);
					multicastClient.enviaMensagem(mensagem);
//					scrollPane.getViewport().setViewPosition(new Point(0, scrollPane.getVerticalScrollBar().getMaximum()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n'){
					String texto = textField.getText();
					textField.setText(null);
					try {
						Mensagem mensagem = new Mensagem(InetAddress.getLocalHost().toString(), textField_IP.getText(), 5, texto);
						multicastClient.enviaMensagem(mensagem);
//						scrollPane.getViewport().setViewPosition(new Point(0, scrollPane.getVerticalScrollBar().getMaximum()));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			
		});
		
		textField.setBounds(10, 274, 332, 36);
		frmAdHocMessenger.getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 414, 192);
		frmAdHocMessenger.getContentPane().add(scrollPane);
		
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		textField_IP = new JTextField();
		
		textField_IP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n'){
//					
				}
			}
			
			
		});
		
		textField_IP.setColumns(10);
		textField_IP.setBounds(10, 11, 315, 36);
		frmAdHocMessenger.getContentPane().add(textField_IP);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnConectar.setBounds(335, 11, 89, 36);
		frmAdHocMessenger.getContentPane().add(btnConectar);
		
		
		multicastClient = new MulticastClient(textArea, "");
	}
}
