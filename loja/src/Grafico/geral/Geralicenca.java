package Grafico.geral;

import entidades.Cademp;
import entidades.Usuario;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.awt.Component;

import javax.swing.SwingConstants;
import beans.AcessoBean;
import beans.LocalBean;

@SuppressWarnings("serial")
public class Geralicenca extends JDialog {
	JPanel contentPane;
	boolean valida = false;
	public JTextField codigocliente, novalicenca;
	AcessoBean aces1 = new AcessoBean();
	LocalBean local = new LocalBean();

	/**
	 * Create the frame.
	 */
	public Geralicenca() throws Exception {
		setResizable(false);
		setTitle("Libera Licen\u00E7a ");
		setBounds(470, 250, 280, 161);
		setModal(true);
		contentPane = new JPanel();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Geralicenca.class.getResource("/imagens/logoinicial.png")));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);
		setLocationRelativeTo(null);

		JLabel lblcodigolibera = new JLabel("Licen\u00E7a:");
		lblcodigolibera.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcodigolibera.setForeground(Color.BLUE);
		lblcodigolibera.setBounds(24, 43, 67, 19);
		contentPane.add(lblcodigolibera);

		novalicenca = new JTextField();
		novalicenca.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		aces1.bloqueado(novalicenca);
		novalicenca.setBounds(101, 42, 153, 20);
		contentPane.add(novalicenca);

		JButton btnsair = new JButton();
		btnsair.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					System.exit(0);
				}
			}
		});
		btnsair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnsair.setIcon(new ImageIcon(Geralicenca.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnsair);
		btnsair.setBounds(90, 73, 55, 46);
		contentPane.add(btnsair);

		codigocliente = new JTextField();
		codigocliente.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		codigocliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB ||
						evt.getKeyCode() == KeyEvent.VK_ENTER) {
					if(codigocliente.getText() != null && !codigocliente.getText().isEmpty()) {
                     novalicenca.setText(local.geralic(codigocliente.getText()));
					}else {
						JOptionPane.showMessageDialog(null, "Valor em branco!!!!!!!");
					}
                     
				}
			}
		});
		codigocliente.setBounds(102, 11, 152, 20);
		aces1.liberado(codigocliente);
		contentPane.add(codigocliente);
		codigocliente.setColumns(10);

		JLabel lblcodigo = new JLabel("C\u00F3digo:");
		lblcodigo.setForeground(Color.BLUE);
		lblcodigo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcodigo.setBounds(25, 12, 67, 19);
		contentPane.add(lblcodigo);

		// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

	}

}
