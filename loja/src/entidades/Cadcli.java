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
public class Cadcli {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Index(name = "idx_id")
	private Long idcadcli;
	
	@Column(nullable=false,columnDefinition = "varchar(6)  default ''")
	@Index(name = "idx_codigo")
	private String CODCLI;
		
	@Column(nullable=false,columnDefinition = "varchar(255)  default ''")
	private String CODEMP,DESCCLI,RAZAO,FAX,BAIRRO,CIDADE,CEP,ESTADO,CGC,IE,TRANSP,
	CGCTRANS,IETRANS,ENTREGA,CIDENTR,ESTENTR,NASCIMENTO,RG,PROFISSAO,FONE_COM,CELULAR,PROXIMO,PHOTO,TIPO,FORMA,TITULAR,REGIAO,
	VENDEDOR,GERENTE,CEPTRANS,SEGMENTO,CEPENTR,RAMOATIV,CONTATO2,CONTATO3,CONTATO4,BAIRROCOB,FONECOB,CONTCOB1,CONTCOB2,BAIRROENT,FONEENT,
	CONTENT1,CONTENT2,SITE,IMPETIQ,CODFOR,OBSET,SUFRAMA,A1_TIPO,A1_TELEX,A1_INSCRM,A1_TPFRET,A1_MENSAGE,A1_COD,A1_LOJA,BCO,A1_COND,A1_CALCSUF,
	NATOPER,DESCARGA,LOCALENT,NUMERO,FONE,CONTATO,EMAIL,ENDER,ENDTRANS,FONETRANS;
	
	@Column(nullable=false,columnDefinition = "varchar(400)  default ''")
	private String OBS;
	
	@Column(nullable=false,columnDefinition = "Double default 0")
	private Double MCOMPRA,NIVEL,RAMAL,GRUPO,SUB_GRUPO,PICM,PIPI,NUMTITULO,LIMCRED,PVENDEDOR,PGERENTE,CONDPAG,MALA,STATUS,A1_METR,
	A1_MSALDO,A1_NROCOM,A1_NROPAG,A1_ATR,A1_MATR,FRETE,txent;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean BOX1,BOX2,BOX3,BOX4,BOXCOB1,BOXCOB2,BOXENT1,BOXENT2,EXPORTA
    ,JURIDICA,FISICA,ATIVO,INATIVO;
	
	@Temporal(TemporalType.DATE)
	private Date DT1COMPRA,DTMCOMPRA,VENCTO;
    
