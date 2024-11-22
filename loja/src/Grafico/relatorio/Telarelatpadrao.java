package Grafico.relatorio;

import entidades.Cadcli;
import entidades.Cademp;
import entidades.CriaExcel;
import entidades.Entidadegenerica;
import entidades.Usuario;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.awt.Component;

import javax.swing.SwingConstants;

import Grafico.Cadcli.listaprocuracliente;
import Grafico.cademp.cadastraempresa;
import Grafico.geral.Barradeprogresso;
import beans.AcessoBean;
import beans.CadcliBean;
import beans.CadempBean;
import beans.LocalBean;
import beans.Usuariobean;

@SuppressWarnings("serial")
public class Telarelatpadrao{
	JButton btnrelat;
	JPanel contentPane;
	public static JFormattedTextField datainicial, datafinal;
	public JTextField codcli;
	JLabel lbldesccli;
	boolean valida = false, btnini = false, btnfim = false;
	Usuariobean lo = new Usuariobean();
	Usuario user = new Usuario();
	AcessoBean aces1 = new AcessoBean();
	CadempBean emp = new CadempBean();
	Cademp cademp = new Cademp();
	LocalBean loc = new LocalBean();
	Date datai1 = null, dataf1 = null;
	Cadcli cli = new Cadcli();
	Cadcli c = new Cadcli();
	CadcliBean clibean = new CadcliBean();
	String abap = "", titulo = "";
	public JDialog telapadrao = new JDialog();

