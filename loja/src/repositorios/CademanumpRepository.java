package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Cadmanuemp;
import filter.EntityManagerUtil;


public class CademanumpRepository {


	public CademanumpRepository(EntityManager manager) {
		
	}

	public void adiciona(Cadmanuemp cadmanuemp) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.persist(cadmanuemp);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void remove(Long id) {
		Cadmanuemp cadmanuemp = this.procura(id);
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.remove(cadmanuemp);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void atualiza(Cadmanuemp cadmanuemp) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(cadmanuemp);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public Cadmanuemp procura(Long id) {
		Cadmanuemp cadmanuemp = new Cadmanuemp();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		cadmanuemp = EntityManagerUtil.manager.find(Cadmanuemp.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		
		return cadmanuemp;
	}

	public List<Cadmanuemp> getLista() {
		List<Cadmanuemp> manuemp = new ArrayList<Cadmanuemp>();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("select x from Cadmanuemp x");
		if (!query.getResultList().isEmpty()) {
			manuemp = query.getResultList();
		}
		
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		
		return manuemp; 
	}

	public Cadmanuemp paramentros() {
		List<Cadmanuemp> manuemps = new ArrayList<Cadmanuemp>();
		Cadmanuemp cadmanuemp = new Cadmanuemp();

		manuemps = getLista();

		for (Cadmanuemp p : manuemps) {
			cadmanuemp = p;

		}

		return cadmanuemp;
	}

	public int rastreioemp(String campo) {
		int v = 0;
		List<Cadmanuemp> manuemps = new ArrayList<Cadmanuemp>();

		manuemps = getLista();

		for (Cadmanuemp p : manuemps) {
			if (p.getId() != null) {
				if (campo.equals("pedido")) {
					if (p.getNumpedc() == null || p.getNumpedc().isEmpty()) {
						v = 1;
					} else {
						v = Integer.parseInt(p.getNumpedc());
					}
				}

				if (campo.equals("orcamentovendas")) {
					if (p.getOrcven() == null || p.getOrcven().isEmpty()) {
						v = 1;
					} else {
						v = Integer.parseInt(p.getOrcven());
					}
				}

				if (campo.equals("pedidovendas")) {
					if (p.getNumpedv() == null || p.getNumpedv().isEmpty()) {
						v = 1;
					} else {
						v = Integer.parseInt(p.getNumpedv());
					}
				}

				if (campo.equals("lote")) {
					if (p.getNumlot() == null || p.getNumlot().isEmpty()) {
						v = 1;
					} else {
						v = Integer.parseInt(p.getNumlot());
					}
				}

				if (campo.equals("requisicao")) {
					if (p.getReqcom() == null || p.getReqcom().isEmpty()) {
						v = 1;
					} else {
						v = Integer.parseInt(p.getReqcom());
					}
				}

				if (campo.equals("recebi")) {
					if (p.getRecebi() == null || p.getRecebi().isEmpty()) {
						v = 1;
					} else {
						v = Integer.parseInt(p.getRecebi());
					}
				}

				if (campo.equals("normas")) {
					if (p.getNormas() == null || p.getNormas().isEmpty()) {
						v = 0;
					} else {
						v = Integer.parseInt(p.getNormas());
					}
				}

				if (campo.equals("inspgui")) {
					if (p.getInspgui() == null || p.getInspgui().isEmpty()) {
						v = 0;
					} else {
						v = Integer.parseInt(p.getInspgui());
					}
				}
				
				if (campo.equals("ordemproducao")) {
					if (p.getOrdemprod() == null || p.getOrdemprod().isEmpty()) {
						v = 0;
					} else {
						v = Integer.parseInt(p.getOrdemprod());
					}
				}
				
				
				if (campo.equals("ordemembalagem")) {
					if (p.getOrdememb() == null || p.getOrdememb().isEmpty()) {
						v = 0;
					} else {
						v = Integer.parseInt(p.getOrdememb());
					}
				}
				
				if (campo.equals("inspecaoprocesso")) {
					if (p.getInspprocesso() == null || p.getInspprocesso().isEmpty()) {
						v = 0;
					} else {
						v = Integer.parseInt(p.getInspprocesso());
					}
				}
				
				if (campo.equals("lotexordem")) {
					if (p.getEtiqpro() == null || p.getEtiqpro().isEmpty()) {
						v = 0;
					} else {
						v = Integer.parseInt(p.getEtiqpro());
					}
				}
				
				if (campo.equals("cliente")) {
					if (p.getCodcli() == null || p.getCodcli().isEmpty()) {
						v = 0;
					} else {
						v = Integer.parseInt(p.getCodcli());
					}
				}
				
				if (campo.equals("fornecedor")) {
					if (p.getCodfor() == null || p.getCodfor().isEmpty()) {
						v = 0;
					} else {
						v = Integer.parseInt(p.getCodfor());
					}
				}
				
				if (campo.equals("transportadora")) {
					if (p.getCodtrans() == null || p.getCodtrans().isEmpty()) {
						v = 0;
					} else {
						v = Integer.parseInt(p.getCodtrans());
					}
				}


			}
		}

		return v;
	}

	public void atualizarastreio(String modulo, String campo) {
		Cadmanuemp manuemp = new Cadmanuemp();
		List<Cadmanuemp> manuemps = new ArrayList<Cadmanuemp>();

		manuemps = getLista();

		for (Cadmanuemp p : manuemps) {
			manuemp = p;
		}
		
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();

		if (modulo.equals("pedido")) {
			manuemp.setNumpedc(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}

		if (modulo.equals("orcamentovendas")) {
			manuemp.setOrcven(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}

		if (modulo.equals("pedidovendas")) {
			manuemp.setNumpedv(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}

		if (modulo.equals("lote")) {
			manuemp.setNumlot(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}

		if (modulo.equals("requisicao")) {
			manuemp.setReqcom(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}

		if (modulo.equals("recebi")) {
			manuemp.setRecebi(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}

		if (modulo.equals("normas")) {
			manuemp.setNormas(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}
		
		if (modulo.equals("inspgui")) {
			manuemp.setInspgui(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}
		
		if (modulo.equals("ordemproducao")) {
			manuemp.setOrdemprod(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}
		
		if (modulo.equals("lote")) {
			manuemp.setNumlot(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}
		
		if (modulo.equals("ordemembalagem")) {
			manuemp.setOrdememb(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}
		
		if (modulo.equals("inspecaoprocesso")) {
			manuemp.setInspprocesso(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}
		
		if (modulo.equals("lotexordem")) {
			manuemp.setEtiqpro(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}
		
		if (modulo.equals("cliente")) {
			manuemp.setCodcli(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}
		
		if (modulo.equals("fornecedor")) {
			manuemp.setCodfor(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}
		
		if (modulo.equals("transportadora")) {
			manuemp.setCodtrans(campo);
			EntityManagerUtil.manager.merge(manuemp);
		}
		
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

}
