package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Estrupro {
	
	@Id
	@GeneratedValue
    private Long idestrupro;
	
	private String CODEMP,CODPRO,UN,CODPRO1,UN1,CODCLI,GUERRA,CODCLIENTE,DESCMAT,
	DESCRICAO,CODMAT,MATERIAL,TIPO,DESTINO,BEDAME;		
	private Double QTDE,PERC,PESOPRO,PESOLIQPRO,COMPPRO,COMPBARRA,PECASBARRA,
	PESOBARRA;
	private Boolean CONTEST,DESCONS,BAIXAOP;
	
	@Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;
	
	public Estrupro() {
		this.setSql_deleted("F");
		
	}

	public Long getIdestrupro() {
		return idestrupro;
	}

	public void setIdestrupro(Long idestrupro) {
		this.idestrupro = idestrupro;
	}

	public String getCODEMP() {
		return CODEMP;
	}

	public void setCODEMP(String cODEMP) {
		CODEMP = cODEMP;
	}

	public String getCODPRO() {
		return CODPRO;
	}

	public void setCODPRO(String cODPRO) {
		CODPRO = cODPRO;
	}

	public String getUN() {
		return UN;
	}

	public void setUN(String uN) {
		UN = uN;
	}

	public String getCODPRO1() {
		return CODPRO1;
	}

	public void setCODPRO1(String cODPRO1) {
		CODPRO1 = cODPRO1;
	}

	public String getUN1() {
		return UN1;
	}

	public void setUN1(String uN1) {
		UN1 = uN1;
	}

	public String getCODCLI() {
		return CODCLI;
	}

	public void setCODCLI(String cODCLI) {
		CODCLI = cODCLI;
	}

	public String getGUERRA() {
		return GUERRA;
	}

	public void setGUERRA(String gUERRA) {
		GUERRA = gUERRA;
	}

	public String getCODCLIENTE() {
		return CODCLIENTE;
	}

	public void setCODCLIENTE(String cODCLIENTE) {
		CODCLIENTE = cODCLIENTE;
	}

	public String getDESCMAT() {
		return DESCMAT;
	}

	public void setDESCMAT(String dESCMAT) {
		DESCMAT = dESCMAT;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public String getCODMAT() {
		return CODMAT;
	}

	public void setCODMAT(String cODMAT) {
		CODMAT = cODMAT;
	}

	public String getMATERIAL() {
		return MATERIAL;
	}

	public void setMATERIAL(String mATERIAL) {
		MATERIAL = mATERIAL;
	}

	public String getTIPO() {
		return TIPO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public String getDESTINO() {
		return DESTINO;
	}

	public void setDESTINO(String dESTINO) {
		DESTINO = dESTINO;
	}

	public String getBEDAME() {
		return BEDAME;
	}

	public void setBEDAME(String bEDAME) {
		BEDAME = bEDAME;
	}

	public Double getQTDE() {
		return QTDE;
	}

	public void setQTDE(Double qTDE) {
		QTDE = qTDE;
	}

	public Double getPERC() {
		return PERC;
	}

	public void setPERC(Double pERC) {
		PERC = pERC;
	}

	public Double getPESOPRO() {
		return PESOPRO;
	}

	public void setPESOPRO(Double pESOPRO) {
		PESOPRO = pESOPRO;
	}

	public Double getPESOLIQPRO() {
		return PESOLIQPRO;
	}

	public void setPESOLIQPRO(Double pESOLIQPRO) {
		PESOLIQPRO = pESOLIQPRO;
	}

	public Double getCOMPPRO() {
		return COMPPRO;
	}

	public void setCOMPPRO(Double cOMPPRO) {
		COMPPRO = cOMPPRO;
	}

	public Double getCOMPBARRA() {
		return COMPBARRA;
	}

	public void setCOMPBARRA(Double cOMPBARRA) {
		COMPBARRA = cOMPBARRA;
	}

	public Double getPECASBARRA() {
		return PECASBARRA;
	}

	public void setPECASBARRA(Double pECASBARRA) {
		PECASBARRA = pECASBARRA;
	}

	public Double getPESOBARRA() {
		return PESOBARRA;
	}

	public void setPESOBARRA(Double pESOBARRA) {
		PESOBARRA = pESOBARRA;
	}

	public Boolean getCONTEST() {
		return CONTEST;
	}

	public void setCONTEST(Boolean cONTEST) {
		CONTEST = cONTEST;
	}

	public Boolean getDESCONS() {
		return DESCONS;
	}

	public void setDESCONS(Boolean dESCONS) {
		DESCONS = dESCONS;
	}

	public Boolean getBAIXAOP() {
		return BAIXAOP;
	}

	public void setBAIXAOP(Boolean bAIXAOP) {
		BAIXAOP = bAIXAOP;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}
	
	
	
	
	
	
	
	
	
	
	


}
