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
import entidades.Tribicms;
import filter.EntityManagerUtil;

public class TribicmsRepository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;

	public TribicmsRepository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public void grava(Tribicms tribicms) {
		try {
			begin();
			if (tribicms.getIdtribicms() == null) {
				manager.persist(tribicms);
			} else {
				manager.merge(tribicms);
			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public void gravalista(List<Tribicms> tribicmss) {
		try {
			begin();
			for (Tribicms p : tribicmss) {
				if (p.getIdtribicms() == null) {
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
			Tribicms tribicms = procura(id);
			manager.remove(tribicms);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public Tribicms procura(Long id) {
		return this.manager.find(Tribicms.class, id);
	}

	public List<Tribicms> getLista() {
		Query query = this.manager.createQuery("select x from Tribicms x");
		return query.getResultList();
	}
	
	public Tribicms buscatributoicms(String codigo) {

		Tribicms tribicmslista = new Tribicms();
		Query query = this.manager.createNativeQuery(
				"SELECT idtribicms, numero, titulo FROM Tribicms" + " WHERE numero = '" + codigo + "'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			tribicmslista = new Tribicms();
			tribicmslista.setIdtribicms(Long.parseLong(row[0].toString()));
			tribicmslista.setNumero((String) row[1]);
			tribicmslista.setTitulo((String) row[2]);
		}

		return tribicmslista;
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
