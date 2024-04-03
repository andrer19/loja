package entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cadbco {

	@Id
	@GeneratedValue
	private Long sql_rowid;
	private String codemp,codbco,descbco,agencia,numcta,praca,numbco,numche;
	private Double sdoatual;
	private Boolean fluxo;
	
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

	public String getCodbco() {
		return codbco;
	}

	public void setCodbco(String codbco) {
		this.codbco = codbco;
	}

	public String getDescbco() {
		return descbco;
	}

	public void setDescbco(String descbco) {
		this.descbco = descbco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumcta() {
		return numcta;
	}

	public void setNumcta(String numcta) {
		this.numcta = numcta;
	}

	public String getPraca() {
		return praca;
	}

	public void setPraca(String praca) {
		this.praca = praca;
	}

	public String getNumbco() {
		return numbco;
	}

	public void setNumbco(String numbco) {
		this.numbco = numbco;
	}

	public String getNumche() {
		return numche;
	}

	public void setNumche(String numche) {
		this.numche = numche;
	}

	public Double getSdoatual() {
		return sdoatual;
	}

	public void setSdoatual(Double sdoatual) {
		this.sdoatual = sdoatual;
	}

	public Boolean getFluxo() {
		return fluxo;
	}

	public void setFluxo(Boolean fluxo) {
		this.fluxo = fluxo;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}
	 
	 
	
	
	
	
	
	
	
	
	
	
}