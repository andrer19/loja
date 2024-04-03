package Grafico.Fornecedor;

import Grafico.Cadcli.listadecliente;
import Grafico.Cadcli.listaprocuracliente;
import Municipio.ListaMunicipio;
import Grafico.geral.Letramaiuscula;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

import entidades.Acesso;
import entidades.Cadcli;
import entidades.Cadfor;
import entidades.Municip;
import entidades.Usuario;
import filter.EntityManagerUtil;
import repositorios.LogusuRepository;
import beans.AcessoBean;
import beans.CadforBean;
import beans.MunicipBean;
import Grafico.geral.TTextAreaDocument;
import Jm.JMascara;

import java.awt.event.KeyAdapter;

@SuppressWarnings("serial")
public class cadastrofornecedor<canilDAO> extends JDialog {
	private JPanel contentPane;
	
	private JLabel lblcidade,lblcep,lblestado,lblcontato,lblcnpj,lblcodigo,lblietransportadora,lblemail,lblpais,lblibge,lblinscricaoestadual,lblsite,
	lbltransportadora,lblcnpjtransportadora,lblenderecotransportadora;
	
	private JTextField descfor,fonetransp,telefone,endereco,bairro,razaosocial,cidade,cep,contato,cnpj,codfor,ietransportadora,email,numero,
	pais,ibge,IE,site,transportadora,cnpjtransportadora,enderecotransportadora;
	
	private JComboBox estado;
	
	private JTextArea obs;
	
	JRadioButton radiodesativado, radiopessoajuridica, radiopessoafisica;
	
	private JButton btncadastrar,btncancelar;

	CadforBean f1 = new CadforBean();
	AcessoBean aces1 = new AcessoBean();
	Letramaiuscula maiuscula = new Letramaiuscula();
	
	String DT1COMPRA1, DTMCOMPRA1;	
	
	Municip m = new Municip();
	MunicipBean mbean = new MunicipBean();

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
	
