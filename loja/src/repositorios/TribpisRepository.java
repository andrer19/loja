package repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Tribpis;
import filter.EntityManagerUtil;

public class TribpisRepository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;

	public TribpisRepository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public void grava(Tribpis tribpis) {
		try {
			begin();
			if (tribpis.getIdtribpis() == null) {
				manager.persist(tribpis);
			} else {
				manager.merge(tribpis);
			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public void gravalista(List<Tribpis> tribpiss) {
		try {
			begin();
			for (Tribpis p : tribpiss) {
				if (p.getIdtribpis() == null) {
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
			Tribpis tribpis = procura(id);
			manager.remove(tribpis);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public Tribpis procura(Long id) {
		return this.manager.find(Tribpis.class, id);
	}

	public List<Tribpis> getLista() {
		Query query = this.manager.createQuery("select x from Tribpis x");
		return query.getResultList();
	}
	
	public Tribpis buscatributopis(String codigo) {

		Tribpis tribpislista = new Tribpis();
		Query query = this.manager.createNativeQuery(
				"SELECT idtribpis, numero, titulo FROM Tribpis" + " WHERE numero = '" + codigo + "'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			tribpislista = new Tribpis();
			tribpislista.setIdtribpis(Long.parseLong(row[0].toString()));
			tribpislista.setNumero((String) row[1]);
			tribpislista.setTitulo((String) row[2]);
		}

		return tribpislista;
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
