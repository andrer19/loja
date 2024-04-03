package repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Tribcofins;
import filter.EntityManagerUtil;

public class TribcofinsRepository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;

	public TribcofinsRepository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public void grava(Tribcofins tribcofins) {
		try {
			begin();
			if (tribcofins.getIdtribcofins() == null) {
				manager.persist(tribcofins);
			} else {
				manager.merge(tribcofins);
			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public void gravalista(List<Tribcofins> tribcofinss) {
		try {
			begin();
			for (Tribcofins p : tribcofinss) {
				if (p.getIdtribcofins() == null) {
					manager.persist(p);
				}
			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public void remove(Long id) {
		try {
			begin();
			Tribcofins tribcofins = procura(id);
			manager.remove(tribcofins);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public Tribcofins procura(Long id) {
		return this.manager.find(Tribcofins.class, id);
	}

	public List<Tribcofins> getLista() {
		Query query = this.manager.createQuery("select x from Tribcofins x");
		return query.getResultList();
	}
	
	public Tribcofins buscatributocofins(String codigo) {

		Tribcofins tribcofinslista = new Tribcofins();
		Query query = this.manager.createNativeQuery(
				"SELECT idtribcofins, numero, titulo FROM Tribcofins" + " WHERE numero = '" + codigo + "'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			tribcofinslista = new Tribcofins();
			tribcofinslista.setIdtribcofins(Long.parseLong(row[0].toString()));
			tribcofinslista.setNumero((String) row[1]);
			tribcofinslista.setTitulo((String) row[2]);
		}

		return tribcofinslista;
	}

	public void fechaconexao() {
		close();
	}

	private void begin() {
		manager.getTransaction().begin();
	}

	private void commit() {
		manager.getTransaction().commit();
	}

	private void rollback() {
		manager.getTransaction().rollback();
	}

	private void close() {
		if (manager.isOpen()) {
			manager.close();
		}
	}
}
