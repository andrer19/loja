package entidades;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

@Entity
public class Pdentc {
	
	@Id
	@GeneratedValue
	@Index(name = "idx_id")
	private Long idpdentc;
	
	@Column(nullable=false,columnDefinition = "varchar(10)  default ''")
	@Index(name = "idx_documento")
	private String numdoc;
	
	@Column(nullable=false,columnDefinition = "varchar(1)  default ''")
	private String conf;
	
	@Column(nullable=false,columnDefinition = "varchar(2)  default ''")
	private String codemp,c7_loja;
	
	@Column(nullable=false,columnDefinition = "varchar(10)  default ''")
	private String pedido,numrev;
	
	@Column(nullable=false,columnDefinition = "varchar(3)  default ''")
	private String serie, serien;
	
	@Column(nullable=false,columnDefinition = "varchar(100)  default ''")
	private String condpag;
	
	@Column(nullable=false,columnDefinition = "varchar(6)  default ''")
	private String reqcomp, nota, vendedor,c7_fornece,contato;
	
	@Temporal(TemporalType.DATE)
	private Date emissao,vencto,fechado;
	
	@Column(nullable=false,columnDefinition = "Double default 0")
	private Double vrmerc,vricm,vripi,vrtot,vrdesc,vrfrete,codpag;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean liberado,status,liquidado;
	
	@Column(nullable=false,columnDefinition = "varchar(400)  default ''")
	private String obs;

	@Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;

	@OneToOne 
	@JoinColumn(name="fornecedor", nullable = false)
	private Cadfor forn;
	
	public Pdentc() {
		conf = "";
		codemp = "01";
		c7_loja = "";
		numdoc = "";
		pedido = "";
		numrev = "";
		serie = "";
		serien = "";
		condpag = "";
		reqcomp = "";
		nota = "";
		vendedor = "";
		contato = "";
		c7_fornece = "";
		obs = "";
		vrmerc = 0.0;
		vricm = 0.0;
		vripi = 0.0;
		vrtot = 0.0;
		vrdesc = 0.0;
		vrfrete = 0.0;
		codpag = 0.0;
		liberado = false;
		status  = false;
		liquidado  = false;
		sql_deleted = "F";
		
	}

	public Long getIdpdentc() {
		return idpdentc;
	}

	public void setIdpdentc(Long idpdentc) {
		this.idpdentc = idpdentc;
	}

	public String getConf() {
		return conf;
	}

	public void setConf(String conf) {
		this.conf = conf;
	}

	public String getCodemp() {
		return codemp;
	}

	public void setCodemp(String codemp) {
		this.codemp = codemp;
	}

	public String getC7_loja() {
		return c7_loja;
	}

	public void setC7_loja(String c7_loja) {
		this.c7_loja = c7_loja;
	}

	public String getNumdoc() {
		return numdoc;
	}

	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getNumrev() {
		return numrev;
	}

	public void setNumrev(String numrev) {
		this.numrev = numrev;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getSerien() {
		return serien;
	}

	public void setSerien(String serien) {
		this.serien = serien;
	}

	public String getCondpag() {
		return condpag;
	}

	public void setCondpag(String condpag) {
		this.condpag = condpag;
	}

	public String getReqcomp() {
		return reqcomp;
	}

	public void setReqcomp(String reqcomp) {
		this.reqcomp = reqcomp;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getC7_fornece() {
		return c7_fornece;
	}

	public void setC7_fornece(String c7_fornece) {
		this.c7_fornece = c7_fornece;
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

	public Date getFechado() {
		return fechado;
	}

	public void setFechado(Date fechado) {
		this.fechado = fechado;
	}

	public Double getVrmerc() {
		return vrmerc;
	}

	public void setVrmerc(Double vrmerc) {
		this.vrmerc = vrmerc;
	}

	public Double getVricm() {
		return vricm;
	}

	public void setVricm(Double vricm) {
		this.vricm = vricm;
	}

	public Double getVripi() {
		return vripi;
	}

	public void setVripi(Double vripi) {
		this.vripi = vripi;
	}

	public Double getVrtot() {
		return vrtot;
	}

	public void setVrtot(Double vrtot) {
		this.vrtot = vrtot;
	}

	public Double getVrdesc() {
		return vrdesc;
	}

	public void setVrdesc(Double vrdesc) {
		this.vrdesc = vrdesc;
	}

	public Double getVrfrete() {
		return vrfrete;
	}

	public void setVrfrete(Double vrfrete) {
		this.vrfrete = vrfrete;
	}

	public Double getCodpag() {
		return codpag;
	}

	public void setCodpag(Double codpag) {
		this.codpag = codpag;
	}

	public Boolean getLiberado() {
		return liberado;
	}

	public void setLiberado(Boolean liberado) {
		this.liberado = liberado;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getLiquidado() {
		return liquidado;
	}

	public void setLiquidado(Boolean liquidado) {
		this.liquidado = liquidado;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

	public Cadfor getForn() {
		return forn;
	}

	public void setForn(Cadfor forn) {
		this.forn = forn;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
	
	
		

}
