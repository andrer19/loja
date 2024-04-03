package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cadnat {
	
	@Id
	@GeneratedValue
	private Long idcadnat;
	
	private String NUMERO,CFOP,DESCNAT,BASEICM,BASEPIS,BASECOFINS,BASECFE,TIPOPRECO,TIPO,OBSERVACAO,
	SITTRIB,OBS1,OBS2,OBS3,OBS4,OBS5,OBS6;
	
	private Double P_IPI,P_ICM,P_ISS,P_PIS,P_COFINS,COD_ICMS,COD_IPI,COD_PIS,COD_COFINS,P_SUBTRIB;
	
	private boolean DESTINO,ENDERECO,CHAVE,QTDE,PEDIDO,NFR,PRECO,DUPLICATA,BAIXAEST,ENTRAESTER,NFRS,NFRE,
	REQUISICAO,ICM_DIF,IPI_DIF,TERCEIROS,PERMANENTE,DIPI,EXPORT,IPI50;
	
	@Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;

	public Long getIdcadnat() {
		return idcadnat;
	}

	public void setIdcadnat(Long idcadnat) {
		this.idcadnat = idcadnat;
	}

	public String getNUMERO() {
		return NUMERO;
	}

	public void setNUMERO(String nUMERO) {
		NUMERO = nUMERO;
	}

	public String getCFOP() {
		return CFOP;
	}

	public void setCFOP(String cFOP) {
		CFOP = cFOP;
	}

	public String getDESCNAT() {
		return DESCNAT;
	}

	public void setDESCNAT(String dESCNAT) {
		DESCNAT = dESCNAT;
	}

	public String getBASEICM() {
		return BASEICM;
	}

	public void setBASEICM(String bASEICM) {
		BASEICM = bASEICM;
	}

	public String getBASEPIS() {
		return BASEPIS;
	}

	public void setBASEPIS(String bASEPIS) {
		BASEPIS = bASEPIS;
	}

	public String getBASECOFINS() {
		return BASECOFINS;
	}

	public void setBASECOFINS(String bASECOFINS) {
		BASECOFINS = bASECOFINS;
	}

	public String getBASECFE() {
		return BASECFE;
	}

	public void setBASECFE(String bASECFE) {
		BASECFE = bASECFE;
	}

	public String getTIPOPRECO() {
		return TIPOPRECO;
	}

	public void setTIPOPRECO(String tIPOPRECO) {
		TIPOPRECO = tIPOPRECO;
	}

	public String getTIPO() {
		return TIPO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public String getOBSERVACAO() {
		return OBSERVACAO;
	}

	public void setOBSERVACAO(String oBSERVACAO) {
		OBSERVACAO = oBSERVACAO;
	}

	public String getSITTRIB() {
		return SITTRIB;
	}

	public void setSITTRIB(String sITTRIB) {
		SITTRIB = sITTRIB;
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

	public String getOBS6() {
		return OBS6;
	}

	public void setOBS6(String oBS6) {
		OBS6 = oBS6;
	}

	public Double getP_IPI() {
		return P_IPI;
	}

	public void setP_IPI(Double p_IPI) {
		P_IPI = p_IPI;
	}

	public Double getP_ICM() {
		return P_ICM;
	}

	public void setP_ICM(Double p_ICM) {
		P_ICM = p_ICM;
	}

	public Double getP_ISS() {
		return P_ISS;
	}

	public void setP_ISS(Double p_ISS) {
		P_ISS = p_ISS;
	}

	public Double getP_PIS() {
		return P_PIS;
	}

	public void setP_PIS(Double p_PIS) {
		P_PIS = p_PIS;
	}

	public Double getP_COFINS() {
		return P_COFINS;
	}

	public void setP_COFINS(Double p_COFINS) {
		P_COFINS = p_COFINS;
	}

	public Double getCOD_ICMS() {
		return COD_ICMS;
	}

	public void setCOD_ICMS(Double cOD_ICMS) {
		COD_ICMS = cOD_ICMS;
	}

	public Double getCOD_IPI() {
		return COD_IPI;
	}

	public void setCOD_IPI(Double cOD_IPI) {
		COD_IPI = cOD_IPI;
	}

	public Double getCOD_PIS() {
		return COD_PIS;
	}

	public void setCOD_PIS(Double cOD_PIS) {
		COD_PIS = cOD_PIS;
	}

	public Double getCOD_COFINS() {
		return COD_COFINS;
	}

	public void setCOD_COFINS(Double cOD_COFINS) {
		COD_COFINS = cOD_COFINS;
	}

	public Double getP_SUBTRIB() {
		return P_SUBTRIB;
	}

	public void setP_SUBTRIB(Double p_SUBTRIB) {
		P_SUBTRIB = p_SUBTRIB;
	}

	public boolean isDESTINO() {
		return DESTINO;
	}

	public void setDESTINO(boolean dESTINO) {
		DESTINO = dESTINO;
	}

	public boolean isENDERECO() {
		return ENDERECO;
	}

	public void setENDERECO(boolean eNDERECO) {
		ENDERECO = eNDERECO;
	}

	public boolean isCHAVE() {
		return CHAVE;
	}

	public void setCHAVE(boolean cHAVE) {
		CHAVE = cHAVE;
	}

	public boolean isQTDE() {
		return QTDE;
	}

	public void setQTDE(boolean qTDE) {
		QTDE = qTDE;
	}

	public boolean isPEDIDO() {
		return PEDIDO;
	}

	public void setPEDIDO(boolean pEDIDO) {
		PEDIDO = pEDIDO;
	}

	public boolean isNFR() {
		return NFR;
	}

	public void setNFR(boolean nFR) {
		NFR = nFR;
	}

	public boolean isPRECO() {
		return PRECO;
	}

	public void setPRECO(boolean pRECO) {
		PRECO = pRECO;
	}

	public boolean isDUPLICATA() {
		return DUPLICATA;
	}

	public void setDUPLICATA(boolean dUPLICATA) {
		DUPLICATA = dUPLICATA;
	}

	public boolean isBAIXAEST() {
		return BAIXAEST;
	}

	public void setBAIXAEST(boolean bAIXAEST) {
		BAIXAEST = bAIXAEST;
	}

	public boolean isENTRAESTER() {
		return ENTRAESTER;
	}

	public void setENTRAESTER(boolean eNTRAESTER) {
		ENTRAESTER = eNTRAESTER;
	}

	public boolean isNFRS() {
		return NFRS;
	}

	public void setNFRS(boolean nFRS) {
		NFRS = nFRS;
	}

	public boolean isNFRE() {
		return NFRE;
	}

	public void setNFRE(boolean nFRE) {
		NFRE = nFRE;
	}

	public boolean isREQUISICAO() {
		return REQUISICAO;
	}

	public void setREQUISICAO(boolean rEQUISICAO) {
		REQUISICAO = rEQUISICAO;
	}

	public boolean isICM_DIF() {
		return ICM_DIF;
	}

	public void setICM_DIF(boolean iCM_DIF) {
		ICM_DIF = iCM_DIF;
	}

	public boolean isIPI_DIF() {
		return IPI_DIF;
	}

	public void setIPI_DIF(boolean iPI_DIF) {
		IPI_DIF = iPI_DIF;
	}

	public boolean isTERCEIROS() {
		return TERCEIROS;
	}

	public void setTERCEIROS(boolean tERCEIROS) {
		TERCEIROS = tERCEIROS;
	}

	public boolean isPERMANENTE() {
		return PERMANENTE;
	}

	public void setPERMANENTE(boolean pERMANENTE) {
		PERMANENTE = pERMANENTE;
	}

	public boolean isDIPI() {
		return DIPI;
	}

	public void setDIPI(boolean dIPI) {
		DIPI = dIPI;
	}

	public boolean isEXPORT() {
		return EXPORT;
	}

	public void setEXPORT(boolean eXPORT) {
		EXPORT = eXPORT;
	}

	public boolean isIPI50() {
		return IPI50;
	}

	public void setIPI50(boolean iPI50) {
		IPI50 = iPI50;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

	
	

}
