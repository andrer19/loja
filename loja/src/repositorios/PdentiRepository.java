package repositorios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.hibernate.Session;

import beans.AcessoBean;
import entidades.Cadmov;
import entidades.Cadpro;
import entidades.Pdentc;
import entidades.Pdenti;
import entidades.Pdsaii;
import entidades.Usuario;
import filter.EntityManagerUtil;

public class PdentiRepository {

	Date data = new Date();
	
	AcessoBean aces1 = new AcessoBean();

	public PdentiRepository(EntityManager manager) {
	}

	public void grava(List<Pdenti> pdentis, Usuario u) {

		Cadpro cadpro = new Cadpro();
		EntityManagerUtil.conexao();
		CadmovRepository repomov = new CadmovRepository(EntityManagerUtil.manager);
		CadproRepository repoprod = new CadproRepository(EntityManagerUtil.manager);

		for (Pdenti p : pdentis) {
			if (p.getIdpdenti() == null) {
				EntityManagerUtil.conexao();
				EntityManagerUtil.begin();
				EntityManagerUtil.manager.persist(p);
				EntityManagerUtil.commit();
				EntityManagerUtil.close();

				LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
				repositorylog.adicionaLog("INCLUSAO",
						"ITEM DO PEDIDO " + p.getPedido() + " PRODUTO " + p.getProduto() + " QTDE " + p.getQuantidade(),
						p.getIdpdenti(), u);

				cadpro = new Cadpro();

				cadpro = repoprod.buscaproduto(p.getProduto().trim());

				
				double qtde1 = cadpro.getQTATUAL();
				double qtde2 = aces1.retornadouble(aces1.removeponto(cadpro.getMATERIAL()));
				qtde1 = qtde1 + p.getQuantidade();
				Double qtdecomprada = aces1.retornadouble(aces1.removeponto(cadpro.getMATERIAL())) + p.getQuantidade();

				cadpro.setQTATUAL(qtde1);
				cadpro.setQTINICIAL(p.getQuantidade());
				repoprod.grava(cadpro);

				Cadmov mov = new Cadmov();
				mov.setCodpro(p.getProduto().trim());
				mov.setDescpro(p.getDescpro().trim());
				mov.setUn(p.getUn());
				mov.setNumdoc(p.getPedido());
				mov.setDtmov(data);
				mov.setQtmov(p.getQuantidade());
				mov.setTpmov("E");
				mov.setVrunit(p.getUnitario());
				mov.setVrtot(p.getVrtot());
				mov.setQtant(qtde2);
				mov.setQtatu(qtde1);
				mov.setDescricao(p.getPedc().getForn().getDESCFOR().trim());
				mov.setHist("INCLUSAO DO PEDIDO COMPRA");
				repomov.grava(mov);
				qtde1 = 0;
				qtdecomprada = 0.0;

			} else {
				EntityManagerUtil.conexao();
				EntityManagerUtil.begin();
				EntityManagerUtil.manager.merge(p);
				EntityManagerUtil.commit();
				EntityManagerUtil.close();
			}
		}
	}

	public void acertaitem(List<Pdenti> pdentis, Usuario u) {

		for (Pdenti p : pdentis) {
			if (p.getIdpdenti() != null) {
				EntityManagerUtil.conexao();
				EntityManagerUtil.begin();
				EntityManagerUtil.manager.merge(p);
				EntityManagerUtil.commit();
				EntityManagerUtil.close();
			}
		}
	}

