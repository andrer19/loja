package Grafico.cademp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

import Grafico.Modulos.ListaModulos;
import Grafico.Rotinas.ListaRotinas;
import Grafico.caminho.ListaCaminhos;
import Grafico.geral.TelaPrincipal;
import beans.AcessoBean;
import beans.CadempBean;
import entidades.Acesso;
import entidades.Cademp;
import entidades.Usuario;
import filter.EntityManagerUtil;
import repositorios.LogusuRepository;

@SuppressWarnings("serial")
public class Listaempresa extends JDialog {
	Font font;
	int idT;
	public JTable table;
	private JToolBar botoes;
	private JPanel contentPane;
	JButton btnIncluir, btnmodulos, btnrotinas, btncaminho, btnmanutencao;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listaempresa = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};
	String rotina = TelaPrincipal.rotinacademp;
	ResultSet resultado;
	Cademp emp = new Cademp();
	CadempBean E = new CadempBean();
	AcessoBean aces1 = new AcessoBean();
	Acesso acesso = new Acesso();
	
	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public Listaempresa(final Usuario u) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 693, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("EMPRESA");
		setLocationRelativeTo(null);
		acesso = aces1.procuraacesso(u, rotina);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		JScrollPane listagem = new JScrollPane();
		listagem.setEnabled(false);
		listagem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listagem.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		listagem.setBounds(1, 2, 677, 110);
		setBackground(getBackground());
		setForeground(Color.BLACK);

		contentPane.add(listagem);

		botoes = new JToolBar();
		botoes.setLocation(1, 112);
		botoes.setSize(675, 60);
		aces1.menubotao(botoes);
		contentPane.add(botoes);

		table = new JTable(listaempresa) {
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
				Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
				c.setFont(aces1.tipoletra());
				aces1.tipocomponete(c);
				if (isCellSelected(rowIndex, vColIndex)) {
					c.setBackground(new Color(173, 216, 230));
				} else {
					/*
					 * if (rowIndex % 2 == 0) { c.setForeground(new Color(28, 28, 28)); }
					 */
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
					gravaitemempresa(emp, u, acesso);
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {

					gravaitemempresa(emp, u, acesso);
				}

			}

		});
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		listagem.setViewportView(table);

		listaempresa.addColumn("idempresa");
		listaempresa.addColumn("CÓDIGO");
		listaempresa.addColumn("FANTASIA");
		listaempresa.addColumn("RAZÃO SOCIAL");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela();

		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(1).setPreferredWidth(65);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setPreferredWidth(400);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		btnIncluir = new JButton();
		btnIncluir.setBounds(0, 0, 0, 0);
		btnIncluir.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				try {
					Cademp emp1 = new Cademp();
					new cadastraempresa(emp1, u, acesso).setVisible(true);

				} catch (Exception e) {

				}
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setNumRows(0);
				try {
					carregartabela();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}

		});
		// btnIncluir.setBounds(29, 423, 55, 46);
		btnIncluir.setIcon(new ImageIcon(Listaempresa.class.getResource("/imagens/incluir.png")));
		btnIncluir.setToolTipText("INCLUIR");
		aces1.butonfundo(btnIncluir);
		if (validaempresa() != null) {
			btnIncluir.setEnabled(false);
		} else {
			btnIncluir.setEnabled(true);
		}
		aces1.butonfundo(btnIncluir);
		botoes.add(btnIncluir);
		aces1.espacobotao(botoes);

		btnmodulos = new JButton();
		btnmodulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ListaModulos(u).setVisible(true);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "ERRO MODULOS: " + e1.getStackTrace());
				}

			}
		});
		btnmodulos.setToolTipText("MODULOS");
		btnmodulos.setBounds(100, 426, 55, 46);
		btnmodulos.setIcon(new ImageIcon(Listaempresa.class.getResource("/imagens/modulos.png")));
		btnmodulos.setToolTipText("MODULOS");
		aces1.butonfundo(btnmodulos);
		if (u.getLogin().equals("ADMSUPER")) {
			// contentPane.add(btnmodulos);
			GridBagConstraints gbc_btnmodulos = new GridBagConstraints();
			aces1.gbcbuton(gbc_btnmodulos);
			botoes.add(btnmodulos, gbc_btnmodulos);
			botoes.addSeparator();
		}

		btnrotinas = new JButton();
		btnrotinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ListaRotinas(u).setVisible(true);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "ERRO MODULOS: " + e1.getStackTrace());
				}

			}
		});
		btnrotinas.setToolTipText("ROTINAS");
		btnrotinas.setBounds(180, 426, 55, 46);
		btnrotinas.setIcon(new ImageIcon(Listaempresa.class.getResource("/imagens/rotinas.png")));
		btnrotinas.setToolTipText("ROTINAS");
		aces1.butonfundo(btnrotinas);
		if (u.getLogin().equals("ADMSUPER")) {
			botoes.add(btnrotinas);
			aces1.espacobotao(botoes);
		}

		btncaminho = new JButton();
		btncaminho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ListaCaminhos(u).setVisible(true);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "ERRO CAMINHOS: " + e1.getStackTrace());
				}

			}
		});
		btncaminho.setToolTipText("CAMINHOS");
		btncaminho.setBounds(260, 426, 55, 46);
		btncaminho.setIcon(new ImageIcon(Listaempresa.class.getResource("/imagens/caminho.png")));
		btncaminho.setToolTipText("CAMINHOS");
		aces1.butonfundo(btncaminho);
		if (acesso.getNivel8() == true) {
			botoes.add(btncaminho);
			aces1.espacobotao(botoes);
		}

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				table.requestFocus();
				table.changeSelection(0, 0, false, false);
			}
		});

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

		CadempBean lista = new CadempBean();
		List<Cademp> list = (List<Cademp>) lista.getCodemps();

		for (Cademp emp : list) {
			listaempresa.addRow(new Object[] { emp.getId(), emp.getCodemp(), emp.getFantasia(), emp.getRazao() });

		}

	}

	public Long validaempresa() {
		Long valida = null;

		List<Cademp> lista = new ArrayList<Cademp>();

		if (lista == null || lista.isEmpty()) {
			CadempBean cbean = new CadempBean();
			lista = cbean.getCodemps();
		}

		for (Cademp p : lista) {
			if (p.getId() != null) {
				valida = p.getId();
			}
		}
		return valida;
	}

	public void gravaitemempresa(Cademp emp, Usuario u, Acesso ap) {

		int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());

		Long idT = Long.parseLong(listaempresa.getValueAt(linhaSel, 0).toString());
		try {

			emp = E.procura(idT);

			new cadastraempresa(emp, u, ap).setVisible(true);
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			tableModel.setNumRows(0);
			carregartabela();
			table.changeSelection(0, 0, false, false);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
