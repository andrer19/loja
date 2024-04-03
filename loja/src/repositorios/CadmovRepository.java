package repositorios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entidades.Cadmov;
import entidades.Pdentc;
import entidades.Pdenti;
import filter.EntityManagerUtil;

public class CadmovRepository {

	public CadmovRepository(EntityManager manager) {
		
	}

	public void grava(Cadmov cadmov) {
		
		    EntityManagerUtil.conexao();
		    EntityManagerUtil.begin();
			if (cadmov.getId() == null) {
				EntityManagerUtil.manager.persist(cadmov);
				EntityManagerUtil.commit();
			} else {
				EntityManagerUtil.manager.merge(cadmov);
				EntityManagerUtil.commit();
			}
			
			EntityManagerUtil.close();
	}

	public void remove(Long id) {
		
		    EntityManagerUtil.begin();
			Cadmov cadmov = procura(id);
			EntityManagerUtil.manager.remove(cadmov);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();
	}

	public void removeatualiza(Long id) {
		    EntityManagerUtil.begin();
			Cadmov cadmov = procura(id);
			cadmov.setSql_deleted("T");
			EntityManagerUtil.manager.merge(cadmov);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();

	}

	public void atualiza(Cadmov cadmov) {
		
		    EntityManagerUtil.begin();
		    EntityManagerUtil.manager.merge(cadmov);
		    EntityManagerUtil.commit();
		    EntityManagerUtil.close();

	}

	public Cadmov procura(Long id) {
		return EntityManagerUtil.manager.find(Cadmov.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cadmov> getListaporcodigo(String produto) {
		List<Cadmov> cadmov1 = new ArrayList<Cadmov>();
		Cadmov cadmovlista = new Cadmov();

			EntityManagerUtil.begin();
			Query query = EntityManagerUtil.manager.createNativeQuery( 
					"SELECT id,codpro FROM Cadmov WHERE codpro = '"+ produto + "' and sql_deleted = 'F'");

			List<Object[]> result = query.getResultList();
			for (Object[] row : result) {
				cadmovlista = new Cadmov();
				cadmovlista = procura(Long.parseLong(row[0].toString()));
				cadmov1.add(cadmovlista);

			}
			EntityManagerUtil.commit();
			EntityManagerUtil.close();

		return cadmov1;

	}

}
