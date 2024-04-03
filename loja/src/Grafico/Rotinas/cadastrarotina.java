package Grafico.Rotinas;

import entidades.Modulos;
import entidades.Rotinas;
import entidades.Usuario;
import Configuracao.Letramaiuscula;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFormattedTextField;

import beans.AcessoBean;
import beans.ModulosBean;
import beans.RotinasBean;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class cadastrarotina extends JDialog {
	private JPanel contentPane;
	private JTextField rotina;
	Font font = new Font("arial", 0, 14);
	JButton btncadastrar;
	private Long id;
	Configuracao.Letramaiuscula bloqueios = new Configuracao.Letramaiuscula();
	AcessoBean aces1 = new AcessoBean();
	RotinasBean rot = new RotinasBean();
	private JButton btnsair;
	private JTextField pagina;
	Boolean gravamodulo = false;
	Boolean gravapagina = false;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @param u
	 * @param emp
	 * 
	 * @param id2
	 * @param id
	 * @param usuario
	 * @param emp
	 * @param cao
	 * @param cao
	 * @throws Exception
	 */
	public cadastrarotina(final Rotinas mod1, Usuario u) throws Exception {

		setTitle("CADASTRA ROTINA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.imagemicone()));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(10, 5, 390, 218);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);
		
		
		JLabel lblrotina = new JLabel("ROTINA");
		aces1.padraojlabel(lblrotina);
		lblrotina.setBounds(46, 30, 70, 14);
		contentPane.add(lblrotina);

		rotina = new JTextField();
		rotina.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		rotina.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!rotina.getText().isEmpty() && rotina.getText() != null) {
						if (mod1.getId() == null) {
							if (rot.verificaexistemodulo(rotina.getText()) == false) {
								aces1.liberado(pagina);
								aces1.bloqueado(rotina);
								pagina.requestFocus();
							} else {
								rotina.setText("");
								rotina.requestFocus();
								JOptionPane.showMessageDialog(null, "ROTINA JÁ EXISTE !!!!!");
							}
						}
					} else {
						rotina.requestFocus();
						JOptionPane.showMessageDialog(null, "campo em branco!!!!!");
					}
				}
			}
		});
		rotina.setBounds(125, 27, 241, 20);
		rotina.setDocument(new Configuracao.Letramaiuscula());
		aces1.bloqueado(rotina);
		contentPane.add(rotina);
		
		JLabel lblpagina = new JLabel("PAGINA");
		aces1.padraojlabel(lblpagina);
		lblpagina.setBounds(46, 64, 65, 14);
		contentPane.add(lblpagina);

		pagina = new JTextField();
		pagina.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		pagina.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (pagina.getText() != null && !pagina.getText().isEmpty()) {
						if (mod1.getId() == null) {
							if (rot.verificaexistepagina(pagina.getText()) == false) {
								aces1.bloqueado(pagina);
								btncadastrar.setEnabled(true);
								btncadastrar.requestFocus();
							} else {
								pagina.setText("");
								pagina.requestFocus();
								JOptionPane.showMessageDialog(null, "PÁGINA JÁ EXISTE!!!!!");
							}
						}
					}else {
						pagina.requestFocus();
						JOptionPane.showMessageDialog(null, "DADOS EM BRANCO !!!!!");
					}
				}
			}
		});
		pagina.setBounds(125, 61, 241, 20);
		aces1.bloqueado(pagina);
		pagina.setDocument(new Configuracao.Letramaiuscula());
		contentPane.add(pagina);

		btncadastrar = new JButton();
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnsair.requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravarotina(mod1);
				}
			}
		});
		btncadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravarotina(mod1);

			}
		});
		btncadastrar.setIcon(new ImageIcon(cadastrarotina.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		btncadastrar.setEnabled(false);
		btncadastrar.setBounds(125, 119, 55, 46);
		contentPane.add(btncadastrar);

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
		btnsair.setBounds(203, 119, 55, 46);
		btnsair.setIcon(new ImageIcon(cadastrarotina.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnsair);
		contentPane.add(btnsair);

		if (mod1.getId() != null) {
			rotina.setText(mod1.getModulo());
			pagina.setText(mod1.getPagina());
			btncadastrar.setEnabled(true);
			aces1.liberado(pagina);
		}else {
			aces1.liberado(rotina);
		}

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				if (mod1.getId() != null) {
					pagina.requestFocus();
					pagina.setCaretPosition(0);
				} else {
					rotina.requestFocus();
					rotina.setCaretPosition(0);
				}
			}

			public void windowClosing(WindowEvent e) {
				dispose();

			}
		});


		// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

	public void gravarotina(Rotinas mod1) {

		Rotinas e = new Rotinas();
		e.setModulo(rotina.getText());
		e.setPagina(pagina.getText());

		if (e.getModulo().equals(null) || e.getModulo().isEmpty() || e.getPagina().equals(null) || e.getPagina().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Dados em branco");
			btncadastrar.setEnabled(false);
		} else {

			try {
				
				
				e.setModulo(rotina.getText());
				e.setPagina(pagina.getText());
				e.setSql_deleted("F");
				e.setId(mod1.getId());
				rot.adiciona(e);
				dispose();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "ERRO: " + e1.getStackTrace());
			}
		}

		try {

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
		}
	}
}
