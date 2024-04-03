package beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidades.Municip;
import filter.EntityManagerUtil;
import repositorios.MunicipRepository;

@ManagedBean
public class MunicipBean {

	Municip municip = new Municip();
	public List<Municip> municips;

	
	public Municip procura(String codigo) {
		EntityManagerUtil.conexao();
		MunicipRepository repository = new MunicipRepository(EntityManagerUtil.manager);

		try {
			municip = repository.procuraporcodigo(codigo);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procura Municipio bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
		return municip;

	}
	
	public Municip validamunicipio(String valor) {

		Municip m2 = new Municip();

		m2 = procura(valor.trim());

		return m2;

	}

	public List<Municip> getMunicipios() {
		EntityManagerUtil.conexao();
		List<Municip> municip1 = new ArrayList<Municip>();
		MunicipRepository repository = new MunicipRepository(EntityManagerUtil.manager);

		try {
			municip1 = repository.getLista();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro lista Municipio bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return municip1;
	}


	// =====================================================================================

	public Municip getMunicip() {
		return municip;
	}

	public void setMunicip(Municip municip) {
		this.municip = municip;
	}

	public List<Municip> getMunicips() {
		return municips;
	}

	public void setMunicips(List<Municip> municips) {
		this.municips = municips;
	}
	


	
}
