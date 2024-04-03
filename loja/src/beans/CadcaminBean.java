package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.swing.JOptionPane;

import entidades.Cadcamin;
import filter.EntityManagerUtil;
import repositorios.CadcaminRepository;

@ManagedBean
public class CadcaminBean {

	Cadcamin cadcamin = new Cadcamin();
	public List<Cadcamin> cadcamins;

	public void adiciona(Cadcamin p) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		CadcaminRepository repository = new CadcaminRepository(EntityManagerUtil.manager);

		try {
			repository.adiciona(p);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adiciona caminho bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}
	
	public void atualiza(Cadcamin p) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		CadcaminRepository repository = new CadcaminRepository(EntityManagerUtil.manager);

		try {
			repository.atualiza(p);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adiciona caminho bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Cadcamin procura(Long id) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		CadcaminRepository repository = new CadcaminRepository(EntityManagerUtil.manager);

		try {
			this.cadcamin = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro altera caminho bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadcamin;
	}

	public List<Cadcamin> listaCadcamin() {

		if (this.cadcamins == null) {
			EntityManagerUtil.conexao();
			EntityManagerUtil.begin();
			CadcaminRepository repository = new CadcaminRepository(EntityManagerUtil.manager);

			try {
				this.cadcamins = repository.getLista();
			} catch (Throwable t) {
				JOptionPane.showMessageDialog(null, "Erro lista caminho bean: " + t.getMessage());
				EntityManagerUtil.rollback();
			} finally {
				EntityManagerUtil.close();
			}
		}

		return cadcamins;
	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		CadcaminRepository repository = new CadcaminRepository(EntityManagerUtil.manager);

		try {
			repository.remove(id);
			this.cadcamins = null;
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove caminho bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

	}
	
	public Cadcamin caminhofixo() {
		EntityManagerUtil.conexao();
		CadcaminRepository repository = new CadcaminRepository(EntityManagerUtil.manager);

		Cadcamin caminho1 = new Cadcamin();
		List<Cadcamin> caminhos1 = new ArrayList<Cadcamin>();

		try {
		caminhos1 = repository.getLista();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro caminhofixo bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		for (Cadcamin p : caminhos1) {
			caminho1 = p;
		}

		return caminho1;

	}

	// =====================================================================================

	public Cadcamin getCadcamin() {
		return cadcamin;
	}

	public void setCadcamin(Cadcamin cadcamin) {
		this.cadcamin = cadcamin;
	}

	public void setCodemps(List<Cadcamin> cadcamins) {
		this.cadcamins = cadcamins;
	}

}
