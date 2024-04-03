package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Cadnat;
import entidades.Tribicms;
import filter.EntityManagerUtil;

public class CadnatRepository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;

	public CadnatRepository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public void grava(Cadnat cadnat) {
		try {
			begin();
			if (cadnat.getIdcadnat() == null) {
				manager.persist(cadnat);
			} else {
				manager.merge(cadnat);
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
			Cadnat cadnat = procura(id);
			manager.remove(cadnat);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		}
	}

	public void removeatualiza(Long id) {
		try {
			begin();
			Cadnat cadnat = procura(id);
			cadnat.setSql_deleted("T");
			manager.merge(cadnat);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		}
	}

	public void atualiza(Cadnat cadnat) {
		try {
			begin();
			manager.merge(cadnat);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		}

	}

	public Cadnat procura(Long id) {
		return manager.find(Cadnat.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cadnat> getLista() {
		List<Cadnat> cadnat1 = new ArrayList<Cadnat>();
		Cadnat cadnatlista = new Cadnat();

		try {
			begin();
			Query query = manager
					.createNativeQuery("SELECT idcadnat,NUMERO,TIPO,CFOP,DESCNAT  FROM cadnat WHERE sql_deleted = 'F'");

			List<Object[]> result = query.getResultList();
			for (Object[] row : result) {
				cadnatlista = new Cadnat();
				cadnatlista.setIdcadnat(Long.parseLong(row[0].toString()));
				cadnatlista.setNUMERO((String) row[1]);
				cadnatlista.setTIPO((String) row[2]);
				cadnatlista.setCFOP((String) row[3]);
				cadnatlista.setDESCNAT((String) row[4]);
				cadnat1.add(cadnatlista);

			}

			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
		return cadnat1;

	}

	public Boolean buscanatureza(String funcao) {
		Boolean valida;

		Cadnat cadnatlista = new Cadnat();
		Query query = this.manager.createNativeQuery(
				"SELECT idcadnat, numero FROM Cadnat" + " WHERE numero = '" + funcao + "' and sql_deleted = 'F'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			cadnatlista = new Cadnat();
			cadnatlista.setIdcadnat(Long.parseLong(row[0].toString()));
			cadnatlista.setNUMERO((String) row[1]);
		}

		if (cadnatlista.getNUMERO() == null || cadnatlista.getNUMERO().isEmpty()) {
			valida = false;
		} else {
			valida = true;
		}

		return valida;
	}
	
	public Cadnat buscaporcodigo(String codigo) {

		Cadnat cadnatlista = new Cadnat();
		Query query = this.manager.createNativeQuery(
				"SELECT idcadnat, numero FROM Cadnat" + " WHERE numero = '" + codigo + "' and sql_deleted = 'F'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			cadnatlista = new Cadnat();
			cadnatlista = procura(Long.parseLong(row[0].toString()));
		}

		return cadnatlista;
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
