package repositorios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Cadosc;
import entidades.Cadosi;
import entidades.Usuario;
import filter.EntityManagerUtil;

public class CadoscRepository {
	
	AcessoRepository repoacesso = new AcessoRepository(EntityManagerUtil.manager);

	public CadoscRepository(EntityManager manager) {

	}

	public void adiciona(Cadosc cadosc, Usuario u) {
		EntityManagerUtil.conexao();
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.persist(cadosc);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		
		if (cadosc.getNumdoc().equals(null) || cadosc.getNumdoc().isEmpty()) {
			cadosc.setNumdoc(String.format("%06d", Integer.parseInt(cadosc.getId().toString())));
			EntityManagerUtil.conexao();
			EntityManagerUtil.begin();
			EntityManagerUtil.manager.merge(cadosc);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();

			repositorylog
					.adicionaLog(
							"INCLUSAO", "ORDEM DE SERVIÇO " + cadosc.getNumdoc() + " CLIENTE "
									+ cadosc.getCliente().getCODCLI().trim() + " " + cadosc.getCliente().getDESCCLI().trim(),
									cadosc.getId(), u);
		}
	}

	public void atualiza(Cadosc cadosc, Usuario u) {
		EntityManagerUtil.conexao();
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(cadosc);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		repositorylog.adicionaLog("ALTERACAO", "ORDEM DE SERVIÇO " + cadosc.getNumdoc() + " CLIENTE "
				+ cadosc.getCliente().getCODCLI() + " " + cadosc.getCliente().getDESCCLI(), cadosc.getId(), u);

	}
	
	public Cadosc procura(Long id) {
		Cadosc cadosc = new Cadosc();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		cadosc = EntityManagerUtil.manager.find(Cadosc.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadosc;
	}
	
	public void removeatualiza(Long id, Usuario u) {

		Cadosc cadosc1 = new Cadosc();
		EntityManagerUtil.conexao();
		List<Cadosi> cadoslista = new ArrayList<Cadosi>();
		CadosiRepository repoitem = new CadosiRepository(EntityManagerUtil.manager);
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

		cadosc1 = procura(id);
		cadosc1.setSql_deleted("T");
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(cadosc1);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		cadoslista = repoitem.getLista(cadosc1.getId());

		for (Cadosi cadosi1 : cadoslista) {
			EntityManagerUtil.conexao();
			EntityManagerUtil.begin();
			cadosi1.setSql_deleted("T");
			EntityManagerUtil.manager.merge(cadosi1);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();
		}
		
		repositorylog
		.adicionaLog(
				"EXCLUSAO", "ORDEM DE SERVIÇO " + cadosc1.getNumdoc() + " CLIENTE "
						+ cadosc1.getCliente().getCODCLI() + " " + cadosc1.getCliente().getDESCCLI(),
						cadosc1.getId(), u);

	}


	@SuppressWarnings("unchecked")
	public List<Cadosc> getLista() {
		List<Cadosc> cadoscs = new ArrayList<Cadosc>();
		Cadosc ordem = new Cadosc();
		SimpleDateFormat formato = new SimpleDateFormat();
		Locale local1 = new Locale("pt", "BR");
		DateFormat datestring = new SimpleDateFormat("yyyy-MM-dd", local1);
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(repoacesso.dataatual());
		c.add(Calendar.DAY_OF_MONTH, -120);

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createNativeQuery(
		"select p.id,p.numdoc,c.codcli,c.desccli,p.emissao,p.formpagto,p.vrtot "
		+ " from Cadosc p "
		+ " INNER JOIN cadcli c ON p.cliente = c.idcadcli "
		+ " where p.emissao >= '" + datestring.format(c.getTime()) + "' and p.sql_deleted = 'F' order by p.numdoc desc;");
		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			ordem = new Cadosc();
			ordem.setId(Long.parseLong(row[0].toString()));
			ordem.setNumdoc((String) row[1]);
			ordem.setCodclifor((String) row[2]);
			ordem.setDescclifor((String) row[3]);
			ordem.setEmissao((Date) row[4]);
			ordem.setFormpagto((String) row[5]);
			ordem.setVrtot((Double) row[6]);
			cadoscs.add(ordem);

		}

		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadoscs;

	}


}
