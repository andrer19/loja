package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Cadcusto;
import filter.EntityManagerUtil;

public class CadcustoRepository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;

	public CadcustoRepository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public void grava(Cadcusto cadcusto) {
		try {
			begin();
			if (cadcusto.getIdcusto() == null) {
				manager.persist(cadcusto);
				cadcusto.setCodcusto(String.format("%03d", cadcusto.getIdcusto().toString()));
				manager.merge(cadcusto);
			} else {
				manager.merge(cadcusto);
			}
			commit();
		} catch (Throwable t) {
			 t.getStackTrace();
			rollback();
		} 
	}

	public void remove(Long id) {
		try {
			begin();
			Cadcusto cadcusto = procura(id);
			manager.remove(cadcusto);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		}
	}

	public void removeatualiza(Long id) {
		try {
			begin();
			Cadcusto cadcusto = procura(id);
			cadcusto.setSql_deleted("T");
			manager.merge(cadcusto);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		}
	}

	public void atualiza(Cadcusto cadcusto) {
		try {
			begin();
			manager.merge(cadcusto);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		}

	}

	public Cadcusto procura(Long id) {
		return manager.find(Cadcusto.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cadcusto> getLista() {
		List<Cadcusto> cadcusto1 = new ArrayList<Cadcusto>();
		Cadcusto cadcustolista = new Cadcusto();

		try {
			begin();
			Query query = manager
					.createNativeQuery("SELECT idcusto,codemp,codcusto,desccusto,baixa,tipo,conta  FROM cadcusto WHERE sql_deleted = 'F'");

			List<Object[]> result = query.getResultList();
			for (Object[] row : result) {
				cadcustolista = new Cadcusto();
				cadcustolista.setIdcusto(Long.parseLong(row[0].toString()));
				cadcustolista.setCodemp((String) row[1]);
				cadcustolista.setCodcusto((String) row[2]);
				cadcustolista.setDesccusto((String) row[3]);
				cadcustolista.setBaixa((int) row[4]);
				cadcustolista.setTipo((String) row[5]);
				cadcustolista.setConta((String) row[6]);
				cadcusto1.add(cadcustolista);

			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
			
		} 
		return cadcusto1;

	}
	
	public Cadcusto buscaporcodigo(String codigo) {

		Cadcusto cadcustolista = new Cadcusto();
		Query query = this.manager.createNativeQuery(
				"SELECT idcusto,codcusto FROM Cadcusto" + " WHERE codcusto = '" + codigo + "' and sql_deleted = 'F'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			cadcustolista = new Cadcusto();
			cadcustolista = procura(Long.parseLong(row[0].toString()));
		}

		return cadcustolista;
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
