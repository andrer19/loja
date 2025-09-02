package entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Index;

@Entity
public class Modulos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Index(name = "idx_id")
	private Long idmodulo;
	
	@Column(nullable = false,columnDefinition = "varchar(255)  default ''")
	@Index(name = "idx_modulo")
	private String Modulo;
	
	@Column(nullable = false,columnDefinition = "varchar(255)  default ''")
	@Index(name = "idx_pagina")
	private String pagina;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean mostra;
	
	@Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;
	
	
	public Modulos() {
		Modulo = "";
		pagina = "";
		mostra = false;
		sql_deleted = "F";
		
		
	}
	
	
	public Long getIdmodulo() {
		return idmodulo;
	}
	public void setIdmodulo(Long idmodulo) {
		this.idmodulo = idmodulo;
	}
	public String getModulo() {
		return Modulo;
	}
	public void setModulo(String modulo) {
		Modulo = modulo;
	}
	public Boolean getMostra() {
		return mostra;
	}
	public void setMostra(Boolean mostra) {
		this.mostra = mostra;
	}
	public String getPagina() {
		return pagina;
	}
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	public String getSql_deleted() {
		return sql_deleted;
	}
	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}
	
	
	
}