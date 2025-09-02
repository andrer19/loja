package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import repositorios.CademanumpRepository;
import repositorios.CadempRepository;
import repositorios.CadproRepository;
import entidades.Cademp;
import entidades.Cadmanuemp;
import entidades.Cadpro;
import filter.EntityManagerUtil;

@ManagedBean
public class CadempBean {

	Cademp cademp = new Cademp();
	public List<Cademp> cademps;
	private Cadmanuemp manuemp = new Cadmanuemp();
	AcessoBean aces1 = new AcessoBean();


	public Cademp buscaempresa() {
		EntityManagerUtil.conexao();
		CadempRepository repository = new CadempRepository(EntityManagerUtil.manager);
		Cademp empresa = new Cademp();

		try {
			empresa = repository.buscaempresa();		
	} catch (Throwable t) {
		JOptionPane.showMessageDialog(null, "Erro buscaempresa bean: " + t.getMessage());
		EntityManagerUtil.rollback();
	} finally {
		EntityManagerUtil.close();
	}

		return empresa;

	}

	public void adiciona(Cademp p) {
		EntityManagerUtil.conexao();
		CadempRepository repository = new CadempRepository(EntityManagerUtil.manager);

		try {
			repository.grava(p);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adiciona empresa bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Cademp procura(Long id) {
		EntityManagerUtil.conexao();
		CadempRepository repository = new CadempRepository(EntityManagerUtil.manager);

		try {
			this.cademp = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro altera empresa bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cademp;
	}

	public List<Cademp> getCodemps() {
		EntityManagerUtil.conexao();
		if (this.cademps == null) {
			CadempRepository repository = new CadempRepository(EntityManagerUtil.manager);
			try {
				this.cademps = repository.getLista();
			} catch (Throwable t) {
				JOptionPane.showMessageDialog(null, "Erro lista empresa bean: " + t.getMessage());
				EntityManagerUtil.rollback();
			} finally {
				EntityManagerUtil.close();
			}
		}

		return cademps;
	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		CadempRepository repository = new CadempRepository(EntityManagerUtil.manager);
		try {
			repository.remove(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove empresa bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		this.cademps = null;

	}
	
	public Long validaempresa() {
		Long valida = null;

		if (this.cademps == null) {
			EntityManagerUtil.conexao();
			CadempRepository repository = new CadempRepository(EntityManagerUtil.manager);

			try {
				this.cademps = repository.getLista();
			} catch (Throwable t) {
				JOptionPane.showMessageDialog(null, "Erro valida empresa bean " + t.getMessage());
				aces1.demologger.error("Erro valida empresa bean " + t.getMessage());
				EntityManagerUtil.rollback();
			} finally {
				EntityManagerUtil.close();
			}
		}

		for (Cademp p : cademps) {
			if (p.getId() != null) {
				valida = p.getId();
			}
		}
		return valida;
	}

	
	
	public Cadmanuemp rastreioemp() {
		manuemp = new Cadmanuemp();
		Cademp cademp1 = new Cademp();
		List<Cadmanuemp> manuemps = new ArrayList<Cadmanuemp>();

		EntityManagerUtil.conexao();
		CademanumpRepository repository = new CademanumpRepository(EntityManagerUtil.manager);
		try {
			manuemps = repository.getLista();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro lista empresa bean1 " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		for (Cadmanuemp p : manuemps) {
			if (p.getId() != null) {
				manuemp = p;
			}
		}

		if (manuemp.getId() == null) {
			EntityManagerUtil.conexao();
			CadempRepository repository1 = new CadempRepository(EntityManagerUtil.manager);

			try {
				cademp1 = repository1.procura(validaempresa());
				aces1.demologger.info("listou empresa bean2 ");
			} catch (Throwable t) {
				JOptionPane.showMessageDialog(null, "Erro lista empresa bean2 " + t.getMessage());
				aces1.demologger.error("Erro lista empresa bean2 " + t.getMessage());
				EntityManagerUtil.rollback();
			} finally {
				EntityManagerUtil.close();
			}

			if (cademp1.getId() != null) {
				manuemp.setDescemp(cademp1.getRazao());
			}

		}

		return manuemp;
	}
	
	
	public Cadmanuemp procuramanuemp(Long id) {
		EntityManagerUtil.conexao();
		CademanumpRepository repository = new CademanumpRepository(EntityManagerUtil.manager);
		Cadmanuemp manuemp = new Cadmanuemp();

		try {
			manuemp = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procura manuempresa bean " + t.getMessage());
			aces1.demologger.error("Erro procura manuempresa bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return manuemp;
	}
	
	public void adicionamanuemp(Cadmanuemp manutencao) {

		EntityManagerUtil.conexao();
		CademanumpRepository repository = new CademanumpRepository(EntityManagerUtil.manager);

		if (manutencao.getId() == null) {
			try {
				repository.adiciona(manutencao);
				aces1.demologger.info("adicionou manu empresabean");
			} catch (Throwable t) {
				JOptionPane.showMessageDialog(null, "Erro adiciona manu empresabean " + t.getMessage());
				aces1.demologger.error("Erro adiciona manu empresabean " + t.getMessage());
				EntityManagerUtil.rollback();
			} finally {
				EntityManagerUtil.close();
			}
		} else {

			try {
				repository.atualiza(manutencao);
				aces1.demologger.info("alterou manu empresabean");
			} catch (Throwable t) {
				JOptionPane.showMessageDialog(null, "Erro altera manu empresabean " + t.getMessage());
				aces1.demologger.error("Erro altera manu empresabean " + t.getMessage());
				EntityManagerUtil.rollback();
			} finally {
				EntityManagerUtil.close();
			}
		}

	}




//================================================================================================
	public Cademp getCademp() {
		return cademp;
	}

	public void setCademp(Cademp cademp) {
		this.cademp = cademp;
	}

	public void setCodemps(List<Cademp> cademps) {
		this.cademps = cademps;
	}

}
