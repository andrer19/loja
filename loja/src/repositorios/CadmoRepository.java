package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Cadfun;
import entidades.Cadmo;
import filter.EntityManagerUtil;

public class CadmoRepository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;

	public CadmoRepository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public void grava(Cadmo cadmo) {
		try {
			begin();
			if (cadmo.getIdcadmo() == null) {
				manager.persist(cadmo);
			} else {
				manager.merge(cadmo);
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
			Cadmo cadmo = procura(id);
			manager.remove(cadmo);
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
			Cadmo cadmo = procura(id);
			cadmo.setSql_deleted("T");
			manager.merge(cadmo);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public void atualiza(Cadmo cadmo) {
		try {
			begin();
			manager.merge(cadmo);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}

	}

	public Cadmo procura(Long id) {
		return manager.find(Cadmo.class, id);
	}
	
	public Boolean buscamatricula(String matricula) {
		Boolean valida;
		
		Cadmo cadmolista = new Cadmo();
		Query query = this.manager.createNativeQuery("SELECT idcadmo, CODMO FROM Cadmo"
				+ " WHERE CODMO = '" + matricula + "' and sql_deleted = 'F'");

			List<Object[]> result = query.getResultList();
			for (Object[] row : result) {
				cadmolista = new Cadmo();
				cadmolista.setIdcadmo(Long.parseLong(row[0].toString()));
				cadmolista.setCODMO((String) row[1]);
			}
			
			if(cadmolista.getCODMO() == null || cadmolista.getCODMO().isEmpty()) {
				valida = false;
			}else {
				valida = true;
			}
		
		
		return valida;
	}

	@SuppressWarnings("unchecked")
	public List<Cadmo> getLista() {
		List<Cadmo> cadmo1 = new ArrayList<Cadmo>();
		Cadmo cadmolista = new Cadmo();

		try {
			begin();
			Query query = manager.createNativeQuery(
					"SELECT idcadmo,codmo,nome,funcao,setor FROM cadmo WHERE sql_deleted = 'F'");

			List<Object[]> result = query.getResultList();
			for (Object[] row : result) {
				cadmolista = new Cadmo();
				cadmolista.setIdcadmo(Long.parseLong(row[0].toString()));
				cadmolista.setCODMO((String) row[1]);
				cadmolista.setNome((String) row[2]);
				cadmolista.setFuncao((String) row[3]);
				cadmolista.setSETOR((String) row[4]);
				cadmo1.add(cadmolista);

			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
		return cadmo1;

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
