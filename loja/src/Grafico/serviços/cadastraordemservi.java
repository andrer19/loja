package Grafico.serviços;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import filter.EntityManagerUtil;
import repositorios.LogusuRepository;
import Grafico.geral.MonetarioDocument;
import beans.AcessoBean;
import beans.CadosiBean;
import entidades.Cadosc;
import entidades.Cadosi;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class cadastraordemservi extends JDialog {
	private JPanel contentPane;
	private JTextField item, valormercadoria;

	JButton btncadastrar, btnlimpar;

	CadosiBean itemordembean = new CadosiBean();

	AcessoBean aces1 = new AcessoBean();
	Double precop = 0.0;

	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
	private JTextArea descricao;

	public cadastraordemservi(final Cadosc c2, final Cadosi ci, int itemp, DefaultTableModel listacadosi)
			throws Exception {

		setTitle("SERVIÇOS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.imagemicone()));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(10, 5, 546, 243);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);
		int novoitem = itemp + 1;
		setPrecop(0.0);

		JLabel lblitem = new JLabel("ITEM");
		aces1.padraojlabel(lblitem);
		;
		lblitem.setBounds(10, 13, 41, 14);
		contentPane.add(lblitem);

		item = new JTextField("" + novoitem);
		item.setBounds(145, 10, 24, 20);
		aces1.bloqueado(item);
		contentPane.add(item);

		JLabel lbldescricao = new JLabel("DESCRIÇÃO");
		aces1.padraojlabel(lbldescricao);
		lbldescricao.setBounds(10, 38, 86, 14);
		contentPane.add(lbldescricao);
		
		descricao = new JTextArea();
		descricao.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		descricao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
					aces1.liberado(valormercadoria);
					valormercadoria.requestFocus();
					
				}
			}
		});
		aces1.liberadojTextArea(descricao);
		descricao.setBounds(145, 35, 354, 72);
		contentPane.add(descricao);

		JLabel lblvalor = new JLabel("VALOR TOTAL");
		aces1.padraojlabel(lblvalor);
		lblvalor.setBounds(10, 118, 86, 14);
		contentPane.add(lblvalor);

		valormercadoria = new JTextField();
		valormercadoria.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		valormercadoria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
					if (valormercadoria.getText().equals(null) || valormercadoria.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite um Valor!!!!");
						valormercadoria.requestFocus();
					} else {
						btncadastrar.setEnabled(true);
						btncadastrar.requestFocus();
					}
				}
			}
		});
		aces1.bloqueado(valormercadoria);
		valormercadoria.setDocument(new MonetarioDocument());
		valormercadoria.setBounds(145, 115, 97, 20);
		contentPane.add(valormercadoria);

		btncadastrar = new JButton();
		btncadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				gravaitem(ci);
			}
		});
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					gravaitem(ci);

				}
			}
		});
		btncadastrar.setEnabled(false);
		btncadastrar.setBounds(205, 150, 55, 46);
		btncadastrar.setIcon(new ImageIcon(cadastraordemservi.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		contentPane.add(btncadastrar);

		btnlimpar = new JButton();
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ci.setItem("");
				ci.setDescricao("");
				dispose();
			}
		});
		btnlimpar.setBounds(283, 150, 55, 46);
		btnlimpar.setIcon(new ImageIcon(cadastraordemservi.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnlimpar);
		contentPane.add(btnlimpar);

		if (ci.getId() != null) {

			item.setText(ci.getItem().toString());
			descricao.setText(ci.getDescricao());
			aces1.moedabanco(ci.getVrtot(), valormercadoria);
		}

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				descricao.requestFocus();
				descricao.setCaretPosition(0);
			}

			public void windowClosing(WindowEvent e) {
				ci.setItem("");
				dispose();

			}
		});

		/// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ci.setId(null);
				ci.setItem("");
				ci.setDescricao("");
				ci.setVrtot(0.0);
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

	public void gravaitem(Cadosi p) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();

		try {
			if (!btncadastrar.isEnabled()) {

			} else {
				p.setItem(item.getText().trim());
				p.setDescricao(descricao.getText().trim());
				p.setVrtot(Double.parseDouble(aces1.gravamoedadouble(valormercadoria.getText().trim())));

			}
			
			if (p.getDescricao().isEmpty() || p.getDescricao().equals(null) || p.getVrtot() == null) {
				JOptionPane.showMessageDialog(null, "EXISTE DADOS OBRIGATORIOS SEM PREEENCHIMENTO !!!!!!!");
				descricao.requestFocus();
			} else {
				
				item.setText("");
				valormercadoria.setText("");
				descricao.setText("");
				valormercadoria.setText("");
				dispose();
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "ERRO ITEM NA LISTA ORDEM DE SERVIÇO " + e1.getMessage());
			aces1.demologger.error("ERRO ITEM NA LISTA ORDEM DE SERVIÇO " + e1.getMessage());
		}

	}

	public Double getPrecop() {
		return precop;
	}

	public void setPrecop(Double precop) {
		this.precop = precop;
	}

}
