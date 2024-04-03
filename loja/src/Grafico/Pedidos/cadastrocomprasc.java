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
import entidades.Cadfor;
import entidades.Cadpro;
import entidades.Pdentc;
import entidades.Pdenti;
import entidades.Usuario;
import filter.EntityManagerUtil;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import Grafico.Cadcli.listadecliente;
import Grafico.Cadcli.listaprocuracliente;
import Grafico.Fornecedor.listaprocurafornecedor;
import beans.AcessoBean;
import beans.CadcliBean;
import beans.CadforBean;
import beans.PdentcBean;
import beans.PdentiBean;
import Grafico.geral.TTextAreaDocument;
import Grafico.geral.TelaPrincipal;

import java.sql.Statement;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextArea;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
@Slf4j
public class cadastrocomprasc extends JDialog {
	private JPanel contentPane;
	private JTable table;
	private JTextField pedido, emissao, descfor, fornecedor, entrega, qttotal, vrmercadoria, contato, vrtotal, vendedor;

	private JTextArea obs;

	JButton btncadastrar;

	PdentcBean c1 = new PdentcBean();
	Pdentc c = new Pdentc();

	Cadfor f = new Cadfor();
	Cadfor cadforp = new Cadfor();
	CadforBean f1 = new CadforBean();

	Pdenti ci = new Pdenti();
	PdentiBean cdao = new PdentiBean();

	String rotina = TelaPrincipal.rotinaco;;
	AcessoBean aces1 = new AcessoBean();
	private Long idp;

	List<Pdenti> pdentiremove = new ArrayList<Pdenti>();
	Locale locale = new Locale("pt", "BR");
	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	DecimalFormat decimal = new DecimalFormat("0.00");
	DecimalFormat decimalqtde = new DecimalFormat("0");

	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listapdenti = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	@SuppressWarnings("unchecked")
	public cadastrocomprasc(final Pdentc p, Usuario u, Acesso acesso) throws Exception {
		idp = p.getIdpdentc();
		cadforp = p.getForn();
		setTitle("PEDIDO DE COMPRA");
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

		JLabel lblpedido = new JLabel("PEDIDO");
		aces1.padraojlabel(lblpedido);
		lblpedido.setBounds(35, 4, 55, 14);
		contentPane.add(lblpedido);

		pedido = new JTextField();
		aces1.bloqueado(pedido);
		pedido.setBounds(10, 19, 100, 20);
		contentPane.add(pedido);

		JLabel lblfornecedor = new JLabel("FORNECEDOR");
		aces1.padraojlabel(lblfornecedor);
		lblfornecedor.setBounds(120, 3, 99, 14);
		contentPane.add(lblfornecedor);

		fornecedor = new JTextField();
		fornecedor.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		fornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

					try {
						if (fornecedor.getText() == null || fornecedor.getText().isEmpty()) {
							new listaprocurafornecedor(f).setVisible(true);
						} else {

							if (c1.Validacao(fornecedor.getText()).getIDFORNECEDOR() != null) {
								cadforp = c1.Validacao(fornecedor.getText());
								descfor.setText(c1.Validacao(fornecedor.getText()).getDESCFOR());
								SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
								formatador.format(aces1.dataatual());
								aces1.liberado(emissao);
								emissao.requestFocus();
								emissao.setText(formatador.format(aces1.dataatual()));
								aces1.liberado(entrega);
								entrega.setText(formatador.format(aces1.dataatual()));
								emissao.setCaretPosition(0);// posicao zero do cursor
								aces1.bloqueado(fornecedor);
								aces1.bloqueado(descfor);
								aces1.liberadojTextArea(obs);
								btncadastrar.setEnabled(true);

							} else {
								JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!!!");
								fornecedor.setText("");
								fornecedor.requestFocus();
								aces1.liberado(fornecedor);
							}

						}

					} catch (Exception e) {

						e.printStackTrace();
					}
					if (f.getIDFORNECEDOR() != null) {
						cadforp = f1.procura(f.getIDFORNECEDOR());
						fornecedor.setText(cadforp.getCODFOR());
						descfor.setText(cadforp.getDESCFOR());
						Date data = new Date();
						SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
						formatador.format(data);
						aces1.liberado(emissao);
						emissao.requestFocus();
						emissao.setText(formatador.format(data));
						aces1.liberado(entrega);
						entrega.setText(formatador.format(data));
						aces1.bloqueado(fornecedor);
						aces1.bloqueado(descfor);
						emissao.setCaretPosition(0);// posicao zero do cursor
						aces1.liberadojTextArea(obs);
						btncadastrar.setEnabled(true);
					} else {
						fornecedor.requestFocus();
					}
				}

