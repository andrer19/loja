package Grafico.geral;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import entidades.Acesso;
import entidades.Cadpro;
import entidades.Pdentc;
import entidades.Pdenti;
import beans.AcessoBean;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class cadastraacesso extends JDialog {
	private JPanel contentPane;
	int idproduto1;
	Font font = new Font("arial", 0, 14);
	JButton btncadastrar, btnlimpar;
	Acesso a = new Acesso();
	AcessoBean aces1 = new AcessoBean();
	JCheckBox chacesso, chinclusao, chalteracao, chexclusao, chnivel5, chnivel6, chnivel7, chnivel8;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @param parametroitem
	 * @param c2
	 * @param ci
	 * @param f
	 * 
	 * @param cao
	 * @param cao
	 * @throws Exception
	 */
	public cadastraacesso(final Acesso c2) throws Exception {

		setTitle("NIVEL DE PERMISS\u00C3O");
		setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.imagemicone()));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(10, 5, 285, 260);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);
		

		JLabel lblacesso = new JLabel("ACESSO");
		lblacesso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblacesso.setForeground(Color.BLACK);
		lblacesso.setBounds(12, 32, 55, 18);
		contentPane.add(lblacesso);

		JLabel lblexclusao = new JLabel("EXCLUS\u00C3O");
		lblexclusao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblexclusao.setForeground(Color.BLACK);
		lblexclusao.setBounds(12, 128, 75, 14);
		contentPane.add(lblexclusao);

		JLabel lblalteracao = new JLabel("ALTERA\u00C7\u00C3O");
		lblalteracao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblalteracao.setForeground(Color.BLACK);
		lblalteracao.setBounds(12, 94, 78, 14);
		contentPane.add(lblalteracao);

		JLabel lblinclusao = new JLabel("INCLUS\u00C3O");
		lblinclusao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblinclusao.setForeground(Color.BLACK);
		lblinclusao.setBounds(12, 65, 70, 14);
		contentPane.add(lblinclusao);

		JLabel lblnivel5 = new JLabel("NIVEL5");
		lblnivel5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnivel5.setForeground(Color.BLACK);
		lblnivel5.setBounds(161, 34, 47, 14);
		contentPane.add(lblnivel5);

		JLabel lblnivel6 = new JLabel("NIVEL6");
		lblnivel6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnivel6.setForeground(Color.BLACK);
		lblnivel6.setBounds(161, 65, 47, 14);
		contentPane.add(lblnivel6);

		JLabel lblnivel7 = new JLabel("NIVEL7");
		lblnivel7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnivel7.setForeground(Color.BLACK);
		lblnivel7.setBounds(161, 94, 47, 14);
		contentPane.add(lblnivel7);

		btncadastrar = new JButton();
		// btncadastrar.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
		// Collections.EMPTY_SET);
		btncadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				gravaitem(c2);
			}
		});
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					gravaitem(c2);

				}
			}
		});
		//btncadastrar.setEnabled(false);
		btncadastrar.setBounds(83, 165, 55, 46);
		btncadastrar.setIcon(new ImageIcon(cadastraacesso.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		contentPane.add(btncadastrar);

		btnlimpar = new JButton();
		// btnlimpar.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
		// Collections.EMPTY_SET);
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * produto.setText(""); vrmercadoria.setText(""); unidade.setText("");
				 * quantidade.setText(""); descpro.setText(""); unitario.setText("");
				 * produto.setText("");
				 */
				// c.fechaconexao();
				dispose();
			}
		});
		btnlimpar.setBounds(161, 165, 55, 46);
		btnlimpar.setIcon(new ImageIcon(cadastraacesso.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnlimpar);
		contentPane.add(btnlimpar);

		JLabel lblnivel8 = new JLabel("NIVEL8");
		lblnivel8.setForeground(Color.BLACK);
		lblnivel8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnivel8.setBounds(161, 128, 47, 14);
		contentPane.add(lblnivel8);

		chacesso = new JCheckBox("");
		chacesso.setForeground(Color.GREEN);
		chacesso.setBackground(aces1.corpadrao());
		chacesso.setBounds(91, 29, 25, 23);
		contentPane.add(chacesso);

		chinclusao = new JCheckBox("");
		chinclusao.setBounds(91, 61, 25, 23);
		chinclusao.setBackground(aces1.corpadrao());
		contentPane.add(chinclusao);

		chalteracao = new JCheckBox("");
		chalteracao.setBackground(aces1.corpadrao());
		chalteracao.setBounds(91, 90, 25, 23);
		contentPane.add(chalteracao);

		chexclusao = new JCheckBox("");
		chexclusao.setBounds(91, 124, 25, 23);
		chexclusao.setBackground(aces1.corpadrao());
		contentPane.add(chexclusao);

		chnivel5 = new JCheckBox("");
		chnivel5.setBackground(aces1.corpadrao());
		chnivel5.setBounds(214, 30, 25, 23);
		contentPane.add(chnivel5);

		chnivel6 = new JCheckBox("");
		chnivel6.setBounds(214, 61, 25, 23);
		chnivel6.setBackground(aces1.corpadrao());
		contentPane.add(chnivel6);

		chnivel7 = new JCheckBox("");
		chnivel7.setBounds(214, 90, 25, 23);
		chnivel7.setBackground(aces1.corpadrao());
		contentPane.add(chnivel7);

		chnivel8 = new JCheckBox("");
		chnivel8.setBounds(214, 124, 25, 23);
		chnivel8.setBackground(aces1.corpadrao());
		contentPane.add(chnivel8);

		if (c2.getId() != null) {
			chacesso.setSelected(c2.getAcesso());
			chinclusao.setSelected(c2.getInclusao());
			chalteracao.setSelected(c2.getAlteracao());
			chexclusao.setSelected(c2.getExclusao());
			chnivel5.setSelected(c2.getNivel5());
			chnivel6.setSelected(c2.getNivel6());
			chnivel7.setSelected(c2.getNivel7());
			chnivel8.setSelected(c2.getNivel8());

		}

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				dispose();

			}
		});

		/// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

	public boolean isCellEditable(int row, int col) {
		if (col == 2) {
			return false;
		} else {
			return true;
		}

	}

	public void gravaitem(Acesso p) {
		Acesso a1 = new Acesso();
		try {
			a1.setAcesso(chacesso.isSelected());
			a1.setInclusao(chinclusao.isSelected());
			a1.setAlteracao(chalteracao.isSelected());
			a1.setExclusao(chexclusao.isSelected());
			a1.setNivel5(chnivel5.isSelected());
			a1.setNivel6(chnivel6.isSelected());
			a1.setNivel7(chnivel7.isSelected());
			a1.setNivel8(chnivel8.isSelected());
			a1.setId(p.getId());
			a1.setRotina(p.getRotina());
			a1.setModulo(p.getModulo());
			a1.setUsuario(p.getUsuario());
			aces1.adiciona(a1);
			dispose();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
		}

	}

	public Boolean Verificaqtde(Double qtdepro, Double qtde) {
		Boolean valida = true;
		if (qtdepro < qtde) {
			valida = false;
			JOptionPane.showMessageDialog(null,
					"Produto não possui saldo suficiente," + " saldo atual : " + String.format("%.0f", qtdepro));
		}
		return valida;

	}
}
