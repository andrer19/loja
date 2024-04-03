package beans;

import java.util.List;

import repositorios.CadbcoRepository;
import entidades.Cadbco;


public class CadbcoBean {
	
	Cadbco cadbco = new Cadbco();
	public List<Cadbco> cadbcos;
	
	public void adiciona(Cadbco p) {
		CadbcoRepository repository = new CadbcoRepository();
		repository.grava(p);
	}
	
	public Cadbco altera(Long id) {
		CadbcoRepository repository = new CadbcoRepository();
		this.cadbco = repository.procura(id);
		 
		return cadbco;
	}
	
	
	public List<Cadbco> getCadbcos() {
		if (this.cadbcos == null) {
			CadbcoRepository repository = new CadbcoRepository();
			this.cadbcos = repository.getLista();
		}
		 
		return cadbcos;
	}
	
	public void remove(Long id) {
		CadbcoRepository repository = new CadbcoRepository();
		repository.remove(id);
		this.cadbcos = null;
		
	}
	
	public Cadbco buscaporcodigo(String codigo) {
		CadbcoRepository repository = new CadbcoRepository();
		return repository.buscaporcodigo(codigo);
		
	}


	public Cadbco getCadbco() {
		return cadbco;
	}

	public void setCadbco(Cadbco cadbco) {
		this.cadbco = cadbco;
	}

	public void setCadbcos(List<Cadbco> cadbcos) {
		this.cadbcos = cadbcos;
	}
	
	

}
