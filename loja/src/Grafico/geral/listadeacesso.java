package Grafico.geral;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
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
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;

import Grafico.Rotinas.ListaprocuraRotinas;
import beans.AcessoBean;
import beans.Usuariobean;
import entidades.Acesso;
import entidades.Usuario;

import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JRadioButton;

public class listadeacesso extends JDialog {
	JTable table;
	JPanel contentPane;
	private JToolBar botoes;

	private JTextField pesquisar;

	JCheckBox chckbxacesso, chinclusao, chcalteracao, chcexclusao, chcnivel4;

	JButton btnsair, btnnovoacesso;

	Usuario user = new Usuario();
	Acesso ne1 = new Acesso();
	AcessoBean ace = new AcessoBean();
	Usuariobean userbean = new Usuariobean();

	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
	DefaultTableModel listadeacesso = new DefaultTableModel() {

		public Class<?> getColumnClass(int col) {
			if (col == 2) {
				return Boolean.class;
			}

			if (col == 3) {
				return Boolean.class;
			}

			if (col == 4) {
				return Boolean.class;
			}
			if (col == 5) {
				return Boolean.class;
			}
			if (col == 6) {
				return Boolean.class;
			}
			if (col == 7) {
				return Boolean.class;
			}
			if (col == 8) {
				return Boolean.class;
			}
			if (col == 9) {
				return Boolean.class;
			}

			if (col == 10) {
				return Boolean.class;
			}

			return super.getColumnClass(col);
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return false;

		}
	};

	public listadeacesso(final Usuario userselecionado, Usuario usersesao) throws Exception {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(470, 250, 847, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setTitle("DIREITOS");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 31, 832, 237);
		contentPane.add(scrollPane);

		JLabel lblpesquisar = new JLabel("PESQUISAR");
		lblpesquisar.setForeground(Color.BLUE);
		lblpesquisar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpesquisar.setBounds(10, 10, 78, 14);
		contentPane.add(lblpesquisar);

		pesquisar = new JTextField();
		pesquisar.setForeground(Color.BLUE);
		pesquisar.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void keyReleased(KeyEvent arg0) {
				int i = 0;

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(listadeacesso);

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

		});
		pesquisar.setDocument(new Configuracao.Letramaiuscula());
		pesquisar.setBounds(98, 7, 200, 20);
		contentPane.add(pesquisar);

		JLabel lblusuario = new JLabel(userselecionado.getNome());
		lblusuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblusuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblusuario.setBounds(300, 4, 520, 26);
		contentPane.add(lblusuario);

		botoes = new JToolBar();
		botoes.setLocation(0, 268);
		botoes.setSize(830, 60);
		ace.menubotao(botoes);
		contentPane.add(botoes, BorderLayout.CENTER);
		espacosprimeiroboto();

