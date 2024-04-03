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

import entidades.Cadmov;
import entidades.Cadpro;
import entidades.Pdenti;
import entidades.Pdsaic;
import entidades.Pdsaii;
import entidades.Usuario;
import filter.EntityManagerUtil;

public class PdsaiiRepository {

	Date data = new Date();

	public PdsaiiRepository(EntityManager manager) {

	}

	public void gravan(Pdsaii pdsaii) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		if (pdsaii.getIdpdsaii() == null) {
			EntityManagerUtil.manager.persist(pdsaii);
			EntityManagerUtil.commit();
		} else {
			EntityManagerUtil.manager.merge(pdsaii);
			EntityManagerUtil.commit();

		}
		EntityManagerUtil.close();
	}

	public void acertaitem(List<Pdsaii> pdsais, Usuario u) {
		for (Pdsaii p : pdsais) {
			if (p.getIdpdsaii() != null) {
				EntityManagerUtil.manager.merge(p);
			}
		}
	}

	public void removeatualiza(Long id, Usuario u) {
		EntityManagerUtil.conexao();
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
		Pdsaii pdsaii1 = new Pdsaii();

		pdsaii1 = procura(id);
		pdsaii1.setSql_deleted("T");
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(pdsaii1);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		repositorylog.adicionaLog("EXCLUSAO", "ITEM PEDIDO VENDA " + pdsaii1.getPedido() + " CLIENTE "
				+ pdsaii1.getPedc().getCliente().getCODCLI() + " " + pdsaii1.getPedc().getCliente().getDESCCLI(),
				pdsaii1.getIdpdsaii(), u);
		retornasaldo(pdsaii1.getProduto(), pdsaii1.getQuantidade(), pdsaii1);
	}

	public void retornasaldo(String produto, Double qtde, Pdsaii pdsaii1) {
		EntityManagerUtil.conexao();
		CadproRepository repoprod = new CadproRepository(EntityManagerUtil.manager);
		CadmovRepository repomov = new CadmovRepository(EntityManagerUtil.manager);
		Cadpro cadprop = new Cadpro();

		cadprop = repoprod.buscaproduto(produto.trim());

		double qtde1 = cadprop.getQTATUAL() + qtde;

		double qtde2 = cadprop.getQTATUAL();

		Double qtdevendida = Double.parseDouble(cadprop.getMATERIAL()) - qtde;

		cadprop.setQTATUAL(qtde1);
		cadprop.setMATERIAL(qtdevendida.toString());

		repoprod.grava(cadprop);

		Cadmov mov = new Cadmov();
		mov.setCodpro(pdsaii1.getProduto());
		mov.setDescpro(pdsaii1.getDescpro());
		mov.setUn(pdsaii1.getUn());
		mov.setNumdoc(pdsaii1.getPedido());
		mov.setDtmov(data);
		mov.setQtmov(pdsaii1.getQuantidade());
		mov.setTpmov("E");
		mov.setVrunit(pdsaii1.getUnitario());
		mov.setVrtot(pdsaii1.getVrtot());
		mov.setQtant(qtde2);
		mov.setQtatu(qtde1);
		mov.setDescricao(pdsaii1.getPedc().getCliente().getDESCCLI());
		mov.setHist("EXCLUSAO ITEM PEDIDO VENDA");
		repomov.grava(mov);

	}

	public void atualiza(Pdsaii pdsaii) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(pdsaii);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public Pdsaii procura(Long id) {
		Pdsaii pdsaii = new Pdsaii();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		pdsaii = EntityManagerUtil.manager.find(Pdsaii.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return pdsaii;
	}

	public Pdsaic procuracabecario(Long id) {
		Pdsaic pdsaic = new Pdsaic();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		pdsaic = EntityManagerUtil.manager.find(Pdsaic.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		return pdsaic;
	}

	public void removeitens(Long idpedido, Usuario u) {

		EntityManagerUtil.conexao();
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
		List<Pdsaii> pdsaiilista = new ArrayList<Pdsaii>();

		pdsaiilista = getLista(idpedido);

		for (Pdsaii pdsaii1 : pdsaiilista) {
			pdsaii1.setSql_deleted("T");
			gravan(pdsaii1);

			repositorylog.adicionaLog("EXCLUSAO", "ITEM PEDIDO VENDA " + pdsaii1.getPedido() + " CLIENTE "
					+ pdsaii1.getPedc().getCliente().getCODCLI() + " " + pdsaii1.getPedc().getCliente().getDESCCLI(),
					pdsaii1.getIdpdsaii(), u);

			retornasaldo(pdsaii1.getProduto(), pdsaii1.getQuantidade(), pdsaii1);

		}
	}

	public List<Pdsaii> getLista(Long idpedido) {

		List<Pdsaii> pdsaiilista = new ArrayList<Pdsaii>();
		
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createQuery("select x from Pdsaii x where x.pedc = '" + idpedido + "' and x.sql_deleted = 'F'");
		if (!query.getResultList().isEmpty()) {
			pdsaiilista = query.getResultList();
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return pdsaiilista;
	}

	public void voltaquantidadeitem(Long id) {

		EntityManagerUtil.conexao();
		CadproRepository repoprod = new CadproRepository(EntityManagerUtil.manager);
		Pdsaii pdsaii1 = new Pdsaii();
		Cadpro cadpro = new Cadpro();
		pdsaii1 = procura(id);
		cadpro = repoprod.buscaproduto(pdsaii1.getProduto().trim());

		double qtde1 = cadpro.getQTATUAL();

		Double qtdevendida = Double.parseDouble(cadpro.getMATERIAL()) - cadpro.getQTATUAL();

		cadpro.setQTATUAL(qtde1);
		cadpro.setMATERIAL(qtdevendida.toString());

		repoprod.grava(cadpro);

	}

	public List<Pdsaii> ultsai(String produto) {

		List<Pdsaii> pdenti1 = new ArrayList<Pdsaii>();
		Pdsaii pdentilista = new Pdsaii();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery(
				"select x from Pdsaii x where produto = :produto and sql_deleted = 'F' ORDER BY pedido DESC");
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
		cadpro1 = repoprod.buscaproduto(prod);

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

	public List<Pdsaii> relatvenda(Date datai, Date dataf) {

		List<Pdsaii> pdenti1 = new ArrayList<Pdsaii>();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery(
				"select x from Pdsaii x where x.emissao >= :datainicial and x.emissao <= :datafinal and x.sql_deleted = 'F' ORDER BY pedido,emissao DESC");
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
