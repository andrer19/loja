package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

@Entity
public class Cadfor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Index(name = "idx_id")
	public Long IDFORNECEDOR;
	
	@Column(nullable=false,columnDefinition = "varchar(6)  default ''")
	@Index(name = "idx_codigo")
	public String CODFOR;
	
	@Column(nullable=false,columnDefinition = "varchar(255)  default ''")
	public String CODEMP,DESCFOR,RAZAO,CONTATO1,DEPTO,DEPTO1,IBGE,NUMERO,
	BAIRRO,CIDADE,ESTADO,CEP,FONE2,CORREIO,FAX,IE,CGC,TRANSP,CGCTRANS,IETRANS,SEGMENTO,IMPRIMIR,
	QUALIFICA,TIPO,SITE,IMPETIQ,CODCLI,RAMOATIV,A2_LOJA,A2_TIPO,A2_INSCRM,A2_COND,A2_NATUREZ,A2_COD,CODFORP,
	FONE,CONTATO,EMAIL,ENDER,ENDTRANS,FONETRANS;
	
	@Column(nullable=false,columnDefinition = "varchar(400)  default ''")
	private String OBS;
	
	@Column(nullable=false,columnDefinition = "Double default 0")
	public Double NIVEL,A2_NROCOM,MCOMPRA,A2_MATR;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
    public Boolean PROG, LIBERAF;
	
	@Temporal(TemporalType.DATE)
	public Date DT1COMPRA,DTMCOMPRA,CADASTRO,VALIDADE;
	
	@Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;

	
	public Cadfor() {
		CODFOR = "";
		CODEMP = "01";
		DESCFOR = "";
		RAZAO = "";
		CONTATO1 = "";
		DEPTO = "";
		DEPTO1 = "";
		IBGE = "";
		NUMERO = "";
		BAIRRO = "";
		CIDADE = "";
		ESTADO = "";
		CEP = "";
		FONE2 = "";
		CORREIO = "";
		FAX = "";
		IE = "";
		CGC = "";
		TRANSP = "";
		CGCTRANS = "";
		IETRANS = "";
		SEGMENTO = "";
		IMPRIMIR = "";
		QUALIFICA = "";
		TIPO = "";
		SITE = "";
		IMPETIQ = "";
		CODCLI = "";
		RAMOATIV = "";
		A2_LOJA = "";
		A2_TIPO = "";
		A2_INSCRM = "";
		A2_COND = "";
		A2_NATUREZ = "";
		A2_COD = "";
		CODFORP = "";
		FONE = "";
		CONTATO = "";
		EMAIL = "";
		ENDER = "";
		ENDTRANS = "";
		FONETRANS = "";
		OBS = "";
		NIVEL = 0.0;
		A2_NROCOM = 0.0;
		MCOMPRA = 0.0;
		A2_MATR = 0.0;
		PROG = false;
		LIBERAF = false;
		sql_deleted = "F";
	}


	public Long getIDFORNECEDOR() {
		return IDFORNECEDOR;
	}


	public void setIDFORNECEDOR(Long iDFORNECEDOR) {
		IDFORNECEDOR = iDFORNECEDOR;
	}


	public String getCODFOR() {
		return CODFOR;
	}


	public void setCODFOR(String cODFOR) {
		CODFOR = cODFOR;
	}


	public String getCODEMP() {
		return CODEMP;
	}


	public void setCODEMP(String cODEMP) {
		CODEMP = cODEMP;
	}


	public String getDESCFOR() {
		return DESCFOR;
	}


	public void setDESCFOR(String dESCFOR) {
		DESCFOR = dESCFOR;
	}


	public String getRAZAO() {
		return RAZAO;
	}


	public void setRAZAO(String rAZAO) {
		RAZAO = rAZAO;
	}


	public String getCONTATO1() {
		return CONTATO1;
	}


	public void setCONTATO1(String cONTATO1) {
		CONTATO1 = cONTATO1;
	}


	public String getDEPTO() {
		return DEPTO;
	}


	public void setDEPTO(String dEPTO) {
		DEPTO = dEPTO;
	}


	public String getDEPTO1() {
		return DEPTO1;
	}


	public void setDEPTO1(String dEPTO1) {
		DEPTO1 = dEPTO1;
	}


	public String getIBGE() {
		return IBGE;
	}


	public void setIBGE(String iBGE) {
		IBGE = iBGE;
	}


	public String getNUMERO() {
		return NUMERO;
	}


	public void setNUMERO(String nUMERO) {
		NUMERO = nUMERO;
	}


	public String getBAIRRO() {
		return BAIRRO;
	}


	public void setBAIRRO(String bAIRRO) {
		BAIRRO = bAIRRO;
	}


	public String getCIDADE() {
		return CIDADE;
	}


	public void setCIDADE(String cIDADE) {
		CIDADE = cIDADE;
	}


	public String getESTADO() {
		return ESTADO;
	}


	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}


	public String getCEP() {
		return CEP;
	}


	public void setCEP(String cEP) {
		CEP = cEP;
	}


	public String getFONE2() {
		return FONE2;
	}


	public void setFONE2(String fONE2) {
		FONE2 = fONE2;
	}


	public String getCORREIO() {
		return CORREIO;
	}


	public void setCORREIO(String cORREIO) {
		CORREIO = cORREIO;
	}


	public String getFAX() {
		return FAX;
	}


	public void setFAX(String fAX) {
		FAX = fAX;
	}


	public String getIE() {
		return IE;
	}


	public void setIE(String iE) {
		IE = iE;
	}


	public String getCGC() {
		return CGC;
	}


	public void setCGC(String cGC) {
		CGC = cGC;
	}


	public String getTRANSP() {
		return TRANSP;
	}


	public void setTRANSP(String tRANSP) {
		TRANSP = tRANSP;
	}


	public String getCGCTRANS() {
		return CGCTRANS;
	}


	public void setCGCTRANS(String cGCTRANS) {
		CGCTRANS = cGCTRANS;
	}


	public String getIETRANS() {
		return IETRANS;
	}


	public void setIETRANS(String iETRANS) {
		IETRANS = iETRANS;
	}


	public String getSEGMENTO() {
		return SEGMENTO;
	}


	public void setSEGMENTO(String sEGMENTO) {
		SEGMENTO = sEGMENTO;
	}


	public String getIMPRIMIR() {
		return IMPRIMIR;
	}


	public void setIMPRIMIR(String iMPRIMIR) {
		IMPRIMIR = iMPRIMIR;
	}


	public String getQUALIFICA() {
		return QUALIFICA;
	}


	public void setQUALIFICA(String qUALIFICA) {
		QUALIFICA = qUALIFICA;
	}


	public String getTIPO() {
		return TIPO;
	}


	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}


	public String getSITE() {
		return SITE;
	}


	public void setSITE(String sITE) {
		SITE = sITE;
	}


	public String getIMPETIQ() {
		return IMPETIQ;
	}


	public void setIMPETIQ(String iMPETIQ) {
		IMPETIQ = iMPETIQ;
	}


	public String getCODCLI() {
		return CODCLI;
	}


	public void setCODCLI(String cODCLI) {
		CODCLI = cODCLI;
	}


	public String getRAMOATIV() {
		return RAMOATIV;
	}


	public void setRAMOATIV(String rAMOATIV) {
		RAMOATIV = rAMOATIV;
	}


	public String getA2_LOJA() {
		return A2_LOJA;
	}


	public void setA2_LOJA(String a2_LOJA) {
		A2_LOJA = a2_LOJA;
	}


	public String getA2_TIPO() {
		return A2_TIPO;
	}


	public void setA2_TIPO(String a2_TIPO) {
		A2_TIPO = a2_TIPO;
	}


	public String getA2_INSCRM() {
		return A2_INSCRM;
	}


	public void setA2_INSCRM(String a2_INSCRM) {
		A2_INSCRM = a2_INSCRM;
	}


	public String getA2_COND() {
		return A2_COND;
	}


	public void setA2_COND(String a2_COND) {
		A2_COND = a2_COND;
	}


	public String getA2_NATUREZ() {
		return A2_NATUREZ;
	}


	public void setA2_NATUREZ(String a2_NATUREZ) {
		A2_NATUREZ = a2_NATUREZ;
	}


	public String getA2_COD() {
		return A2_COD;
	}


	public void setA2_COD(String a2_COD) {
		A2_COD = a2_COD;
	}


	public String getCODFORP() {
		return CODFORP;
	}


	public void setCODFORP(String cODFORP) {
		CODFORP = cODFORP;
	}


	public String getFONE() {
		return FONE;
	}


	public void setFONE(String fONE) {
		FONE = fONE;
	}


	public String getCONTATO() {
		return CONTATO;
	}


	public void setCONTATO(String cONTATO) {
		CONTATO = cONTATO;
	}


	public String getEMAIL() {
		return EMAIL;
	}


	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}


	public String getENDER() {
		return ENDER;
	}


	public void setENDER(String eNDER) {
		ENDER = eNDER;
	}


	public String getENDTRANS() {
		return ENDTRANS;
	}


	public void setENDTRANS(String eNDTRANS) {
		ENDTRANS = eNDTRANS;
	}


	public String getFONETRANS() {
		return FONETRANS;
	}


	public void setFONETRANS(String fONETRANS) {
		FONETRANS = fONETRANS;
	}


	public Boolean getPROG() {
		return PROG;
	}


	public void setPROG(Boolean pROG) {
		PROG = pROG;
	}


	public Boolean getLIBERAF() {
		return LIBERAF;
	}


	public void setLIBERAF(Boolean lIBERAF) {
		LIBERAF = lIBERAF;
	}


	public Date getDT1COMPRA() {
		return DT1COMPRA;
	}


	public void setDT1COMPRA(Date dT1COMPRA) {
		DT1COMPRA = dT1COMPRA;
	}


	public Date getDTMCOMPRA() {
		return DTMCOMPRA;
	}


	public void setDTMCOMPRA(Date dTMCOMPRA) {
		DTMCOMPRA = dTMCOMPRA;
	}


	public Date getCADASTRO() {
		return CADASTRO;
	}


	public void setCADASTRO(Date cADASTRO) {
		CADASTRO = cADASTRO;
	}


	public Date getVALIDADE() {
		return VALIDADE;
	}


	public void setVALIDADE(Date vALIDADE) {
		VALIDADE = vALIDADE;
	}


	public Double getNIVEL() {
		return NIVEL;
	}


	public void setNIVEL(Double nIVEL) {
		NIVEL = nIVEL;
	}


	public Double getA2_NROCOM() {
		return A2_NROCOM;
	}


	public void setA2_NROCOM(Double a2_NROCOM) {
		A2_NROCOM = a2_NROCOM;
	}


	public Double getMCOMPRA() {
		return MCOMPRA;
	}


	public void setMCOMPRA(Double mCOMPRA) {
		MCOMPRA = mCOMPRA;
	}


	public Double getA2_MATR() {
		return A2_MATR;
	}


	public void setA2_MATR(Double a2_MATR) {
		A2_MATR = a2_MATR;
	}


	public String getOBS() {
		return OBS;
	}


	public void setOBS(String oBS) {
		OBS = oBS;
	}


	public String getSql_deleted() {
		return sql_deleted;
	}


	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}
		
}
