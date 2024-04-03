package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Rotinas {

	@Id
	@GeneratedValue
	private Long id;
	private String modulo,pagina;
	
	@Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;
	
	public Rotinas() {
		modulo = "";
		pagina = "";
		sql_deleted = "F";
		
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
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
