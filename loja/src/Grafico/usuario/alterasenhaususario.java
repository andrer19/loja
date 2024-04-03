package Grafico.usuario;

import java.awt.BorderLayout;

import java.awt.KeyboardFocusManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JLabel;

import beans.AcessoBean;
import beans.Usuariobean;
import entidades.Usuario;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;

@SuppressWarnings("serial")
public class alterasenhaususario extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JTextField loginuser;
	private JPasswordField senhauserconfirma;
	Usuario cadastro = new Usuario();
	Usuariobean userbean = new Usuariobean();
	JButton btncadastrar;
	public JPasswordField senhausernova;
	boolean valida;
	AcessoBean aces1 = new AcessoBean();
	private JButton btnsair;

	/**
	 * Launch the application.
	 */

	public alterasenhaususario(final Usuario u) {
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(470, 250, 414, 313);
		setTitle("ALTERA SENHA");
		contentPanel = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		aces1.fundotela(contentPanel);
		
		JLabel lbllogin = new JLabel("LOGIN");
		aces1.padraojlabel(lbllogin);
		lbllogin.setBounds(30, 47, 82, 14);
		contentPanel.add(lbllogin);

		loginuser = new JTextField(u.getLogin());
		aces1.bloqueado(loginuser);
		loginuser.setForeground(Color.red);
		loginuser.setBounds(164, 44, 217, 20);
		contentPanel.add(loginuser);

		JLabel lblsenhanova = new JLabel("SENHA NOVA");
		aces1.padraojlabel(lblsenhanova);
		lblsenhanova.setBounds(30, 103, 97, 14);
		contentPanel.add(lblsenhanova);

		senhausernova = new JPasswordField();
		senhausernova.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		senhausernova.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
					if (senhausernova.getText().isEmpty() || senhausernova.getText() == null) {
						JOptionPane.showMessageDialog(null, "senha em branco");
						senhausernova.requestFocus();

					} else {
						if (userbean.procurasenha(u.getLogin() ,senhausernova.getText()) == false) {
							aces1.liberado(senhauserconfirma);
							aces1.bloqueado(senhausernova);
							senhauserconfirma.requestFocus();
						} else {
							JOptionPane.showMessageDialog(null, "SENHA JÁ EXISTE !!!!!!!");
							senhausernova.setText("");
							senhausernova.requestFocus();
						}

					}
				}

			}
		});
		senhausernova.setBounds(164, 100, 217, 20);
		aces1.liberado(senhausernova);
		contentPanel.add(senhausernova);

		JLabel lblconfirmasenha = new JLabel("CONFIRMA SENHA");
		aces1.padraojlabel(lblconfirmasenha);
		lblconfirmasenha.setBounds(30, 160, 125, 14);
		contentPanel.add(lblconfirmasenha);

		senhauserconfirma = new JPasswordField();
		senhauserconfirma.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		senhauserconfirma.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (senhauserconfirma.getText().isEmpty()) {
						senhausernova.setEditable(false);
						btncadastrar.setEnabled(false);
					} else {
						if (senhauserconfirma.getText().equals(senhausernova.getText())) {
							btncadastrar.setEnabled(true);
							aces1.bloqueado(senhauserconfirma);
						} else {
							JOptionPane.showMessageDialog(null, u.getLogin() + " AS SENHAS NÃO SÃO IGUAIS");
							btncadastrar.setEnabled(false);
							senhauserconfirma.setText("");
							senhauserconfirma.requestFocus();

						}
					}
				}
			}
		});

		senhauserconfirma.setBounds(164, 157, 217, 20);
		aces1.bloqueado(senhauserconfirma);
		contentPanel.add(senhauserconfirma);

		btncadastrar = new JButton();
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnsair.requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravausuario(u);
				}
			}
		});
		btncadastrar.setEnabled(false);
		btncadastrar.setBounds(140, 218, 55, 46);
		btncadastrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				gravausuario(u);
			}
		});
		btncadastrar.setIcon(new ImageIcon(alterasenhaususario.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		btncadastrar.setEnabled(false);
		contentPanel.add(btncadastrar);
		// btncadastrar.setActionCommand("OK");
		// getRootPane().setDefaultButton(btncadastrar);

		btnsair = new JButton();
		btnsair.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
				}
			}
		});
		btnsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnsair.setBounds(204, 218, 55, 46);
		btnsair.setIcon(new ImageIcon(alterasenhaususario.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnsair);
		contentPanel.add(btnsair);
		// cancelButton.setActionCommand("Cancel");

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				senhausernova.requestFocus();
				senhausernova.setCaretPosition(0);
			}

			public void windowClosing(WindowEvent e) {
				//userbean.fechaconexao();
				dispose();

			}
		});

		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				//userbean.fechaconexao();
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

	public void gravausuario(Usuario vuser) {

		try {
			cadastro.setLogin(vuser.getLogin());
			cadastro.setNome(vuser.getNome());
			cadastro.setSenha(userbean.convertStringToMd5(senhauserconfirma.getText()));
			cadastro.setId(vuser.getId());
			if (senhausernova.getText().isEmpty() || senhausernova.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, vuser.getLogin() + " senha esta branco");
				senhausernova.requestFocus();
			} else {
				userbean.adiciona(cadastro);
				senhausernova.setText("");
				senhauserconfirma.setText("");
				senhausernova.setFocusable(true);
				//JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");

				dispose();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
		}
	}
}
