package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Index;

@Entity
public class Municip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Index(name = "idx_id")
	private Long id;
	
	@Column(nullable=false,columnDefinition = "varchar(2)  default ''")
	private String uf,codigo;
	
	@Column(nullable=false,columnDefinition = "varchar(18)  default ''")
	private String nome_uf,sigla_uf;
	
	@Column(nullable=false,columnDefinition = "varchar(9)  default ''") 
	private String municipio,cep;
	
	@Column(nullable=false,columnDefinition = "varchar(30)  default ''")
	private String nome_mun;
	
	@Column(nullable=false,columnDefinition = "varchar(40)  default ''") 
	private String nome;
	
	@Column(nullable=false,columnDefinition = "varchar(7)  default ''") 
	private String cod_ibge;
	
	@Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;
	
	public Municip() {
		uf = "";
		codigo = "";
		nome_uf = "";
		sigla_uf = "";
		municipio = "";
		cep = "";
		nome_mun = "";
		nome = "";
		cod_ibge = "";
		sql_deleted = "F";
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome_uf() {
		return nome_uf;
	}

	public void setNome_uf(String nome_uf) {
		this.nome_uf = nome_uf;
	}

	public String getSigla_uf() {
		return sigla_uf;
	}

	public void setSigla_uf(String sigla_uf) {
		this.sigla_uf = sigla_uf;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNome_mun() {
		return nome_mun;
	}

	public void setNome_mun(String nome_mun) {
		this.nome_mun = nome_mun;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCod_ibge() {
		return cod_ibge;
	}

	public void setCod_ibge(String cod_ibge) {
		this.cod_ibge = cod_ibge;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

}
