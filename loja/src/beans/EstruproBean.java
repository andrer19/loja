package beans;

import java.util.ArrayList;
import java.util.List;

import repositorios.EstruproRepository;
import entidades.Estrupro;

public class EstruproBean {
	
	Estrupro estrupro = new Estrupro();
	public List<Estrupro> estrupros;
	
	
	public void adiciona(Estrupro e) {
		EstruproRepository repository = new EstruproRepository();
		repository.grava(e);
	}
	
	public Estrupro altera(Long id) {
		EstruproRepository repository = new EstruproRepository();
		this.estrupro = repository.procura(id);
		return estrupro;
		
		
	}
	
	
	public void remove(Long id) {
		EstruproRepository repository = new EstruproRepository();
		repository.removeatualiza(id);
	}

	
	
	public List<Estrupro> getEstrupros() {
		
		List<Estrupro> estrupro1 = new ArrayList<Estrupro>();
		EstruproRepository repository = new EstruproRepository();
		estrupro1 = repository.getLista();
		
		return estrupro1;
	}
	
      public List<Estrupro> listaitens(String produto) {
		
		List<Estrupro> estrupro1 = new ArrayList<Estrupro>();
		EstruproRepository repository = new EstruproRepository();
		estrupro1 = repository.listaitens(produto);
		
		return estrupro1;
	}
	
	public void fechaconexao() {
		
		EstruproRepository repository = new EstruproRepository();
		repository.fechaconexao();
	}
	
	// GETTERS E SETTERS

	public Estrupro getEstrupro() {
		return estrupro;
	}

	public void setEstrupro(Estrupro estrupro) {
		this.estrupro = estrupro;
	}

	public void setEstrupros(List<Estrupro> estrupros) {
		this.estrupros = estrupros;
	}
	
	

	
	
	
	

}
