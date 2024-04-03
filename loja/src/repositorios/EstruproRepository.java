package repositorios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Cadcli;
import entidades.Estrupro;
import entidades.Pdentc;
import filter.EntityManagerUtil;



public class EstruproRepository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;
	
	Locale local1 = new Locale("pt", "BR");
	DateFormat datestring = new SimpleDateFormat("dd/MM/yyyy", local1);

	public EstruproRepository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public void grava(Estrupro estrupro) {
		try {
			begin();
			if (estrupro.getIdestrupro() == null) {
				manager.persist(estrupro);
			} else {
				manager.merge(estrupro);
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
			 Estrupro estrupro = procura(id);
			 manager.remove(estrupro);
			 commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}
	
	public void removeatualiza(Long id) {
		try {
			begin();
			Estrupro estrupro = procura(id);
			estrupro.setSql_deleted("T");
			manager.merge(estrupro);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public void atualiza(Estrupro estrupro) {
		try {
			begin();
			manager.merge(estrupro);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}

}

	public Estrupro procura(Long id) {
		return this.manager.find(Estrupro.class, id);
	}
	
	public List<Estrupro> getLista() {
		List<Estrupro> pdentcs = new ArrayList<Estrupro>();

		try {
			begin();
			Query query = manager.createQuery("select x from Estrupro x where sql_deleted = 'F'");
			pdentcs = query.getResultList();


			Collections.sort(pdentcs, new Comparator<Estrupro>() {
				public int compare(Estrupro o1, Estrupro o2) {
					return o1.getCODPRO().compareTo(o2.getCODPRO());
				}
			});
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} 

		return pdentcs;
	}
	
	public List<Estrupro> listaitens(String produto) {
		List<Estrupro> pdentcs = new ArrayList<Estrupro>();

		try {
			begin();
			Query query = manager.createQuery("select x from Estrupro x where codpro = :produto and sql_deleted = 'F'");
			query.setParameter("produto", produto);
			pdentcs = query.getResultList();


			Collections.sort(pdentcs, new Comparator<Estrupro>() {
				public int compare(Estrupro o1, Estrupro o2) {
					return o1.getCODPRO1().compareTo(o2.getCODPRO1());
				}
			});
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} 

		return pdentcs;
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
