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
public class Nfenti {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idnfenti;
	
	private String codpro,descpro,un,terceiro,codfor,numdoc,item,pedido,nforigem,
	lote,natoper,cod_icms,cod_ipi,cod_pis,cod_cofins,serie,pipi,itemped,custo;
	private Double qtde,unitario,vrtot,vripi,vrdesc,saldo,vricm,icmdif,ipidif,baseicm,vrfrete,
	vrseg,despacess,picm,qtdev,reduzicm;
	
	@Temporal(TemporalType.DATE)
	private Date entrega,prazo,emissao;
	
	@Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;

	public Long getIdnfenti() {
		return idnfenti;
	}

	public void setIdnfenti(Long idnfenti) {
		this.idnfenti = idnfenti;
	}

	public String getCodpro() {
		return codpro;
	}

	public void setCodpro(String codpro) {
		this.codpro = codpro;
	}

	public String getDescpro() {
		return descpro;
	}

	public void setDescpro(String descpro) {
		this.descpro = descpro;
	}

	public String getUn() {
		return un;
	}

	public void setUn(String un) {
		this.un = un;
	}

	public String getTerceiro() {
		return terceiro;
	}

	public void setTerceiro(String terceiro) {
		this.terceiro = terceiro;
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

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getNforigem() {
		return nforigem;
	}

	public void setNforigem(String nforigem) {
		this.nforigem = nforigem;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getNatoper() {
		return natoper;
	}

	public void setNatoper(String natoper) {
		this.natoper = natoper;
	}

	public String getCod_icms() {
		return cod_icms;
	}

	public void setCod_icms(String cod_icms) {
		this.cod_icms = cod_icms;
	}

	public String getCod_ipi() {
		return cod_ipi;
	}

	public void setCod_ipi(String cod_ipi) {
		this.cod_ipi = cod_ipi;
	}

	public String getCod_pis() {
		return cod_pis;
	}

	public void setCod_pis(String cod_pis) {
		this.cod_pis = cod_pis;
	}

	public String getCod_cofins() {
		return cod_cofins;
	}

	public void setCod_cofins(String cod_cofins) {
		this.cod_cofins = cod_cofins;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getPipi() {
		return pipi;
	}

	public void setPipi(String pipi) {
		this.pipi = pipi;
	}

	public String getItemped() {
		return itemped;
	}

	public void setItemped(String itemped) {
		this.itemped = itemped;
	}

	public Double getQtde() {
		return qtde;
	}

	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}

	public Double getUnitario() {
		return unitario;
	}

	public void setUnitario(Double unitario) {
		this.unitario = unitario;
	}

	public Double getVrtot() {
		return vrtot;
	}

	public void setVrtot(Double vrtot) {
		this.vrtot = vrtot;
	}

	public Double getVripi() {
		return vripi;
	}

	public void setVripi(Double vripi) {
		this.vripi = vripi;
	}

	public Double getVrdesc() {
		return vrdesc;
	}

	public void setVrdesc(Double vrdesc) {
		this.vrdesc = vrdesc;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getVricm() {
		return vricm;
	}

	public void setVricm(Double vricm) {
		this.vricm = vricm;
	}

	public Double getIcmdif() {
		return icmdif;
	}

	public void setIcmdif(Double icmdif) {
		this.icmdif = icmdif;
	}

	public Double getIpidif() {
		return ipidif;
	}

	public void setIpidif(Double ipidif) {
		this.ipidif = ipidif;
	}

	public Double getBaseicm() {
		return baseicm;
	}

	public void setBaseicm(Double baseicm) {
		this.baseicm = baseicm;
	}

	public Double getVrfrete() {
		return vrfrete;
	}

	public void setVrfrete(Double vrfrete) {
		this.vrfrete = vrfrete;
	}

	public Double getVrseg() {
		return vrseg;
	}

	public void setVrseg(Double vrseg) {
		this.vrseg = vrseg;
	}

	public Double getDespacess() {
		return despacess;
	}

	public void setDespacess(Double despacess) {
		this.despacess = despacess;
	}

	public Double getPicm() {
		return picm;
	}

	public void setPicm(Double picm) {
		this.picm = picm;
	}

	public Double getQtdev() {
		return qtdev;
	}

	public void setQtdev(Double qtdev) {
		this.qtdev = qtdev;
	}

	public Double getReduzicm() {
		return reduzicm;
	}

	public void setReduzicm(Double reduzicm) {
		this.reduzicm = reduzicm;
	}

	public Date getEntrega() {
		return entrega;
	}

	public void setEntrega(Date entrega) {
		this.entrega = entrega;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public Date getEmissao() {
		return emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

	public String getCusto() {
		return custo;
	}

	public void setCusto(String custo) {
		this.custo = custo;
	}

	

	
	
	
	
	

}
