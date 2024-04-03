package beans;


import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Grafico.Tributos.listatribipi;
import entidades.Tribipi;
import repositorios.TribipiRepository;


public class TribipiBean {
	
	Boolean valida = false;
	AcessoBean aces1 = new AcessoBean();
	
	public void adiciona(Tribipi p) {
		TribipiRepository repository = new TribipiRepository();
		repository.grava(p);
	}

	public Tribipi procura(Long id) {
		TribipiRepository repository = new TribipiRepository();
		return repository.procura(id);

	}
	
	public List<Tribipi> lista() {
		TribipiRepository repository = new TribipiRepository();
		return repository.getLista();
	}
	
	public Tribipi buscatributoipi(String codigo) {
		TribipiRepository repository = new TribipiRepository();
		return repository.buscatributoipi(codigo);
	}
	
	public Boolean validaipi(JTextField campo, JTextField proximo, Tribipi ipi) {
		Boolean valida = false;

		ipi = new Tribipi();
		if (campo.getText() != null && !campo.getText().isEmpty()) {
			ipi = buscatributoipi(campo.getText());

			if (ipi.getIdtribipi() != null) {
				campo.setText(ipi.getNumero());
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

	public Boolean buscaipi(JTextField campo, JTextField proximo, Tribipi ipi) {
		Boolean valida = false;

		if (campo.getText() == null || campo.getText().isEmpty()) {
			try {
				ipi = new Tribipi();
				new listatribipi(ipi).setVisible(true);
				if (ipi.getIdtribipi() != null) {
					campo.setText(ipi.getNumero());
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
				JOptionPane.showMessageDialog(null, "ERRO TRIBUTOS IPI: " + e1.getStackTrace());
			}

		} else {
			valida = validaipi(campo, proximo, ipi);
		}
		return valida;

	}

}
