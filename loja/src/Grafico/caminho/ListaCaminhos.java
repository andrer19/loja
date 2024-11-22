package Grafico.caminho;

import Grafico.geral.Letramaiuscula;

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
import beans.CadcaminBean;
import entidades.Cadcamin;
import entidades.Usuario;


@SuppressWarnings("serial")
public class ListaCaminhos extends JDialog {
	Font font;
	int idT;
	public JTable table;
	private JPanel contentPane;
	JButton btnIncluir;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listacaminho = new DefaultTableModel() {
		
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
			
		}
	};

	ResultSet resultado;
	Cadcamin caminho = new Cadcamin();
	CadcaminBean E = new CadcaminBean();
	AcessoBean aces1 = new AcessoBean();
	final int rows = 10;
	
	
	
	public ListaCaminhos(final Usuario u) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 345, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("CAMINHOS");
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
		listagem.setBounds(0, 0, 330, 100);
		setBackground(getBackground());
		setForeground(Color.BLACK);

		contentPane.add(listagem);
		
		table = new JTable(listacaminho){
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
					gravacaminho(caminho, u);
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {

					gravacaminho(caminho, u);
				}
				

			}

		});
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		listagem.setViewportView(table);

		listacaminho.addColumn("id");
		listacaminho.addColumn("CAMINHO");
		listacaminho.addColumn("BANCO");
		
		

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela();
		
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		

	    btnIncluir = new JButton();
		btnIncluir.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				try {
					Cadcamin mod1 = new Cadcamin();
					new cadastracaminho(mod1, u).setVisible(true);
					
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
		btnIncluir.setBounds(10, 110, 55, 46);
		btnIncluir.setIcon(new ImageIcon(ListaCaminhos.class.getResource("/imagens/incluir.png")));
		btnIncluir.setToolTipText("INCLUIR");
		aces1.butonfundo(btnIncluir);
		if(validacaminho() != null){
			btnIncluir.setEnabled(false);
		}else{
			btnIncluir.setEnabled(true);
		}
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

		CadcaminBean lista = new CadcaminBean();
		List<Cadcamin> list = lista.listaCadcamin();

		for (Cadcamin mod : list) {
			listacaminho.addRow(new Object[] { 
					mod.getId(), mod.getCaminho(), mod.getBanco()});

		}

	}
	
	public Long validacaminho() {
		Long valida = null;

		List<Cadcamin> lista = new ArrayList<Cadcamin>();

		if (lista == null || lista.isEmpty()) {
			CadcaminBean cbean = new CadcaminBean();
			lista = cbean.listaCadcamin();
		}

		for (Cadcamin p : lista) {
			if (p.getId() != null) {
				valida = p.getId();
			}
		}
		return valida;
	}
	
	public void gravacaminho(Cadcamin mod1, Usuario u) {
		
		int linhaSel = table.convertRowIndexToModel(table
				.getSelectedRow());

		Long idT = Long.parseLong(listacaminho.getValueAt(
				linhaSel, 0).toString());
		try {

			caminho = E.procura(idT);

			new cadastracaminho(caminho,u).setVisible(true);
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			tableModel.setNumRows(0);
			carregartabela();
			table.changeSelection(0, 0, false, false);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}








