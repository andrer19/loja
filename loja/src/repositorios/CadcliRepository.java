package repositorios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entidades.Cadcli;
import entidades.Modulos;
import entidades.Pdentc;
import entidades.Pdenti;
import filter.EntityManagerUtil;

public class CadcliRepository {

	public CadcliRepository(EntityManager manager) {

	}

	public void grava(Cadcli cadcli) {

		if (cadcli.getIdcadcli() == null) {
			EntityManagerUtil.conexao();
			EntityManagerUtil.begin();
			EntityManagerUtil.manager.persist(cadcli);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();
			if (cadcli.getCODCLI().equals(null) || cadcli.getCODCLI().isEmpty()) {
				cadcli.setCODCLI(String.format("%06d", Integer.parseInt(cadcli.getIdcadcli().toString())));
				EntityManagerUtil.conexao();
				EntityManagerUtil.begin();
				EntityManagerUtil.manager.merge(cadcli);
				EntityManagerUtil.commit();
				EntityManagerUtil.close();
			}
		} else {
			EntityManagerUtil.conexao();
			EntityManagerUtil.begin();
			EntityManagerUtil.manager.merge(cadcli);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();
		}

	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Cadcli cadcli = procura(id);
		EntityManagerUtil.manager.remove(cadcli);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void removeatualiza(Long id) {

		Cadcli cadcli = procura(id);
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		cadcli.setSql_deleted("T");
		EntityManagerUtil.manager.merge(cadcli);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void atualiza(Cadcli cadcli) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(cadcli);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public Cadcli procura(Long id) {
		Cadcli cadcli = new Cadcli();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		cadcli = EntityManagerUtil.manager.find(Cadcli.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadcli;
	}

	public Cadcli procuracodigo(String codigo) {
		Cadcli cadclilista = new Cadcli();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createQuery("SELECT x FROM Cadcli x WHERE x.CODCLI ='" + codigo + "' and x.sql_deleted = 'F'");
		if (!query.getResultList().isEmpty()) {
			cadclilista = (Cadcli) query.getSingleResult();
		}

		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadclilista;
	}

	public List<Cadcli> getLista() {
		List<Cadcli> cadcli1 = new ArrayList<Cadcli>();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("SELECT x FROM Cadcli x WHERE x.sql_deleted = 'F' order by x.id DESC");
		if (!query.getResultList().isEmpty()) {
			cadcli1 = query.getResultList();
		}

		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadcli1;

	}
	
	public List<Cadcli> getListaativos() {
		List<Cadcli> cadcli1 = new ArrayList<Cadcli>();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("SELECT x FROM Cadcli x WHERE x.sql_deleted = 'F' and x.INATIVO = false");
		if (!query.getResultList().isEmpty()) {
			cadcli1 = query.getResultList();
		}

		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadcli1;

	}

}
