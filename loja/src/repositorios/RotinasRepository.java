package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import beans.AcessoBean;
import entidades.Acesso;
import entidades.Cademp;
import entidades.Cadpro;
import entidades.Rotinas;
import entidades.Usuario;
import filter.EntityManagerUtil;

public class RotinasRepository {

	public RotinasRepository(EntityManager manager) {

	}

	public void adiciona(Rotinas rotinas) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		if (rotinas.getId() == null) {
			EntityManagerUtil.manager.persist(rotinas);
		} else {
			EntityManagerUtil.manager.merge(rotinas);
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public void remove(Long id) {
		Rotinas rotinas = this.procura(id);
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.remove(rotinas);;
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void removeatualiza(Long id) {

			Rotinas rotinas = this.procura(id);
			EntityManagerUtil.conexao();
			EntityManagerUtil.begin();
			rotinas.setSql_deleted("T");
			EntityManagerUtil.manager.merge(rotinas);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();
	}

	public void atualiza(Rotinas rotinas) {
		
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(rotinas);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public Rotinas procura(Long id) {
		Rotinas rotinas = new Rotinas();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		rotinas = EntityManagerUtil.manager.find(Rotinas.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		return rotinas;
	}

	public List<Rotinas> getLista() {
		List<Rotinas> lista = new ArrayList<Rotinas>();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("select x from Rotinas x where x.sql_deleted = 'F'");
		
		if(!query.getResultList().isEmpty()) {
			lista = query.getResultList();
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		
		return lista;
	}

	public Rotinas verificaexistentemodulo(String p) {

		Rotinas rotinalista = new Rotinas();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		
		Query query = EntityManagerUtil.manager.createNativeQuery(
				"SELECT id, modulo, pagina FROM Rotinas" + " WHERE modulo = '" + p + "' and sql_deleted = 'F'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			rotinalista = new Rotinas();
			rotinalista.setId(Long.parseLong(row[0].toString()));
			rotinalista.setModulo((String) row[1]);
			rotinalista.setPagina((String) row[2]);
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return rotinalista;
	}

	public Rotinas verificaexistentepagina(String p) {

		Rotinas rotinalista = new Rotinas();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		
		Query query = EntityManagerUtil.manager.createNativeQuery(
				"SELECT id, modulo, pagina FROM Rotinas" + " WHERE pagina = '" + p + "' and sql_deleted = 'F'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			rotinalista = new Rotinas();
			rotinalista.setId(Long.parseLong(row[0].toString()));
			rotinalista.setModulo((String) row[1]);
			rotinalista.setPagina((String) row[2]);

		}
		
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return rotinalista;
	}
}
