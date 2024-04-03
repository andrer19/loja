package beans;

import java.util.ArrayList;
import java.util.List;

import repositorios.CadmoRepository;

import entidades.Cadmo;
public class CadmoBean {

	Cadmo cadmo = new Cadmo();
	public List<Cadmo> cadmos;

	public void adiciona(Cadmo p) {
		CadmoRepository repository = new CadmoRepository();
		repository.grava(p);
	}

	public Cadmo altera(Long id) {
		CadmoRepository repository = new CadmoRepository();
		this.cadmo = repository.procura(id);
		return cadmo;

	}

	public List<Cadmo> getcadmos() {
		List<Cadmo> cadmo1 = new ArrayList<Cadmo>();
		CadmoRepository repository = new CadmoRepository();
		cadmo1 = repository.getLista();

		return cadmo1;
	}

	public void remove(Long id) {
		CadmoRepository repository = new CadmoRepository();
		repository.removeatualiza(id);
	}
	
	public Boolean buscamatricula(String matricula) {
		CadmoRepository repository = new CadmoRepository();
		return repository.buscamatricula(matricula);
		
	}

	public void fechaconexao() {

		CadmoRepository repository = new CadmoRepository();
		repository.fechaconexao();
	}

	public Cadmo getcadmo() {
		return cadmo;
	}

	public void setcadmo(Cadmo cadmo) {
		this.cadmo = cadmo;
	}

	public void setcadmos(List<Cadmo> cadmos) {
		this.cadmos = cadmos;
	}

}
