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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

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
import entidades.Cadosc;
import beans.AcessoBean;
import beans.CadcliBean;
import beans.CadosiBean;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class listaservicocporliente extends JDialog {
	public JTable table;
	
	public int linhaSel;
	CadcliBean clibean = new CadcliBean();
	AcessoBean aces1 = new AcessoBean();
	
	private JButton btnimprimir;
	private JPanel contentPane;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listaservicocliente = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};
	private JTextField pesquisar;
	CadosiBean itens = new CadosiBean();
	
	Locale local1 = new Locale("pt", "BR");
	DateFormat datestring = new SimpleDateFormat("dd/MM/yyyy", local1);

	public listaservicocporliente(Long idclientep) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(470, 250, 438, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setTitle("LISTA ORDEM DE SERVI�O ANTIGOS POR CLIENTE");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setToolTipText("red");
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		scrollPane_1.setBounds(0, 43, 423, 226);
		setBackground(getBackground());
		setForeground(Color.BLACK);
		contentPane.add(scrollPane_1);

		JLabel lblpesquisar = new JLabel("PESQUISAR");
		lblpesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblpesquisar.setForeground(Color.BLUE);
		lblpesquisar.setBounds(10, 18, 70, 14);
		contentPane.add(lblpesquisar);

		pesquisar = new JTextField();
		pesquisar.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		pesquisar.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void keyTyped(KeyEvent evt) {
				String caracteres = "0987654321";
				char charTeste = '"';
				String especiais = "<>:;?/{}[]!@#$%�&*()_-+=,.�~^`'" + charTeste;
				if (especiais.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
				int i = 0;

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listaservicocliente);

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
		pesquisar.setBounds(80, 15, 320, 20);
		pesquisar.setDocument(new Letramaiuscula());
		contentPane.add(pesquisar);

		table = new JTable(listaservicocliente) {
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
				
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnimprimir.requestFocus();
				}
			}
		});

		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		table.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(table);

		listaservicocliente.addColumn("id");
		listaservicocliente.addColumn("SERVI�O");
		listaservicocliente.addColumn("CLIENTE");
		listaservicocliente.addColumn("EMISSAO");
		listaservicocliente.addColumn("VALOR");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);

		carregartabela(idclientep);

		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		
		btnimprimir = new JButton("");
		btnimprimir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					selecionapedido();
				}
				
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					pesquisar.requestFocus();
				}
			}
		});
		btnimprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionapedido();
			}
		});
		btnimprimir.setIcon(new ImageIcon(listadecliente.class.getResource("/imagens/A4.png")));
		btnimprimir.setToolTipText("INCLUIR");
		aces1.butonfundo(btnimprimir);
		btnimprimir.setBounds(170, 272, 55, 46);
		contentPane.add(btnimprimir);

		pesquisar.requestFocus();
		table.changeSelection(0, 0, false, false);
		
		

		// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

	}

	public void carregartabela(Long idp) throws Exception {

		CadcliBean lista = new CadcliBean();
		List<Cadosc> list = lista.Listaservicoantigos(idp);

		for (Cadosc servicos : list) {
			listaservicocliente.addRow(new Object[] { servicos.getId(), servicos.getNumdoc(),
					servicos.getCliente().getDESCCLI(), datestring.format(servicos.getEmissao()),
			aces1.valordinheiro(servicos.getVrtot()) });
		}

	}

	public void selecionapedido() {
		if (table.getSelectedRow() != -1 && table.getRowCount() > 0) {
			linhaSel = table.getSelectedRow();
			try {
				itens.imprimirA4(Long.parseLong(table.getValueAt(linhaSel, 0).toString()));
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "ERRO AO IMPRIMIR ORDEM DE SERVI�O POR CLIENTE " + e1.getMessage());
				aces1.demologger.error("ERRO AO IMPRIMIR ORDEM DE SERVI�O POR CLIENTE " + e1.getMessage());
			}
		}else {
			JOptionPane.showMessageDialog(null, "SELECIONE UM CLIENTE !!!!!!");
		}

	}
}
