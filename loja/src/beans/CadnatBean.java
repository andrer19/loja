package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import repositorios.CadnatRepository;

import entidades.Cadnat;
import filter.EntityManagerUtil;

public class CadnatBean {

	Cadnat cadnat = new Cadnat();
	public List<Cadnat> cadnats;

	Boolean valida = false;
	AcessoBean aces1 = new AcessoBean();

	public void adiciona(Cadnat p) {
		CadnatRepository repository = new CadnatRepository();
		repository.grava(p);
	}

	public Cadnat altera(Long id) {
		CadnatRepository repository = new CadnatRepository();
		cadnat = repository.procura(id);
		return cadnat;

	}

	public List<Cadnat> getcadnats() {
		List<Cadnat> cadnat1 = new ArrayList<Cadnat>();
		CadnatRepository repository = new CadnatRepository();
		cadnat1 = repository.getLista();

		return cadnat1;
	}

	public void remove(Long id) {
		CadnatRepository repository = new CadnatRepository();
		repository.removeatualiza(id);
	}

	public Boolean buscanatureza(String funcao) {
		CadnatRepository repository = new CadnatRepository();
		return repository.buscanatureza(funcao);

	}

	public Cadnat buscaporcodigo(String codigo) {
		CadnatRepository repository = new CadnatRepository();
		return repository.buscaporcodigo(codigo);
	}

	public Boolean validacadnat(JTextField campo, JTextField icms, JTextField ipi, JTextField pis, JTextField cofins,
			Cadnat cadnat) {
		Boolean valida = false;

		if (campo.getText() != null && !campo.getText().isEmpty()) {
			cadnat = new Cadnat();
			cadnat = buscaporcodigo(campo.getText());

			if (cadnat.getIdcadnat() != null) {
				if (Integer.parseInt(cadnat.getNUMERO().substring(0, 1)) > 4) {
					JOptionPane.showMessageDialog(null, "Natureza de Operação Invalido");
					campo.setText(null);
					campo.requestFocus();
				} else {
					campo.setText(cadnat.getNUMERO());
					icms.setText(aces1.retornaString(cadnat.getCOD_ICMS()));
					ipi.setText(aces1.retornaString(cadnat.getCOD_IPI()));
					pis.setText(aces1.retornaString(cadnat.getCOD_PIS()));
					cofins.setText(aces1.retornaString(cadnat.getCOD_COFINS()));
					aces1.liberado(icms);
					valida = true;
					// tribicms.setCaretPosition(0);
					// tribicms.selectAll();
					// tribicms.requestFocus();
				}
			} else {
				campo.setText(null);
				campo.requestFocus();
			}
		}

		return valida;

	}

	

	public Cadnat getcadnat() {
		return cadnat;
	}

	public void setcadnat(Cadnat cadnat) {
		this.cadnat = cadnat;
	}

	public void setcadnats(List<Cadnat> cadnats) {
		this.cadnats = cadnats;
	}

}
