package Grafico.Cadcli;

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
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import entidades.Cadcli;
import beans.AcessoBean;
import beans.CadcliBean;

public class listaprocuracliente extends JDialog {
	public JTable table;
	public Long idT;
	public int id, linhaSel;
	CadcliBean l = new CadcliBean();
	Cadcli cli1 = new Cadcli();
	AcessoBean aces1 = new AcessoBean();
	private JPanel contentPane;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listaclientes = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};
	private JTextField pesquisar;

	public listaprocuracliente(final Cadcli c) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(470, 250, 439, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setTitle("LISTA PROCURA DE CLIENTES");

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
				String especiais = "<>:;?/{}[]!@#$%ï¿½&*()_-+=,.ï¿½~^`'" + charTeste;
				if (caracteres.contains(evt.getKeyChar() + "") || especiais.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
				int i = 0;

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listaclientes);

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
		pesquisar.setBounds(80, 22, 320, 20);
		pesquisar.setDocument(new Letramaiuscula());
		contentPane.add(pesquisar);

		table = new JTable(listaclientes) {
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
				Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
				c.setFont(aces1.tipoletra());
				aces1.tipocomponete(c);
				if (isCellSelected(rowIndex, vColIndex)) {
					c.setBackground(new Color(173, 216, 230));
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
					selecionacliente(c);
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
					selecionacliente(c);
				}

			}

		});

		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		table.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(table);

		listaclientes.addColumn("id");
		listaclientes.addColumn("NUMERO");
		listaclientes.addColumn("DESCRIÇÃO");
		listaclientes.addColumn("");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);

		carregartabela();

		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(65);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		pesquisar.requestFocus();
		table.changeSelection(0, 0, false, false);

		// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				c.setIdcadcli(Long.parseLong("0"));
				// l.fechaconexao();
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				c.setIdcadcli(Long.parseLong("0"));
				// l.fechaconexao();
				dispose();
			}
		});

	}

	public void carregartabela() throws Exception {

		CadcliBean lista = new CadcliBean();
		List<Cadcli> list = (List<Cadcli>) lista.getClientesativos();

		for (Cadcli cli : list) {
			listaclientes.addRow(new Object[] { cli.getIdcadcli(), cli.getCODCLI(), cli.getDESCCLI() });
		}

	}

	public void selecionacliente(Cadcli clip) {
		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			linhaSel = table.getSelectedRow();

			idT = Long.parseLong(table.getValueAt(linhaSel, 0).toString());

			try {
				clip.setIdcadcli(idT);
				dispose();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "ERRO AO SELECIONAR O CLIENTE " + e1.getMessage());
				aces1.demologger.error("ERRO AO SELECIONAR O CLIENTE " + e1.getMessage());
			}
		}else {
			JOptionPane.showMessageDialog(null, "Selecione um Cliente !!!!!!");
		}

	}

}
