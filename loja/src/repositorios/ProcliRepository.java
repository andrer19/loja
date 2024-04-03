package repositorios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entidades.Procli;
import filter.EntityManagerUtil;

public class ProcliRepository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;

	public ProcliRepository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public void grava(Procli procli) {
		try {
			begin();
			if (procli.getIdprocli() == null) {
				manager.persist(procli);
			} else {
			manager.merge(procli);
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
			Procli procli = procura(id);
			manager.remove(procli);
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
			Procli procli = procura(id);
			procli.setSql_deleted("T");
			manager.merge(procli);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public void atualiza(Procli procli) {
		try {
			begin();
			manager.merge(procli);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}

	}

	public Procli procura(Long id) {
		return manager.find(Procli.class, id);
	}
	
	public Boolean validaprocli(String codcli, String codpro) {
		Boolean valida = false;
		Procli proclilista = new Procli();

		try {
			begin();
			Query query = manager
					.createNativeQuery("SELECT idprocli,codcli,codpro"
							+ " FROM procli WHERE codcli='" + codcli +
							"' and codpro='" + codpro + "' and sql_deleted = 'F'");

			List<Object[]> result = query.getResultList();
			for (Object[] row : result) {
				proclilista = new Procli();
				proclilista.setIdprocli(Long.parseLong(row[0].toString()));
				proclilista.setCODCLI((String) row[1]);
				proclilista.setCODPRO((String) row[2]);

			}
			
			if(proclilista.getIdprocli() != null) {
			valida = true;	
			}
			
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		}
		return valida;

	}

	@SuppressWarnings("unchecked")
	public List<Procli> getLista() {
		List<Procli> procli1 = new ArrayList<Procli>();
		Procli proclilista = new Procli();

		try {
			begin();
			Query query = manager
					.createNativeQuery("SELECT idprocli,codcli,desccli,estrutura, codpro,descpro FROM procli WHERE sql_deleted = 'F'");

			List<Object[]> result = query.getResultList();
			for (Object[] row : result) {
				proclilista = new Procli();
				proclilista.setIdprocli(Long.parseLong(row[0].toString()));
				proclilista.setCODCLI((String) row[1]);
				proclilista.setDESCCLI((String) row[2]);
				proclilista.setESTRUTURA((String) row[3]);
				proclilista.setCODPRO((String) row[4]);
				proclilista.setDESCPRO((String) row[5]);
				procli1.add(proclilista);

			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
		return procli1;

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
