package beans;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.*;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import org.apache.poi.ss.formula.functions.T;

import entidades.Acesso;
import entidades.Cadcamin;
import entidades.Cademp;
import entidades.Modulos;
import entidades.Rotinas;
import entidades.Usuario;
import filter.EntityManagerUtil;
import repositorios.AcessoRepository;
import repositorios.CadempRepository;
import repositorios.LogusuRepository;
import repositorios.ModulosRepository;
import repositorios.RotinasRepository;
import repositorios.UsuarioRepository;

@ManagedBean
public class AcessoBean {

	public String incluir = "N„o Possui Permiss„o para Incluir!!!!!";

	public String alterar = "N„o Possui Permiss„o para Alterar!!!!!";

	public String excluir = "N„o Possui Permiss„o para Excluir!!!!!";

	public String logincluir = "INCLUSAO";

	public String logalterar = "ALTERACAO";

	public String logexcluir = "EXCLUSAO";

	public T obj;

	public Logger demologger = LogManager.getLogger(T.class.getName());

	Connection con = null;

	Cadcamin caminho = new Cadcamin();
	CadcaminBean caminbean = new CadcaminBean();

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public void adiciona(Acesso p) {
		EntityManagerUtil.conexao();
		AcessoRepository repository = new AcessoRepository(EntityManagerUtil.manager);

		try {
			repository.grava(p);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adiciona acesso bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		AcessoRepository repository = new AcessoRepository(EntityManagerUtil.manager);

		try {
			repository.remove(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove acesso bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Acesso procura(Long id) {
		EntityManagerUtil.conexao();
		AcessoRepository repository = new AcessoRepository(EntityManagerUtil.manager);
		Acesso acesso = new Acesso();

		try {
			acesso = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procura acesso bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return acesso;

	}

	public List<Acesso> getUseracessos(Usuario user) {

		EntityManagerUtil.conexao();
		List<Acesso> acessos1 = new ArrayList<Acesso>();

		AcessoRepository repository = new AcessoRepository(EntityManagerUtil.manager);

		try {
			acessos1 = repository.getLista(user);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro useracessos acesso bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return acessos1;
	}

	public List<Acesso> listaacesso(Usuario user) {
		EntityManagerUtil.conexao();

		List<Acesso> acessos1 = new ArrayList<Acesso>();

		AcessoRepository repository = new AcessoRepository(EntityManagerUtil.manager);

		try {
			acessos1 = repository.getLista(user);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro listaacesso acesso bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return acessos1;

	}

	public void gravatodos(Usuario user, Usuario uc) throws IOException {

		List<Acesso> useracessos = new ArrayList<Acesso>();
		Boolean existe = false;
		Acesso acesson = new Acesso();
		EntityManagerUtil.conexao();
		AcessoRepository repository = new AcessoRepository(EntityManagerUtil.manager);

		useracessos = repository.verificaacessos(user);

		if (!useracessos.isEmpty()) {
			existe = true;

			for (Acesso p : useracessos) {
				if (p.getRotina() != null) {
					acesson = new Acesso();

					acesson.setAcesso(true);
					acesson.setInclusao(true);
					acesson.setAlteracao(true);
					acesson.setExclusao(true);
					acesson.setNivel5(true);
					acesson.setNivel6(true);
					acesson.setNivel7(true);
					acesson.setNivel8(true);
					acesson.setModulo(p.getModulo());
					acesson.setUsuario(user);
					acesson.setRotina(p.getRotina());
					acesson.setId(p.getId());

					repository.grava(acesson);

					repositorylog
							.adicionaLog(
									logalterar, "ACESSO " + " DIREITOS DA ROTINA " + acesson.getRotina().getId()
											+ " DO USUARIO " + acesson.getUsuario().getNome().trim(),
									acesson.getId(), uc);

				}

			}

		} else {
			JOptionPane.showMessageDialog(null, "USUARIO SEM ACESSOS !!!!!!!!");
		}

		if (existe == true) {
			JOptionPane.showMessageDialog(null, "ACESSOS PERMITIDOS!!!!!!!!");
		}
	}

	public Boolean validaacesso(Usuario u, String m) {

		boolean valida = false;
		Acesso acesson = new Acesso();
		try {
			if (u.getLogin().equals("ADMSUPER")) {
				valida = true;

			} else {
				EntityManagerUtil.conexao();
				AcessoRepository ace = new AcessoRepository(EntityManagerUtil.manager);
				acesson = ace.procuraacesso1(u, m);
				if (acesson.getId() != null) {

					if (acesson.getAcesso() == true) {
						valida = true;
					}
				}
			}

		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro valida acesso bean: " + t.getMessage());
			// Logger.getLogger(AcessoBean.class.getName()).log(Level.SEVERE, null,
			// t.getMessage());
			demologger.error("Erro valida acesso bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return valida;

	}

	public Acesso procuraacesso(Usuario u, String m) {
		EntityManagerUtil.conexao();
		Acesso acesson = new Acesso();

		AcessoRepository ace = new AcessoRepository(EntityManagerUtil.manager);

		try {
			acesson = ace.procuraacesso1(u, m);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procura1 acesso bean: " + t.getMessage());
			demologger.error("Erro procura1 acesso bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return acesson;

	}

	// ==================================funcaopadrao==========================================================

	public String caminho() {
		String ip = "localhost";

		return ip;

	}

	public void fechaconectabanco() throws Exception {
		if (con != null) {
			con.close();
		}

	}

	public String caminhoireport() {
		caminho = caminbean.caminhofixo();
		String ip = caminho.getCaminho().trim();

		return ip;
	}

	public String retornalogorelat() {
		String foto = caminhoireport() + "\\imagens\\logoinicial.png";

		return foto;
	}

	public Connection conectareport() throws Exception {
		String url = "";
		FileReader fr = new FileReader(EntityManagerUtil.caminhobanco);
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			// lÍ a proxima linha
			String linha = br.readLine();
			// faz algo com a linha
			url = linha.replace("javax.persistence.jdbc.url =", "").trim();
		}
		br.close();
		fr.close();
		con = null;
		String usuario = "root";
		String senha = "poli";
		con = DriverManager.getConnection(url, usuario, senha);

		return con;

	}

	public Boolean mostramodulo(String modulo, Usuario user) {
		Boolean valida = false;
		EntityManagerUtil.conexao();

		Modulos m = new Modulos();

		try {
			if (user.getLogin().equals("ADMSUPER")) {
				valida = true;
			} else {

				ModulosRepository repository = new ModulosRepository(EntityManagerUtil.manager);
				m = repository.procuramodulo(modulo);

				if (m.getIdmodulo() != null) {
					if (m.getMostra() == true) {
						valida = true;
					}
				}
			}
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro modulo acesso bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return valida;
	}

	public Cademp empresabanco() {
		EntityManagerUtil.conexao();
		CadempRepository repository = new CadempRepository(EntityManagerUtil.manager);

		Cademp cademp = new Cademp();
		List<Cademp> cademps = new ArrayList<Cademp>();

		cademps = repository.getLista();

		for (Cademp p : cademps) {
			cademp = p;
			if (p != null) {
				cademp = p;
			}
		}

		return cademp;

	}

	public String numeromilheiro(Double numero) {
		String vfinal = "";
		DecimalFormat formatter = new DecimalFormat("#,###");
		vfinal = formatter.format(numero);
		return vfinal;
	}

	public Font tipoletra() {
		Font font = new Font("Arial", Font.BOLD, 12);
		return font;

	}

	public String Stringnotnull(String p) {
		String valor = null;
		if (p == null) {
			valor = "";
		} else {
			valor = p;
		}
		return valor;

	}

	public String retornaString(Double p) {
		String valor = null;
		if (p == null) {
			valor = "0";
		} else {
			valor = p.toString().replace(".0", "");
		}
		return valor;

	}

	public Date retornadata(String p) {
		Date valor = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		if (p == null || p.replace("/", "").trim().isEmpty()) {
			valor = null;
		} else {
			try {
				valor = formato.parse(p);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return valor;

	}

	public String retornadate(Date valor) throws ParseException {
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String data = null;
		if (valor == null) {
			data = "";
		} else {
			data = formato.format(valor);
		}
		return data;

	}

	public String retornadatastring(Date valor) throws ParseException {
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String data = null;
		if (valor == null) {
			data = "";
		} else {
			data = formato.format(valor);
		}
		return data;

	}

	public Date gravadata(String p) throws ParseException {
		Date valor = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		if (p == null) {
			valor = new Date();
		} else {
			valor = formato.parse(p);
		}
		return valor;

	}

	public Double retornadouble(String p) {
		Double valor = null;
		if (p.equals(null) || p.isEmpty()) {
			valor = 0.0;
		} else {
			valor = Double.parseDouble(p.replace(",", "."));
		}
		return valor;

	}

	public Boolean retornaBoolean(JCheckBox p) {
		Boolean valor = false;
		if (p.isSelected()) {
			valor = true;
		}
		return valor;

	}

	public void butonfundopaginaincial(final JButton cor) {

		cor.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				cor.setBorder(new BevelBorder(BevelBorder.RAISED));
				cor.setBorderPainted(true);
				cor.setBackground(Color.lightGray);
			}

			public void mouseExited(MouseEvent e) {
				cor.setBorderPainted(false);
				cor.setBackground(Color.WHITE);
			}

		});
	}

	public void tipotable(JTable tabela) {
		tabela.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
		tabela.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "TAB");
		tabela.setCellSelectionEnabled(false);
		tabela.setColumnSelectionAllowed(true);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setRowSelectionAllowed(true);
		tabela.setColumnSelectionAllowed(false);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// bloqueia multi linhas
		tabela.setRowHeight(20);
		tabela.setBackground(new Color(169, 169, 169));
		tabela.setForeground(Color.BLACK);
		tabela.setSelectionBackground(new Color(173, 216, 230));

	}

	public Component tipocomponete(Component t) {
		t.setBackground(new Color(200, 200, 200));
		t.setForeground(Color.BLACK);

		return t;

	}

	public Color corcomponente() {
		Color cor = new Color(136, 245, 90);
		return cor;

	}

	public String imagemicone() {
		String ip = caminho();
		String local = "\\\\" + ip + "\\imagens\\logoinicial.png";

		return local;

	}

	public Date dataatual() {
		Date data = null;
		EntityManagerUtil.conexao();
		AcessoRepository repository = new AcessoRepository(EntityManagerUtil.manager);

		try {
			data = repository.dataatual();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro datatual acesso bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return data;
	}

	public String datastring() {
		EntityManagerUtil.conexao();
		String data = null;
		AcessoRepository repository = new AcessoRepository(EntityManagerUtil.manager);

		try {
			data = repository.data();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro datastring acesso bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return data;
	}

	public String hora() {
		EntityManagerUtil.conexao();
		String hora = null;
		AcessoRepository repository = new AcessoRepository(EntityManagerUtil.manager);

		try {
			hora = repository.hora();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro horatual acesso bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return hora;
	}

	public MaskFormatter setNumero(String numero) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(numero);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}

	public Color corpadrao() {
		Color c = new java.awt.Color(220, 220, 220);
		return c;
	}

	public void fundotela(JPanel tela) {
		tela.setBorder(new EmptyBorder(5, 5, 5, 5));
		tela.setBackground(corpadrao());
		tela.setLayout(null);
		tela.setForeground(Color.BLUE);

	}

	public void bloqueado(JTextField cor) {
		cor.setEditable(false);
		cor.setForeground(Color.blue);
		cor.setDisabledTextColor(Color.blue);
		cor.setEnabled(false);
		cor.setOpaque(true);
		cor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		cor.setBackground(new java.awt.Color(211, 211, 211));
		cor.setBorder(new LineBorder(Color.BLACK));
	}

	public void bloqueadojTextArea(JTextArea cor) {
		cor.setEditable(false);
		cor.setForeground(Color.blue);
		cor.setDisabledTextColor(Color.blue);
		cor.setEnabled(false);
		cor.setOpaque(true);
		cor.setBackground(new java.awt.Color(211, 211, 211));
		cor.setBorder(new LineBorder(Color.BLACK));
	}

	public JButton butonfundo(final JButton cor) {
		cor.setBorder(new BevelBorder(BevelBorder.RAISED));
		cor.setOpaque(false);
		cor.setContentAreaFilled(false);
		cor.setBorderPainted(false);

		cor.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				cor.setBorder(new BevelBorder(BevelBorder.RAISED));
				// cor.setBorder(new RoundedBorder(10));
				cor.setBorderPainted(true);
				cor.setBackground(Color.lightGray);
			}

			public void mouseExited(MouseEvent e) {
				cor.setOpaque(false);
				cor.setContentAreaFilled(false);
				cor.setBorderPainted(false);
			}

		});

		return cor;

	}

	public void menubotao(JToolBar cor) {

		cor.setBorder(null);
		cor.setPreferredSize(new Dimension(0, 65));
		/*
		 * GridBagConstraints gbc_botoes = new GridBagConstraints(); gbc_botoes.anchor =
		 * GridBagConstraints.NORTH; gbc_botoes.fill = GridBagConstraints.HORIZONTAL;
		 * gbc_botoes.insets = new Insets(0, 0, 0, 0); gbc_botoes.gridx = 0;
		 * gbc_botoes.gridy = 0;
		 */
		cor.addSeparator();

		// return cor;

	}

	public void espacobotao(JToolBar cor) {

		cor.addSeparator();

	}

	public void padraojlabel(JLabel branco) {
		branco.setFont(new Font("Tahoma", Font.BOLD, 12));
		branco.setForeground(Color.BLACK);
	}

	public void validacnpjecpf(JTextField branco) {
		String campo = branco.getText();
		if (campo.length() == 11) {
			String cpf = String.valueOf("" + campo.charAt(0) + campo.charAt(1) + campo.charAt(2) + "." + campo.charAt(3)
					+ campo.charAt(4) + campo.charAt(5) + "." + campo.charAt(6) + campo.charAt(7) + "-"
					+ campo.charAt(8) + "-" + campo.charAt(9) + "-" + campo.charAt(10));
			branco.setText(cpf);
		} else if (campo.length() == 14) {
			String cnpjp = String.valueOf("" + campo.charAt(0) + campo.charAt(1) + "." + campo.charAt(2)
					+ campo.charAt(3) + campo.charAt(4) + "." + campo.charAt(5) + campo.charAt(6) + campo.charAt(7)
					+ "/" + campo.charAt(8) + campo.charAt(9) + campo.charAt(10) + campo.charAt(11) + "-"
					+ campo.charAt(12) + campo.charAt(13));
			branco.setText(cnpjp);
		}
	}

	public int padraotamanhocampo() {
		int tamanho = 20;

		return tamanho;
	}

	public void focusclique(JTextField branco) {
		branco.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				branco.selectAll();
			}
		});
	}

	public void gbcbuton(final GridBagConstraints gbc_cor) {
		gbc_cor.anchor = GridBagConstraints.WEST;
		gbc_cor.insets = new Insets(0, 0, 0, 0);
		gbc_cor.gridx = 0;
		gbc_cor.gridy = 0;

	}

	public void gbcbutonlista(final GridBagConstraints gbc_cor, JButton nomebotao, final JToolBar Tobotao) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbcbuton(gbc_cor);
		Tobotao.add(nomebotao, gbc);
		Tobotao.addSeparator();

	}

	public void butoninicial(final JButton cor) {
		cor.setEnabled(true);
		cor.setBackground(Color.white);
		cor.setVerticalTextPosition(SwingConstants.BOTTOM);
		cor.setHorizontalTextPosition(SwingConstants.CENTER);
		cor.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
		cor.setBorderPainted(false);
		cor.setBorder(new BevelBorder(BevelBorder.LOWERED));

	}

	public void liberado(JTextField branco) {
		branco.setForeground(Color.blue);
		branco.setEnabled(true);
		branco.setEditable(true);
		branco.setBorder(new LineBorder(Color.BLACK));
		branco.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		branco.setBackground(Color.WHITE);
		branco.setCaretPosition(0);
		focusclique(branco);
	}

	public void liberadojTextArea(JTextArea branco) {
		branco.setForeground(Color.blue);
		branco.setEnabled(true);
		branco.setEditable(true);
		branco.setBorder(new LineBorder(Color.BLACK));
		branco.setBackground(Color.WHITE);
		branco.setCaretPosition(0);
		branco.setOpaque(true);
		branco.cut(); // recortar
		branco.copy(); // copiar
		branco.setLineWrap(true);
		branco.setFont(new Font("Arial", Font.PLAIN, 14));
	}

	public void letras(JTextField branco) {
		branco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				char charTeste = '"';
				String especiais = "¬‘„ı”⁄“ŸÃÕ»…¿¡¥``™∫ß*&®%$#¬ Œ‘€‚ÍÓÙ˚<>:;?/\\\\\\\\|{}[]!«@#$%®&*()_.-+=,¥~^`'Á`'"
						+ charTeste;
				if (caracteres.contains(e.getKeyChar() + "") || especiais.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}

		});
	}

	public void numeros(JTextField branco) {
		branco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "abcdefghijklmnopqrstuvxzwyABCDEFGHIJKLMNOPQRSTUVXZWY";
				char charTeste = '"';
				String especiais = "¬‘„ı”⁄“ŸÃÕ»…¿¡¥``™∫ß*&®%$#¬ Œ‘€‚ÍÓÙ˚<>:;?/\\\\\\\\|{}[]!«@#$%®&*()_-+=,¥~^`'Á"
						+ charTeste;
				if (caracteres.contains(e.getKeyChar() + "") || especiais.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}

		});
	}

