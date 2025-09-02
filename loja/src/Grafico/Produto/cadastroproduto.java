package Grafico.Produto;

import Grafico.Cadcli.listadecliente;
import Grafico.geral.Letramaiuscula;
import Grafico.geral.MonetarioDocument;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;

import entidades.Acesso;
import entidades.Cadpro;
import entidades.Usuario;
import filter.EntityManagerUtil;
import repositorios.LogusuRepository;
import beans.AcessoBean;
import beans.CadproBean;
import Grafico.geral.TTextAreaDocument;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class cadastroproduto extends JDialog {
	private JPanel contentPane;
	private JTextField codpro, vrvenda, qtatual, unidade, descpro, vrcusto, qtdecomprada, qtdevendida, lotecon,
			qtdembalagem, pesopeca, pesobruto, troca;

	private JCheckBox chinativo;

	private JTextArea complemento;

	JButton btncadastrar, btnlimpar;

	CadproBean c = new CadproBean();
	AcessoBean aces1 = new AcessoBean();

	String dtult;
	String dtultalt;
	java.sql.Date data3;

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	Locale BRAZIL = new Locale("pt", "BR");
	DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
	DecimalFormat moeda = new DecimalFormat("R$ ###,###,##0.00", REAL);

	public cadastroproduto(final Cadpro pro, final Usuario u, Acesso acesso) throws Exception {

		setTitle("CADASTRO DE PRODUTO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.imagemicone()));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(10, 5, 577, 380);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);

		JLabel lblproduto = new JLabel("PRODUTO");
		aces1.padraojlabel(lblproduto);
		lblproduto.setBounds(13, 14, 71, 14);
		contentPane.add(lblproduto);

		codpro = new JTextField();
		codpro.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		codpro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (codpro.getText().equals("") || codpro.getText().equals(null)) {
						JOptionPane.showMessageDialog(null, "Digite um Valor!!!!");
						codpro.requestFocus();
					} else {

						if (c.Validapro(codpro.getText()) == false) {
							aces1.liberado(descpro);
							aces1.bloqueado(codpro);
							aces1.liberado(unidade);
							if (acesso.getNivel6() == true) {
								aces1.liberado(vrcusto);
							}
							if (acesso.getNivel7() == true) {
								aces1.liberado(vrvenda);
							}
							aces1.liberado(lotecon);
							aces1.liberado(qtdembalagem);
							aces1.liberado(pesopeca);
							aces1.liberado(pesobruto);
							aces1.liberadojTextArea(complemento);
							aces1.liberado(troca);
							btncadastrar.setEnabled(true);
							descpro.requestFocus();
						} else {
							JOptionPane.showMessageDialog(null, "Produto j· existe!!!!!");
							codpro.requestFocus();
						}
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "abcdefghijklmnopqrstuvxzwyABCDEFGHIJKLMNOPQRSTUVXZWY";
				char charTeste = '"';
				String especiais = "¬‘„ı”⁄“ŸÃÕ»…¿¡¥``™∫ß*&®%$#¬ Œ‘€‚ÍÓÙ˚<>:;?/\\|{}[]!«@#$%®&*()_-+=,¥~^`'Á"
						+ charTeste;
				if (caracteres.contains(e.getKeyChar() + "") || especiais.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		aces1.bloqueado(codpro);
		codpro.setBounds(134, 11, 248, 20);
		contentPane.add(codpro);

		JLabel lbldescpro = new JLabel("DESCRI«√O");
		aces1.padraojlabel(lbldescpro);
		lbldescpro.setBounds(13, 39, 80, 14);
		contentPane.add(lbldescpro);

		descpro = new JTextField();
		descpro.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		descpro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (descpro.getText().equals("") || descpro.getText().equals(null)) {
						JOptionPane.showMessageDialog(null, "Digite um Valor!!!!");
						descpro.requestFocus();
					} else {
						unidade.requestFocus();
					}
				}

			}
		});
		descpro.setBounds(134, 36, 413, 20);
		descpro.setDocument(new Grafico.geral.Letramaiuscula());
		aces1.bloqueado(descpro);
		contentPane.add(descpro);

		JLabel lblunidade = new JLabel("UNIDADE");
		aces1.padraojlabel(lblunidade);
		lblunidade.setBounds(13, 64, 105, 14);
		contentPane.add(lblunidade);

		unidade = new JTextField();
		unidade.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		unidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (unidade.getText().equals("") || unidade.getText().equals(null)) {
						JOptionPane.showMessageDialog(null, "Digite um Valor!!!!");
						unidade.requestFocus();
					} else {
						vrcusto.requestFocus();
						vrcusto.selectAll();
					}
				}
			}
		});
		unidade.setDocument(new Letramaiuscula());
		aces1.bloqueado(unidade);
		aces1.letras(unidade);
		unidade.setBounds(134, 61, 43, 20);
		contentPane.add(unidade);

		JLabel lblvrcusto = new JLabel("VR CUSTO");
		aces1.padraojlabel(lblvrcusto);
		lblvrcusto.setBounds(180, 64, 70, 14);
		contentPane.add(lblvrcusto);

		vrcusto = new JTextField();
		vrcusto.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		vrcusto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					validavalorcompra();
					vrvenda.requestFocus();
					vrvenda.selectAll();
				}
			}
		});
		vrcusto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validavalorcompra();
			}

			@Override
			public void focusGained(FocusEvent e) {
				vrcusto.selectAll();
			}
		});
		aces1.bloqueado(vrcusto);
		aces1.numeroscomvirgula(vrcusto);
		vrcusto.setBounds(248, 61, 107, 20);
		contentPane.add(vrcusto);

		JLabel lblvenda = new JLabel("VR VENDA");
		aces1.padraojlabel(lblvenda);
		lblvenda.setBounds(361, 64, 68, 14);
		contentPane.add(lblvenda);

		vrvenda = new JTextField("");
		vrvenda.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		vrvenda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					validavalorvenda();
					lotecon.requestFocus();
					lotecon.selectAll();

				}
			}
		});
		vrvenda.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validavalorvenda();
			}

			@Override
			public void focusGained(FocusEvent e) {
				vrvenda.selectAll();
			}
		});
		aces1.bloqueado(vrvenda);
		aces1.numeroscomvirgula(vrvenda);
		vrvenda.setBounds(429, 61, 118, 20);
		contentPane.add(vrvenda);

		JLabel lblqtatual = new JLabel("QUANT. ATUAL");
		aces1.padraojlabel(lblqtatual);
		lblqtatual.setBounds(13, 89, 105, 14);
		contentPane.add(lblqtatual);

		qtatual = new JTextField();
		qtatual.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		qtatual.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					// qtdecomprada.requestFocus();
					// qtdecomprada.selectAll();
				}
			}
		});
		qtatual.setBounds(134, 86, 62, 20);
		contentPane.add(qtatual);
		aces1.bloqueado(qtatual);

		JLabel lblqtdecomprada = new JLabel("ULTIMA COMPRA");
		aces1.padraojlabel(lblqtdecomprada);
		lblqtdecomprada.setBounds(202, 89, 106, 14);
		contentPane.add(lblqtdecomprada);

		qtdecomprada = new JTextField("");
		qtdecomprada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					// lotecon.requestFocus();
					// lotecon.selectAll();
				}
			}
		});
		aces1.bloqueado(qtdecomprada);
		aces1.numeroscomvirgula(qtdecomprada);
		qtdecomprada.setBounds(310, 86, 62, 20);
		contentPane.add(qtdecomprada);

		JLabel lblqtdevendida = new JLabel("QTDE VENDIDA");
		aces1.padraojlabel(lblqtdevendida);
		lblqtdevendida.setBounds(373, 89, 103, 14);
		contentPane.add(lblqtdevendida);

		qtdevendida = new JTextField();
		qtdevendida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// lotecon.requestFocus();
				// lotecon.selectAll();
			}
		});
		aces1.bloqueado(qtdevendida);
		aces1.numeroscomvirgula(qtdevendida);
		qtdevendida.setBounds(478, 86, 69, 20);
		contentPane.add(qtdevendida);

		JLabel lblotecon = new JLabel("LOTE ECONOMICO");
		aces1.padraojlabel(lblotecon);
		lblotecon.setBounds(13, 114, 120, 14);
		contentPane.add(lblotecon);

		lotecon = new JTextField("");
		lotecon.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		lotecon.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					qtdembalagem.requestFocus();
					qtdembalagem.selectAll();
				}
			}
		});
		aces1.bloqueado(lotecon);
		aces1.numeros(lotecon);
		lotecon.setBounds(134, 111, 62, 20);
		contentPane.add(lotecon);

		JLabel lblqtdembalagem = new JLabel("QTDE EMBALAGEM");
		aces1.padraojlabel(lblqtdembalagem);
		lblqtdembalagem.setBounds(200, 114, 115, 14);
		contentPane.add(lblqtdembalagem);

		qtdembalagem = new JTextField();
		qtdembalagem.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		qtdembalagem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					pesopeca.requestFocus();
					pesopeca.selectAll();
				}
			}
		});
		aces1.bloqueado(qtdembalagem);
		aces1.numeros(qtdembalagem);
		qtdembalagem.setBounds(317, 111, 53, 20);
		contentPane.add(qtdembalagem);

		JLabel lblpesopeca = new JLabel("PESO PE«A");
		aces1.padraojlabel(lblpesopeca);
		lblpesopeca.setBounds(372, 114, 72, 14);
		contentPane.add(lblpesopeca);

		pesopeca = new JTextField();
		pesopeca.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		pesopeca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					validapesoliquido();
					pesobruto.requestFocus();
					pesobruto.selectAll();
				}
			}
		});
		pesopeca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validapesoliquido();
			}

			@Override
			public void focusGained(FocusEvent e) {
				pesopeca.selectAll();
			}
		});
		aces1.bloqueado(pesopeca);
		aces1.numeroscomvirgula(pesopeca);
		pesopeca.setBounds(447, 111, 100, 20);
		contentPane.add(pesopeca);

		JLabel lblpesobruto = new JLabel("PESO BRUTO");
		aces1.padraojlabel(lblpesobruto);
		lblpesobruto.setBounds(13, 139, 84, 14);
		contentPane.add(lblpesobruto);

		pesobruto = new JTextField();
		pesobruto.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		pesobruto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					validapesobruto();
					chinativo.requestFocus();
				}
			}
		});
		pesobruto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validapesobruto();
			}

			@Override
			public void focusGained(FocusEvent e) {
				pesobruto.selectAll();
			}
		});
		aces1.bloqueado(pesobruto);
		aces1.numeroscomvirgula(pesobruto);
		pesobruto.setBounds(134, 136, 71, 20);
		contentPane.add(pesobruto);

		chinativo = new JCheckBox("PRODUTO INATIVO");
		chinativo.setFont(new Font("Tahoma", Font.BOLD, 11));
		chinativo.setForeground(new Color(0, 0, 0));
		chinativo.setBackground(aces1.corpadrao());
		chinativo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					troca.requestFocus();

				}
			}
		});
		chinativo.setBounds(249, 139, 135, 14);
		contentPane.add(chinativo);

		JLabel lbltroca = new JLabel("TROCA");
		aces1.padraojlabel(lbltroca);
		lbltroca.setBounds(420, 139, 53, 14);
		contentPane.add(lbltroca);

		troca = new JTextField();
		troca.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		troca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				complemento.requestFocus();
			}
		});
		aces1.bloqueado(troca);
		troca.setBounds(476, 136, 71, 20);
		contentPane.add(troca);

		JLabel lblcomplemento = new JLabel("OBSERVA«√O");
		aces1.padraojlabel(lblcomplemento);
		lblcomplemento.setBounds(240, 165, 95, 14);
		contentPane.add(lblcomplemento);

		complemento = new JTextArea(new TTextAreaDocument(250));
		complemento.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		complemento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
					btncadastrar.requestFocus();
				}
			}
		});
		aces1.bloqueadojTextArea(complemento);
		complemento.setBounds(13, 187, 534, 73);
		contentPane.add(complemento);

		btncadastrar = new JButton();
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnlimpar.requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravaproduto(pro, acesso, u);
				}
			}
		});
		btncadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				gravaproduto(pro, acesso, u);

			}
		});
		btncadastrar.setBounds(220, 280, 55, 46);
		btncadastrar.setIcon(new ImageIcon(cadastroproduto.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		contentPane.add(btncadastrar);

		btnlimpar = new JButton();
		btnlimpar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				dispose();
			}
		});
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnlimpar.setIcon(new ImageIcon(cadastroproduto.class.getResource("/imagens/fechar.png")));
		btnlimpar.setBounds(303, 280, 55, 46);
		aces1.butonfundo(btnlimpar);
		contentPane.add(btnlimpar);

		if (pro.getIdcadpro() != null) {

			if (pro.getATIVO() == true) {
				chinativo.setSelected(true);
			}

			qtdecomprada.setText(aces1.mascaraquantidadecomvirgula(pro.getQTINICIAL()));

			qtdevendida.setText(
					aces1.mascaraquantidadecomvirgula(aces1.retornadouble(pro.getMATERIAL())));
			codpro.setText(pro.getCODPRO());
			descpro.setText(pro.getDESCPRO());
			unidade.setText(pro.getUN());

			vrcusto.setText(aces1.valordinheiro(pro.getVRCOMPRA()));

			vrvenda.setText(aces1.valordinheiro(pro.getVRVENDA()));

			qtatual.setText(aces1.mascaraquantidadecomvirgula(pro.getQTATUAL()));

			lotecon.setText(aces1.mascaraquantidadecomvirgula(pro.getECONOMICO()));

			qtdembalagem.setText(aces1.mascaraquantidadecomvirgula(
						aces1.retornadouble(aces1.removeponto(pro.getQTCXA().toString()))));
			
			pesopeca.setText(aces1.mascaraquantidadecomvirgula(pro.getPESOLIQ()));

			pesobruto.setText(aces1.mascaraquantidadecomvirgula(pro.getPESOBRUTO()));
			
			complemento.setText(pro.getFOTO());
			troca.setText(Double.toString(pro.getTroca()));

			aces1.liberado(descpro);
			aces1.bloqueado(codpro);
			aces1.liberado(unidade);
			if (acesso.getNivel6() == true) {
				aces1.liberado(vrcusto);
			}
			if (acesso.getNivel7() == true) {
				aces1.liberado(vrvenda);
			}
			aces1.liberado(lotecon);
			aces1.liberado(qtdembalagem);
			aces1.liberado(pesopeca);
			aces1.liberado(pesobruto);
			aces1.liberadojTextArea(complemento);
			aces1.liberado(troca);
			btncadastrar.setEnabled(true);
		} else {
			aces1.liberado(codpro);
			vrcusto.setText(aces1.valordinheiro(0.0));
			vrvenda.setText(aces1.valordinheiro(0.0));
			qtatual.setText(aces1.mascaraquantidadecomvirgula(0.0));
			lotecon.setText(aces1.mascaraquantidadecomvirgula(0.0));
			qtdembalagem.setText(aces1.mascaraquantidadecomvirgula(0.0));
			pesopeca.setText(aces1.mascaraquantidadecomvirgula(0.0));
			pesobruto.setText(aces1.mascaraquantidadecomvirgula(0.0));
			qtdecomprada.setText(aces1.mascaraquantidadecomvirgula(0.0));
			qtdevendida.setText(aces1.mascaraquantidadecomvirgula(0.0));
		}

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				if (pro.getIdcadpro() != null) {
					descpro.requestFocus();
				} else {
					codpro.requestFocus();
					codpro.setCaretPosition(0);
				}
			}
		});

		// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// c.fechaconexao();
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				pro.setCODPRO(null);
				dispose();
			}
		});

	}

	public void gravaproduto(Cadpro p1, Acesso acesso, Usuario up) {

		try {
			Cadpro p = new Cadpro();
			String log = "ATIVADO";

			if (p1.getIdcadpro() != null) {
				p = c.procura(p1.getIdcadpro());
			}

			p.setCODPRO(codpro.getText().trim());
			p.setDESCPRO(descpro.getText().trim());
			p.setUN(unidade.getText().trim());
			p.setVRVENDA(aces1.retornadouble(aces1.removeponto(vrvenda.getText())));
			p.setQTATUAL(Double.parseDouble(aces1.mascaraquantidade(aces1.retornadouble(aces1.removeponto(qtatual.getText())))));
			p.setVRCOMPRA(aces1.retornadouble(aces1.removeponto(vrcusto.getText())));
			p.setECONOMICO(aces1.retornadouble(lotecon.getText()));
			p.setATIVO(aces1.retornaBoolean(chinativo));
			p.setQTINICIAL((Double.parseDouble(aces1.mascaraquantidade(aces1.retornadouble(aces1.removeponto(qtdecomprada.getText()))))));
			p.setMATERIAL(qtdevendida.getText().trim());
			p.setPESOLIQ((Double.parseDouble(aces1.mascaraquantidade(aces1.retornadouble(aces1.removeponto(pesopeca.getText()))))));
			p.setPESOBRUTO((Double.parseDouble(aces1.mascaraquantidade(aces1.retornadouble(aces1.removeponto(pesobruto.getText()))))));
			p.setQTCXA((Double.parseDouble(aces1.mascaraquantidade(aces1.retornadouble(aces1.removeponto(qtdembalagem.getText()))))));
			p.setFOTO(complemento.getText().trim());
			p.setTroca(aces1.retornadouble(troca.getText()));

			if (p.getCODPRO().equals(null) || p.getCODPRO().trim().isEmpty() || p.getDESCPRO().equals(null)
					|| p.getDESCPRO().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Dados em branco");
			} else {

				if (p.getIdcadpro() == null) {
					if (acesso.getInclusao() == true) {
						c.adiciona(p);

						if (p.getATIVO() == true) {
							log = "DESATIVADO";
						}

						EntityManagerUtil.conexao();
						LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
						repositorylog.adicionaLog(aces1.logincluir, "PRODUTO " + p.getCODPRO().trim() + " FOI " + log,
								p.getIdcadpro(), up);

						codpro.setText("");
						descpro.setText("");
						unidade.setText("");
						vrvenda.setText("");
						qtatual.setText("");
						descpro.setText("");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, aces1.incluir);
					}
				} else {
					if (acesso.getAlteracao() == true) {
						c.adiciona(p);
						if (p1.getATIVO() != p.getATIVO()) {
							if (p.getATIVO() == true) {
								log = "DESATIVADO";
							}

							EntityManagerUtil.conexao();
							LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
							repositorylog.adicionaLog(aces1.logalterar,
									"PRODUTO " + p.getCODPRO().trim() + " FOI " + log, p.getIdcadpro(), up);
						}

						if (!p1.getVRVENDA().equals(p.getVRVENDA())) {
							EntityManagerUtil.conexao();
							LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
							repositorylog.adicionaLog(aces1.logalterar,
									"PRODUTO " + p.getCODPRO().trim() + " FOI ALTERADO PRE«O DE VENDA DE "
											+ moeda.format(p1.getVRVENDA()) + " PARA " + moeda.format(p.getVRVENDA()),
									p.getIdcadpro(), up);
						}

						if (!p1.getVRCOMPRA().equals(p.getVRCOMPRA())) {
							EntityManagerUtil.conexao();
							LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
							repositorylog.adicionaLog(aces1.logalterar,
									"PRODUTO " + p.getCODPRO().trim() + " FOI ALTERADO PRE«O DE COMPRA DE "
											+ moeda.format(p1.getVRCOMPRA()) + " PARA " + moeda.format(p.getVRCOMPRA()),
									p.getIdcadpro(), up);
						}

						dispose();
					} else {
						JOptionPane.showMessageDialog(null, aces1.alterar);
					}
				}

			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "ERRO AO GRAVAR O PRODUTO " + e1.getMessage());
			aces1.demologger.error("ERRO AO GRAVAR O PRODUTO " + e1.getMessage());
		}
	}

	public void validavalorcompra() {

		if (vrcusto.getText().equals(null) || vrcusto.getText().trim().isEmpty()) {
			vrcusto.setText(aces1.valordinheiro(0.0));
		} else {

			double vrcompra1 = aces1.retornadouble(aces1.removeponto(vrcusto.getText().trim()));
			vrcusto.setText(aces1.valordinheiro(vrcompra1));

		}

	}

	public void validavalorvenda() {

		if (vrvenda.getText().equals(null) || vrvenda.getText().trim().isEmpty()) {
			vrvenda.setText(aces1.valordinheiro(0.0));
		} else {

			double vrvenda1 = aces1.retornadouble(aces1.removeponto(vrvenda.getText().trim()));
			vrvenda.setText(aces1.valordinheiro(vrvenda1));

		}

	}
	
	public void validapesoliquido() {

		if (pesopeca.getText().equals(null) || pesopeca.getText().trim().isEmpty()) {
			pesopeca.setText(aces1.mascaraquantidadecomvirgula(0.0));
		} else {

			double pesopeca1 = aces1.retornadouble(aces1.removeponto(pesopeca.getText().trim()));
			pesopeca.setText(aces1.mascaraquantidadecomvirgula(pesopeca1));

		}

	}
	
	public void validapesobruto() {

		if (pesobruto.getText().equals(null) || pesobruto.getText().trim().isEmpty()) {
			pesobruto.setText(aces1.mascaraquantidadecomvirgula(0.0));
		} else {

			double pesobruto1 = aces1.retornadouble(aces1.removeponto(pesobruto.getText().trim()));
			pesobruto.setText(aces1.mascaraquantidadecomvirgula(pesobruto1));

		}

	}
}
