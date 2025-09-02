package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.swing.JOptionPane;

import Configuracao.RelatorioPadraoBean;
import Grafico.geral.TelaPrincipal;
import repositorios.CadempRepository;
import repositorios.CadosiRepository;
import entidades.Cademp;
import entidades.Cadosi;
import entidades.Usuario;
import filter.EntityManagerUtil;

@ManagedBean
public class CadosiBean {

	Cadosi cadosi = new Cadosi();
	public List<Cadosi> cadosis;

	AcessoBean aces1 = new AcessoBean();
	Cademp cademp = new Cademp();

	public void adiciona(List<Cadosi> p, Usuario u) {
		EntityManagerUtil.conexao();
		CadosiRepository repository = new CadosiRepository(EntityManagerUtil.manager);

		try {
			for (Cadosi cadosi : p) {
				if (cadosi.getId() == null) {
					repository.gravan(cadosi);
				} else {
					repository.gravan(cadosi);
				}
			}
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro grava item Ordem Serviço bean " + t.getMessage());
			aces1.demologger.error("Erro grava item Ordem Serviço bean" + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public void acertaitem(List<Cadosi> p, Usuario u) {
		EntityManagerUtil.conexao();
		CadosiRepository repository = new CadosiRepository(EntityManagerUtil.manager);
		
		try {
			repository.acertaitem(p, u);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro acertaitem Ordem Serviço bean " + t.getMessage());
			aces1.demologger.error("Erro acertaitem Ordem Serviço bean" + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Cadosi procura(Long id) {
		EntityManagerUtil.conexao();
		CadosiRepository repository = new CadosiRepository(EntityManagerUtil.manager);
		try {
			this.cadosi = repository.procura(id);

		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro altera item Ordem Serviço bean " + t.getMessage());
			aces1.demologger.error("Erro altera item Ordem Serviço bean" + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
		return cadosi;

	}

	public List<Cadosi> getCadosis(Long idpedido) {
		EntityManagerUtil.conexao();
		List<Cadosi> cadosi1 = new ArrayList<Cadosi>();
		CadosiRepository repository = new CadosiRepository(EntityManagerUtil.manager);

		try {
			cadosi1 = repository.getLista(idpedido);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro lista itens Ordem Serviço bean " + t.getMessage());
			aces1.demologger.error("Erro lista itens Ordem Serviço bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return cadosi1;
	}

	public void removeitens(Long idpedido, Usuario u) {
		EntityManagerUtil.conexao();
		CadosiRepository repository = new CadosiRepository(EntityManagerUtil.manager);
		try {
			repository.removeitens(idpedido, u);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro remove itens Ordem Serviço bean " + t.getMessage());
			aces1.demologger.error("Erro remove itens Ordem Serviço bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

	}

	public void remove(List<Cadosi> cadosir, Usuario u) {
		EntityManagerUtil.conexao();
		CadosiRepository repository = new CadosiRepository(EntityManagerUtil.manager);

		try {
			for (Cadosi idremove : cadosir) {
				repository.removeatualiza(idremove.getId(), u);
			}
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro removeitem Ordem Serviço bean " + t.getMessage());
			aces1.demologger.error("Erro removeitem Ordem Serviço bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

		
	public void imprimirA4(Long idordemserv) throws Exception {
		EntityManagerUtil.conexao();
		
		CadempRepository repository = new CadempRepository(EntityManagerUtil.manager);
		RelatorioPadraoBean relat = new RelatorioPadraoBean();
		
		String nomearquivo  = "ordems";		
		cademp = repository.buscaempresa();
		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put("id", idordemserv);
		parametros.put("logo", aces1.retornalogorelat());
		parametros.put("razao", cademp.getRazao());
		parametros.put("enderempresa",
				cademp.getEnder() + "," + cademp.getNum() + " - " + cademp.getBairro() + " - " + cademp.getCidade());
		parametros.put("foneempresa", cademp.getFone());
		parametros.put("data", aces1.datastring());
		parametros.put("hora", aces1.hora());
		parametros.put("enderempresa", cademp.getEnder() + ", " + cademp.getNum() + " - " + cademp.getBairro() + " - "
				+ cademp.getCidade() + " - " + cademp.getEstado());
		parametros.put("decunitario", TelaPrincipal.decunit);
		parametros.put("decquantidade", TelaPrincipal.decqtde);
		relat.visualizaireport(nomearquivo, parametros);

	}


	// =================================================================================

	public Cadosi getCadosi() {
		return cadosi;
	}

	public void setCadosi(Cadosi cadosi) {
		this.cadosi = cadosi;
	}

	public void setCadosis(List<Cadosi> cadosis) {
		this.cadosis = cadosis;
	}

}
