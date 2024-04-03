package Grafico.usuario;

import java.awt.BorderLayout;

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

import Configuracao.LetraMaiusculacomLimite;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

import javax.swing.JLabel;

import java.util.Collections;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import beans.AcessoBean;
import beans.Usuariobean;
import entidades.Acesso;
import entidades.Usuario;
import filter.EntityManagerUtil;
import repositorios.LogusuRepository;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class cadastrousuario extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JTextField loginuser, nome, senhauser, email;

	private JLabel lbllogin, lblnome, lblsenha, lblemail, lbldesativado;

	JCheckBox chdesativado;

	private JButton btncadastrar;
	private JButton btnfechar;
	Usuariobean userbean = new Usuariobean();
	AcessoBean aces1 = new AcessoBean();

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public cadastrousuario(final Usuario u, final Usuario p, Acesso acesso) {

		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(470, 250, 353, 235);
		contentPanel = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		aces1.fundotela(contentPanel);
		setTitle("USUARIO");

		lbllogin = new JLabel("LOGIN");
		aces1.padraojlabel(lbllogin);
		lbllogin.setBounds(10, 18, 82, 14);
		contentPanel.add(lbllogin);

		loginuser = new JTextField();
		if (p.getId() == null) {
			loginuser.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent evt) {
					if (evt.getClickCount() == 1) {
						if (loginuser.getText().isEmpty() || senhauser.getText().isEmpty()
								|| nome.getText().isEmpty()) {
							loginuser.requestFocus();
						}
						loginuser.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
								Collections.EMPTY_SET);

						if (loginuser.getText().isEmpty() || loginuser.getText() == null) {
							loginuser.requestFocus();
						} else {
							if (Validacao(loginuser.getText(), senhauser.getText())) {
								nome.requestFocus();
								aces1.liberado(nome);
								aces1.bloqueado(loginuser);
							} else {
								JOptionPane.showMessageDialog(null, "usuário ja existe");
								loginuser.requestFocus();
								loginuser.setText("");
								aces1.bloqueado(senhauser);
								aces1.bloqueado(nome);
							}
						}
					}
				}

			});
		}
		loginuser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
				if (loginuser.getText().isEmpty() || senhauser.getText().isEmpty() || nome.getText().isEmpty()) {
					loginuser.requestFocus();
				}
				loginuser.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
				if (evt.getKeyCode() == KeyEvent.VK_TAB) {
					if (loginuser.getText().isEmpty() || loginuser.getText() == null) {
						loginuser.requestFocus();
					} else {
						if (Validacao(loginuser.getText(), senhauser.getText())) {
							nome.requestFocus();
							aces1.liberado(nome);
							aces1.bloqueado(loginuser);
						} else {
							JOptionPane.showMessageDialog(null, "usuário ja existe");
							loginuser.requestFocus();
							loginuser.setText("");
							aces1.bloqueado(senhauser);
							aces1.bloqueado(nome);
						}
					}
				}
			}

		});
		loginuser.setBounds(100, 15, 217, 20);
		loginuser.setDocument(new LetraMaiusculacomLimite(20));
		if (p.getId() != null) {
			aces1.bloqueado(loginuser);
		} else {
			aces1.liberado(loginuser);
			loginuser.requestFocus();
		}
		contentPanel.add(loginuser);
		loginuser.setColumns(10);

		lblnome = new JLabel("NOME");
		aces1.padraojlabel(lblnome);
		lblnome.setBounds(10, 43, 80, 14);
		contentPanel.add(lblnome);

		nome = new JTextField();
		nome.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		nome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
					if (nome.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nome não pode estar em branco");
						nome.requestFocus();
					} else {
						if (p.getId() != null) {
							nome.requestFocus();
							aces1.liberado(senhauser);
							senhauser.requestFocus();
						} else {
							senhauser.requestFocus();
							aces1.liberado(senhauser);
						}
					}
				}
			}
		});
		nome.setBounds(100, 40, 217, 20);
		nome.setDocument(new Configuracao.Letramaiuscula());
		aces1.bloqueado(nome);
		contentPanel.add(nome);

		lblsenha = new JLabel("SENHA");
		aces1.padraojlabel(lblsenha);
		lblsenha.setBounds(10, 68, 46, 14);
		contentPanel.add(lblsenha);

		senhauser = new JPasswordField();
		senhauser.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		senhauser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {

					if (validalogin() == true) {
						JOptionPane.showMessageDialog(null, "Login ou senha ja existe");
					} else {
						aces1.liberado(email);
						email.requestFocus();
						email.setCaretPosition(0);
					}

				}

			}

		});
		senhauser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (validalogin() == true) {
					JOptionPane.showMessageDialog(null, "Login ou senha ja existe");
				} else {
					aces1.liberado(email);
				}
			}
		});
		aces1.bloqueado(loginuser);
		senhauser.setBounds(100, 65, 217, 20);
		contentPanel.add(senhauser);

		lblemail = new JLabel("EMAIL");
		aces1.padraojlabel(lblemail);
		lblemail.setBounds(10, 93, 80, 14);
		contentPanel.add(lblemail);

		email = new JTextField();
		aces1.bloqueado(email);
		email.setBounds(100, 90, 217, 20);
		contentPanel.add(email);

		lbldesativado = new JLabel("DESATIVADO");
		aces1.padraojlabel(lbldesativado);
		lbldesativado.setBounds(10, 118, 100, 14);
		contentPanel.add(lbldesativado);

		chdesativado = new JCheckBox("");
		chdesativado.setBackground(aces1.corpadrao());
		chdesativado.setBounds(100, 114, 25, 20);
		if(acesso.getExclusao() == true) {
			chdesativado.setEnabled(true);
		}else {
			chdesativado.setEnabled(false);
		}
		contentPanel.add(chdesativado);

		btncadastrar = new JButton();
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnfechar.requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravausuario(p, u, acesso);
				}
			}
		});
		btncadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravausuario(p, u, acesso);

			}
		});

		btncadastrar.setIcon(new ImageIcon(cadastrousuario.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		btncadastrar.setToolTipText("SALVAR");
		btncadastrar.setBounds(100, 140, 55, 46);
		btncadastrar.setEnabled(false);
		contentPanel.add(btncadastrar);

		btnfechar = new JButton();
		btnfechar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
				}
			}
		});
		btnfechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnfechar.setIcon(new ImageIcon(cadastrousuario.class.getResource("/imagens/fechar.png")));
		btnfechar.setBounds(180, 140, 55, 46);
		aces1.butonfundo(btnfechar);
		btnfechar.setToolTipText("FECHAR");
		contentPanel.add(btnfechar);

		if (p.getId() != null) {
			aces1.liberado(nome);
			loginuser.setText(p.getLogin().trim());
			nome.setText(p.getNome().trim());
			senhauser.setText(p.getSenha().trim());
			email.setText(p.getEmail());
			chdesativado.setSelected(p.isAtivo());
			aces1.liberado(senhauser);
			btncadastrar.setEnabled(true);
		} else {
			aces1.liberado(loginuser);
			aces1.bloqueado(senhauser);
			aces1.bloqueado(email);
			chdesativado.setSelected(false);
			btncadastrar.setEnabled(true);
		}

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				if (p.getId() != null) {
					nome.requestFocus();
				} else {
					loginuser.requestFocus();
					loginuser.setCaretPosition(0);
				}
			}

			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

	public boolean Validacao(String login1, String nome1) {
		Usuariobean user = new Usuariobean();
		Usuario uservalida = new Usuario();
		uservalida.setLogin(null);
		boolean vT = false;
		try {
			uservalida.setLogin(user.procuranomeusuario(login1, nome1));
		} catch (Exception e) {

		}
		if (uservalida.getLogin() == null || uservalida.getLogin().isEmpty()) {
			vT = true;
		}

		return vT;

	}

	public void gravausuario(Usuario u2, Usuario up, Acesso ap) {

		try {

			Usuario cadastro = new Usuario();
			Usuariobean ubean = new Usuariobean();
			String ativa = "ATIVADO";

			if (u2.getId() != null) {

				cadastro = ubean.procura(u2.getId());

			}

			cadastro.setLogin(loginuser.getText().trim());
			if (u2.getId() == null) {
				cadastro.setSenha(ubean.convertStringToMd5(senhauser.getText().trim()));
			} else {
				if (u2.getSenha().equals(senhauser.getText().trim())) {
					cadastro.setSenha(senhauser.getText().trim());
				} else {
					cadastro.setSenha(ubean.convertStringToMd5(senhauser.getText().trim()));
				}
			}
			cadastro.setNome(nome.getText());
			cadastro.setAtivo(aces1.retornaBoolean(chdesativado));

			if (cadastro.getLogin().isEmpty() || cadastro.getSenha().isEmpty() || cadastro.getNome().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Dados obrigatórios em branco");
			} else {

				if (nome.getText().equals(null) || nome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nome em branco");
					btncadastrar.setEnabled(false);
				} else {
					if (u2.getId() == null) {
						if (ap.getInclusao() == true) {
							ubean.adiciona(cadastro);

							repositorylog.adicionaLog(aces1.logincluir, "USUARIO " + " NOME " + cadastro.getNome(),
									cadastro.getId(), up);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, aces1.incluir);

						}

					} else {
						if (ap.getAlteracao() == true) {
							ubean.adiciona(cadastro);

							repositorylog.adicionaLog(aces1.logalterar, "USUARIO " + " NOME " + cadastro.getNome(),
									cadastro.getId(), up);
							
							if(u2.isAtivo() != cadastro.isAtivo()) {
								if(cadastro.isAtivo() == true) {
									ativa = "DESATIVADO";
								}
								
								repositorylog.adicionaLog(aces1.logalterar,
										"USUARIO " + ativa + " NOME " + cadastro.getNome(),
										cadastro.getId(), up);
								
							}
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, aces1.alterar);

						}

					}

				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO AO GRAVAR USUARIO " + e.getMessage());
			aces1.demologger.error("ERRO AO GRAVAR USUARIO " + e.getMessage());

		}
	}

	public Boolean validalogin() {
		Boolean m = false;
		if (senhauser.getText().equals(null) || senhauser.getText().isEmpty()) {
			senhauser.requestFocus();
		} else {

			if (loginuser.getText().isEmpty() || senhauser.getText().isEmpty()) {
				btncadastrar.setEnabled(false);
			} else {
				btncadastrar.setEnabled(true);
				aces1.liberado(email);
			}
			if (userbean.procurasenha(loginuser.getText(), senhauser.getText()) == false) {
				btncadastrar.setEnabled(true);
				aces1.liberado(email);

			} else {
				senhauser.requestFocus();
				btncadastrar.setEnabled(false);
				senhauser.setText("");
				m = true;
			}
		}
		return m;

	}
}
