package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import repositorios.CadempRepository;
import repositorios.CadproRepository;
import entidades.Cademp;
import entidades.Cadpro;
import filter.EntityManagerUtil;

@ManagedBean
public class CadempBean {

	Cademp cademp = new Cademp();
	public List<Cademp> cademps;


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
