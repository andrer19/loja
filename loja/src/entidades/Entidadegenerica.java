package entidades;

import java.util.Date;

import javax.persistence.Column;

public class Entidadegenerica{

	public String contador, codigo, numdoc, desc, codcfop, desccfop, 
	codclifor, descclifor,status,hora, obs,emitente,sql_deleted;
	public Long sql_rowid;
	
	@Column(columnDefinition = "TINYINT(1)")
	Boolean fatura, relatorio;
	
	Double valortotal, vrunitario, quantidade, picm, vricm, pipi, vripi, custo,refeicao,
	taxi,correios,telefone,outras, estatual, unitcompra, totcompra, unitvenda, totvenda, qtdesaida, totlucro;
	
	Date data;

	public Entidadegenerica(){
			
		contador = "";
		codigo = "";
		numdoc = "";
		desc = "";
		status = "";
		hora = "";
		obs = "";
		emitente = "";
		sql_deleted = "";
		custo = 0.0;
		fatura = false;
		relatorio = false;
		valortotal = 0.0;
		vrunitario = 0.0;
		quantidade = 0.0;
		picm = 0.0;
		vricm  = 0.0;
		pipi = 0.0;
		vripi = 0.0;
		refeicao = 0.0;
		taxi = 0.0;
		correios = 0.0;
		telefone = 0.0;
		outras = 0.0;
		estatual = 0.0;
		unitcompra = 0.0;
		totcompra = 0.0;
		unitvenda = 0.0;
		totvenda = 0.0;
		qtdesaida = 0.0;
		totlucro = 0.0;
	
	}

	public String getContador() {
		return contador;
	}

	public void setContador(String contador) {
		this.contador = contador;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNumdoc() {
		return numdoc;
	}

	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCodcfop() {
		return codcfop;
	}

	public void setCodcfop(String codcfop) {
		this.codcfop = codcfop;
	}

	public String getDesccfop() {
		return desccfop;
	}

	public void setDesccfop(String desccfop) {
		this.desccfop = desccfop;
	}

	public String getCodclifor() {
		return codclifor;
	}

	public void setCodclifor(String codclifor) {
		this.codclifor = codclifor;
	}

	public String getDescclifor() {
		return descclifor;
	}

	public void setDescclifor(String descclifor) {
		this.descclifor = descclifor;
	}

	public long getSql_rowid() {
		return sql_rowid;
	}

	public void setSql_rowid(long sql_rowid) {
		this.sql_rowid = sql_rowid;
	}

	public Boolean getFatura() {
		return fatura;
	}

	public void setFatura(Boolean fatura) {
		this.fatura = fatura;
	}

	public Double getValortotal() {
		return valortotal;
	}

	public void setValortotal(Double valortotal) {
		this.valortotal = valortotal;
	}

	public Double getVrunitario() {
		return vrunitario;
	}

	public void setVrunitario(Double vrunitario) {
		this.vrunitario = vrunitario;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPicm() {
		return picm;
	}

	public void setPicm(Double picm) {
		this.picm = picm;
	}

	public Double getVricm() {
		return vricm;
	}

	public void setVricm(Double vricm) {
		this.vricm = vricm;
	}

	public Double getPipi() {
		return pipi;
	}

	public void setPipi(Double pipi) {
		this.pipi = pipi;
	}

	public Double getVripi() {
		return vripi;
	}

	public void setVripi(Double vripi) {
		this.vripi = vripi;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public Boolean getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Boolean relatorio) {
		this.relatorio = relatorio;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getEmitente() {
		return emitente;
	}

	public void setEmitente(String emitente) {
		this.emitente = emitente;
	}

	public Double getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Double refeicao) {
		this.refeicao = refeicao;
	}

	public Double getTaxi() {
		return taxi;
	}

	public void setTaxi(Double taxi) {
		this.taxi = taxi;
	}

	public Double getCorreios() {
		return correios;
	}

	public void setCorreios(Double correios) {
		this.correios = correios;
	}

	public Double getTelefone() {
		return telefone;
	}

	public void setTelefone(Double telefone) {
		this.telefone = telefone;
	}

	public Double getOutras() {
		return outras;
	}

	public void setOutras(Double outras) {
		this.outras = outras;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

	public void setSql_rowid(Long sql_rowid) {
		this.sql_rowid = sql_rowid;
	}

	public Double getEstatual() {
		return estatual;
	}

	public void setEstatual(Double estatual) {
		this.estatual = estatual;
	}

	public Double getUnitcompra() {
		return unitcompra;
	}

	public void setUnitcompra(Double unitcompra) {
		this.unitcompra = unitcompra;
	}

	public Double getTotcompra() {
		return totcompra;
	}

	public void setTotcompra(Double totcompra) {
		this.totcompra = totcompra;
	}

	public Double getUnitvenda() {
		return unitvenda;
	}

	public void setUnitvenda(Double unitvenda) {
		this.unitvenda = unitvenda;
	}

	public Double getTotvenda() {
		return totvenda;
	}

	public void setTotvenda(Double totvenda) {
		this.totvenda = totvenda;
	}

	public Double getQtdesaida() {
		return qtdesaida;
	}

	public void setQtdesaida(Double qtdesaida) {
		this.qtdesaida = qtdesaida;
	}

	public Double getTotlucro() {
		return totlucro;
	}

	public void setTotlucro(Double totlucro) {
		this.totlucro = totlucro;
	}

	
	
	
	
}
