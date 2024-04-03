package Grafico.Pedidos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import entidades.Cadpro;
import entidades.Pdsaic;
import entidades.Pdsaii;
import filter.EntityManagerUtil;
import repositorios.LogusuRepository;
import Grafico.Cadcli.listadecliente;
import Grafico.Cadcli.listaprocuracliente;
import Grafico.Produto.listaprocuraproduto;
import Grafico.geral.MonetarioDocument;
import beans.AcessoBean;
import beans.CadproBean;
import beans.PdsaiiBean;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class cadastravendasi extends JDialog {
	private JPanel contentPane;
	private JTextField item,unidade,descpro,quantidade,unitario,vrmercadoria,produto;
	
	JButton btncadastrar, btnlimpar;
	
	PdsaiiBean c = new PdsaiiBean();
	
	Cadpro p = new Cadpro();
	CadproBean pbean = new CadproBean();
	AcessoBean aces1 = new AcessoBean();
	Double precop = 0.0;
	
	Locale locale = new Locale("pt", "BR");
	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public cadastravendasi(final Pdsaic c2, final Pdsaii ci, int itemp, DefaultTableModel listapdsaii)
			throws Exception {

		setTitle("VENDAS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.imagemicone()));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(10, 5, 546, 295);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);
		int novoitem = itemp + 1;
		setPrecop(0.0);
		
		JLabel lblitem = new JLabel("ITEM");
		aces1.padraojlabel(lblitem);;
		lblitem.setBounds(10, 13, 41, 14);
		contentPane.add(lblitem);

		item = new JTextField("" + novoitem);
		item.setBounds(145, 10, 24, 20);
		aces1.bloqueado(item);
		contentPane.add(item);

		JLabel lblproduto = new JLabel("PRODUTO");
		aces1.padraojlabel(lblproduto);
		lblproduto.setBounds(10, 38, 63, 14);
		contentPane.add(lblproduto);
		
		produto = new JTextField();
		produto.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		produto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				validaproduto(produto.getText());
			}
		});
		produto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB) {
					p = validaproduto(produto.getText());
					setPrecop(p.getVRVENDA());

				}

				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					if (produto.getText() == null || produto.getText().isEmpty()) {
						try {
							new listaprocuraproduto(p).setVisible(true);
							if (p.getIdcadpro() != null) {
								produto.setText(p.getCODPRO());
								descpro.setText(p.getDESCPRO());
								aces1.moedabanco(p.getVRVENDA(), unitario);
								unidade.setText(p.getUN());
								setPrecop(p.getVRVENDA());
								aces1.liberado(unidade);
								aces1.liberado(quantidade);
								aces1.bloqueado(produto);
								quantidade.requestFocus();
								quantidade.selectAll();
							} else {
								produto.requestFocus();
							}
						} catch (Exception e) {
							
							JOptionPane.showMessageDialog(null, "ERRO AO TENTAR INCLUIR O ITEM NA TELA DO PEDIDO DE VENDAS " + e.getMessage());
							aces1.demologger.error("ERRO AO TENTAR INCLUIR O ITEM NA TELA DO PEDIDO DE VENDAS " + e.getMessage());
						}
					} else {
						p = validaproduto(produto.getText());
						setPrecop(p.getVRVENDA());
					}

				}
			}

		});
		produto.setBounds(145, 35, 220, 20);
		aces1.numeroscomponto(produto);
		aces1.liberado(produto);
		contentPane.add(produto);

		JLabel lbldescricao = new JLabel("DESCRIÇÃO");
		aces1.padraojlabel(lbldescricao);
		lbldescricao.setBounds(10, 63, 86, 14);
		contentPane.add(lbldescricao);
		
		descpro = new JTextField();
		aces1.bloqueado(descpro);
		descpro.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descpro.setBounds(145, 60, 377, 20);
		contentPane.add(descpro);
		
		JLabel lblunidade = new JLabel("UNIDADE");
		aces1.padraojlabel(lblunidade);
		lblunidade.setBounds(10, 88, 80, 14);
		contentPane.add(lblunidade);

		unidade = new JTextField();
		aces1.bloqueado(unidade);
		unidade.setBounds(145, 85, 35, 20);
		contentPane.add(unidade);
		
		JLabel lblquantidade = new JLabel("QUANTIDADE");
		aces1.padraojlabel(lblquantidade);
		lblquantidade.setBounds(10, 113, 105, 14);
		contentPane.add(lblquantidade);

		quantidade = new JTextField();
		quantidade.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		quantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
					if (quantidade.getText().equals("") || quantidade.getText().equals(null)
							|| quantidade.getText().equals("0")) {
						JOptionPane.showMessageDialog(null, "Digite um Valor!!!!");
						aces1.bloqueado(unitario);
						quantidade.requestFocus();

					} else {
						Double qtdep = 0.0, qtdep1 = 0.0;

						qtdep = Double.parseDouble(quantidade.getText().trim());
						for (int x = 0; x < listapdsaii.getRowCount(); x++) {
							if (listapdsaii.getValueAt(x, 2) != null
									&& !listapdsaii.getValueAt(x, 2).toString().isEmpty()) {
								if (listapdsaii.getValueAt(x, 0) == null) {
									if (listapdsaii.getValueAt(x, 2).toString().equals(produto.getText())) {
										qtdep = qtdep + Double.parseDouble(listapdsaii.getValueAt(x, 7).toString());
										qtdep1 = qtdep1 + Double.parseDouble(listapdsaii.getValueAt(x, 7).toString());
									}
								}

							}
						}
						if (Verificaqtde(p.getQTATUAL(), qtdep, qtdep1) == true) {
							aces1.bloqueado(quantidade);
							unitario.requestFocus();
							aces1.liberado(unitario);
							unitario.selectAll();
						} else {
							aces1.bloqueado(unitario);
							quantidade.requestFocus();
						}
					}

				}
			}
		});
		quantidade.setBounds(145, 110, 97, 20);
		contentPane.add(quantidade);
		aces1.bloqueado(quantidade);
		aces1.numeros(quantidade);
		quantidade.setColumns(10);

		JLabel lblunitario = new JLabel("UNIT\u00C1RIO");
		aces1.padraojlabel(lblunitario);
		lblunitario.setBounds(10, 138, 86, 14);
		contentPane.add(lblunitario);

		unitario = new JTextField();
		unitario.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		unitario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
					if (unitario.getText().equals(null) || unitario.getText().trim().isEmpty() || unitario.getText().equals("0,00")) {
						JOptionPane.showMessageDialog(null, "Digite um Valor!!!!");
						aces1.bloqueado(vrmercadoria);
						btncadastrar.setEnabled(false);
						unitario.requestFocus();
					} else {
						Double precof = 0.0;
						Double preco1 = (getPrecop() / 100) * 5;
						precof = getPrecop() - preco1;

							double unitario1 = Double.parseDouble(aces1.gravamoedadouble(unitario.getText().trim()));
							double quantidade1 = Double.parseDouble(quantidade.getText().replace(",", ".").trim());
							double valor = unitario1 * quantidade1;
							vrmercadoria.setText(currencyFormatter.format(valor).toString().replace("R$", "").trim());
							aces1.bloqueado(unitario);
							btncadastrar.setEnabled(true);
							btncadastrar.requestFocus();
					}
				}
			}
		});
		aces1.bloqueado(unitario);
		unitario.setDocument(new MonetarioDocument());
		unitario.setBounds(145, 135, 97, 20);
		contentPane.add(unitario);

		JLabel lblvalormercadoria = new JLabel("VALOR MERCADORIA");
		aces1.padraojlabel(lblvalormercadoria);
		lblvalormercadoria.setBounds(10, 163, 131, 14);
		contentPane.add(lblvalormercadoria);

		vrmercadoria = new JTextField();
		aces1.bloqueado(vrmercadoria);
		vrmercadoria.setBounds(145, 160, 97, 20);
		contentPane.add(vrmercadoria);


		btncadastrar = new JButton();
		btncadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				gravaitem(ci);
			}
		});
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					gravaitem(ci);

				}
			}
		});
		btncadastrar.setEnabled(false);
		btncadastrar.setBounds(205, 200, 55, 46);
		btncadastrar.setIcon(new ImageIcon(cadastravendasi.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		contentPane.add(btncadastrar);

		btnlimpar = new JButton();
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ci.setItem("");
				ci.setProduto("");
				dispose();
			}
		});
		btnlimpar.setBounds(283, 200, 55, 46);
		btnlimpar.setIcon(new ImageIcon(cadastravendasi.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnlimpar);
		contentPane.add(btnlimpar);

		if (ci.getIdpdsaii() != null) {

			item.setText(ci.getItem().toString());
			produto.setText(ci.getProduto());
			unidade.setText(ci.getUn());
			quantidade.setText(String.format("%.0f", ci.getQuantidade()));
			descpro.setText(ci.getDescpro());
			aces1.moedabanco(ci.getUnitario(), unitario);
			vrmercadoria.setText(aces1.formataMoeda(ci.getVrtot()).replace("R$", ""));
			aces1.bloqueado(produto);
			aces1.bloqueado(quantidade);
			aces1.bloqueado(unitario);
		}

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				if (ci.getIdpdsaii() == null) {
					produto.requestFocus();
					produto.setCaretPosition(0);
				} else {
					btnlimpar.requestFocus();
					// quantidade.selectAll();
				}
			}

			public void windowClosing(WindowEvent e) {
				ci.setItem("");

				// c.fechaconexao();
				dispose();

			}
		});

		/// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ci.setIdpdsaii(null);
				ci.setItem("");
				ci.setProduto("");
				ci.setDescpro("");
				ci.setUn("");
				ci.setQuantidade(0.0);
				ci.setUnitario(0.0);
				ci.setVrtot(0.0);
				// c.fechaconexao();
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

	public Cadpro validaproduto(String prod) {
		Cadpro cadpron = new Cadpro();
		if (prod.equals("") || prod.equals(null)) {
			JOptionPane.showMessageDialog(null, "Digite um Valor!!!!");
			aces1.bloqueado(unidade);
			aces1.bloqueado(quantidade);
			aces1.bloqueado(vrmercadoria);
			btncadastrar.setEnabled(false);
			produto.requestFocus();
		} else {

			cadpron = c.Validapro(prod);
			if (cadpron.getIdcadpro() != null) {
				if (cadpron.getATIVO() == true) {
					JOptionPane.showMessageDialog(null, "Produto Desativado");
					aces1.bloqueado(unidade);
					aces1.bloqueado(quantidade);
					produto.requestFocus();

				} else {
					descpro.setText(cadpron.getDESCPRO());
					unidade.setText(cadpron.getUN());
					aces1.moedabanco(cadpron.getVRVENDA(), unitario);
					setPrecop(p.getVRVENDA());
					aces1.liberado(unidade);
					aces1.liberado(quantidade);
					quantidade.requestFocus();
					aces1.bloqueado(produto);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Produto não encontrado");
				aces1.bloqueado(unidade);
				aces1.bloqueado(quantidade);
				produto.requestFocus();
			}
		}
		return cadpron;

	}

	public void gravaitem(Pdsaii p) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		
		try {
			if (!btncadastrar.isEnabled()) {

			} else {
				p.setItem(item.getText().trim());
				p.setProduto(produto.getText().trim());
				p.setDescpro(descpro.getText().trim());
				p.setUn(unidade.getText().trim());
				p.setQuantidade(Double.parseDouble(quantidade.getText().trim()));
				p.setUnitario(Double.parseDouble(aces1.gravamoedadouble(unitario.getText().trim())));
				p.setVrtot(Double.parseDouble(aces1.gravamoedadouble(vrmercadoria.getText().trim())));

			}
			if (p.getProduto().isEmpty() || p.getUnitario() == null || p.getQuantidade() == null) {
				JOptionPane.showMessageDialog(null, "Dados em branco");
				quantidade.requestFocus();
				btncadastrar.setEnabled(false);
			} else {
				item.setText("");
				produto.setText("");
				vrmercadoria.setText("");
				unidade.setText("");
				quantidade.setText("");
				descpro.setText("");
				unitario.setText("");
				produto.setText("");
				dispose();
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "ERRO ITEM NA LISTA PEDIDO DE VENDAS " + e1.getMessage());
			aces1.demologger.error("ERRO ITEM NA LISTA PEDIDO DE VENDAS " + e1.getMessage());
		}

	}

	public Boolean Verificaqtde(Double qtdepro, Double qtde, Double qtdeparam) {
		Boolean valida = true;
		Double qtdeatual = qtdepro;
		if (qtde > qtdepro) {
			valida = false;
			qtdeatual = qtdepro - qtdeparam;

			JOptionPane.showMessageDialog(null,
					"PRODUTO NÃO POSSUI SALDO SUFICIENTE, SALDO ATUAL " + String.format("%.0f", qtdeatual));
		}
		return valida;

	}

	public Double getPrecop() {
		return precop;
	}

	public void setPrecop(Double precop) {
		this.precop = precop;
	}

}
