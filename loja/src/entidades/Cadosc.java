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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

@Entity
public class Cadosc extends Entidadegenerica{
	
	@Id
	@GeneratedValue
	@Index(name = "idx_id")
	private Long id;
	
	@Column(nullable=false,columnDefinition = "varchar(10)  default ''")
	@Index(name = "idx_documento")
	private String numdoc;
	
	@Column(nullable=false,columnDefinition = "varchar(255)  default ''")
	private String formpagto, modelo,imei;
	
	@Column(nullable=false,columnDefinition = "varchar(400)  default ''")
	private String obs,obscliente;
	
	@Column(nullable=false,columnDefinition="Double default 0")
	private Double vrtot;
	
	@Temporal(TemporalType.DATE)
	private Date emissao;
	
	@Column(nullable = false, columnDefinition = "ENUM('F','T')")
	private String sql_deleted;
	
	@OneToOne
	@JoinColumn(name="cliente",nullable = false)
	private Cadcli cliente;	

	public Cadosc() {
		numdoc = "";
		formpagto = "";
		obs = "";
		obscliente = "";
		modelo = "";
		imei = "";
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

	public String getFormpagto() {
		return formpagto;
	}

	public void setFormpagto(String formpagto) {
		this.formpagto = formpagto;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getObscliente() {
		return obscliente;
	}

	public void setObscliente(String obscliente) {
		this.obscliente = obscliente;
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

	public Cadcli getCliente() {
		return cliente;
	}

	public void setCliente(Cadcli cliente) {
		this.cliente = cliente;
	}

}
