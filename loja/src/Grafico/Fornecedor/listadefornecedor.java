package Grafico.Fornecedor;

import Grafico.Cadcli.listadecliente;
import Grafico.geral.Letramaiuscula;
import Grafico.geral.TelaPrincipal;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import beans.CadforBean;
import entidades.Acesso;
import entidades.Cadfor;
import entidades.Usuario;
import filter.EntityManagerUtil;
import repositorios.LogusuRepository;

public class listadefornecedor extends JDialog {
	Font font;
	int idT;
	public JTable table;
	private JToolBar botoes;
	private JPanel contentPane;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listafornecedor = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};

	String rotina = TelaPrincipal.rotinafor;
	ResultSet resultado;
	JTextField pesquisar;
	Cadfor p = new Cadfor();
	CadforBean f = new CadforBean();
	AcessoBean aces1 = new AcessoBean();
	private JButton btnexcluir, btnIncluir;

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public listadefornecedor(final Usuario u) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 692, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setTitle("LISTA DE FORNECEDORES");
		setLocationRelativeTo(null);
		Acesso acesso = aces1.procuraacesso(u, rotina);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setToolTipText("lista de fornecedores");
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		scrollPane_1.setBounds(0, 43, 677, 343);
		setBackground(getBackground());
		setForeground(Color.BLACK);

		contentPane.add(scrollPane_1);

		botoes = new JToolBar();
		botoes.setLocation(0, 385);
		botoes.setSize(675, 60);
		aces1.menubotao(botoes);
		contentPane.add(botoes);

		JLabel lblpesquisar = new JLabel("PESQUISAR");
		lblpesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblpesquisar.setForeground(Color.BLUE);
		lblpesquisar.setBounds(16, 15, 69, 14);
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

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listafornecedor);

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

		table = new JTable(listafornecedor) {
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

					if (table.getValueAt(rowIndex, 5).toString().equals("3")) {
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
					gravaitemfornecedor(u, acesso);
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					gravaitemfornecedor(u, acesso);
				}
			}
		});
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		scrollPane_1.setViewportView(table);

		listafornecedor.addColumn("id");
		listafornecedor.addColumn("CODIGO");
		listafornecedor.addColumn("DESCRIÇÃO");
		listafornecedor.addColumn("CNPJ");
		listafornecedor.addColumn("IE");
		listafornecedor.addColumn("NIVEL");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela();

		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(5).setMinWidth(0);
		table.getColumnModel().getColumn(5).setMaxWidth(0);

		btnIncluir = new JButton();
		if (acesso.getInclusao() == true) {
			btnIncluir.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {

						try {
							Cadfor p = new Cadfor();
							new cadastrofornecedor(p, u, acesso).setVisible(true);

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "ERRO AO INCLUIR O FORNECEDOR " + e1.getMessage());
							aces1.demologger.error("ERRO AO INCLUIR O FORNECEDOR " + e1.getMessage());
						}
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.setNumRows(0);
						try {
							carregartabela();
						} catch (Exception e1) {

							JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR LISTA FORNECEDOR " + e1.getMessage());
							aces1.demologger.error("ERRO AO CARREGAR LISTA FORNECEDOR " + e1.getMessage());
						}
						table.changeSelection(0, 0, false, false);

					}
					if (e.getKeyCode() == KeyEvent.VK_TAB) {
						btnexcluir.requestFocus();
					}
				}
			});
			btnIncluir.addActionListener(new ActionListener() {
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent arg0) {
					try {
						Cadfor p = new Cadfor();
						new cadastrofornecedor(p, u, acesso).setVisible(true);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO AO INCLUIR O FORNECEDOR " + e.getMessage());
						aces1.demologger.error("ERRO AO INCLUIR O FORNECEDOR " + e.getMessage());
					}
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setNumRows(0);
					try {
						carregartabela();
					} catch (Exception e) {

						JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR LISTA FORNECEDOR " + e.getMessage());
						aces1.demologger.error("ERRO AO CARREGAR LISTA FORNECEDOR " + e.getMessage());
					}
					table.requestFocus();
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
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						excluiritem(u);
					}
				}
			});

			btnexcluir.addActionListener(new ActionListener() {
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent arg0) {
					excluiritem(u);
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

	}

	public void carregartabela() throws Exception {

		CadforBean lista = new CadforBean();
		List<Cadfor> list = (List<Cadfor>) lista.getCadfors();

		for (Cadfor forn : list) {
			listafornecedor.addRow(new Object[] { forn.getIDFORNECEDOR(), forn.getCODFOR(), forn.getRAZAO(),
					forn.getCGC(), forn.getIE(), forn.getNIVEL().toString().replace(".0", "") });
		}

	}

	public void gravaitemfornecedor(Usuario u, Acesso acesso) {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());

			Long idT = Long.parseLong(listafornecedor.getValueAt(linhaSel, 0).toString());
			try {
				p = f.procura(idT);

				new cadastrofornecedor(p, u, acesso).setVisible(true);
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setNumRows(0);
				carregartabela();
				table.changeSelection(0, 0, false, false);

			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "ERRO AO ADCIONAR O FORNECEDOR " + e.getMessage());
				aces1.demologger.error("ERRO AO ADCIONAR O FORNECEDOR " + e.getMessage());
			}
		}else {
			JOptionPane.showMessageDialog(null, "Selecione um fornecedor !!!!!!!!!!");
		}
	}

	public void excluiritem(Usuario uc) {
		if (table.getSelectedRow() != -1 && table.getSelectedRowCount() > 0) {
			int linha = table.getSelectedRow();

			try {

				String nome = "Deseja realmente excluir o Fornecedor: " + table.getValueAt(linha, 2).toString() + " ?";
				int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, aces1.logexcluir + " ",
						JOptionPane.YES_NO_OPTION);
				if (opcao_escolhida == JOptionPane.YES_OPTION) {
					f.remove(Long.parseLong(table.getValueAt(linha, 0).toString()));

					repositorylog.adicionaLog(aces1.logexcluir,
							"FORNECEDOR " + " CODIGO " + table.getValueAt(linha, 1).toString() + " DESCRICAO "
									+ table.getValueAt(linha, 2).toString(),
							Long.parseLong(table.getValueAt(linha, 0).toString()), uc);

					listafornecedor.removeRow(linha);
					table.setModel(listafornecedor);

				} else {
					return;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR O FORNECEDOR " + e.getMessage());
				aces1.demologger.error("ERRO AO EXCLUIR O FORNECEDOR " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um nome para excluir");
		}

	}
}
