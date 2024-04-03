package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entidades.Cadfun;
import filter.EntityManagerUtil;

public class CadfunRepository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;

	public CadfunRepository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public void grava(Cadfun cadfun) {
		try {
			begin();
			if (cadfun.getIdcadfun() == null) {
				manager.persist(cadfun);
			} else {
			manager.merge(cadfun);
			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		}finally {
			close();
		}
	}

	public void remove(Long id) {
		try {
			begin();
			Cadfun cadfun = procura(id);
			manager.remove(cadfun);
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
			Cadfun cadfun = procura(id);
			cadfun.setSql_deleted("T");
			manager.merge(cadfun);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public void atualiza(Cadfun cadfun) {
		try {
			begin();
			manager.merge(cadfun);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}

	}

	public Cadfun procura(Long id) {
		return manager.find(Cadfun.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cadfun> getLista() {
		List<Cadfun> cadfun1 = new ArrayList<Cadfun>();
		Cadfun cadfunlista = new Cadfun();

		try {
			begin();
			Query query = manager
					.createNativeQuery("SELECT idcadfun,FUNCAO,HSTOTAL,HSUTIL,HSRES,HSDISPON FROM cadfun WHERE sql_deleted = 'F'");

			List<Object[]> result = query.getResultList();
			for (Object[] row : result) {
				cadfunlista = new Cadfun();
				cadfunlista.setIdcadfun(Long.parseLong(row[0].toString()));
				cadfunlista.setFUNCAO((String) row[1]);
				cadfunlista.setHSTOTAL((Double) row[2]);
				cadfunlista.setHSUTIL((Double) row[3]);
				cadfunlista.setHSRES((Double) row[4]);
				cadfunlista.setHSDISPON((Double) row[5]);
				cadfun1.add(cadfunlista);

			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
		return cadfun1;

	}
	
	public List<Cadfun> listafuncoes() {
		List<Cadfun> cadfun1 = new ArrayList<Cadfun>();
		Cadfun cadfunlista = new Cadfun();

		try {
			begin();
			Query query = manager
					.createNativeQuery("SELECT idcadfun,FUNCAO FROM cadfun WHERE sql_deleted = 'F'");

			List<Object[]> result = query.getResultList();
			for (Object[] row : result) {
				cadfunlista = new Cadfun();
				cadfunlista.setIdcadfun(Long.parseLong(row[0].toString()));
				cadfunlista.setFUNCAO((String) row[1]);
				cadfun1.add(cadfunlista);

			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
		return cadfun1;

	}
	
	public Boolean buscafuncao(String funcao) {
		Boolean valida;
		
		Cadfun cadfunlista = new Cadfun();
		Query query = this.manager.createNativeQuery("SELECT idcadfun, FUNCAO FROM Cadfun"
				+ " WHERE FUNCAO = '" + funcao + "' and sql_deleted = 'F'");

			List<Object[]> result = query.getResultList();
			for (Object[] row : result) {
				cadfunlista = new Cadfun();
				cadfunlista.setIdcadfun(Long.parseLong(row[0].toString()));
				cadfunlista.setFUNCAO((String) row[1]);
			}
			
			if(cadfunlista.getFUNCAO() == null || cadfunlista.getFUNCAO().isEmpty()) {
				valida = false;
			}else {
				valida = true;
			}
		
		
		return valida;
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
