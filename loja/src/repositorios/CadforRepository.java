package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Cadcli;
import entidades.Cademp;
import entidades.Cadfor;
import entidades.Cadpro;
import filter.EntityManagerUtil;

public class CadforRepository {

	public CadforRepository(EntityManager manager) {

	}

	public void grava(Cadfor cadfor) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		if (cadfor.getIDFORNECEDOR() == null) {
			EntityManagerUtil.manager.persist(cadfor);
		} else {
			EntityManagerUtil.manager.merge(cadfor);
		}
		
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Cadfor cadfor = procura(id);
		EntityManagerUtil.manager.remove(cadfor);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void removeatualiza(Long id) {
		
		Cadfor cadfor = procura(id);
		cadfor.setSql_deleted("T");
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(cadfor);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public Cadfor procura(Long id) {
		Cadfor cadfor = new Cadfor();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		cadfor = EntityManagerUtil.manager.find(Cadfor.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		return cadfor;
	}

	public Cadfor buscacodfor(String prod) {
		Cadfor cadfor1 = new Cadfor();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createQuery("SELECT x from Cadfor x where x.CODFOR ='" + prod + "' and x.sql_deleted = 'F'");
		if (!query.getResultList().isEmpty()) {
			cadfor1 = (Cadfor) query.getSingleResult();
		}

		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadfor1;

	}

	@SuppressWarnings("unchecked")
	public List<Cadfor> getLista() {
		List<Cadfor> cadfor1 = new ArrayList<Cadfor>();
		Cadfor cadforlista = new Cadfor();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createNativeQuery(
				"SELECT IDFORNECEDOR,CODFOR,RAZAO, CGC,IE,DESCFOR,NIVEL FROM cadfor WHERE sql_deleted = 'F'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			cadforlista = new Cadfor();
			cadforlista.setIDFORNECEDOR(Long.parseLong(row[0].toString()));
			cadforlista.setCODFOR((String) row[1]);
			cadforlista.setRAZAO((String) row[2]);
			cadforlista.setCGC((String) row[3]);
			cadforlista.setIE((String) row[4]);
			cadforlista.setDESCFOR((String) row[5]);
			cadforlista.setNIVEL((Double) row[6]);
			cadfor1.add(cadforlista);
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadfor1;
	}
	
	public Boolean validacnpj(String cnpj,String codigo) {
		Cadfor cadforlista = new Cadfor();
		Boolean valida = false;

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createQuery("SELECT x FROM Cadfor x WHERE x.CGC ='" + cnpj + "' and CODFOR <> '" + codigo +"' and x.sql_deleted = 'F'");
		if (!query.getResultList().isEmpty()) {
			cadforlista = (Cadfor) query.getSingleResult();
		}

		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		
		if(cadforlista.getIDFORNECEDOR() != null) {
			valida = true;
		}

		return valida;
	}

}
