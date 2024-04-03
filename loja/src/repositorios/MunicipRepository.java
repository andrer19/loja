package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Municip;
import filter.EntityManagerUtil;



public class MunicipRepository {

	

	public MunicipRepository(EntityManager manager) {
		
	}

	public void adiciona(Municip municip) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.persist(municip);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void remove(Long id) {
		Municip municip = this.procura(id);
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.remove(municip);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void atualiza(Municip municip) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(municip);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public Municip procura(Long id) {
		Municip municipio = new Municip();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		municipio = EntityManagerUtil.manager.find(Municip.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		
		return municipio;
	}
	
	public Municip procuraporcodigo(String cod) {
		Municip municipio = new Municip();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createQuery("SELECT x FROM Municip x WHERE x.cod_ibge ='" + cod + "' and x.sql_deleted = 'F'");
		if (!query.getResultList().isEmpty()) {
			municipio = (Municip) query.getSingleResult();
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		
		return municipio;
	}

	public List<Municip> getLista() {
		List<Municip> lista = new ArrayList<Municip>();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("select x from Municip x where x.sql_deleted = :deletado");
		query.setParameter("deletado", "F");
		lista = query.getResultList();
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		
		return lista;
	}

}
