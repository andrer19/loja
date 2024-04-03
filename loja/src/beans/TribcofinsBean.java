package beans;


import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Grafico.Tributos.listatribcofins;
import entidades.Tribcofins;
import repositorios.TribcofinsRepository;


public class TribcofinsBean {
	
	Boolean valida = false;
	AcessoBean aces1 = new AcessoBean();
	
	public void adiciona(Tribcofins p) {
		TribcofinsRepository repository = new TribcofinsRepository();
		repository.grava(p);
	}

	public Tribcofins procura(Long id) {
		TribcofinsRepository repository = new TribcofinsRepository();
		return repository.procura(id);

	}
	
	public List<Tribcofins> lista() {
		TribcofinsRepository repository = new TribcofinsRepository();
		return repository.getLista();
	}
	
	public Tribcofins buscatributocofins(String codigo) {
		TribcofinsRepository repository = new TribcofinsRepository();
		return repository.buscatributocofins(codigo);
	}
	
	public Boolean validacofins(JTextField campo, JButton botao, Tribcofins cofins) {
		Boolean valida = false;

		cofins = new Tribcofins();
		if (campo.getText() != null && !campo.getText().isEmpty()) {
			cofins = buscatributocofins(campo.getText());

			if (cofins.getIdtribcofins() != null) {
				campo.setText(cofins.getNumero());
				//botao.setEnabled(true);
				//botao.requestFocus();
				valida = true;
			} else {
				campo.setText(null);
				campo.requestFocus();
			}
		}

		return valida;

	}

	public Boolean buscacofins(JTextField campo, JButton botao, Tribcofins cofins) {
		Boolean valida = false;

		if (campo.getText() == null || campo.getText().isEmpty()) {
			try {
				cofins = new Tribcofins();
				new listatribcofins(cofins).setVisible(true);
				if (cofins.getIdtribcofins() != null) {
					campo.setText(cofins.getNumero());
					botao.setEnabled(true);
					botao.requestFocus();
					valida = true;
				} else {
					campo.setText(null);
					campo.requestFocus();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "ERRO TRIBUTOS COFINS: " + e1.getStackTrace());
			}

		} else {
			valida = validacofins(campo, botao, cofins);
		}
		return valida;

	}


}