    @Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;
    
    
    public Cadcli() {
    	CODEMP = "01";
    	CODCLI = "";
    	DESCCLI = "";
    	RAZAO = "";
    	FAX = "";
    	BAIRRO = "";
    	CIDADE = "";
    	CEP = "";
    	ESTADO = "";
    	CGC = "";
    	IE = "";
    	TRANSP = "";
    	CGCTRANS = "";
    	IETRANS = "";
    	ENTREGA = "";
    	CIDENTR = "";
    	ESTENTR = "";
    	NASCIMENTO = "";
    	RG = "";
    	PROFISSAO = "";
    	FONE_COM = "";
    	CELULAR = "";
    	PROXIMO = "";
    	PHOTO = "";
    	TIPO = "";
    	FORMA = "";
    	TITULAR = "";
    	REGIAO = "";
    	VENDEDOR = "";
    	GERENTE = "";
    	CEPTRANS = "";
    	SEGMENTO = "";
    	CEPENTR = "";
    	RAMOATIV = "";
    	CONTATO2 = "";
    	CONTATO3 = "";
    	CONTATO4 = "";
    	BAIRROCOB = "";
    	FONECOB = "";
    	CONTCOB1 = "";
    	CONTCOB2 = "";
    	BAIRROENT = "";
    	FONEENT = "";
    	CONTENT1 = "";
    	CONTENT2 = "";
    	SITE = "";
    	IMPETIQ = "";
    	CODFOR = "";
    	OBSET = "";
    	SUFRAMA = "";
    	A1_TIPO = "";
    	A1_TELEX = "";
    	A1_INSCRM = "";
    	A1_TPFRET = "";
    	A1_MENSAGE = "";
    	A1_COD = "";
    	A1_LOJA = "";
    	BCO = "";
    	A1_COND = "";
    	A1_CALCSUF = "";
    	NATOPER = "";
    	DESCARGA = "";
    	LOCALENT = "";
    	NUMERO = "";
    	FONE = "";
    	CONTATO = "";
    	OBS = "";
    	EMAIL = "";
    	ENDER = "";
    	ENDTRANS = "";
    	FONETRANS = "";
    	MCOMPRA = 0.0;
    	NIVEL = 0.0;
    	RAMAL = 0.0;
    	GRUPO = 0.0;
    	SUB_GRUPO = 0.0;
    	PICM = 0.0;
    	PIPI = 0.0;
    	NUMTITULO = 0.0;
    	LIMCRED = 0.0;
    	PVENDEDOR = 0.0;
    	PGERENTE = 0.0;
    	CONDPAG = 0.0;
    	MALA = 0.0;
    	STATUS = 0.0;
    	A1_METR = 0.0;
    	A1_MSALDO = 0.0;
    	A1_NROCOM = 0.0;
    	A1_NROPAG = 0.0;
    	A1_ATR = 0.0;
    	A1_MATR = 0.0;
    	FRETE = 0.0;
    	txent  = 0.0;
    	BOX1 = false;
    	BOX2 = false;
    	BOX3 = false;
    	BOX4 = false;
    	BOXCOB1 = false;
    	BOXCOB2 = false;
    	BOXENT1 = false;
    	BOXENT2 = false;
    	EXPORTA = false;
        JURIDICA = false;
        FISICA = false;
        ATIVO = false;
        INATIVO = false;
        sql_deleted = "F";
    	
    	
    }

	public Long getIdcadcli() {
		return idcadcli;
	}

	public void setIdcadcli(Long idcadcli) {
		this.idcadcli = idcadcli;
	}

	public String getCODEMP() {
		return CODEMP;
	}

	public void setCODEMP(String cODEMP) {
		CODEMP = cODEMP;
	}

	public String getCODCLI() {
		return CODCLI;
	}

	public void setCODCLI(String cODCLI) {
		CODCLI = cODCLI;
	}

	public String getDESCCLI() {
		return DESCCLI;
	}

	public void setDESCCLI(String dESCCLI) {
		DESCCLI = dESCCLI;
	}

	public String getRAZAO() {
		return RAZAO;
	}

	public void setRAZAO(String rAZAO) {
		RAZAO = rAZAO;
	}

	public String getFAX() {
		return FAX;
	}

