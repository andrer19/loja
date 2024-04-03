package entidades;

import javax.persistence.Column;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Logusu {
	
	@Id
	@GeneratedValue
	private Long sql_rowid;
	
	private Double recno;
	private String alias;
	private String usuario;
	private Date data;
	private String hora;
	private String texto;
	private String ip;
	private String obs;
	private Long id1;
	

	public Long getSql_rowid() {
		return sql_rowid;
	}

	public void setSql_rowid(Long sql_rowid) {
		this.sql_rowid = sql_rowid;
	}

	@Column(nullable = false, columnDefinition = "ENUM('F','T')")
	private String sql_deleted;

	public Double getRecno() {
		return recno;
	}

	public void setRecno(Double recno) {
		this.recno = recno;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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
	
	public Long getId1() {
		return id1;
	}

	public void setId1(Long id1) {
		this.id1 = id1;
	}

	public Logusu() {
		recno = 0.0;
		alias = "";
		usuario = "";
		hora = "";
		texto = "";
		ip = "";
		obs = "";
		sql_deleted="F";
	}
}
