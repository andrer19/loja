package beans;


import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Grafico.Tributos.listatribpis;
import entidades.Tribpis;
import repositorios.TribpisRepository;


public class TribpisBean {
	
	Boolean valida = false;
	AcessoBean aces1 = new AcessoBean();
	
	public void adiciona(Tribpis p) {
		TribpisRepository repository = new TribpisRepository();
		repository.grava(p);
	}

	public Tribpis procura(Long id) {
		TribpisRepository repository = new TribpisRepository();
		return repository.procura(id);

	}
	
	public List<Tribpis> lista() {
		TribpisRepository repository = new TribpisRepository();
		return repository.getLista();
	}
	
	public Tribpis buscatributopis(String codigo) {
		TribpisRepository repository = new TribpisRepository();
		return repository.buscatributopis(codigo);
	}
	
	public Boolean validapis(JTextField campo, JTextField proximo, Tribpis pis) {
		Boolean valida = false;

		pis = new Tribpis();
		if (campo.getText() != null && !campo.getText().isEmpty()) {
			pis = buscatributopis(campo.getText());

			if (pis.getIdtribpis() != null) {
				campo.setText(pis.getNumero());
				aces1.liberado(proximo);
				//proximo.setCaretPosition(0);
				//proximo.selectAll();
				//proximo.requestFocus();
				valida = true;
			} else {
				campo.setText(null);
				campo.requestFocus();
			}
		}

		return valida;

	}

	public Boolean buscapis(JTextField campo, JTextField proximo, Tribpis pis) {
		Boolean valida = false;

		if (campo.getText() == null || campo.getText().isEmpty()) {
			try {
				pis = new Tribpis();
				new listatribpis(pis).setVisible(true);
				if (pis.getIdtribpis() != null) {
					campo.setText(pis.getNumero());
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
				JOptionPane.showMessageDialog(null, "ERRO TRIBUTOS PIS: " + e1.getStackTrace());
			}

		} else {
			valida = validapis(campo, proximo, pis);
		}
		return valida;

	}

}
