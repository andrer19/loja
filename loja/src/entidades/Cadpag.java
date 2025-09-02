package entidades;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Cadpag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sql_rowid;
	private String codemp,codfor,numdoc,serie,hist,cheque,banco,codcus,parcela,
	chpre,bcopre,agepre,ctapre,titpre,obs,status,totparc,situacao,e5_fornec,e5_loja,E5_nomfor;
	@Column(nullable=false, columnDefinition = "TINYINT")
	private Boolean balancete;
	@Temporal(TemporalType.DATE)
    private Date emissao,vencto,dtbaixa,vcfluxo,datadev,datadig;
    private Double vrtot,vrbxa,vrsdo,vrjuros,abat; 
    
    @Column(nullable=false,columnDefinition ="ENUM('F','T')")
  	private String sql_deleted;

	public Long getSql_rowid() {
		return sql_rowid;
	}

	public void setSql_rowid(Long sql_rowid) {
		this.sql_rowid = sql_rowid;
	}

	public String getCodemp() {
		return codemp;
	}

	public void setCodemp(String codemp) {
		this.codemp = codemp;
	}

	public String getCodfor() {
		return codfor;
	}

	public void setCodfor(String codfor) {
		this.codfor = codfor;
	}

	public String getNumdoc() {
		return numdoc;
	}

	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getHist() {
		return hist;
	}

	public void setHist(String hist) {
		this.hist = hist;
	}

	public String getCheque() {
		return cheque;
	}

	public void setCheque(String cheque) {
		this.cheque = cheque;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCodcus() {
		return codcus;
	}

	public void setCodcus(String codcus) {
		this.codcus = codcus;
	}

	public String getParcela() {
		return parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public String getChpre() {
		return chpre;
	}

	public void setChpre(String chpre) {
		this.chpre = chpre;
	}

	public String getBcopre() {
		return bcopre;
	}

	public void setBcopre(String bcopre) {
		this.bcopre = bcopre;
	}

	public String getAgepre() {
		return agepre;
	}

	public void setAgepre(String agepre) {
		this.agepre = agepre;
	}

	public String getCtapre() {
		return ctapre;
	}

	public void setCtapre(String ctapre) {
		this.ctapre = ctapre;
	}

	public String getTitpre() {
		return titpre;
	}

	public void setTitpre(String titpre) {
		this.titpre = titpre;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotparc() {
		return totparc;
	}

	public void setTotparc(String totparc) {
		this.totparc = totparc;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getE5_fornec() {
		return e5_fornec;
	}

	public void setE5_fornec(String e5_fornec) {
		this.e5_fornec = e5_fornec;
	}

	public String getE5_loja() {
		return e5_loja;
	}

	public void setE5_loja(String e5_loja) {
		this.e5_loja = e5_loja;
	}

	public String getE5_nomfor() {
		return E5_nomfor;
	}

	public void setE5_nomfor(String e5_nomfor) {
		E5_nomfor = e5_nomfor;
	}

	public Boolean getBalancete() {
		return balancete;
	}

	public void setBalancete(Boolean balancete) {
		this.balancete = balancete;
	}

	public Date getEmissao() {
		return emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}

	public Date getVencto() {
		return vencto;
	}

	public void setVencto(Date vencto) {
		this.vencto = vencto;
	}

	public Date getDtbaixa() {
		return dtbaixa;
	}

	public void setDtbaixa(Date dtbaixa) {
		this.dtbaixa = dtbaixa;
	}

	public Date getVcfluxo() {
		return vcfluxo;
	}

	public void setVcfluxo(Date vcfluxo) {
		this.vcfluxo = vcfluxo;
	}

	public Date getDatadev() {
		return datadev;
	}

	public void setDatadev(Date datadev) {
		this.datadev = datadev;
	}

	public Date getDatadig() {
		return datadig;
	}

	public void setDatadig(Date datadig) {
		this.datadig = datadig;
	}

	public Double getVrtot() {
		return vrtot;
	}

	public void setVrtot(Double vrtot) {
		this.vrtot = vrtot;
	}

	public Double getVrbxa() {
		return vrbxa;
	}

	public void setVrbxa(Double vrbxa) {
		this.vrbxa = vrbxa;
	}

	public Double getVrsdo() {
		return vrsdo;
	}

	public void setVrsdo(Double vrsdo) {
		this.vrsdo = vrsdo;
	}

	public Double getVrjuros() {
		return vrjuros;
	}

	public void setVrjuros(Double vrjuros) {
		this.vrjuros = vrjuros;
	}

	public Double getAbat() {
		return abat;
	}

	public void setAbat(Double abat) {
		this.abat = abat;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

	
    
    
    
}
