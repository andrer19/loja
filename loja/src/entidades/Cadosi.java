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
public class Cadosi{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Index(name = "idx_id")
	private Long id;
	
	@Column(nullable=false,columnDefinition = "varchar(10)  default ''")
	private String numdoc;
	
	@Column(nullable=false,columnDefinition = "varchar(255)  default ''")
	private String descricao,item;
	
	@Column(nullable=false,columnDefinition="Double default 0")
	private Double vrtot;
	
	@Temporal(TemporalType.DATE)
	private Date emissao;
	
	@Column(nullable = false, columnDefinition = "ENUM('F','T')")
	private String sql_deleted;
	
	@OneToOne
	@JoinColumn(name="ordemc",nullable = false)
	private Cadosc ordemc;	

	public Cadosi() {
		numdoc = "";
		descricao = "";
		item = "";
		vrtot = 0.0;
		sql_deleted = "F";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumdoc() {
		return numdoc;
	}

	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Double getVrtot() {
		return vrtot;
	}

	public void setVrtot(Double vrtot) {
		this.vrtot = vrtot;
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

	public Cadosc getOrdemc() {
		return ordemc;
	}

	public void setOrdemc(Cadosc ordemc) {
		this.ordemc = ordemc;
	}

	
}
