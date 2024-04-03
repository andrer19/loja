package Grafico.Modulos;

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
import beans.ModulosBean;
import entidades.Cademp;
import entidades.Modulos;
import entidades.Usuario;


@SuppressWarnings("serial")
public class ListaModulos extends JDialog {
	Font font;
	int idT;
	public JTable table;
	private JPanel contentPane;
	JButton btnIncluir;
	
	private JTextField pesquisar;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listamodulo = new DefaultTableModel() {
		
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
	Modulos modulo = new Modulos();
	ModulosBean E = new ModulosBean();
	AcessoBean aces1 = new AcessoBean();
	private JLabel lblpesquisar;
	final int rows = 10;
	
	
	
	public ListaModulos(final Usuario u) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 334, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setTitle("LISTA MODULOS");
		setLocationRelativeTo(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		final JScrollPane listagem = new JScrollPane();
		listagem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		listagem.setEnabled(false);
		listagem.setToolTipText("red");
		listagem
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listagem.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3,
				true));
		listagem.setBounds(0, 45, 320, 229);
		setBackground(getBackground());
		setForeground(Color.BLACK);

		contentPane.add(listagem);
		
		lblpesquisar = new JLabel("PESQUISAR");
		lblpesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblpesquisar.setForeground(Color.BLUE);
		lblpesquisar.setBounds(7, 15, 70, 14);
		contentPane.add(lblpesquisar);
		
		pesquisar = new JTextField();
		pesquisar.setFocusTraversalKeys(
				KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
				Collections.EMPTY_SET);
		pesquisar.setForeground(Color.BLUE);
		pesquisar.setDocument(new Letramaiuscula());
		pesquisar.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void keyReleased(KeyEvent arg0) {
				int i = 0;

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
						listamodulo);

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
		pesquisar.setBounds(80, 12, 230, 20);
		contentPane.add(pesquisar);
		pesquisar.setColumns(10);
		
		table = new JTable(listamodulo){
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
				Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
			    c.setFont(aces1.tipoletra());
			    aces1.tipocomponete(c);
				if (isCellSelected(rowIndex, vColIndex)) {
					c.setBackground(new Color(173,216,230));
				} else {
					/*if (rowIndex % 2 == 0) {
						c.setForeground(new Color(28, 28, 28));
					}*/
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
					gravamodulo(modulo, u);
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {

					gravamodulo(modulo, u);
				}
				

			}

		});
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		listagem.setViewportView(table);

		listamodulo.addColumn("idmodulo");
		listamodulo.addColumn("MODULO");
		listamodulo.addColumn("");
		
		

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela();
		
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		

	    btnIncluir = new JButton();
		btnIncluir.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				try {
					Modulos mod1 = new Modulos();
					new cadastramodulo(mod1, u).setVisible(true);
					
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
		btnIncluir.setBounds(10, 284, 55, 46);
		btnIncluir.setIcon(new ImageIcon(ListaModulos.class.getResource("/imagens/incluir.png")));
		btnIncluir.setToolTipText("INCLUIR");
		aces1.butonfundo(btnIncluir);
		contentPane.add(btnIncluir);
		
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				table.requestFocus();
				table.changeSelection(0, 0, false, false);
			}
		});
		
		//fechar janela com esc
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

		ModulosBean lista = new ModulosBean();
		List<Modulos> list = lista.listaModulos();

		for (Modulos mod : list) {
			listamodulo.addRow(new Object[] { 
					mod.getIdmodulo(), mod.getModulo(), mod.getMostra()});

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
	
	public void gravamodulo(Modulos mod1, Usuario u) {
		
		int linhaSel = table.convertRowIndexToModel(table
				.getSelectedRow());

		Long idT = Long.parseLong(listamodulo.getValueAt(
				linhaSel, 0).toString());
		try {

			modulo = E.altera(idT);

			new cadastramodulo(modulo,u).setVisible(true);
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			tableModel.setNumRows(0);
			carregartabela();
			table.changeSelection(0, 0, false, false);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}








