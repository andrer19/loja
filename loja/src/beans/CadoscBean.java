package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.swing.JOptionPane;

import repositorios.CadcliRepository;
import repositorios.CadoscRepository;
import entidades.Cadcli;
import entidades.Cadosc;
import entidades.Usuario;
import filter.EntityManagerUtil;

@ManagedBean
public class CadoscBean {

	Cadosc cadosc = new Cadosc();
	public List<Cadosc> cadoscs;


	public void adiciona(Cadosc p, Usuario u) {
		EntityManagerUtil.conexao();
		CadoscRepository repository = new CadoscRepository(EntityManagerUtil.manager);
		try {
			if (p.getId() == null) {
				repository.adiciona(p, u);
			} else {
				repository.atualiza(p, u);
			}
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adciona serviço bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

	}

	public Cadosc procura(Long id) {
		EntityManagerUtil.conexao();
		CadoscRepository repository = new CadoscRepository(EntityManagerUtil.manager);

		try {
			this.cadosc = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro altera serviço bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadosc;
	}

	public List<Cadosc> getLista() {
		EntityManagerUtil.conexao();
		List<Cadosc> cadosc1 = new ArrayList<Cadosc>();

		CadoscRepository repository = new CadoscRepository(EntityManagerUtil.manager);
		try {
			cadosc1 = repository.getLista();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro lista serviço bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadosc1;
	}

	public void remove(Long id, Usuario u) {
		EntityManagerUtil.conexao();
		CadoscRepository repository = new CadoscRepository(EntityManagerUtil.manager);
		try {
			repository.removeatualiza(id, u);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove serviço bean: " + t.getMessage());
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
			JOptionPane.showMessageDialog(null, "Erro cliente serviço bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return c;

	}

	// =======================================================================================

	public Cadosc getCadosc() {
		return cadosc;
	}

	public void setCadosc(Cadosc cadosc) {
		this.cadosc = cadosc;
	}

	public void setCadoscs(List<Cadosc> cadoscs) {
		this.cadoscs = cadoscs;
	}

}
