package Grafico.relatorio;

import entidades.Cademp;
import entidades.CriaExcel;
import entidades.Usuario;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
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
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.awt.Component;

import javax.swing.SwingConstants;

import Grafico.cademp.cadastraempresa;
import Grafico.geral.Barradeprogresso;
import beans.AcessoBean;
import beans.CadempBean;
import beans.LocalBean;
import beans.Usuariobean;

@SuppressWarnings("serial")
public class Telarelatvendas extends JDialog {
	JButton btnrelat;
	JPanel contentPane;
	public static JFormattedTextField datainicial, datafinal;
	boolean valida = false, btnini = false, btnfim = false;
	Usuariobean lo = new Usuariobean();
	Usuario user = new Usuario();
	AcessoBean aces1 = new AcessoBean();
	CadempBean emp = new CadempBean();
	Cademp cademp = new Cademp();
	LocalBean loc = new LocalBean();
	Date datai1 = null, dataf1 = null;
	private JTextField descpro;

	public Telarelatvendas() throws ParseException {
		setResizable(false);
		setModal(true);
		setTitle("      RELAT\u00D3RIO DE VENDAS");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(470, 250, 434, 208);
		contentPane = new JPanel();
		setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.caminhoireport() + "\\LOJA\\imagens\\logoinicial.png"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);
		setLocationRelativeTo(null);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);

		JLabel lbldatainicial = new JLabel("EMISS\u00C3O DE");
		//lbldatainicial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		aces1.padraojlabel(lbldatainicial);
		lbldatainicial.setForeground(Color.BLUE);
		lbldatainicial.setBounds(10, 23, 100, 14);
		contentPane.add(lbldatainicial);

		javax.swing.text.MaskFormatter dataini = new javax.swing.text.MaskFormatter("##/##/####");
		datainicial = new javax.swing.JFormattedTextField(dataini);
		datainicial.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		datainicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						df.parse(datainicial.getText());
						datafinal.requestFocus();
						datafinal.setCaretPosition(0);// posicao zero do cursor
						btnini = true;

						if (btnfim == true && btnini == true) {
							btnrelat.setEnabled(true);
						}

					} catch (ParseException ex) {
						JOptionPane.showMessageDialog(null, "Data Inválida");
						btnini = false;
						btnrelat.setEnabled(false);
						// }
					}
				}
			}
		});
		datainicial.setCaretPosition(0);
		aces1.liberado(datainicial);
		datainicial.setBounds(160, 20, 100, 20);
		contentPane.add(datainicial);

		JLabel lbldatafinal = new JLabel("EMISS\u00C3O AT\u00C9");
		aces1.padraojlabel(lbldatafinal);
		lbldatafinal.setForeground(Color.BLUE);
		lbldatafinal.setBounds(10, 48, 110, 14);
		contentPane.add(lbldatafinal);

		javax.swing.text.MaskFormatter datafim = new javax.swing.text.MaskFormatter("##/##/####");
		datafinal = new javax.swing.JFormattedTextField(datafim);
		datafinal.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		datafinal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						df.parse(datafinal.getText());
						descpro.requestFocus();
						btnfim = true;
						if (btnfim == true && btnini == true) {
							btnrelat.setEnabled(true);
						}

					} catch (ParseException ex) {
						JOptionPane.showMessageDialog(null, "Data Inválida");
						btnfim = false;
						btnrelat.setEnabled(false);
						// }
					}
				}
			}
		});

		aces1.liberado(datafinal);
		datafinal.setBounds(160, 45, 100, 20);
		contentPane.add(datafinal);
		
		JLabel lbldescricao = new JLabel("DESCRI\u00C7\u00C3O PRODUTO");
		aces1.padraojlabel(lbldescricao);
		lbldescricao.setForeground(Color.BLUE);
		lbldescricao.setBounds(10, 73, 146, 14);
		contentPane.add(lbldescricao);
		
		descpro = new JTextField();
		descpro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnrelat.requestFocus();
				}
			}
		});
		aces1.liberado(descpro);
		descpro.setDocument(new Grafico.geral.Letramaiuscula());
		descpro.setBounds(160, 70, 248, 20);
		contentPane.add(descpro);
		
		btnrelat = new JButton();
		btnrelat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						geraexcel();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnrelat.addMouseListener(new MouseAdapter() {

			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (!btnrelat.isEnabled()) {

					} else {
						geraexcel();
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"ERRO11: " + e1.getMessage());
				}
			}

		});
		btnrelat.setIcon(new ImageIcon(Telarelatvendas.class.getResource("/imagens/excel.png")));
		btnrelat.setToolTipText("GERAR RELATÓRIO");
		aces1.butonfundo(btnrelat);
		btnrelat.setBounds(130, 110, 55, 46);
		btnrelat.setEnabled(false);
		contentPane.add(btnrelat);

		JButton btnsair = new JButton();
		btnsair.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				dispose();
			}
		});
		btnsair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnsair.setIcon(new ImageIcon(Telarelatvendas.class.getResource("/imagens/fechar.png")));
		btnsair.setToolTipText("SAIR");
		aces1.butonfundo(btnsair);
		btnsair.setBounds(200, 110, 55, 46);
		contentPane.add(btnsair);

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

	public void geraexcel() throws InterruptedException, IOException {

		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			datai1 = formato.parse(datainicial.getText());
			dataf1 = formato.parse(datafinal.getText());
			dispose();
			
			Thread t = new Thread(new Runnable() {  
			    @Override  
			    public void run() {  
			    	Barradeprogresso bar = new Barradeprogresso();  
			        bar.iniciaBar(); 
			        bar.relavenda(datai1, dataf1,descpro.getText().trim());
			        bar.paraBar();  
			    }  
			});
			t.start();    
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
