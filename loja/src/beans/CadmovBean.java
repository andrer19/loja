package beans;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import repositorios.CadmovRepository;
import entidades.Cadmov;
import filter.EntityManagerUtil;

public class CadmovBean {

	Cadmov cadmov = new Cadmov();

	public void adiciona(Cadmov p) {
		EntityManagerUtil.conexao();
		CadmovRepository repository = new CadmovRepository(EntityManagerUtil.manager);
		try {
			repository.grava(p);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adciona cadmov bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Cadmov altera(Long id) {
		EntityManagerUtil.conexao();
		CadmovRepository repository = new CadmovRepository(EntityManagerUtil.manager);
		try {
			this.cadmov = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro altera cadmov bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
		return cadmov;

	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		CadmovRepository repository = new CadmovRepository(EntityManagerUtil.manager);
		try {
			repository.removeatualiza(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove cadmov bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public List<Cadmov> listamovimentos(String produto) {

		List<Cadmov> cadmov1 = new ArrayList<Cadmov>();
		EntityManagerUtil.conexao();
		CadmovRepository repository = new CadmovRepository(EntityManagerUtil.manager);

		try {
			cadmov1 = repository.getListaporcodigo(produto);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro lista cadmov bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadmov1;
	}

	// GETTERS E SETTERS

	public Cadmov getCadmov() {
		return cadmov;
	}

	public void setCadmov(Cadmov cadmov) {
		this.cadmov = cadmov;
	}

}
