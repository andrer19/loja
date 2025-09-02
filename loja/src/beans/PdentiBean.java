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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
import repositorios.CadempRepository;
import repositorios.CadproRepository;
import repositorios.LogusuRepository;
import repositorios.PdentcRepository;
import repositorios.PdentiRepository;
import entidades.Cadcli;
import entidades.Cademp;
import entidades.Cadfor;
import entidades.Cadpro;
import entidades.Pdentc;
import entidades.Pdenti;
import entidades.Usuario;
import filter.EntityManagerUtil;

public class PdentiBean {

	Pdenti pdenti = new Pdenti();
	public List<Pdenti> pdentis;
	AcessoBean aces1 = new AcessoBean();
	Cademp cademp = new Cademp();
	
	LogusuRepository repositorylog = new LogusuRepository(EntityManagerUtil.manager);

	public void adiciona(List<Pdenti> p, Usuario u) {
		EntityManagerUtil.conexao();
		PdentiRepository repository = new PdentiRepository(EntityManagerUtil.manager);

		try {
			repository.grava(p, u);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro grava item compra bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Pdenti procura(Long id) {
		EntityManagerUtil.conexao();
		PdentiRepository repository = new PdentiRepository(EntityManagerUtil.manager);

		try {
			this.pdenti = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procura item compra bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
		return pdenti;

	}

	public List<Pdenti> getPdentis(Long idpedido) {
		EntityManagerUtil.conexao();
		List<Pdenti> pdenti1 = new ArrayList<Pdenti>();
		PdentiRepository repository = new PdentiRepository(EntityManagerUtil.manager);

		try {
			pdenti1 = repository.getLista(idpedido);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro lista item compra bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return pdenti1;
	}

	public void acertaitem(List<Pdenti> p, Usuario u) {
		EntityManagerUtil.conexao();
		PdentiRepository repository = new PdentiRepository(EntityManagerUtil.manager);

		try {
			repository.acertaitem(p, u);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro acerta item compra bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public List<Pdenti> ultent(String produto) {

		List<Pdenti> pdenti1 = new ArrayList<Pdenti>();
		EntityManagerUtil.conexao();

		PdentiRepository repository = new PdentiRepository(EntityManagerUtil.manager);

		try {
			pdenti1 = repository.ultent(produto);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro ultimas entradas item compra bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return pdenti1;
	}

	public void removeitens(Long idpedido, Usuario u) {
		EntityManagerUtil.conexao();

		PdentiRepository repository = new PdentiRepository(EntityManagerUtil.manager);

		try {
			repository.removeitens(idpedido, u);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove itens compra bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

	}

	public void remove(List<Pdenti> pdentir, Usuario u) {
		EntityManagerUtil.conexao();
		PdentiRepository repository = new PdentiRepository(EntityManagerUtil.manager);

		try {
			for (Pdenti idremove : pdentir) {
				repository.removeatualiza(idremove.getIdpdenti(), u);
				repositorylog.adicionaLog(aces1.logexcluir,
						"PEDIDOCOMPRAS " + " NUMERO " + idremove.getPedido().trim() + " PRODUTO "
								+ idremove.getProduto() + " "
								+ idremove.getDescpro(),
								idremove.getIdpdenti(), u);
			}
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove item compra bean " + t.getMessage());
			aces1.demologger.error("Erro remove item compra bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public void voltaquantidadeitem(Long id) {
		EntityManagerUtil.conexao();

		PdentiRepository repository = new PdentiRepository(EntityManagerUtil.manager);

		try {
			repository.voltaquantidadeitem(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro volta quantidade item compra bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

	}

	public void tiraquantidade(String produto, Double qtde) {
		EntityManagerUtil.conexao();
		Cadpro cadpro1 = new Cadpro();
		PdentiRepository repository = new PdentiRepository(EntityManagerUtil.manager);

		try {
			repository.tiraquantidade(produto, qtde);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro volta quantidade item compra bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Cadpro procurapro(String prod) {
		EntityManagerUtil.conexao();
		Long id = null;
		Cadpro cadpro1 = new Cadpro();

		PdentiRepository repository = new PdentiRepository(EntityManagerUtil.manager);

		try {
			cadpro1 = repository.Validapro(prod);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procura produto item compra bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadpro1;

	}

	public Cadpro Validapro(String prod) {
		EntityManagerUtil.conexao();
		Cadpro cadpro1 = new Cadpro();

		PdentiRepository repository = new PdentiRepository(EntityManagerUtil.manager);

		try {
			cadpro1 = repository.Validapro(prod);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro valida produto item compra bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadpro1;

	}

	public void imprimir(Long idpedidocompras) throws Exception {
		CadempRepository repository = new CadempRepository(EntityManagerUtil.manager);
		RelatorioPadraoBean relat = new RelatorioPadraoBean();
		
		String nomearquivo  = "pedcomprasA4";		
		cademp = repository.buscaempresa();
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("id", idpedidocompras);
		parametros.put("logo", "\\\\" + aces1.caminhoireport() + "\\imagens\\logoinicial.png");
		parametros.put("razao", aces1.empresabanco().getRazao());
		parametros.put("enderempresa", cademp.getEnder() + ", " + cademp.getNum() + " - " + cademp.getBairro() + " - "
				+ cademp.getCidade() + " - " + cademp.getEstado());
		parametros.put("foneempresa", cademp.getFone());
		parametros.put("decunitario", TelaPrincipal.decunit);
		parametros.put("decquantidade", TelaPrincipal.decqtde);
		relat.visualizaireport(nomearquivo, parametros);

	}

	public Pdenti getPdenti() {
		return pdenti;
	}

	public void setPdentc(Pdenti pdenti) {
		this.pdenti = pdenti;
	}

	public void setPdentcs(List<Pdenti> pdentis) {
		this.pdentis = pdentis;
	}

}
