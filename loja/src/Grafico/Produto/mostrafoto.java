package Grafico.Produto;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import Configuracao.clsRedimensionarImagem;
import beans.AcessoBean;
import beans.CadproBean;

import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import entidades.Acesso;
import entidades.Cadpro;
import entidades.Usuario;
import Grafico.geral.TTextAreaDocument;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFormattedTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.ResultSet;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.util.Collections;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class mostrafoto extends JDialog {
	private JPanel contentPane;
	Font font = new Font("arial", 0, 14);
	clsRedimensionarImagem resize = new clsRedimensionarImagem();
	JButton Arvore;
	CadproBean c = new CadproBean();
	AcessoBean aces1 = new AcessoBean();
	
	public mostrafoto(final Cadpro c, String camin) throws Exception {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("FOTO PRODUTO");
		setResizable(false);
		setModal(true);
		setBounds(300, 80, 818, 587);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);
		
		//JOptionPane.showMessageDialog(null, "id: " + c.getNome().trim() + " " + aces1.caminhoireport() + "\\imagens\\" + c.getId() +".png");
		ImageIcon icon = new ImageIcon();
		
		String fotojpg1 = "";
		File gto = null;
		
		File file = new File("\\\\" + camin + "\\ACABADO\\");
		if (!file.exists()) {
			file.mkdirs();
		}
		
		fotojpg1 = "\\\\" + camin + "\\ACABADO\\" + c.getCODPRO().trim() +".png";
		
		gto = new File(fotojpg1);
		//JOptionPane.showMessageDialog(null, "id: " + c.getNome().trim() + " " + gto.exists());
		
		
		if (gto.exists()) {
			icon = new ImageIcon(fotojpg1);
		}
		
		if (!gto.exists()) {
			fotojpg1 = "\\\\" + camin + "\\ACABADO\\" + c.getCODPRO().trim() +".jpg";	
			
			gto = new File(fotojpg1);
			
			if (gto.exists()) {
				icon = new ImageIcon(fotojpg1);
			}
			
		}
		
		if (!gto.exists()) {
			fotojpg1 = "\\\\" + camin + "\\ACABADO\\" + c.getCODPRO().trim() +".jpeg";	
			
			gto = new File(fotojpg1);
			
			if (gto.exists()) {
				icon = new ImageIcon(fotojpg1);
			}
			
		}
		
		JLabel lblfoto = new JLabel(icon, JLabel.CENTER);
		lblfoto.setLocation(6, 20);
		lblfoto.setSize(785, 500);
		contentPane.add(lblfoto);

		// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				setVisible(false);
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

	}
}
