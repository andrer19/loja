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
public class Pdsaii {
	
	@Id
	@GeneratedValue
	@Index(name = "idx_id")
	private Long idpdsaii;
	
	@Column(nullable=false,columnDefinition = "varchar(10)  default ''")
	@Index(name = "idx_documento")
	private String pedido;
	
	@Column(nullable=false,columnDefinition = "varchar(20)  default ''")
	@Index(name = "idx_produto")
	private String produto;
	
	@Column(nullable=false,columnDefinition = "varchar(255)  default ''")
	private String descpro,codemp, un,item;
	
	@Column(nullable=false,columnDefinition="Double default 0")
	private Double quantidade,unitario,vrtot,vrdesc;
	
	@Column(nullable=false,columnDefinition = "varchar(400)  default ''")
	private String obs;
	
	@Temporal(TemporalType.DATE)
	private Date dtatend,prazo, emissao;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean troca;
	
	@Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;

	@OneToOne 
	@NotNull
	@JoinColumn(name="pedidoc")
	private Pdsaic pedc;
	 
	 public Pdsaii() {
		 codemp = "01";
		 item = "";
		 un = "";
		 pedido  = "";
		 produto  = "";
		 obs = "";
		 descpro = "";
		 quantidade = 0.0;
         unitario = 0.0;
         vrtot = 0.0;
         vrdesc = 0.0;
         troca = false;
         sql_deleted = "F";
		 
		 
	 }

	public Long getIdpdsaii() {
		return idpdsaii;
	}

	public void setIdpdsaii(Long idpdsaii) {
		this.idpdsaii = idpdsaii;
	}

	public String getCodemp() {
		return codemp;
	}

	public void setCodemp(String codemp) {
		this.codemp = codemp;
	}

	public String getUn() {
		return un;
	}

	public void setUn(String un) {
		this.un = un;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getUnitario() {
		return unitario;
	}

	public void setUnitario(Double unitario) {
		this.unitario = unitario;
	}

	public Double getVrtot() {
		return vrtot;
	}

	public void setVrtot(Double vrtot) {
		this.vrtot = vrtot;
	}

	public Double getVrdesc() {
		return vrdesc;
	}

	public void setVrdesc(Double vrdesc) {
		this.vrdesc = vrdesc;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getDescpro() {
		return descpro;
	}

	public void setDescpro(String descpro) {
		this.descpro = descpro;
	}

	public Date getDtatend() {
		return dtatend;
	}

	public void setDtatend(Date dtatend) {
		this.dtatend = dtatend;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

	public Pdsaic getPedc() {
		return pedc;
	}

	public void setPedc(Pdsaic pedc) {
		this.pedc = pedc;
	}

	public Date getEmissao() {
		return emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}

	public Boolean getTroca() {
		return troca;
	}

	public void setTroca(Boolean troca) {
		this.troca = troca;
	}

	
}
