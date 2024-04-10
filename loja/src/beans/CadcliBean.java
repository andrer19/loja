package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import repositorios.CadcliRepository;
import repositorios.CadproRepository;
import repositorios.PdsaicRepository;
import entidades.Cadcli;
import entidades.Pdsaic;
import filter.EntityManagerUtil;

@ManagedBean
public class CadcliBean {

	Cadcli cadcli = new Cadcli();
	public List<Cadcli> cadclis;
	AcessoBean aces1 = new AcessoBean();

	public void adiciona(Cadcli p) {
		EntityManagerUtil.conexao();
		CadcliRepository repository = new CadcliRepository(EntityManagerUtil.manager);

		try {
			repository.grava(p);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adiciona cliente bean " + t.getMessage());
			aces1.demologger.error("Erro adiciona cliente bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Cadcli procura(Long id) {
		EntityManagerUtil.conexao();
		CadcliRepository repository = new CadcliRepository(EntityManagerUtil.manager);

		try {
			this.cadcli = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro altera cliente bean: " + t.getMessage());
			aces1.demologger.error("ERRO ALTERA CLIENTE BEAN " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
		return cadcli;

	}

	public List<Cadcli> getCadclis() {
		EntityManagerUtil.conexao();
		List<Cadcli> cadcli1 = new ArrayList<Cadcli>();
		CadcliRepository repository = new CadcliRepository(EntityManagerUtil.manager);

		try {
			cadcli1 = repository.getLista();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro lista cliente bean: " + t.getMessage());
			aces1.demologger.error("ERRO LISTA CLIENTE BEAN " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadcli1;
	}
	
	public List<Cadcli> getClientesativos() {
		EntityManagerUtil.conexao();
		List<Cadcli> cadcli1 = new ArrayList<Cadcli>();
		CadcliRepository repository = new CadcliRepository(EntityManagerUtil.manager);

		try {
			cadcli1 = repository.getListaativos();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro lista cliente Ativos bean: " + t.getMessage());
			aces1.demologger.error("ERRO LISTA CLIENTES ATIVOS BEAN " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadcli1;
	}
	
	public List<Pdsaic> Listapedidoantigos(Long idcliente) {
		EntityManagerUtil.conexao();
		List<Pdsaic> pedidocliente = new ArrayList<Pdsaic>();
		PdsaicRepository repository = new PdsaicRepository(EntityManagerUtil.manager);
		
		try {
			pedidocliente = repository.Listapedidoantigos(idcliente);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro lista Pedidos cliente bean: " + t.getMessage());
			aces1.demologger.error("ERRO LISTA PEDIDOS POR CLIENTE BEAN " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
		
		return pedidocliente;
	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		CadcliRepository repository = new CadcliRepository(EntityManagerUtil.manager);

		try {
			repository.removeatualiza(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove cliente bean: " + t.getMessage());
			aces1.demologger.error("ERRO REMOVE CLIENTE BEAN " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Cadcli Validacao(String codigo) {
		Cadcli c = new Cadcli();
		EntityManagerUtil.conexao();
		CadcliRepository repocliente = new CadcliRepository(EntityManagerUtil.manager);

		try {
			c = repocliente.procuracodigo(codigo.trim());
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro buscacliente bean: " + t.getMessage());
			aces1.demologger.error("ERRO BUSCA CLIENTE BEAN " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return c;

	}

	// =====================================================================================

	public Cadcli getCadcli() {
		return cadcli;
	}

	public void setCadcli(Cadcli cadcli) {
		this.cadcli = cadcli;
	}

	public void setCadclis(List<Cadcli> cadclis) {
		this.cadclis = cadclis;
	}

}
