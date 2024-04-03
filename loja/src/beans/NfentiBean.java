package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import repositorios.CadproRepository;
import repositorios.NfentiRepository;
import entidades.Cadpro;
import entidades.Nfenti;
import filter.EntityManagerUtil;

public class NfentiBean {

	Nfenti nfenti = new Nfenti();
	public List<Nfenti> nfentis;
	AcessoBean aces1 = new AcessoBean();
	
	EntityManager manager;

	public NfentiBean() {
		manager = this.getManager();
	}

	public void adiciona(Nfenti p) {
		NfentiRepository repository = new NfentiRepository();
		repository.grava(p);
	}

	public Nfenti altera(Long id) {
		NfentiRepository repository = new NfentiRepository();
		this.nfenti = repository.procura(id);
		return nfenti;

	}

	public List<Nfenti> getNfentis(String num, String forn) {

		List<Nfenti> nfenti1 = new ArrayList<Nfenti>();
		NfentiRepository repository = new NfentiRepository();

		nfenti1 = repository.getLista(num, forn);

		return nfenti1;
	}

	public void removeitens(String pedido) {

		NfentiRepository repository = new NfentiRepository();
		repository.removeitens(pedido);

	}

	public void remove(Long id) {
		NfentiRepository repository = new NfentiRepository();
		repository.removeatualiza(id);
	}

	public Cadpro procurapro(String prod) {
		manager = this.getManager();
		Cadpro cadpro1 = new Cadpro();

		CadproRepository repository = new CadproRepository(manager);
		cadpro1 = repository.buscaproduto(prod);

		return cadpro1;

	}
	
	private EntityManager getManager() {
		EntityManager manager = EntityManagerUtil.getManager();

		return manager;
	}

	public void fechaconexao() {

		NfentiRepository repository = new NfentiRepository();
		repository.fechaconexao();
	}

	public Nfenti getNfenti() {
		return nfenti;
	}

	public void setPdentc(Nfenti nfenti) {
		this.nfenti = nfenti;
	}

	public void setPdentcs(List<Nfenti> nfentis) {
		this.nfentis = nfentis;
	}

}
