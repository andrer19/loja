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
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import entidades.Cadpro;
import entidades.Pdentc;
import entidades.Pdenti;
import filter.EntityManagerUtil;
import repositorios.LogusuRepository;
import Grafico.Cadcli.listadecliente;
import Grafico.Cadcli.listaprocuracliente;
import Grafico.Produto.listaprocuraproduto;
import Grafico.geral.MonetarioDocument;
import beans.AcessoBean;
import beans.CadproBean;
import beans.PdentiBean;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class cadastracomprasi extends JDialog {
	private JPanel contentPane;
	private JTextField item, unidade, descpro, quantidade, unitario, vrmercadoria, produto, unitariovalor;

	JButton btncadastrar, btnlimpar;

	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	Double precop = 0.0;

	PdentiBean c = new PdentiBean();

	Cadpro p = new Cadpro();
	CadproBean pbean = new CadproBean();
	AcessoBean aces1 = new AcessoBean();

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public cadastracomprasi(final Pdentc c2, final Pdenti ci, int itemp) throws Exception {

		setTitle("ITEM DE COMPRA");
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

		JLabel lblitem = new JLabel("ITEM");
		aces1.padraojlabel(lblitem);
		lblitem.setBounds(10, 13, 41, 14);
		contentPane.add(lblitem);

		item = new JTextField("" + novoitem);
		aces1.bloqueado(item);
		item.setBounds(145, 10, 24, 20);
		contentPane.add(item);

		JLabel lblproduto = new JLabel("PRODUTO");
		aces1.padraojlabel(lblproduto);
		lblproduto.setBounds(10, 38, 80, 14);
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
					validaproduto(produto.getText());

				}

				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					if (produto.getText() == null || produto.getText().isEmpty()) {
						try {
							new listaprocuraproduto(p).setVisible(true);
							if (p.getIdcadpro() != null) {
								produto.setText(p.getCODPRO());
								descpro.setText(p.getDESCPRO());
								unitario.setText(aces1.valordinheiro(p.getVRCOMPRA()));
								unidade.setText(p.getUN());
								setPrecop(p.getVRCOMPRA());
								aces1.liberado(quantidade);
								aces1.bloqueado(produto);
								quantidade.requestFocus();
								quantidade.selectAll();
							} else {
								produto.requestFocus();
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "ERRO AO LISTAR O PRODUTO PEDIDO DE COMPRAS " + e.getMessage());
							aces1.demologger.error("ERRO AO LISTAR O PRODUTO PEDIDO DE COMPRAS " + e.getMessage());
						}
					} else {
						validaproduto(produto.getText());
					}

				}
			}

		});
		produto.setBounds(145, 35, 220, 20);
		aces1.numeroscomponto(produto);
		aces1.liberado(produto);
		contentPane.add(produto);

		JLabel lbldescricao = new JLabel("DESCRI\u00C7\u00C3O");
		aces1.padraojlabel(lbldescricao);
		lbldescricao.setBounds(10, 63, 86, 14);
		contentPane.add(lbldescricao);

		descpro = new JTextField();
		aces1.bloqueado(descpro);
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
					validaquantidade();
				}
			}
		});
		quantidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validaquantidade();
			}

			@Override
			public void focusGained(FocusEvent e) {
				quantidade.selectAll();
			}
		});
		quantidade.setBounds(145, 110, 97, 20);
		contentPane.add(quantidade);
		aces1.bloqueado(quantidade);
		aces1.numeroscomvirgula(quantidade);
		quantidade.setColumns(10);

		JLabel lblunitario = new JLabel("UNITÁRIO");
		aces1.padraojlabel(lblunitario);
		lblunitario.setBounds(10, 138, 86, 14);
		contentPane.add(lblunitario);

		unitario = new JTextField();
		unitario.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		unitario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
					validaunitario();
				}
			}
		});
		unitario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validaunitario();
			}

			@Override
			public void focusGained(FocusEvent e) {
				unitario.selectAll();
			}
		});
		aces1.bloqueado(unitario);
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
		btncadastrar.setIcon(new ImageIcon(cadastracomprasi.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		contentPane.add(btncadastrar);

		btnlimpar = new JButton();
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ci.setIdpdenti(null);
				ci.setProduto("");
				ci.setItem(null);
				dispose();
			}
		});
		btnlimpar.setBounds(283, 200, 55, 46);
		btnlimpar.setIcon(new ImageIcon(cadastracomprasi.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnlimpar);
		contentPane.add(btnlimpar);

		if (ci.getIdpdenti() != null) {

			item.setText(ci.getItem());
			produto.setText(ci.getProduto());
			unidade.setText(ci.getUn());
			quantidade.setText(aces1.mascaraquantidadecomvirgula(ci.getQuantidade()));
			descpro.setText(ci.getDescpro());
			unitario.setText(aces1.valordinheiro(ci.getUnitario()));
			vrmercadoria.setText(aces1.valordinheiro(ci.getVrtot()));
			aces1.bloqueado(produto);
			aces1.bloqueado(quantidade);
			aces1.bloqueado(unitario);
		}

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				if (ci.getIdpdenti() == null) {
					produto.requestFocus();
					produto.setCaretPosition(0);
				} else {
					btnlimpar.requestFocus();
					// quantidade.selectAll();
				}
			}

			public void windowClosing(WindowEvent e) {
				ci.setIdpdenti(null);
				ci.setItem(null);
				dispose();

			}
		});

		/// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ci.setIdpdenti(null);
				ci.setItem("");
				ci.setProduto("");
				ci.setDescpro("");
				ci.setUn("");
				ci.setQuantidade(null);
				ci.setUnitario(null);
				ci.setVrtot(null);
				ci.setPrazo(null);
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

	public Boolean validaproduto(String prod) {
		Boolean valida = false;
		if (prod.equals(null) || prod.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite um Valor!!!!");
			aces1.bloqueado(unidade);
			aces1.bloqueado(quantidade);
			aces1.bloqueado(vrmercadoria);
			btncadastrar.setEnabled(false);
			produto.requestFocus();
		} else {
			Cadpro cadpron = new Cadpro();
			cadpron = pbean.buscaproduto(prod);
			if (cadpron.getIdcadpro() != null) {
				if (cadpron.getATIVO() == true) {
					JOptionPane.showMessageDialog(null, "Produto Desativado");
					aces1.bloqueado(unidade);
					aces1.bloqueado(quantidade);
					produto.requestFocus();

				} else {
					descpro.setText(cadpron.getDESCPRO());
					unidade.setText(cadpron.getUN());
					unitario.setText(aces1.valordinheiro(cadpron.getVRCOMPRA()));
					setPrecop(cadpron.getVRCOMPRA());
					aces1.liberado(unidade);
					aces1.liberado(quantidade);
					quantidade.requestFocus();
					aces1.bloqueado(produto);
					valida = true;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Produto Não encontrado");
				aces1.bloqueado(unidade);
				aces1.bloqueado(quantidade);
				produto.requestFocus();
			}
		}
		return valida;

	}

	public void gravaitem(Pdenti p) {

		try {
			if (!btncadastrar.isEnabled()) {

			} else {

				p.setItem(item.getText().trim());
				p.setProduto(produto.getText().trim());
				p.setDescpro(descpro.getText().trim());
				p.setUn(unidade.getText().trim());
				p.setQuantidade(aces1.retornadouble(
						aces1.mascaraquantidade(aces1.retornadouble(aces1.removeponto(quantidade.getText().trim())))));
				p.setUnitario(aces1.retornadouble(aces1.removeponto(unitario.getText().trim())));
				p.setVrtot(aces1.retornadouble(aces1.removeponto(vrmercadoria.getText().trim())));

			}
			if (p.getProduto().isEmpty() || p.getUnitario() == null || p.getQuantidade() == null) {
				JOptionPane.showMessageDialog(null, "Dados em branco");
				quantidade.requestFocus();
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
			JOptionPane.showMessageDialog(null, "ERRO AO ADICIONAR O ITEM NO PEDIDO DE COMPAS " + e1.getMessage());
			aces1.demologger.error("ERRO AO ADICIONAR O ITEM NO PEDIDO DE COMPAS " + e1.getMessage());
		}

	}
	
	public void validaunitario() {
		
		if (unitario.getText().equals("") || unitario.getText().equals(null) || unitario.getText().equals("0,00")) {
			JOptionPane.showMessageDialog(null, "Digite um Valor!!!!");
			aces1.bloqueado(vrmercadoria);
			btncadastrar.setEnabled(false);
			unitario.requestFocus();
		} else {

			double unitario1 = aces1.retornadouble(aces1.removeponto(unitario.getText().trim()));
			double quantidade1 = aces1.retornadouble(
					aces1.mascaraquantidade(aces1.retornadouble(aces1.gravamoedadouble(quantidade.getText()))));
			double valor = unitario1 * quantidade1;
			vrmercadoria.setText(aces1.valordinheiro(valor));
			aces1.bloqueado(unitario);
			btncadastrar.setEnabled(true);
			btncadastrar.requestFocus();
			
			if (unitario.getText().equals(null) || unitario.getText().trim().isEmpty()) {
				unitario.setText(aces1.valordinheiro(0.0));
			} else {
				
				double init1 = aces1.retornadouble(aces1.removeponto(unitario.getText().trim()));
				unitario.setText(aces1.valordinheiro(init1));
				
			}

		}

		
	}
	
	public void validaquantidade() {
		
		if (quantidade.getText().equals("") || quantidade.getText().equals(null)
				|| quantidade.getText().equals("0")) {
			JOptionPane.showMessageDialog(null, "Digite um Valor!!!!");
			aces1.bloqueado(unitario);
			quantidade.requestFocus();

		} else {
			aces1.bloqueado(quantidade);
			unitario.requestFocus();
			aces1.liberado(unitario);
			unitario.selectAll();
		}
		
		if (quantidade.getText().equals(null) || quantidade.getText().trim().isEmpty()) {
			quantidade.setText(aces1.mascaraquantidadecomvirgula(0.0));
		} else {
			
			double quant1 = aces1.retornadouble(
					aces1.mascaraquantidade(aces1.retornadouble(aces1.removeponto(quantidade.getText().trim()))));
			quantidade.setText(aces1.mascaraquantidadecomvirgula(quant1));
			
		}

		
		
	}

	public Double getPrecop() {
		return precop;
	}

	public void setPrecop(Double precop) {
		this.precop = precop;
	}

}
