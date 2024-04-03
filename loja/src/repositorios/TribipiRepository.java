package repositorios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Cadnat;
import entidades.Tribipi;
import filter.EntityManagerUtil;

public class TribipiRepository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;

	public TribipiRepository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public void grava(Tribipi tribipi) {
		try {
			begin();
			if (tribipi.getIdtribipi() == null) {
				manager.persist(tribipi);
			} else {
				manager.merge(tribipi);
			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public void gravalista(List<Tribipi> tribipis) {
		try {
			begin();
			for (Tribipi p : tribipis) {
				if (p.getIdtribipi() == null) {
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
			Tribipi tribipi = procura(id);
			manager.remove(tribipi);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public Tribipi procura(Long id) {
		return this.manager.find(Tribipi.class, id);
	}

	public List<Tribipi> getLista() {
		Query query = this.manager.createQuery("select x from Tribipi x");
		return query.getResultList();
	}
	
	public Tribipi buscatributoipi(String codigo) {

		Tribipi tribipilista = new Tribipi();
		Query query = this.manager.createNativeQuery(
				"SELECT idtribipi, numero, titulo FROM Tribipi" + " WHERE numero = '" + codigo + "'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			tribipilista = new Tribipi();
			tribipilista.setIdtribipi(Long.parseLong(row[0].toString()));
			tribipilista.setNumero((String) row[1]);
			tribipilista.setTitulo((String) row[2]);
		}

		return tribipilista;
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
