package repositorios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Cadosc;
import entidades.Cadosi;
import entidades.Usuario;
import filter.EntityManagerUtil;

public class CadosiRepository {

	Date data = new Date();

	public CadosiRepository(EntityManager manager) {

	}

	public void gravan(Cadosi cadosi) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		if (cadosi.getId() == null) {
			EntityManagerUtil.manager.persist(cadosi);
			EntityManagerUtil.commit();
		} else {
			EntityManagerUtil.manager.merge(cadosi);
			EntityManagerUtil.commit();

		}
		EntityManagerUtil.close();
	}

	public void acertaitem(List<Cadosi> pdsais, Usuario u) {
		for (Cadosi p : pdsais) {
			if (p.getId() != null) {
				EntityManagerUtil.manager.merge(p);
			}
		}
	}

	public void removeatualiza(Long id, Usuario u) {
		EntityManagerUtil.conexao();
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
		Cadosi cadosi1 = new Cadosi();

		cadosi1 = procura(id);
		cadosi1.setSql_deleted("T");
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(cadosi1);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		repositorylog.adicionaLog("EXCLUSAO", "ITEM ORDEM SERVIÇO " + cadosi1.getNumdoc() + " CLIENTE "
				+ cadosi1.getOrdemc().getCliente().getCODCLI() + " " + cadosi1.getOrdemc().getCliente().getDESCCLI(),
				cadosi1.getId(), u);
	}

	public void atualiza(Cadosi cadosi) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(cadosi);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public Cadosi procura(Long id) {
		Cadosi cadosi = new Cadosi();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		cadosi = EntityManagerUtil.manager.find(Cadosi.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadosi;
	}

	public Cadosc procuracabecario(Long id) {
		Cadosc cadosc = new Cadosc();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		cadosc = EntityManagerUtil.manager.find(Cadosc.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		return cadosc;
	}

	public void removeitens(Long idpedido, Usuario u) {

		EntityManagerUtil.conexao();
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
		List<Cadosi> cadosilista = new ArrayList<Cadosi>();

		cadosilista = getLista(idpedido);

		for (Cadosi cadosi1 : cadosilista) {
			cadosi1.setSql_deleted("T");
			gravan(cadosi1);

			repositorylog.adicionaLog("EXCLUSAO",
					"ITEM ORDEM SERVIÇO " + cadosi1.getNumdoc() + " CLIENTE "
							+ cadosi1.getOrdemc().getCliente().getCODCLI() + " "
							+ cadosi1.getOrdemc().getCliente().getDESCCLI(),
					cadosi1.getId(), u);

		}
	}

	public List<Cadosi> getLista(Long idordem) {

		List<Cadosi> cadosilista = new ArrayList<Cadosi>();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createQuery("select x from Cadosi x where x.ordemc = '" + idordem + "' and x.sql_deleted = 'F'");
		if (!query.getResultList().isEmpty()) {
			cadosilista = query.getResultList();
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadosilista;
	}

	public List<Cadosi> relatordemservico(Date datai, Date dataf) {

		List<Cadosi> cadosi1 = new ArrayList<Cadosi>();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery(
				"select x from Cadosi x where x.emissao >= :datainicial and x.emissao <= :datafinal and x.sql_deleted = 'F' ORDER BY x.numdoc,x.emissao DESC");
		query.setParameter("datainicial", datai);
		query.setParameter("datafinal", dataf);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		if (!query.getResultList().isEmpty()) {
			cadosi1 = query.getResultList();
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadosi1;
	}

}
