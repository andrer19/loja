package Grafico.Tributos;

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
import java.awt.Toolkit;
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

import beans.AcessoBean;
import beans.TribcofinsBean;
import entidades.Tribcofins;


public class listatribcofins extends JDialog {
	public JTable table;
	public Long idT;
	public int id, linhaSel;
	TribcofinsBean l = new TribcofinsBean();
	Tribcofins cofins1 = new Tribcofins();
	AcessoBean aces1 = new AcessoBean();
	private JPanel contentPane;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listatribcofins = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}

	};

	public listatribcofins(final Tribcofins c) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(470, 250, 665, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setTitle("COFINS");
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setToolTipText("red");
		scrollPane_1
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3,
				true));
		scrollPane_1.setBounds(0, 0, 659, 260);
		setBackground(getBackground());
		setForeground(Color.BLACK);

		contentPane.add(scrollPane_1);
		table = new JTable(listatribcofins){
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
				Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
			    c.setFont(aces1.tipoletra());
			    aces1.tipocomponete(c);
				if (isCellSelected(rowIndex, vColIndex)) {
					c.setBackground(new Color(173,216,230));
				} 
				return c;
			}
		};

		aces1.tipotable(table);
		
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					linhaSel = table.getSelectedRow();

					idT = Long.parseLong(table.getValueAt(linhaSel, 0).toString());

					try {
						//l.fechaconexao();
						c.setIdtribcofins(idT);
						c.setNumero(table.getValueAt(linhaSel, 1).toString());
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

					try {

						c.setIdtribcofins(idT);
						c.setNumero(table.getValueAt(linhaSel, 1).toString());
						//l.fechaconexao();
						dispose();
					} catch (Exception e) {

						e.printStackTrace();
					}

				}

			}

		});

		scrollPane_1.setViewportView(table);

		listatribcofins.addColumn("id");
		listatribcofins.addColumn("CODIGO");
		listatribcofins.addColumn("TITULO");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		carregartabela();

		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(700);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		
		table.changeSelection(0, 0, false, false);
		
		//fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {  
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	c.setIdtribcofins(null);
	        	dispose(); 
	        }  
	    };
	   rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
	   addWindowListener(new WindowAdapter(){
	          public void windowClosing(WindowEvent e) {
	        	  c.setIdtribcofins(null);
	          	  dispose(); 
	        		}
	          });

	}

	public void carregartabela() throws Exception {

		TribcofinsBean lista = new TribcofinsBean();
		List<Tribcofins> list =  lista.lista();

		for (Tribcofins tri : list) {
			listatribcofins.addRow(new Object[] { tri.getIdtribcofins(),
					tri.getNumero(),tri.getTitulo() });
		}

	}

}
