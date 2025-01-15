package Grafico.Pedidos;

import javax.persistence.Query;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
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

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JTable;

import repositorios.CadcliRepository;
import repositorios.LogusuRepository;
import entidades.Acesso;
import entidades.Cadcli;
import entidades.Cadpro;
import entidades.Pdsaic;
import entidades.Pdsaii;
import entidades.Usuario;
import filter.EntityManagerUtil;
import Grafico.Cadcli.listadecliente;
import Grafico.Cadcli.listaprocuracliente;
import Grafico.geral.MonetarioDocument;
import beans.AcessoBean;
import beans.CadcliBean;
import beans.PdsaicBean;
import beans.PdsaiiBean;

import java.sql.Statement;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import Grafico.geral.TTextAreaDocument;

@SuppressWarnings("serial")
public class cadastrovendasc extends JDialog {
	private JPanel contentPane;
	private JTable table;
	private JTextField pedido, emissao, desccli, cliente, entrega, qttotal, vrmercadoria, contato, vrtotal, vendedor,
			txentrega;

	JComboBox formapagamento = new JComboBox();

	private JTextArea obs;

	JButton btncadastrar;

	PdsaicBean c1 = new PdsaicBean();
	Pdsaic c = new Pdsaic();

	Cadcli f = new Cadcli();
	Cadcli cadclip = new Cadcli();
	CadcliBean f1 = new CadcliBean();

	Pdsaii ci = new Pdsaii();
	PdsaiiBean cdao = new PdsaiiBean();
	
