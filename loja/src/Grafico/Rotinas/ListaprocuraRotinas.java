package Grafico.Rotinas;


import Configuracao.Letramaiuscula;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
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
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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

import beans.AcessoBean;
import beans.CadempBean;
import beans.RotinasBean;
import entidades.Acesso;
import entidades.Cademp;
import entidades.Rotinas;
import entidades.Usuario;

@SuppressWarnings("serial")
public class ListaprocuraRotinas extends JDialog {
	Font font;
	int idT;
	public JTable table;
	private JPanel contentPane;
	JButton btncadastrar;

	private JTextField pesquisar;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listarotinas = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};

	ResultSet resultado;
	Rotinas rotina = new Rotinas();
	RotinasBean E = new RotinasBean();
	AcessoBean aces1 = new AcessoBean();
	private JLabel lblpesquisar;
	final int rows = 10;

	public ListaprocuraRotinas(final Usuario u) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 451, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setTitle("ACESSO NOVO");
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		final JScrollPane listagem = new JScrollPane();
		listagem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		listagem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listagem.setEnabled(false);
		listagem.setToolTipText("red");
		listagem.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		listagem.setBounds(0, 43, 438, 229);
		setBackground(getBackground());
		setForeground(Color.BLACK);

		contentPane.add(listagem);

		table = new JTable(listarotinas) {
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
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		listagem.setViewportView(table);

		listarotinas.addColumn("id");
		listarotinas.addColumn("PAGINA");
		listarotinas.addColumn("MODULO");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela(u);

		table.getColumnModel().getColumn(1).setPreferredWidth(450);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(450);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		pesquisar = new JTextField();
		pesquisar.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		pesquisar.setForeground(Color.BLUE);
		pesquisar.setDocument(new Letramaiuscula());
		pesquisar.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void keyReleased(KeyEvent arg0) {
				int i = 0;

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listarotinas);

				TableModel model = (TableModel) table.getModel();

				sorter = new TableRowSorter<TableModel>(model);
				table.setRowSorter(sorter);

				String text = pesquisar.getText();
				if (text.length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter(text));
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					table.requestFocus();
					table.changeSelection(0, 0, false, false);
				}
			}
		});
		pesquisar.setBounds(85, 12, 325, 20);
		contentPane.add(pesquisar);
		pesquisar.setColumns(10);

		lblpesquisar = new JLabel("PESQUISAR");
		lblpesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblpesquisar.setForeground(Color.BLUE);
		lblpesquisar.setBounds(15, 15, 70, 14);
		contentPane.add(lblpesquisar);

		btncadastrar = new JButton("");
		btncadastrar.addKeyListener(new KeyAdapter() {
			public void keypressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					// btnsair.requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                   gravanovoacesso(u);
				}
			}
		});
		btncadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravanovoacesso(u);
			}
		});
		btncadastrar.setIcon(new ImageIcon(ListaprocuraRotinas.class.getResource("/imagens/salvar.png")));
		btncadastrar.setBounds(29, 298, 55, 46);
		aces1.butonfundo(btncadastrar);
		contentPane.add(btncadastrar);

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

	public void carregartabela(Usuario user) throws Exception {

		RotinasBean lista = new RotinasBean();
		List<Rotinas> list = lista.getRotinas1(user);

		for (Rotinas mod : list) {
			listarotinas.addRow(new Object[] { mod.getId(), mod.getPagina(), mod.getModulo() });

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

	public void gravanovoacesso(Usuario user) {
		//JOptionPane.showMessageDialog(null, "Selecione " + table.getSelectedRow());
		//if (table.getSelectedRow() != -1 && table.getSelectedRow() != 0) {
		if (table.getSelectedRow() != -1) {
			int linha = table.getSelectedRow();
			Rotinas rotina1 = new Rotinas();
			RotinasBean e1 = new RotinasBean();
			

			try {
				rotina1 = e1.procura(Long.parseLong(table.getValueAt(linha, 0).toString()));
				Acesso acesson = new Acesso();
				
				acesson.setNivel5(false);
				acesson.setNivel6(false);
				acesson.setNivel7(false);
				acesson.setNivel8(false);
				acesson.setNivel5(false);
				acesson.setAcesso(true);
				acesson.setInclusao(false);
				acesson.setAlteracao(false);
				acesson.setExclusao(false);
				acesson.setRotina(rotina1);
				acesson.setUsuario(user);
				acesson.setModulo(rotina1.getModulo());
				
				aces1.adiciona(acesson);
				dispose();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um Modulo!!!!!!!");
		}

	}
}
