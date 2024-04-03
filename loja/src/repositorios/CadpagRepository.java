package repositorios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Cadpag;
import filter.EntityManagerUtil;

public class CadpagRepository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;

	public CadpagRepository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public EntityManager getEntityManager() {
		return manager;
	}
	public void grava(Cadpag cadpag) {
		try {
			begin();
			if (cadpag.getSql_rowid() == null) {
				this.manager.persist(cadpag);
			} else {
				manager.merge(cadpag);
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
			 Cadpag cadpag = this.procura(id);
			 this.manager.remove(cadpag);
			 commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public Cadpag atualiza(Cadpag cadpag) {
		return this.manager.merge(cadpag);
	}

	public Cadpag procura(Long id) {
		return this.manager.find(Cadpag.class, id);
	}

	public List<Cadpag> getLista() {
		
		List<Cadpag> cadpaglista = new ArrayList<Cadpag>();
		Cadpag sz = new Cadpag();
		try {
			//begin();
		Query query = this.manager.createNativeQuery("select sql_rowid,numdoc,serie,"
				+ "parcela,codfor,e5_nomfor,vrbxa,dtbaixa,emissao,vencto,vrtot from Cadpag "
				+ " where sql_deleted = 'F' and emissao BETWEEN CURDATE() - INTERVAL 1460 DAY AND CURDATE()");
		
		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			sz = new Cadpag();
			sz.setSql_rowid(Long.parseLong(row[0].toString()));
			sz.setNumdoc((String) row[1]);
			sz.setSerie((String) row[2]);
			sz.setParcela((String) row[3]);
			sz.setCodfor((String) row[4]);
			sz.setE5_nomfor((String) row[5]);
			sz.setVrbxa((Double) row[6]);
			sz.setDtbaixa((Date) row[7]);
			sz.setEmissao((Date) row[8]);
			sz.setVencto((Date) row[9]);
			sz.setVrtot((Double) row[10]);
			cadpaglista.add(sz);

		}
		//commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} 
		
		return cadpaglista;
	}
	
public Boolean validadoc(String numdoc,String serie,String parcela,String codfor) {
		
		Boolean valida = false;
		Cadpag sz = new Cadpag();
		try {
			
		Query query = this.manager.createNativeQuery("select sql_rowid,numdoc,serie,"
				+ "parcela,codfor from Cadpag where numdoc = '" + numdoc + "' and serie ='" + serie 
				+ "' and parcela ='" + parcela + "' and codfor = '" + codfor + "' and sql_deleted = 'F'");
		
		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			sz = new Cadpag();
			sz.setSql_rowid(Long.parseLong(row[0].toString()));
			sz.setNumdoc((String) row[1]);
			sz.setSerie((String) row[2]);
			sz.setParcela((String) row[3]);
			sz.setCodfor((String) row[4]);
			
			
		}
		
		if(sz.getSql_rowid() != null) {
			valida = true;
		}
		
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
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
