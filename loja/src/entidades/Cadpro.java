package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

@Entity
public class Cadpro {
	
	@Id
	@GeneratedValue
	@Index(name = "idx_id")
	public Long idcadpro;

	@Column(nullable=false,columnDefinition = "varchar(20)  default ''")
	@Index(name = "idx_codpro")
	public String CODPRO;
	
	@Column(nullable=false,columnDefinition = "varchar(255)  default ''")
	public String CODEMP,DESCPRO,UN,TP,TIPO,DESCFOR,ANO,CFULTMOV,CLIFOR,CLASIPI,
	SEGUN,FOTO,NORMA,CUSTO;
	
	@Column(nullable=false,columnDefinition = "varchar(400)  default ''")
	public String MATERIAL;
	
	@Column(nullable=false,columnDefinition = "Double default 0")
	public Double VRVENDA,QTINICIAL,QTATUAL,VRCOMPRA,VRCOMIS,ESTSEG,QTNECES,VRMEDIO,
	EST1,EST2,VRREPOS,ECONOMICO,ESTMAXIMO,CMM,QTREQ,REDUZICM,PICM,PIPI,VRULTMOV,PRFORNEC,MARKUP,
	CMEDIO,VRINICIAL,VRATUAL,PESOLIQ,PESOBRUTO,QTCXA,PESOCAIXA,TMP,troca;	
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean ATIVO,CONTREST,BANCADA;
	
	@Temporal(TemporalType.DATE)
	private Date DTINICIAL,DTREPOS,DTVENDA,DTULTMOV;
	
	 @Column(nullable=false,columnDefinition ="ENUM('F','T')")
		private String sql_deleted;
	 
	 public Cadpro() {
		 CODEMP = "01";
		 CODPRO = "";
		 DESCPRO = "";
		 UN = "";
		 TP = "";
		 TIPO = "";
		 DESCFOR = "";
		 ANO = "";
		 CFULTMOV = "";
		 CLIFOR = "";
		 CLASIPI = "";
		 MATERIAL = "";
		 SEGUN = "";
		 FOTO = "";
		 NORMA = "";
		 CUSTO = ""; 
		 VRVENDA = 0.0;
		 QTATUAL = 0.0;
		 VRCOMPRA = 0.0;
		 VRCOMIS = 0.0;
		 ESTSEG = 0.0;
		 QTNECES = 0.0;
		 VRMEDIO = 0.0;
		 EST1 = 0.0;
		 EST2 = 0.0;
		 VRREPOS = 0.0;
		 ECONOMICO = 0.0;
		 ESTMAXIMO = 0.0;
		 CMM = 0.0;
		 QTREQ = 0.0;
		 REDUZICM = 0.0;
		 PICM = 0.0;
		 PIPI = 0.0;
		 VRULTMOV = 0.0;
		 PRFORNEC = 0.0;
		 MARKUP = 0.0;
		 CMEDIO = 0.0;
		 VRINICIAL = 0.0;
		 VRATUAL = 0.0;
		 PESOLIQ = 0.0;
		 PESOBRUTO = 0.0;
		 QTCXA = 0.0;
		 PESOCAIXA = 0.0;
		 TMP = 0.0;
		 troca = 0.0;
		 ATIVO = false;
		 CONTREST = false;
		 BANCADA = false;
		 sql_deleted = "F";
		 
	 }

	public Long getIdcadpro() {
		return idcadpro;
	}

