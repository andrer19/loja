package repositorios;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import filter.EntityManagerUtil;

public class Repository<T> {
	private EntityManager manager;

	public Repository(EntityManager manager) {
		
	}

	public T procura(T clazz, Long id) {
		clazz = manager.find((Class<T>) clazz.getClass(), id);
		return clazz;
	}

	public void altera(T obj, String tabela, Long id, List<T> lista, T obj1) {

		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
		//repositorylog.adicionaLog("Alteracao", tabela, id);

		this.manager.merge(obj);

		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).equals(obj1)) {
					lista.set(i, obj);
				}
			}
		}

	}

	public void altera(T obj, String tabela, Long id) {

		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
		//repositorylog.adicionaLog("Alteracao", tabela, id);

		this.manager.merge(obj);

	}

	public void salva(T obj, String tabela) {
		this.manager.persist(obj);
	}

	public void salva(T obj) {
		
		this.manager.persist(obj);
	}
	
	public void salvalog(Long id, String tabela) {
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
		//repositorylog.adicionaLog("Inclusao", tabela, id);
	}

	public List<T> getLista(String tabela) {
		Query query = this.manager.createQuery(
				"select x from " + tabela + " x  where x.sql_deleted = :sql_deleted order by id desc");
		query.setParameter("sql_deleted", "F");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getListasql(String sql) {
		
		Query query = this.manager.createQuery(sql);
		return query.getResultList();
	}

	public void removet(Long id1, String tabela,String campo) {

		Query query = this.manager
				.createNativeQuery("update " + tabela + " set sql_deleted = 'T' where " +  campo +" = :sql_rowid");
		query.setParameter("sql_rowid", id1);
		query.executeUpdate();

	}

	public List<T> removet(T id1, String tabela, List<T> lista) {

		Query query = this.manager
				.createNativeQuery("update " + tabela + " set sql_deleted = 'T' where sql_rowid = :sql_rowid");
		query.setParameter("sql_rowid", id1);
		query.executeUpdate();

		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).equals(id1)) {
					lista.remove(i);
				}
			}
		}

		return lista;
	}

	public List<T> ListaLazy(int primeiroDigito, int segundoDigito, T clazz, String id2) {
		return this.manager.createQuery("FROM " + id2, (Class<T>) clazz.getClass()).setFirstResult(primeiroDigito)
				.setMaxResults(segundoDigito).getResultList();
	}

	public Long QtdeTotal(String id1) {
		return this.manager.createQuery("SELECT count(c) FROM "+id1+" c where sql_deleted = 'F'", Long.class).getSingleResult();
	}

	
	public List<T> ListaPagFilter(int inicio, int fim, T clazz, Map<String, Object> filters) {

		List<T> cadprol = new ArrayList<T>();
		boolean match = true;
		CriteriaQuery<T> query = this.manager.getCriteriaBuilder().createQuery((Class<T>) clazz.getClass());
		Root<T> root = query.from((Class<T>) clazz.getClass());
		final List<Predicate> predicates = new ArrayList<Predicate>();
		for (Entry<String, Object> e : filters.entrySet()) {
			final String key = e.getKey();
			final String value = (String) e.getValue();

			if (key != null && value != null) {
				predicates.add(manager.getCriteriaBuilder().like(root.get(key), value));
			}
		}
		query = query.where(predicates.toArray(new Predicate[predicates.size()]));
		List<T> lista = manager.createQuery(query).setFirstResult(inicio).setMaxResults(fim).getResultList();

		return lista;

	}
	
	@SuppressWarnings("unchecked")
	public List<T> getListap(String sql) {
		
		Query query = this.manager.createQuery(sql);
		return query.getResultList();
	}
	
	public boolean registroExiste(String sql)
	{
		if (this.getListap(sql).size()>0) {
			
			return true;
			
		}else {
			
			return false;
			
		}
	}
	

}
