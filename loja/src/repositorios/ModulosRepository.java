package repositorios;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Cadpro;
import entidades.Modulos;
import entidades.Rotinas;
import filter.EntityManagerUtil;

public class ModulosRepository {

	public ModulosRepository(EntityManager manager) {

	}

	public void grava(Modulos modulos) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Rotinas ro = new Rotinas();
		if (modulos.getIdmodulo() == null) {
			EntityManagerUtil.manager.persist(modulos);
			ro.setModulo(modulos.getModulo());
			ro.setPagina(modulos.getPagina());
			EntityManagerUtil.manager.persist(ro);
		} else {
			EntityManagerUtil.manager.merge(modulos);
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Modulos modulos = procura(id);
		EntityManagerUtil.manager.remove(modulos);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void atualiza(Modulos modulos) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(modulos);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public void removeatualiza(Long id) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Modulos modulos = procura(id);
		modulos.setSql_deleted("T");
		EntityManagerUtil.manager.merge(modulos);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public Modulos procura(Long id) {
		Modulos modulos = new Modulos();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		modulos = EntityManagerUtil.manager.find(Modulos.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		return modulos;
	}

	public List<Modulos> getLista() {
		List<Modulos> lista = new ArrayList<Modulos>();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("select x from Modulos x where x.sql_deleted = :deletado");
		query.setParameter("deletado", "F");
		lista = query.getResultList();
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return lista;
	}

	public Modulos procuramodulo(String modulo) {

		Modulos mo = new Modulos();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("SELECT x FROM Modulos x WHERE x.Modulo='" + modulo + "'");
		if (!query.getResultList().isEmpty()) {
			mo = (Modulos) query.getSingleResult();
		}

		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return mo;
	}

}