	public void setIdcadpro(Long idcadpro) {
		this.idcadpro = idcadpro;
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

	public String getDESCPRO() {
		return DESCPRO;
	}

	public void setDESCPRO(String dESCPRO) {
		DESCPRO = dESCPRO;
	}

	public String getUN() {
		return UN;
	}

	public void setUN(String uN) {
		UN = uN;
	}

	public String getTP() {
		return TP;
	}

	public void setTP(String tP) {
		TP = tP;
	}

	public String getTIPO() {
		return TIPO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public String getDESCFOR() {
		return DESCFOR;
	}

	public void setDESCFOR(String dESCFOR) {
		DESCFOR = dESCFOR;
	}

	public String getANO() {
		return ANO;
	}

	public void setANO(String aNO) {
		ANO = aNO;
	}

	public String getCFULTMOV() {
		return CFULTMOV;
	}

	public void setCFULTMOV(String cFULTMOV) {
		CFULTMOV = cFULTMOV;
	}

	public String getCLIFOR() {
		return CLIFOR;
	}

	public void setCLIFOR(String cLIFOR) {
		CLIFOR = cLIFOR;
	}

	public String getCLASIPI() {
		return CLASIPI;
	}

	public void setCLASIPI(String cLASIPI) {
		CLASIPI = cLASIPI;
	}

	public String getMATERIAL() {
		return MATERIAL;
	}

	public void setMATERIAL(String mATERIAL) {
		MATERIAL = mATERIAL;
	}

	public String getSEGUN() {
		return SEGUN;
	}

	public void setSEGUN(String sEGUN) {
		SEGUN = sEGUN;
	}

	public String getFOTO() {
		return FOTO;
	}

	public void setFOTO(String fOTO) {
		FOTO = fOTO;
	}

	public String getNORMA() {
		return NORMA;
	}

	public void setNORMA(String nORMA) {
		NORMA = nORMA;
	}

	public String getCUSTO() {
		return CUSTO;
	}

	public void setCUSTO(String cUSTO) {
		CUSTO = cUSTO;
	}

	public Double getVRVENDA() {
		return VRVENDA;
	}

	public void setVRVENDA(Double vRVENDA) {
		VRVENDA = vRVENDA;
	}

	public Double getQTINICIAL() {
		return QTINICIAL;
	}

	public void setQTINICIAL(Double qTINICIAL) {
		QTINICIAL = qTINICIAL;
	}

	public Double getQTATUAL() {
		return QTATUAL;
	}

	public void setQTATUAL(Double qTATUAL) {
		QTATUAL = qTATUAL;
	}

	public Double getVRCOMPRA() {
		return VRCOMPRA;
	}

	public void setVRCOMPRA(Double vRCOMPRA) {
		VRCOMPRA = vRCOMPRA;
	}

	public Double getVRCOMIS() {
		return VRCOMIS;
	}

	public void setVRCOMIS(Double vRCOMIS) {
		VRCOMIS = vRCOMIS;
	}

	public Double getESTSEG() {
		return ESTSEG;
	}

	public void setESTSEG(Double eSTSEG) {
		ESTSEG = eSTSEG;
	}

	public Double getQTNECES() {
		return QTNECES;
	}

	public void setQTNECES(Double qTNECES) {
		QTNECES = qTNECES;
	}

	public Double getVRMEDIO() {
		return VRMEDIO;
	}

	public void setVRMEDIO(Double vRMEDIO) {
		VRMEDIO = vRMEDIO;
	}

	public Double getEST1() {
		return EST1;
	}

	public void setEST1(Double eST1) {
		EST1 = eST1;
	}

	public Double getEST2() {
		return EST2;
	}

	public void setEST2(Double eST2) {
		EST2 = eST2;
	}

	public Double getVRREPOS() {
		return VRREPOS;
	}

	public void setVRREPOS(Double vRREPOS) {
		VRREPOS = vRREPOS;
	}

	public Double getECONOMICO() {
		return ECONOMICO;
	}

	public void setECONOMICO(Double eCONOMICO) {
		ECONOMICO = eCONOMICO;
	}

	public Double getESTMAXIMO() {
		return ESTMAXIMO;
	}

	public void setESTMAXIMO(Double eSTMAXIMO) {
		ESTMAXIMO = eSTMAXIMO;
	}

	public Double getCMM() {
		return CMM;
	}

	public void setCMM(Double cMM) {
		CMM = cMM;
	}

	public Double getQTREQ() {
		return QTREQ;
	}

	public void setQTREQ(Double qTREQ) {
		QTREQ = qTREQ;
	}

	public Double getREDUZICM() {
		return REDUZICM;
	}

	public void setREDUZICM(Double rEDUZICM) {
		REDUZICM = rEDUZICM;
	}

	public Double getPICM() {
		return PICM;
	}

	public void setPICM(Double pICM) {
		PICM = pICM;
	}

	public Double getPIPI() {
		return PIPI;
	}

	public void setPIPI(Double pIPI) {
		PIPI = pIPI;
	}

	public Double getVRULTMOV() {
		return VRULTMOV;
	}

	public void setVRULTMOV(Double vRULTMOV) {
		VRULTMOV = vRULTMOV;
	}

	public Double getPRFORNEC() {
		return PRFORNEC;
	}

	public void setPRFORNEC(Double pRFORNEC) {
		PRFORNEC = pRFORNEC;
	}

	public Double getMARKUP() {
		return MARKUP;
	}

	public void setMARKUP(Double mARKUP) {
		MARKUP = mARKUP;
	}

	public Double getCMEDIO() {
		return CMEDIO;
	}

	public void setCMEDIO(Double cMEDIO) {
		CMEDIO = cMEDIO;
	}

	public Double getVRINICIAL() {
		return VRINICIAL;
	}

	public void setVRINICIAL(Double vRINICIAL) {
		VRINICIAL = vRINICIAL;
	}

	public Double getVRATUAL() {
		return VRATUAL;
	}

	public void setVRATUAL(Double vRATUAL) {
		VRATUAL = vRATUAL;
	}

	public Double getPESOLIQ() {
		return PESOLIQ;
	}

	public void setPESOLIQ(Double pESOLIQ) {
		PESOLIQ = pESOLIQ;
	}

	public Double getPESOBRUTO() {
		return PESOBRUTO;
	}

	public void setPESOBRUTO(Double pESOBRUTO) {
		PESOBRUTO = pESOBRUTO;
	}

	public Double getQTCXA() {
		return QTCXA;
	}

	public void setQTCXA(Double qTCXA) {
		QTCXA = qTCXA;
	}

	public Double getPESOCAIXA() {
		return PESOCAIXA;
	}

	public void setPESOCAIXA(Double pESOCAIXA) {
		PESOCAIXA = pESOCAIXA;
	}

	public Double getTMP() {
		return TMP;
	}

	public void setTMP(Double tMP) {
		TMP = tMP;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public Boolean getCONTREST() {
		return CONTREST;
	}

	public void setCONTREST(Boolean cONTREST) {
		CONTREST = cONTREST;
	}

	public Boolean getBANCADA() {
		return BANCADA;
	}

	public void setBANCADA(Boolean bANCADA) {
		BANCADA = bANCADA;
	}

	public Date getDTINICIAL() {
		return DTINICIAL;
	}

	public void setDTINICIAL(Date dTINICIAL) {
		DTINICIAL = dTINICIAL;
	}

	public Date getDTREPOS() {
		return DTREPOS;
	}

	public void setDTREPOS(Date dTREPOS) {
		DTREPOS = dTREPOS;
	}

	public Date getDTVENDA() {
		return DTVENDA;
	}

	public void setDTVENDA(Date dTVENDA) {
		DTVENDA = dTVENDA;
	}

	public Date getDTULTMOV() {
		return DTULTMOV;
	}

	public void setDTULTMOV(Date dTULTMOV) {
		DTULTMOV = dTULTMOV;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

	public Double getTroca() {
		return troca;
	}

	public void setTroca(Double troca) {
		this.troca = troca;
	}

	
	
	


}
