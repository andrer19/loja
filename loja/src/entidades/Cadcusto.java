package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cadcusto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcusto;
	private int baixa;
	private String codcusto, desccusto, tipo, conta,codemp;
	
	@Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;

	public Long getIdcusto() {
		return idcusto;
	}

	public void setIdcusto(Long idcusto) {
		this.idcusto = idcusto;
	}

	public int getBaixa() {
		return baixa;
	}

	public void setBaixa(int baixa) {
		this.baixa = baixa;
	}

	public String getCodcusto() {
		return codcusto;
	}

	public void setCodcusto(String codcusto) {
		this.codcusto = codcusto;
	}

	public String getDesccusto() {
		return desccusto;
	}

	public void setDesccusto(String desccusto) {
		this.desccusto = desccusto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getCodemp() {
		return codemp;
	}

	public void setCodemp(String codemp) {
		this.codemp = codemp;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

	
	
}
