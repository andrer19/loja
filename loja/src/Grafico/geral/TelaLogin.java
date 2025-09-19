package Grafico.geral;

import entidades.Cademp;
import entidades.Usuario;
import filter.EntityManagerUtil;

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

import org.apache.log4j.BasicConfigurator;

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

import Grafico.Produto.listadeproduto;
import Grafico.cademp.cadastraempresa;
import beans.AcessoBean;
import beans.CadempBean;
import beans.LocalBean;
import beans.Usuariobean;


public class TelaLogin extends JFrame {
	JButton btnacessar;
	JPanel contentPane;
	public static JPasswordField senha;
	private static JTextField login;
	boolean valida = false;
	private JTextField empresa;
	Usuariobean lo = new Usuariobean();
	Usuario user = new Usuario();
	AcessoBean aces1 = new AcessoBean();
	CadempBean emp = new CadempBean();
	Cademp cademp = new Cademp();
	LocalBean loc = new LocalBean();
	static telainicial tela = new telainicial();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		tela.setVisible(true);
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					new TelaLogin().setVisible(true);
					tela.setVisible(false);
					login.requestFocus();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro login: " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setResizable(false);
		setTitle("                     Logon de Acesso");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(470, 250, 408, 204);
		contentPane = new JPanel();
		EntityManagerUtil.getManager();
		//setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/imagens/logoinicial.png")));
		setIconImage(Toolkit.getDefaultToolkit().getImage("\\\\" + aces1.caminhoireport() + "\\imagens\\logoinicial.png"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);
		setLocationRelativeTo(null);
		cademp = emp.buscaempresa();
		
		JLabel lbllogin = new JLabel("LOGIN");
		lbllogin.setForeground(Color.BLUE);
		lbllogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbllogin.setBounds(168, 44, 55, 19);
		contentPane.add(lbllogin);
		
		login = new JTextField();
		login.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		login.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					senha.requestFocus();
				}
			}
		});
		aces1.liberado(login);
		aces1.semcaracteres(login);
		login.setDocument(new Grafico.geral.Letramaiuscula());
		login.setBounds(221, 44, 153, 20);
		contentPane.add(login);

		JLabel lblsenha = new JLabel("SENHA");
		lblsenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblsenha.setForeground(Color.BLUE);
		lblsenha.setBounds(168, 73, 55, 19);
		contentPane.add(lblsenha);

		senha = new JPasswordField();
		senha.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		senha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					      validalogin();
				}
			}
		});
		aces1.liberado(senha);
		senha.setBounds(221, 72, 153, 20);
		contentPane.add(senha);

		btnacessar = new JButton();
		btnacessar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					entrasistema(cademp);
				}
			}
		});
		btnacessar.setEnabled(false);

		btnacessar.addMouseListener(new MouseAdapter() {

			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (btnacessar.isEnabled()) {
						entrasistema(cademp);
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro11 login mouse: " + e1.getMessage());
					System.out.println("ERRO11: " + e1.getMessage());
				}
			}

		});
		btnacessar.setIcon(new ImageIcon(TelaLogin.class.getResource("/imagens/acessar.png")));
		aces1.butonfundo(btnacessar);
		btnacessar.setBounds(218, 107, 55, 46);
		contentPane.add(btnacessar);

		JButton btnsair = new JButton();
		btnsair.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				System.exit(0);
			}
		});
		btnsair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnsair.setIcon(new ImageIcon(TelaLogin.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnsair);
		btnsair.setBounds(287, 107, 55, 46);
		contentPane.add(btnsair);

		JLabel foto = new JLabel("");
		foto.setHorizontalAlignment(SwingConstants.CENTER);
		//JOptionPane.showMessageDialog(null, aces1.caminhoireport()+"//LOJA//imagens//logoinicial.png");
		foto.setIcon(new ImageIcon("\\\\" + aces1.caminhoireport()+"\\imagens\\logoinicial.png"));
		foto.setBounds(5, -2, 153, 170);
		contentPane.add(foto);

		empresa = new JTextField();
		empresa.setHorizontalAlignment(SwingConstants.CENTER);
		empresa.setText(cademp.getFantasia());
		empresa.setEditable(false);
		empresa.setBackground(new Color(211, 211, 211));
		empresa.setFont(new Font("Arial", Font.PLAIN, 16));
		empresa.setBorder(new LineBorder(Color.BLACK));
		empresa.setForeground(Color.BLUE);
		empresa.setBounds(168, 13, 205, 20);
		contentPane.add(empresa);
		empresa.setColumns(10);
		// contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new
		// Component[]{senha, btnacessar, btnsair}));

		// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(0);
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				dispose();
			}
		});

	}

	public void entrasistema(Cademp cademp) {
		Usuario user1 = new Usuario();
		TelaPrincipal tp = null;
		try {
			
			user1 = lo.procurausuario(login.getText(),senha.getText());
			//JOptionPane.showMessageDialog(null, " entra sistema: " + user1.getId());
			tp = new TelaPrincipal(cademp, user1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro entra sistema: " + e1.getMessage());
			e1.printStackTrace();
		}

		if (user1.getLogin().equals("ADMSUPER")) {
			tp.setVisible(true);
		} else {
			if (loc.validalicenca() == true) {
				tp.setVisible(true);
			} else {
				new Licencaespirada().setVisible(true);
			}
		}
		dispose();
	}
	
	public void validalogin() {
		if (senha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "dados em branco");
			senha.requestFocus();
		} else {
			if (lo.procurasenha(login.getText(),senha.getText()) == false) {
				JOptionPane.showMessageDialog(null, "usuario não encontrado");
				senha.setText("");
				senha.requestFocus();
				btnacessar.setEnabled(false);
			} else {
				btnacessar.setEnabled(true);
				btnacessar.requestFocus();
				senha.setEditable(false);
			}
		}
		
	}
	
	
	
	
}
