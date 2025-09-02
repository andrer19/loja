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

import beans.AcessoBean;
import entidades.Cadcli;
import entidades.Cadfor;
import entidades.Cadmov;
import entidades.Cadpro;
import entidades.Pdenti;
import entidades.Pdsaii;
import filter.EntityManagerUtil;

public class CadproRepository {

	Date data = new Date();
	AcessoBean aces1 = new AcessoBean();

	public CadproRepository(EntityManager manager) {

	}

	public void grava(Cadpro cadpro) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		if (cadpro.getIdcadpro() == null) {
			EntityManagerUtil.manager.persist(cadpro);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();
		} else {
			EntityManagerUtil.manager.merge(cadpro);
			EntityManagerUtil.commit();
			EntityManagerUtil.close();
		}
	}

	public void removeid(Long id) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Cadpro cadpro = procura(id);
		EntityManagerUtil.manager.remove(cadpro);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void removeatualiza(Long id) {
		
		Cadpro cadpro = procura(id);
		cadpro.setSql_deleted("T");
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(cadpro);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void atualiza(Cadpro cadpro) {

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(cadpro);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public Cadpro procura(Long id) {
		Cadpro cadpro = new Cadpro();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		cadpro = EntityManagerUtil.manager.find(Cadpro.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		return cadpro;
	}

	public void qtdevendida(Pdsaii pdsaii) {

		EntityManagerUtil.conexao();
		CadmovRepository repomov = new CadmovRepository(EntityManagerUtil.manager);

		Cadpro cadpro1 = new Cadpro();
		cadpro1 = buscaproduto(pdsaii.getProduto().trim());
		double qtde1 = cadpro1.getQTATUAL();
		qtde1 = qtde1 - pdsaii.getQuantidade();
		double qtde2 = cadpro1.getQTATUAL();
		Double qtdevendida = aces1.retornadouble(aces1.removeponto(cadpro1.getMATERIAL())) + pdsaii.getQuantidade();

		cadpro1.setQTATUAL(qtde1);
		cadpro1.setMATERIAL(qtdevendida.toString());

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(cadpro1);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		Cadmov mov = new Cadmov();
		mov.setCodpro(pdsaii.getProduto());
		mov.setDescpro(pdsaii.getDescpro());
		mov.setUn(pdsaii.getUn());
		mov.setNumdoc(pdsaii.getPedido());
		mov.setDtmov(data);
		mov.setQtmov(pdsaii.getQuantidade());
		mov.setTpmov("S");
		mov.setVrunit(pdsaii.getUnitario());
		mov.setVrtot(pdsaii.getVrtot());
		mov.setQtant(qtde2);
		mov.setQtatu(qtde1);
		mov.setDescricao(pdsaii.getPedc().getCliente().getDESCCLI());
		mov.setHist("INCLUSAO ITEM PEDIDO VENDA");
		repomov.grava(mov);
	}

	public List<Cadpro> getLista() {
		List<Cadpro> cadpro1 = new ArrayList<Cadpro>();
		Cadpro cadprolista = new Cadpro();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createNativeQuery("SELECT idcadpro,codpro,descpro,un, vrcompra,economico,"
						+ "vrvenda,qtatual,ativo FROM cadpro WHERE sql_deleted = 'F'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			cadprolista = new Cadpro();
			cadprolista.setIdcadpro(Long.parseLong(row[0].toString()));
			cadprolista.setCODPRO((String) row[1]);
			cadprolista.setDESCPRO((String) row[2]);
			cadprolista.setUN((String) row[3]);
			cadprolista.setVRCOMPRA((Double) row[4]);
			cadprolista.setECONOMICO((Double) row[5]);
			cadprolista.setVRVENDA((Double) row[6]);
			cadprolista.setQTATUAL((Double) row[7]);
			cadprolista.setATIVO((Boolean) row[8]);
			cadpro1.add(cadprolista);

		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadpro1;
	}

	public List<Cadpro> getLista1() {
		List<Cadpro> cadpro1 = new ArrayList<Cadpro>();
		Cadpro cadprolista = new Cadpro();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createNativeQuery("SELECT idcadpro,codpro,descpro,un, vrcompra,economico,"
						+ "vrvenda,qtatual,ativo FROM cadpro WHERE sql_deleted = 'F' and ativo = 'F'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			cadprolista = new Cadpro();
			cadprolista.setIdcadpro(Long.parseLong(row[0].toString()));
			cadprolista.setCODPRO((String) row[1]);
			cadprolista.setDESCPRO((String) row[2]);
			cadprolista.setUN((String) row[3]);
			cadprolista.setVRCOMPRA((Double) row[4]);
			cadprolista.setECONOMICO((Double) row[5]);
			cadprolista.setVRVENDA((Double) row[6]);
			cadprolista.setQTATUAL((Double) row[7]);
			cadprolista.setATIVO((Boolean) row[8]);
			cadpro1.add(cadprolista);

		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadpro1;
	}

	public Cadpro buscaproduto(String prod) {
		Cadpro cadpro1 = new Cadpro();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createQuery("SELECT x from Cadpro x where x.CODPRO ='" + prod + "' and x.sql_deleted = 'F'");
		if (!query.getResultList().isEmpty()) {
			cadpro1 = (Cadpro) query.getSingleResult();
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadpro1;

	}

	public boolean Validapro(String prod) {
		boolean vT = true;
		Cadpro cadpro1 = new Cadpro();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createQuery("SELECT x FROM Cadpro x WHERE x.CODPRO = '" + prod + "' and x.sql_deleted = 'F'");
		if (!query.getResultList().isEmpty()) {
		cadpro1 = (Cadpro) query.getSingleResult();
		}

		if (cadpro1.getIdcadpro() == null) {
			vT = false;
		}

		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return vT;

	}

	public Cadpro Validapro1(String prod) {
		Cadpro cadpro1 = new Cadpro();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createNativeQuery(
				"select idcadpro,codpro,descpro,un,vrvenda,ativo,qtatual from cadpro where CODPRO = '" + prod
						+ "' and sql_deleted = 'F'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {

			cadpro1 = new Cadpro();
			cadpro1.setIdcadpro(Long.parseLong(row[0].toString()));
			cadpro1.setCODPRO((String) row[1]);
			cadpro1.setDESCPRO((String) row[2]);
			cadpro1.setUN((String) row[3]);
			cadpro1.setVRVENDA((Double) row[4]);
			cadpro1.setATIVO((Boolean) row[5]);
			cadpro1.setQTATUAL((Double) row[6]);

		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return cadpro1;

	}

}
