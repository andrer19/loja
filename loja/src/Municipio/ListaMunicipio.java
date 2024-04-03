package Municipio;

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
import beans.MunicipBean;
import entidades.Cademp;
import entidades.Municip;
import entidades.Usuario;

@SuppressWarnings("serial")
public class ListaMunicipio extends JDialog {
	Font font;
	Long idT;
	public int linhaSel;
	public String codigoibge;
	public JTable table;
	private JPanel contentPane;

	private JTextField procuramunicipio;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listamunicipio = new DefaultTableModel() {

		public Class<?> getColumnClass(int col) {
			if (col == 2) {
				return Boolean.class;
			}

			return super.getColumnClass(col);
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};

	ResultSet resultado;
	Municip municipio = new Municip();
	MunicipBean E = new MunicipBean();
	AcessoBean aces1 = new AcessoBean();
	private JLabel lblprocura;
	final int rows = 10;

	public ListaMunicipio(final Municip m4) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 493, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setTitle("MUNICIPIO");
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		final JScrollPane listagem = new JScrollPane();
		listagem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		listagem.setEnabled(false);
		listagem.setToolTipText("red");
		listagem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listagem.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		listagem.setBounds(0, 45, 477, 229);
		setBackground(getBackground());
		setForeground(Color.BLACK);

		contentPane.add(listagem);

		table = new JTable(listamunicipio) {
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
				Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
				c.setFont(aces1.tipoletra());
				aces1.tipocomponete(c);
				if (isCellSelected(rowIndex, vColIndex)) {
					c.setBackground(new Color(173, 216, 230));
				} else {
					
				}
				return c;
			}
		};

		aces1.tipotable(table);

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					linhaSel = table.getSelectedRow();

					idT = Long.parseLong(table.getValueAt(linhaSel, 0).toString());

					codigoibge = table.getValueAt(linhaSel, 3).toString();

					try {
                       
						m4.setId(idT);
						m4.setCod_ibge(codigoibge.trim());
						dispose();
					} catch (Exception e1) {

						e1.printStackTrace();
					}
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {

					linhaSel = table.getSelectedRow();

					idT = Long.parseLong(table.getValueAt(linhaSel, 0).toString());

					codigoibge = table.getValueAt(linhaSel, 3).toString();

					try {
						
						m4.setId(idT);
						m4.setCod_ibge(codigoibge.trim());
						dispose();
					} catch (Exception e) {

						e.printStackTrace();
					}
				}

			}

		});
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		listagem.setViewportView(table);

		listamunicipio.addColumn("idmunicipio");
		listamunicipio.addColumn("CIDADE");
		listamunicipio.addColumn("ESTADO");
		listamunicipio.addColumn("IBGE");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela();

		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		procuramunicipio = new JTextField();
		procuramunicipio.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		procuramunicipio.setForeground(Color.BLUE);
		procuramunicipio.setDocument(new Letramaiuscula());
		procuramunicipio.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void keyReleased(KeyEvent arg0) {
				int i = 0;

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listamunicipio);

				TableModel model = (TableModel) table.getModel();

				sorter = new TableRowSorter<TableModel>(model);
				table.setRowSorter(sorter);

				String text = procuramunicipio.getText();
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
		procuramunicipio.setBounds(65, 12, 388, 20);
		contentPane.add(procuramunicipio);
		procuramunicipio.setColumns(10);

		lblprocura = new JLabel("PROCURA");
		lblprocura.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblprocura.setBounds(7, 15, 50, 14);
		contentPane.add(lblprocura);

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

		MunicipBean lista = new MunicipBean();
		List<Municip> list = lista.getMunicipios();

		for (Municip mod : list) {
			listamunicipio.addRow(new Object[] { mod.getId(), mod.getNome(), mod.getUf(), mod.getCod_ibge() });

		}

	}

}
