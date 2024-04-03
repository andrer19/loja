package Grafico.Cadcli;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Collections;

import javax.swing.JTextArea;
import javax.swing.JComboBox;

import entidades.Acesso;
import entidades.Cadcli;
import entidades.Usuario;
import filter.EntityManagerUtil;
import repositorios.LogusuRepository;
import Grafico.geral.Letramaiuscula;
import Jm.JMascara;
import beans.AcessoBean;
import beans.CadcliBean;

import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class cadastrocliente extends JDialog {
	private JPanel contentPane;
	
	private JLabel lblcidade,lblcep,lblestado,lblcontato,lblcnpj,lblemail,lblinscricaoestadual;
	
	private JTextField desccli,telefone,endereco,bairro,razaosocial,site,cidade,cep,contato,cnpj,inscestadual,email,codcli,numero;
	
	private JComboBox estado;
	
	private JTextArea obs;
	
	JRadioButton radiojuridica, radiofisica, radioativo, radioinativo;
	
	JButton btncadastrar,btnsair;
	
	CadcliBean c = new CadcliBean();
	AcessoBean aces1 = new AcessoBean();
		
	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public cadastrocliente(final Cadcli cadcli, Usuario u, Acesso acesso) throws Exception {

		setTitle("CLIENTES");
		setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.imagemicone()));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(10, 5, 690, 450);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);

		JLabel lblcodcli = new JLabel("CODIGO");
		aces1.padraojlabel(lblcodcli);
		lblcodcli.setBounds(10, 15, 63, 14);
		contentPane.add(lblcodcli);

		codcli = new JTextField();
		aces1.bloqueado(codcli);
		codcli.setBounds(116, 12, 74, 20);
		contentPane.add(codcli);

		JLabel lbldesccli = new JLabel("FANTASIA");
		aces1.padraojlabel(lbldesccli);
		lbldesccli.setBounds(192, 15, 63, 14);
		contentPane.add(lbldesccli);

		desccli = new JTextField();
		aces1.liberado(desccli);
		aces1.semcaracteres(desccli);
		desccli.setDocument(new Letramaiuscula());
		desccli.setBounds(254, 12, 410, 20);
		contentPane.add(desccli);

		JLabel lblrazaosocial = new JLabel("RAZÂO SOCIAL");
		aces1.padraojlabel(lblrazaosocial);
		lblrazaosocial.setBounds(10, 40, 96, 14);
		contentPane.add(lblrazaosocial);

		razaosocial = new JTextField();
		razaosocial.setBounds(116, 37, 548, 20);
		aces1.liberado(razaosocial);
		aces1.semcaracteres(razaosocial);
		razaosocial.setDocument(new Letramaiuscula());
		contentPane.add(razaosocial);

		JLabel lblendereco = new JLabel("ENDEREÇO");
		aces1.padraojlabel(lblendereco);
		lblendereco.setBounds(10, 65, 75, 14);
		contentPane.add(lblendereco);

		endereco = new JTextField();
		endereco.setBounds(116, 62, 460, 20);
		contentPane.add(endereco);
		aces1.liberado(endereco);

		JLabel lblnumero = new JLabel("Nº");
		aces1.padraojlabel(lblnumero);
		lblnumero.setBounds(586, 65, 24, 14);
		contentPane.add(lblnumero);

		numero = new JTextField();
		aces1.liberado(numero);
		numero.setBounds(614, 62, 50, 20);
		contentPane.add(numero);

		JLabel lblbairro = new JLabel("BAIRRO");
		aces1.padraojlabel(lblbairro);
		lblbairro.setBounds(10, 90, 55, 14);
		contentPane.add(lblbairro);

		bairro = new JTextField();
		aces1.liberado(bairro);
		bairro.setBounds(116, 87, 220, 20);
		contentPane.add(bairro);

		lblestado = new JLabel("UF");
		aces1.padraojlabel(lblestado);
		lblestado.setBounds(353, 90, 19, 14);
		contentPane.add(lblestado);

		estado = new JComboBox();
		estado.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		estado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					cep.requestFocus();
				}
			}
		});
		estado.setForeground(Color.BLUE);
		estado.setModel(new DefaultComboBoxModel(aces1.listasiglasuf()));
		estado.setBounds(371, 87, 57, 20);
		contentPane.add(estado);

		lblcep = new JLabel("CEP");
		aces1.padraojlabel(lblcep);
		lblcep.setBounds(438, 90, 28, 14);
		contentPane.add(lblcep);

		cep  = new JTextField();
		cep.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		cep.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				cep.setText(JMascara.GetJmascaraCep(cep.getText()));
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					cidade.requestFocus();
				}
			}
		});
		cep.setColumns(10);
		aces1.liberado(cep);
		cep.setBounds(464, 87, 201, 20);
		contentPane.add(cep);

		lblcidade = new JLabel("CIDADE");
		aces1.padraojlabel(lblcidade);
		lblcidade.setBounds(10, 115, 50, 14);
		contentPane.add(lblcidade);

		cidade = new JTextField();
		aces1.liberado(cidade);
		cidade.setBounds(116, 112, 220, 20);
		contentPane.add(cidade);

		lblcnpj = new JLabel("CNPJ");
		aces1.padraojlabel(lblcnpj);
		lblcnpj.setBounds(342, 115, 33, 14);
		contentPane.add(lblcnpj);

		cnpj = new JTextField();
		cnpj.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		cnpj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				cnpj.setText(JMascara.GetJmascaraCpfCnpj(cnpj.getText()));
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					radiojuridica.requestFocus();
				}
			}
		});
		aces1.liberado(cnpj);
		cnpj.setBounds(377, 112, 186, 20);
		contentPane.add(cnpj);

		radiojuridica = new JRadioButton("P. JURIDICA");
		radiojuridica.setBackground(aces1.corpadrao());
		radiojuridica.setForeground(new Color(0, 0, 0));
		radiojuridica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radiojuridica.isSelected()) {
					radiofisica.setSelected(false);

				}
			}
		});
		radiojuridica.setBounds(565, 112, 100, 20);
		if (cadcli.getIdcadcli() == null) {
			radiojuridica.setSelected(true);
		}
		contentPane.add(radiojuridica);

		lblinscricaoestadual = new JLabel("INSC. ESTADUAL");
		aces1.padraojlabel(lblinscricaoestadual);
		lblinscricaoestadual.setBounds(10, 140, 105, 14);
		contentPane.add(lblinscricaoestadual);

		inscestadual = new JTextField();
		aces1.liberado(inscestadual);
		inscestadual.setBounds(116, 137, 173, 20);
		contentPane.add(inscestadual);

		JLabel lblsite = new JLabel("SITE");
		aces1.padraojlabel(lblsite);
		lblsite.setBounds(293, 140, 30, 14);
		contentPane.add(lblsite);

		site = new JTextField();
		aces1.liberado(site);
		site.setBounds(323, 137, 240, 20);
		contentPane.add(site);

		radiofisica = new JRadioButton("P. FISICA");
		radiofisica.setBackground(aces1.corpadrao());
		radiofisica.setForeground(new Color(0, 0, 0));
		radiofisica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radiofisica.isSelected()) {
					radiojuridica.setSelected(false);

				}
			}
		});
		radiofisica.setBounds(565, 137, 100, 20);
		contentPane.add(radiofisica);

		JLabel lbltelefone = new JLabel("TELEFONE");
		aces1.padraojlabel(lbltelefone);
		lbltelefone.setBounds(10, 165, 70, 14);
		contentPane.add(lbltelefone);

		telefone = new JTextField();
		aces1.liberado(telefone);
		telefone.setBounds(116, 162, 548, 20);
		contentPane.add(telefone);

		lblcontato = new JLabel("CONTATO");
		aces1.padraojlabel(lblcontato);
		lblcontato.setBounds(10, 190, 65, 14);
		contentPane.add(lblcontato);

		contato = new JTextField();
		aces1.liberado(contato);
		contato.setBounds(115, 187, 220, 20);
		contentPane.add(contato);

		lblemail = new JLabel("E-MAIL");
		aces1.padraojlabel(lblemail);
		lblemail.setBounds(358, 190, 45, 14);
		contentPane.add(lblemail);

		email = new JTextField();
		aces1.liberado(email);
		email.setBounds(406, 187, 258, 20);
		contentPane.add(email);

		JLabel lblcliente = new JLabel("CLIENTE");
		aces1.padraojlabel(lblcliente);
		lblcliente.setBounds(10, 215, 57, 14);
		contentPane.add(lblcliente);

		radioativo = new JRadioButton("ATIVO");
		radioativo.setBackground(aces1.corpadrao());
		radioativo.setForeground(new Color(0, 0, 0));
		radioativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioativo.isSelected()) {
					radioinativo.setSelected(false);

				}
			}
		});
		if (cadcli.getIdcadcli() == null) {
			radioativo.setSelected(true);
		}
		radioativo.setBounds(115, 212, 74, 20);
		contentPane.add(radioativo);

		radioinativo = new JRadioButton("INATIVO");
		radioinativo.setBackground(aces1.corpadrao());
		radioinativo.setForeground(new Color(0, 0, 0));
		radioinativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioinativo.isSelected()) {
					radioativo.setSelected(false);

				}
			}
		});
		radioinativo.setBounds(191, 212, 74, 20);
		contentPane.add(radioinativo);

		JLabel lblobs = new JLabel("OBSERVAÇÃO");
		aces1.padraojlabel(lblobs);
		lblobs.setBounds(290, 237, 90, 14);
		contentPane.add(lblobs);

		obs = new JTextArea();
		obs.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		obs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					btncadastrar.requestFocus();
				}
			}
		});
		aces1.liberadojTextArea(obs);
		obs.setBounds(10, 257, 652, 80);
		contentPane.add(obs);

		btncadastrar = new JButton();
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnsair.requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravacliente(cadcli, acesso, u);
				}
			}
		});
		btncadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				gravacliente(cadcli, acesso, u);

			}
		});
		btncadastrar.setIcon(new ImageIcon(listadecliente.class.getResource("/imagens/salvar.png")));
		btncadastrar.setBounds(290, 350, 55, 46);
		aces1.butonfundo(btncadastrar);
		contentPane.add(btncadastrar);

		btnsair = new JButton();
		btnsair.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
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
		btnsair.setBounds(358, 350, 55, 46);
		btnsair.setIcon(new ImageIcon(listadecliente.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnsair);
		contentPane.add(btnsair);

		if (cadcli.getIdcadcli() != null) {

			SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");

			if (cadcli.getFISICA() == true) {
				radiofisica.setSelected(true);
			} else {
				radiofisica.setSelected(false);
			}

			if (cadcli.getJURIDICA() == true) {
				radiojuridica.setSelected(true);
			} else {
				radiojuridica.setSelected(false);
			}

			if (cadcli.getATIVO() == true) {
				radioativo.setSelected(true);
			} else {
				radioativo.setSelected(false);
			}

			if (cadcli.getINATIVO() == true) {
				radioinativo.setSelected(true);
			} else {
				radioinativo.setSelected(false);
			}

			codcli.setText(cadcli.getCODCLI().trim());
			desccli.setText(cadcli.getDESCCLI().trim());
			razaosocial.setText(cadcli.getRAZAO().trim());
			telefone.setText(cadcli.getFONE().trim());
			endereco.setText(cadcli.getENDER().trim());
			numero.setText(cadcli.getNUMERO().trim());
			bairro.setText(cadcli.getBAIRRO().trim());
			cidade.setText(cadcli.getCIDADE().trim());
			cep.setText(JMascara.GetJmascaraCep(cadcli.getCEP().trim()));
			estado.setSelectedItem(cadcli.getESTADO().trim());
			contato.setText(cadcli.getCONTATO().trim());
			cnpj.setText(JMascara.GetJmascaraCpfCnpj(cadcli.getCGC().trim()));
			inscestadual.setText(cadcli.getIE().trim());
			email.setText(cadcli.getEMAIL().trim());
			obs.setText(cadcli.getOBS().trim());
			site.setText(cadcli.getSITE().trim());

		}
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				desccli.requestFocus();
				desccli.setCaretPosition(0);

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

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

	}

	private MaskFormatter setNumero(String numero) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(numero);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}

	public void gravacliente(Cadcli c2, Acesso acesso, Usuario uc) {

		Cadcli cadastro = new Cadcli();

		String log = "ATIVADO";
		String tipo = "I";

		if (c2.getIdcadcli() != null) {
			cadastro = c.procura(c2.getIdcadcli());
		}

		try {
			cadastro.setCODCLI(codcli.getText().trim());
			cadastro.setDESCCLI(desccli.getText().trim());
			cadastro.setRAZAO(razaosocial.getText().trim());
			cadastro.setFONE(telefone.getText().trim());
			cadastro.setENDER(endereco.getText().trim());
			cadastro.setNUMERO(numero.getText().trim());
			cadastro.setBAIRRO(bairro.getText().trim());
			cadastro.setCIDADE(cidade.getText().trim());
			cadastro.setCEP(JMascara.GetJmascaraLimpar(cep.getText().trim()));
			cadastro.setESTADO(estado.getSelectedItem().toString().trim());
			cadastro.setCONTATO(contato.getText().trim());
			cadastro.setCGC(JMascara.GetJmascaraLimpar(cnpj.getText().trim()));
			cadastro.setIE(inscestadual.getText().trim());
			cadastro.setEMAIL(email.getText().trim());
			cadastro.setOBS(obs.getText().trim());
			cadastro.setSITE(site.getText().trim());
			if (radiofisica.isSelected()) {
				cadastro.setFISICA(true);
			} else {
				cadastro.setFISICA(false);
			}

			if (radiojuridica.isSelected()) {
				cadastro.setJURIDICA(true);
			} else {
				cadastro.setJURIDICA(false);
			}

			if (radioativo.isSelected()) {
				cadastro.setATIVO(true);
			} else {
				cadastro.setATIVO(false);
			}

			if (radioinativo.isSelected()) {
				cadastro.setINATIVO(true);
			} else {
				cadastro.setINATIVO(false);
			}

			if (!radioinativo.isSelected() && !radioinativo.isSelected()) {
				cadastro.setATIVO(true);
			}

			if (cadastro.getRAZAO().isEmpty() || cadastro.getDESCCLI().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Dados em branco");
			} else {
				Boolean g = false;
				if (cadastro.getIdcadcli() == null) {
					if (acesso.getInclusao() == true) {
						g = true;
					} else {
						JOptionPane.showMessageDialog(null, aces1.incluir);
					}

				} else {
					if (acesso.getAlteracao() == true) {
						g = true;
						tipo = "A";
					} else {
						JOptionPane.showMessageDialog(null, aces1.alterar);
					}

				}

				if (g == true) {
					c.adiciona(cadastro);

					if (tipo.equals("A")) {

						repositorylog.adicionaLog(aces1.logalterar,
								"CLIENTE CODIGO " + cadastro.getCODCLI() + " DESCRICAO " + cadastro.getDESCCLI(),
								cadastro.getIdcadcli(), uc);

						if (cadastro.getINATIVO() != c2.getINATIVO()) {
							if (cadastro.getINATIVO() == true) {
								log = "DESATIVADO";
							}

							EntityManagerUtil.conexao();

							repositorylog.adicionaLog(aces1.logalterar, "CLIENTE " + cadastro.getCODCLI().trim() + " "
									+ cadastro.getDESCCLI().trim() + " FOI " + log, cadastro.getIdcadcli(), uc);
						}
					} else {
						if (cadastro.getINATIVO() == true) {
							log = "DESATIVADO";
						}

						repositorylog.adicionaLog(aces1.logincluir,
								"CLIENTE CODIGO " + cadastro.getCODCLI() + " DESCRICAO " + cadastro.getDESCCLI(),
								cadastro.getIdcadcli(), uc);

						repositorylog.adicionaLog(
								aces1.logincluir, "CLIENTE " + cadastro.getCODCLI().trim() + " "
										+ cadastro.getDESCCLI().trim() + " FOI " + log,
								cadastro.getIdcadcli(), acesso.getUsuario());

					}

					dispose();
				}
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "ERRO AO GRAVAR O CLIENTE " + e1.getMessage());
			aces1.demologger.error("ERRO AO GRAVAR O CLIENTE " + e1.getMessage());
		}
	}
}
