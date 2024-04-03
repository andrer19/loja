package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Nfentc;
import entidades.Nfenti;
import filter.EntityManagerUtil;

public class NfentiRepository {

	@PersistenceContext(unitName = "play")
	//private final EntityManager manager;


	// public NfentiRepository() {
	private EntityManager manager = EntityManagerUtil.getManager();
	// }

	public void grava(Nfenti p) {
		try {
			begin();
				
				if (p.getIdnfenti() == null) {
					manager.persist(p);
				} else {
					manager.merge(p);
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
		Nfenti nfenti1 = new Nfenti();

		try {
			begin();
			manager.remove(nfenti1);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}
	}

	public void removeatualiza(Long id) {
		Nfenti nfenti1 = new Nfenti();

		try {
			begin();
			nfenti1 = procura(id);
			nfenti1.setSql_deleted("T");
			manager.merge(nfenti1);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
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
			}
			//commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro voltaquantidade item: " + e.getMessage());
		}

	}

	public void atualiza(Nfenti nfenti) {

		try {
			begin();
			manager.merge(nfenti);
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		} finally {
			close();
		}

	}

	public Nfenti procura(Long id) {
		return manager.find(Nfenti.class, id);
	}

	public Nfentc procuracabecario(Long id) {
		return manager.find(Nfentc.class, id);
	}

	public void removeitens(String pedido) {

		try {
			begin();
			
			Query query = manager.createNativeQuery(
					"SELECT idnfenti,numdoc FROM Nfenti WHERE numdoc ='" + pedido + "' and sql_deleted = 'F'");
			@SuppressWarnings("unchecked")
			List<Object[]> result = query.getResultList();
			for (Object[] row : result) {
				Nfenti nfenti1 = new Nfenti();
				nfenti1 = procura(Long.parseLong(row[0].toString()));
				nfenti1.setSql_deleted("T");
				manager.merge(nfenti1);
			}
			commit();
		} catch (Throwable t) {
			t.printStackTrace();
			rollback();
		}
	}

	public List<Nfenti> getLista(String num, String fornecedor) {
		List<Nfenti> nfentilista = new ArrayList<Nfenti>();
		try {
			begin();
			//JOptionPane.showMessageDialog(null, "codigo: " + fornecedor + " nota: " + num);
			Query query = manager.createQuery("select x from Nfenti x where x.numdoc = :numero and x.codfor = :forn and x.sql_deleted = :deletado");
			query.setParameter("numero", num);
			query.setParameter("forn", fornecedor);
			query.setParameter("deletado", "F");
			nfentilista = query.getResultList();
			commit();
			//JOptionPane.showMessageDialog(null, "fechou: ");
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null,"ERRO NOS ITENS NF ENTRADA: " + t.getStackTrace());
			rollback();
		}

		return nfentilista;
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
