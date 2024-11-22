package Grafico.serviços;

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
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;

import repositorios.LogusuRepository;
import entidades.Acesso;
import entidades.Cadcli;
import entidades.Cadosc;
import entidades.Cadosi;
import entidades.Usuario;
import filter.EntityManagerUtil;
import Grafico.Cadcli.listadecliente;
import Grafico.Cadcli.listaprocuracliente;
import Grafico.geral.MonetarioDocument;
import beans.AcessoBean;
import beans.CadcliBean;
import beans.CadoscBean;
import beans.CadosiBean;

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
import javax.swing.text.Document;

@SuppressWarnings("serial")
public class cadastroordemservc extends JDialog {
	private JPanel contentPane;
	private JTable table;
	
	private JLabel lblnumero,lblcliente,lblemissao,lblvrtotal,lblformpagto,lblmodelo,lblimei,lblobs,lblobscliente;
	
	private JTextField ordemservico, emissao, desccli, cliente, vrtotal;

	JComboBox formapagamento;

	private JTextArea obs,obscliente;

	JButton btncadastrar;

	CadoscBean ordembean = new CadoscBean();
	Cadosc ordem = new Cadosc();

	Cadcli cli = new Cadcli();
	Cadcli cadclip = new Cadcli();
	CadcliBean clibean = new CadcliBean();

	Cadosi itemordem = new Cadosi();
	CadosiBean itemordembean = new CadosiBean();

	AcessoBean aces1 = new AcessoBean();
	private Long idp;
	List<Cadosi> cadosiremove = new ArrayList<Cadosi>();

	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	DecimalFormat decimal = new DecimalFormat("0.00");
	DecimalFormat decimalqtde = new DecimalFormat("0");

	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listacadosi = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
	private JTextField modelo;
	private JTextField imei;

