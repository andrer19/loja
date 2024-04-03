package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Cadcamin;
import filter.EntityManagerUtil;


public class CadcaminRepository {


	public CadcaminRepository(EntityManager manager) {
		
	}
	
	public void adiciona(Cadcamin cadcamin) {
			EntityManagerUtil.conexao();
			EntityManagerUtil.begin();
			EntityManagerUtil.manager.persist(cadcamin);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();
	}


	public void remove(Long id) {
		
		Cadcamin cadcamin = procura(id);
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		cadcamin.setSql_deleted("T");
		EntityManagerUtil.manager.merge(cadcamin);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		
	}

	public void atualiza(Cadcamin cadcamin) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(cadcamin);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public Cadcamin procura(Long id) {
		Cadcamin cadcamin = new Cadcamin();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		cadcamin = EntityManagerUtil.manager.find(Cadcamin.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadcamin;
	}

	public List<Cadcamin> getLista() {
		List<Cadcamin> cadcamin1 = new ArrayList<Cadcamin>();

		//EntityManagerUtil.conexao();
		//EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("SELECT x FROM Cadcamin x WHERE x.sql_deleted = 'F'");
		if (!query.getResultList().isEmpty()) {
			cadcamin1 = query.getResultList();
		}

		//EntityManagerUtil.commit();
		//EntityManagerUtil.close();

		return cadcamin1;

	}
	
	
}
