package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cadfun {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcadfun;
	private String FUNCAO;
	private Double HSTOTAL,HSUTIL,HSRES,HSDISPON;
	
	 @Column(nullable=false,columnDefinition ="ENUM('F','T')")
		private String sql_deleted;
	
	
	public Long getIdcadfun() {
		return idcadfun;
	}
	public void setIdcadfun(Long idcadfun) {
		this.idcadfun = idcadfun;
	}
	public String getFUNCAO() {
		return FUNCAO;
	}
	public void setFUNCAO(String fUNCAO) {
		FUNCAO = fUNCAO;
	}
	public Double getHSTOTAL() {
		return HSTOTAL;
	}
	public void setHSTOTAL(Double hSTOTAL) {
		HSTOTAL = hSTOTAL;
	}
	public Double getHSUTIL() {
		return HSUTIL;
	}
	public void setHSUTIL(Double hSUTIL) {
		HSUTIL = hSUTIL;
	}
	public Double getHSRES() {
		return HSRES;
	}
	public void setHSRES(Double hSRES) {
		HSRES = hSRES;
	}
	public Double getHSDISPON() {
		return HSDISPON;
	}
	public void setHSDISPON(Double hSDISPON) {
		HSDISPON = hSDISPON;
	}
	public String getSql_deleted() {
		return sql_deleted;
	}
	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}
	
	
	


}
