package br.ufpi.easii.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import br.ufpi.easii.model.Contato;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TelaLogin {

	private JFrame frame;
	private JTextField textField;
	private TelaPrincipal telaPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin window = new TelaLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 437, 128);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Meus Dados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 401, 63);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("");
		label.setBounds(13, 23, 0, 0);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(37, 20, 51, 23);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textField.getText();
				try {
					String meuIp = InetAddress.getLocalHost().getHostAddress();
					Contato eu = new Contato(nome, meuIp);
					telaPrincipal = new TelaPrincipal(eu);
					telaPrincipal.frmAdHocMessenger.setVisible(true);
					frame.setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnConectar.setBounds(281, 20, 110, 23);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(-10018, -10042, 0, 0);
		
		textField = new JTextField();
		textField.setBounds(91, 20, 180, 23);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n'){
					String nome = textField.getText();
					try {
						String meuIp = InetAddress.getLocalHost().getHostAddress();
						Contato eu = new Contato(nome, meuIp);
						telaPrincipal = new TelaPrincipal(eu);
						telaPrincipal.frmAdHocMessenger.setVisible(true);
						frame.setVisible(false);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			
		});
		
		panel.setLayout(null);
		panel.add(label);
		panel.add(lblNome);
		panel.add(btnConectar);
		panel.add(label_1);
		panel.add(textField);
	}
}
