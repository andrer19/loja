package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class Cademp {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false,columnDefinition = "varchar(100)  default ''")
	private String site,email, smtp;
	
	@Column(nullable=false,columnDefinition = "varchar(2)  default ''")
	private String codemp;
	
	@Column(nullable=false,columnDefinition = "varchar(20)  default ''")
	private String bairro;
	
	@Column(nullable=false,columnDefinition = "varchar(30)  default ''")
	private String cidade,inscmun,cnpj,ie;
	
	@Column(nullable=false,columnDefinition = "varchar(50)  default ''")
	private String razao,ender,fone,estado,senha;
	
	@Column(nullable=false,columnDefinition = "varchar(7)  default ''")
	private String ibge,porta;
	
	@Column(nullable=false,columnDefinition = "varchar(10)  default ''")
	private String num;
	
	@Column(nullable=false,columnDefinition = "varchar(9)  default ''")
	private String cep;
	
	@Column(nullable=false,columnDefinition = "varchar(60)  default ''")
	private String fantasia;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean autenticacaossl; 
	
	@Column(nullable = false, columnDefinition = "ENUM('F','T')")
	private String sql_deleted;
	
	public Cademp() {
		site = "";
		email = "";
		codemp = "01";
		bairro = "";
		cidade = "";
		inscmun = "";
		cnpj = "";
		ie = "";
		razao = "";
		ender = "";
		fone = "";
		estado = "";
		ibge = "";
		num = "";
		cep = "";
		fantasia = "";
		smtp = "";
		senha = "";
		porta = "";
		autenticacaossl = false;
		sql_deleted = "F";
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCodemp() {
		return codemp;
	}

	public void setCodemp(String codemp) {
		this.codemp = codemp;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getInscmun() {
		return inscmun;
	}

	public void setInscmun(String inscmun) {
		this.inscmun = inscmun;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getEnder() {
		return ender;
	}

	public void setEnder(String ender) {
		this.ender = ender;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public Boolean getAutenticacaossl() {
		return autenticacaossl;
	}

	public void setAutenticacaossl(Boolean autenticacaossl) {
		this.autenticacaossl = autenticacaossl;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}	
	

}
