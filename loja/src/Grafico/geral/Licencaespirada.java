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
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Licencaespirada extends JFrame {
	JButton btnacessar;
	JPanel contentPane;
	boolean valida = false;
	public JTextField codigo, codigolibera;
	AcessoBean aces1 = new AcessoBean();
	LocalBean local = new LocalBean();

	/**
	 * Create the frame.
	 */
	public Licencaespirada() {
		setResizable(false);
		setTitle("LICENÇA EXPIRADA");
		// setModal(true);
		setBounds(470, 250, 557, 228);
		contentPane = new JPanel();
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Licencaespirada.class.getResource("/imagens/gemaap.png")));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);
		setLocationRelativeTo(null);
		local.numerorandom();
		// local.verificacodigo();
		local.numerocliente();

		JLabel lblcodigolibera = new JLabel("LICEN\u00C7A");
		lblcodigolibera.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcodigolibera.setForeground(Color.BLUE);
		lblcodigolibera.setBounds(160, 96, 67, 19);
		contentPane.add(lblcodigolibera);

		codigolibera = new JTextField();
		codigolibera.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		codigolibera.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
					if (codigolibera.getText() != null && !codigolibera.getText().isEmpty()) {
						if (local.liberasenha(codigo.getText(), codigolibera.getText()) == true) {
							btnacessar.setEnabled(true);
							aces1.bloqueado(codigolibera);
						} else {
							JOptionPane.showMessageDialog(null, "Licença Inválida!!!!!");
						}
					} else {
						codigolibera.requestFocus();
						JOptionPane.showMessageDialog(null, "Licença em branco");
					}
				}
			}
		});
		codigolibera.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		aces1.liberado(codigolibera);
		codigolibera.setBounds(237, 95, 153, 20);
		contentPane.add(codigolibera);

		btnacessar = new JButton();
		btnacessar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				gravalicencatela();
			}
		});
		btnacessar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					gravalicencatela();
				}

			}
		});
		btnacessar.setEnabled(false);
		btnacessar.setIcon(new ImageIcon(Licencaespirada.class.getResource("/imagens/acessar.png")));
		aces1.butonfundo(btnacessar);
		btnacessar.setBounds(195, 133, 55, 46);
		contentPane.add(btnacessar);

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
		btnsair.setIcon(new ImageIcon(Licencaespirada.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnsair);
		btnsair.setBounds(272, 133, 55, 46);
		contentPane.add(btnsair);

		codigo = new JTextField(local.numerocliente());
		codigo.setBounds(237, 65, 152, 20);
		aces1.bloqueado(codigo);
		contentPane.add(codigo);
		codigo.setColumns(10);

		JLabel lblcodigo = new JLabel("C\u00D3DIGO");
		lblcodigo.setForeground(Color.BLUE);
		lblcodigo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcodigo.setBounds(160, 66, 67, 19);
		contentPane.add(lblcodigo);

		JLabel lbltextolicenca = new JLabel("   SEU TEMPO DE LICENÇA EXPIROU, ENTRAR EM CONTATO COM");
		lbltextolicenca.setForeground(Color.BLUE);
		lbltextolicenca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbltextolicenca.setBounds(10, 11, 519, 19);
		contentPane.add(lbltextolicenca);

		JLabel lblNoTelefone = new JLabel("            GEMAAP TECNOLOGIA NO TELEFONE (11)96662-1415 ");
		lblNoTelefone.setForeground(Color.BLUE);
		lblNoTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNoTelefone.setBounds(10, 41, 519, 19);
		contentPane.add(lblNoTelefone);
		
		JLabel lbllogo = new JLabel();
		lbllogo.setIcon(new ImageIcon(Licencaespirada.class.getResource("/imagens/gemaap.png")));
		lbllogo.setBounds(10, 68, 145, 111);
		contentPane.add(lbllogo);
		// contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new
		// Component[]{senha, btnacessar, btnsair}));

		// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// System.exit(0);
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
				dispose();
			}
		});

	}
	
	public void gravalicencatela() {
		
		if (local.gravalicenca() == true) {
			JOptionPane.showMessageDialog(null, "Licença renovada, uso Liberado");
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Licença não renovada, uso Bloqueado");	
		}
		
	}
}
