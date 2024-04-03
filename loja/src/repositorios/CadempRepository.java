package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Cademp;
import filter.EntityManagerUtil;

public class CadempRepository {

	public CadempRepository(EntityManager manager) {

	}

	public void grava(Cademp cademp) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		if (cademp.getId() == null) {
			EntityManagerUtil.manager.persist(cademp);
			EntityManagerUtil.commit();
		} else {
			EntityManagerUtil.manager.merge(cademp);
			EntityManagerUtil.commit();
		}

		EntityManagerUtil.close();
	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Cademp cademp = procura(id);
		EntityManagerUtil.manager.remove(cademp);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void atualiza(Cademp cademp) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(cademp);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public Cademp procura(Long id) {
		Cademp cademp = new Cademp();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		cademp = EntityManagerUtil.manager.find(Cademp.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		return cademp;
	}

	public List<Cademp> getLista() {
		List<Cademp> lista = new ArrayList<Cademp>();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("select x from Cademp x");
		lista = query.getResultList();
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return lista;
	}

	public Cademp buscaempresa() {
		String num = "01";
		Cademp cademp = new Cademp();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("select x from Cademp x where x.sql_deleted = 'F'");
		
		if (!query.getResultList().isEmpty()) {
			cademp = (Cademp) query.getSingleResult();
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cademp;

	}
}
