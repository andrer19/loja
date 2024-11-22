package beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import repositorios.CadcliRepository;
import repositorios.PdentiRepository;
import repositorios.PdsaicRepository;
import repositorios.PdsaiiRepository;
import entidades.Cadcli;
import entidades.Cadpro;
import entidades.Pdentc;
import entidades.Pdenti;
import entidades.Pdsaic;
import entidades.Pdsaii;
import entidades.Usuario;
import filter.EntityManagerUtil;

@ManagedBean
public class PdsaicBean {

	Pdsaic pdsaic = new Pdsaic();
	public List<Pdsaic> pdsaics;


	public void adiciona(Pdsaic p, Usuario u) {
		EntityManagerUtil.conexao();
		PdsaicRepository repository = new PdsaicRepository(EntityManagerUtil.manager);
		try {
			if (p.getIdpdsaic() == null) {
				repository.adiciona(p, u);
			} else {
				repository.atualiza(p, u);
			}
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adciona venda bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

	}

	public Pdsaic procura(Long id) {
		EntityManagerUtil.conexao();
		PdsaicRepository repository = new PdsaicRepository(EntityManagerUtil.manager);

		try {
			this.pdsaic = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro altera venda bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return pdsaic;
	}

	public List<Pdsaic> getPdsaics() {
		EntityManagerUtil.conexao();
		List<Pdsaic> pdsaic1 = new ArrayList<Pdsaic>();

		PdsaicRepository repository = new PdsaicRepository(EntityManagerUtil.manager);
		try {
			//pdsaic1 = repository.getLista();
			pdsaic1 = repository.Listapedidosnativo();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro lista venda bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return pdsaic1;
	}

	public void remove(Long id, Usuario u) {
		EntityManagerUtil.conexao();
		PdsaicRepository repository = new PdsaicRepository(EntityManagerUtil.manager);
		try {
			repository.removeatualiza(id, u);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove venda bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Cadcli Validacao(String codigo) {
		EntityManagerUtil.conexao();
		Cadcli c = new Cadcli();
		CadcliRepository reposicli = new CadcliRepository(EntityManagerUtil.manager);

		try {
			c = reposicli.procuracodigo(codigo);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro cliente venda bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return c;

	}

	// =======================================================================================

	public Pdsaic getPdsaic() {
		return pdsaic;
	}

	public void setPdsaic(Pdsaic pdsaic) {
		this.pdsaic = pdsaic;
	}

	public void setPdsaics(List<Pdsaic> pdsaics) {
		this.pdsaics = pdsaics;
	}

}
