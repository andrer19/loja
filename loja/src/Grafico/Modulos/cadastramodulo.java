package Grafico.Modulos;

import entidades.Modulos;
import entidades.Rotinas;
import entidades.Usuario;
import Configuracao.Letramaiuscula;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFormattedTextField;

import beans.AcessoBean;
import beans.ModulosBean;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class cadastramodulo extends JDialog {
	private JPanel contentPane;
	private JTextField modulo;
	Font font = new Font("arial", 0, 14);
	JButton btncadastrar;
	private Long id;
	Configuracao.Letramaiuscula bloqueios = new Configuracao.Letramaiuscula();
	AcessoBean aces1 = new AcessoBean();
	private JButton btnsair;
	private JCheckBox chcautoriza;
	private JTextField pagina;

	public cadastramodulo(final Modulos mod, Usuario u) throws Exception {

		setTitle("CADASTRA MODULO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.imagemicone()));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(10, 5, 390, 218);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);
		
		JLabel lblmodulo = new JLabel("MODULO");
		aces1.padraojlabel(lblmodulo);
		lblmodulo.setBounds(25, 28, 68, 14);
		contentPane.add(lblmodulo);

		modulo = new JTextField();
		modulo.setBounds(125, 25, 241, 20);
		modulo.setDocument(new Configuracao.Letramaiuscula());
		aces1.liberado(modulo);
		contentPane.add(modulo);
		
		JLabel lblpagina = new JLabel("PAGINA");
		aces1.padraojlabel(lblpagina);
		lblpagina.setBounds(25, 58, 60, 14);
		contentPane.add(lblpagina);

		pagina = new JTextField();
		pagina.setBounds(125, 55, 241, 20);
		aces1.liberado(pagina);
		pagina.setDocument(new Configuracao.Letramaiuscula());
		contentPane.add(pagina);
		
		JLabel lolautoriza = new JLabel("AUTORIZADO");
		aces1.padraojlabel(lolautoriza);
		lolautoriza.setBounds(25, 88, 95, 18);
		contentPane.add(lolautoriza);

		chcautoriza = new JCheckBox("");
		chcautoriza.setBackground(aces1.corpadrao());
		chcautoriza.setBounds(122, 88, 26, 20);
		contentPane.add(chcautoriza);
		chcautoriza.setSelected(false);

		btncadastrar = new JButton();
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnsair.requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravamodulo(mod);
				}
			}
		});
		btncadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravamodulo(mod);

			}
		});
		btncadastrar.setIcon(new ImageIcon(cadastramodulo.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		btncadastrar.setBounds(125, 119, 55, 46);
		contentPane.add(btncadastrar);

		btnsair = new JButton();
		btnsair.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
				}
			}
		});
		btnsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnsair.setBounds(203, 119, 55, 46);
		btnsair.setIcon(new ImageIcon(cadastramodulo.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnsair);
		contentPane.add(btnsair);	

		if (mod.getIdmodulo() != null) {
			modulo.setText(mod.getModulo());
			pagina.setText(mod.getPagina());
			if (mod.getMostra() == true) {
				chcautoriza.setSelected(true);
			}

		}

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

	private MaskFormatter setNumero(String numero) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(numero);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}

	public void gravamodulo(Modulos e2) {

		Modulos e = new Modulos();
		ModulosBean u = new ModulosBean();

		e.setModulo(modulo.getText());
		e.setPagina(pagina.getText());
		//JOptionPane.showMessageDialog(null, "deletado " + e.getSql_deleted());

		if (e.getModulo().isEmpty() || e.getModulo().isEmpty() || e.getPagina().isEmpty() || e.getPagina().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Dados em branco");
			btncadastrar.setEnabled(false);
		} else {

			try {
				e.setModulo(modulo.getText());
				e.setPagina(pagina.getText());
				if (chcautoriza.isSelected()) {
					e.setMostra(true);
				} else {
					e.setMostra(false);
				}
				e.setSql_deleted("F");
				e.setIdmodulo(e2.getIdmodulo());
				u.adiciona(e);
				dispose();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
		}
	}
}