	public Telarelatpadrao(String clifor, String tabela) throws ParseException {
		telapadrao.setResizable(false);
		telapadrao.setModal(true);
		telapadrao.setTitle("                                                                       TELA RELATÓRIO");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telapadrao.setBounds(470, 250, 561, 232);
		contentPane = new JPanel();
		telapadrao.setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.caminhoireport() + "\\LOJA\\imagens\\logoinicial.png"));
		telapadrao.setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);
		telapadrao.setLocationRelativeTo(null);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);

		JLabel lbldatainicial = new JLabel("EMISS\u00C3O DE");
		lbldatainicial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldatainicial.setForeground(Color.BLUE);
		lbldatainicial.setBounds(26, 20, 100, 19);
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
		datainicial.setBounds(135, 19, 95, 20);
		contentPane.add(datainicial);

		JLabel lbldatafinal = new JLabel("EMISS\u00C3O AT\u00C9");
		lbldatafinal.setForeground(Color.BLUE);
		lbldatafinal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldatafinal.setBounds(26, 51, 110, 19);
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
						aces1.liberado(codcli);
						codcli.requestFocus();
						btnfim = true;
					} catch (ParseException ex) {
						JOptionPane.showMessageDialog(null, "Data Inválida");
						btnfim = false;
					}
				}
			}
		});

		aces1.liberado(datafinal);
		datafinal.setBounds(135, 50, 95, 20);
		contentPane.add(datafinal);

		JLabel lblcliente = new JLabel("CLIENTE");
		lblcliente.setForeground(Color.BLUE);
		lblcliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcliente.setBounds(26, 82, 110, 19);
		if (clifor.trim().equals("CLIENTE")) {
		contentPane.add(lblcliente);
		}

		codcli = new JTextField();
		codcli.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		codcli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

					try {
						if (codcli.getText() == null || codcli.getText().trim().isEmpty()) {
							new listaprocuracliente(c).setVisible(true);
						} else {
							validacliente();
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO AO VALIDAR O CLIENTE RELATORIO " + e.getMessage());
						aces1.demologger.error("ERRO AO VALIDAR O CLIENTE RELATORIO " + e.getMessage());
					}
					if (c.getIdcadcli() != 0) {
						cli = clibean.procura(c.getIdcadcli());
						codcli.setText(cli.getCODCLI());
						lbldesccli.setText(cli.getDESCCLI().trim());
					} else {
						lbldesccli.setText("");
						codcli.requestFocus();
					}
				}

				if (evt.getKeyCode() == KeyEvent.VK_TAB) {
					if (codcli.getText() == null || codcli.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite um valor!!!");
						lbldesccli.setText("");
						codcli.requestFocus();
					} else {
						validacliente();
					}
				}
			}
		});
		aces1.bloqueado(codcli);
		codcli.setBounds(135, 81, 95, 20);
		if (clifor.trim().equals("CLIENTE")) {
			contentPane.add(codcli);
		}

		lbldesccli = new JLabel("");
		aces1.padraojlabel(lbldesccli);
		lbldesccli.setBounds(240, 84, 295, 14);
		if (clifor.trim().equals("CLIENTE")) {
			contentPane.add(lbldesccli);
		}

		btnrelat = new JButton();
		btnrelat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						geraexcel(tabela);
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
						geraexcel(tabela);
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "ERRO11: " + e1.getMessage());
				}
			}

		});
		btnrelat.setIcon(new ImageIcon(Telarelatpadrao.class.getResource("/imagens/excel.png")));
		btnrelat.setToolTipText("GERAR RELATÓRIO");
		aces1.butonfundo(btnrelat);
		btnrelat.setBounds(220, 125, 55, 46);
		btnrelat.setEnabled(false);
		contentPane.add(btnrelat);

		JButton btnsair = new JButton();
		btnsair.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				telapadrao.dispose();
			}
		});
		btnsair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				telapadrao.dispose();
			}
		});
		btnsair.setIcon(new ImageIcon(Telarelatpadrao.class.getResource("/imagens/fechar.png")));
		btnsair.setToolTipText("SAIR");
		aces1.butonfundo(btnsair);
		btnsair.setBounds(300, 125, 55, 46);
		contentPane.add(btnsair);

		// fechar janela com esc
		JRootPane rootPane = this.telapadrao.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				telapadrao.dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
		telapadrao.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				telapadrao.dispose();
			}
		});

	}

	public void geraexcel(String tabelap) throws InterruptedException, IOException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		abap = "";
		titulo = "";
		Entidadegenerica genp = new Entidadegenerica();

		if (tabelap.trim().equals("relatclientetotal")) {
			abap = "CLIENTE TOTAL";
			titulo = "total venda por cliente";
			genp.setSql_rowid(cli.getIdcadcli());
		}

		try {
			datai1 = formato.parse(datainicial.getText());
			dataf1 = formato.parse(datafinal.getText());

			telapadrao.dispose();

			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					Barradeprogresso bar = new Barradeprogresso();
					bar.iniciaBar();
					try {
						bar.relatpadrao(tabelap, abap, datai1, dataf1, genp, titulo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					bar.paraBar();
				}
			});
			t.start();

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void validacliente() {

		Cadcli c2 = new Cadcli();

		c2 = clibean.Validacao(codcli.getText());

		if (c2.getIdcadcli() != null) {
			cli = c2;
			if (btnfim == true && btnini == true) {
				btnrelat.setEnabled(true);
				btnrelat.requestFocus();
			}
			lbldesccli.setText(c2.getDESCCLI().trim());
		} else {
			JOptionPane.showMessageDialog(null, "Cliente não encontrado!!!");
			codcli.setText("");
			lbldesccli.setText("");
			codcli.requestFocus();
		}

	}
	
	private void titleAlign(JDialog frame) {

        Font font = frame.getFont();

        String currentTitle = frame.getTitle().trim();
        FontMetrics fm = frame.getFontMetrics(font);
        int frameWidth = frame.getWidth();
        int titleWidth = fm.stringWidth(currentTitle);
        int spaceWidth = fm.stringWidth(" ");
        int centerPos = (frameWidth / 2) - (titleWidth / 2);
        int spaceCount = centerPos / spaceWidth;
        String pad = "";
        pad = String.format("%" + (spaceCount - 14) + "s", pad);
        frame.setTitle(pad + currentTitle);

    }
}
