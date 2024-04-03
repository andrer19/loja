package beans;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

import Grafico.Tributos.listatribicms;
import entidades.Tribicms;
import entidades.Tribicms;
import filter.EntityManagerUtil;
import repositorios.TribicmsRepository;
import repositorios.TribicmsRepository;;

public class TribicmsBean {

	Boolean valida = false;
	AcessoBean aces1 = new AcessoBean();

	public void adiciona(Tribicms p) {
		TribicmsRepository repository = new TribicmsRepository();
		repository.grava(p);
	}

	public Tribicms procura(Long id) {
		TribicmsRepository repository = new TribicmsRepository();
		return repository.procura(id);

	}

	public List<Tribicms> lista() {
		TribicmsRepository repository = new TribicmsRepository();
		return repository.getLista();
	}

	public Tribicms buscatributoicms(String codigo) {
		TribicmsRepository repository = new TribicmsRepository();
		return repository.buscatributoicms(codigo);
	}

	public Boolean validaicms(JTextField campo, JTextField proximo, Tribicms icms) {
		Boolean valida = false;

		icms = new Tribicms();
		if (campo.getText() != null || !campo.getText().isEmpty()) {
			icms = buscatributoicms(campo.getText());

			if (icms.getIdtribicms() != null) {
				campo.setText(icms.getNumero());
				aces1.liberado(proximo);
				proximo.setCaretPosition(0);
				proximo.selectAll();
				proximo.requestFocus();
				valida = true;
			} else {
				campo.setText(null);
				campo.requestFocus();
			}
		}

		return valida;

	}

	public Boolean buscaicms(JTextField campo, JTextField proximo, Tribicms icms) {
		Boolean valida = false;

		if (campo.getText() == null || campo.getText().isEmpty()) {
			try {
				icms = new Tribicms();
				new listatribicms(icms).setVisible(true);
				if (icms.getIdtribicms() != null) {
					campo.setText(icms.getNumero());
					aces1.liberado(proximo);
					proximo.setCaretPosition(0);
					proximo.selectAll();
					proximo.requestFocus();
					valida = true;
				} else {
					campo.setText(null);
					campo.requestFocus();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "ERRO TRIBUTOS ICMS: " + e1.getStackTrace());
			}

		} else {
			valida = validaicms(campo, proximo, icms);
		}
		return valida;

	}

}
