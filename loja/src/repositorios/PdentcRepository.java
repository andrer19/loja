package repositorios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Cadmov;
import entidades.Cadpro;
import entidades.Pdentc;
import entidades.Pdenti;
import entidades.Usuario;
import filter.EntityManagerUtil;

public class PdentcRepository {

	Date data = new Date();

	public PdentcRepository(EntityManager manager) {

	}

	public void adiciona(Pdentc pdentc, Usuario u) {

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.persist(pdentc);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		if (pdentc.getNumdoc().equals(null) || pdentc.getNumdoc().isEmpty()) {
			pdentc.setNumdoc(String.format("%06d", Integer.parseInt(pdentc.getIdpdentc().toString())));
			EntityManagerUtil.conexao();
			EntityManagerUtil.begin();
			EntityManagerUtil.manager.merge(pdentc);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();
			LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
			repositorylog.adicionaLog("INCLUSAO", "PEDIDO COMPRA " + pdentc.getNumdoc() + " FORNECEDOR "
					+ pdentc.getForn().getCODFOR() + " " + pdentc.getForn().getDESCFOR(), pdentc.getIdpdentc(), u);
		}

	}

	public void remove(Long id) {

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Pdentc pdentc = procura(id);
		EntityManagerUtil.manager.remove(pdentc);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public void removeatualiza(Long id, Usuario u) {

		Pdentc pdentc1 = new Pdentc();
		List<Pdenti> pdenti1 = new ArrayList<Pdenti>();
		EntityManagerUtil.conexao();
		PdentiRepository iterepo = new PdentiRepository(EntityManagerUtil.manager);
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

		pdentc1 = procura(id);
		pdentc1.setSql_deleted("T");
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(pdentc1);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		pdenti1 = iterepo.getLista(pdentc1.getIdpdentc());

		for (Pdenti pdenti : pdenti1) {

			EntityManagerUtil.conexao();
			EntityManagerUtil.begin();
			pdenti.setSql_deleted("T");
			EntityManagerUtil.manager.merge(pdenti);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();

			repositorylog.adicionaLog("EXCLUSAO", "PEDIDO COMPRA " + pdenti.getPedido() + " PRODUTO "
					+ pdenti.getProduto() + " QTDE " + pdenti.getQuantidade(), pdenti.getIdpdenti(), u);
			retornasaldo(pdenti.getProduto(), pdenti.getQuantidade(), pdenti);

		}
	}

	public void retornasaldo(String produto, Double qtde, Pdenti p) {
		Cadpro cadpro = new Cadpro();
		EntityManagerUtil.conexao();
		CadmovRepository repomov = new CadmovRepository(EntityManagerUtil.manager);
		CadproRepository repoprod = new CadproRepository(EntityManagerUtil.manager);

		cadpro = repoprod.buscaproduto(produto.trim());

		if (cadpro.getIdcadpro() != null) {
			double qtde1 = cadpro.getQTATUAL() - qtde;

			cadpro.setQTATUAL(qtde1);

			repoprod.grava(cadpro);

			Cadmov mov = new Cadmov();
			mov.setCodpro(p.getProduto());
			mov.setDescpro(p.getDescpro());
			mov.setUn(p.getUn());
			mov.setNumdoc(p.getPedido());
			mov.setDtmov(data);
			mov.setQtmov(p.getQuantidade());
			mov.setTpmov("S");
			mov.setVrunit(p.getUnitario());
			mov.setVrtot(p.getVrtot());
			mov.setQtant(cadpro.getQTATUAL());
			mov.setQtatu(qtde1);
			mov.setDescricao(p.getPedc().getForn().getDESCFOR());
			mov.setHist("EXCLUSÂO PEDIDO COMPRA");
			repomov.grava(mov);
		}

	}

	public void atualiza(Pdentc pdentc, Usuario u) {

		EntityManagerUtil.conexao();
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(pdentc);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		repositorylog.adicionaLog("ALTERACAO", "PEDIDO COMPRA " + pdentc.getNumdoc() + " FORNECEDOR "
				+ pdentc.getForn().getCODFOR() + " " + pdentc.getForn().getDESCFOR(), pdentc.getIdpdentc(), u);

	}

	public Pdentc procura(Long id) {
		Pdentc pdentc = new Pdentc();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		pdentc = EntityManagerUtil.manager.find(Pdentc.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return pdentc;
	}

	public List<Pdentc> getLista() {
		List<Pdentc> pdentcs = new ArrayList<Pdentc>();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("select x from Pdentc x where x.sql_deleted = 'F'");

		pdentcs = query.getResultList();

		Collections.sort(pdentcs, new Comparator<Pdentc>() {
			public int compare(Pdentc o1, Pdentc o2) {
				return o1.getNumdoc().compareTo(o2.getNumdoc());
			}
		});

		Collections.reverse(pdentcs);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return pdentcs;
	}

}
