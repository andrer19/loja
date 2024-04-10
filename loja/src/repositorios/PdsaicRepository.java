package repositorios;

import java.awt.HeadlessException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Cadmov;
import entidades.Cadpro;
import entidades.Pdsaic;
import entidades.Pdsaii;
import entidades.Usuario;
import filter.EntityManagerUtil;

public class PdsaicRepository {

	public PdsaicRepository(EntityManager manager) {

	}
	
	AcessoRepository repoacesso = new AcessoRepository(EntityManagerUtil.manager);

	public void adiciona(Pdsaic pdsaic, Usuario u) {
		EntityManagerUtil.conexao();
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.persist(pdsaic);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		if (pdsaic.getNumdoc().equals(null) || pdsaic.getNumdoc().isEmpty()) {
			pdsaic.setNumdoc(String.format("%06d", Integer.parseInt(pdsaic.getIdpdsaic().toString())));
			EntityManagerUtil.conexao();
			EntityManagerUtil.begin();
			EntityManagerUtil.manager.merge(pdsaic);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();

			repositorylog
					.adicionaLog(
							"INCLUSAO", "PEDIDO VENDA " + pdsaic.getNumdoc() + " CLIENTE "
									+ pdsaic.getCliente().getCODCLI() + " " + pdsaic.getCliente().getDESCCLI(),
							pdsaic.getIdpdsaic(), u);
		}
	}

	public void remove(Long id) {

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Pdsaic pdsaic = procura(id);
		EntityManagerUtil.manager.remove(pdsaic);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public Pdsaic procurapedido(String pedido) {

		Pdsaic pdsaic1 = new Pdsaic();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createQuery("SELECT x FROM Pdsaic x WHERE x.numdoc ='" + pedido + "' and x.sql_deleted = 'F'");
		if (!query.getResultList().isEmpty()) {
			pdsaic1 = (Pdsaic) query.getSingleResult();
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		return pdsaic1;
	}

	public void removeatualiza(Long id, Usuario u) {

		Pdsaic pdsaic1 = new Pdsaic();
		EntityManagerUtil.conexao();
		List<Pdsaii> pdsaiilista = new ArrayList<Pdsaii>();
		PdsaiiRepository repoitem = new PdsaiiRepository(EntityManagerUtil.manager);
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

		pdsaic1 = procura(id);
		pdsaic1.setSql_deleted("T");
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(pdsaic1);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		pdsaiilista = repoitem.getLista(pdsaic1.getIdpdsaic());

		for (Pdsaii pdsaii1 : pdsaiilista) {
			EntityManagerUtil.conexao();
			EntityManagerUtil.begin();
			pdsaii1.setSql_deleted("T");
			EntityManagerUtil.manager.merge(pdsaii1);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();

			repositorylog
					.adicionaLog(
							"EXCLUSAO", "PEDIDO VENDA " + pdsaic1.getNumdoc() + " CLIENTE "
									+ pdsaic1.getCliente().getCODCLI() + " " + pdsaic1.getCliente().getDESCCLI(),
							pdsaic1.getIdpdsaic(), u);
			retornasaldo(pdsaii1.getProduto(), pdsaii1.getQuantidade(), pdsaii1);
		}

	}

	public void retornasaldo(String produto, Double qtde, Pdsaii pdsaii1) {

		Date data = new Date();
		Cadpro prod = new Cadpro();
		EntityManagerUtil.conexao();
		CadproRepository repoprod = new CadproRepository(EntityManagerUtil.manager);
		CadmovRepository repomov = new CadmovRepository(EntityManagerUtil.manager);

		prod = repoprod.buscaproduto(produto.trim());

		if (prod.getIdcadpro() != null) {
			double qtde1 = prod.getQTATUAL() + qtde;

			double qtde2 = prod.getQTATUAL();

			Double qtdevendida = Double.parseDouble(prod.getMATERIAL()) - qtde;

			prod.setQTATUAL(qtde1);
			prod.setMATERIAL(qtdevendida.toString());
			repoprod.atualiza(prod);

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
			mov.setHist("EXCLUSAO DO PEDIDO VENDA");
			repomov.grava(mov);
		}
	}

	public void atualiza(Pdsaic pdsaic, Usuario u) {
		EntityManagerUtil.conexao();
		LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(pdsaic);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		repositorylog.adicionaLog("ALTERACAO", "PEDIDO VENDA " + pdsaic.getNumdoc() + " CLIENTE "
				+ pdsaic.getCliente().getCODCLI() + " " + pdsaic.getCliente().getDESCCLI(), pdsaic.getIdpdsaic(), u);

	}

	public Pdsaic procura(Long id) {
		Pdsaic pdsaic = new Pdsaic();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		pdsaic = EntityManagerUtil.manager.find(Pdsaic.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return pdsaic;
	}

	public List<Pdsaic> getLista() {
		List<Pdsaic> pdsaics = new ArrayList<Pdsaic>();
		SimpleDateFormat formato = new SimpleDateFormat();
		Locale local1 = new Locale("pt", "BR");
		DateFormat datestring = new SimpleDateFormat("yyyy-MM-dd", local1);
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(repoacesso.dataatual());
		c.add(Calendar.DAY_OF_MONTH, -120);

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery(
		"select x from Pdsaic x where x.emissao >= '" + datestring.format(c.getTime()) + "' and x.sql_deleted = 'F' order by x.numdoc desc");
		pdsaics = query.getResultList();

		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return pdsaics;
	}
	
	public List<Pdsaic> Listapedidoantigos(Long idcliente) {
		List<Pdsaic> pdsaics = new ArrayList<Pdsaic>();
		SimpleDateFormat formato = new SimpleDateFormat();
		Locale local1 = new Locale("pt", "BR");
		DateFormat datestring = new SimpleDateFormat("yyyy-MM-dd", local1);
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(repoacesso.dataatual());
		c.add(Calendar.DAY_OF_MONTH, -120);

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery(
				"select x from Pdsaic x where x.emissao < '" + datestring.format(c.getTime()) + "' and x.cliente = " + idcliente + " and x.sql_deleted = 'F' order by x.numdoc desc");
		pdsaics = query.getResultList();

		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return pdsaics;
	}

}