		table = new JTable(listadeacesso) {
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
				Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
				c.setFont(ace.tipoletra());
				ace.tipocomponete(c);
				if (isCellSelected(rowIndex, vColIndex)) {
					c.setBackground(ace.corcomponente());
				}

				if (rowIndex % 2 == 0) {
					c.setForeground(new Color(28, 28, 28));
				} else {
					c.setBackground(new Color(220, 220, 220));
				}

				return c;
			}
		};

		ace.tipotable(table);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					gravaacesso(userselecionado);
				}

				if (evt.getKeyCode() == KeyEvent.VK_TAB) {
					btnsair.requestFocus();
				}

				if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
					excluiacesso(userselecionado);
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				if (e.getClickCount() == 2) {
					gravaacesso(userselecionado);
				}
			}
		});

		scrollPane.setViewportView(table);

		listadeacesso.addColumn("idacesso");
		listadeacesso.addColumn("MODULO");
		listadeacesso.addColumn("ACESSO");
		listadeacesso.addColumn("INCLUSAO");
		listadeacesso.addColumn("ALTERACAO");
		listadeacesso.addColumn("EXCLUSAO");
		listadeacesso.addColumn("NIVEL5");
		listadeacesso.addColumn("NIVEL6");
		listadeacesso.addColumn("NIVEL7");
		listadeacesso.addColumn("NIVEL8");

		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setNumRows(0);
		carregartabela(userselecionado);

		table.getColumnModel().getColumn(1).setPreferredWidth(290);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(6).setPreferredWidth(55);
		table.getColumnModel().getColumn(7).setPreferredWidth(55);
		table.getColumnModel().getColumn(8).setPreferredWidth(55);
		table.getColumnModel().getColumn(9).setPreferredWidth(55);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		btnnovoacesso = new JButton();
		btnnovoacesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rotinaspendente(userselecionado);
			}
		});
		btnnovoacesso.setToolTipText("INCLUIR NOVO ACESSO");
		btnnovoacesso.setIcon(new ImageIcon(TelaLogin.class.getResource("/imagens/incluir.png")));
		ace.butonfundo(btnnovoacesso);
		botoes.add(btnnovoacesso);
		ace.espacobotao(botoes);

		btnsair = new JButton();
		btnsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnsair.setIcon(new ImageIcon(TelaLogin.class.getResource("/imagens/fechar.png")));
		ace.butonfundo(btnsair);
		btnsair.setToolTipText("SAIR");
		botoes.add(btnsair);
		ace.espacobotao(botoes);

		JButton btnacessogeral = new JButton();
		btnacessogeral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String nome = "Deseja realmente permitir acesso geral para os modulos da lista para o Usuario " + userselecionado.getNome().trim() + " ?";
					int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, "Permitir ",
							JOptionPane.YES_NO_OPTION);
					if (opcao_escolhida == JOptionPane.YES_OPTION) {
						ace.gravatodos(userselecionado, usersesao);
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.setNumRows(0);
						carregartabela(userselecionado);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnacessogeral.setIcon(new ImageIcon(TelaLogin.class.getResource("/imagens/todos.png")));
		btnacessogeral.setToolTipText("PERMITIR TODOS");
		ace.butonfundo(btnacessogeral);
		botoes.add(btnacessogeral);

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

	public void carregartabela(Usuario user) throws Exception {

		AcessoBean lista = new AcessoBean();
		List<Acesso> list = lista.listaacesso(user);

		for (Acesso acesso : list) {
			listadeacesso.addRow(new Object[] { acesso.getId(), acesso.getRotina().getPagina(), acesso.getAcesso(),
					acesso.getInclusao(), acesso.getAlteracao(), acesso.getExclusao(), acesso.getNivel5(),
					acesso.getNivel6(), acesso.getNivel7(), acesso.getNivel8() });

		}
	}

	public void gravaacesso(Usuario u1) {

		int linhaSel = table.convertRowIndexToModel(table.getSelectedRow());

		Long idT = Long.parseLong(listadeacesso.getValueAt(linhaSel, 0).toString());

		try {
			ne1 = ace.procura(idT);

			new cadastraacesso(ne1).setVisible(true);
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			tableModel.setNumRows(0);
			carregartabela(u1);

			table.changeSelection(linhaSel, 0, false, false);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void rotinaspendente(Usuario u1) {

		try {
			new ListaprocuraRotinas(u1).setVisible(true);
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			tableModel.setNumRows(0);
			carregartabela(u1);
			table.changeSelection(0, 0, false, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ERRO: " + e.getStackTrace());
		}
	}

	public void excluiacesso(Usuario u1) {
		if (table.getSelectedRow() != -1 && table.getRowCount() != 0) {
			int linha = table.convertRowIndexToModel(table.getSelectedRow());
			int selectedRow = table.getSelectedRow();
			try {

				String nome = "Deseja realmente excluir o acesso: " + table.getValueAt(linha, 1).toString() + " ?";
				int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, "Exclusão ", JOptionPane.YES_NO_OPTION);
				if (opcao_escolhida == JOptionPane.YES_OPTION) {
					int conseguiu_excluir = 1;
					ace.remove(Long.parseLong(table.getValueAt(linha, 0).toString()));

					if (conseguiu_excluir == 1) {
						listadeacesso.removeRow(linha);
						table.setModel(listadeacesso);
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						tableModel.setNumRows(0);
						carregartabela(u1);
						table.changeSelection(0, 0, false, false);
					}
				} else {
					return;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR ACESSO: " + e.getStackTrace());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um nome para excluir");
		}

	}

	public void espacosprimeiroboto() {

		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);
		ace.espacobotao(botoes);

	}
}
