package entidades;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Sz1010 {
	
	@Id
	@GeneratedValue
    private Long sql_rowid;
	private String z1_filial,z1_num,z1_serie,z1_doc,z1_fornec,z1_loja,z1_nomefor,
	z1_certif,z1_obs,z1_hora,z1_codpro,z1_descpro,z1_razao;
	@Column(nullable=false, columnDefinition = "TINYINT")
	private Boolean ETIQ;
    private Date z1_data;
    private Double z1_qtde,z1_qtsai,z1_qtchapa,z1_saldo;
    
    
    @Column(nullable=false,columnDefinition ="ENUM('F','T')")
  	private String sql_deleted;


	public Long getSql_rowid() {
		return sql_rowid;
	}


	public void setSql_rowid(Long sql_rowid) {
		this.sql_rowid = sql_rowid;
	}


	public String getZ1_filial() {
		return z1_filial;
	}


	public void setZ1_filial(String z1_filial) {
		this.z1_filial = z1_filial;
	}


	public String getZ1_num() {
		return z1_num;
	}


	public void setZ1_num(String z1_num) {
		this.z1_num = z1_num;
	}


	public String getZ1_serie() {
		return z1_serie;
	}


	public void setZ1_serie(String z1_serie) {
		this.z1_serie = z1_serie;
	}


	public String getZ1_doc() {
		return z1_doc;
	}


	public void setZ1_doc(String z1_doc) {
		this.z1_doc = z1_doc;
	}


	public String getZ1_fornec() {
		return z1_fornec;
	}


	public void setZ1_fornec(String z1_fornec) {
		this.z1_fornec = z1_fornec;
	}


	public String getZ1_loja() {
		return z1_loja;
	}


	public void setZ1_loja(String z1_loja) {
		this.z1_loja = z1_loja;
	}


	public String getZ1_nomefor() {
		return z1_nomefor;
	}


	public void setZ1_nomefor(String z1_nomefor) {
		this.z1_nomefor = z1_nomefor;
	}


	public String getZ1_certif() {
		return z1_certif;
	}


	public void setZ1_certif(String z1_certif) {
		this.z1_certif = z1_certif;
	}


	public String getZ1_obs() {
		return z1_obs;
	}


	public void setZ1_obs(String z1_obs) {
		this.z1_obs = z1_obs;
	}


	public String getZ1_hora() {
		return z1_hora;
	}


	public void setZ1_hora(String z1_hora) {
		this.z1_hora = z1_hora;
	}


	public String getZ1_codpro() {
		return z1_codpro;
	}


	public void setZ1_codpro(String z1_codpro) {
		this.z1_codpro = z1_codpro;
	}


	public String getZ1_descpro() {
		return z1_descpro;
	}


	public void setZ1_descpro(String z1_descpro) {
		this.z1_descpro = z1_descpro;
	}


	public String getZ1_razao() {
		return z1_razao;
	}


	public void setZ1_razao(String z1_razao) {
		this.z1_razao = z1_razao;
	}


	public Boolean getETIQ() {
		return ETIQ;
	}


	public void setETIQ(Boolean eTIQ) {
		ETIQ = eTIQ;
	}


	public Date getZ1_data() {
		return z1_data;
	}


	public void setZ1_data(Date z1_data) {
		this.z1_data = z1_data;
	}


	public Double getZ1_qtde() {
		return z1_qtde;
	}


	public void setZ1_qtde(Double z1_qtde) {
		this.z1_qtde = z1_qtde;
	}


	public Double getZ1_qtsai() {
		return z1_qtsai;
	}


	public void setZ1_qtsai(Double z1_qtsai) {
		this.z1_qtsai = z1_qtsai;
	}


	public Double getZ1_qtchapa() {
		return z1_qtchapa;
	}


	public void setZ1_qtchapa(Double z1_qtchapa) {
		this.z1_qtchapa = z1_qtchapa;
	}


	public Double getZ1_saldo() {
		return z1_saldo;
	}


	public void setZ1_saldo(Double z1_saldo) {
		this.z1_saldo = z1_saldo;
	}


	public String getSql_deleted() {
		return sql_deleted;
	}


	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}
    
    
	
    
    
}