	AcessoBean aces1 = new AcessoBean();
	private Long idp;
	List<Pdsaii> pdsaiiremove = new ArrayList<Pdsaii>();
	
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	DecimalFormat decimal = new DecimalFormat("0.00");
	DecimalFormat decimalqtde = new DecimalFormat("0");

	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listapdsaii = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	@SuppressWarnings("unchecked")
	public cadastrovendasc(final Pdsaic p, Usuario u,Acesso acesso) throws Exception {
		idp = p.getIdpdsaic();
		cadclip = p.getCliente();
		setTitle("PEDIDO DE VENDA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.imagemicone()));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(10, 5, 747, 547);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		scrollPane_1.setBounds(0, 45, 732, 209);
		setBackground(new java.awt.Color(192, 192, 255));
		setForeground(Color.BLACK);
		contentPane.add(scrollPane_1);

		JLabel lblpedido = new JLabel("PEDIDO");
		aces1.padraojlabel(lblpedido);
		lblpedido.setBounds(36, 5, 55, 14);
		contentPane.add(lblpedido);

		pedido = new JTextField();
		aces1.bloqueado(pedido);
		pedido.setBounds(10, 19, 100, 20);
		contentPane.add(pedido);

		JLabel lblcliente = new JLabel("CLIENTE");
		aces1.padraojlabel(lblcliente);
		lblcliente.setBounds(135, 5, 55, 14);
		contentPane.add(lblcliente);

		cliente = new JTextField();
		cliente.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		cliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

					try {
						if (cliente.getText() == null || cliente.getText().trim().isEmpty()) {
							new listaprocuracliente(f).setVisible(true);
						} else {
							validacliente();
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO AO VALIDAR O CLIENTE " + e.getMessage());
						aces1.demologger.error("ERRO AO VALIDAR O CLIENTE " + e.getMessage());
					}
					if (f.getIdcadcli() != 0) {
						cadclip = f1.procura(f.getIdcadcli());
						cliente.setText(cadclip.getCODCLI());
						desccli.setText(cadclip.getDESCCLI());
						aces1.moedabanco(cadclip.getTxent(), txentrega);
						aces1.bloqueado(emissao);
						try {
							emissao.setText(aces1.retornadatastring(aces1.dataatual()));
						} catch (ParseException e) {
						}
						aces1.liberado(entrega);
						entrega.setText(emissao.getText());
						entrega.requestFocus();
						aces1.bloqueado(cliente);
						aces1.bloqueado(desccli);
						entrega.setCaretPosition(0);// posicao zero do cursor
					} else {
						cliente.requestFocus();
					}
				}

				if (evt.getKeyCode() == KeyEvent.VK_TAB) {
					if (cliente.getText() == null || cliente.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite um valor!!!");
						cliente.requestFocus();
					} else {
						validacliente();
					}
				}
			}
		});
		cliente.setBounds(115, 19, 100, 20);
		aces1.liberado(cliente);
		contentPane.add(cliente);

		desccli = new JTextField();
		aces1.bloqueado(desccli);
		desccli.setBounds(220, 19, 296, 20);
		contentPane.add(desccli);

		JLabel lblemissao = new JLabel("EMISSÃO");
		aces1.padraojlabel(lblemissao);
		lblemissao.setBounds(545, 5, 60, 14);
		contentPane.add(lblemissao);

		javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
		emissao = new javax.swing.JFormattedTextField(data);
		emissao.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		emissao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
					aces1.validadata(true, emissao, entrega);
				}
			}
		});
		aces1.bloqueado(emissao);
		aces1.numeros(emissao);
		emissao.setBounds(525, 19, 97, 20);
		contentPane.add(emissao);

		JLabel lblentrega = new JLabel("ENTREGA");
		aces1.padraojlabel(lblentrega);
		lblentrega.setBounds(650, 5, 62, 14);
		contentPane.add(lblentrega);

		entrega = new JFormattedTextField((setNumero("##/##/####")));
		entrega.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		entrega.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					df.setLenient(false);
					try {
						Date data1 = df.parse(emissao.getText());
						Date data2 = df.parse(entrega.getText());
						int compara = data1.compareTo(data2);
						if (compara == 0 || compara == -1) {
							df.parse(entrega.getText());
							table.requestFocus();
							table.changeSelection(0, 0, false, false);
						} else {
							JOptionPane.showMessageDialog(null, "Data de Entega está menor que a Data de Emissão");
							entrega.requestFocus();
						}
					} catch (ParseException ex) {
						JOptionPane.showMessageDialog(null, "Data Inválida");
					}
				}
			}
		});
		aces1.bloqueado(entrega);
		aces1.numeros(entrega);
		entrega.setBounds(630, 19, 97, 20);
		contentPane.add(entrega);

		table = new JTable(listapdsaii) {
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
				Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
				c.setFont(aces1.tipoletra());
				aces1.tipocomponete(c);
				if (isCellSelected(rowIndex, vColIndex)) {
					c.setBackground(aces1.corcomponente());
				}
				
				if (table.getValueAt(rowIndex, 9).equals(true)) {
					c.setForeground(new Color(194, 175, 112));
				}
				
				return c;
			}
		};

		aces1.tipotable(table);
		table.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_INSERT) {
					try {
						int item = 0;

						if (listapdsaii.getRowCount() == 1) {
							int linha = table.convertRowIndexToModel(table.getSelectedRow());
							if (listapdsaii.getValueAt(linha, 2) == null
									|| listapdsaii.getValueAt(linha, 2).toString().isEmpty()) {
								listapdsaii.removeRow(linha);
							} else {
								item = listapdsaii.getRowCount();
							}
						} else {
							item = listapdsaii.getRowCount();
						}
						Pdsaii pi = new Pdsaii();
						new cadastravendasi(p, pi, item, listapdsaii).setVisible(true);
						if (pi.getItem() != null && !pi.getItem().toString().isEmpty()) {
							String iditem = ci.getItem().toString() + 1;
							listapdsaii.addRow(
									new Object[] { null, pi.getItem(), pi.getProduto(), pi.getDescpro(), pi.getUn(),
											aces1.valordinheiro(pi.getUnitario()),aces1.valordinheiro(pi.getVrtot()),
											decimalqtde.format(pi.getQuantidade()), pi.getPrazo(), pi.getTroca() });
							qttotal.setText(quantidadeTotal());
							vrtotal.setText(aces1.valordinheiro(valorTotal()));
							vrmercadoria.setText(aces1.valordinheiro(totalmercadoria()));
							table.setRowSelectionAllowed(true);
							int linha = listapdsaii.getRowCount();
							int conta = linha - 1;
							table.changeSelection(conta, linha, false, false);
							table.setSelectionBackground(Color.GREEN);
							table.changeSelection(0, 0, false, false);

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (evt.getKeyCode() == KeyEvent.VK_TAB) {
					contato.requestFocus();
				}

				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					gravaitem(p, ci);
				}

				if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
					if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
						int linha = table.convertRowIndexToModel(table.getSelectedRow());
						List<Pdsaii> listapdsaii1 = new ArrayList<Pdsaii>();
						try {
							int conseguiu_excluir = 0;
							String nome = "Deseja realmente excluir o item: " + table.getValueAt(linha, 1).toString()
									+ " ?";
							int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, "Exclusão ",
									JOptionPane.YES_NO_OPTION);
							if (opcao_escolhida == JOptionPane.YES_OPTION) {
								conseguiu_excluir = 1;
							}

							if (conseguiu_excluir == 1) {
								if (listapdsaii.getValueAt(linha, 0) != null) {
									Pdsaii removeitem = new Pdsaii();
									removeitem = cdao
											.procura(Long.parseLong(listapdsaii.getValueAt(linha, 0).toString()));
									pdsaiiremove.add(removeitem);
								}
								listapdsaii.removeRow(linha);

								for (int x = 0; x < listapdsaii.getRowCount(); x++) {
									int soma = x + 1;
									
									Pdsaii mudaitem = new Pdsaii();

									if (listapdsaii.getValueAt(x, 0) != null) {
										mudaitem.setIdpdsaii(Long.parseLong(listapdsaii.getValueAt(x, 0).toString()));
									}

									mudaitem.setItem(String.valueOf(soma));
									mudaitem.setProduto(listapdsaii.getValueAt(x, 2).toString());
									mudaitem.setDescpro(String.valueOf(listapdsaii.getValueAt(x, 3).toString()));
									mudaitem.setUn(String.valueOf(listapdsaii.getValueAt(x, 4).toString()));
									mudaitem.setUnitario(Double.parseDouble(
											aces1.gravamoedadouble(listapdsaii.getValueAt(x, 5).toString())));
									mudaitem.setVrtot(Double.parseDouble(
											aces1.gravamoedadouble(listapdsaii.getValueAt(x, 6).toString())));
									mudaitem.setQuantidade(Double.parseDouble(listapdsaii.getValueAt(x, 7).toString()));
									mudaitem.setPrazo((Date) listapdsaii.getValueAt(x, 8));
									mudaitem.setTroca((Boolean) listapdsaii.getValueAt(x, 9));
									mudaitem.setPedido(pedido.getText());
									listapdsaii1.add(mudaitem);
									mudaitem = new Pdsaii();

								}
								cdao.acertaitem(listapdsaii1, u);
								listapdsaii.setRowCount(0);

								for (Pdsaii com : listapdsaii1) {
									listapdsaii.addRow(new Object[] { com.getIdpdsaii(), com.getItem(),
											com.getProduto(), com.getDescpro(), com.getUn(),
											aces1.valordinheiro(com.getUnitario()),
											aces1.valordinheiro(com.getVrtot()),
											decimalqtde.format(com.getQuantidade()), com.getPrazo(),com.getTroca() });

								}

								table.setModel(listapdsaii);
								table.changeSelection(linha, 0, false, false);
								qttotal.setText(quantidadeTotal());
								vrtotal.setText(aces1.valordinheiro(valorTotal()));
								vrmercadoria.setText(aces1.valordinheiro(totalmercadoria()));
								table.changeSelection(0, 0, false, false);

							}

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "ERRO AO TENTAR EXCLUIR O ITEM " + e1.getMessage());
							aces1.demologger.error("ERRO AO TENTAR EXCLUIR O ITEM " + e1.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(null, "SELECIONE UM PEDIDO DE VENDAS !!!!!!!");
					}
				}

			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					gravaitem(p, ci);

				}
			}

		});
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		scrollPane_1.setViewportView(table);

		listapdsaii.addColumn("id");
		listapdsaii.addColumn("ITEM");
		listapdsaii.addColumn("PRODUTO");
		listapdsaii.addColumn("DESCRIÇÂO");
		listapdsaii.addColumn("UN");
		listapdsaii.addColumn("UNITARIO");
		listapdsaii.addColumn("VALORTOTAL");
		listapdsaii.addColumn("QUANTIDADE");
		listapdsaii.addColumn("Prazo");
		listapdsaii.addColumn("TROCA");

		// JOptionPane.showMessageDialog(null, "Pedido: " + pedidi);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela(p.getIdpdsaic());

		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setPreferredWidth(420);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(8).setMinWidth(0);
		table.getColumnModel().getColumn(8).setMaxWidth(0);
		table.getColumnModel().getColumn(9).setMinWidth(0);
		table.getColumnModel().getColumn(9).setMaxWidth(0);

		JLabel lblvrtotal = new JLabel("VALOR TOTAL");
		aces1.padraojlabel(lblvrtotal);
		lblvrtotal.setBounds(600, 255, 92, 14);
		contentPane.add(lblvrtotal);

		vrtotal = new JTextField("0,00");
		vrtotal.setFont(new Font("Tahoma", Font.PLAIN, 23));
		vrtotal.setForeground(Color.BLUE);
		vrtotal.setColumns(10);
		vrtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		vrtotal.setDisabledTextColor(Color.blue);
		vrtotal.setEnabled(false);
		vrtotal.setEditable(false);
		vrtotal.setOpaque(true);
		vrtotal.setBackground(new Color(240, 248, 255));
		vrtotal.setBorder(new LineBorder(Color.BLACK));
		vrtotal.setBounds(570, 269, 149, 66);
		contentPane.add(vrtotal);

		JLabel lblqttotal = new JLabel("QUANTIDADE TOTAL");
		aces1.padraojlabel(lblqttotal);
		lblqttotal.setBounds(5, 255, 125, 14);
		contentPane.add(lblqttotal);

		qttotal = new JTextField("0.0");
		aces1.bloqueado(qttotal);
		qttotal.setBounds(5, 272, 125, 20);
		contentPane.add(qttotal);

		JLabel lblvrmercadoria = new JLabel("VALOR MERCADORIA");
		aces1.padraojlabel(lblvrmercadoria);
		lblvrmercadoria.setBounds(137, 255, 132, 14);
		contentPane.add(lblvrmercadoria);

		vrmercadoria = new JTextField("0.0");
		aces1.bloqueado(vrmercadoria);
		vrmercadoria.setBounds(135, 272, 135, 20);
		contentPane.add(vrmercadoria);

		JLabel lblcontato = new JLabel("CONTATO");
		aces1.padraojlabel(lblcontato);
		lblcontato.setBounds(42, 298, 66, 14);
		contentPane.add(lblcontato);

		contato = new JTextField();
		contato.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		contato.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
					vendedor.requestFocus();
				}
			}
		});
		aces1.liberado(contato);
		aces1.letras(contato);
		contato.setBounds(5, 315, 150, 20);
		contentPane.add(contato);

		JLabel lblvendedor = new JLabel("VENDEDOR");
		aces1.padraojlabel(lblvendedor);
		lblvendedor.setBounds(200, 298, 75, 14);
		contentPane.add(lblvendedor);

		vendedor = new JTextField();
		vendedor.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		vendedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
					txentrega.requestFocus();
				}
			}

		});
		aces1.letras(vendedor);
		aces1.liberado(vendedor);
		vendedor.setBounds(160, 315, 150, 20);
		contentPane.add(vendedor);

		JLabel lbltxentrega = new JLabel("TAXA ENTREGA");
		aces1.padraojlabel(lbltxentrega);
		lbltxentrega.setBounds(320, 298, 98, 14);
		contentPane.add(lbltxentrega);

		txentrega = new JTextField();
		txentrega.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		txentrega.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent etx) {
				if (etx.getKeyCode() == KeyEvent.VK_TAB || etx.getKeyCode() == KeyEvent.VK_ENTER) {
					vrtotal.setText(aces1.formataMoeda(valorTotal()).replace("R$", ""));
					if (listapdsaii.getRowCount() <= 1) {

						int linha = table.convertRowIndexToModel(table.getSelectedRow());
						if (listapdsaii.getRowCount() == 0) {
							JOptionPane.showMessageDialog(null, "Pedido sem item!!!!!!!!");
							formapagamento.setEnabled(false);
						} else {
							if (txentrega.getText() != null && !txentrega.getText().isEmpty()) {
								if (listapdsaii.getValueAt(linha, 2) == null
										|| listapdsaii.getValueAt(linha, 2) == "") {
									JOptionPane.showMessageDialog(null, "Pedido sem item");
									formapagamento.setEnabled(false);
								} else {
									if (cliente.getText() == null || cliente.getText().isEmpty()) {
										JOptionPane.showMessageDialog(null, "Pedido sem cliente");
									} else {
										formapagamento.setEnabled(true);
										formapagamento.requestFocus();
									}
								}
							} else {
								txentrega.requestFocus();
								JOptionPane.showMessageDialog(null, "Pedido sem taxa de Entrega");
							}
						}
					} else {
						if (cliente.getText() == null || cliente.getText().trim().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Pedido sem cliente");
						} else {
							formapagamento.setEnabled(true);
							formapagamento.requestFocus();
						}

					}

				}
			}
		});
		aces1.liberado(txentrega);
		txentrega.setDocument(new MonetarioDocument());
		txentrega.setBounds(325, 315, 83, 20);
		contentPane.add(txentrega);

		JLabel lblformpagto = new JLabel("FORMA PAGAMENTO");
		aces1.padraojlabel(lblformpagto);
		lblformpagto.setBounds(430, 298, 130, 14);
		contentPane.add(lblformpagto);

		formapagamento = new JComboBox();
		formapagamento.setModel(new DefaultComboBoxModel(
				new String[] { "DINHEIRO", "CARTÃO CREDITO", "CARTÃO DEBITO", "PIX", "CHEQUE" }));
		formapagamento.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		formapagamento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent etx) {
				if (etx.getKeyCode() == KeyEvent.VK_TAB || etx.getKeyCode() == KeyEvent.VK_ENTER) {
					vrtotal.setText(aces1.formataMoeda(valorTotal()).replace("R$", ""));
					if (listapdsaii.getRowCount() <= 1) {

						int linha = table.convertRowIndexToModel(table.getSelectedRow());
						if (listapdsaii.getRowCount() == 0) {
							JOptionPane.showMessageDialog(null, "Pedido sem item!!!!!!!!");
							btncadastrar.setEnabled(false);
						} else {

							if (listapdsaii.getValueAt(linha, 2) == null || listapdsaii.getValueAt(linha, 2) == "") {
								JOptionPane.showMessageDialog(null, "Pedido sem item");
								btncadastrar.setEnabled(false);
							} else {
								if (cliente.getText() == null || cliente.getText().isEmpty()) {
									JOptionPane.showMessageDialog(null, "Pedido sem cliente");
								} else {
									btncadastrar.setEnabled(true);
									obs.requestFocus();
								}
							}
						}
					} else {
						if (cliente.getText() == null || cliente.getText() == "") {
							JOptionPane.showMessageDialog(null, "Pedido sem cliente");
						} else {
							btncadastrar.setEnabled(true);
							obs.requestFocus();
						}

					}

				}
			}
		});
		formapagamento.setEnabled(false);
		formapagamento.setForeground(Color.BLUE);
		formapagamento.setBounds(425, 315, 130, 20);
		contentPane.add(formapagamento);

		JLabel lblobs = new JLabel("OBSERVAÇÃO");
		aces1.padraojlabel(lblobs);
		lblobs.setBounds(320, 345, 90, 14);
		contentPane.add(lblobs);

		obs = new JTextArea(new TTextAreaDocument(250));
		obs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					btncadastrar.requestFocus();
				}
			}
		});
		aces1.liberadojTextArea(obs);
		obs.setBounds(4, 365, 723, 64);
		contentPane.add(obs);

		btncadastrar = new JButton();
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravapedido(acesso,u);
				}
			}
		});
		btncadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				gravapedido(acesso,u);
			}
		});
		btncadastrar.setEnabled(false);
		btncadastrar.setBounds(300, 450, 55, 46);
		btncadastrar.setIcon(new ImageIcon(listadecliente.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		contentPane.add(btncadastrar);

		JButton btncancelar = new JButton();
		btncancelar.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		btncancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
				}

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					entrega.requestFocus();
				}

			}
		});
		btncancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btncancelar.setIcon(new ImageIcon(listadecliente.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btncancelar);
		btncancelar.setBounds(370, 450, 55, 46);
		contentPane.add(btncancelar);

		if (p.getIdpdsaic() != null) {

			cliente.setText(p.getCliente().getCODCLI().trim());
			desccli.setText(p.getCliente().getDESCCLI().trim());
			emissao.setText(aces1.retornadatastring(p.getEmissao()));
			entrega.setText(aces1.retornadatastring(p.getVencto()));
			pedido.setText(p.getNumdoc().trim());
			if (p.getTxent() == null) {
				txentrega.setText("0.0");
			} else {
				aces1.moedabanco(p.getTxent(), txentrega);
			}
			vrmercadoria.setText(aces1.valordinheiro(totalmercadoria()));
			vrtotal.setText(aces1.valordinheiro(valorTotal()));
			obs.setText(p.getOBS().trim());
			vendedor.setText(p.getVendedor().trim());
			cadclip = p.getCliente();
			contato.setText(p.getContato().trim());
			qttotal.setText(quantidadeTotal());
			formapagamento.setSelectedItem(p.getFormpagto().trim());

			aces1.bloqueado(cliente);
			aces1.bloqueado(emissao);
			aces1.liberado(entrega);
			formapagamento.setEnabled(true);
			btncadastrar.setEnabled(true);

		}

		if (listapdsaii.getRowCount() == 0) {
			Pdsaii pi = new Pdsaii();
			listapdsaii.addRow(new Object[] { pi.getIdpdsaii(), pi.getItem(), pi.getProduto(), pi.getDescpro(),
					pi.getUn(), pi.getUnitario(), pi.getVrtot(), pi.getQuantidade(), pi.getPrazo(), pi.getTroca() });
		}
		table.changeSelection(0, 0, false, false);

		// fechar janela com esc e quebrando parametros
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cliente.setText("");
				desccli.setText("");
				emissao.setText("");
				pedido.setText("");
				vrmercadoria.setText("");
				vrtotal.setText("");
				obs.setText("");
				vendedor.setText("");
				entrega.setText("");
				ci = new Pdsaii();
				p.setIdpdsaic(null);
				// c1.fechaconexao();
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				if (p.getIdpdsaic() == null) {
					cliente.requestFocus();
					cliente.setCaretPosition(0);
				} else {
					entrega.requestFocus();
					entrega.setCaretPosition(0);
				}

			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

	}

	// fim do Jdialog

	private MaskFormatter setNumero(String numero) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(numero);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}

	public void carregartabela(Long idpedido) throws Exception {

		PdsaiiBean lista = new PdsaiiBean();
		if (idpedido != null) {
			List<Pdsaii> list = lista.getPdsaiis(idpedido);

			for (Pdsaii com : list) {
				listapdsaii.addRow(new Object[] { com.getIdpdsaii(), com.getItem(), com.getProduto().trim(), com.getDescpro().trim(),
						com.getUn().trim(), aces1.valordinheiro(com.getUnitario()),aces1.valordinheiro(com.getVrtot()),
						decimalqtde.format(com.getQuantidade()), com.getPrazo(),com.getTroca() });

			}
		}

	}

	private String quantidadeTotal() {
		Double Orcamento = 0.0;
		for (int i = 0; i < listapdsaii.getRowCount(); i++) {
			Orcamento += Double.parseDouble(listapdsaii.getValueAt(i, 7).toString().replace(".", "").replace(",", "."));
		}
		return decimalqtde.format(Orcamento);
	}

	private Double valorTotal() {
		DecimalFormat decimal = new DecimalFormat("0.00");
		Double Orcamento = 0.0;
		for (int i = 0; i < listapdsaii.getRowCount(); i++) {
			Orcamento += Double.parseDouble((listapdsaii.getValueAt(i, 6).toString().replace(".", "").replace(",", ".")));
		}
		
		if (txentrega.getText() != null && !txentrega.getText().isEmpty()) {
			Orcamento += Double.parseDouble(txentrega.getText().replace(".", "").replace(",", "."));
		}
		
		return Orcamento;
	}

	private Double totalmercadoria() {
		Double Orcamento = 0.0;
		for (int i = 0; i < listapdsaii.getRowCount(); i++) {
			Orcamento += Double.parseDouble(aces1.gravamoedadouble(listapdsaii.getValueAt(i, 6).toString().trim()));
		}

		return Orcamento;
	}

	private void gravapedido(Acesso acesso, Usuario u1) {
		Pdsaic cadastro = new Pdsaic();
		Pdsaii ciitem = new Pdsaii();
		try {

			if (idp != null) {
				cadastro = c1.procura(idp);
			}

			cadastro.setEmissao(aces1.retornadata(emissao.getText()));
			cadastro.setVencto(aces1.retornadata(entrega.getText()));
			cadastro.setNumdoc(pedido.getText().trim());
			cadastro.setVrmerc(Double.parseDouble(aces1.gravamoedadouble(vrmercadoria.getText())));
			cadastro.setVrtot(Double.parseDouble(aces1.gravamoedadouble(vrtotal.getText())));
			cadastro.setCliente(cadclip);
			cadastro.setOBS(obs.getText().trim());
			cadastro.setContato(contato.getText().trim());
			cadastro.setVendedor(vendedor.getText().trim());
			cadastro.setTxent(Double.parseDouble(aces1.gravamoedadouble(txentrega.getText())));
			cadastro.setFormpagto(formapagamento.getSelectedItem().toString().trim());
			cadastro.setVrdesc(Double.parseDouble(aces1.gravamoedadouble(vrmercadoria.getText())));

			int l = 0;

			for (int x = 0; x < listapdsaii.getRowCount(); x++) {
				if (listapdsaii.getValueAt(x, 2) != "") {
					l++;
				}

			}

			if (cliente.getText().isEmpty() || cliente.getText().equals(null) || desccli.getText().isEmpty()
					|| desccli.getText() == null || l == 0) {
				JOptionPane.showMessageDialog(null, "Dados em branco");
			} else {

				Boolean g = false;

				if (cadastro.getIdpdsaic() == null) {
					if (acesso.getInclusao() == true) {
						g = true;
					} else {
						JOptionPane.showMessageDialog(null, aces1.incluir);

					}
				} else {
					if (acesso.getAlteracao() == true) {
						g = true;
					} else {
						JOptionPane.showMessageDialog(null, aces1.alterar);

					}

				}

				if (g == true) {
					c1.adiciona(cadastro, u1);
					pedido.setText(cadastro.getNumdoc());

					if (idp == null) {

						repositorylog.adicionaLog(aces1.logincluir,
								"PEDIDOVENDAS NUMERO " + cadastro.getNumdoc() + " CLIENTE "
										+ cadastro.getCliente().getCODCLI().trim() + " "
										+ cadastro.getCliente().getDESCCLI().trim(),
								cadastro.getIdpdsaic(), u1);
					} else {

						repositorylog.adicionaLog(aces1.logalterar,
								"PEDIDOVENDAS NUMERO " + cadastro.getNumdoc() + " CLIENTE "
										+ cadastro.getCliente().getCODCLI().trim() + " "
										+ cadastro.getCliente().getDESCCLI().trim(),
								cadastro.getIdpdsaic(), u1);
					}

					List<Pdsaii> listapdenti1 = new ArrayList<Pdsaii>();

					for (int x = 0; x < listapdsaii.getRowCount(); x++) {
						ciitem.setPedido(pedido.getText());
						ciitem.setIdpdsaii((Long) listapdsaii.getValueAt(x, 0));
						ciitem.setItem(listapdsaii.getValueAt(x, 1).toString());
						ciitem.setProduto(String.valueOf(listapdsaii.getValueAt(x, 2)));
						ciitem.setDescpro(String.valueOf(listapdsaii.getValueAt(x, 3)));
						ciitem.setUn(String.valueOf(listapdsaii.getValueAt(x, 4)));
						ciitem.setUnitario(
								Double.parseDouble(aces1.gravamoedadouble(listapdsaii.getValueAt(x, 5).toString())));
						ciitem.setVrtot(
								Double.parseDouble(aces1.gravamoedadouble(listapdsaii.getValueAt(x, 6).toString())));
						ciitem.setQuantidade(Double.parseDouble(listapdsaii.getValueAt(x, 7).toString()));
						ciitem.setPrazo((Date) listapdsaii.getValueAt(x, 8));
						ciitem.setTroca((Boolean) listapdsaii.getValueAt(x, 9));
						ciitem.setEmissao(cadastro.getEmissao());
						listapdenti1.add(ciitem);
						ciitem.setPedc(cadastro);

						ciitem = new Pdsaii();
					}

					cdao.adiciona(listapdenti1, u1);

					if(!pdsaiiremove.isEmpty()) {
						cdao.remove(pdsaiiremove, u1);
					}
					
					dispose();
				}
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "ERRO GRAVA PEDIDO DE VENDAS " + e1.getMessage());
			aces1.demologger.error("ERRO GRAVA PEDIDO DE VENDAS " + e1.getMessage());
		}
	}

	public void gravaitem(Pdsaic p, Pdsaii pi) {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());

			Long idT = null;

			if (listapdsaii.getValueAt(linhaSel, 1) != null) {
				if (listapdsaii.getValueAt(linhaSel, 0) != null) {
					idT = Long.parseLong(listapdsaii.getValueAt(linhaSel, 0).toString());
				}
			} else {
				idT = null;
			}

			Object item;
			if (listapdsaii.getValueAt(linhaSel, 1) == null
					|| listapdsaii.getValueAt(linhaSel, 1).toString().isEmpty()) {
				item = 0;
			} else {
				item = listapdsaii.getValueAt(linhaSel, 1);

			}

			Object produto = listapdsaii.getValueAt(linhaSel, 2);
			Object descpro = listapdsaii.getValueAt(linhaSel, 3);
			Object un = listapdsaii.getValueAt(linhaSel, 4);
			Object unitario = listapdsaii.getValueAt(linhaSel, 5);
			Object vrtotal1 = listapdsaii.getValueAt(linhaSel, 6);
			Object quantidade = listapdsaii.getValueAt(linhaSel, 7);
			Object prazo = listapdsaii.getValueAt(linhaSel, 8);
			Object troca = listapdsaii.getValueAt(linhaSel, 9);

			try {
				if (idT == null) {
					if (produto != null && !String.valueOf(produto).isEmpty()) {
						idT = Long.parseLong("0");
					}
				}

				pi.setIdpdsaii(idT);
				pi.setItem(item.toString());
				pi.setProduto(String.valueOf(produto));
				pi.setDescpro(String.valueOf(descpro));
				pi.setUn(String.valueOf(un));
				pi.setUnitario(Double.parseDouble(aces1.gravamoedadouble(unitario.toString())));
				pi.setVrtot(Double.parseDouble(aces1.gravamoedadouble(vrtotal1.toString())));
				pi.setQuantidade(Double.parseDouble(quantidade.toString()));
				pi.setPrazo((Date) prazo);
				pi.setTroca((Boolean) troca);

				new cadastravendasi(p, ci, Integer.parseInt(item.toString()), listapdsaii).setVisible(true);

				DecimalFormat formato = new DecimalFormat("#.##");
				idT = ci.getIdpdsaii();
				item = ci.getItem();
				produto = ci.getProduto();
				descpro = ci.getDescpro();
				un = ci.getUn();
				unitario = ci.getUnitario();
				vrtotal1 = ci.getVrtot();
				quantidade = ci.getQuantidade();
				prazo = ci.getPrazo();
				troca = ci.getTroca();

				if (ci.getItem() != null && !ci.getItem().toString().isEmpty()) {
					Object id = idT;
					listapdsaii.setValueAt(id, linhaSel, 0);
					listapdsaii.setValueAt(item, linhaSel, 1);
					listapdsaii.setValueAt(produto, linhaSel, 2);
					listapdsaii.setValueAt(descpro, linhaSel, 3);
					listapdsaii.setValueAt(un, linhaSel, 4);
					listapdsaii.setValueAt(aces1.valordinheiro(Double.parseDouble(unitario.toString())),linhaSel, 5);
					listapdsaii.setValueAt(aces1.valordinheiro(Double.parseDouble(vrtotal1.toString())),linhaSel, 6);
					listapdsaii.setValueAt(decimalqtde.format(quantidade), linhaSel, 7);
					listapdsaii.setValueAt(prazo, linhaSel, 8);
					listapdsaii.setValueAt(troca, linhaSel, 9);
					table.changeSelection(linhaSel, 0, false, false);
					qttotal.setText(quantidadeTotal());
					vrtotal.setText(aces1.valordinheiro(valorTotal()));
					vrmercadoria.setText(aces1.valordinheiro(totalmercadoria()));
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO AO ALTERAR ITEM PEDIDO VENDAS " + e.getMessage());
				aces1.demologger.error("ERRO AO ALTERAR ITEM PEDIDO VENDAS " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM PEDIDO PARA VISUALIZAR !!!");
		}

	}

	public void validacliente() {

		Cadcli c2 = new Cadcli();

		c2 = c1.Validacao(cliente.getText());

		if (c2.getIdcadcli() != null) {

			if (c2.getINATIVO() == true) {
				JOptionPane.showMessageDialog(null, "Cliente Desativado !!!");
				cliente.setText("");
				desccli.setText("");
				cliente.requestFocus();
				aces1.liberado(cliente);
			} else {
				cadclip = c2;
				desccli.setText(c2.getDESCCLI().trim());
				Date data = new Date();
				SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
				formatador.format(data);
				aces1.liberado(emissao);
				emissao.requestFocus();
				emissao.setText(formatador.format(data));
				aces1.liberado(entrega);
				entrega.setText(formatador.format(data));
				emissao.setCaretPosition(0);// posicao zero do cursor
				aces1.bloqueado(cliente);
				aces1.bloqueado(desccli);
			}

		} else {
			JOptionPane.showMessageDialog(null, "Cliente não encontrado!!!");
			cliente.setText("");
			cliente.requestFocus();
			aces1.liberado(cliente);
		}

	}
}