	public cadastrofornecedor(final Cadfor p, final Usuario u, Acesso acesso) throws Exception {

		setTitle("FORNECEDOR");
		setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.imagemicone()));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(10, 5, 620, 528);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);
		
		lblcodigo = new JLabel("CODIGO");
		aces1.padraojlabel(lblcodigo);
		lblcodigo.setBounds(4, 15, 60, 14);
		contentPane.add(lblcodigo);

		codfor = new JTextField();
		aces1.bloqueado(codfor);
		codfor.setBounds(130, 12, 75, 20);
		contentPane.add(codfor);
		
		JLabel lbldescfor = new JLabel("FANTASIA");
		aces1.padraojlabel(lbldescfor);
		lbldescfor.setBounds(212, 15, 63, 14);
		contentPane.add(lbldescfor);

		descfor = new JTextField();
		aces1.liberado(descfor);
		aces1.semcaracteres(descfor);
		descfor.setDocument(new Letramaiuscula());
		descfor.setBounds(276, 12, 320, 20);
		contentPane.add(descfor);

		JLabel lblrazaosocial = new JLabel("RAZÂO SOCIAL");
		aces1.padraojlabel(lblrazaosocial);
		lblrazaosocial.setBounds(4, 40, 94, 14);
		contentPane.add(lblrazaosocial);
		
		razaosocial = new JTextField();
		razaosocial.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		razaosocial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					endereco.requestFocus();
				}
			}
		});
		razaosocial.setBounds(130, 37, 466, 20);
		aces1.liberado(razaosocial);
		aces1.semcaracteres(razaosocial);
		razaosocial.setDocument(new Letramaiuscula());
		contentPane.add(razaosocial);
		
		JLabel lblendereco = new JLabel("ENDEREÇO");
		aces1.padraojlabel(lblendereco);
		lblendereco.setBounds(4, 65, 73, 14);
		contentPane.add(lblendereco);
		
		endereco = new JTextField();
		aces1.liberado(endereco);
		aces1.semcaracteres(endereco);
		endereco.setBounds(130, 62, 275, 20);
		contentPane.add(endereco);
		
		JLabel lblnumero = new JLabel("Nº");
		aces1.padraojlabel(lblnumero);
		lblnumero.setBounds(410, 65, 20, 14);
		contentPane.add(lblnumero);

		numero = new JTextField();
		numero.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		numero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					ibge.requestFocus();
				}
			}
		});
		aces1.liberado(numero);
		aces1.numeros(numero);
		numero.setBounds(431, 62, 50, 20);
		contentPane.add(numero);
		
		lblibge = new JLabel("IBGE");
		aces1.padraojlabel(lblibge);
		lblibge.setBounds(483, 65, 31, 14);
		contentPane.add(lblibge);

		ibge = new JTextField();
		ibge.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		ibge.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

					try {
						if (ibge.getText() == null || ibge.getText().isEmpty()) {
							Municip m1 = new Municip();
							new ListaMunicipio(m1).setVisible(true);

							if (m1.getId() != null) {
								m = m1;
								ibge.setText(m1.getCod_ibge().trim());
								bairro.requestFocus();
								bairro.setCaretPosition(0);
							} else {
								ibge.setText("");
								ibge.requestFocus();
							}

						} else {
							m = mbean.validamunicipio(ibge.getText().trim());

							if (m.getId() != null) {
								ibge.setText(m.getCod_ibge().trim());
								bairro.requestFocus();
								bairro.setCaretPosition(0);
							} else {
								JOptionPane.showMessageDialog(null, "Municipio não encontrado!!!!! ");
								ibge.setText("");
								ibge.requestFocus();
							}

						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO AO PESQUISAR O IBGE " + e.getMessage());
						aces1.demologger.error("ERRO AO PESQUISAR O IBGE " + e.getMessage());
					}
				}

			}

		});

		ibge.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB) {
					if (ibge.getText() == null || ibge.getText().isEmpty()) {
						bairro.requestFocus();
						bairro.setCaretPosition(0);
					} else {
						m = mbean.validamunicipio(ibge.getText());

						if (m.getId() != null) {
							ibge.setText(m.getCod_ibge().trim());
							bairro.requestFocus();
							bairro.setCaretPosition(0);
						} else {
							JOptionPane.showMessageDialog(null, "Municipio não encontrado!!!!! ");
							ibge.setText("");
							ibge.requestFocus();
						}
					}

				}
			}
		});

		ibge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					if (ibge.getText() != null || !ibge.getText().isEmpty()) {
						m = mbean.validamunicipio(ibge.getText());

						if (m.getId() != null) {
							ibge.setText(m.getCod_ibge().trim());
							bairro.requestFocus();
							bairro.setCaretPosition(0);
						} else {
							ibge.setText("");
						}
					}

				} catch (Exception e1) {
					aces1.demologger.error("ERRO AO PESQUISAR O IBGE " + e1.getMessage());
				}

			}
		});
		ibge.setBounds(514, 62, 82, 20);
		aces1.liberado(ibge);
		contentPane.add(ibge);
		
		JLabel lblbairro = new JLabel("BAIRRO");
		aces1.padraojlabel(lblbairro);
		lblbairro.setBounds(4, 90, 50, 14);
		contentPane.add(lblbairro);

		bairro = new JTextField();
		aces1.liberado(bairro);
		bairro.setBounds(130, 87, 225, 20);
		contentPane.add(bairro);
		
		lblcep = new JLabel("CEP");
		aces1.padraojlabel(lblcep);
		lblcep.setBounds(360, 90, 30, 14);
		contentPane.add(lblcep);
		
		cep = new JTextField();
		cep.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		cep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				cep.setText(JMascara.GetJmascaraCep(cep.getText()));
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					estado.requestFocus();
				}
			}
		});
		aces1.liberado(cep);
		cep.setBounds(390, 87, 100, 20);
		contentPane.add(cep);
		
		lblestado = new JLabel("ESTADO");
		aces1.padraojlabel(lblestado);
		lblestado.setBounds(495, 90, 57, 14);
		contentPane.add(lblestado);
		
		estado = new JComboBox();
		estado.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		estado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					cidade.requestFocus();
				}
			}
		});
		estado.setForeground(Color.BLUE);
		estado.setBounds(546, 87, 50, 20);
		estado.setModel(new DefaultComboBoxModel(aces1.listasiglasuf()));
		contentPane.add(estado);
		
		lblcidade = new JLabel("CIDADE");
		aces1.padraojlabel(lblcidade);
		lblcidade.setBounds(4, 115, 50, 14);
		contentPane.add(lblcidade);

		cidade = new JTextField();
		aces1.liberado(cidade);
		cidade.setBounds(130, 112, 257, 20);
		contentPane.add(cidade);
		
		lblpais = new JLabel("PAÍS");
		aces1.padraojlabel(lblpais);
		lblpais.setBounds(397, 115, 40, 14);
		contentPane.add(lblpais);

		pais = new JTextField();
		aces1.liberado(pais);
		pais.setBounds(436, 112, 160, 20);
		contentPane.add(pais);
		
		lblcnpj = new JLabel("CNPJ");
		aces1.padraojlabel(lblcnpj);
		lblcnpj.setBounds(4, 140, 35, 14);
		contentPane.add(lblcnpj);

		cnpj = new JTextField();
		cnpj.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		cnpj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (cnpj.getText() == null || cnpj.getText().isEmpty()) {
						contato.requestFocus();
						contato.setCaretPosition(0);
						contato.selectAll();
					} else {
						if (f1.Validacnpj(JMascara.GetJmascaraLimpar(cnpj.getText()),
								codfor.getText().trim()) == true) {
							JOptionPane.showMessageDialog(null, "CNPJ ou CPF já Existe !!!!!!!!!");
							cnpj.setText("");
							contato.requestFocus();
							contato.setCaretPosition(0);
						} else {
							contato.requestFocus();
							contato.setCaretPosition(0);
						}

					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

				cnpj.setText(JMascara.GetJmascaraCpfCnpj(cnpj.getText()));
			}
		});
		cnpj.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (cnpj.getText() != null && !cnpj.getText().isEmpty()) {
					if (f1.Validacnpj(JMascara.GetJmascaraLimpar(cnpj.getText()), codfor.getText().trim()) == true) {
						cnpj.setText("");
					}

				}
			}
		});
		aces1.liberado(cnpj);
		cnpj.setBounds(130, 137, 148, 20);
		contentPane.add(cnpj);
		
		lblcontato = new JLabel("CONTATO");
		aces1.padraojlabel(lblcontato);
		lblcontato.setBounds(280, 140, 65, 14);
		contentPane.add(lblcontato);

		contato = new JTextField();
		aces1.liberado(contato);
		contato.setBounds(346, 137, 250, 20);
		contentPane.add(contato);
		
		JLabel lblTipo = new JLabel("TIPO");
		aces1.padraojlabel(lblTipo);
		lblTipo.setBounds(4, 165, 76, 14);
		contentPane.add(lblTipo);

		radiopessoajuridica = new JRadioButton("PESSOA JURIDICA");
		radiopessoajuridica.setBackground(aces1.corpadrao());
		radiopessoajuridica.setForeground(Color.BLACK);
		radiopessoajuridica.setFont(new Font("Tahoma", Font.BOLD, 13));
		radiopessoajuridica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radiopessoajuridica.isSelected()) {
					radiopessoafisica.setSelected(false);
					radiodesativado.setSelected(false);
				}
			}
		});
		radiopessoajuridica.setBounds(130, 162, 140, 20);
		if (p.getIDFORNECEDOR() == null) {
			radiopessoajuridica.setSelected(true);
		}
		contentPane.add(radiopessoajuridica);

		radiopessoafisica = new JRadioButton("PESSOA FISICA");
		radiopessoafisica.setBackground(aces1.corpadrao());
		radiopessoafisica.setForeground(Color.BLACK);
		radiopessoafisica.setFont(new Font("Tahoma", Font.BOLD, 13));
		radiopessoafisica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radiopessoafisica.isSelected()) {
					radiopessoajuridica.setSelected(false);
					radiodesativado.setSelected(false);
				}
			}
		});
		radiopessoafisica.setBounds(276, 162, 130, 20);
		contentPane.add(radiopessoafisica);

		radiodesativado = new JRadioButton("DESATIVADO");
		radiodesativado.setBackground(aces1.corpadrao());
		radiodesativado.setForeground(Color.BLACK);
		radiodesativado.setFont(new Font("Tahoma", Font.BOLD, 13));
		radiodesativado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radiodesativado.isSelected()) {
					radiopessoajuridica.setSelected(false);
					radiopessoafisica.setSelected(false);
				}
			}
		});
		radiodesativado.setBounds(411, 162, 110, 20);
		contentPane.add(radiodesativado);
		
		JLabel lbltelefone = new JLabel("TELEFONE");
		aces1.padraojlabel(lbltelefone);
		lbltelefone.setBounds(4, 190, 65, 14);
		contentPane.add(lbltelefone);
		
		telefone = new JTextField();
		contentPane.add(telefone);
		telefone.setBounds(130, 187, 225, 20);
		aces1.liberado(telefone);
		
		lblinscricaoestadual = new JLabel("INSC. ESTADUAL");
		aces1.padraojlabel(lblinscricaoestadual);
		lblinscricaoestadual.setBounds(360, 190, 105, 14);
		contentPane.add(lblinscricaoestadual);

		IE = new JTextField();
		aces1.liberado(IE);
		IE.setBounds(467, 187, 129, 20);
		contentPane.add(IE);
		
		lblemail = new JLabel("E-MAIL");
		aces1.padraojlabel(lblemail);
		lblemail.setBounds(4, 215, 90, 14);
		contentPane.add(lblemail);

		email = new JTextField();
		aces1.liberado(email);
		email.setBounds(130, 212, 240, 20);
		contentPane.add(email);
		
		lblsite = new JLabel("SITE");
		aces1.padraojlabel(lblsite);
		lblsite.setBounds(375, 215, 35, 14);
		contentPane.add(lblsite);

		site = new JTextField();
		aces1.liberado(site);
		site.setBounds(412, 212, 184, 20);
		contentPane.add(site);
		
		lbltransportadora = new JLabel("TRANSPORTADORA");
		aces1.padraojlabel(lbltransportadora);
		lbltransportadora.setBounds(4, 240, 122, 14);
		contentPane.add(lbltransportadora);

		transportadora = new JTextField();
		aces1.liberado(transportadora);
		transportadora.setBounds(130, 237, 466, 20);
		contentPane.add(transportadora);
		
		lblcnpjtransportadora = new JLabel("CNPJ");
		aces1.padraojlabel(lblcnpjtransportadora);
		lblcnpjtransportadora.setBounds(4, 265, 40, 14);
		contentPane.add(lblcnpjtransportadora);

		cnpjtransportadora = new JTextField();
		cnpjtransportadora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				cnpjtransportadora.setText(JMascara.GetJmascaraCpfCnpj(cnpjtransportadora.getText()));
			}
		});
		aces1.liberado(cnpjtransportadora);
		cnpjtransportadora.setBounds(130, 262, 143, 20);
		contentPane.add(cnpjtransportadora);

		lblenderecotransportadora = new JLabel("ENDEREÇO");
		aces1.padraojlabel(lblenderecotransportadora);
		lblenderecotransportadora.setBounds(278, 265, 68, 14);
		contentPane.add(lblenderecotransportadora);

		enderecotransportadora = new JTextField();
		aces1.liberado(enderecotransportadora);
		enderecotransportadora.setBounds(346, 262, 250, 20);
		contentPane.add(enderecotransportadora);

		JLabel lblfonetransportadora = new JLabel("TELEFONE");
		aces1.padraojlabel(lblfonetransportadora);
		lblfonetransportadora.setBounds(352, 290, 68, 14);
		contentPane.add(lblfonetransportadora);

		fonetransp = new JTextField();
		aces1.liberado(fonetransp);
		fonetransp.setBounds(419, 287, 177, 20);
		contentPane.add(fonetransp);

		lblietransportadora = new JLabel("INSC. ESTADUAL");
		aces1.padraojlabel(lblietransportadora);
		lblietransportadora.setBounds(4, 290, 110, 14);
		contentPane.add(lblietransportadora);

		ietransportadora = new JTextField();
		aces1.liberado(ietransportadora);
		ietransportadora.setBounds(130, 287, 220, 20);
		contentPane.add(ietransportadora);

		JLabel lblobs = new JLabel("OBSERVAÇÃO");
		aces1.padraojlabel(lblobs);
		lblobs.setBounds(250, 315, 88, 14);
		contentPane.add(lblobs);

		obs = new JTextArea(new TTextAreaDocument(250));
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
		obs.setBounds(4, 335, 592, 78);
		contentPane.add(obs);

		btncadastrar = new JButton();
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btncancelar.requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravafornecedor(p, acesso, u);
				}
			}
		});
		btncadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravafornecedor(p, acesso, u);
			}
		});
		btncadastrar.setIcon(new ImageIcon(listadecliente.class.getResource("/imagens/salvar.png")));
		btncadastrar.setBounds(214, 430, 55, 46);
		aces1.butonfundo(btncadastrar);
		contentPane.add(btncadastrar);

		btncancelar = new JButton();
		btncancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
				}
			}
		});
		btncancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btncancelar.setIcon(new ImageIcon(listadecliente.class.getResource("/imagens/fechar.png")));
		btncancelar.setBounds(279, 430, 55, 46);
		aces1.butonfundo(btncancelar);
		contentPane.add(btncancelar);

		if (p.getIDFORNECEDOR() != null) {

			SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
			if (p.getDT1COMPRA() != null) {
				DT1COMPRA1 = out.format(p.getDT1COMPRA());
			} else {
				DT1COMPRA1 = "";
			}

			if (p.getDTMCOMPRA() != null) {
				DTMCOMPRA1 = out.format(p.getDTMCOMPRA());
			} else {
				DT1COMPRA1 = "";
			}

			if (p.getNIVEL() == 1 || p.getNIVEL() == 0) {
				radiopessoajuridica.setSelected(true);
			}

			if (p.getNIVEL() == 2) {
				radiopessoafisica.setSelected(true);
			}

			if (p.getNIVEL() == 3) {
				radiodesativado.setSelected(true);
			}

			codfor.setText(p.getCODFOR());
			descfor.setText(p.getDESCFOR());
			razaosocial.setText(p.getRAZAO());
			numero.setText(p.getNUMERO());
			fonetransp.setText(p.getFONETRANS());
			contato.setText(p.getCONTATO());
			numero.setText(p.getNUMERO());
			pais.setText(p.getCORREIO());
			ibge.setText(p.getIBGE());
			endereco.setText(p.getENDER());
			bairro.setText(p.getBAIRRO());
			site.setText(p.getSITE());
			cidade.setText(p.getCIDADE());
			telefone.setText(p.getFONE());
			estado.setSelectedItem(p.getESTADO());
			cep.setText(JMascara.GetJmascaraCep(p.getCEP()));
			telefone.setText(p.getFONE());
			IE.setText(p.getIE());
			cnpj.setText(JMascara.GetJmascaraCpfCnpj(p.getCGC()));
			transportadora.setText(p.getTRANSP());
			enderecotransportadora.setText(p.getENDTRANS());
			fonetransp.setText(p.getFONETRANS());
			cnpjtransportadora.setText(JMascara.GetJmascaraCpfCnpj(p.getCGCTRANS()));
			ietransportadora.setText(p.getIETRANS());
			email.setText(p.getEMAIL());
			fonetransp.setText(p.getA2_NATUREZ());
			obs.setText(p.getOBS());
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

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

	}

	public void gravafornecedor(Cadfor f2, Acesso acesso, Usuario uf) {

		Cadfor cadastro = new Cadfor();
		
		if(f2.IDFORNECEDOR != null) {
			cadastro = 	f1.procura(f2.IDFORNECEDOR);
		}

		String log = "ATIVADO";
		try {
			cadastro.setCODFOR(codfor.getText().trim());
			cadastro.setDESCFOR(descfor.getText().trim());
			cadastro.setRAZAO(razaosocial.getText().trim());
			cadastro.setCONTATO(contato.getText().trim());
			cadastro.setDEPTO(numero.getText().trim());
			cadastro.setDEPTO1(ibge.getText().trim());
			cadastro.setENDER(endereco.getText().trim());
			cadastro.setNUMERO(numero.getText().trim());
			cadastro.setBAIRRO(bairro.getText().trim());
			cadastro.setCIDADE(cidade.getText().trim());
			cadastro.setIBGE(ibge.getText().trim());
			cadastro.setESTADO(estado.getSelectedItem().toString());
			cadastro.setCEP(JMascara.GetJmascaraLimpar(cep.getText().trim()));
			cadastro.setFONE(telefone.getText().trim());
			cadastro.setIE(IE.getText().trim());
			cadastro.setCGC(JMascara.GetJmascaraLimpar(cnpj.getText().trim()));
			cadastro.setTRANSP(transportadora.getText().trim());
			cadastro.setENDTRANS(enderecotransportadora.getText().trim());
			cadastro.setFONETRANS(fonetransp.getText().trim());
			cadastro.setCGCTRANS(JMascara.GetJmascaraLimpar(cnpjtransportadora.getText().trim()));
			cadastro.setIETRANS(ietransportadora.getText().trim());
			cadastro.setSEGMENTO(ietransportadora.getText().trim());
			cadastro.setCORREIO(pais.getText().trim());
			if (radiodesativado.isSelected()) {
				cadastro.setNIVEL(Double.parseDouble("3"));
			}

			if (radiopessoafisica.isSelected()) {
				cadastro.setNIVEL(Double.parseDouble("2"));
			}

			if (radiopessoajuridica.isSelected()) {
				cadastro.setNIVEL(Double.parseDouble("1"));
			}
			if (!radiodesativado.isSelected() && !radiopessoafisica.isSelected() && !radiopessoajuridica.isSelected()) {
				cadastro.setNIVEL(Double.parseDouble("1"));
			}
			cadastro.setEMAIL(email.getText().trim());
			cadastro.setSITE(site.getText().trim());
			cadastro.setOBS(obs.getText().trim());

			if (cadastro.getRAZAO().isEmpty() || cadastro.getDESCFOR().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Dados em branco");
			} else {
				Boolean passa = false;
				if (cadastro.getIDFORNECEDOR() == null) {
					if (acesso.getInclusao() == true) {
						f1.adiciona(cadastro);
						if (codfor.getText().equals(null) || codfor.getText().isEmpty()) {
							cadastro.setCODFOR(
									String.format("%06d", Integer.parseInt(cadastro.getIDFORNECEDOR().toString())));
							f1.adiciona(cadastro);
							
							if (cadastro.getNIVEL().toString().replace(".0", "").equals("3")) {
								log = "DESATIVADO";
							}
							repositorylog.adicionaLog(aces1.logincluir, "FORNECEDOR " + cadastro.getCODFOR().trim() + " " + cadastro.getDESCFOR().trim() + " FOI " + log,
									cadastro.getIDFORNECEDOR(), uf);
							passa = true;
						}
					} else {

						JOptionPane.showMessageDialog(null, aces1.incluir);
					}
				} else {

					if (acesso.getAlteracao() == true) {
						f1.adiciona(cadastro);
						if (!cadastro.getNIVEL().toString().replace(".0", "").equals( f2.getNIVEL().toString().replace(".0", ""))) {
							if (cadastro.getNIVEL().toString().replace(".0", "").equals("3")) {
								log = "DESATIVADO";
							}
							repositorylog.adicionaLog(aces1.logalterar, "FORNECEDOR " + cadastro.getCODFOR().trim() + " " + cadastro.getDESCFOR().trim() + " FOI " + log,
									cadastro.getIDFORNECEDOR(), uf);
						}
						passa = true;
					} else {

						JOptionPane.showMessageDialog(null, aces1.alterar);
					}

				}

				if (passa == true) {
					dispose();
				}
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "ERRO AO GRAVAR O FORNECEDOR " + e1.getMessage());
			aces1.demologger.error("ERRO AO GRAVAR O FORNECEDOR " + e1.getMessage());
		}

	}
	
}