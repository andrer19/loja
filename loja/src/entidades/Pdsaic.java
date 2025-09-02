package entidades;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Index;

@Entity
public class Pdsaic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Index(name = "idx_id")
	private Long idpdsaic;
	
	@Column(nullable=false,columnDefinition = "varchar(10)  default ''")
	@Index(name = "idx_documento")
	private String numdoc;
	
	@Column(nullable=false,columnDefinition = "varchar(255)  default ''")
	private String vendedor,contato, formpagto,serie,codemp,pedido;
	
	@Temporal(TemporalType.DATE)
	private Date emissao,vencto;
	
	@Column(columnDefinition="Double default 0")
	private Double vrmerc,vricm,vripi,vrtot,vrdesc,vrfrete,codpag,txent;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean liberado,status,liquidado;
	
	@Column(nullable=false,columnDefinition = "varchar(400)  default ''")
	private String OBS;

	@Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;

	@OneToOne
	@NotNull
	@JoinColumn(name="cliente")
	private Cadcli cliente;

	public Pdsaic() {
		codemp = "01";
		serie  = "";
		numdoc  = "";
		pedido = "";
		vendedor = "";
		contato  = "";
		formpagto = "";
		OBS   = "";
		vrmerc = 0.0;
		vricm = 0.0;
		vripi = 0.0;
		vrtot = 0.0;
		vrdesc = 0.0;
		vrfrete = 0.0;
		codpag = 0.0;
		txent  = 0.0;
		liberado = false;
		status = false;
		liquidado = false;
		sql_deleted = "F";
		
	}

	public Long getIdpdsaic() {
		return idpdsaic;
	}

	public void setIdpdsaic(Long idpdsaic) {
		this.idpdsaic = idpdsaic;
	}

	public String getCodemp() {
		return codemp;
	}

	public void setCodemp(String codemp) {
		this.codemp = codemp;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
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

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
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

	public String getOBS() {
		return OBS;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

	public Cadcli getCliente() {
		return cliente;
	}

	public void setCliente(Cadcli cliente) {
		this.cliente = cliente;
	}

	public Double getTxent() {
		return txent;
	}

	public void setTxent(Double txent) {
		this.txent = txent;
	}

	public String getFormpagto() {
		return formpagto;
	}

	public void setFormpagto(String formpagto) {
		this.formpagto = formpagto;
	}	

}
