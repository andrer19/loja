package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Acesso;
import entidades.Cadcusto;
import entidades.Rotinas;
import entidades.Usuario;
import filter.EntityManagerUtil;
import repositorios.RotinasRepository;

public class RotinasBean {
	
	
	AcessoBean aces1 = new AcessoBean();

	public Boolean verificaexistemodulo(String p) {
		EntityManagerUtil.conexao();

		RotinasRepository repository = new RotinasRepository(EntityManagerUtil.manager);
		Boolean verifica = false;
		Rotinas rotinabean = new Rotinas();

		try {
			rotinabean = repository.verificaexistentemodulo(p);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro verificamodulo rotina bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		if (rotinabean.getId() != null) {
			verifica = true;

		}
		return verifica;

	}

	public Boolean verificaexistepagina(String p) {
		Boolean verifica = false;
		Rotinas rotinabean = new Rotinas();

		EntityManagerUtil.conexao();
		RotinasRepository repository = new RotinasRepository(EntityManagerUtil.manager);

		try {
			rotinabean = repository.verificaexistentepagina(p);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro listapagina rotina bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		if (rotinabean.getId() != null) {
			verifica = true;

		}
		return verifica;

	}

	public void adiciona(Rotinas r) {

		EntityManagerUtil.conexao();
		RotinasRepository repository = new RotinasRepository(EntityManagerUtil.manager);

		try {
			repository.adiciona(r);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adiciona rotina bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

	}

	public Rotinas procura(Long id1) {
		Rotinas rotinaatauliza = new Rotinas();
		EntityManagerUtil.conexao();
		RotinasRepository repository = new RotinasRepository(EntityManagerUtil.manager);

		try {
			rotinaatauliza = repository.procura(id1);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procura rotina bean: " + t.getMessage());
			aces1.demologger.error("Erro procura rotina bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return rotinaatauliza;

	}

	public void remove(Long id1) {
		EntityManagerUtil.conexao();
		RotinasRepository repository = new RotinasRepository(EntityManagerUtil.manager);

		try {
			repository.removeatualiza(id1);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove rotina bean: " + t.getMessage());
			aces1.demologger.error("Erro remove rotina bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public List<Rotinas> getRotinas() {
		List<Rotinas> rotinas1 = new ArrayList<Rotinas>();
		EntityManagerUtil.conexao();
		RotinasRepository repository = new RotinasRepository(EntityManagerUtil.manager);

		try {
			rotinas1 = repository.getLista();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro listarotinas rotina bean: " + t.getMessage());
			aces1.demologger.error("Erro listarotinas rotina bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return rotinas1;
	}

	public List<Rotinas> getRotinas1(Usuario user) {
		Boolean verifica = false;
		List<Rotinas> rotinas2 = new ArrayList<Rotinas>();
		List<Acesso> acesso2 = new ArrayList<Acesso>();
		
		EntityManagerUtil.conexao();
		RotinasRepository repository = new RotinasRepository(EntityManagerUtil.manager);
		
		acesso2 = aces1.getUseracessos(user);
		

		if (user.getId() != null) {
			for (Rotinas r : repository.getLista()) {
				verifica = false;
				for (Acesso p : acesso2) {
					if (p.getRotina() != null) {
						if (r.getId() == p.getRotina().getId()) {
							verifica = true;
						}

					}
				}
				if (verifica == false) {
					rotinas2.add(r);

				}

			} // fim do for rotinas

		} // fim do id

		return rotinas2;
	}

}
