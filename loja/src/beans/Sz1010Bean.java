package beans;


import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidades.Sz1010;
import repositorios.Sz1010Repository;



public class Sz1010Bean {
	
	Boolean valida = false;
	AcessoBean aces1 = new AcessoBean();
	
	public void adiciona(Sz1010 p) {
		Sz1010Repository repository = new Sz1010Repository();
		repository.grava(p);
	}

	public Sz1010 procura(Long id) {
		Sz1010Repository repository = new Sz1010Repository();
		return repository.procura(id);

	}
	
	public List<Sz1010> lista() {
		Sz1010Repository repository = new Sz1010Repository();
		return repository.getLista();
	}
	
	
	

	
}
