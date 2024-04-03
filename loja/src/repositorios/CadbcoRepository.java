package repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Cadbco;
import entidades.Cadnat;
import filter.EntityManagerUtil;

public class CadbcoRepository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;

	public CadbcoRepository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public void grava(Cadbco cadbco) {
		try {
			begin();
			if (cadbco.getSql_rowid() == null) {
				manager.persist(cadbco);
			} else {
				manager.merge(cadbco);
			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public void gravalista(List<Cadbco> cadbcos) {
		try {
			begin();
			for (Cadbco p : cadbcos) {
				if (p.getSql_rowid() == null) {
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
			Cadbco cadbco = procura(id);
			manager.remove(cadbco);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public Cadbco procura(Long id) {
		return this.manager.find(Cadbco.class, id);
	}

	public List<Cadbco> getLista() {
		Query query = this.manager.createQuery("select x from Cadbco x where x.sql_deleted = :deletedo");
		query.setParameter("deletedo", "F");
		return query.getResultList();
	}
	
	public Cadbco buscaporcodigo(String codigo) {

		Cadbco cadbcolista = new Cadbco();
		Query query = this.manager.createNativeQuery(
				"SELECT sql_rowid, codbco FROM Cadbco" + " WHERE codbco = '" + codigo + "' and sql_deleted = 'F'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			cadbcolista = new Cadbco();
			cadbcolista = procura(Long.parseLong(row[0].toString()));
		}

		return cadbcolista;
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
