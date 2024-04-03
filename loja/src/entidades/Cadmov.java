package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

@Entity
public class Cadmov {

	  	
	@Id
	@GeneratedValue
	@Index(name = "idx_id")
	private Long id;
	
	@Column(columnDefinition="Double(19,4)")
	private Double qtmov, qtant, qtatu;
	
	@Column(columnDefinition="Double(14,4)")
	private Double vrunit, vrtot;
	
	@Column(length=13)
	private String codpro;
	
	@Column(length=2)
	private String codemp, un;
 
	@Column(length=10)
	private String numdoc, item;
	
	@Column(length=3)
	private String serie;
	
	@Column(length=6)
	private String codforcli;
	
	@Column(length=70)
	private String descpro, descpro1;
	
	@Column(length=1)
	private String tpmov;
	
	@Column(length=4)
	private String locest;
	
	@Column(length=20)
	private String descricao;
	
	@Column(length=40)
	private String hist;
	
	@Column(length=9)
	private String tipoest;
	
	
	@Temporal(TemporalType.DATE)
	private Date dtmov;
	
	@Column(nullable = false, columnDefinition = "ENUM('F','T')")
	private String sql_deleted;

	public Cadmov() {
		qtmov = 0.0;
		qtant = 0.0;
		qtatu = 0.0;
		vrunit = 0.0;
		vrtot = 0.0;
		codpro = "";
		codemp = "01";
		un = "";
		numdoc = "";
		item = "";
		serie = "";
		codforcli = "";
		descpro = "";
		descpro1 = "";
		tpmov = "";
		locest = "";
		descricao = "";
		hist = "";
		tipoest = "";
		sql_deleted = "F";
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getQtmov() {
		return qtmov;
	}

	public void setQtmov(Double qtmov) {
		this.qtmov = qtmov;
	}

	public Double getQtant() {
		return qtant;
	}

	public void setQtant(Double qtant) {
		this.qtant = qtant;
	}

	public Double getQtatu() {
		return qtatu;
	}

	public void setQtatu(Double qtatu) {
		this.qtatu = qtatu;
	}

	public Double getVrunit() {
		return vrunit;
	}

	public void setVrunit(Double vrunit) {
		this.vrunit = vrunit;
	}

	public Double getVrtot() {
		return vrtot;
	}

	public void setVrtot(Double vrtot) {
		this.vrtot = vrtot;
	}

	public String getCodpro() {
		return codpro;
	}

	public void setCodpro(String codpro) {
		this.codpro = codpro;
	}

	public String getCodemp() {
		return codemp;
	}

	public void setCodemp(String codemp) {
		this.codemp = codemp;
	}

	public String getUn() {
		return un;
	}

	public void setUn(String un) {
		this.un = un;
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

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getCodforcli() {
		return codforcli;
	}

	public void setCodforcli(String codforcli) {
		this.codforcli = codforcli;
	}

	public String getDescpro() {
		return descpro;
	}

	public void setDescpro(String descpro) {
		this.descpro = descpro;
	}

	public String getDescpro1() {
		return descpro1;
	}

	public void setDescpro1(String descpro1) {
		this.descpro1 = descpro1;
	}

	public String getTpmov() {
		return tpmov;
	}

	public void setTpmov(String tpmov) {
		this.tpmov = tpmov;
	}

	public String getLocest() {
		return locest;
	}

	public void setLocest(String locest) {
		this.locest = locest;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getHist() {
		return hist;
	}

	public void setHist(String hist) {
		this.hist = hist;
	}

	public String getTipoest() {
		return tipoest;
	}

	public void setTipoest(String tipoest) {
		this.tipoest = tipoest;
	}

	public Date getDtmov() {
		return dtmov;
	}

	public void setDtmov(Date dtmov) {
		this.dtmov = dtmov;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

		
}
