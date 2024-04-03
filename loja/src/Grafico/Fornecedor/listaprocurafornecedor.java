package Grafico.Fornecedor;

import Grafico.geral.Letramaiuscula;

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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import entidades.Cadfor;
import beans.AcessoBean;
import beans.CadforBean;

public class listaprocurafornecedor extends JDialog {
	public JTable table;
	public Long idT;
	public int id, linhaSel;
	CadforBean l = new CadforBean();
	Cadfor for1 = new Cadfor();
	private JPanel contentPane;
	AcessoBean aces1 = new AcessoBean();
	public DefaultTableModel listafornecedores = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};
	private JTextField pesquisar;

	public listaprocurafornecedor(final Cadfor f) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(470, 250, 439, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setTitle("LISTA DE FORNECEDORES");

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

		pesquisar = new JTextField();
		pesquisar.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		pesquisar.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void keyTyped(KeyEvent evt) {
				String caracteres = "0987654321";
				char charTeste = '"';
				String especiais = "<>:;?/{}[]!@#$%¨&*()_-+=,.´~^`'" + charTeste;
				if (caracteres.contains(evt.getKeyChar() + "") || especiais.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
				int i = 0;

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listafornecedores);

				TableModel model = (TableModel) table.getModel();

				sorter = new TableRowSorter<TableModel>(model);
				table.setRowSorter(sorter);

				String text = pesquisar.getText();
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
		pesquisar.setFont(new Font("Tahoma", Font.BOLD, 12));
		pesquisar.setForeground(Color.BLUE);
		pesquisar.setBounds(90, 22, 310, 20);
		pesquisar.setDocument(new Letramaiuscula());
		contentPane.add(pesquisar);

		table = new JTable(listafornecedores) {
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
					selecionafornecedor(f);
				}
				
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					pesquisar.requestFocus();
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					selecionafornecedor(f);
				}

			}

		});

		scrollPane_1.setViewportView(table);

		listafornecedores.addColumn("idfornecedor");
		listafornecedores.addColumn("NUMERO");
		listafornecedores.addColumn("DESCRIÇÂO");
		listafornecedores.addColumn("");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);

		carregartabela();

		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		// table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				f.setIDFORNECEDOR(null);
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setIDFORNECEDOR(null);
				dispose();
			}
		});

	}

	public void carregartabela() throws Exception {

		CadforBean lista = new CadforBean();
		List<Cadfor> list = (List<Cadfor>) lista.getCadfors();

		for (Cadfor forn : list) {
			listafornecedores.addRow(new Object[] { forn.getIDFORNECEDOR(), forn.getCODFOR(), forn.getRAZAO(),
					forn.getCGC(), forn.getIE() });
		}

	}

	public void selecionafornecedor(Cadfor fornece) {
		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			linhaSel = table.getSelectedRow();

			idT = Long.parseLong(table.getValueAt(linhaSel, 0).toString());

			try {
				Cadfor f1 = new Cadfor();
				f1 = l.procura(idT);
				if (f1.getNIVEL().toString().replace(".0", "").equals("3")) {
					JOptionPane.showMessageDialog(null, "Fornecedor Desativado!!!");
				} else {
					fornece.setIDFORNECEDOR(idT);
					fornece.setCODFOR(f1.getCODFOR());
					fornece.setDESCFOR(f1.getDESCFOR());
					fornece.setNIVEL(f1.getNIVEL());
					dispose();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO AO SELECIONAR O FORNECEDOR " + e.getMessage());
				aces1.demologger.error("ERRO AO SELECIONAR O FORNECEDOR " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um fornecedor!!!!!!");
		}

	}

}
