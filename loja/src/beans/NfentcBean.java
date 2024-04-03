package beans;


import java.util.ArrayList;

import java.util.List;


import repositorios.NfentcRepository;

import entidades.Cadfor;
import entidades.Nfentc;
import filter.EntityManagerUtil;

public class NfentcBean {

	Nfentc nfentc = new Nfentc();
	public List<Nfentc> nfentcs;

	public void adiciona(Nfentc p) {
		NfentcRepository repository = new NfentcRepository();
		if (p.getIdnfentc() == null) {
			repository.adiciona(p);
		} else {
			repository.atualiza(p);
		}
	}

	public Nfentc altera(Long id) {

		NfentcRepository repository = new NfentcRepository();
		this.nfentc = repository.procura(id);

		return nfentc;
	}

	public List<Nfentc> getNfentcs() {

		List<Nfentc> nfentc1 = new ArrayList<Nfentc>();

		NfentcRepository repository = new NfentcRepository();
		nfentc1 = repository.getLista();

		return nfentc1;
	}

	public void remove(Long id) {

		NfentcRepository repository = new NfentcRepository();
		repository.removeatualiza(id);
	}

	public Cadfor Validacao(String codigo) {
		Cadfor c = new Cadfor();
		List<Cadfor> lista = new ArrayList<Cadfor>();

		CadforBean cbean = new CadforBean();
		lista = cbean.getCadfors();

		for (Cadfor c1 : lista) {
			if (c1.getCODFOR().equals(codigo)) {
				c = c1;
			}
		}
		return c;

	}
	
	public Boolean validanota(String nota, String serie, String fornecedor) {
		NfentcRepository repository = new NfentcRepository();
		return repository.validanota(nota, serie, fornecedor);
		
		
	}

	public void fechaconexao() {

		NfentcRepository repository = new NfentcRepository();
		repository.fechaconexao();
	}

	public Nfentc getNfentc() {
		return nfentc;
	}

	public void setNfentc(Nfentc nfentc) {
		this.nfentc = nfentc;
	}

	public void setNfentcs(List<Nfentc> nfentcs) {
		this.nfentcs = nfentcs;
	}

}
