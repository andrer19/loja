package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import repositorios.CadfunRepository;

import entidades.Cadfun;
import filter.EntityManagerUtil;

public class CadfunBean {

	Cadfun cadfun = new Cadfun();
	public List<Cadfun> cadfuns;

	public void adiciona(Cadfun p) {
		CadfunRepository repository = new CadfunRepository();
		repository.grava(p);
	}

	public Cadfun altera(Long id) {
		CadfunRepository repository = new CadfunRepository();
		this.cadfun = repository.procura(id);
		return cadfun;

	}

	public List<Cadfun> getcadfuns() {
		List<Cadfun> cadfun1 = new ArrayList<Cadfun>();
		CadfunRepository repository = new CadfunRepository();
		cadfun1 = repository.getLista();

		return cadfun1;
	}
	
	public List<Cadfun> listafuncoes() {
		List<Cadfun> cadfun1 = new ArrayList<Cadfun>();
		CadfunRepository repository = new CadfunRepository();
		cadfun1 = repository.listafuncoes();

		return cadfun1;
		
	}

	public void remove(Long id) {
		CadfunRepository repository = new CadfunRepository();
		repository.removeatualiza(id);
	}
	
	public Boolean buscafuncao(String funcao) {
		CadfunRepository repository = new CadfunRepository();
		return repository.buscafuncao(funcao);
		
	}

	public void fechaconexao() {

		CadfunRepository repository = new CadfunRepository();
		repository.fechaconexao();
	}

	public Cadfun getcadfun() {
		return cadfun;
	}

	public void setcadfun(Cadfun cadfun) {
		this.cadfun = cadfun;
	}

	public void setcadfuns(List<Cadfun> cadfuns) {
		this.cadfuns = cadfuns;
	}

}
