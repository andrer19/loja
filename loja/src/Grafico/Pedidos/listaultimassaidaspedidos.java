package Grafico.Pedidos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Locale;


import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import entidades.Pdenti;
import entidades.Pdsaii;
import entidades.Usuario;
import beans.AcessoBean;
import beans.PdentiBean;
import beans.PdsaiiBean;


public class listaultimassaidaspedidos extends JDialog {
	Font font;
	int idT;
	public JTable table;
	private JPanel contentPane;
	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	public DefaultTableModel listahistoricopdsaii = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
			
		}
		
			

		

	};
	ResultSet resultado;
	Pdenti pdenti = new Pdenti();
	PdentiBean pdentibean = new PdentiBean();
    AcessoBean aces1 = new AcessoBean();
    Locale local1 = new Locale("pt", "BR");
	DateFormat datestring = new SimpleDateFormat("dd/MM/yyyy", local1);
	public listaultimassaidaspedidos(final Usuario u, final String produto) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 690, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setTitle("ULTIMAS SAIDAS PRODUTO");
		setLocationRelativeTo(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setToolTipText("Lista Produto");
		scrollPane_1
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3,
				true));
		scrollPane_1.setBounds(0, 2, 677, 400);
		setBackground(getBackground());
		setForeground(Color.BLACK);

		contentPane.add(scrollPane_1);

		table = new JTable(listahistoricopdsaii){
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

		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setViewportView(table);

		listahistoricopdsaii.addColumn("IDPRODUTO");
		listahistoricopdsaii.addColumn("PEDIDO");
		listahistoricopdsaii.addColumn("CLIENTE");
		listahistoricopdsaii.addColumn("PRODUTO");
		listahistoricopdsaii.addColumn("QTDE");
		listahistoricopdsaii.addColumn("UNIDADE");
		listahistoricopdsaii.addColumn("UNITARIO");
		listahistoricopdsaii.addColumn("TOTAL");
		

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela(produto);
        
		table.getColumnModel().getColumn(1).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(7).setPreferredWidth(150);
		table.getColumnModel().getColumn(7).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.changeSelection(0, 0, false, false);
		
		//fechar janela com esc
				JRootPane rootPane = this.getRootPane();
				KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
				ActionListener cancelAction = new java.awt.event.ActionListener() {  
		            public void actionPerformed(java.awt.event.ActionEvent evt) {  
		            	//pdentibean.fechaconexao();
						dispose(); 
		            }  
		        };
		       rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
		       
		       addWindowListener(new WindowAdapter(){
			          public void windowClosing(WindowEvent e) {
			        	 // pdentibean.fechaconexao();
						  dispose();
			        }
			          });

	}
	
	
	

	public void carregartabela(String produto) throws Exception {

		PdsaiiBean lista = new PdsaiiBean();
		List<Pdsaii> list = (List<Pdsaii>) lista.ultsai(produto);
		DecimalFormat format = new DecimalFormat("##");
		Locale BRAZIL = new Locale("pt", "BR");
		DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
		DecimalFormat moeda = new DecimalFormat("R$ ###,###,##0.00", REAL);

		for (Pdsaii prod : list) {
			listahistoricopdsaii.addRow(new Object[] { prod.getIdpdsaii(),
					prod.getPedido(),prod.getPedc().getCliente().getDESCCLI(), prod.getProduto(),
					String.format("%.0f", prod.getQuantidade()),prod.getUn(), 
					moeda.format(prod.getUnitario()), moeda.format(prod.getVrtot())					
			});

		}

	}
}







