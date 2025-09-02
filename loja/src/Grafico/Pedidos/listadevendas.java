package Grafico.Pedidos;

import Grafico.Cadcli.listadecliente;
import Grafico.geral.Letramaiuscula;
import Grafico.geral.TelaPrincipal;
import Lazy.GenericLazyDataTable;
import Lazy.GenericService;
import Lazy.PdsaicService;

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
import java.io.File;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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

import Configuracao.RelatorioPadraoBean;
import beans.AcessoBean;
import beans.CadcaminBean;
import beans.PdsaicBean;
import beans.PdsaiiBean;
import entidades.Acesso;
import entidades.Cadcamin;
import entidades.Cademp;
import entidades.Pdsaic;
import entidades.Pdsaii;
import entidades.Usuario;
import filter.EntityManagerUtil;
import filter.EnviaEmail;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import repositorios.LogusuRepository;

public class listadevendas extends JDialog {

	public JTable table;
	private JPanel contentPane;
	private JToolBar botoes;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listavendas = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};
	private JButton btnimprimir, btnIncluir, btnexcluir, btnimprimirA4, btnpedidoemail;
	String rotina = TelaPrincipal.rotinaven;
	ResultSet resultado;
	JTextField pesquisar;

	Pdsaic f = new Pdsaic();
	Pdsaii I = new Pdsaii();
	PdsaicBean l = new PdsaicBean();
	PdsaiiBean itens = new PdsaiiBean();
	AcessoBean aces1 = new AcessoBean();
	CadcaminBean caminbean = new CadcaminBean();
	Cadcamin caminho = new Cadcamin();
	Acesso acesso = new Acesso();

	Locale local1 = new Locale("pt", "BR");
	DateFormat datestring = new SimpleDateFormat("dd/MM/yyyy", local1);

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public listadevendas(final Usuario u) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 692, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("LISTA PEDIDO DE VENDAS");
		setLocationRelativeTo(null);
		acesso = aces1.procuraacesso(u, rotina);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setToolTipText("VENDAS");
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		scrollPane_1.setBounds(0, 43, 677, 343);
		setBackground(getBackground());
		setForeground(Color.BLACK);

		contentPane.add(scrollPane_1);

		JLabel lblpesquisar = new JLabel("PESQUISAR");
		lblpesquisar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpesquisar.setForeground(Color.BLUE);
		lblpesquisar.setBounds(12, 15, 80, 14);
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

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listavendas);

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

		botoes = new JToolBar();
		botoes.setLocation(0, 385);
		botoes.setSize(675, 60);
		aces1.menubotao(botoes);
		contentPane.add(botoes);

		table = new JTable(listavendas) {
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
					visualizapedidovenda(u,acesso);
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					visualizapedidovenda(u,acesso);
				}
			}
		});
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		table.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(table);

		listavendas.addColumn("id");
		listavendas.addColumn("PEDIDO");
		listavendas.addColumn("CODIGO");
		listavendas.addColumn("CLIENTE");
		listavendas.addColumn("EMISSAO");
		listavendas.addColumn("PAGAMENTO");
		listavendas.addColumn("VALORPEDIDO");
		

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
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
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
							Pdsaic f = new Pdsaic();
							new cadastrovendasc(f, u,acesso).setVisible(true);

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"Erro ao abrir tela de incluir novo pedido " + e1.getMessage());
							aces1.demologger.error("Erro ao abrir tela de incluir novo pedido " + e1.getMessage());
						}
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.setNumRows(0);
						try {
							carregartabela();

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR A LISTA PEDIDO DE VENDA AO INCLUIR " + e1.getMessage());
							aces1.demologger.error("ERRO AO CARREGAR A LISTA PEDIDO DE VENDA AO INCLUIR " + e1.getMessage());
						}
						table.changeSelection(0, 0, false, false);
						pesquisar.requestFocus();

					}
				}
			});
			btnIncluir.setIcon(new ImageIcon(listadevendas.class.getResource("/imagens/incluir.png")));
			btnIncluir.addActionListener(new ActionListener() {
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent arg0) {
					try {
						Pdsaic f = new Pdsaic();
						new cadastrovendasc(f, u,acesso).setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"Erro ao abrir tela de incluir novo pedido " + e.getMessage());
						aces1.demologger.error("Erro ao abrir tela de incluir novo pedido " + e.getMessage());
					}
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setNumRows(0);
					try {
						carregartabela();

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR A LISTA PEDIDO DE VENDA AO INCLUIR " + e.getMessage());
						aces1.demologger.error("ERRO AO CARREGAR A LISTA PEDIDO DE VENDA AO INCLUIR " + e.getMessage());
					}
					table.changeSelection(0, 0, false, false);
					pesquisar.requestFocus();
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
				imprimirpedidozebra();
			}
		});
		btnimprimir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					imprimirpedidozebra();
				}
			}
		});
		btnimprimir.setToolTipText("IMPRIMIR PEDIDO TAMANHO ZEBRA");
		btnimprimir.setIcon(new ImageIcon(listadevendas.class.getResource("/imagens/imprimir.png")));
		aces1.butonfundo(btnimprimir);
		botoes.add(btnimprimir);
		aces1.espacobotao(botoes);

		btnimprimirA4 = new JButton();
		btnimprimirA4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					imprimirpedidoA4();
				}
			}
		});
		btnimprimirA4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				imprimirpedidoA4();
			}
		});
		btnimprimirA4.setIcon(new ImageIcon(listadevendas.class.getResource("/imagens/A4.png")));
		btnimprimirA4.setToolTipText("IMPRIMIR PEDIDO TAMANHO A4");
		aces1.butonfundo(btnimprimirA4);
		botoes.add(btnimprimirA4);
		aces1.espacobotao(botoes);

		btnpedidoemail = new JButton();
		if (aces1.mostramodulo("EMAILPEDIDOVENDA", u) == true) {
			if (acesso.getNivel5() == true) {
				btnpedidoemail.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						try {
							enviarpedidovenda();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "ERRO AO IMPRIMIR PEDIDO DE VENDA A4 " + e.getMessage());
							aces1.demologger.error("ERRO AO IMPRIMIR PEDIDO DE VENDA A4 " + e.getMessage());
						}
					}
				});
			} else {
				btnpedidoemail.setEnabled(false);
			}
			btnpedidoemail.setToolTipText("ENVIAR PEDIDO EMAIL");
			btnpedidoemail.setIcon(new ImageIcon(listadevendas.class.getResource("/imagens/email.png")));
			aces1.butonfundo(btnpedidoemail);
			botoes.add(btnpedidoemail);
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

		PdsaicBean lista = new PdsaicBean();
		List<Pdsaic> list = (List<Pdsaic>) lista.getPdsaics();
		
		for (Pdsaic com : list) {

			listavendas.addRow(new Object[] { com.getIdpdsaic(), com.getNumdoc(), com.getPedido(),
					com.getContato(), aces1.retornadatastring(com.getEmissao()), com.getFormpagto(),
					aces1.valordinheiro(com.getVrtot()) });

		}

	}

	public void visualizapedidovenda(Usuario u,Acesso ac) {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());

			Long idT = Long.parseLong(listavendas.getValueAt(linhaSel, 0).toString());

			try {
				f = l.procura(idT);

				new cadastrovendasc(f, u,ac).setVisible(true);
				if (f.getIdpdsaic() != null) {
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setNumRows(0);
					carregartabela();
				}
				table.requestFocus();
				table.changeSelection(linhaSel, 0, false, false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO VISUALIZAR PEDIDO DE VENDA " + e.getMessage());
				aces1.demologger.error("ERRO VISUALIZAR PEDIDO DE VENDA " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM PEDIDO PARA VISUALIZAR !!!!!!!!!");
		}
	}

	public void removepedido(Usuario u1) {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linha = table.getSelectedRow();
			try {

				String nome = "Deseja realmente excluir o pedido: " + table.getValueAt(linha, 1).toString() + " ?";
				int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, aces1.logexcluir + " ", JOptionPane.YES_NO_OPTION);
				if (opcao_escolhida == JOptionPane.YES_OPTION) {
					l.remove(Long.parseLong(table.getValueAt(linha, 0).toString()), u1);
					
					repositorylog.adicionaLog(aces1.logexcluir,
							"PEDIDOVENDAS NUMERO " + table.getValueAt(linha, 1).toString() + " CLIENTE "
									+ table.getValueAt(linha, 2).toString().trim() + " " + table.getValueAt(linha, 3).toString().trim(),
							Long.parseLong(table.getValueAt(linha, 0).toString()), u1);
					
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setNumRows(0);

					try {
						carregartabela();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,
								"ERRO AO CARREGAR LISTA AO EXCLUIR PEDIDO DE VENDA " + e1.getMessage());
						aces1.demologger.error("ERRO AO CARREGAR LISTA AO EXCLUIR PEDIDO DE VENDA " + e1.getMessage());
					}
					table.changeSelection(0, 0, false, false);
				} else {
					return;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO DELETAR PEDIDO DE VENDA " + e.getMessage());
				aces1.demologger.error("ERRO DELETAR PEDIDO DE VENDA " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM PEDIDO PARA EXCLUIR !!!!!!!");
		}
	}

	public void enviarpedidovenda() throws Exception {

		Cademp empresa = new Cademp();
		EnviaEmail mail = new EnviaEmail();
		Pdsaic pedidoimp = new Pdsaic();
		PdsaicBean pedidobean = new PdsaicBean();

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linha = table.getSelectedRow();
			try {

				pedidoimp = pedidobean.procura(Long.parseLong(table.getValueAt(linha, 0).toString()));

				DecimalFormat df = new DecimalFormat("###.#");

				String jrmlx = "pedvendasA4";
				RelatorioPadraoBean relat = new RelatorioPadraoBean();
				caminho = caminbean.caminhofixo();

				empresa = aces1.empresabanco();

				String nomearquivo = pedidoimp.getNumdoc().toString();
				Map<String, Object> parametros = new HashMap<>();
				parametros.put("id", pedidoimp.getIdpdsaic());
				parametros.put("logo", aces1.retornalogorelat());
				parametros.put("razao", empresa.getRazao());
				parametros.put("enderempresa", empresa.getEnder() + "," + empresa.getNum() + " - " + empresa.getBairro()
						+ " - " + empresa.getCidade());
				parametros.put("foneempresa", empresa.getFone());
				parametros.put("data", aces1.datastring());
				parametros.put("hora", aces1.hora());
				parametros.put("enderempresa", empresa.getEnder() + ", " + empresa.getNum() + " - "
						+ empresa.getBairro() + " - " + empresa.getCidade() + " - " + empresa.getEstado());
				parametros.put("decunitario", TelaPrincipal.decunit);
				parametros.put("decquantidade", TelaPrincipal.decqtde);

				relat.PDFSEMVISUALIZAR(parametros, jrmlx, nomearquivo, "\\pdf\\");
				File arquivo = new File("\\\\" + caminho.getCaminho() + "\\pdf\\" + nomearquivo + ".pdf");

				if (empresa.getEmail() == null || empresa.getEmail().isEmpty() || empresa.getPorta() == null
						|| empresa.getPorta().isEmpty()) {

					JOptionPane.showMessageDialog(null, "EMPRESA SEM E-MAIL DE ENVIO CONFIGURADO !!!!!");

				} else {
					if (pedidoimp.getCliente().getEMAIL() != null && !pedidoimp.getCliente().getEMAIL().isEmpty()) {

						if (mail.emailpedidovenda(empresa.getFantasia().trim(), empresa.getSmtp().trim(),
								empresa.getPorta().trim(), empresa.getSenha().trim(), empresa.getEmail().trim(), false,
								"\\\\" + caminho.getCaminho() + "\\pdf\\" + nomearquivo + ".pdf",
								pedidoimp.getNumdoc().trim(), pedidoimp.getCliente().getRAZAO().trim(),
								pedidoimp.getCliente().getEMAIL()) == true) {

							JOptionPane.showMessageDialog(null, "PEDIDO ENVIADO COM SUCESSO POR E-MAIL !!!!!");

						} else {
							JOptionPane.showMessageDialog(null, "ERRO ENVIAR PEDIDO DE VENDA !!!!!");
							aces1.demologger.error("ERRO ENVIAR PEDIDO DE VENDA ");

						}

					} else {
						JOptionPane.showMessageDialog(null, "CLIENTE SEM E-MAIL CADASTRADO !!!!!");

					}
				}
				arquivo.delete();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO ENVIAR PEDIDO DE VENDA POR EMAIL " + e.getMessage());
				aces1.demologger.error("ERRO ENVIAR PEDIDO DE VENDA POR EMAIL " + e.getMessage());
			}

		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM PEDIDO DE VENDA PARA ENVIAR POR EMAIL !!!!!!");
		}

	}

	public void imprimirpedidozebra() {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linha = table.getSelectedRow();
			int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());
			try {
				itens.imprimir(Long.parseLong(table.getValueAt(linha, 0).toString()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO AO IMPRIMIR PEDIDO DE VENDA ZEBRA " + e.getMessage());
				aces1.demologger.error("ERRO AO IMPRIMIR PEDIDO DE VENDA ZEBRA " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM PEDIDO DE VENDA !!!!!!");
		}
		table.changeSelection(0, 0, false, false);

	}

	public void imprimirpedidoA4() {
		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linha = table.getSelectedRow();
			int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());
			try {
				itens.imprimirA4(Long.parseLong(table.getValueAt(linha, 0).toString()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO AO IMPRIMIR PEDIDO DE VENDA A4 " + e.getMessage());
				aces1.demologger.error("ERRO AO IMPRIMIR PEDIDO DE VENDA A4 " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM PEDIDO DE VENDA !!!!!!");
		}
		table.changeSelection(0, 0, false, false);
	}
}
