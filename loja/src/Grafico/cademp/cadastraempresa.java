package Grafico.cademp;

import entidades.Acesso;
import entidades.Cademp;
import entidades.Municip;
import entidades.Usuario;
import filter.EntityManagerUtil;
import filter.EnviaEmail;
import repositorios.LogusuRepository;
import Jm.JMascara;
import Municipio.ListaMunicipio;

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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

import javax.swing.JFormattedTextField;

import beans.AcessoBean;
import beans.CadempBean;
import beans.MunicipBean;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class cadastraempresa extends JDialog {
	private JPanel contentPane;
	private JTextField fantasia, ender, numero, bairro, cidade, cnpj, telefone, ibge, inscest, site, cep, email, porta,
			smtp, senhaemail, razaosocial;

	private JComboBox estado;

	private JLabel lblsmtp, lblsenhaemail;

	private JCheckBox chautenticaossl;

	JButton btncadastrar, btnlimpar;

	private Long id;
	Configuracao.Letramaiuscula bloqueios = new Configuracao.Letramaiuscula();
	AcessoBean aces1 = new AcessoBean();

	Municip m = new Municip();
	MunicipBean mbean = new MunicipBean();

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public cadastraempresa(final Cademp emp, Usuario u, Acesso acesso) throws Exception {

		setTitle("EMPRESA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.imagemicone()));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(10, 5, 607, 440);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		aces1.fundotela(contentPane);
		contentPane.setLayout(null);

		JLabel lblrazaosocial = new JLabel("RAZ\u00C3O SOCIAL");
		aces1.padraojlabel(lblrazaosocial);
		lblrazaosocial.setBounds(10, 40, 95, 14);
		contentPane.add(lblrazaosocial);

		fantasia = new JTextField();
		fantasia.setBounds(110, 63, 323, 20);
		fantasia.setDocument(new Configuracao.Letramaiuscula());
		aces1.liberado(fantasia);
		contentPane.add(fantasia);

		razaosocial = new JTextField();
		razaosocial.setBounds(110, 38, 456, 20);
		razaosocial.setDocument(new Configuracao.Letramaiuscula());
		aces1.liberado(razaosocial);
		contentPane.add(razaosocial);

		JLabel lblFantasia = new JLabel("FANTASIA");
		aces1.padraojlabel(lblFantasia);
		lblFantasia.setBounds(10, 65, 63, 14);
		contentPane.add(lblFantasia);

		JLabel label = new JLabel("");
		label.setBounds(405, 27, 0, 0);
		contentPane.add(label);

		JLabel lblEndereo = new JLabel("ENDERE\u00C7O");
		aces1.padraojlabel(lblEndereo);
		lblEndereo.setBounds(10, 90, 80, 14);
		contentPane.add(lblEndereo);

		ender = new JTextField();
		ender.setBounds(110, 88, 323, 20);
		aces1.liberado(ender);
		contentPane.add(ender);

		JLabel lblnm = new JLabel("N\u00BA");
		aces1.padraojlabel(lblnm);
		lblnm.setBounds(464, 90, 21, 14);
		contentPane.add(lblnm);

		numero = new JTextField();
		numero.setBounds(486, 88, 80, 20);
		aces1.liberado(numero);
		contentPane.add(numero);

		JLabel lblbairro = new JLabel("BAIRRO");
		aces1.padraojlabel(lblbairro);
		lblbairro.setBounds(10, 115, 50, 14);
		contentPane.add(lblbairro);

		bairro = new JTextField();
		bairro.setBounds(110, 113, 290, 20);
		aces1.liberado(bairro);
		contentPane.add(bairro);

		JLabel lblcep = new JLabel("CEP");
		aces1.padraojlabel(lblcep);
		lblcep.setBounds(420, 115, 30, 14);
		contentPane.add(lblcep);

		cep = new javax.swing.JFormattedTextField(setNumero("#####-###"));
		cep.setBounds(449, 113, 117, 20);
		aces1.liberado(cep);
		aces1.numeros(cep);
		contentPane.add(cep);

		JLabel lblcidade = new JLabel("CIDADE");
		aces1.padraojlabel(lblcidade);
		lblcidade.setBounds(10, 140, 50, 14);
		contentPane.add(lblcidade);

		cidade = new JTextField();
		cidade.setBounds(110, 138, 290, 20);
		aces1.liberado(cidade);
		cidade.setDocument(new Configuracao.Letramaiuscula());
		contentPane.add(cidade);

		JLabel lblestado = new JLabel("ESTADO");
		aces1.padraojlabel(lblestado);
		lblestado.setBounds(470, 140, 55, 14);
		contentPane.add(lblestado);

		estado = new JComboBox();
		estado.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		estado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					cnpj.requestFocus();
					cnpj.setCaretPosition(0);

				}
			}
		});
		estado.setBounds(525, 138, 41, 20);
		estado.setForeground(Color.BLUE);
		estado.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		contentPane.add(estado);

		cnpj = new JTextField();
		cnpj.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		cnpj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (cnpj.getText() == null || cnpj.getText().isEmpty()) {
						inscest.requestFocus();
						inscest.setCaretPosition(0);
						inscest.selectAll();
					} else {
						if (cnpj.getText().length() < 11) {
							cnpj.setText("");
						} else {
							cnpj.setText(JMascara.GetJmascaraCpfCnpj(cnpj.getText()));
						}
						inscest.requestFocus();
						inscest.setCaretPosition(0);
						inscest.selectAll();

					}
				}
			}
		});
		cnpj.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (cnpj.getText() != null && !cnpj.getText().isEmpty()) {
					if (cnpj.getText().length() < 11) {
						cnpj.setText("");
					} else {
						cnpj.setText(JMascara.GetJmascaraCpfCnpj(cnpj.getText()));
					}
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				cnpj.selectAll();
			}
		});
		cnpj.setBounds(110, 163, 149, 20);
		aces1.liberado(cnpj);
		aces1.numeros(cnpj);
		contentPane.add(cnpj);

		JLabel lblcnpj = new JLabel("CNPJ");
		aces1.padraojlabel(lblcnpj);
		lblcnpj.setBounds(10, 165, 37, 14);
		contentPane.add(lblcnpj);

		JLabel lbltelefone = new JLabel("TELEFONE");
		aces1.padraojlabel(lbltelefone);
		lbltelefone.setBounds(10, 190, 65, 14);
		contentPane.add(lbltelefone);

		telefone = new JTextField();
		telefone.setBounds(110, 188, 149, 20);
		aces1.liberado(telefone);
		contentPane.add(telefone);

		JLabel lblibge = new JLabel("IBGE");
		aces1.padraojlabel(lblibge);
		lblibge.setBounds(445, 65, 31, 14);
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
								ender.requestFocus();
							} else {
								ibge.setText("");
								ibge.requestFocus();
							}
						} else {
							m = mbean.validamunicipio(ibge.getText().trim());

							if (m.getId() != null) {
								ibge.setText(m.getCod_ibge().trim());
								ender.requestFocus();
							} else {
								JOptionPane.showMessageDialog(null, "Municipio não encontrado!!!!! ");
								ibge.setText("");
								ibge.requestFocus();
							}
						}

					} catch (Exception e) {

						e.printStackTrace();
					}

				}

			}

		});

		ibge.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB) {
					if (ibge.getText() == null || ibge.getText().isEmpty()) {
						ender.requestFocus();
					} else {
						m = mbean.validamunicipio(ibge.getText().trim());

						if (m.getId() != null) {
							ibge.setText(m.getCod_ibge().trim());
							ender.requestFocus();
							ender.setCaretPosition(0);
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
					}

				} catch (Exception e1) {

					e1.printStackTrace();
				}

				if (m.getId() != null) {
					ibge.setText(m.getCod_ibge().trim());
					ender.requestFocus();
					ender.setCaretPosition(0);
				} else {
					ibge.setText("");
				}
			}
		});
		ibge.setBounds(476, 63, 90, 20);
		aces1.liberado(ibge);
		aces1.numeros(ibge);
		contentPane.add(ibge);

		JLabel lblinscricao = new JLabel("INSCRI\u00C7\u00C3O ESTADUAL");
		aces1.padraojlabel(lblinscricao);
		lblinscricao.setBounds(270, 165, 142, 14);
		contentPane.add(lblinscricao);

		inscest = new JTextField();
		inscest.setBounds(417, 163, 149, 20);
		aces1.liberado(inscest);
		aces1.numeroscomponto(inscest);
		contentPane.add(inscest);

		JLabel lblsite = new JLabel("SITE");
		aces1.padraojlabel(lblsite);
		lblsite.setBounds(280, 190, 33, 14);
		contentPane.add(lblsite);

		site = new JTextField();
		site.setBounds(315, 188, 251, 20);
		aces1.liberado(site);
		contentPane.add(site);

		JLabel lblempresa = new JLabel("EMPRESA");
		lblempresa.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblempresa.setForeground(Color.BLACK);
		lblempresa.setBounds(247, 11, 130, 20);
		contentPane.add(lblempresa);

		JLabel lbldadosemail = new JLabel("DADOS DE EMAIL");
		lbldadosemail.setFont(new Font("Arial Black", Font.BOLD, 22));
		lbldadosemail.setForeground(Color.BLACK);
		lbldadosemail.setBounds(190, 215, 240, 20);
		contentPane.add(lbldadosemail);

		JLabel lblemail = new JLabel("E-MAIL");
		aces1.padraojlabel(lblemail);
		lblemail.setBounds(10, 252, 65, 14);
		contentPane.add(lblemail);

		email = new JTextField();
		aces1.liberado(email);
		email.setBounds(110, 250, 279, 20);
		contentPane.add(email);

		JLabel lblporta = new JLabel("PORTA");
		aces1.padraojlabel(lblporta);
		lblporta.setBounds(397, 252, 46, 14);
		contentPane.add(lblporta);

		porta = new JTextField();
		aces1.liberado(porta);
		aces1.numeros(porta);
		porta.setBounds(444, 250, 122, 20);
		contentPane.add(porta);

		lblsmtp = new JLabel("SMTP");
		aces1.padraojlabel(lblsmtp);
		lblsmtp.setBounds(10, 277, 65, 14);
		contentPane.add(lblsmtp);

		smtp = new JTextField();
		smtp.setBounds(110, 275, 249, 20);
		aces1.liberado(smtp);
		contentPane.add(smtp);

		lblsenhaemail = new JLabel("SENHA");
		aces1.padraojlabel(lblsenhaemail);
		lblsenhaemail.setBounds(369, 277, 43, 14);
		contentPane.add(lblsenhaemail);

		senhaemail = new JPasswordField();
		senhaemail.setBounds(417, 275, 149, 20);
		aces1.liberado(senhaemail);
		contentPane.add(senhaemail);

		chautenticaossl = new JCheckBox("AUTENTICA\u00C7\u00C3O SSL");
		chautenticaossl.setFont(new Font("Tahoma", Font.BOLD, 14));
		chautenticaossl.setForeground(Color.BLACK);
		chautenticaossl.setBackground(aces1.corpadrao());
		chautenticaossl.setBounds(215, 300, 180, 20);
		contentPane.add(chautenticaossl);

		btncadastrar = new JButton();
		btncadastrar.setBounds(229, 340, 55, 46);
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnlimpar.requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravaempresa(emp, acesso,u);
				}
			}
		});
		btncadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravaempresa(emp, acesso,u);

			}
		});
		btncadastrar.setIcon(new ImageIcon(cadastraempresa.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		contentPane.add(btncadastrar);

		btnlimpar = new JButton();
		btnlimpar.setBounds(304, 340, 55, 46);
		btnlimpar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					setVisible(false);
				}
			}
		});
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * fantasia.setText(""); razaosocial.setText("");
				 */
				setVisible(false);
			}
		});
		btnlimpar.setIcon(new ImageIcon(cadastraempresa.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnlimpar);
		contentPane.add(btnlimpar);

		JButton btntestaemail = new JButton();
		btntestaemail.setFont(new Font("Tahoma", Font.BOLD, 11));
		btntestaemail.setForeground(new Color(0, 0, 0));
		btntestaemail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validadadosemail();
			}
		});
		btntestaemail.setText("TESTA EMAIL");
		btntestaemail.setBounds(420, 300, 110, 20);
		contentPane.add(btntestaemail);

		if (emp.getId() != null) {

			fantasia.setText(emp.getFantasia().trim());
			razaosocial.setText(emp.getRazao().trim());
			ender.setText(emp.getEnder().trim());
			numero.setText(emp.getNum().trim());
			bairro.setText(emp.getBairro().trim());
			cidade.setText(emp.getCidade().trim());
			cep.setText(emp.getCep().trim());
			estado.setSelectedItem(emp.getEstado().trim());
			cnpj.setText(JMascara.GetJmascaraCpfCnpj(emp.getCnpj().trim()));
			telefone.setText(emp.getFone().trim());
			site.setText(emp.getSite().trim());
			inscest.setText(emp.getInscmun().trim());
			ibge.setText(emp.getIbge().trim());
			email.setText(emp.getEmail().trim());
			porta.setText(emp.getPorta().trim());
			smtp.setText(emp.getSmtp().trim());
			senhaemail.setText(emp.getSenha().trim());
			chautenticaossl.setSelected(emp.getAutenticacaossl());
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

	private MaskFormatter setNumero(String numero) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(numero);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}

	public void gravaempresa(Cademp e2, Acesso ap, Usuario up) {

		Cademp e = new Cademp();
		CadempBean empbean = new CadempBean();

		if (e2.getId() != null) {
			e = empbean.procura(e2.getId());
		}

		e.setFantasia(fantasia.getText().trim());
		e.setRazao(razaosocial.getText().trim());
		e.setEnder(ender.getText().trim());
		e.setNum(numero.getText().trim());
		e.setBairro(bairro.getText().trim());
		e.setCidade(cidade.getText().trim());
		e.setCep(cep.getText().trim());
		e.setEstado(estado.getSelectedItem().toString().trim());
		e.setCnpj(JMascara.GetJmascaraLimpar(cnpj.getText().trim()));
		e.setFone(telefone.getText().trim());
		e.setSite(site.getText().trim());
		e.setInscmun(inscest.getText().trim());
		e.setIbge(ibge.getText().trim());
		e.setEmail(email.getText().trim());
		e.setPorta(porta.getText().trim());
		e.setSmtp(smtp.getText().trim());
		e.setSenha(senhaemail.getText());
		e.setAutenticacaossl(chautenticaossl.isSelected());

		if (e.getCodemp().isEmpty() || e.getFantasia().isEmpty() || e.getRazao().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Dados em branco");
			btncadastrar.setEnabled(false);
		} else {

			try {
				if (e2.getId() == null) {
					if (ap.getInclusao() == true) {
						empbean.adiciona(e);

						repositorylog.adicionaLog(aces1.logincluir, "EMPRESA " +  e.getFantasia(),
								e.getId(), up);

						dispose();
					} else {
						JOptionPane.showMessageDialog(null, aces1.incluir);
					}
				} else {
					if (ap.getAlteracao() == true) {
						empbean.adiciona(e);
						
						repositorylog.adicionaLog(aces1.logalterar, "EMPRESA " +  e.getFantasia(),
								e.getId(), up);
						
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, aces1.alterar);
					}

				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Erro grava empresa " + e1.getMessage());
			}

		}
	}

	public void validadadosemail() {
		EnviaEmail mail = new EnviaEmail();

		if (mail.emailteste(fantasia.getText().trim(), smtp.getText().trim(), porta.getText().trim(),
				senhaemail.getText().trim(), email.getText().trim(), chautenticaossl.isSelected()) == true) {
			JOptionPane.showMessageDialog(null, "E-mail enviado com sucesso !!!!!!!!!!");
		} else {

			JOptionPane.showMessageDialog(null, "E-mail não enviado !!!!!!!!!!", "", JOptionPane.ERROR_MESSAGE);

		}

	}

}
