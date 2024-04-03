package Grafico.usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Grafico.geral.TelaPrincipal;
import Grafico.geral.listadeacesso;
import beans.AcessoBean;
import beans.Usuariobean;
import entidades.Acesso;
import entidades.Usuario;
import filter.EntityManagerUtil;
import repositorios.AcessoRepository;
import repositorios.LogusuRepository;

import java.awt.event.KeyAdapter;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class listadeusuario extends JDialog {

	public JTable table;
	Usuariobean userbean = new Usuariobean();
	private JPanel contentPane;
	private JToolBar botoes;
	AcessoBean aces1 = new AcessoBean();
	private JButton btnexcluir, btnIncluir, btndireitos, btnheranca;
	DefaultTableModel listadeusuario = new DefaultTableModel() {

		public Class<?> getColumnClass(int col) {
			if (col == 4) {
				return Boolean.class;
			}

			return super.getColumnClass(col);
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	String rotina = TelaPrincipal.rotinau;
	ResultSet resultado;
	Usuario user = new Usuario();
	Usuario userclone = new Usuario();
	private JTextField pesquisar;

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public listadeusuario(final Usuario u) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(470, 250, 547, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setTitle("USUARIOS");
		Acesso acesso = aces1.procuraacesso(u, rotina);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setToolTipText("red");
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		scrollPane_1.setBounds(1, 47, 531, 226);
		setBackground(getBackground());
		setForeground(Color.BLACK);
		contentPane.add(scrollPane_1);

		JLabel lblpesquisar = new JLabel("PESQUISAR");
		lblpesquisar.setForeground(Color.BLUE);
		lblpesquisar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpesquisar.setBounds(10, 15, 78, 14);
		contentPane.add(lblpesquisar);

		pesquisar = new JTextField();
		pesquisar.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		pesquisar.setForeground(Color.BLUE);
		pesquisar.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void keyTyped(KeyEvent arg0) {
				int i = 0;

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listadeusuario);

				TableModel model = (TableModel) table.getModel();

				sorter = new TableRowSorter<TableModel>(model);
				table.setRowSorter(sorter);

				String text = pesquisar.getText();
				if (text.length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter(text));
				}

				table.changeSelection(0, 0, false, false);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					table.requestFocus();
					table.changeSelection(0, 0, false, false);
				}
			}

		});
		pesquisar.setDocument(new Configuracao.Letramaiuscula());
		pesquisar.setBounds(93, 11, 400, 20);
		contentPane.add(pesquisar);

		botoes = new JToolBar();
		botoes.setLocation(0, 272);
		botoes.setSize(530, 60);
		aces1.menubotao(botoes);
		contentPane.add(botoes, BorderLayout.CENTER);

		table = new JTable(listadeusuario) {
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
				Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
				c.setFont(aces1.tipoletra());
				aces1.tipocomponete(c);
				if (isCellSelected(rowIndex, vColIndex)) {
					c.setBackground(aces1.corcomponente());
				} else {

					if (rowIndex % 2 == 0) {
						c.setForeground(new Color(28, 28, 28));
					} else {
						c.setBackground(new Color(220, 220, 220));
					}

					if (table.getValueAt(rowIndex, 3).equals(true)) {
						c.setForeground(new Color(255, 0, 0));
					}
				}

				return c;
			}
		};

		aces1.tipotable(table);

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnIncluir.requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravaitemusuario(u, acesso);
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {

					gravaitemusuario(u, acesso);
				}

			}

		});

		scrollPane_1.setViewportView(table);

		listadeusuario.addColumn("idusuario");
		listadeusuario.addColumn("LOGIN");
		listadeusuario.addColumn("NOME");
		listadeusuario.addColumn("DESATIVADO");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela1();

		table.getColumnModel().getColumn(1).setPreferredWidth(158);
		table.getColumnModel().getColumn(2).setPreferredWidth(350);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);

		btnIncluir = new JButton();
		btnIncluir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnexcluir.requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Usuario user = new Usuario();
					new cadastrousuario(u, user, acesso).setVisible(true);
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setNumRows(0);
					try {
						carregartabela1();
						table.changeSelection(0, 0, false, false);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR LISTA USUARIO " + e1.getMessage());
						aces1.demologger.error("ERRO AO CARREGAR LISTA USUARIO " + e1.getMessage());
					}
				}
			}
		});
		btnIncluir.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				Usuario user = new Usuario();
				new cadastrousuario(u, user, acesso).setVisible(true);
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setNumRows(0);
				try {
					carregartabela1();
					table.changeSelection(0, 0, false, false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR LISTA USUARIO " + e.getMessage());
					aces1.demologger.error("ERRO AO CARREGAR LISTA USUARIO " + e.getMessage());
				}
			}
		});
		btnIncluir.setIcon(new ImageIcon(listadeusuario.class.getResource("/imagens/incluir.png")));
		btnIncluir.setToolTipText("INCLUIR");
		aces1.butonfundo(btnIncluir);
		if (acesso.getInclusao() == true) {
			btnIncluir.setEnabled(true);
		} else {
			btnIncluir.setEnabled(false);
		}
		botoes.add(btnIncluir);
		aces1.espacobotao(botoes);

		btnexcluir = new JButton();
		btnexcluir.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
					int linha = table.getSelectedRow();

					try {

						String nome = "Deseja realmente Desativar o usuario: " + table.getValueAt(linha, 1).toString()
								+ " ?";
						int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, "Desativa ",
								JOptionPane.YES_NO_OPTION);
						if (opcao_escolhida == JOptionPane.YES_OPTION) {
							int conseguiu_excluir = 1;
							userbean.desativa(Long.parseLong(table.getValueAt(linha, 0).toString()));

							repositorylog.adicionaLog(aces1.logalterar,
									"USUARIO DESATIVADO " + " NOME " + table.getValueAt(linha, 2).toString(),
									Long.parseLong(table.getValueAt(linha, 0).toString()), u);

							if (conseguiu_excluir == 1) {
								DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
								tableModel.setNumRows(0);
								carregartabela1();
								table.changeSelection(0, 0, false, false);
							}
						} else {
							return;
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO AO DESLIGAR O USUARIO " + e.getMessage());
						aces1.demologger.error("ERRO AO DESLIGAR O USUARIO " + e.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null, "SELECIONE UM USUARIO !!!!!!!");
				}
			}
		});
		btnexcluir.setIcon(new ImageIcon(listadeusuario.class.getResource("/imagens/desligado.png")));
		btnexcluir.setToolTipText("DESATIVAR USUARIO");
		aces1.butonfundo(btnexcluir);
		if (acesso.getExclusao() == true) {
			btnexcluir.setEnabled(true);
		} else {
			btnexcluir.setEnabled(false);
		}
		botoes.add(btnexcluir);
		aces1.espacobotao(botoes);

		btndireitos = new JButton();
		btndireitos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Acessos(u);
			}
		});
		btndireitos.setIcon(new ImageIcon(listadeusuario.class.getResource("/imagens/acesso.png")));
		aces1.butonfundo(btndireitos);
		btndireitos.setToolTipText("ACESSOS");
		if (acesso.getNivel7() == true) {
			btndireitos.setEnabled(true);
		} else {
			btndireitos.setEnabled(false);
		}
		botoes.add(btndireitos);
		aces1.espacobotao(botoes);

		btnheranca = new JButton();
		btnheranca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				herancausuarios();
			}
		});
		btnheranca.setIcon(new ImageIcon(listadeusuario.class.getResource("/imagens/heranca.png")));
		aces1.butonfundo(btnheranca);
		btnheranca.setToolTipText("HERANÇA DE ACESSOS");
		if (acesso.getNivel7() == true) {
			btnheranca.setEnabled(true);
		} else {
			btnheranca.setEnabled(false);
		}
		botoes.add(btnheranca);
		aces1.espacobotao(botoes);

		pesquisar.requestFocus();
		table.changeSelection(0, 0, false, false);

		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

	public void carregartabela1() throws Exception {

		Usuariobean lista = new Usuariobean();
		List<Usuario> list = (List<Usuario>) lista.getUsuarios();

		for (Usuario user : list) {
			listadeusuario.addRow(new Object[] { user.getId(), user.getLogin(), user.getNome(), user.isAtivo() });

		}

	}

	public void gravaitemusuario(Usuario u, Acesso ap) {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linhaSel = table.getSelectedRow();

			Long idT = Long.parseLong(table.getValueAt(linhaSel, 0).toString());
			Usuario user = new Usuario();

			try {

				user = userbean.procurausuarioid(idT);

				new cadastrousuario(u, user, ap).setVisible(true);
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setNumRows(0);

				carregartabela1();
				table.requestFocus();
				table.changeSelection(0, 0, false, false);

			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "ERRO AO ADCIONAR O USUARIO " + e.getMessage());
				aces1.demologger.error("ERRO AO ADCIONAR O USUARIO " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM USUARIO !!!!!!!!!!");
		}
	}

	public void Acessos(Usuario up) {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linhaSel = table.getSelectedRow();

			Long idT = Long.parseLong(table.getValueAt(linhaSel, 0).toString());
			Usuario user = new Usuario();

			user = userbean.procurausuarioid(idT);

			try {
				new listadeacesso(user, up).setVisible(true);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO AO LISTAR OS ACESSOS " + e.getMessage());
				aces1.demologger.error("ERRO AO LISTAR OS ACESSOS " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM USUARIO !!!!!!!");
		}

	}

	public void herancausuarios() {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linhaSel = table.getSelectedRow();

			Long idT = Long.parseLong(table.getValueAt(linhaSel, 0).toString());
			user = new Usuario();
			Usuario p = new Usuario();

			user = userbean.procurausuarioid(idT);

			try {
				new listausuarioclone(p).setVisible(true);

				if (p.getId() != null) {
					userclone = userbean.procurausuarioid(p.getId());

					EntityManagerUtil.conexao();
					AcessoRepository repositoryAcesso = new AcessoRepository(EntityManagerUtil.manager);

					// deleta acessos do usuario que recebera o clone
					repositoryAcesso.deletaacessos(user);

					// Pega todos os acessos do usuario a ser clonado
					List<Acesso> acessosUsuarioClonado = repositoryAcesso.listausuarioacessos(userclone);

					// Insere os acessos

					for (Acesso acessoClonado : acessosUsuarioClonado) {

						Acesso acesso = new Acesso();
						acesso.setUsuario(user);

						acesso.setRotina(acessoClonado.getRotina());
						acesso.setAcesso(acessoClonado.getAcesso());
						acesso.setInclusao(acessoClonado.getInclusao());
						acesso.setAlteracao(acessoClonado.getAlteracao());
						acesso.setExclusao(acessoClonado.getExclusao());
						acesso.setNivel5(acessoClonado.getNivel5());
						acesso.setNivel6(acessoClonado.getNivel6());
						acesso.setNivel7(acessoClonado.getNivel7());
						acesso.setNivel8(acessoClonado.getNivel8());
						acesso.setModulo(acessoClonado.getModulo());
						repositoryAcesso.grava(acesso);

					}

					userclone = null;
					user = null;

					JOptionPane.showMessageDialog(null, "PERMISSÕES CLONADAS COM SUCESSO !!!!!!!");

				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO AO CLONAR OS ACESSOS " + e.getMessage());
				aces1.demologger.error("ERRO AO CLONAR OS ACESSOS " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM USUARIO !!!!!!!");
		}

	}
}