	public void numeroscomponto(JTextField branco) {
		branco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "abcdefghijklmnopqrstuvxzwyABCDEFGHIJKLMNOPQRSTUVXZWY";
				char charTeste = '"';
				String especiais = "¬‘„ı”⁄“ŸÃÕ»…¿¡¥``™∫ß*&®%$#¬ Œ‘€‚ÍÓÙ˚<>:;?/\\\\|{}[]!«@#$%®&*()_-+=,¥~^`'Á"
						+ charTeste;
				if (caracteres.contains(e.getKeyChar() + "") || especiais.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}

		});
	}

	public void numeroscombarra(JTextField branco) {
		branco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "abcdefghijklmnopqrstuvxzwyABCDEFGHIJKLMNOPQRSTUVXZWY";
				char charTeste = '"';
				String especiais = "¬‘„ı”⁄“ŸÃÕ»…¿¡¥``™∫ß*&®%$#¬ Œ‘€‚ÍÓÙ˚<>:;?\\\\\\\\|{}[]!«@#$%®&*()_-+=,¥~^`'Á"
						+ charTeste;
				if (caracteres.contains(e.getKeyChar() + "") || especiais.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}

		});
	}

	public void letrascomtraco(JTextField branco) {
		branco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789ABCDEFGHIJKLMNOPQRSTUVXZWY";
				char charTeste = '"';
				String especiais = "¬‘„ı”⁄“ŸÃÕ»…¿¡¥``™∫ß*&®%$#¬ Œ‘€‚ÍÓÙ˚<>:;?/\\\\|{}[]!«@#$%®&*()_.+=,¥~^`'Á"
						+ charTeste;
				if (caracteres.contains(e.getKeyChar() + "") || especiais.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}

		});
	}

	public void semcaracteres(JTextField branco) {
		branco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char charTeste = '"';
				String especiais = "™∫ß*&®%$#<>:;?/\\\\|{}[]!«@#$%®&*()_.-+=," + charTeste;
				if (especiais.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}

		});
	}

	public void moedabanco(Double valor, JTextField campo) {
		try {
			if (String.valueOf(valor).substring(String.valueOf(valor).length() - 2, String.valueOf(valor).length() - 1)
					.equals(".")) {
				campo.setText(String.valueOf(valor).replace(".", "") + "0");
			} else {
				campo.setText(String.valueOf(valor).replace(".", ""));
			}
		} catch (StringIndexOutOfBoundsException ex) {
			if (String.valueOf(valor).substring(String.valueOf(valor).length() - 2, String.valueOf(valor).length() - 1)
					.equals(".")) {
				campo.setText(String.valueOf(valor).replace(".", "") + "0");
			} else {
				campo.setText(String.valueOf(valor).replace(".", ""));
			}
		}

		if (valor == null) {

			campo.setText("0,00");
		}

	}

	public String gravamoedadouble(String v) {

		v = v.replace("R$", "");
		v = v.replace(".", "");
		v = v.replace(",", ".");
		v = String.valueOf(Float.parseFloat((v.replace(',', '.')).toString()));
		v = v.replace(" ", "");
		// v = String.format("%.2f", v);

		return v.trim();

	}

	public static String formataMoeda(Double vlr) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		if (vlr == null) {
			vlr = 0.0;
		}
		return nf.format(vlr).trim();
	}

	public String[] listasiglasuf() {
		String[] ufs = new String[27];
		;

		ufs[0] = "AC";
		ufs[1] = "AP";
		ufs[2] = "AP";
		ufs[3] = "AM";
		ufs[4] = "BA";
		ufs[5] = "CE";
		ufs[6] = "DF";
		ufs[7] = "ES";
		ufs[8] = "GO";
		ufs[9] = "MA";
		ufs[10] = "MT";
		ufs[11] = "MS";
		ufs[12] = "MG";
		ufs[13] = "PA";
		ufs[14] = "PB";
		ufs[15] = "PR";
		ufs[16] = "PE";
		ufs[17] = "PI";
		ufs[18] = "RJ";
		ufs[19] = "RN";
		ufs[20] = "RS";
		ufs[21] = "RO";
		ufs[22] = "RR";
		ufs[23] = "SC";
		ufs[24] = "SP";
		ufs[25] = "SE";
		ufs[26] = "TO";

		return ufs;

	}

	public void validadata(Boolean mostra, JTextField atual, JTextField proximo) {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		try {
			if (atual.getText().replace("/", "").trim().isEmpty()) {
				if (mostra == true) {
					proximo.requestFocus();
					proximo.setCaretPosition(0);
				}
			} else {
				df.parse(atual.getText());
				if (mostra == true) {
					proximo.requestFocus();
					proximo.setCaretPosition(0);
				}
			}

		} catch (ParseException ex) {
			atual.setText("");
			if (mostra == true) {
				JOptionPane.showMessageDialog(null, "Data Inv·lida");
			}

		}

	}

	// ============================================================================================

	public String getIncluir() {
		return incluir;
	}

	public void setIncluir(String incluir) {
		this.incluir = incluir;
	}

	public String getAlterar() {
		return alterar;
	}

	public void setAlterar(String alterar) {
		this.alterar = alterar;
	}

	public String getExcluir() {
		return excluir;
	}

	public void setExcluir(String excluir) {
		this.excluir = excluir;
	}

}
