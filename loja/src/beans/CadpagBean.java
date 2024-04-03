package beans;


import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidades.Cadpag;
import repositorios.CadpagRepository;



public class CadpagBean {
	
	public void adiciona(Cadpag p) {
		CadpagRepository repository = new CadpagRepository();
		repository.grava(p);
	}

	public Cadpag procura(Long id) {
		CadpagRepository repository = new CadpagRepository();
		return repository.procura(id);

	}
	
	public List<Cadpag> lista() {
		CadpagRepository repository = new CadpagRepository();
		return repository.getLista();
	}
	
	
	public Boolean validadoc(String numdoc,String serie,String parcela,String codfor) {
		CadpagRepository repository = new CadpagRepository();
		return repository.validadoc(numdoc, serie, parcela, codfor);
		
	}
	

	
}
