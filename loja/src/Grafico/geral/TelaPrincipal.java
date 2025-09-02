package Grafico.geral;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Menu;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;

import Grafico.Pedidos.listadecompras;
import Grafico.Pedidos.listadevendas;
import Grafico.Produto.*;
import Grafico.cademp.Listaempresa;
import Grafico.serviços.listadeordemserviços;
import Grafico.usuario.alterasenhaususario;
import Grafico.usuario.listadeusuario;
import Grafico.Cadcli.listadecliente;
import Grafico.Fornecedor.listadefornecedor;

import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

import org.apache.log4j.BasicConfigurator;

import entidades.Acesso;
import entidades.Cademp;
import entidades.Cadmanuemp;
import entidades.Usuario;
import repositorios.AcessoRepository;
import beans.AcessoBean;
import beans.CadempBean;
import beans.ModulosBean;
import Configuracao.clsRedimensionarImagem;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {
	private JPanel contentPrincipal;
	private JMenuBar menuPrincipal;
	public String Nusuario;
	private JMenu cadastros;
	private JMenu RH;
	private JMenu configuracao;
	private JMenu ferramentaria;
	private JMenu movimentos;
	private JMenuItem altusersenha;
	private JMenuItem funcao;
	private JMenuItem revferr;
	private JMenuItem cadempresa;
	private JFrame principal = new JFrame();
	clsRedimensionarImagem resize = new clsRedimensionarImagem();
	private JButton btnproduto;
	private JButton btncliente;
	private JButton btnfornecedor;
	private JButton btnvendas;
	private JButton btnotasent;
	private JButton btnsair;
	private JButton btnguilhotina;
	private JButton btnestrupro;
	private JButton btnrr;
	private JButton btnestoque;
	private JButton btnprocli;
	private JButton btncademp;
	private JButton btncadusuario;
	private JButton btnalerasenha;
	private JButton btnlicenca;
	private JButton btnordemservico;
	private JToolBar menus;
	private JMenuBar menusuario;
	
	public static int decunit = 0, decqtde = 0;
	
	public static String rotinau = "USUARIO";
	public static String rotinap = "PRODUTO";
	public static String rotinac = "CLIENTE";
	public static String rotinae = "EMPRESA";
	public static String rotinar = "RECURSOS HUMANOS";
	public static String rotinaco = "PEDIDOCOMPRAS";
	public static String rotinanotaent = "NOTAENTRADA";
	public static String rotinagui = "GUILHOTINA";
	public static String rotinapcli = "PROCLI";
	public static String rotinafun = "FUNCAO";
	public static String rotinacadusuario = "USUARIO";
	public static String rotinacademp = "EMPRESA";
	public static String rotinafor = "FORNECEDOR";
	public static String rotinaven = "PEDIDOVENDAS";
	public static String rotinaordemserv = "ORDEMSERVICO";
	
	BufferedImage b;
	Rectangle2D rect;
	private TexturePaint imagem;
	AcessoBean aces1 = new AcessoBean();
	private JButton btncompras;
	String data = "";
	DateFormat dateehorastring = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


	@SuppressWarnings("unused")
	public TelaPrincipal(Cademp e, final Usuario u) throws SQLException {

		try {
			File jarName = new java.io.File(TelaPrincipal.class.getProtectionDomain()
				      .getCodeSource()
				      .getLocation()
				      .toURI()
				      .getPath());
			
			File aberto = new File(System.getProperty("user.dir") +"\\"+ jarName.getName());
			
			data = dateehorastring.format(new Date(aberto.lastModified()));
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("DeskSystem Versão 1 - "+ data + " EMAIL: andre.santos@gemaap.com.br  SITE: www.gemaap.com.br WHATSAPP (11)96662-1415");
		principal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(aces1.retornalogorelat()));
		pegarResolucao();
		contentPrincipal = new JPanel();
		contentPrincipal.setBackground(new Color(211, 211, 211));
		setContentPane(contentPrincipal);
		GridBagLayout gbl_contentPrincipal = new GridBagLayout();
		gbl_contentPrincipal.columnWidths = new int[] { 1419, 0 };
		gbl_contentPrincipal.rowHeights = new int[] { 32, 400, 23, 0 };
		gbl_contentPrincipal.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPrincipal.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPrincipal.setLayout(gbl_contentPrincipal);

		menus = new JToolBar();
		menus.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		menus.setPreferredSize(new Dimension(0, 65));
		GridBagConstraints gbc_menus = new GridBagConstraints();
		gbc_menus.anchor = GridBagConstraints.NORTH;
		gbc_menus.fill = GridBagConstraints.HORIZONTAL;
		gbc_menus.insets = new Insets(0, 0, 0, 0);
		gbc_menus.gridx = 0;
		gbc_menus.gridy = 0;
		contentPrincipal.add(menus, gbc_menus);

		if (aces1.mostramodulo(rotinap, u) == true) {
			btnproduto = new JButton(rotinap);
			GridBagConstraints gbc_btnproduto = new GridBagConstraints();
			aces1.gbcbuton(gbc_btnproduto);
			aces1.butonfundopaginaincial(btnproduto);
			btnproduto.setToolTipText("PRODUTOS");
			menus.add(btnproduto, gbc_btnproduto);
			aces1.butoninicial(btnproduto);
			btnproduto.setText(rotinap);
			btnproduto.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/produtos.png")));
			btnproduto.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							//new listadeproduto(u).setVisible(true);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "ERRO: " + e1.getMessage());
						}
					}
				}
			});
			btnproduto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new listadeproduto(u).setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
					}

				}
			});
			
			if (aces1.validaacesso(u, rotinap) == true) {
				btnproduto.setEnabled(true);
			} else {
				btnproduto.setEnabled(false);
			}
			
		}
		
		if (aces1.mostramodulo(rotinafor, u) == true) {
			btnfornecedor = new JButton(rotinafor);
			GridBagConstraints gbc_btnfornecedor = new GridBagConstraints();
			aces1.gbcbuton(gbc_btnfornecedor);
			menus.add(btnfornecedor, gbc_btnfornecedor);
			aces1.butonfundopaginaincial(btnfornecedor);
			btnfornecedor.setToolTipText("FORNECEDORES");
			btnfornecedor.setText(rotinafor);
			aces1.butoninicial(btnfornecedor);
			btnfornecedor.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/fornecedor.png")));
			btnfornecedor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {

							new listadefornecedor(u).setVisible(true);

						} catch (Exception e1) {

							JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

						}
					}
				}
			});

			btnfornecedor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {

						new listadefornecedor(u).setVisible(true);

					} catch (Exception e) {

						JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

					}
				}
			});
			
			
			if (aces1.validaacesso(u, rotinafor) == true) {
				btnfornecedor.setEnabled(true);
			} else {
				btnfornecedor.setEnabled(false);
			}
			
		}

		if (aces1.mostramodulo(rotinaco, u) == true) {
			btncompras = new JButton();
			btncompras.setToolTipText("COMPRAS");
			btncompras.setText("COMPRAS");
			aces1.butoninicial(btncompras);
			GridBagConstraints gbc_btncompras = new GridBagConstraints();
			aces1.gbcbuton(gbc_btncompras);
			menus.add(btncompras);
			aces1.butonfundopaginaincial(btncompras);
			btncompras.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/compras.png")));
			btncompras.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {

							new listadecompras(u).setVisible(true);

						} catch (Exception e1) {

							JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

						}
					}
				}
			});
			btncompras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					try {
						new listadecompras(u).setVisible(true);
					} catch (Exception e) {

					}
				}
			});
			
			if (aces1.validaacesso(u, rotinaco) == true) {
				btncompras.setEnabled(true);
			} else {
				btncompras.setEnabled(false);
			}
			
		}

		if (aces1.mostramodulo(rotinaven, u) == true) {
			btnvendas = new JButton();
			GridBagConstraints gbc_btnvendas = new GridBagConstraints();
			aces1.gbcbuton(gbc_btnvendas);
			menus.add(btnvendas, gbc_btnvendas);
			aces1.butonfundopaginaincial(btnvendas);
			btnvendas.setToolTipText("VENDAS");
			btnvendas.setText("VENDAS");
			aces1.butoninicial(btnvendas);
			btnvendas.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/vendas.png")));
			btnvendas.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {

							new listadevendas(u).setVisible(true);

						} catch (Exception e1) {

							JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

						}
					}
				}
			});
			btnvendas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {

						new listadevendas(u).setVisible(true);

					} catch (Exception e) {

						JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

					}
				}
			});
			
			if (aces1.validaacesso(u, rotinaven) == true) {
				btnvendas.setEnabled(true);
			} else {
				btnvendas.setEnabled(false);
			}
			
		}

		if (aces1.mostramodulo(rotinac, u) == true) {
			btncliente = new JButton();
			GridBagConstraints gbc_btncliente = new GridBagConstraints();
			aces1.gbcbuton(gbc_btncliente);
			menus.add(btncliente, gbc_btncliente);
			aces1.butonfundopaginaincial(btncliente);
			btncliente.setToolTipText("CLIENTES");
			btncliente.setText(rotinac);
			aces1.butoninicial(btncliente);
			btncliente.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/clientes.png")));
			btncliente.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							new listadecliente(u).setVisible(true);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "ERRO CLIENTE " + e1.getMessage());
						}
					}
				}
			});
			btncliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					try {
						new listadecliente(u).setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO CLIENTE " + e.getMessage());
					}
				}
			});
			
			if (aces1.validaacesso(u, rotinac) == true) {
				btncliente.setEnabled(true);
			} else {
				btncliente.setEnabled(false);
			}
			
		}
		
		if (aces1.mostramodulo(rotinaordemserv, u) == true) {
			btnordemservico = new JButton();
			GridBagConstraints gbc_btnordemservico = new GridBagConstraints();
			aces1.gbcbuton(gbc_btnordemservico);
			menus.add(btnordemservico, gbc_btnordemservico);
			aces1.butonfundopaginaincial(btnordemservico);
			btnordemservico.setToolTipText("ORDEM DE SERVIÇO");
			btnordemservico.setText(rotinaordemserv);
			aces1.butoninicial(btnordemservico);
			btnordemservico.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/ordemservico.png")));
			btnordemservico.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							new listadeordemserviços(u).setVisible(true);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "ERRO ORDEM DE SERVIÇO " + e1.getMessage());
						}
					}
				}
			});
			btnordemservico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					try {
						new listadeordemserviços(u).setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO ORDEM DE SERVIÇO " + e.getMessage());
					}
				}
			});
			
			if (aces1.validaacesso(u, rotinaordemserv) == true) {
				btnordemservico.setEnabled(true);
			} else {
				btnordemservico.setEnabled(false);
			}
			
		}

		if (aces1.mostramodulo(rotinau, u) == true) {
			btncadusuario = new JButton(rotinau);
			GridBagConstraints gbc_btncadusuario = new GridBagConstraints();
			aces1.gbcbuton(gbc_btncadusuario);
			menus.add(btncadusuario, gbc_btncadusuario);
			aces1.butonfundopaginaincial(btncadusuario);
			btncadusuario.setToolTipText("USUARIO");
			btncadusuario.setText(rotinau);
			aces1.butoninicial(btncadusuario);
			btncadusuario.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/usuario.png")));
			btncadusuario.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							new listadeusuario(u).setVisible(true);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "ERRO USUARIO " + e1.getMessage());
						}
					}
				}
			});
			btncadusuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					try {
						new listadeusuario(u).setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO USUARIO " + e.getMessage());
					}
				}
			});
			
			
			if (aces1.validaacesso(u, rotinau) == true) {
				btncadusuario.setEnabled(true);
			} else {
				btncadusuario.setEnabled(false);
			}
			
		}

		if (aces1.mostramodulo(rotinacademp, u) == true) {
			btncademp = new JButton();
			GridBagConstraints gbc_btncademp = new GridBagConstraints();
			aces1.gbcbuton(gbc_btncademp);
			menus.add(btncademp, gbc_btncademp);
			aces1.butonfundopaginaincial(btncademp);
			btncademp.setToolTipText(rotinacademp);
			btncademp.setText(rotinacademp);
			aces1.butoninicial(btncademp);
			btncademp.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/empresa.png")));
			btncademp.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							new Listaempresa(u).setVisible(true);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "ERRO EMPRESA " + e1.getMessage());
						}

					}
				}
			});
			btncademp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					try {
						new Listaempresa(u).setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERRO EMPRESA " + e.getMessage());
					}
				}
			});
			
			if (aces1.validaacesso(u, rotinacademp) == true) {
				btncademp.setEnabled(true);
			} else {
				btncademp.setEnabled(false);
			}
			
		}

		btnalerasenha = new JButton("ALTERA SENHA");
		GridBagConstraints gbc_btnalerasenha = new GridBagConstraints();
		aces1.gbcbuton(gbc_btnalerasenha);
		menus.add(btnalerasenha, gbc_btnalerasenha);
		btnalerasenha.setEnabled(true);
		aces1.butonfundopaginaincial(btnalerasenha);
		btnalerasenha.setToolTipText("ALTERA SENHA");
		btnalerasenha.setText("ALTERA SENHA");
		aces1.butoninicial(btnalerasenha);
		btnalerasenha.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/senha.png")));
		btnalerasenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						new alterasenhaususario(u).setVisible(true);
					} catch (Exception e1) {

					}
				}
			}
		});
		btnalerasenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					new alterasenhaususario(u).setVisible(true);
				} catch (Exception e) {

				}
			}
		});
		

		btnlicenca = new JButton();
		btnlicenca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {

						new Geralicenca().setVisible(true);
					} catch (Exception e1) {

					}
				}
			}
		});
		btnlicenca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					new Geralicenca().setVisible(true);
				} catch (Exception e) {

				}
			}
		});
		GridBagConstraints gbc_btnlicenca = new GridBagConstraints();
		aces1.gbcbuton(gbc_btnlicenca);
		if (u.getLogin().equals("ADMSUPER")) {
			menus.add(btnlicenca, gbc_btnlicenca);
		}
		aces1.butonfundopaginaincial(btnlicenca);
		btnlicenca.setText("LICENCA");
		aces1.butoninicial(btnlicenca);
		btnlicenca.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/licenca.png")));

		btnsair = new JButton();
		btnsair.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {

						System.exit(0);
					} catch (Exception e1) {

					}
				}
			}
		});
		btnsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					System.exit(0);
				} catch (Exception e) {

				}
			}
		});
		GridBagConstraints gbc_btnsair = new GridBagConstraints();
		aces1.gbcbuton(gbc_btnsair);
		menus.add(btnsair, gbc_btnsair);
		aces1.butonfundopaginaincial(btnsair);
		btnsair.setText("SAIR");
		aces1.butoninicial(btnsair);
		btnsair.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/sair.png")));

		JLabel foto = new JLabel("");
		//foto.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/logoprincipal.png")));
		foto.setIcon(new ImageIcon("\\\\" + aces1.caminhoireport()+"\\imagens\\logoprincipal.png"));
		GridBagConstraints gbc_foto = new GridBagConstraints();
		gbc_foto.insets = new Insets(0, 0, 5, 0);
		gbc_foto.gridx = 0;
		gbc_foto.gridy = 1;
		contentPrincipal.add(foto, gbc_foto);

		menusuario = new JMenuBar();
		menusuario.setForeground(Color.BLUE);
		menusuario.setPreferredSize(new Dimension(-5, 25));
		menusuario.setBackground(Color.gray);
		//menusuario.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));

		JMenuItem user = new JMenuItem("USUARIO: " + u.getLogin());
		//user.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		user.setPreferredSize(new Dimension(250, 0));
		menusuario.add(user);

		JMenuItem empresanome = new JMenuItem("EMPRESA: " + e.getFantasia());
		//empresanome.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		empresanome.setPreferredSize(new Dimension(250, 0));
		menusuario.add(empresanome);

		JMenuItem espaco = new JMenuItem();
		espaco.setPreferredSize(new Dimension(1000, 0));
		//espaco.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		menusuario.add(espaco);

		GridBagConstraints gbc_menusuario = new GridBagConstraints();
		gbc_menusuario.fill = GridBagConstraints.BOTH;
		gbc_menusuario.insets = new Insets(0, -6, -2, 0);
		gbc_menusuario.gridx = 0;
		gbc_menusuario.gridy = 2;
		contentPrincipal.add(menusuario, gbc_menusuario);
		
        decunit = decimaisunitario();
		
		decqtde = decimaisqtde();


		// fechar janela com esc
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				dispose();
			}
		});

	}

	private void pegarResolucao() {
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension dimensao = t.getScreenSize();
		//this.setSize((dimensao.width + 5), (dimensao.height - 38));
		this.setBounds(-7, 0, (dimensao.width + 14), (dimensao.height - 30));

	}
	
	private int decimaisunitario() {
		int casaunit = 0;
		Cadmanuemp manuemp = new Cadmanuemp();
		CadempBean empbean = new CadempBean();

		manuemp = empbean.rastreioemp();

		casaunit = manuemp.getDecunit();

		return casaunit;

	}
	
	private int decimaisqtde() {
		int casaqtde = 0;
		Cadmanuemp manuemp = new Cadmanuemp();
		CadempBean empbean = new CadempBean();

		manuemp = empbean.rastreioemp();

		
		casaqtde = manuemp.getDecqtde();
		

		return casaqtde;

	}

}
