package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import repositorios.CadcustoRepository;

import entidades.Cadcusto;
import filter.EntityManagerUtil;

public class CadcustoBean {

	Cadcusto cadcusto = new Cadcusto();

	public void adiciona(Cadcusto p) {
		CadcustoRepository repository = new CadcustoRepository();
		repository.grava(p);
	}

	public Cadcusto altera(Long id) {
		CadcustoRepository repository = new CadcustoRepository();
		cadcusto = repository.procura(id);
		return cadcusto;

	}

	public List<Cadcusto> getcadcustos() {
		List<Cadcusto> cadcusto1 = new ArrayList<Cadcusto>();
		CadcustoRepository repository = new CadcustoRepository();
		cadcusto1 = repository.getLista();

		return cadcusto1;
	}

	public void remove(Long id) {
		CadcustoRepository repository = new CadcustoRepository();
		repository.removeatualiza(id);
	}
	
	public Cadcusto buscaporcodigo(String codigo) {
		CadcustoRepository repository = new CadcustoRepository();
		return repository.buscaporcodigo(codigo);
	}

	public void fechaconexao() {

		CadcustoRepository repository = new CadcustoRepository();
		repository.fechaconexao();
	}

	public Cadcusto getcadcusto() {
		return cadcusto;
	}

	public void setcadcusto(Cadcusto cadcusto) {
		this.cadcusto = cadcusto;
	}


}
