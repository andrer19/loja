package beans;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Configuracao.RelatorioPadraoBean;
import Grafico.geral.TelaPrincipal;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import repositorios.CadcliRepository;
import repositorios.CadempRepository;
import repositorios.CadproRepository;
import repositorios.LogusuRepository;
import repositorios.PdsaicRepository;
import repositorios.PdsaiiRepository;
import entidades.Cadcli;
import entidades.Cademp;
import entidades.Cadpro;
import entidades.Pdenti;
import entidades.Pdsaic;
import entidades.Pdsaii;
import entidades.Usuario;
import filter.EntityManagerUtil;

@ManagedBean
public class PdsaiiBean {

	Pdsaii pdsaii = new Pdsaii();
	public List<Pdsaii> pdsaiis;

	AcessoBean aces1 = new AcessoBean();
	Cademp cademp = new Cademp();

	public void adiciona(List<Pdsaii> p, Usuario u) {
		EntityManagerUtil.conexao();
		PdsaiiRepository repository = new PdsaiiRepository(EntityManagerUtil.manager);
		CadproRepository repoprod = new CadproRepository(EntityManagerUtil.manager);
		LogusuRepository repolog = new LogusuRepository(EntityManagerUtil.manager);

		try {
			for (Pdsaii pdsaii : p) {
				if (pdsaii.getIdpdsaii() == null) {
					repository.gravan(pdsaii);
					repoprod.qtdevendida(pdsaii);
				} else {
					repository.gravan(pdsaii);
				}
			}
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro grava item bean " + t.getMessage());
			aces1.demologger.error("Erro grava item bean" + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public void acertaitem(List<Pdsaii> p, Usuario u) {
		EntityManagerUtil.conexao();
		PdsaiiRepository repository = new PdsaiiRepository(EntityManagerUtil.manager);
		
		try {
			repository.acertaitem(p, u);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro acertaitem bean " + t.getMessage());
			aces1.demologger.error("Erro acertaitem bean" + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Pdsaii procura(Long id) {
		EntityManagerUtil.conexao();
		PdsaiiRepository repository = new PdsaiiRepository(EntityManagerUtil.manager);
		try {
			this.pdsaii = repository.procura(id);

		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro altera item bean " + t.getMessage());
			aces1.demologger.error("Erro altera item bean" + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
		return pdsaii;

	}

	public List<Pdsaii> getPdsaiis(Long idpedido) {
		EntityManagerUtil.conexao();
		List<Pdsaii> pdsaii1 = new ArrayList<Pdsaii>();
		PdsaiiRepository repository = new PdsaiiRepository(EntityManagerUtil.manager);

		try {
			pdsaii1 = repository.getLista(idpedido);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro lista itens bean " + t.getMessage());
			aces1.demologger.error("Erro lista itens bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return pdsaii1;
	}

	public List<Pdsaii> ultsai(String produto) {
		EntityManagerUtil.conexao();
		List<Pdsaii> pdenti1 = new ArrayList<Pdsaii>();

		PdsaiiRepository repository = new PdsaiiRepository(EntityManagerUtil.manager);
		try {
			pdenti1 = repository.ultsai(produto);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro ultimasaidas bean " + t.getMessage());
			aces1.demologger.error("Erro ultimasaidas bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return pdenti1;
	}

	public void removeitens(Long idpedido, Usuario u) {
		EntityManagerUtil.conexao();
		PdsaiiRepository repository = new PdsaiiRepository(EntityManagerUtil.manager);
		try {
			repository.removeitens(idpedido, u);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove itens bean " + t.getMessage());
			aces1.demologger.error("Erro remove itens bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

	}

	public void remove(List<Pdsaii> pdsaiir, Usuario u) {
		EntityManagerUtil.conexao();
		PdsaiiRepository repository = new PdsaiiRepository(EntityManagerUtil.manager);

		try {
			for (Pdsaii idremove : pdsaiir) {
				repository.removeatualiza(idremove.getIdpdsaii(), u);
			}
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro removeitem bean " + t.getMessage());
			aces1.demologger.error("Erro removeitem bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public void voltaquantidadeitem(Long id) {
		EntityManagerUtil.conexao();
		PdsaiiRepository repository = new PdsaiiRepository(EntityManagerUtil.manager);

		try {
			repository.voltaquantidadeitem(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro voltaquantidade bean " + t.getMessage());
			aces1.demologger.error("Erro voltaquantidade bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

	}

	public void tiraquantidade(String produto, Double qtde) {
		EntityManagerUtil.conexao();
		PdsaiiRepository repository = new PdsaiiRepository(EntityManagerUtil.manager);

		try {
			repository.tiraquantidade(produto, qtde);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro tiraquantidade bean " + t.getMessage());
			aces1.demologger.error("Erro tiraquantidade bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Cadpro procurapro(String prod) {
		EntityManagerUtil.conexao();
		Cadpro cadpro1 = new Cadpro();

		PdsaiiRepository repository = new PdsaiiRepository(EntityManagerUtil.manager);
		try {
			cadpro1 = repository.Validapro(prod);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procurapro bean " + t.getMessage());
			aces1.demologger.error("Erro procurapro bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadpro1;

	}

	public Cadpro Validapro(String prod) {
		EntityManagerUtil.conexao();
		Cadpro cadpro1 = new Cadpro();

		PdsaiiRepository repository = new PdsaiiRepository(EntityManagerUtil.manager);
		try {
			cadpro1 = repository.Validapro(prod);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro validapro bean " + t.getMessage());
			aces1.demologger.error("Erro validapro bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadpro1;

	}

	public void imprimir(Long idpedido) throws Exception {
		EntityManagerUtil.conexao();
		
		CadempRepository repository = new CadempRepository(EntityManagerUtil.manager);
		RelatorioPadraoBean relat = new RelatorioPadraoBean();
		
		String nomearquivo  = "pedvendas";
		Map<String, Object> parametros = new HashMap<>();
		cademp = repository.buscaempresa();

		parametros.put("id", idpedido);
		parametros.put("logo", aces1.retornalogorelat());
		parametros.put("razao", cademp.getRazao());
		parametros.put("enderempresa",
				cademp.getEnder() + "," + cademp.getNum() + " - " + cademp.getBairro() + " - " + cademp.getCidade());
		parametros.put("foneempresa", cademp.getFone());
		parametros.put("data", aces1.datastring());
		parametros.put("hora", aces1.hora());
		parametros.put("enderempresa", cademp.getEnder() + ", " + cademp.getNum() + " - " + cademp.getBairro() + " - "
				+ cademp.getCidade() + " - " + cademp.getEstado());
		relat.visualizaireport(nomearquivo, parametros);

	}
	
	
	public void imprimirA4(Long idpedidovenda) throws Exception {
		EntityManagerUtil.conexao();
		
		CadempRepository repository = new CadempRepository(EntityManagerUtil.manager);
		RelatorioPadraoBean relat = new RelatorioPadraoBean();
		
		String nomearquivo  = "pedvendasA4";		
		cademp = repository.buscaempresa();
		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put("id", idpedidovenda);
		parametros.put("logo", aces1.retornalogorelat());
		parametros.put("razao", cademp.getRazao());
		parametros.put("enderempresa",
				cademp.getEnder() + "," + cademp.getNum() + " - " + cademp.getBairro() + " - " + cademp.getCidade());
		parametros.put("foneempresa", cademp.getFone());
		parametros.put("data", aces1.datastring());
		parametros.put("hora", aces1.hora());
		parametros.put("enderempresa", cademp.getEnder() + ", " + cademp.getNum() + " - " + cademp.getBairro() + " - "
				+ cademp.getCidade() + " - " + cademp.getEstado());
		relat.visualizaireport(nomearquivo, parametros);

	}


	// =================================================================================

	public Pdsaii getPdsaii() {
		return pdsaii;
	}

	public void setPdsaii(Pdsaii pdsaii) {
		this.pdsaii = pdsaii;
	}

	public void setPdsaiis(List<Pdsaii> pdsaiis) {
		this.pdsaiis = pdsaiis;
	}

}
