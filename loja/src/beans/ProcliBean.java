package beans;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import repositorios.ProcliRepository;
import entidades.Procli;

public class ProcliBean {

	Procli procli = new Procli();
	public List<Procli> proclis;

	public void adiciona(Procli p) {
		ProcliRepository repository = new ProcliRepository();
		repository.grava(p);
	}

	public Procli altera(Long id) {
		ProcliRepository repository = new ProcliRepository();
		this.procli = repository.procura(id);
		return procli;

	}

	public List<Procli> getProclis() {
		List<Procli> procli1 = new ArrayList<Procli>();
		ProcliRepository repository = new ProcliRepository();
		procli1 = repository.getLista();

		return procli1;
	}

	public void remove(Long id) {
		ProcliRepository repository = new ProcliRepository();
		repository.removeatualiza(id);
	}
	
	public Boolean validaprocli(String codcli, String codpro) {
		
		ProcliRepository repository = new ProcliRepository();
		//JOptionPane.showMessageDialog(null, "valor: " + repository.validaprocli(codcli, codpro));
		return repository.validaprocli(codcli, codpro);
		
	}

	public void fechaconexao() {

		ProcliRepository repository = new ProcliRepository();
		repository.fechaconexao();
	}

	public Procli getProcli() {
		return procli;
	}

	public void setProcli(Procli procli) {
		this.procli = procli;
	}

	public void setProclis(List<Procli> proclis) {
		this.proclis = proclis;
	}

}
