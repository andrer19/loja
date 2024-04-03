package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import repositorios.CadforRepository;
import entidades.Cadfor;
import filter.EntityManagerUtil;;

public class CadforBean {

	Cadfor cadfor = new Cadfor();
	public List<Cadfor> cadfors;
	AcessoBean aces1 = new AcessoBean();
	

	public void adiciona(Cadfor p) {
		EntityManagerUtil.conexao();
		CadforRepository repository = new CadforRepository(EntityManagerUtil.manager);
		try {
			repository.grava(p);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adiciona fornecedor bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

	}

	public Cadfor procura(Long id) {
		EntityManagerUtil.conexao();
		CadforRepository repository = new CadforRepository(EntityManagerUtil.manager);
		try {
			this.cadfor = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro altera fornecedor bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return this.cadfor;
	}

	public List<Cadfor> getCadfors() {
		EntityManagerUtil.conexao();
		List<Cadfor> cadforsp = new ArrayList<Cadfor>();
		CadforRepository repository = new CadforRepository(EntityManagerUtil.manager);
		try {
			cadforsp = repository.getLista();
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro altera fornecedor bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
		return cadforsp;

	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		CadforRepository repository = new CadforRepository(EntityManagerUtil.manager);
		try {
			repository.removeatualiza(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove fornecedor bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Cadfor buscacodfor(String prod) {
		Cadfor cadfor1 = new Cadfor();
		EntityManagerUtil.conexao();
		CadforRepository repository = new CadforRepository(EntityManagerUtil.manager);

		try {
			cadfor1 = repository.buscacodfor(prod);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro busca fornecedor bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadfor1;

	}
	
	public boolean Validacnpj(String cnpj, String codigo) {
		EntityManagerUtil.conexao();
		Boolean valida = false;
		CadforRepository repository = new CadforRepository(EntityManagerUtil.manager);

		try {
			valida = repository.validacnpj(cnpj,codigo);
			aces1.demologger.info("validou fornecedor cnpjbean");
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro valida fornecedor cnpjbean " + t.getMessage());
			aces1.demologger.error("Erro valida fornecedor cnpjbean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return valida;

	}
	
	//===========================================================================================

	public Cadfor getCadfor() {
		return cadfor;
	}

	public void setCadfor(Cadfor cadfor) {
		this.cadfor = cadfor;
	}

	public void setCadfors(List<Cadfor> cadfors) {
		this.cadfors = cadfors;
	}

}