				if (evt.getKeyCode() == KeyEvent.VK_TAB) {
					if (fornecedor.getText().equals("") || fornecedor.getText() == null) {
						JOptionPane.showMessageDialog(null, "Digite um valor!!!");
						fornecedor.requestFocus();
					} else {
						cadforp = c1.Validacao(fornecedor.getText());
						if (cadforp.getIDFORNECEDOR() != null) {
							descfor.setText(cadforp.getDESCFOR());
							Date data = new Date();
							SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
							formatador.format(data);
							aces1.liberado(emissao);
							emissao.requestFocus();
							emissao.setText(formatador.format(data));
							aces1.liberado(entrega);
							entrega.setText(formatador.format(data));
							emissao.setCaretPosition(0);// posicao zero do cursor
							aces1.bloqueado(fornecedor);
							aces1.bloqueado(descfor);
							aces1.liberadojTextArea(obs);
							btncadastrar.setEnabled(true);

						} else {
							JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!!!");
							fornecedor.setText("");
							fornecedor.requestFocus();
							aces1.liberado(fornecedor);
						}
					}
				}

			}

		});
		aces1.liberado(fornecedor);
		fornecedor.setBounds(115, 19, 100, 20);
		contentPane.add(fornecedor);

		descfor = new JTextField();
		aces1.bloqueado(descfor);
		descfor.setBounds(220, 19, 296, 20);
		contentPane.add(descfor);

		JLabel lblemissao = new JLabel("EMISSÃO");
		aces1.padraojlabel(lblemissao);
		lblemissao.setBounds(545, 3, 60, 14);
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
		emissao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				aces1.validadata(false, emissao, entrega);
			}
		});
		aces1.bloqueado(emissao);
		aces1.numeros(emissao);
		emissao.setBounds(525, 19, 97, 20);
		contentPane.add(emissao);

		JLabel lblentrega = new JLabel("ENTREGA");
		aces1.padraojlabel(lblentrega);
		lblentrega.setBounds(650, 3, 63, 14);
		contentPane.add(lblentrega);

		entrega = new JFormattedTextField((setNumero("##/##/####")));
		entrega.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		entrega.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					df.setLenient(false); // aqui o pulo do gato
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

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		scrollPane_1.setBounds(0, 45, 732, 204);
		setBackground(new java.awt.Color(192, 192, 255));
		setForeground(Color.BLACK);

		contentPane.add(scrollPane_1);

		table = new JTable(listapdenti) {
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
				Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
				c.setFont(aces1.tipoletra());
				aces1.tipocomponete(c);
				if (isCellSelected(rowIndex, vColIndex)) {
					c.setBackground(aces1.corcomponente());
				}
				return c;
			}
		};

		aces1.tipotable(table);
		table.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent evt) {
				if (acesso.getInclusao() == true) {
					if (evt.getKeyCode() == KeyEvent.VK_INSERT) {
						try {

							int item = 0;

							if (listapdenti.getRowCount() == 1) {
								int linha = table.convertRowIndexToModel(table.getSelectedRow());
								if (listapdenti.getValueAt(linha, 2) == null
										|| listapdenti.getValueAt(linha, 2) == "") {
									listapdenti.removeRow(linha);
								} else {
									item = listapdenti.getRowCount();
								}
							} else {
								item = listapdenti.getRowCount();
							}
							Pdenti pi = new Pdenti();
							new cadastracomprasi(p, pi, item).setVisible(true);
							if (pi.getItem() != null && !pi.getItem().trim().isEmpty()) {
								String iditem = ci.getItem() + 1;
								listapdenti.addRow(new Object[] { null, pi.getItem(), pi.getProduto(), pi.getDescpro(),
										pi.getUn(),
										currencyFormatter.format(pi.getUnitario()).toString().replace("R$", "").trim(),
										currencyFormatter.format(pi.getVrtot()).toString().replace("R$", "").trim(),
										decimalqtde.format(pi.getQuantidade()), pi.getPrazo() });
								qttotal.setText(quantidadeTotal());
								vrtotal.setText(aces1.formataMoeda(valorTotal()).replace("R$", ""));
								vrmercadoria.setText(aces1.formataMoeda(totalmercadoria()).replace("R$", ""));
								table.setRowSelectionAllowed(true);
								int linha = listapdenti.getRowCount();
								int conta = linha - 1;
								table.changeSelection(conta, linha, false, false);
								table.setSelectionBackground(Color.GREEN);
								table.changeSelection(0, 0, false, false);

							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Erro insert lista de compras: " + e.getMessage());
							e.printStackTrace();
						}
					}
				}

				if (evt.getKeyCode() == KeyEvent.VK_TAB) {
					contato.requestFocus();
				}

				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					if (acesso.getInclusao() == true) {
						gravaitem(p, ci, acesso);
					}
				}

				if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
					if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
						int linha = table.convertRowIndexToModel(table.getSelectedRow());
						List<Pdenti> listapdenti1 = new ArrayList<Pdenti>();
						try {
							if (acesso.getExclusao() == true) {
								int conseguiu_excluir = 0;
								String nome = "Deseja realmente excluir o item: "
										+ table.getValueAt(linha, 1).toString() + " ?";
								int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, "Exclusão ",
										JOptionPane.YES_NO_OPTION);
								if (opcao_escolhida == JOptionPane.YES_OPTION) {
									conseguiu_excluir = 1;
								}

								if (conseguiu_excluir == 1) {
									if (listapdenti.getValueAt(linha, 0) != null) {
										Pdenti removeitem = new Pdenti();
										removeitem = cdao
												.procura(Long.parseLong(listapdenti.getValueAt(linha, 0).toString()));
										pdentiremove.add(removeitem);

									}
									listapdenti.removeRow(linha);

									for (int x = 0; x < listapdenti.getRowCount(); x++) {
										int soma = x + 1;
										Pdenti mudaitem = new Pdenti();

										if (listapdenti.getValueAt(x, 0) != null) {
											mudaitem.setIdpdenti(
													Long.parseLong(listapdenti.getValueAt(x, 0).toString()));

										}

										mudaitem.setPedido(pedido.getText());
										mudaitem.setIdpdenti((Long) listapdenti.getValueAt(x, 0));
										mudaitem.setItem(String.valueOf(soma));
										mudaitem.setProduto(String.valueOf(listapdenti.getValueAt(x, 2)));
										mudaitem.setDescpro(String.valueOf(listapdenti.getValueAt(x, 3)));
										mudaitem.setUn(String.valueOf(listapdenti.getValueAt(x, 4)));
										mudaitem.setUnitario(Double.parseDouble(
												aces1.gravamoedadouble(listapdenti.getValueAt(x, 5).toString())));
										mudaitem.setVrtot(Double.parseDouble(
												aces1.gravamoedadouble(listapdenti.getValueAt(x, 6).toString())));
										mudaitem.setQuantidade(
												Double.parseDouble(listapdenti.getValueAt(x, 7).toString()));

										listapdenti1.add(mudaitem);
										mudaitem = new Pdenti();

									}

									listapdenti.setRowCount(0);

									for (Pdenti com : listapdenti1) {
										listapdenti.addRow(new Object[] { com.getIdpdenti(), com.getItem(),
												com.getProduto(), com.getDescpro(), com.getUn(),
												currencyFormatter.format(com.getUnitario()).toString().replace("R$", "")
														.trim(),
												currencyFormatter.format(com.getVrtot()).toString().replace("R$", "")
														.trim(),
												decimalqtde.format(com.getQuantidade()), com.getPrazo() });

									}

									table.setModel(listapdenti);
									table.changeSelection(linha, 0, false, false);
									qttotal.setText(quantidadeTotal());
									vrtotal.setText(aces1.formataMoeda(valorTotal()).replace("R$", ""));
									vrmercadoria.setText(aces1.formataMoeda(totalmercadoria()).replace("R$", ""));
									table.changeSelection(0, 0, false, false);
								}
							} else {
								JOptionPane.showMessageDialog(null, aces1.getExcluir());
							}

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "ERRO AO TENTAR EXCLUIR O ITEM " + e1.getMessage());
							aces1.demologger.error("ERRO AO TENTAR EXCLUIR O ITEM " + e1.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(null, "SELECIONE UM PEDIDO DE COMPRAS !!!!!!!!");
					}
				}

			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					gravaitem(p, ci, acesso);

				}
			}

		});
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		scrollPane_1.setViewportView(table);

		listapdenti.addColumn("id");
		listapdenti.addColumn("ITEM");
		listapdenti.addColumn("PRODUTO");
		listapdenti.addColumn("DESCRIÇÃO");
		listapdenti.addColumn("UN");
		listapdenti.addColumn("UNITARIO");
		listapdenti.addColumn("VALORTOTAL");
		listapdenti.addColumn("QUANTIDADE");
		listapdenti.addColumn("Prazo");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela(p.getIdpdentc());

		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setPreferredWidth(400);
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

		JLabel lblvrtotal = new JLabel("VALOR TOTAL");
		aces1.padraojlabel(lblvrtotal);
		lblvrtotal.setBounds(600, 252, 92, 14);
		contentPane.add(lblvrtotal);

		vrtotal = new JTextField("0,00");
		vrtotal.setFont(new Font("Tahoma", Font.PLAIN, 23));
		vrtotal.setForeground(Color.BLUE);
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
		lblcontato.setBounds(60, 298, 65, 14);
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
		contato.setBounds(5, 315, 168, 20);
		contentPane.add(contato);

		JLabel lblvendedor = new JLabel("VENDEDOR");
		aces1.padraojlabel(lblvendedor);
		lblvendedor.setBounds(230, 298, 75, 14);
		contentPane.add(lblvendedor);

		vendedor = new JTextField();
		vendedor.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		vendedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
					obs.requestFocus();
				}
			}
		});
		aces1.letras(vendedor);
		aces1.liberado(vendedor);
		vendedor.setBounds(175, 315, 168, 20);
		contentPane.add(vendedor);

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
		aces1.bloqueadojTextArea(obs);
		obs.setBounds(4, 365, 723, 64);
		contentPane.add(obs);

		btncadastrar = new JButton();
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravapedido(acesso, u);
				}
			}
		});
		btncadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				gravapedido(acesso, u);
			}
		});
		btncadastrar.setEnabled(false);
		btncadastrar.setIcon(new ImageIcon(listadecliente.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		btncadastrar.setBounds(300, 450, 55, 46);
		contentPane.add(btncadastrar);

		JButton btncancelar = new JButton();
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
		aces1.butonfundo(btncancelar);
		btncancelar.setBounds(370, 450, 55, 46);
		contentPane.add(btncancelar);

		if (p.getIdpdentc() != null) {

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			fornecedor.setText(p.getForn().getCODFOR().trim());
			descfor.setText(p.getForn().getDESCFOR().trim());
			emissao.setText(dateFormat.format(p.getEmissao()));
			entrega.setText(dateFormat.format(p.getVencto()));
			pedido.setText(p.getNumdoc().trim());
			vrmercadoria.setText(aces1.formataMoeda(totalmercadoria()).replace("R$", ""));
			vrtotal.setText(aces1.formataMoeda(valorTotal()).replace("R$", ""));
			obs.setText(p.getObs().trim());
			vendedor.setText(p.getVendedor().trim());
			cadforp = p.getForn();
			contato.setText(p.getConf().trim());
			qttotal.setText(quantidadeTotal());

			aces1.bloqueado(emissao);
			aces1.bloqueado(fornecedor);
			aces1.liberado(entrega);
			aces1.liberadojTextArea(obs);
			btncadastrar.setEnabled(true);

		}

		if (listapdenti.getRowCount() == 0) {
			Pdenti pi = new Pdenti();
			listapdenti.addRow(new Object[] { pi.getIdpdenti(), pi.getItem(), pi.getProduto(), pi.getDescpro(),
					pi.getUn(), pi.getUnitario(), pi.getVrtot(), pi.getQuantidade(), pi.getPrazo() });
		}
		table.changeSelection(0, 0, false, false);

		// fechar janela com esc e quebrando parametros
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fornecedor.setText("");
				descfor.setText("");
				emissao.setText("");
				pedido.setText("");
				vrmercadoria.setText("");
				vrtotal.setText("");
				obs.setText("");
				vendedor.setText("");
				entrega.setText("");
				ci = new Pdenti();
				p.setIdpdentc(null);
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				if (p.getIdpdentc() == null) {
					fornecedor.requestFocus();
					fornecedor.setCaretPosition(0);
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
			JOptionPane.showMessageDialog(null, "Erro maskara: " + ex.getMessage());
		}
		return mask;
	}

	public void carregartabela(Long pe) throws Exception {

		PdentiBean lista = new PdentiBean();
		List<Pdenti> list = (List<Pdenti>) lista.getPdentis(pe);

		for (Pdenti com : list) {
			listapdenti.addRow(new Object[] { com.getIdpdenti(), com.getItem(), com.getProduto(), com.getDescpro(),
					com.getUn(), currencyFormatter.format(com.getUnitario()).toString().replace("R$", "").trim(),
					currencyFormatter.format(com.getVrtot()).toString().replace("R$", "").trim(),
					decimalqtde.format(com.getQuantidade()), com.getPrazo() });

		}

	}

	private String quantidadeTotal() {
		Double Orcamento = 0.0;
		DecimalFormat decimalqtde = new DecimalFormat("0");
		for (int i = 0; i < listapdenti.getRowCount(); i++) {
			Orcamento += Double.parseDouble(listapdenti.getValueAt(i, 7).toString());
		}
		return decimalqtde.format(Orcamento);
	}

	// soma os valores e arredonda
	private Double valorTotal() {
		Double total = 0.0;
		for (int i = 0; i < listapdenti.getRowCount(); i++) {
			total += Double.parseDouble(aces1.gravamoedadouble(listapdenti.getValueAt(i, 6).toString().trim()));
		}
		return total;
	}

	private Double totalmercadoria() {
		Double Orcamento = 0.0;
		for (int i = 0; i < listapdenti.getRowCount(); i++) {
			Orcamento += Double.parseDouble(aces1.gravamoedadouble(listapdenti.getValueAt(i, 6).toString().trim()));
		}

		return Orcamento;
	}

	private void gravapedido(Acesso acesso, Usuario u) {
		Pdentc cadastro = new Pdentc();
		Pdenti ciitem = new Pdenti();
		try {

			if (idp != null) {
				cadastro = c1.procura(idp);
			}

			cadastro.setEmissao(aces1.retornadata(emissao.getText()));
			cadastro.setVencto(aces1.retornadata(entrega.getText()));
			cadastro.setNumdoc(pedido.getText().trim());
			cadastro.setVrmerc(Double.parseDouble(aces1.gravamoedadouble(vrmercadoria.getText())));
			cadastro.setVrtot(Double.parseDouble(aces1.gravamoedadouble(vrtotal.getText())));
			cadastro.setForn(cadforp);
			cadastro.setObs(obs.getText().trim());
			cadastro.setContato(contato.getText().trim());
			cadastro.setVendedor(vendedor.getText().trim());

			int l = 0;

			for (int x = 0; x < listapdenti.getRowCount(); x++) {
				if (listapdenti.getValueAt(x, 2) != "") {

					l++;

				}

			}

			if (fornecedor.getText().isEmpty() || fornecedor.getText().equals(null) || descfor.getText().isEmpty()
					|| descfor.getText() == null || l == 0) {
				JOptionPane.showMessageDialog(null, "Dados em branco");
			} else {

				Boolean g = false;

				if (cadastro.getIdpdentc() == null) {
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

					c1.adiciona(cadastro, u);
					pedido.setText(cadastro.getNumdoc());

					if (idp == null) {

						repositorylog.adicionaLog(aces1.logincluir,
								"PEDIDOCOMPRAS " + " NUMERO " + cadastro.getNumdoc() + " FORNECEDOR "
										+ cadastro.getForn().getCODFOR().trim() + " "
										+ cadastro.getForn().getDESCFOR().trim(),
								cadastro.getIdpdentc(), u);
					} else {

						repositorylog.adicionaLog(aces1.logalterar,
								"PEDIDOCOMPRAS " + " NUMERO " + cadastro.getNumdoc() + " FORNECEDOR "
										+ cadastro.getForn().getCODFOR().trim() + " "
										+ cadastro.getForn().getDESCFOR().trim(),
								cadastro.getIdpdentc(), u);
					}

					List<Pdenti> listapdenti1 = new ArrayList<Pdenti>();

					for (int x = 0; x < listapdenti.getRowCount(); x++) {
						ciitem = new Pdenti();
						ciitem.setPedido(pedido.getText().trim());
						ciitem.setIdpdenti((Long) listapdenti.getValueAt(x, 0));
						ciitem.setItem(String.valueOf(listapdenti.getValueAt(x, 1)).trim());
						ciitem.setProduto(String.valueOf(listapdenti.getValueAt(x, 2)).trim());
						ciitem.setDescpro(String.valueOf(listapdenti.getValueAt(x, 3)).trim());
						ciitem.setUn(String.valueOf(listapdenti.getValueAt(x, 4)).trim());
						ciitem.setUnitario(
								Double.parseDouble(aces1.gravamoedadouble(listapdenti.getValueAt(x, 5).toString())));
						ciitem.setVrtot(
								Double.parseDouble(aces1.gravamoedadouble(listapdenti.getValueAt(x, 6).toString())));
						ciitem.setQuantidade(Double.parseDouble(listapdenti.getValueAt(x, 7).toString()));
						ciitem.setEmissao(cadastro.getEmissao());
						ciitem.setPedc(cadastro);
						listapdenti1.add(ciitem);

					}

					cdao.adiciona(listapdenti1, u);

					if (!pdentiremove.isEmpty()) {
						cdao.remove(pdentiremove, u);
					}

					dispose();
				}
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "ERRO AO GRAVAR O PEDIDO DE COMPRAS " + e1.getMessage());
			aces1.demologger.error("ERRO AO GRAVAR O PEDIDO DE COMPRAS " + e1.getMessage());
		}
	}

	public void gravaitem(Pdentc p, Pdenti pi, Acesso acesso) {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());

			Long idT = null;

			if (listapdenti.getValueAt(linhaSel, 1) != null) {
				if (listapdenti.getValueAt(linhaSel, 0) != null) {
					idT = Long.parseLong(listapdenti.getValueAt(linhaSel, 0).toString());
				}
			} else {
				idT = null;
			}

			Object item;
			if (listapdenti.getValueAt(linhaSel, 1) == null
					|| listapdenti.getValueAt(linhaSel, 1).toString().trim().isEmpty()) {
				item = String.valueOf("0");
			} else {
				item = listapdenti.getValueAt(linhaSel, 1).toString();
			}
			Object produto = listapdenti.getValueAt(linhaSel, 2);
			Object descpro = listapdenti.getValueAt(linhaSel, 3);
			Object un = listapdenti.getValueAt(linhaSel, 4);
			Object unitario = listapdenti.getValueAt(linhaSel, 5);
			Object vrtotal1 = listapdenti.getValueAt(linhaSel, 6);
			Object quantidade = listapdenti.getValueAt(linhaSel, 7);
			Object prazo = listapdenti.getValueAt(linhaSel, 8);

			try {
				if (idT == null) {
					if (produto != null && !String.valueOf(produto).isEmpty()) {
						idT = Long.parseLong("0");
					}
				}

				pi.setIdpdenti(idT);
				pi.setItem(String.valueOf(item));
				pi.setProduto(String.valueOf(produto));
				pi.setDescpro(String.valueOf(descpro));
				pi.setUn(String.valueOf(un));
				pi.setUnitario(Double.parseDouble(aces1.gravamoedadouble(unitario.toString())));
				pi.setVrtot(Double.parseDouble(aces1.gravamoedadouble(vrtotal1.toString())));
				pi.setQuantidade(Double.parseDouble(quantidade.toString()));

				new cadastracomprasi(p, ci, Integer.parseInt(item.toString())).setVisible(true);

				// passando parametro para alterar linha do jtable
				DecimalFormat formato = new DecimalFormat("#.##");
				idT = ci.getIdpdenti();
				item = ci.getItem();
				produto = ci.getProduto();
				descpro = ci.getDescpro();
				un = ci.getUn();
				unitario = ci.getUnitario();
				vrtotal1 = ci.getVrtot();
				quantidade = ci.getQuantidade();
				prazo = ci.getPrazo();

				if (ci.getItem() != null && !ci.getItem().trim().isEmpty()) {
					Object id = idT;
					listapdenti.setValueAt(id, linhaSel, 0);
					listapdenti.setValueAt(item, linhaSel, 1);
					listapdenti.setValueAt(produto, linhaSel, 2);
					listapdenti.setValueAt(descpro, linhaSel, 3);
					listapdenti.setValueAt(un, linhaSel, 4);
					listapdenti.setValueAt(currencyFormatter.format(unitario).toString().replace("R$", "").trim(),
							linhaSel, 5);
					listapdenti.setValueAt(currencyFormatter.format(vrtotal1).toString().replace("R$", "").trim(),
							linhaSel, 6);
					listapdenti.setValueAt(decimalqtde.format(quantidade), linhaSel, 7);
					listapdenti.setValueAt(prazo, linhaSel, 8);
					table.changeSelection(linhaSel, 0, false, false);
					qttotal.setText(quantidadeTotal());
					vrtotal.setText(aces1.formataMoeda(valorTotal()).replace("R$", ""));
					vrmercadoria.setText(aces1.formataMoeda(totalmercadoria()).replace("R$", ""));
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO AO GRAVAR O ITEM NO PEDIDO DE COMPAS " + e.getMessage());
				aces1.demologger.error("ERRO AO GRAVAR O ITEM NO PEDIDO DE COMPAS " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM PEDIDO DE COMPRAS !!!!!!!!!!");
		}

	}

}
