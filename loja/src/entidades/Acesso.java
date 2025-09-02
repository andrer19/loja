package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Index;

@Entity
public class Acesso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Index(name = "idx_id")
	private Long id;
	
	
	@Column(nullable = false,columnDefinition = "varchar(255)  default ''")
	@Index(name = "idx_modulo")
	private String modulo;
	
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean acesso, inclusao, alteracao, exclusao, nivel4, nivel5, nivel6, nivel7, nivel8;

	@Column(nullable = false, columnDefinition = "ENUM('F','T')")
	private String sql_deleted;
	
	@OneToOne
	@Index(name = "idx_usuario")
	private Usuario usuario;

	@OneToOne
	private Rotinas rotina;

	public Acesso() {
		acesso = false;
		inclusao = false;
		alteracao = false;
		exclusao = false;
		nivel4 = false;
		nivel5 = false;
		nivel6 = false;
		nivel7 = false;
		nivel8 = false;
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

	public Boolean getAcesso() {
		return acesso;
	}

	public void setAcesso(Boolean acesso) {
		this.acesso = acesso;
	}

	public Boolean getInclusao() {
		return inclusao;
	}

	public void setInclusao(Boolean inclusao) {
		this.inclusao = inclusao;
	}

	public Boolean getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(Boolean alteracao) {
		this.alteracao = alteracao;
	}

	public Boolean getExclusao() {
		return exclusao;
	}

	public void setExclusao(Boolean exclusao) {
		this.exclusao = exclusao;
	}

	public Boolean getNivel4() {
		return nivel4;
	}

	public void setNivel4(Boolean nivel4) {
		this.nivel4 = nivel4;
	}

	public Boolean getNivel5() {
		return nivel5;
	}

	public void setNivel5(Boolean nivel5) {
		this.nivel5 = nivel5;
	}

	public Boolean getNivel6() {
		return nivel6;
	}

	public void setNivel6(Boolean nivel6) {
		this.nivel6 = nivel6;
	}

	public Boolean getNivel7() {
		return nivel7;
	}

	public void setNivel7(Boolean nivel7) {
		this.nivel7 = nivel7;
	}

	public Boolean getNivel8() {
		return nivel8;
	}

	public void setNivel8(Boolean nivel8) {
		this.nivel8 = nivel8;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Rotinas getRotina() {
		return rotina;
	}

	public void setRotina(Rotinas rotina) {
		this.rotina = rotina;
	}

}
