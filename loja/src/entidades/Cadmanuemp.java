package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

@Entity
public class Cadmanuemp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Index(name = "idx_id")
	private Long id;

	@Column(nullable = false, columnDefinition = "varchar(255)  default ''")
	private String numpedc, reqcom, numpedv, orcven, numnf, numlot, descemp, baixa, recebi, normas, inspgui, ordemprod,
			ordememb, inspprocesso, etiqpro, codcli, codfor, codtrans;
	
	@Column(nullable = false, columnDefinition = "int(2)  default '0'")
	private int decqtde,decunit;

	@Column(nullable = false, columnDefinition = "ENUM('F','T')")
	private String sql_deleted;

	public Cadmanuemp() {
		numpedc = "";
		reqcom = "";
		numpedv = "";
		orcven = "";
		numnf = "";
		numlot = "";
		descemp = "";
		baixa = "";
		recebi = "";
		normas = "";
		inspgui = "";
		ordemprod = "";
		ordememb = "";
		inspprocesso = "";
		etiqpro = "";
		codcli = "";
		codfor = "";
		codtrans = "";
		decqtde = 0;
		decunit = 2;
		sql_deleted = "F";

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumpedc() {
		return numpedc;
	}

	public void setNumpedc(String numpedc) {
		this.numpedc = numpedc;
	}

	public String getReqcom() {
		return reqcom;
	}

	public void setReqcom(String reqcom) {
		this.reqcom = reqcom;
	}

	public String getNumpedv() {
		return numpedv;
	}

	public void setNumpedv(String numpedv) {
		this.numpedv = numpedv;
	}

	public String getOrcven() {
		return orcven;
	}

	public void setOrcven(String orcven) {
		this.orcven = orcven;
	}

	public String getNumnf() {
		return numnf;
	}

	public void setNumnf(String numnf) {
		this.numnf = numnf;
	}

	public String getNumlot() {
		return numlot;
	}

	public void setNumlot(String numlot) {
		this.numlot = numlot;
	}

	public String getDescemp() {
		return descemp;
	}

	public void setDescemp(String descemp) {
		this.descemp = descemp;
	}

	public String getBaixa() {
		return baixa;
	}

	public void setBaixa(String baixa) {
		this.baixa = baixa;
	}

	public String getRecebi() {
		return recebi;
	}

	public void setRecebi(String recebi) {
		this.recebi = recebi;
	}

	public String getNormas() {
		return normas;
	}

	public void setNormas(String normas) {
		this.normas = normas;
	}

	public String getInspgui() {
		return inspgui;
	}

	public void setInspgui(String inspgui) {
		this.inspgui = inspgui;
	}

	public String getOrdemprod() {
		return ordemprod;
	}

	public void setOrdemprod(String ordemprod) {
		this.ordemprod = ordemprod;
	}

	public String getOrdememb() {
		return ordememb;
	}

	public void setOrdememb(String ordememb) {
		this.ordememb = ordememb;
	}

	public String getInspprocesso() {
		return inspprocesso;
	}

	public void setInspprocesso(String inspprocesso) {
		this.inspprocesso = inspprocesso;
	}

	public String getEtiqpro() {
		return etiqpro;
	}

	public void setEtiqpro(String etiqpro) {
		this.etiqpro = etiqpro;
	}

	public String getCodcli() {
		return codcli;
	}

	public void setCodcli(String codcli) {
		this.codcli = codcli;
	}

	public String getCodfor() {
		return codfor;
	}

	public void setCodfor(String codfor) {
		this.codfor = codfor;
	}

	public String getCodtrans() {
		return codtrans;
	}

	public void setCodtrans(String codtrans) {
		this.codtrans = codtrans;
	}

	public int getDecqtde() {
		return decqtde;
	}

	public void setDecqtde(int decqtde) {
		this.decqtde = decqtde;
	}

	public int getDecunit() {
		return decunit;
	}

	public void setDecunit(int decunit) {
		this.decunit = decunit;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

}
