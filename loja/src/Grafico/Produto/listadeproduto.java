package Grafico.Produto;

import Grafico.Pedidos.cadastrocomprasc;
import Grafico.Pedidos.listaultimassaidaspedidos;
import Grafico.geral.Letramaiuscula;
import Grafico.geral.TelaPrincipal;
import Grafico.relatorio.Telarelatcompras;
import Grafico.relatorio.Telarelatvendas;

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
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.BasicConfigurator;

import entidades.Acesso;
import entidades.Cadpro;
import entidades.CriaExcel;
import entidades.Usuario;
import filter.EntityManagerUtil;
import repositorios.LogusuRepository;
import beans.AcessoBean;
import beans.CadproBean;

public class listadeproduto extends JDialog {

	public JTable table;
	private JPanel contentPane;
	private JToolBar botoes;
	private JButton btnexcluir, btnIncluir, btnhistoricoentradas, btnhistoricosaidas, btnfoto;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listaproduto = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};

	String rotina = TelaPrincipal.rotinap;
	ResultSet resultado;
	JTextField pesquisar;
	Cadpro p = new Cadpro();
	CadproBean l = new CadproBean();
	AcessoBean aces1 = new AcessoBean();
	String caminhof = "";

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public listadeproduto(final Usuario u) throws Exception {

		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 693, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setTitle("PRODUTOS");
		setLocationRelativeTo(null);
		Acesso acesso = aces1.procuraacesso(u, rotina);
		caminhof = aces1.caminhoireport();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setToolTipText("LISTA DE PRODUTO");
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		scrollPane_1.setBounds(1, 43, 677, 343);
		setForeground(Color.BLACK);

		contentPane.add(scrollPane_1);

		botoes = new JToolBar();
		botoes.setLocation(0, 385);
		botoes.setSize(677, 60);
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

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listaproduto);

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
		pesquisar.setColumns(10);

		table = new JTable(listaproduto) {
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

					if (table.getValueAt(rowIndex, 6).equals(true)) {
						c.setForeground(new Color(255, 0, 0));
					}

					if (Integer.parseInt(table.getValueAt(rowIndex, 4).toString()) < Integer
							.parseInt(table.getValueAt(rowIndex, 7).toString())) {
						c.setForeground(new Color(255, 127, 0));
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
					gravaitemproduto(u, acesso);
				}

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnIncluir.requestFocus();
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					gravaitemproduto(u, acesso);
				}
			}
		});

		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		scrollPane_1.setViewportView(table);

		listaproduto.addColumn("ID");
		listaproduto.addColumn("PRODUTO");
		listaproduto.addColumn("DESCRIÇÃO");
		listaproduto.addColumn("VRVENDA");
		listaproduto.addColumn("QTATUAL");
		listaproduto.addColumn("UN");
		listaproduto.addColumn("Ativo");
		listaproduto.addColumn("ECONOMICO");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela();

		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(6).setMinWidth(0);
		table.getColumnModel().getColumn(6).setMaxWidth(0);
		table.getColumnModel().getColumn(7).setMinWidth(0);
		table.getColumnModel().getColumn(7).setMaxWidth(0);

		btnIncluir = new JButton("");
		btnIncluir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Cadpro c = new Cadpro();

						new cadastroproduto(c, u, acesso).setVisible(true);

					} catch (Exception e1) {
						Logger.getLogger(listadeproduto.class.getName()).log(Level.SEVERE, null, e1.getMessage());
					}
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setNumRows(0);
					try {
						carregartabela();
						table.changeSelection(0, 0, false, false);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "ERRO CARREGA TABELA DE PRODUTO: " + e1.getMessage());
						Logger.getLogger(listadeproduto.class.getName()).log(Level.SEVERE, null, e1.getMessage());
					}

				}

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnexcluir.requestFocus();
				}
			}
		});
		if (acesso.getInclusao() == true) {

			btnIncluir.addActionListener(new ActionListener() {
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent arg0) {
					try {
						Cadpro c = new Cadpro();

						new cadastroproduto(c, u, acesso).setVisible(true);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"ERRO ABRIR TELA DE CADASTRO DE PRODUTO: " + e.getMessage());
						aces1.demologger.error("ERRO ABRIR TELA DE CADASTRO DE PRODUTO: " + e.getMessage());
					}
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setNumRows(0);
					try {
						carregartabela();
						table.changeSelection(0, 0, false, false);
					} catch (Exception e) {

						JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR LISTA DE PRODUTO " + e.getMessage());
						aces1.demologger.error("ERRO AO CARREGAR LISTA DE PRODUTO " + e.getMessage());
					}
				}

			});
		} else {
			btnIncluir.setEnabled(false);
		}
		btnIncluir.setIcon(new ImageIcon(listadeproduto.class.getResource("/imagens/incluir.png")));
		btnIncluir.setToolTipText("INCLUIR");
		aces1.butonfundo(btnIncluir);
		botoes.add(btnIncluir);
		aces1.espacobotao(botoes);

		btnexcluir = new JButton("");
		btnexcluir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					excluirproduto(u);
				}

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnhistoricoentradas.requestFocus();
				}
			}
		});
		if (acesso.getExclusao() == true) {
			btnexcluir.addActionListener(new ActionListener() {
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent arg0) {
					excluirproduto(u);
				}
			});
		} else {
			btnexcluir.setEnabled(false);
		}
		btnexcluir.setToolTipText("EXCLUIR");
		btnexcluir.setIcon(new ImageIcon(listadeproduto.class.getResource("/imagens/excluir.png")));
		aces1.butonfundo(btnexcluir);
		botoes.add(btnexcluir);
		aces1.espacobotao(botoes);

		btnhistoricoentradas = new JButton("");
		btnhistoricoentradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
					int linha = table.getSelectedRow();
					try {

						new listaultimasentradasproduto(u, table.getValueAt(linha, 1).toString()).setVisible(true);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,
								"ERRO LISTAR HISTORICO ENTRADA DE PRODUTO: " + e1.getMessage());
						aces1.demologger.error("ERRO LISTAR HISTORICO ENTRADA DE PRODUTO: " + e1.getMessage());
					}
				}

			}
		});
		btnhistoricoentradas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
						int linha = table.getSelectedRow();
						try {

							new listaultimasentradasproduto(u, table.getValueAt(linha, 1).toString()).setVisible(true);

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"ERRO LISTAR HISTORICO ENTRADA DE PRODUTO: " + e1.getMessage());
							aces1.demologger.error("ERRO LISTAR HISTORICO ENTRADA DE PRODUTO: " + e1.getMessage());
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "SELECIONE UM PRODUTO !!!!!!!!");
				}
			}
		});
		btnhistoricoentradas.setToolTipText("ULTIMAS ENTRADAS");
		btnhistoricoentradas.setIcon(new ImageIcon(listadeproduto.class.getResource("/imagens/ultimas entradas.png")));
		aces1.butonfundo(btnhistoricoentradas);
		botoes.add(btnhistoricoentradas);
		aces1.espacobotao(botoes);

		btnhistoricosaidas = new JButton();
		btnhistoricosaidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
					int linha = table.getSelectedRow();
					try {

						new listaultimassaidaspedidos(u, table.getValueAt(linha, 1).toString()).setVisible(true);
						;

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,
								"ERRO LISTAR HISTORICO SAIDA DE PRODUTO: " + e1.getMessage());
						aces1.demologger.error("ERRO LISTAR HISTORICO SAIDA DE PRODUTO: " + e1.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null, "SELECIONE UM PRODUTO !!!!!!!!");
				}

			}
		});
		btnhistoricosaidas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (table.getSelectedRow() != -1) {
						int linha = table.getSelectedRow();
						try {

							new listaultimassaidaspedidos(u, table.getValueAt(linha, 1).toString()).setVisible(true);

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"ERRO LISTAR HISTORICO SAIDA DE PRODUTO: " + e1.getMessage());
							aces1.demologger.error("ERRO LISTAR HISTORICO SAIDA DE PRODUTO: " + e1.getMessage());
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "SELECIONE UM PRODUTO !!!!!!!!");
				}
			}
		});
		btnhistoricosaidas.setToolTipText("ULTIMAS SAIDAS");
		btnhistoricosaidas.setIcon(new ImageIcon(listadeproduto.class.getResource("/imagens/ultimas saidas.png")));
		aces1.butonfundo(btnhistoricosaidas);
		botoes.add(btnhistoricosaidas);
		aces1.espacobotao(botoes);

		JButton btnrelatvenda = new JButton("");
		btnrelatvenda.setIcon(new ImageIcon(listadeproduto.class.getResource("/imagens/relatorio.png")));
		btnrelatvenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					new Telarelatvendas().setVisible(true);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "ERRO ABRIR TELA RELATORIO DE VENDAS: " + e.getMessage());
					aces1.demologger.error("ERRO ABRIR TELA RELATORIO DE VENDAS: " + e.getMessage());
				}

			}
		});
		btnrelatvenda.setToolTipText("RELATORIO DE VENDAS");
		aces1.butonfundo(btnrelatvenda);
		botoes.add(btnrelatvenda);
		aces1.espacobotao(botoes);

		JButton btnrelatcompra = new JButton("");
		btnrelatcompra.setIcon(new ImageIcon(listadeproduto.class.getResource("/imagens/relatcompra.png")));
		btnrelatcompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					new Telarelatcompras().setVisible(true);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "ERRO ABRIR TELA RELATORIO DE COMPRAS: " + e.getMessage());
					aces1.demologger.error("ERRO ABRIR TELA RELATORIO DE COMPRAS: " + e.getMessage());
				}

			}
		});
		btnrelatcompra.setToolTipText("RELATORIO DE COMPRAS");
		aces1.butonfundo(btnrelatcompra);
		botoes.add(btnrelatcompra);
		aces1.espacobotao(botoes);

		btnfoto = new JButton("");
		btnfoto.setIcon(new ImageIcon(listadeproduto.class.getResource("/imagens/foto.png")));
		btnfoto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					mostrafoto();
				}
			}
		});
		btnfoto.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				mostrafoto();
			}

		});
		btnfoto.setToolTipText("FOTO DO PRODUTO");
		btnfoto.setBounds(398, 458, 55, 46);
		aces1.butonfundo(btnfoto);
		botoes.add(btnfoto);
		aces1.espacobotao(botoes);

		pesquisar.requestFocus();
		table.changeSelection(0, 0, false, false);

		// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// l.fechaconexao();
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// l.fechaconexao();
				dispose();
			}
		});

	}

	public List<Cadpro> carregartabela() throws Exception {

		CadproBean lista = new CadproBean();
		List<Cadpro> list = (List<Cadpro>) lista.getCodpros();
		DecimalFormat format = new DecimalFormat("##");
		Locale BRAZIL = new Locale("pt", "BR");
		DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
		DecimalFormat moeda = new DecimalFormat("###,###,##0.00", REAL);

		for (Cadpro prod : list) {
			listaproduto.addRow(new Object[] { prod.getIdcadpro(), prod.getCODPRO(), prod.getDESCPRO(),
					moeda.format(prod.getVRVENDA()), format.format(prod.getQTATUAL()), prod.getUN(), prod.getATIVO(),
					format.format(prod.getECONOMICO())

			});

		}

		return list;

	}

	public void gravaitemproduto(Usuario u, Acesso acesso) {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());

			Long idT = Long.parseLong(listaproduto.getValueAt(linhaSel, 0).toString());
			try {
				p = new Cadpro();
				p = l.procura(idT);

				new cadastroproduto(p, u, acesso).setVisible(true);
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				listaproduto.setNumRows(0);
				carregartabela();
				table.changeSelection(0, 0, false, false);

			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "ERRO AO GRAVAR O PRODUTO " + e.getMessage());
				aces1.demologger.error("ERRO AO GRAVAR O PRODUTO " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM PRODUTO !!!!!!!");
		}
	}

	public void excluirproduto(Usuario up) {
		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linha = table.getSelectedRow();
			try {

				String nome = "Deseja realmente excluir o produto: " + table.getValueAt(linha, 1).toString() + " ?";
				int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, "Exclusão ", JOptionPane.YES_NO_OPTION);
				if (opcao_escolhida == JOptionPane.YES_OPTION) {
					int conseguiu_excluir = 1;
					l.remove(Long.parseLong(table.getValueAt(linha, 0).toString()));
					repositorylog.adicionaLog(aces1.logexcluir,
							"PRODUTO " + " CODIGO " + table.getValueAt(linha, 1).toString(),
							Long.parseLong(table.getValueAt(linha, 0).toString()), up);

					if (conseguiu_excluir == 1) {
						listaproduto.removeRow(linha);
						table.setModel(listaproduto);
						pesquisar.setText("");
						listaproduto.setNumRows(0);
						TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listaproduto);

						TableModel model = (TableModel) table.getModel();

						sorter = new TableRowSorter<TableModel>(model);
						table.setRowSorter(sorter);
						sorter.setRowFilter(null);
						carregartabela();
						table.changeSelection(0, 0, false, false);
					}
				} else {
					return;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR O PRODUTO " + e.getMessage());
				aces1.demologger.error("ERRO AO EXCLUIR O PRODUTO " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM PRODUTO !!!!!!!!");
		}

	}

	public void mostrafoto() {

		try {
			if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
				int linha = table.getSelectedRow();
				Cadpro cfoto = new Cadpro();
				cfoto = l.buscaproduto(table.getValueAt(linha, 1).toString());
				new mostrafoto(cfoto, caminhof).setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "SELECIONE UM PRODUTO !!!!!");
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "ERRO AO MOSTRAR FOTO " + e1.getMessage());
			aces1.demologger.error("ERRO AO MOSTRAR FOTO " + e1.getMessage());
		}
	}
}