	public void remove(Long id) {
		Pdenti pdenti1 = new Pdenti();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.remove(pdenti1);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public void removeatualiza(Long id, Usuario u) {
		Pdenti pdenti1 = new Pdenti();

		pdenti1 = procura(id);
		pdenti1.setSql_deleted("T");
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(pdenti1);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
		repositorylog.adicionaLog("EXCLUSAO", "ITEM DO PEDIDO " + pdenti1.getPedido() + " PRODUTO "
				+ pdenti1.getProduto() + " QTDE " + pdenti1.getQuantidade(), pdenti1.getIdpdenti(), u);
		retornasaldo(pdenti1.getProduto(), pdenti1.getQuantidade(), pdenti1);

	}

	public void retornasaldo(String produto, Double qtde, Pdenti p) {
		Cadpro cadpro = new Cadpro();
		EntityManagerUtil.conexao();
		CadmovRepository repomov = new CadmovRepository(EntityManagerUtil.manager);
		CadproRepository repoprod = new CadproRepository(EntityManagerUtil.manager);

		cadpro = repoprod.buscaproduto(produto.trim());

		double qtde1 = cadpro.getQTATUAL() - p.getQuantidade();

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
		mov.setHist("EXCLUSAO ITEM DO PEDIDO COMPRA");
		repomov.grava(mov);

	}

	public void atualiza(Pdenti pdenti) {

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(pdenti);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public Pdenti procura(Long id) {
		Pdenti pdenti = new Pdenti();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		pdenti = EntityManagerUtil.manager.find(Pdenti.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		return pdenti;
	}

	public Pdentc procuracabecario(Long id) {
		Pdentc pdentc = new Pdentc();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		pdentc = EntityManagerUtil.manager.find(Pdentc.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return pdentc;
	}

	public void removeitens(Long idpedido, Usuario u) {

		EntityManagerUtil.conexao();
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
		List<Pdenti> pdentilista = new ArrayList<Pdenti>();
		pdentilista = getLista(idpedido);

		for (Pdenti pdenti1 : pdentilista) {
			pdenti1.setSql_deleted("T");
			atualiza(pdenti1);

			repositorylog.adicionaLog("Exclusao", "ITEM DO PEDIDO " + pdenti1.getPedido() + " PRODUTO "
					+ pdenti1.getProduto() + " QTDE " + pdenti1.getQuantidade(), pdenti1.getIdpdenti(), u);

		}

	}

	public List<Pdenti> getLista(Long idpedido) {
		List<Pdenti> pdenti1 = new ArrayList<Pdenti>();
		Pdenti pdentilista = new Pdenti();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createQuery("select x from Pdenti x where x.pedc = " + idpedido + " and x.sql_deleted = 'F'");
		if (!query.getResultList().isEmpty()) {
			pdenti1 = query.getResultList();
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return pdenti1;
	}

	public void voltaquantidadeitem(Long id) {
		Pdenti p = new Pdenti();
		Cadpro cadpro = new Cadpro();
		EntityManagerUtil.conexao();
		CadmovRepository repomov = new CadmovRepository(EntityManagerUtil.manager);
		CadproRepository repoprod = new CadproRepository(EntityManagerUtil.manager);

		p = procura(id);

		cadpro = repoprod.buscaproduto(p.getProduto().trim());

		double qtde1 = cadpro.getQTATUAL();

		Double qtdevendida = Double.parseDouble(cadpro.getMATERIAL()) - cadpro.getQTATUAL();

		cadpro.setQTATUAL(qtde1);
		cadpro.setMATERIAL(qtdevendida.toString());
		repoprod.grava(cadpro);

		Cadmov mov = new Cadmov();
		mov.setCodpro(p.getProduto());
		mov.setDescpro(p.getDescpro());
		mov.setUn(p.getUn());
		mov.setNumdoc(p.getPedido());
		mov.setDtmov(data);
		mov.setQtmov(p.getQuantidade());
		mov.setTpmov("E");
		mov.setVrunit(p.getUnitario());
		mov.setVrtot(p.getVrtot());
		mov.setQtant(qtde1);
		mov.setQtatu(qtdevendida);
		mov.setDescricao(p.getPedc().getForn().getDESCFOR());
		mov.setHist("EXCLUSAO ITEM DO PEDIDO COMPRA");
		repomov.grava(mov);

	}

	public List<Pdenti> ultent(String produto) {

		List<Pdenti> pdenti1 = new ArrayList<Pdenti>();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();

		Query query = EntityManagerUtil.manager.createQuery(
				"select x from Pdenti x where produto = :produto and sql_deleted = 'F' ORDER BY pedido DESC");
		query.setParameter("produto", produto);
		pdenti1 = query.getResultList();
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return pdenti1;
	}

	public Cadpro Validapro(String prod) {
		Cadpro cadpro1 = new Cadpro();
		EntityManagerUtil.conexao();
		CadproRepository repoprod = new CadproRepository(EntityManagerUtil.manager);

		cadpro1 = repoprod.buscaproduto(prod.trim());
		return cadpro1;

	}

	public void tiraquantidade(String produto, Double qtde) {
		Cadpro cadpro1 = new Cadpro();
		EntityManagerUtil.conexao();
		CadproRepository repoprod = new CadproRepository(EntityManagerUtil.manager);

		cadpro1 = repoprod.buscaproduto(produto.trim());

		double qtde1 = cadpro1.getQTATUAL();
		qtde1 = qtde1 - qtde;
		Double qtdevendida = Double.parseDouble(cadpro1.getMATERIAL()) + qtde;

		cadpro1.setQTATUAL(qtde1);
		cadpro1.setMATERIAL(qtdevendida.toString());

		repoprod.grava(cadpro1);
	}

	public List<Pdenti> relatcompra(Date datai, Date dataf) {

		List<Pdenti> pdenti1 = new ArrayList<Pdenti>();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery(
				"select x from Pdenti x where x.emissao >= :datainicial and x.emissao <= :datafinal and x.sql_deleted = 'F' ORDER BY pedido,emissao DESC");
		query.setParameter("datainicial", datai);
		query.setParameter("datafinal", dataf);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		pdenti1 = query.getResultList();
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return pdenti1;
	}

}
