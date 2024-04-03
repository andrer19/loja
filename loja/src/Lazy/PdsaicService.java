package Lazy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import Lazy.GenericService;
import entidades.Pdsaic;
import filter.EntityManagerUtil;
import repositorios.PdsaicRepository;
import repositorios.Repository;

public class PdsaicService implements GenericService<Pdsaic> {

	// representando o banco de dados
	private List<Pdsaic> dataSource;
	private List<Pdsaic> list;

	@Override
	public List<Pdsaic> buscaPaginada(int inicio, int fim) {

		return dataSource;

	}

	@Override
	public List<Pdsaic> buscaPaginada(int inicio, int fim, Pdsaic obj) {
		return dataSource;
	}

	@Override
	public List<Pdsaic> buscaPaginada(int inicio, int fim, Map<String, Object> filters, Pdsaic obj) {
		// TODO Auto-generated method stub
		return dataSource;
	}

	@Override
	public List<Pdsaic> buscaPaginada(int inicio, int fim, Map<String, Object> filters) {
		EntityManager manager = this.getManager();
		PdsaicRepository reposytori = new PdsaicRepository(manager);

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Pdsaic> query = cb.createQuery(Pdsaic.class);
		Root<Pdsaic> root = query.from(Pdsaic.class);

		final List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate deletado = root.get("sql_deleted").in("F");
		predicates.add(cb.and(deletado));
		query.orderBy(cb.desc(root.get("idpdsaic")));

		for (Entry<String, Object> e : filters.entrySet()) {
			final String key = e.getKey();
			final String value = (String) e.getValue();

			if (key != null && value != null) {
				predicates.add(cb.like(root.<String>get(key), value + "%"));
			}
		}

		query = query.where(predicates.toArray(new Predicate[predicates.size()]));
		List<Pdsaic> lista = manager.createQuery(query).setFirstResult(inicio).setMaxResults(fim).getResultList();

		return lista;
	}

	public List<Pdsaic> buscaPaginada(Set<String> organizationTypes, String countryId, String stateId) {
		EntityManager manager = EntityManagerUtil.getManager();
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Pdsaic> query = cb.createQuery(Pdsaic.class);
		Root<Pdsaic> org = query.from(Pdsaic.class);
		query.select(org);
		query.where(cb.and(org.get("organizationType").get("id").in(organizationTypes),
				cb.equal(org.get("state").get("id"), stateId), cb.equal(org.get("country").get("id"), countryId)));
		return manager.createQuery(query).getResultList();
	}

	
	@Override
	public Object getRowKey(entidades.Pdsaic mandatory) {
		return mandatory;
	}

	@Override
	public Pdsaic getRowData(String rowKey) {
		Pdsaic cadpro = new Pdsaic();

		EntityManager manager = EntityManagerUtil.getManager();
		Repository<Pdsaic> repository = new <Pdsaic>Repository(manager);
		cadpro = repository.procura(cadpro, Long.parseLong(rowKey));

		return cadpro;
	}

	@Override
	public int countLinhas() {

		EntityManager manager = EntityManagerUtil.getManager();
		Repository repository = new Repository(manager);
		return repository.QtdeTotal("Pdsaic").intValue();

	}

	public Date dataatual() {
		Date data = null;

		EntityManager manager = EntityManagerUtil.getManager();

		try {

			Date f = (Date) manager.createNativeQuery("select NOW()").getSingleResult();

			data = f;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;
	}
	
	private EntityManager getManager() {
		EntityManager manager = EntityManagerUtil.getManager();
		
		return manager;
	}

}
