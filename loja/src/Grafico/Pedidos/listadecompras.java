package Grafico.Pedidos;

import Grafico.Cadcli.listadecliente;
import Grafico.geral.Letramaiuscula;
import Grafico.geral.TelaPrincipal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import javax.swing.JViewport;
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
import beans.PdentcBean;
import beans.PdentiBean;
import entidades.Acesso;
import entidades.Pdentc;
import entidades.Pdenti;
import entidades.Usuario;
import filter.EntityManagerUtil;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import repositorios.LogusuRepository;

public class listadecompras extends JDialog {

	public JTable table;
	private JPanel contentPane;
	private JToolBar botoes;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listacompras = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};

	String rotina = TelaPrincipal.rotinaco;
	ResultSet resultado;
	JTextField pesquisar;
	Pdentc f = new Pdentc();
	Pdenti I = new Pdenti();
	PdentcBean l = new PdentcBean();
	PdentiBean itens = new PdentiBean();
	int idcomprasc, pedido;
	String descricao;
	AcessoBean aces1 = new AcessoBean();
	Locale local1 = new Locale("pt", "BR");
	DateFormat datestring = new SimpleDateFormat("dd/MM/yyyy", local1);
	private JButton btnimprimir, btnIncluir, btnexcluir;
	
	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public listadecompras(final Usuario u) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 692, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("COMPRAS");
		setLocationRelativeTo(null);
		Acesso acesso = aces1.procuraacesso(u, rotina);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setToolTipText("COMPRAS");
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		scrollPane_1.setBounds(0, 43, 677, 343);
		setBackground(getBackground());
		setForeground(Color.BLACK);

		contentPane.add(scrollPane_1);

		JLabel lblpesquisar = new JLabel("PESQUISAR");
		lblpesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblpesquisar.setForeground(Color.BLUE);
		lblpesquisar.setBounds(13, 15, 70, 14);
		contentPane.add(lblpesquisar);

		pesquisar = new JTextField();
		pesquisar.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		pesquisar.setForeground(Color.BLUE);
		pesquisar.setDocument(new Letramaiuscula());
		pesquisar.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void keyTyped(KeyEvent e) {

				int i = 0;

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listacompras);

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
		pesquisar.setBounds(105, 12, 544, 20);
		contentPane.add(pesquisar);
		pesquisar.setColumns(10);

		botoes = new JToolBar();
		botoes.setLocation(0, 385);
		botoes.setSize(675, 60);
		aces1.menubotao(botoes);
		contentPane.add(botoes);

		table = new JTable(listacompras) {
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
					graitemcompras(u, acesso);
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					graitemcompras(u, acesso);
				}
			}
		});
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setViewportView(table);

		listacompras.addColumn("id");
		listacompras.addColumn("PEDIDO");
		listacompras.addColumn("CODIGO");
		listacompras.addColumn("FORNECEDOR");
		listacompras.addColumn("EMISSAO");
		listacompras.addColumn("ENTREGA");
		listacompras.addColumn("VALORPEDIDO");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela();

		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		btnIncluir = new JButton();
		if (acesso.getInclusao() == true) {
			btnIncluir.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {

					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							Pdentc f = new Pdentc();
							new cadastrocomprasc(f, u, acesso).setVisible(true);

						} catch (Exception e1) {

						}
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.setNumRows(0);
						try {
							carregartabela();

						} catch (Exception e1) {

							JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR A TABELA: " + e1.getStackTrace());
						}
						table.changeSelection(0, 0, false, false);

					}
				}
			});
			btnIncluir.setIcon(new ImageIcon(listadecompras.class.getResource("/imagens/incluir.png")));

			btnIncluir.addActionListener(new ActionListener() {
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent arg0) {
					try {
						Pdentc f = new Pdentc();
						new cadastrocomprasc(f, u, acesso).setVisible(true);

					} catch (Exception e) {

					}
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setNumRows(0);
					try {
						carregartabela();

					} catch (Exception e) {

						JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR A TABELA: " + e.getStackTrace());
					}
					table.changeSelection(0, 0, false, false);
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
			btnexcluir.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_TAB) {
						btnimprimir.requestFocus();
					}

					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						removepedido(u);
					}
				}
			});

			btnexcluir.addActionListener(new ActionListener() {
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent arg0) {
					removepedido(u);
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

		btnimprimir = new JButton();
		btnimprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
					int linha = table.getSelectedRow();
					int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());
					try {
						itens.imprimir(Long.parseLong(table.getValueAt(linha, 0).toString()));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO AO IMPRIMIR PEDIDO DE COMPRAS " + e.getMessage());
						aces1.demologger.error("ERRO AO IMPRIMIR PEDIDO DE COMPRAS " + e.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null, "SELECIONE UM PEDIDO COMPRAS !!!!!!");
				}
				table.changeSelection(0, 0, false, false);
			}
		});
		btnimprimir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
						int linha = table.getSelectedRow();
						int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());
						try {
							itens.imprimir(Long.parseLong(table.getValueAt(linha, 0).toString()));
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "ERRO AO IMPRIMIR PEDIDO DE COMPRAS " + e1.getMessage());
							aces1.demologger.error("ERRO AO IMPRIMIR PEDIDO DE COMPRAS " + e1.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(null, "SELECIONE UM PEDIDO COMPRAS !!!!!!");
					}
					table.changeSelection(0, 0, false, false);

				}
			}
		});
		btnimprimir.setToolTipText("IMPRIMIR PEDIDO TAMANHO A4");
		btnimprimir.setIcon(new ImageIcon(listadecompras.class.getResource("/imagens/A4.png")));
		aces1.butonfundo(btnimprimir);
		botoes.add(btnimprimir);
		aces1.espacobotao(botoes);

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

		PdentcBean lista = new PdentcBean();
		List<Pdentc> list = (List<Pdentc>) lista.getPdentcs();
		
		
		for (Pdentc com : list) {
			listacompras.addRow(new Object[] { com.getIdpdentc(), com.getNumdoc(), com.getForn().getCODFOR(),
					com.getForn().getDESCFOR(), aces1.retornadatastring(com.getEmissao()), aces1.retornadatastring(com.getVencto()),
					aces1.valordinheiro(com.getVrtot()) });

		}

	}

	public void graitemcompras(Usuario u, Acesso acesso) {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {

			int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());

			Long idT = Long.parseLong(listacompras.getValueAt(linhaSel, 0).toString());

			try {
				f = l.procura(idT);
				new cadastrocomprasc(f, u, acesso).setVisible(true);
				if (f.getIdpdentc() != null) {
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setNumRows(0);
					carregartabela();
				}
				table.requestFocus();
				table.changeSelection(linhaSel, 0, false, false);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO AO VISUALIZAR PEDIDO DE COMPRAS " + e.getMessage());
				aces1.demologger.error("ERRO AO VISUALIZAR PEDIDO DE COMPRAS " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um pedido para visualizar !!!!!!!!!");
		}
	}

	public void removepedido(Usuario u) {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linha = table.getSelectedRow();
			try {

				String nome = "DESEJA REALMENTE EXCLUIR O PEDIDO " + table.getValueAt(linha, 1).toString() + " ?";
				int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, aces1.logexcluir + " ", JOptionPane.YES_NO_OPTION);
				if (opcao_escolhida == JOptionPane.YES_OPTION) {
					l.remove(Long.parseLong(table.getValueAt(linha, 0).toString()), u);
					
					repositorylog.adicionaLog(aces1.logexcluir,
							"PEDIDOCOMPRAS NUMERO " + table.getValueAt(linha, 1).toString() + " FORNECEDOR "
									+ table.getValueAt(linha, 2).toString().trim() + " " + table.getValueAt(linha, 3).toString().trim(),
							Long.parseLong(table.getValueAt(linha, 0).toString()), u);
					
					
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setNumRows(0);
					try {
						carregartabela();

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR A LISTA PEDIDO DE COMPRAS AO EXCLUIR PEDIDO " + e1.getMessage());
						aces1.demologger.error("ERRO AO CARREGAR A LISTA PEDIDO DE COMPRAS AO EXCLUIR PEDIDO " + e1.getMessage());
					}
					table.requestFocus();
					table.changeSelection(0, 0, false, false);
				} else {
					return;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO DELETAR PEDIDO DE COMPRAS" + e.getMessage());
				aces1.demologger.error("ERRO DELETAR PEDIDO DE COMPRAS" + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um pedido para excluir");
		}
	}

}
