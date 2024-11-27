package filter;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.IllegalCharsetNameException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;


import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import entidades.Cademp;
import repositorios.CadempRepository;

public class EnviaEmail {

	private List<Cademp> cademps;
	public String email, senha, smtp;
	public int porta;
	public Boolean autentica;
	Cademp empresa = new Cademp();

	public EnviaEmail() {
		if (this.cademps == null) {
			EntityManagerUtil.conexao();
			CadempRepository repository = new CadempRepository(EntityManagerUtil.manager);
			this.cademps = repository.getLista();
		}

		for (Cademp p : cademps) {
			if(p.getEmail() != null && !p.getEmail().isEmpty() &&
					p.getPorta() != null && !p.getPorta().isEmpty()) {
			this.setEmail(p.getEmail());
			this.setPorta(Integer.parseInt(p.getPorta()));
			this.setSenha(p.getSenha());
			this.setAutentica(p.getAutenticacaossl());
			this.setSmtp(p.getSmtp());
			empresa = p;
			}
		}

	}

	public Boolean emailteste(String empresa, String smtp, String porta, String senha, String email1,
			boolean autentica) {
		Boolean verifica = false;
		try {
			Email email = new SimpleEmail();
			email.setHostName(smtp);
			email.setSmtpPort(Integer.parseInt(porta));
			email.setAuthentication(email1, senha);
			email.setSSLOnConnect(autentica);

			email.setFrom(email1);
			email.setSubject("Email de teste");
			email.setMsg("Teste de email comfirmado empresa " + empresa + ", smtp " + smtp + " porta " + porta
					+ " email " + email1 + " senha " + senha + " ssl " + autentica);
			email.addTo("andrer19@hotmail.com");
			email.send();
			verifica = true;
		} catch (EmailException e) {
			Logger.getLogger(EnviaEmail.class.getName()).log(Level.SEVERE, null, e.getMessage());
		}
		return verifica;
	}
	

	public Boolean emailpedidovenda(String empresa, String smtp, String porta, String senha, String email1,
			boolean autentica, String arquivo, String pedido, String cliente, String emails) {
		Boolean verifica = false;
		try {
			MultiPartEmail email = new MultiPartEmail();
			StringBuilder sb = new StringBuilder("");
			String virgula = ",";

			// cria o anexo.
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(arquivo); // caminho da imagem
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription(pedido);
			attachment.setName(arquivo + ".pdf");

			email.setHostName(smtp);
			email.setSmtpPort(Integer.parseInt(porta));
			email.setAuthentication(email1, senha);
			email.setSSLOnConnect(autentica);

			String dados = emails;
			String[] tokens = dados.split(";");

			sb.append(
					"|-----------------------------------------------------------------------------------------------------------------| \n");
			sb.append(cliente + ", PEDIDO POR EMAIL NUMERO " + pedido + ", EMITIDO POR " + empresa + "\n");
			sb.append(
					"|-----------------------------------------------------------------------------------------------------------------| \n");
			sb.append(this.empresa.getRazao().trim() + " \n");
			sb.append(this.empresa.getEnder().trim() + ", " + this.empresa.getNum().trim() + " - "
					+ this.empresa.getBairro().trim() + " - " + this.empresa.getCidade().trim() + " - "
					+ this.empresa.getEstado().trim() + " \n");

			email.setFrom(email1);
			email.setSubject("PEDIDO DE VENDA NUMERO " + pedido);
			email.setMsg(sb.toString());
			email.attach(attachment);
			int linhas = tokens.length;
			String destinatario = "";
			for (String token : tokens) {
				destinatario = token;
			}
			
			email.addTo(destinatario);
			email.addCc(tokens);
			email.send();
			verifica = true;
		} catch (EmailException e) {
			Logger.getLogger(EnviaEmail.class.getName()).log(Level.SEVERE, null, e.getMessage());
		} catch (IllegalCharsetNameException x) {
			Logger.getLogger(EnviaEmail.class.getName()).log(Level.SEVERE, null, x.getMessage());
		}
		return verifica;
	}
	
	
	public Boolean emailordemservico(String empresa, String smtp, String porta, String senha, String email1,
			boolean autentica, String arquivo, String pedido, String cliente, String emails) {
		Boolean verifica = false;
		try {
			MultiPartEmail email = new MultiPartEmail();
			StringBuilder sb = new StringBuilder("");
			String virgula = ",";

			// cria o anexo.
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(arquivo); // caminho da imagem
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription(pedido);
			attachment.setName(arquivo + ".pdf");

			email.setHostName(smtp);
			email.setSmtpPort(Integer.parseInt(porta));
			email.setAuthentication(email1, senha);
			email.setSSLOnConnect(autentica);

			String dados = emails;
			String[] tokens = dados.split(";");

			sb.append(
					"|-----------------------------------------------------------------------------------------------------------------| \n");
			sb.append(cliente + ", ORDEM DE SERVIÇO POR EMAIL NUMERO " + pedido + ", EMITIDO POR " + empresa + "\n");
			sb.append(
					"|-----------------------------------------------------------------------------------------------------------------| \n");
			sb.append(this.empresa.getRazao().trim() + " \n");
			sb.append(this.empresa.getEnder().trim() + ", " + this.empresa.getNum().trim() + " - "
					+ this.empresa.getBairro().trim() + " - " + this.empresa.getCidade().trim() + " - "
					+ this.empresa.getEstado().trim() + " \n");

			email.setFrom(email1);
			email.setSubject("ORDEM DE SERVIÇO NUMERO " + pedido);
			email.setMsg(sb.toString());
			email.attach(attachment);
			int linhas = tokens.length;
			String destinatario = "";
			for (String token : tokens) {
				destinatario = token;
			}
			
			email.addTo(destinatario);
			email.addCc(tokens);
			email.send();
			verifica = true;
		} catch (EmailException e) {
			Logger.getLogger(EnviaEmail.class.getName()).log(Level.SEVERE, null, e.getMessage());
		} catch (IllegalCharsetNameException x) {
			Logger.getLogger(EnviaEmail.class.getName()).log(Level.SEVERE, null, x.getMessage());
		}
		return verifica;
	}


	

	// =================================================================================

	public List<Cademp> getCademps() {
		return cademps;
	}

	public void setCademps(List<Cademp> cademps) {
		this.cademps = cademps;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public Boolean getAutentica() {
		return autentica;
	}

	public void setAutentica(Boolean autentica) {
		this.autentica = autentica;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public Cademp getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Cademp empresa) {
		this.empresa = empresa;
	}

}
