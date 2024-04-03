package beans;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.swing.JOptionPane;

import repositorios.ModulosRepository;
import entidades.Modulos;
import filter.EntityManagerUtil;

@ManagedBean
@SessionScoped
public class ModulosBean {

	Modulos modulos = new Modulos();
	public List<Modulos> moduloss;

	public void adiciona(Modulos p) {
		EntityManagerUtil.conexao();
		ModulosRepository repository = new ModulosRepository(EntityManagerUtil.manager);

		try {
			repository.grava(p);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adiciona modulo bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Modulos altera(Long id) {
		EntityManagerUtil.conexao();
		ModulosRepository repository = new ModulosRepository(EntityManagerUtil.manager);

		try {
			this.modulos = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro altera modulo bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return modulos;
	}

	public List<Modulos> listaModulos() {

		if (this.moduloss == null) {
			EntityManagerUtil.conexao();
			ModulosRepository repository = new ModulosRepository(EntityManagerUtil.manager);

			try {
				this.moduloss = repository.getLista();
			} catch (Throwable t) {
				JOptionPane.showMessageDialog(null, "Erro lista modulo bean: " + t.getMessage());
				EntityManagerUtil.rollback();
			} finally {
				EntityManagerUtil.close();
			}
		}

		return moduloss;
	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		ModulosRepository repository = new ModulosRepository(EntityManagerUtil.manager);

		try {
			repository.remove(id);
			this.moduloss = null;
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove modulo bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

	}

	// =====================================================================================

	public Modulos getModulos() {
		return modulos;
	}

	public void setModulos(Modulos modulos) {
		this.modulos = modulos;
	}

	public void setCodemps(List<Modulos> moduloss) {
		this.moduloss = moduloss;
	}

}
