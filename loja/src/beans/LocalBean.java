package beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Local;
import filter.EntityManagerUtil;
import repositorios.LocalRepository;

@ManagedBean
public class LocalBean {

	Local local = new Local();
	public List<Local> locals;

	public int minimo;

	public int maximo, numero, dias;
	public String chavecliente, codigolibera, codigop, codigocliente, geralicenca, dia, mes, ano, contador, data,
			codigof, diasf;

	Locale local1 = new Locale("pt", "BR");
	DateFormat datestring = new SimpleDateFormat("dd/MM/yyyy", local1);

	public void adiciona(Local p) {
		EntityManagerUtil.conexao();
		LocalRepository repository = new LocalRepository(EntityManagerUtil.manager);
		try {
			repository.grava(p);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adiciona loc bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Local altera(Long id) {
		EntityManagerUtil.conexao();
		LocalRepository repository = new LocalRepository(EntityManagerUtil.manager);
		try {
			this.local = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro altera loc bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return local;
	}

	public Boolean liberasenha(String codigocliente, String novalicenca) {

		Boolean valida = false, verificadia = false;
		String pdias = novalicenca.substring(6, novalicenca.length());
		String hexa = novalicenca.substring(0, novalicenca.length() - 3);
		int value = Integer.parseInt(codigocliente, 16);
		String hexacodcli = String.valueOf(value + "2301");
		String codigot = Integer.toHexString(Integer.parseInt(hexacodcli));

		String licenca = null;
		diasf = null;

		Double valor;

		if (pdias != null && !pdias.isEmpty()) {
			try {
				valor = (Double.parseDouble(pdias));
				verificadia = true;
			} catch (NumberFormatException e) {
				verificadia = false;
			}
		}

		if (codigot.equals(hexa) && verificadia == true) {

			diasf = pdias;
			codigof = codigot;

			valida = true;

		}

		return valida;

	}

	public Boolean gravalicenca() {
		EntityManagerUtil.conexao();

		Boolean valida = false;

		int mes, dia, ano;

		String diar, mesr, licenca = null, lic = null;

		Double valor;

		try {
			this.setDias(Integer.parseInt(diasf));
			LocalRepository repository = new LocalRepository(EntityManagerUtil.manager);

			Calendar c = Calendar.getInstance();
			c.setTime(repository.dataatual());
			c.add(Calendar.DATE, +this.getDias());

			dia = c.get(c.DAY_OF_MONTH);
			mes = c.get(c.MONTH);

			if (dia < 10) {
				diar = "0" + String.valueOf(dia);
			} else {
				diar = String.valueOf(dia);
			}

			int mes2 = mes + 1;

			if (mes2 < 10) {
				mesr = "0" + String.valueOf(mes2);
			} else {
				mesr = String.valueOf(mes2);
			}

			ano = c.get(c.YEAR);

			licenca = "HYF8JCVRMY" + diar + "CM74GRPHKFPW" + mesr + "487YHKGDVQ" + ano + "27RYRBXJMPVMWG" + diasf
					+ "38TFJ82HXT6CR";
			if (this.getDias() < 100) {
				lic = "NKJFKGPH0" + this.getDias() + "7G8C3JP6JXRHQRJR";
			} else {
				lic = "NKJFKGPH" + this.getDias() + "7G8C3JP6JXRHQRJR";
			}

			locals = repository.getLista();

			for (Local locs : locals) {
				this.setLocal(locs);
			}

			local.setLibera(licenca);
			local.setLic(lic);

			repository.grava(local);
			valida = true;
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro gravar licença bean: " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
		
		return valida;

	}

	public String geralic(String numero1) {

		int num = Integer.parseInt(numero1, 16);
		String g = null, hexadecimal = null;

		g = String.valueOf(num + "2301");

		hexadecimal = Integer.toHexString(Integer.parseInt(g));

		System.out.println("numero: " + numero1);
		System.out.println("numero num: " + num);
		System.out.println("codigo hexadecimal: " + hexadecimal);

		return hexadecimal;

	}

	public String verificacodigo() {

		String t = String.valueOf(numero + "2301");
		codigop = Integer.toHexString(Integer.parseInt(t));

		return codigop;
	}

	public int numerorandom() {

		int n = 0;

		Random r = new Random();

		int minimo = 100;
		int maximo = 999;

		n = r.nextInt(maximo - minimo + 1) + minimo;

		numero = n;

		return n;

	}

	public String numerocliente() {

		chavecliente = Integer.toHexString(numero);
		String t = String.valueOf(numero + "2301");
		codigop = Integer.toHexString(Integer.parseInt(t));
		
		return chavecliente;

	}

	public Boolean validalicenca() {
		boolean valida = false;
		EntityManagerUtil.conexao();

		LocalRepository repository = new LocalRepository(EntityManagerUtil.manager);

		for (Local loc : repository.getLista()) {
			this.setLocal(loc);
		}

		if (local.getLibera() != null && !local.getLibera().isEmpty()) {
			dia = local.getLibera();
			dia = dia.substring(10, dia.length() - 58);

			mes = local.getLibera();
			mes = mes.substring(24, mes.length() - 44);

			ano = local.getLibera();
			ano = ano.substring(36, ano.length() - 30);

			contador = local.getLibera();
			contador = contador.substring(54, contador.length() - 13);

			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
			c.set(Calendar.MONTH, Integer.parseInt(mes) - 1);
			c.set(Calendar.YEAR, Integer.parseInt(ano));

			Date datab = c.getTime();

			data = datestring.format(datab);

			Calendar c1 = Calendar.getInstance();
			c1.setTime(datab);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(repository.dataatual());

			long diferenca = c1.getTime().getTime() - c2.getTime().getTime();

			String lic1 = null;
			lic1 = local.getLic();
			lic1 = lic1.substring(8, lic1.length() - 16);

			if (Long.parseLong(lic1) >= TimeUnit.MILLISECONDS.toDays(diferenca)) {
				if (repository.dataatual().before(datab)
						|| datestring.format(repository.dataatual()).compareTo(datestring.format(datab)) == 0) {

					valida = true;
				}
			}
		}
		EntityManagerUtil.close();
		return valida;

	}

	// ============================================================================================

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public String getChavecliente() {
		return chavecliente;
	}

	public void setChavecliente(String chavecliente) {
		this.chavecliente = chavecliente;
	}

	public String getCodigolibera() {
		return codigolibera;
	}

	public void setCodigolibera(String codigolibera) {
		this.codigolibera = codigolibera;
	}

	public String getCodigop() {
		return codigop;
	}

	public void setCodigop(String codigop) {
		this.codigop = codigop;
	}

	public String getCodigocliente() {
		return codigocliente;
	}

	public void setCodigocliente(String codigocliente) {
		this.codigocliente = codigocliente;
	}

	public String getGeralicenca() {
		return geralicenca;
	}

	public void setGeralicenca(String geralicenca) {
		this.geralicenca = geralicenca;
	}

}
