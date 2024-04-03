package Grafico.Produto;

import entidades.Cadpro;
import filter.EntityManagerUtil;
import Grafico.geral.Letramaiuscula;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import beans.AcessoBean;
import beans.CadproBean;

public class listaprocuraproduto extends JDialog {
	public JTable table;
	CadproBean l = new CadproBean();
	Cadpro pro1 = new Cadpro();
	private JPanel contentPane;
	AcessoBean aces1 = new AcessoBean();
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listaprodutos = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};
	private JTextField perquisar;

	public listaprocuraproduto(final Cadpro p) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(470, 250, 438, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setTitle("LISTA PROCURA PRODUTO");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setToolTipText("red");
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		scrollPane_1.setBounds(0, 53, 423, 226);
		setBackground(getBackground());
		setForeground(Color.BLACK);
		contentPane.add(scrollPane_1);

		JLabel lblpesquisar = new JLabel("PESQUISAR");
		lblpesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblpesquisar.setForeground(Color.BLUE);
		lblpesquisar.setBounds(10, 25, 70, 14);
		contentPane.add(lblpesquisar);

		perquisar = new JTextField();
		perquisar.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		perquisar.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void keyTyped(KeyEvent evt) {
				char charTeste = '"';
				String especiais = "<>:;?/{}[]!@#$%ï¿½&*()_-+=,ï¿½~^`'" + charTeste;
				if (especiais.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
				int i = 0;

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listaprodutos);

				TableModel model = (TableModel) table.getModel();

				sorter = new TableRowSorter<TableModel>(model);
				table.setRowSorter(sorter);

				String text = perquisar.getText();
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
		perquisar.setFont(new Font("Tahoma", Font.BOLD, 12));
		perquisar.setForeground(Color.BLUE);
		perquisar.setBounds(80, 22, 300, 20);
		perquisar.setDocument(new Letramaiuscula());
		contentPane.add(perquisar);

		table = new JTable(listaprodutos) {
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

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					produtoselecionado(p);
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					produtoselecionado(p);
				}

			}

		});

		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setViewportView(table);

		listaprodutos.addColumn("id");
		listaprodutos.addColumn("NUMERO");
		listaprodutos.addColumn("DESCRIÇÂO");
		listaprodutos.addColumn("UN");
		listaprodutos.addColumn("VALOR");
		listaprodutos.addColumn("QTATUAL");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela();

		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		perquisar.requestFocus();
		table.changeSelection(0, 0, false, false);

		// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				p.setIdcadpro(null);
				// l.fechaconexao();
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p.setIdcadpro(null);
				// l.fechaconexao();
				dispose();
			}
		});

	}

	public void carregartabela() throws Exception {

		CadproBean lista = new CadproBean();
		List<Cadpro> list = (List<Cadpro>) lista.getprodutoativos();

		for (Cadpro forn : list) {
			listaprodutos.addRow(new Object[] { forn.getIdcadpro(), forn.getCODPRO(), forn.getDESCPRO(), forn.getUN(),
					forn.getVRVENDA(), forn.getQTATUAL() });

		}

	}

	public void produtoselecionado(Cadpro cadpro) {
		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			int linhaSel = table.getSelectedRow();

			Cadpro prod = new Cadpro();

			Long idT = Long.parseLong(table.getValueAt(linhaSel, 0).toString());
			String codpro = table.getValueAt(linhaSel, 1).toString();
			String descpro = table.getValueAt(linhaSel, 2).toString();
			String un = table.getValueAt(linhaSel, 3).toString();
			Double vrvenda = Double.parseDouble(table.getValueAt(linhaSel, 4).toString());
			Double QTATUAL = Double.parseDouble(table.getValueAt(linhaSel, 5).toString());

			prod = l.buscaproduto(codpro);

			cadpro.setIdcadpro(idT);
			cadpro.setCODPRO(codpro);
			cadpro.setDESCPRO(descpro);
			cadpro.setUN(un);
			cadpro.setVRVENDA(vrvenda);
			cadpro.setQTATUAL(QTATUAL);
			cadpro.setVRCOMPRA(prod.getVRCOMPRA());
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um produto!!!!!!");
		}

	}

}
