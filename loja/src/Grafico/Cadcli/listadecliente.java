package Grafico.Cadcli;

import Grafico.Produto.listadeproduto;
import Grafico.geral.Letramaiuscula;
import Grafico.geral.TelaPrincipal;
import Grafico.relatorio.Telarelatpadrao;
import Grafico.relatorio.Telarelatvendas;
import Jm.JMascara;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

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
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import beans.AcessoBean;
import beans.CadcliBean;
import entidades.Acesso;
import entidades.Cadcli;
import entidades.Usuario;
import filter.EntityManagerUtil;
import repositorios.LogusuRepository;

public class listadecliente extends JDialog {
	Font font;
	int idT;
	public JTable table;
	private JPanel contentPane;
	private JToolBar botoes;
	JButton btncrelatclientetotal,btnexcluir,btnIncluir,btnpedidos,btnservicos;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listacliente = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};
	String rotina = TelaPrincipal.rotinac;
	ResultSet resultado;
	JTextField pesquisar;
	Cadcli p = new Cadcli();
	CadcliBean c = new CadcliBean();
	AcessoBean aces1 = new AcessoBean();
	Acesso acesso = new Acesso();

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public listadecliente(final Usuario u) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 689, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("CLIENTES");
		setLocationRelativeTo(null);
		acesso = aces1.procuraacesso(u, rotina);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setToolTipText("LISTA DE CLIENTES");
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		scrollPane_1.setBounds(0, 43, 677, 343);
		setBackground(getBackground());
		setForeground(Color.BLACK);

		contentPane.add(scrollPane_1);

		botoes = new JToolBar();
		botoes.setLocation(0, 385);
		botoes.setSize(673, 60);
		aces1.menubotao(botoes);
		contentPane.add(botoes);

		JLabel lblpesquisar = new JLabel("PESQUISAR");
		lblpesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblpesquisar.setForeground(Color.BLUE);
		lblpesquisar.setBounds(16, 15, 68, 14);
		contentPane.add(lblpesquisar);

		pesquisar = new JTextField();
		pesquisar.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		pesquisar.setForeground(Color.BLUE);
		pesquisar.setDocument(new Letramaiuscula());
		pesquisar.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void keyTyped(KeyEvent arg0) {
				int i = 0;

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listacliente);

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
		pesquisar.setBounds(94, 12, 555, 20);
		contentPane.add(pesquisar);

		table = new JTable(listacliente) {
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

					if (table.getValueAt(rowIndex, 5).equals(true)) {
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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravaitemcliente(u, acesso);
				}

			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					gravaitemcliente(u, acesso);
				}
			}
		});

		scrollPane_1.setViewportView(table);

		listacliente.addColumn("id");
		listacliente.addColumn("CODIGO");
		listacliente.addColumn("FANTASIA");
		listacliente.addColumn("RAZÃO SOCIAL");
		listacliente.addColumn("CNPJ");
		listacliente.addColumn("INATIVO");

		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela();

		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(5).setMinWidth(0);
		table.getColumnModel().getColumn(5).setMaxWidth(0);

		btnIncluir = new JButton();
		btnIncluir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Cadcli p = new Cadcli();
						new cadastrocliente(p, u, acesso).setVisible(true);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "ERRO AO INCLUIR O CLIENTE " + e1.getMessage());
						aces1.demologger.error("ERRO AO INCLUIR O CLIENTE " + e1.getMessage());
					}
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setNumRows(0);
					try {
						carregartabela();
					} catch (Exception e1) {

						JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR LISTA CLIENTE " + e1.getMessage());
						aces1.demologger.error("ERRO AO CARREGAR LISTA CLIENTE " + e1.getMessage());
					}
				}
			}
		});
		if (acesso.getInclusao() == true) {
			btnIncluir.addActionListener(new ActionListener() {
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent arg0) {
					try {
						Cadcli p = new Cadcli();
						new cadastrocliente(p, u, acesso).setVisible(true);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO AO INCLUIR O CLIENTE " + e.getMessage());
						aces1.demologger.error("ERRO AO INCLUIR O CLIENTE " + e.getMessage());
					}
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setNumRows(0);
					try {
						carregartabela();
					} catch (Exception e) {

						JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR LISTA CLIENTE " + e.getMessage());
						aces1.demologger.error("ERRO AO CARREGAR LISTA CLIENTE " + e.getMessage());
					}
				}

			});
		} else {
			btnIncluir.setEnabled(false);
		}
		btnIncluir.setIcon(new ImageIcon(listadecliente.class.getResource("/imagens/incluir.png")));
		btnIncluir.setToolTipText("INCLUIR");
		aces1.butonfundo(btnIncluir);
		botoes.add(btnIncluir);
		aces1.espacobotao(botoes);

		btnexcluir = new JButton();
		if (acesso.getExclusao() == true) {
			btnexcluir.addActionListener(new ActionListener() {
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent arg0) {
					if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
						int linha = table.getSelectedRow();

						try {

							String nome = "Deseja realmente excluir o Cliente: " + table.getValueAt(linha, 2).toString()
									+ " ?";
							int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, aces1.logexcluir + " ",
									JOptionPane.YES_NO_OPTION);
							if (opcao_escolhida == JOptionPane.YES_OPTION) {
								int conseguiu_excluir = 1;
								c.remove(Long.parseLong(table.getValueAt(linha, 0).toString()));

								repositorylog.adicionaLog(aces1.logexcluir,
										"CLIENTE CODIGO " + table.getValueAt(linha, 1).toString() + " DESCRICAO "
												+ table.getValueAt(linha, 2).toString(),
										Long.parseLong(table.getValueAt(linha, 0).toString()), u);

								listacliente.removeRow(linha);
								table.setModel(listacliente);
							} else {
								return;
							}

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR O CLIENTE " + e.getMessage());
							aces1.demologger.error("ERRO AO EXCLUIR O CLIENTE " + e.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(null, "Selecione um nome para excluir");
					}
				}
			});
		} else {
			btnexcluir.setEnabled(false);
		}
		btnexcluir.setToolTipText("EXCLUIR");
		btnexcluir.setIcon(new ImageIcon(listadecliente.class.getResource("/imagens/excluir.png")));
		aces1.butonfundo(btnexcluir);
		botoes.add(btnexcluir);
		aces1.espacobotao(botoes);
		
		btncrelatclientetotal = new JButton();
		btncrelatclientetotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					//new Telarelatpadrao("CLIENTE", "relatclientetotal").setVisible(true);
					new Telarelatpadrao("CLIENTE", "relatclientetotal").telapadrao.setVisible(true);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "ERRO ABRIR TELA RELATORIO DE CLIENTE TOTAL: " + e.getMessage());
					aces1.demologger.error("ERRO ABRIR TELA RELATORIO DE CLIENTE TOTAL: " + e.getMessage());
				}

			}
		});
		btncrelatclientetotal.setToolTipText("RELATORIO TOTAL DE VENDAS POR CLIENTE");
		aces1.butonfundo(btncrelatclientetotal);
		btncrelatclientetotal.setIcon(new ImageIcon(listadecliente.class.getResource("/imagens/relatorio.png")));
		botoes.add(btncrelatclientetotal);
		aces1.espacobotao(botoes);

		btnpedidos = new JButton();
		btnpedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listapedidos();
			}
		});
		btnpedidos.setIcon(new ImageIcon(listadecliente.class.getResource("/imagens/vendas.png")));
		aces1.butonfundo(btnpedidos);
		btnpedidos.setToolTipText("VISUALIZAR PEDIDOS DE VENDAS ANTIGOS");
		if (aces1.mostramodulo("PEDIDOSANTIGO", u) == true) {
			botoes.add(btnpedidos);
			aces1.espacobotao(botoes);
		}
		
		btnservicos = new JButton();
		btnservicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaservico();
			}
		});
		btnservicos.setIcon(new ImageIcon(listadecliente.class.getResource("/imagens/ordemservico.png")));
		aces1.butonfundo(btnservicos);
		btnservicos.setToolTipText("VISUALIZAR ORDEM DE SERVIÇOS ANTIGOS");
		if (aces1.mostramodulo("SERVICOSANTIGO", u) == true) {
			botoes.add(btnservicos);
			aces1.espacobotao(botoes);
		}
		
		

		pesquisar.requestFocus();
		table.changeSelection(0, 0, false, false);

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

	public void carregartabela() throws Exception {

		CadcliBean lista = new CadcliBean();
		List<Cadcli> list = (List<Cadcli>) lista.getCadclis();

		for (Cadcli cli : list) {
			listacliente.addRow(new Object[] { cli.getIdcadcli(), cli.getCODCLI(), cli.getDESCCLI(), cli.getRAZAO(),
					JMascara.GetJmascaraCpfCnpj(cli.getCGC()), cli.getINATIVO() });

		}

	}

	public void gravaitemcliente(Usuario u, Acesso acesso) {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());

			Long idT = Long.parseLong(listacliente.getValueAt(linhaSel, 0).toString());
			try {
				p = c.procura(idT);
				new cadastrocliente(p, u, acesso).setVisible(true);
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setNumRows(0);
				carregartabela();
				table.changeSelection(0, 0, false, false);

			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "ERRO AO ADCIONAR O CLIENTE " + e.getMessage());
				aces1.demologger.error("ERRO AO ADCIONAR O CLIENTE " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM CLIENTE !!!!!!!!!!");
		}
	}

	public void listapedidos() {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());

			Long idT = Long.parseLong(listacliente.getValueAt(linhaSel, 0).toString());
			try {

				new listapedidocporliente(idT).setVisible(true);
				table.requestFocus();
				table.changeSelection(0, 0, false, false);

			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "ERRO LISTAR PEDIDO DE VENDAS POR CLIENTE " + e.getMessage());
				aces1.demologger.error("ERRO LISTAR PEDIDO DE VENDAS POR CLIENTE " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM CLIENTE !!!!!!!!!!");
		}

	}
	
	public void listaservico() {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());

			Long idT = Long.parseLong(listacliente.getValueAt(linhaSel, 0).toString());
			try {

				new listaservicocporliente(idT).setVisible(true);
				table.requestFocus();
				table.changeSelection(0, 0, false, false);

			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "ERRO LISTAR ORDEM DE SERVIÇO POR CLIENTE " + e.getMessage());
				aces1.demologger.error("ERRO LISTAR ORDEM DE SERVIÇO POR CLIENTE " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM CLIENTE !!!!!!!!!!");
		}

	}
}
