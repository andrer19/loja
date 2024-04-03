package beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import repositorios.CadcliRepository;
import repositorios.PdentcRepository;
import repositorios.PdentiRepository;
import entidades.Cadcli;
import entidades.Cadfor;
import entidades.Cadpro;
import entidades.Pdentc;
import entidades.Usuario;
import filter.EntityManagerUtil;

public class PdentcBean {

	Pdentc pdentc = new Pdentc();
	public List<Pdentc> pdentcs;

	public void adiciona(Pdentc p, Usuario u) {
		EntityManagerUtil.conexao();
		PdentcRepository repository = new PdentcRepository(EntityManagerUtil.manager);
		try {
			if (p.getIdpdentc() == null) {
				repository.adiciona(p, u);
			} else {
				repository.atualiza(p, u);
			}
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adiciona pedido entrada bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Pdentc procura(Long id) {
		EntityManagerUtil.conexao();
		PdentcRepository repository = new PdentcRepository(EntityManagerUtil.manager);

		try {
			this.pdentc = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro altera pedido entrada bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return pdentc;
	}

	public List<Pdentc> getPdentcs() {

		List<Pdentc> pdentc1 = new ArrayList<Pdentc>();
		EntityManagerUtil.conexao();
		PdentcRepository repository = new PdentcRepository(EntityManagerUtil.manager);

		try {
			pdentc1 = repository.getLista();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro lista pedido entrada bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return pdentc1;
	}

	public void remove(Long id, Usuario u) {
		EntityManagerUtil.conexao();
		PdentcRepository repository = new PdentcRepository(EntityManagerUtil.manager);

		try {
			repository.removeatualiza(id, u);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove pedido entrada bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Cadfor Validacao(String codigo) {
		Cadfor c = new Cadfor();
		List<Cadfor> lista = new ArrayList<Cadfor>();

		CadforBean cbean = new CadforBean();
		lista = cbean.getCadfors();

		for (Cadfor c1 : lista) {
			if (c1.getCODFOR().equals(codigo)) {
				c = c1;
			}
		}
		return c;

	}

	// ===========================================================================================

	public Pdentc getPdentc() {
		return pdentc;
	}

	public void setPdentc(Pdentc pdentc) {
		this.pdentc = pdentc;
	}

	public void setPdentcs(List<Pdentc> pdentcs) {
		this.pdentcs = pdentcs;
	}

}