	public void setFAX(String fAX) {
		FAX = fAX;
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

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public String getCGC() {
		return CGC;
	}

	public void setCGC(String cGC) {
		CGC = cGC;
	}

	public String getIE() {
		return IE;
	}

	public void setIE(String iE) {
		IE = iE;
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

	public String getENTREGA() {
		return ENTREGA;
	}

	public void setENTREGA(String eNTREGA) {
		ENTREGA = eNTREGA;
	}

	public String getCIDENTR() {
		return CIDENTR;
	}

	public void setCIDENTR(String cIDENTR) {
		CIDENTR = cIDENTR;
	}

	public String getESTENTR() {
		return ESTENTR;
	}

	public void setESTENTR(String eSTENTR) {
		ESTENTR = eSTENTR;
	}

	public String getNASCIMENTO() {
		return NASCIMENTO;
	}

	public void setNASCIMENTO(String nASCIMENTO) {
		NASCIMENTO = nASCIMENTO;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getPROFISSAO() {
		return PROFISSAO;
	}

	public void setPROFISSAO(String pROFISSAO) {
		PROFISSAO = pROFISSAO;
	}

	public String getFONE_COM() {
		return FONE_COM;
	}

	public void setFONE_COM(String fONE_COM) {
		FONE_COM = fONE_COM;
	}

	public String getCELULAR() {
		return CELULAR;
	}

	public void setCELULAR(String cELULAR) {
		CELULAR = cELULAR;
	}

	public String getPROXIMO() {
		return PROXIMO;
	}

	public void setPROXIMO(String pROXIMO) {
		PROXIMO = pROXIMO;
	}

	public String getPHOTO() {
		return PHOTO;
	}

	public void setPHOTO(String pHOTO) {
		PHOTO = pHOTO;
	}

	public String getTIPO() {
		return TIPO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public String getFORMA() {
		return FORMA;
	}

	public void setFORMA(String fORMA) {
		FORMA = fORMA;
	}

	public String getTITULAR() {
		return TITULAR;
	}

	public void setTITULAR(String tITULAR) {
		TITULAR = tITULAR;
	}

	public String getREGIAO() {
		return REGIAO;
	}

	public void setREGIAO(String rEGIAO) {
		REGIAO = rEGIAO;
	}

	public String getVENDEDOR() {
		return VENDEDOR;
	}

	public void setVENDEDOR(String vENDEDOR) {
		VENDEDOR = vENDEDOR;
	}

	public String getGERENTE() {
		return GERENTE;
	}

	public void setGERENTE(String gERENTE) {
		GERENTE = gERENTE;
	}

	public String getCEPTRANS() {
		return CEPTRANS;
	}

	public void setCEPTRANS(String cEPTRANS) {
		CEPTRANS = cEPTRANS;
	}

	public String getSEGMENTO() {
		return SEGMENTO;
	}

	public void setSEGMENTO(String sEGMENTO) {
		SEGMENTO = sEGMENTO;
	}

	public String getCEPENTR() {
		return CEPENTR;
	}

	public void setCEPENTR(String cEPENTR) {
		CEPENTR = cEPENTR;
	}

	public String getRAMOATIV() {
		return RAMOATIV;
	}

	public void setRAMOATIV(String rAMOATIV) {
		RAMOATIV = rAMOATIV;
	}

	public String getCONTATO2() {
		return CONTATO2;
	}

	public void setCONTATO2(String cONTATO2) {
		CONTATO2 = cONTATO2;
	}

	public String getCONTATO3() {
		return CONTATO3;
	}

	public void setCONTATO3(String cONTATO3) {
		CONTATO3 = cONTATO3;
	}

	public String getCONTATO4() {
		return CONTATO4;
	}

	public void setCONTATO4(String cONTATO4) {
		CONTATO4 = cONTATO4;
	}

	public String getBAIRROCOB() {
		return BAIRROCOB;
	}

	public void setBAIRROCOB(String bAIRROCOB) {
		BAIRROCOB = bAIRROCOB;
	}

	public String getFONECOB() {
		return FONECOB;
	}

	public void setFONECOB(String fONECOB) {
		FONECOB = fONECOB;
	}

	public String getCONTCOB1() {
		return CONTCOB1;
	}

	public void setCONTCOB1(String cONTCOB1) {
		CONTCOB1 = cONTCOB1;
	}

	public String getCONTCOB2() {
		return CONTCOB2;
	}

	public void setCONTCOB2(String cONTCOB2) {
		CONTCOB2 = cONTCOB2;
	}

	public String getBAIRROENT() {
		return BAIRROENT;
	}

	public void setBAIRROENT(String bAIRROENT) {
		BAIRROENT = bAIRROENT;
	}

	public String getFONEENT() {
		return FONEENT;
	}

	public void setFONEENT(String fONEENT) {
		FONEENT = fONEENT;
	}

	public String getCONTENT1() {
		return CONTENT1;
	}

	public void setCONTENT1(String cONTENT1) {
		CONTENT1 = cONTENT1;
	}

	public String getCONTENT2() {
		return CONTENT2;
	}

	public void setCONTENT2(String cONTENT2) {
		CONTENT2 = cONTENT2;
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

	public String getCODFOR() {
		return CODFOR;
	}

	public void setCODFOR(String cODFOR) {
		CODFOR = cODFOR;
	}

	public String getOBSET() {
		return OBSET;
	}

	public void setOBSET(String oBSET) {
		OBSET = oBSET;
	}

	public String getSUFRAMA() {
		return SUFRAMA;
	}

	public void setSUFRAMA(String sUFRAMA) {
		SUFRAMA = sUFRAMA;
	}

	public String getA1_TIPO() {
		return A1_TIPO;
	}

	public void setA1_TIPO(String a1_TIPO) {
		A1_TIPO = a1_TIPO;
	}

	public String getA1_TELEX() {
		return A1_TELEX;
	}

	public void setA1_TELEX(String a1_TELEX) {
		A1_TELEX = a1_TELEX;
	}

	public String getA1_INSCRM() {
		return A1_INSCRM;
	}

	public void setA1_INSCRM(String a1_INSCRM) {
		A1_INSCRM = a1_INSCRM;
	}

	public String getA1_TPFRET() {
		return A1_TPFRET;
	}

	public void setA1_TPFRET(String a1_TPFRET) {
		A1_TPFRET = a1_TPFRET;
	}

	public String getA1_MENSAGE() {
		return A1_MENSAGE;
	}

	public void setA1_MENSAGE(String a1_MENSAGE) {
		A1_MENSAGE = a1_MENSAGE;
	}

	public String getA1_COD() {
		return A1_COD;
	}

	public void setA1_COD(String a1_COD) {
		A1_COD = a1_COD;
	}

	public String getA1_LOJA() {
		return A1_LOJA;
	}

	public void setA1_LOJA(String a1_LOJA) {
		A1_LOJA = a1_LOJA;
	}

	public String getBCO() {
		return BCO;
	}

	public void setBCO(String bCO) {
		BCO = bCO;
	}

	public String getA1_COND() {
		return A1_COND;
	}

	public void setA1_COND(String a1_COND) {
		A1_COND = a1_COND;
	}

	public String getA1_CALCSUF() {
		return A1_CALCSUF;
	}

	public void setA1_CALCSUF(String a1_CALCSUF) {
		A1_CALCSUF = a1_CALCSUF;
	}

	public String getNATOPER() {
		return NATOPER;
	}

	public void setNATOPER(String nATOPER) {
		NATOPER = nATOPER;
	}

	public String getDESCARGA() {
		return DESCARGA;
	}

	public void setDESCARGA(String dESCARGA) {
		DESCARGA = dESCARGA;
	}

	public String getLOCALENT() {
		return LOCALENT;
	}

	public void setLOCALENT(String lOCALENT) {
		LOCALENT = lOCALENT;
	}

	public String getNUMERO() {
		return NUMERO;
	}

	public void setNUMERO(String nUMERO) {
		NUMERO = nUMERO;
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

	public Date getVENCTO() {
		return VENCTO;
	}

	public void setVENCTO(Date vENCTO) {
		VENCTO = vENCTO;
	}

	public Double getMCOMPRA() {
		return MCOMPRA;
	}

	public void setMCOMPRA(Double mCOMPRA) {
		MCOMPRA = mCOMPRA;
	}

	public Double getNIVEL() {
		return NIVEL;
	}

	public void setNIVEL(Double nIVEL) {
		NIVEL = nIVEL;
	}

	public Double getRAMAL() {
		return RAMAL;
	}

	public void setRAMAL(Double rAMAL) {
		RAMAL = rAMAL;
	}

	public Double getGRUPO() {
		return GRUPO;
	}

	public void setGRUPO(Double gRUPO) {
		GRUPO = gRUPO;
	}

	public Double getSUB_GRUPO() {
		return SUB_GRUPO;
	}

	public void setSUB_GRUPO(Double sUB_GRUPO) {
		SUB_GRUPO = sUB_GRUPO;
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

	public Double getNUMTITULO() {
		return NUMTITULO;
	}

	public void setNUMTITULO(Double nUMTITULO) {
		NUMTITULO = nUMTITULO;
	}

	public Double getLIMCRED() {
		return LIMCRED;
	}

	public void setLIMCRED(Double lIMCRED) {
		LIMCRED = lIMCRED;
	}

	public Double getPVENDEDOR() {
		return PVENDEDOR;
	}

	public void setPVENDEDOR(Double pVENDEDOR) {
		PVENDEDOR = pVENDEDOR;
	}

	public Double getPGERENTE() {
		return PGERENTE;
	}

	public void setPGERENTE(Double pGERENTE) {
		PGERENTE = pGERENTE;
	}

	public Double getCONDPAG() {
		return CONDPAG;
	}

	public void setCONDPAG(Double cONDPAG) {
		CONDPAG = cONDPAG;
	}

	public Double getMALA() {
		return MALA;
	}

	public void setMALA(Double mALA) {
		MALA = mALA;
	}

	public Double getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(Double sTATUS) {
		STATUS = sTATUS;
	}

	public Double getA1_METR() {
		return A1_METR;
	}

	public void setA1_METR(Double a1_METR) {
		A1_METR = a1_METR;
	}

	public Double getA1_MSALDO() {
		return A1_MSALDO;
	}

	public void setA1_MSALDO(Double a1_MSALDO) {
		A1_MSALDO = a1_MSALDO;
	}

	public Double getA1_NROCOM() {
		return A1_NROCOM;
	}

	public void setA1_NROCOM(Double a1_NROCOM) {
		A1_NROCOM = a1_NROCOM;
	}

	public Double getA1_NROPAG() {
		return A1_NROPAG;
	}

	public void setA1_NROPAG(Double a1_NROPAG) {
		A1_NROPAG = a1_NROPAG;
	}

	public Double getA1_ATR() {
		return A1_ATR;
	}

	public void setA1_ATR(Double a1_ATR) {
		A1_ATR = a1_ATR;
	}

	public Double getA1_MATR() {
		return A1_MATR;
	}

	public void setA1_MATR(Double a1_MATR) {
		A1_MATR = a1_MATR;
	}

	public Double getFRETE() {
		return FRETE;
	}

	public void setFRETE(Double fRETE) {
		FRETE = fRETE;
	}

	public Boolean getBOX1() {
		return BOX1;
	}

	public void setBOX1(Boolean bOX1) {
		BOX1 = bOX1;
	}

	public Boolean getBOX2() {
		return BOX2;
	}

	public void setBOX2(Boolean bOX2) {
		BOX2 = bOX2;
	}

	public Boolean getBOX3() {
		return BOX3;
	}

	public void setBOX3(Boolean bOX3) {
		BOX3 = bOX3;
	}

	public Boolean getBOX4() {
		return BOX4;
	}

	public void setBOX4(Boolean bOX4) {
		BOX4 = bOX4;
	}

	public Boolean getBOXCOB1() {
		return BOXCOB1;
	}

	public void setBOXCOB1(Boolean bOXCOB1) {
		BOXCOB1 = bOXCOB1;
	}

	public Boolean getBOXCOB2() {
		return BOXCOB2;
	}

	public void setBOXCOB2(Boolean bOXCOB2) {
		BOXCOB2 = bOXCOB2;
	}

	public Boolean getBOXENT1() {
		return BOXENT1;
	}

	public void setBOXENT1(Boolean bOXENT1) {
		BOXENT1 = bOXENT1;
	}

	public Boolean getBOXENT2() {
		return BOXENT2;
	}

	public void setBOXENT2(Boolean bOXENT2) {
		BOXENT2 = bOXENT2;
	}

	public Boolean getEXPORTA() {
		return EXPORTA;
	}

	public void setEXPORTA(Boolean eXPORTA) {
		EXPORTA = eXPORTA;
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

	public String getOBS() {
		return OBS;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
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

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

	public Boolean getJURIDICA() {
		return JURIDICA;
	}

	public void setJURIDICA(Boolean jURIDICA) {
		JURIDICA = jURIDICA;
	}

	public Boolean getFISICA() {
		return FISICA;
	}

	public void setFISICA(Boolean fISICA) {
		FISICA = fISICA;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public Double getTxent() {
		return txent;
	}

	public void setTxent(Double txent) {
		this.txent = txent;
	}
	
}
