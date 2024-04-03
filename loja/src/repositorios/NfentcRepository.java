package repositorios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Cadfor;
import entidades.Nfentc;
import filter.EntityManagerUtil;

public class NfentcRepository {

	@PersistenceContext(unitName = "play")
	private final EntityManager manager;

	public NfentcRepository() {
		this.manager = EntityManagerUtil.getManager();
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public void adiciona(Nfentc nfentc) {

		try {
			begin();
			manager.persist(nfentc);
			if (nfentc.getNUMDOC().equals(null) || nfentc.getNUMDOC().isEmpty()) {
				nfentc.setNUMDOC(String.format("%06d", Integer.parseInt(nfentc.getIdnfentc().toString())));
				manager.merge(nfentc);
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
			Nfentc nfentc = procura(id);
			manager.remove(nfentc);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}

	}
	
	public Boolean validanota(String nota, String serie, String fornecedor) {

		Boolean valida = false;
		Nfentc nfentc1 = new Nfentc();

		try {
			//begin();
			Query query = manager.createNativeQuery("SELECT idnfentc,numdoc,serie,codfor FROM Nfentc WHERE numdoc='"
					+ nota + "' and serie='" + serie + "' and codfor='" + fornecedor + "' and sql_deleted='F'");
			List<Object[]> result = query.getResultList();
			for (Object[] row : result) {
				nfentc1 = new Nfentc();
				nfentc1.setIdnfentc(Long.parseLong(row[0].toString()));
				nfentc1.setNUMDOC((String) row[1]);
				nfentc1.setSERIE((String) row[2]);
				nfentc1.setCODFOR((String) row[3]);
			}			
			if(nfentc1.getNUMDOC() != null) {
				valida = true;
			}
			
			//commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"ERRO NOTA SIMILAR: " + e.getStackTrace());
			//rollback();
		}
		return valida; 
	}

	public void removeatualiza(Long id) {

		Nfentc nfentc1 = new Nfentc();

		try {
			begin();
			nfentc1 = procura(id);
			nfentc1.setSql_deleted("T");
			manager.merge(nfentc1);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} 
	}

	public void retornasaldo(String produto, Double qtde) {
		try {
			//begin();
			Query query2 = manager.createNativeQuery(
					"select idcadpro,codpro, qtatual,material from cadpro where CODPRO = '" + produto + "'");

			List<Object[]> result1 = query2.getResultList();
			for (Object[] row1 : result1) {
				 
				double qtde1 = Double.parseDouble(row1[2].toString()) + qtde;

				Double qtdevendida = Double.parseDouble(row1[3].toString()) - qtde;
				
				Query query1 = manager.createNativeQuery("update cadpro set qtatual =" + qtde1 + ", material = "
						+ qtdevendida + " where codpro ='" + row1[1].toString() + "'");
				query1.executeUpdate();
				qtde1 = 0;
				break;
			}
			//commit();
		} catch (Exception e) {
 			JOptionPane.showMessageDialog(null, "Erro voltaquantidade: " + e.getMessage());
 		}
		
	}

	public void atualiza(Nfentc nfentc) {
		try {
			begin();
			manager.merge(nfentc);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}

	}

	public Nfentc procura(Long id) {
		return manager.find(Nfentc.class, id);
	}

	public List<Nfentc> getLista() {
		//List<Nfentc> nfentc1 = new ArrayList<Nfentc>();
		List<Nfentc> nfentcs = new ArrayList<Nfentc>();
		Nfentc nfentc1 = new Nfentc();

		try {
			begin();
			Query query = manager.createNativeQuery("select Idnfentc,NUMDOC,SERIE,EMISSAO,NATOPER,CODFOR,descfor from Nfentc where sql_deleted = 'F'");
			List<Object[]> result = query.getResultList();
			
			for (Object[] row : result) {
				nfentc1 = new Nfentc();
				
				nfentc1.setIdnfentc(Long.parseLong(row[0].toString()));
				nfentc1.setNUMDOC((String) row[1]);
				nfentc1.setSERIE((String) row[2]);
				nfentc1.setEMISSAO((Date) row[3]);
				nfentc1.setNATOPER((String) row[4]);
				nfentc1.setCODFOR((String) row[5]);
				nfentc1.setCODFOR((String) row[5]);
				nfentc1.setDESCFOR((String) row[6]);
				nfentcs.add(nfentc1);
			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} 

		return nfentcs;
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
		;
	}

	private void close() {
		if (manager.isOpen()) {
			manager.close();
		}
	}
}
