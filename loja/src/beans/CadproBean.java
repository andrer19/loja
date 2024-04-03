package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import repositorios.CadproRepository;
import entidades.Cadcli;
import entidades.Cadpro;
import entidades.Usuario;
import filter.EntityManagerUtil;

@ManagedBean
public class CadproBean {

	Cadpro cadpro = new Cadpro();
	public List<Cadpro> codpros;

	public void adiciona(Cadpro p) {
		EntityManagerUtil.conexao();
		CadproRepository repository = new CadproRepository(EntityManagerUtil.manager);
		try {
			repository.grava(p);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adiciona bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Cadpro procura(Long id) {
		EntityManagerUtil.conexao();
		CadproRepository repository = new CadproRepository(EntityManagerUtil.manager);
		try {
			this.cadpro = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procura bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadpro;

	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		CadproRepository repository = new CadproRepository(EntityManagerUtil.manager);
		try {
			repository.removeatualiza(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public List<Cadpro> getCodpros() {
		EntityManagerUtil.conexao();
		List<Cadpro> cadpro1 = new ArrayList<Cadpro>();
		CadproRepository repository = new CadproRepository(EntityManagerUtil.manager);
		try {
			cadpro1 = repository.getLista();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro lista bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadpro1;
	}

	public List<Cadpro> getprodutoativos() {
		EntityManagerUtil.conexao();
		List<Cadpro> cadpro1 = new ArrayList<Cadpro>();
		CadproRepository repository = new CadproRepository(EntityManagerUtil.manager);
		try {
			cadpro1 = repository.getLista1();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro ativos bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadpro1;
	}

	public Cadpro buscaproduto(String prod) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Cadpro cadpro1 = new Cadpro();
		CadproRepository repo = new CadproRepository(EntityManagerUtil.manager);
		try {
			cadpro1 = repo.buscaproduto(prod);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro busca bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadpro1;

	}

	public boolean Validapro(String prod) {
		EntityManagerUtil.conexao();
		Boolean valida = true;
		CadproRepository repository = new CadproRepository(EntityManagerUtil.manager);

		try {
			valida = repository.Validapro(prod);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro valida produto bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return valida;

	}

	public Cadpro Validapro1(String prod) {
		EntityManagerUtil.conexao();
		Cadpro validapro1 = new Cadpro();
		CadproRepository repository = new CadproRepository(EntityManagerUtil.manager);

		try {
			validapro1 = repository.Validapro1(prod);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro valida1 produto bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return validapro1;

	}

	

	// ========================================================================================

	public Cadpro getCadpro() {
		return cadpro;
	}

	public void setCadpro(Cadpro cadpro) {
		this.cadpro = cadpro;
	}

	public void setCodpros(List<Cadpro> codpros) {
		this.codpros = codpros;
	}

}
