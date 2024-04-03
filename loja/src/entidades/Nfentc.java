package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Nfentc {

	@Id
	@GeneratedValue
	private Long idnfentc;
	private String CODEMP,NUMDOC,SERIE,CODFOR,PEDIDO,NATOPER,CODPED,MOEDADI,STATUS,OBS1,OBS2,
	OBS3,OBS4,OBS5,TRANSP,PLACA,XML,CHAVENFE,DESCFOR;
	private Double VRMERC,VRICM,VRIPI,VRTOT,VRDESC,VRFRETE,PICM,BASEICM,CODPAG,PISS,VRISS,BASESUBST,ICMSUBST,TOTSEG,TOTACESS,
	VRPIS,VRCOFINS,PPIS,PCOFINS;
	private Date EMISSAO,VENCTO,DATADEV;
	private Boolean STATE;
	
	@Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;
	
	public Long getIdnfentc() {
		return idnfentc;
	}
	public void setIdnfentc(Long idnfentc) {
		this.idnfentc = idnfentc;
	}
	public String getCODEMP() {
		return CODEMP;
	}
	public void setCODEMP(String cODEMP) {
		CODEMP = cODEMP;
	}
	public String getNUMDOC() {
		return NUMDOC;
	}
	public void setNUMDOC(String nUMDOC) {
		NUMDOC = nUMDOC;
	}
	public String getSERIE() {
		return SERIE;
	}
	public void setSERIE(String sERIE) {
		SERIE = sERIE;
	}
	public String getCODFOR() {
		return CODFOR;
	}
	public void setCODFOR(String cODFOR) {
		CODFOR = cODFOR;
	}
	public String getPEDIDO() {
		return PEDIDO;
	}
	public void setPEDIDO(String pEDIDO) {
		PEDIDO = pEDIDO;
	}
	public String getNATOPER() {
		return NATOPER;
	}
	public void setNATOPER(String nATOPER) {
		NATOPER = nATOPER;
	}
	public String getCODPED() {
		return CODPED;
	}
	public void setCODPED(String cODPED) {
		CODPED = cODPED;
	}
	public String getMOEDADI() {
		return MOEDADI;
	}
	public void setMOEDADI(String mOEDADI) {
		MOEDADI = mOEDADI;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getOBS1() {
		return OBS1;
	}
	public void setOBS1(String oBS1) {
		OBS1 = oBS1;
	}
	public String getOBS2() {
		return OBS2;
	}
	public void setOBS2(String oBS2) {
		OBS2 = oBS2;
	}
	public String getOBS3() {
		return OBS3;
	}
	public void setOBS3(String oBS3) {
		OBS3 = oBS3;
	}
	public String getOBS4() {
		return OBS4;
	}
	public void setOBS4(String oBS4) {
		OBS4 = oBS4;
	}
	public String getOBS5() {
		return OBS5;
	}
	public void setOBS5(String oBS5) {
		OBS5 = oBS5;
	}
	public String getTRANSP() {
		return TRANSP;
	}
	public void setTRANSP(String tRANSP) {
		TRANSP = tRANSP;
	}
	public String getPLACA() {
		return PLACA;
	}
	public void setPLACA(String pLACA) {
		PLACA = pLACA;
	}
	public String getXML() {
		return XML;
	}
	public void setXML(String xML) {
		XML = xML;
	}
	public String getCHAVENFE() {
		return CHAVENFE;
	}
	public void setCHAVENFE(String cHAVENFE) {
		CHAVENFE = cHAVENFE;
	}
	public Double getVRMERC() {
		return VRMERC;
	}
	public void setVRMERC(Double vRMERC) {
		VRMERC = vRMERC;
	}
	public Double getVRICM() {
		return VRICM;
	}
	public void setVRICM(Double vRICM) {
		VRICM = vRICM;
	}
	public Double getVRIPI() {
		return VRIPI;
	}
	public void setVRIPI(Double vRIPI) {
		VRIPI = vRIPI;
	}
	public Double getVRTOT() {
		return VRTOT;
	}
	public void setVRTOT(Double vRTOT) {
		VRTOT = vRTOT;
	}
	public Double getVRDESC() {
		return VRDESC;
	}
	public void setVRDESC(Double vRDESC) {
		VRDESC = vRDESC;
	}
	public Double getVRFRETE() {
		return VRFRETE;
	}
	public void setVRFRETE(Double vRFRETE) {
		VRFRETE = vRFRETE;
	}
	public Double getPICM() {
		return PICM;
	}
	public void setPICM(Double pICM) {
		PICM = pICM;
	}
	public Double getBASEICM() {
		return BASEICM;
	}
	public void setBASEICM(Double bASEICM) {
		BASEICM = bASEICM;
	}
	public Double getCODPAG() {
		return CODPAG;
	}
	public void setCODPAG(Double cODPAG) {
		CODPAG = cODPAG;
	}
	public Double getPISS() {
		return PISS;
	}
	public void setPISS(Double pISS) {
		PISS = pISS;
	}
	public Double getVRISS() {
		return VRISS;
	}
	public void setVRISS(Double vRISS) {
		VRISS = vRISS;
	}
	public Double getBASESUBST() {
		return BASESUBST;
	}
	public void setBASESUBST(Double bASESUBST) {
		BASESUBST = bASESUBST;
	}
	public Double getICMSUBST() {
		return ICMSUBST;
	}
	public void setICMSUBST(Double iCMSUBST) {
		ICMSUBST = iCMSUBST;
	}
	public Double getTOTSEG() {
		return TOTSEG;
	}
	public void setTOTSEG(Double tOTSEG) {
		TOTSEG = tOTSEG;
	}
	public Double getTOTACESS() {
		return TOTACESS;
	}
	public void setTOTACESS(Double tOTACESS) {
		TOTACESS = tOTACESS;
	}
	public Double getVRPIS() {
		return VRPIS;
	}
	public void setVRPIS(Double vRPIS) {
		VRPIS = vRPIS;
	}
	public Double getVRCOFINS() {
		return VRCOFINS;
	}
	public void setVRCOFINS(Double vRCOFINS) {
		VRCOFINS = vRCOFINS;
	}
	public Double getPPIS() {
		return PPIS;
	}
	public void setPPIS(Double pPIS) {
		PPIS = pPIS;
	}
	public Double getPCOFINS() {
		return PCOFINS;
	}
	public void setPCOFINS(Double pCOFINS) {
		PCOFINS = pCOFINS;
	}
	public Date getEMISSAO() {
		return EMISSAO;
	}
	public void setEMISSAO(Date eMISSAO) {
		EMISSAO = eMISSAO;
	}
	public Date getVENCTO() {
		return VENCTO;
	}
	public void setVENCTO(Date vENCTO) {
		VENCTO = vENCTO;
	}
	public Date getDATADEV() {
		return DATADEV;
	}
	public void setDATADEV(Date dATADEV) {
		DATADEV = dATADEV;
	}
	public Boolean getSTATE() {
		return STATE;
	}
	public void setSTATE(Boolean sTATE) {
		STATE = sTATE;
	}
	public String getSql_deleted() {
		return sql_deleted;
	}
	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}
	public String getDESCFOR() {
		return DESCFOR;
	}
	public void setDESCFOR(String dESCFOR) {
		DESCFOR = dESCFOR;
	}

	
	
	
	
	

}
