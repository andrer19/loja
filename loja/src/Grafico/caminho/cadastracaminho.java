package Grafico.caminho;

import entidades.Cadcamin;
import entidades.Rotinas;
import entidades.Usuario;
import Grafico.geral.Letramaiuscula;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
import beans.CadcaminBean;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class cadastracaminho extends JDialog {
	private JPanel contentPane;
	private JTextField caminho;
	Font font = new Font("arial", 0, 14);
	JButton btncadastrar;
	private Long id;
	Grafico.geral.Letramaiuscula bloqueios = new Grafico.geral.Letramaiuscula();
	AcessoBean aces1 = new AcessoBean();
	private JButton btnsair;
	private JTextField IP;
	private JTextField porta;
	private JTextField banco;
	private JTextField usuario;
	private JTextField senha;

	
	public cadastracaminho(final Cadcamin camin, Usuario u) throws Exception {

		setTitle("CAMINHO");
		//setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.imagemicone()));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(10, 5, 390, 318);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);

		JLabel lblcaminho = new JLabel("CAMINHO:");
		lblcaminho.setForeground(Color.BLACK);
		lblcaminho.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblcaminho.setBounds(10, 8, 65, 18);
		contentPane.add(lblcaminho);

		caminho = new JTextField();
		caminho.setBounds(100, 8, 241, 23);
		aces1.liberado(caminho);
		contentPane.add(caminho);

		JLabel lblip = new JLabel("IP CONEX\u00C3O:");
		lblip.setForeground(Color.BLACK);
		lblip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblip.setBounds(10, 42, 85, 18);
		contentPane.add(lblip);

		IP = new JTextField();
		IP.setBounds(100, 40, 241, 23);
		aces1.liberado(IP);
		contentPane.add(IP);

		JLabel lblporta = new JLabel("PORTA");
		lblporta.setForeground(Color.BLACK);
		lblporta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblporta.setBounds(10, 77, 85, 18);
		contentPane.add(lblporta);

		porta = new JTextField();
		porta.setBounds(100, 74, 241, 23);
		aces1.liberado(porta);
		contentPane.add(porta);

		JLabel lblbanco = new JLabel("BANCO");
		lblbanco.setForeground(Color.BLACK);
		lblbanco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblbanco.setBounds(10, 110, 85, 18);
		contentPane.add(lblbanco);

		banco = new JTextField();
		banco.setBounds(100, 108, 241, 23);
		aces1.liberado(banco);
		contentPane.add(banco);

		JLabel lblusuario = new JLabel("USUARIO");
		lblusuario.setForeground(Color.BLACK);
		lblusuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblusuario.setBounds(10, 144, 85, 18);
		contentPane.add(lblusuario);

		usuario = new JTextField();
		usuario.setBounds(100, 142, 241, 23);
		aces1.liberado(usuario);
		contentPane.add(usuario);

		JLabel lblsenha = new JLabel("SENHA");
		lblsenha.setForeground(Color.BLACK);
		lblsenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblsenha.setBounds(10, 178, 85, 18);
		contentPane.add(lblsenha);

		senha = new JPasswordField();
		senha.setBounds(100, 176, 241, 23);
		aces1.liberado(senha);
		contentPane.add(senha);

		btncadastrar = new JButton();
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnsair.requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravacaminho(camin);
				}
			}
		});
		btncadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravacaminho(camin);

			}
		});
		btncadastrar.setIcon(new ImageIcon(cadastracaminho.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		btncadastrar.setBounds(125, 225, 55, 46);
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
		btnsair.setBounds(203, 225, 55, 46);
		btnsair.setIcon(new ImageIcon(cadastracaminho.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnsair);
		contentPane.add(btnsair);

		if (camin.getId() != null) {
			
			caminho.setText(camin.getCaminho().trim());
			IP.setText(camin.getIpconexao().trim());
			banco.setText(camin.getBanco().trim());
			porta.setText(camin.getPorta().trim());
			usuario.setText(camin.getUser().trim());
			senha.setText(camin.getSenha().trim());

		}

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

	public void gravacaminho(Cadcamin e2) {

		Cadcamin e = new Cadcamin();
		CadcaminBean u = new CadcaminBean();

		e.setCaminho(caminho.getText().trim());
		e.setIpconexao(IP.getText().trim());
		e.setBanco(banco.getText().trim());
		e.setPorta(porta.getText().trim());
		e.setUser(usuario.getText().trim());
		e.setSenha(senha.getText().trim());

		if (e.getCaminho().isEmpty() || e.getIpconexao().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Dados em branco");
			btncadastrar.setEnabled(false);
		} else {

			try {
				e.setId(e2.getId());
				if (e.getId() == null) {
					u.adiciona(e);
				} else {
					u.atualiza(e);
				}
				dispose();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Erro Caminho: " + e1.getMessage());
		}
	}
}
