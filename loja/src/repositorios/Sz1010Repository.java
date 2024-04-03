package repositorios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Nfenti;
import entidades.Procli;
import entidades.Sz1010;
import filter.EntityManagerUtil;

public class Sz1010Repository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;

	public Sz1010Repository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public EntityManager getEntityManager() {
		return manager;
	}
	public void grava(Sz1010 sz1010) {
		try {
			begin();
			if (sz1010.getSql_rowid() == null) {
				this.manager.persist(sz1010);
			} else {
				manager.merge(sz1010);
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
			 Sz1010 sz1010 = this.procura(id);
			 this.manager.remove(sz1010);
			 commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public Sz1010 atualiza(Sz1010 sz1010) {
		return this.manager.merge(sz1010);
	}

	public Sz1010 procura(Long id) {
		return this.manager.find(Sz1010.class, id);
	}

	public List<Sz1010> getLista() {
		
		List<Sz1010> sz1010lista = new ArrayList<Sz1010>();
		Sz1010 sz = new Sz1010();
		try {
			//begin();
		Query query = this.manager.createNativeQuery("select sql_rowid,z1_num,z1_doc,"
				+ "z1_codpro,z1_qtde,z1_data,z1_nomefor,z1_hora from Sz1010 "
				+ " where sql_deleted = 'F' and z1_data BETWEEN CURDATE() - INTERVAL 1460 DAY AND CURDATE()");
		
		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			sz = new Sz1010();
			sz.setSql_rowid(Long.parseLong(row[0].toString()));
			sz.setZ1_num((String) row[1]);
			sz.setZ1_doc((String) row[2]);
			sz.setZ1_codpro((String) row[3]);
			sz.setZ1_qtde((Double) row[4]);
			sz.setZ1_data((Date) row[5]);
			sz.setZ1_nomefor((String) row[6]);
			sz.setZ1_hora((String) row[7]);
			sz1010lista.add(sz);

		}
		//commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} 
		
		return sz1010lista;
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
