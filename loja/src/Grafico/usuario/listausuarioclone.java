package Grafico.usuario;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Grafico.cademp.cadastraempresa;
import Grafico.geral.TelaLogin;
import Grafico.geral.listadeacesso;
import beans.AcessoBean;
import beans.Usuariobean;
import entidades.Acesso;
import entidades.Usuario;
import java.awt.event.KeyAdapter;

@SuppressWarnings("serial")
public class listausuarioclone extends JDialog {

	public JTable table;
	Usuariobean userbean = new Usuariobean();
	private JPanel contentPane;
	AcessoBean aces1 = new AcessoBean();
	public Long idT;
	public int linhaSel;
	DefaultTableModel listadeusuario = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	Font font = new Font("arial", 0, 14);
	ResultSet resultado;
	Usuario user = new Usuario();
	private JTextField procurauser;


	public listausuarioclone(final Usuario u) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(470, 250, 547, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setTitle("USUARIOS CLONE");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 208, 2, 2);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setToolTipText("red");
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		scrollPane_1.setBounds(1, 37, 531, 226);
		setBackground(getBackground());
		setForeground(Color.BLACK);

		contentPane.add(scrollPane_1);
		table = new JTable(listadeusuario) {
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
				Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
				c.setFont(aces1.tipoletra());
				aces1.tipocomponete(c);
				if (isCellSelected(rowIndex, vColIndex)) {
					c.setBackground(aces1.corcomponente());
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

					try {
						u.setId(idT);
						dispose();
					} catch (Exception e1) {

						e1.printStackTrace();
					}
					
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					
					linhaSel = table.getSelectedRow();

					idT = Long.parseLong(table.getValueAt(linhaSel, 0).toString());

					try {
						u.setId(idT);
						dispose();
					} catch (Exception e1) {

						e1.printStackTrace();
					}
					
				}

			}

		});

		scrollPane_1.setViewportView(table);

		listadeusuario.addColumn("idusuario");
		listadeusuario.addColumn("LOGIN");
		listadeusuario.addColumn("NOME");

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela();

		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(158);
		table.getColumnModel().getColumn(2).setPreferredWidth(350);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		
		JLabel lblpesquisar = new JLabel("PESQUISAR");
		lblpesquisar.setForeground(Color.BLUE);
		lblpesquisar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpesquisar.setBounds(10, 15, 78, 14);
		contentPane.add(lblpesquisar);
		
		procurauser = new JTextField();
		procurauser.setForeground(Color.BLUE);
		procurauser.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void keyReleased(KeyEvent arg0) {
				int i = 0;

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listadeusuario);

				TableModel model = (TableModel) table.getModel();

				sorter = new TableRowSorter<TableModel>(model);
				table.setRowSorter(sorter);

				String text = procurauser.getText();
				if (text.length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter(text));
				}

			}

		});
		procurauser.setDocument(new Grafico.geral.Letramaiuscula());
		procurauser.setColumns(10);
		procurauser.setBounds(93, 11, 400, 20);
		contentPane.add(procurauser);

		table.changeSelection(0, 0, false, false);

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

		Usuariobean lista = new Usuariobean();
		List<Usuario> list = (List<Usuario>) lista.getUsuariosclone();

		for (Usuario user : list) {
			listadeusuario.addRow(new Object[] { user.getId(), user.getLogin(), user.getNome() });

		}

	}
	
}