	@SuppressWarnings("unchecked")
	public cadastroordemservc(final Cadosc p, Usuario u, Acesso acesso) throws Exception {
		idp = p.getId();
		cadclip = p.getCliente();
		setTitle("ORDEM DE SERVIÇO");
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

		lblnumero = new JLabel("NUMERO");
		aces1.padraojlabel(lblnumero);
		lblnumero.setBounds(36, 5, 55, 14);
		contentPane.add(lblnumero);

		ordemservico = new JTextField();
		aces1.bloqueado(ordemservico);
		ordemservico.setBounds(10, 19, 100, 20);
		contentPane.add(ordemservico);

		lblcliente = new JLabel("CLIENTE");
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
							new listaprocuracliente(cli).setVisible(true);
						} else {
							validacliente();
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO AO VALIDAR O CLIENTE " + e.getMessage());
						aces1.demologger.error("ERRO AO VALIDAR O CLIENTE " + e.getMessage());
					}
					if (cli.getIdcadcli() != 0) {
						cadclip = clibean.procura(cli.getIdcadcli());
						cliente.setText(cadclip.getCODCLI());
						desccli.setText(cadclip.getDESCCLI());
						aces1.bloqueado(emissao);
						try {
							emissao.setText(aces1.retornadatastring(aces1.dataatual()));
						} catch (ParseException e) {
						}
						aces1.bloqueado(cliente);
						aces1.bloqueado(desccli);
						table.changeSelection(0, 0, false, false);
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
		desccli.setBounds(220, 19, 270, 20);
		contentPane.add(desccli);

		lblemissao = new JLabel("EMISSÃO");
		aces1.padraojlabel(lblemissao);
		lblemissao.setBounds(515, 5, 60, 14);
		contentPane.add(lblemissao);

		javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
		emissao = new javax.swing.JFormattedTextField(data);
		aces1.bloqueado(emissao);
		aces1.numeros(emissao);
		emissao.setBounds(495, 19, 97, 20);
		contentPane.add(emissao);

		table = new JTable(listacadosi) {
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
				if (evt.getKeyCode() == KeyEvent.VK_INSERT) {
					try {
						int item = 0;

						if (listacadosi.getRowCount() == 1) {
							int linha = table.convertRowIndexToModel(table.getSelectedRow());
							if (listacadosi.getValueAt(linha, 2) == null
									|| listacadosi.getValueAt(linha, 2).toString().isEmpty()) {
								listacadosi.removeRow(linha);
							} else {
								item = listacadosi.getRowCount();
							}
						} else {
							item = listacadosi.getRowCount();
						}
						Cadosi pi = new Cadosi();
						new cadastraordemservi(p, pi, item, listacadosi).setVisible(true);
						if (pi.getItem() != null && !pi.getItem().toString().isEmpty()) {
							String iditem = itemordem.getItem().toString() + 1;
							listacadosi.addRow(new Object[] { null, pi.getItem(), pi.getDescricao(),
									aces1.valordinheiro(pi.getVrtot())});
							vrtotal.setText(aces1.valordinheiro(valorTotal()));
							table.setRowSelectionAllowed(true);
							int linha = listacadosi.getRowCount();
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
					aces1.liberado(modelo);
					modelo.requestFocus();
				}

				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					gravaitem(p, itemordem);
				}

				if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
					if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
						int linha = table.convertRowIndexToModel(table.getSelectedRow());
						List<Cadosi> listacadosi1 = new ArrayList<Cadosi>();
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
								if (listacadosi.getValueAt(linha, 0) != null) {
									Cadosi removeitem = new Cadosi();
									removeitem = itemordembean
											.procura(Long.parseLong(listacadosi.getValueAt(linha, 0).toString()));
									cadosiremove.add(removeitem);
								}
								listacadosi.removeRow(linha);

								for (int x = 0; x < listacadosi.getRowCount(); x++) {
									int soma = x + 1;

									Cadosi mudaitem = new Cadosi();

									if (listacadosi.getValueAt(x, 0) != null) {
										mudaitem.setId(Long.parseLong(listacadosi.getValueAt(x, 0).toString()));
									}

									mudaitem.setItem(String.valueOf(soma));
									mudaitem.setDescricao(String.valueOf(listacadosi.getValueAt(x, 2).toString()));
									mudaitem.setVrtot(Double.parseDouble(
											aces1.gravamoedadouble(listacadosi.getValueAt(x, 3).toString())));
									mudaitem.setNumdoc(ordemservico.getText());
									listacadosi1.add(mudaitem);
									mudaitem = new Cadosi();

								}
								itemordembean.acertaitem(listacadosi1, u);
								listacadosi.setRowCount(0);

								for (Cadosi com : listacadosi1) {
									listacadosi.addRow(new Object[] { com.getId(), com.getItem(),
											 com.getDescricao(), aces1.valordinheiro(com.getVrtot())});

								}

								table.setModel(listacadosi);
								table.changeSelection(linha, 0, false, false);
								vrtotal.setText(aces1.valordinheiro(valorTotal()));
								table.changeSelection(0, 0, false, false);

							}

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "ERRO AO TENTAR EXCLUIR O ITEM " + e1.getMessage());
							aces1.demologger.error("ERRO AO TENTAR EXCLUIR O ITEM " + e1.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(null, "SELECIONE UMA ORDEM DE SERVIÇO !!!!!!!");
					}
				}

			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					gravaitem(p, itemordem);

				}
			}

		});
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		scrollPane_1.setViewportView(table);

		listacadosi.addColumn("id");
		listacadosi.addColumn("ITEM");
		listacadosi.addColumn("DESCRIÇÂO");
		listacadosi.addColumn("VALORTOTAL");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela(p.getId());

		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(570);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		lblvrtotal = new JLabel("VALOR TOTAL");
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
		vrtotal.setBounds(570, 269, 149, 35);
		contentPane.add(vrtotal);
		
		lblmodelo = new JLabel("MODELO");
		aces1.padraojlabel(lblmodelo);
		lblmodelo.setBounds(55, 255, 70, 14);
		contentPane.add(lblmodelo);
		
		modelo = new JTextField();
		modelo.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		modelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					aces1.liberado(imei);
					imei.requestFocus();
				}
			}
		});
		aces1.bloqueado(modelo);
		modelo.setBounds(7, 269, 150, 20);
		contentPane.add(modelo);
		
		lblimei = new JLabel("NUMERO DE SERIE");
		aces1.padraojlabel(lblimei);
		lblimei.setBounds(183, 255, 130, 14);
		contentPane.add(lblimei);
		
		imei = new JTextField();
		imei.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		imei.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					aces1.liberadoJComboBox(formapagamento);
					formapagamento.requestFocus();
				}
			}
		});
		aces1.bloqueado(imei);
		imei.setBounds(167, 269, 150, 20);
		contentPane.add(imei);
		
		lblformpagto = new JLabel("FORMA PAGAMENTO");
		aces1.padraojlabel(lblformpagto);
		lblformpagto.setBounds(333, 255, 128, 14);
		contentPane.add(lblformpagto);

		formapagamento = new JComboBox();
		formapagamento.setModel(new DefaultComboBoxModel(aces1.listaformapagamento()));
		formapagamento.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		formapagamento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent etx) {
				if (etx.getKeyCode() == KeyEvent.VK_TAB || etx.getKeyCode() == KeyEvent.VK_ENTER) {
					aces1.liberadojTextArea(obs);
					obs.requestFocus();

				}
			}
		});
		aces1.bloqueadoJComboBox(formapagamento);
		formapagamento.setBounds(330, 269, 130, 20);
		contentPane.add(formapagamento);
		
		lblobs = new JLabel("OBSERVAÇÃO");
		aces1.padraojlabel(lblobs);
		lblobs.setBounds(145, 310, 90, 14);
		contentPane.add(lblobs);

		obs = new JTextArea(new TTextAreaDocument(400));
		obs.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		obs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					aces1.liberadojTextArea(obscliente);
					obscliente.requestFocus();
				}
			}
		});
		aces1.bloqueadojTextArea(obs);
		obs.setBounds(4, 324, 357, 115);
		contentPane.add(obs);
		
		lblobscliente = new JLabel("RELATO DO CLIENTE");
		aces1.padraojlabel(lblobscliente);
		lblobscliente.setBounds(490, 310, 159, 14);
		contentPane.add(lblobscliente);
		
		obscliente = new JTextArea(new TTextAreaDocument(400));
		obscliente.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		obscliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					btncadastrar.setEnabled(true);
					btncadastrar.requestFocus();
				}
			}
		});
		aces1.bloqueadojTextArea(obscliente);
		obscliente.setBounds(367, 324, 357, 115);
		contentPane.add(obscliente);


		btncadastrar = new JButton();
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravaordem(acesso, u);
				}
			}
		});
		btncadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				gravaordem(acesso, u);
			}
		});
		btncadastrar.setEnabled(false);
		btncadastrar.setBounds(300, 450, 55, 46);
		btncadastrar.setIcon(new ImageIcon(listadeordemserviços.class.getResource("/imagens/salvar.png")));
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
					table.changeSelection(0, 0, false, false);
				}

			}
		});
		btncancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btncancelar.setIcon(new ImageIcon(listadeordemserviços.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btncancelar);
		btncancelar.setBounds(370, 450, 55, 46);
		contentPane.add(btncancelar);
		
		if (p.getId() != null) {

			cadclip = p.getCliente();
			cliente.setText(p.getCliente().getCODCLI().trim());
			desccli.setText(p.getCliente().getDESCCLI().trim());
			emissao.setText(aces1.retornadatastring(p.getEmissao()));
			ordemservico.setText(p.getNumdoc().trim());
			vrtotal.setText(aces1.valordinheiro(valorTotal()));
			modelo.setText(p.getModelo().trim());
			imei.setText(p.getImei().trim());
			formapagamento.setSelectedItem(p.getFormpagto().trim());
			obs.setText(p.getObs().trim());
			obscliente.setText(p.getObscliente().trim());
			

			aces1.bloqueado(cliente);
			aces1.bloqueado(emissao);
			aces1.liberado(modelo);
			aces1.liberado(imei);
			aces1.liberadoJComboBox(formapagamento);
			aces1.liberadojTextArea(obs);
			aces1.liberadojTextArea(obscliente);
			btncadastrar.setEnabled(true);

		}

		if (listacadosi.getRowCount() == 0) {
			Cadosi pi = new Cadosi();
			listacadosi.addRow(new Object[] { pi.getId(), pi.getItem(), pi.getDescricao(),
					 pi.getVrtot()});
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
				ordemservico.setText("");
				vrtotal.setText("");
				obs.setText("");
				itemordem = new Cadosi();
				p.setId(null);
				// c1.fechaconexao();
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				if (p.getId() == null) {
					cliente.requestFocus();
					cliente.setCaretPosition(0);
				} else {
					obs.requestFocus();
					obs.setCaretPosition(0);
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

		CadosiBean lista = new CadosiBean();
		if (idpedido != null) {
			List<Cadosi> list = lista.getCadosis(idpedido);

			for (Cadosi com : list) {
				listacadosi.addRow(new Object[] { com.getId(), com.getItem(),
						com.getDescricao().trim(),aces1.valordinheiro(com.getVrtot())});

			}
		}

	}

	private Double valorTotal() {
		DecimalFormat decimal = new DecimalFormat("0.00");
		Double Orcamento = 0.0;
		for (int i = 0; i < listacadosi.getRowCount(); i++) {
			Orcamento += Double
					.parseDouble((listacadosi.getValueAt(i, 3).toString().replace(".", "").replace(",", ".")));
		}

		return Orcamento;
	}

	private void gravaordem(Acesso acesso, Usuario u1) {
		Cadosc cadastro = new Cadosc();
		Cadosi ciitem = new Cadosi();
		try {

			if (idp != null) {
				cadastro = ordembean.procura(idp);
			}

			cadastro.setEmissao(aces1.retornadata(emissao.getText()));
			cadastro.setNumdoc(ordemservico.getText().trim());
			cadastro.setVrtot(Double.parseDouble(aces1.gravamoedadouble(vrtotal.getText())));
			cadastro.setCliente(cadclip);
			cadastro.setObs(obs.getText().trim());
			cadastro.setFormpagto(formapagamento.getSelectedItem().toString().trim());
			cadastro.setModelo(modelo.getText().trim());
			cadastro.setImei(imei.getText().trim());
			cadastro.setObscliente(obscliente.getText().trim());
			

			int l = 0;

			for (int x = 0; x < listacadosi.getRowCount(); x++) {
				if (listacadosi.getValueAt(x, 2) != "") {
					l++;
				}

			}

			if (cliente.getText().isEmpty() || cliente.getText().equals(null) || desccli.getText().isEmpty()
					|| desccli.getText().equals(null) || l == 0 || obscliente.getText().isEmpty() || obscliente.getText().equals(null)) {
				JOptionPane.showMessageDialog(null, "EXISTE DADOS OBRIGATORIOS SEM PREEENCHIMENTO !!!!!!!");
			} else {

				Boolean g = false;

				if (cadastro.getId() == null) {
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
					ordembean.adiciona(cadastro, u1);
					ordemservico.setText(cadastro.getNumdoc());

					if (idp == null) {

						repositorylog.adicionaLog(aces1.logincluir,
								"ORDEMSERVIÇO NUMERO " + cadastro.getNumdoc() + " CLIENTE "
										+ cadastro.getCliente().getCODCLI().trim() + " "
										+ cadastro.getCliente().getDESCCLI().trim(),
								cadastro.getId(), u1);
					} else {

						repositorylog.adicionaLog(aces1.logalterar,
								"ORDEMSERVIÇO NUMERO " + cadastro.getNumdoc() + " CLIENTE "
										+ cadastro.getCliente().getCODCLI().trim() + " "
										+ cadastro.getCliente().getDESCCLI().trim(),
								cadastro.getId(), u1);
					}

					List<Cadosi> listacadosi1 = new ArrayList<Cadosi>();

					for (int x = 0; x < listacadosi.getRowCount(); x++) {
						ciitem.setNumdoc(ordemservico.getText());
						ciitem.setId((Long) listacadosi.getValueAt(x, 0));
						ciitem.setItem(listacadosi.getValueAt(x, 1).toString());
						ciitem.setDescricao(String.valueOf(listacadosi.getValueAt(x, 2)));
						ciitem.setVrtot(
								Double.parseDouble(aces1.gravamoedadouble(listacadosi.getValueAt(x, 3).toString())));
						ciitem.setEmissao(cadastro.getEmissao());
						listacadosi1.add(ciitem);
						ciitem.setOrdemc(cadastro);

						ciitem = new Cadosi();
					}

					itemordembean.adiciona(listacadosi1, u1);

					if (!cadosiremove.isEmpty()) {
						itemordembean.remove(cadosiremove, u1);
					}

					dispose();
				}
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "ERRO GRAVA ORDEM DE SERVIÇO " + e1.getMessage());
			aces1.demologger.error("ERRO GRAVA ORDEM DE SERVIÇO " + e1.getMessage());
		}
	}

	public void gravaitem(Cadosc p, Cadosi pi) {

		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());

			Long idT = null;

			if (listacadosi.getValueAt(linhaSel, 1) != null) {
				if (listacadosi.getValueAt(linhaSel, 0) != null) {
					idT = Long.parseLong(listacadosi.getValueAt(linhaSel, 0).toString());
				}
			} else {
				idT = null;
			}

			Object item;
			if (listacadosi.getValueAt(linhaSel, 1) == null
					|| listacadosi.getValueAt(linhaSel, 1).toString().isEmpty()) {
				item = 0;
			} else {
				item = listacadosi.getValueAt(linhaSel, 1);

			}

			Object descricao = listacadosi.getValueAt(linhaSel, 2);
			Object vrtotal1 = listacadosi.getValueAt(linhaSel, 3);

			try {
				if (idT == null) {
					if (descricao != null && !String.valueOf(descricao).isEmpty()) {
						idT = Long.parseLong("0");
					}
				}

				pi.setId(idT);
				pi.setItem(item.toString());
				pi.setDescricao(String.valueOf(descricao));
				pi.setVrtot(Double.parseDouble(aces1.gravamoedadouble(vrtotal1.toString())));

				new cadastraordemservi(p, itemordem, Integer.parseInt(item.toString()), listacadosi).setVisible(true);

				DecimalFormat formato = new DecimalFormat("#.##");
				idT = itemordem.getId();
				item = itemordem.getItem();
				descricao = itemordem.getDescricao();
				vrtotal1 = itemordem.getVrtot();

				if (itemordem.getItem() != null && !itemordem.getItem().toString().isEmpty()) {
					Object id = idT;
					listacadosi.setValueAt(id, linhaSel, 0);
					listacadosi.setValueAt(item, linhaSel, 1);
					listacadosi.setValueAt(descricao, linhaSel, 2);
					listacadosi.setValueAt(aces1.valordinheiro(Double.parseDouble(vrtotal1.toString())), linhaSel, 3);
					table.changeSelection(linhaSel, 0, false, false);
					vrtotal.setText(aces1.valordinheiro(valorTotal()));
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO AO ALTERAR ITEM ORDEM SERVIÇO " + e.getMessage());
				aces1.demologger.error("ERRO AO ALTERAR ITEM ORDEM SERVIÇO " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "SELECIONE UMA ORDEM DE SERVIÇO PARA VISUALIZAR !!!");
		}

	}

	public void validacliente() {

		Cadcli c2 = new Cadcli();

		c2 = ordembean.Validacao(cliente.getText());

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
				table.changeSelection(0, 0, false, false);
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
