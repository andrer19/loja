package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Procli {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idprocli;

    public String CODEMP,CODPRO,DESCPRO,UNIDADE,CODCLI,DESCCLI,POSIPI,PASTA,MAQUINA,DIMENSAO,
    ESTRUTURA,DOSAGMIN,DOSAGMAX,DENSIDADE,TEMPVERT,TEMPHORIZ,SACHET,CANINHO,A7_CLIENTE,A7_LOJA,PEDCLI,A7_LOCEST,A7_PLANTA,
    A7_LOCENT,CODPROSUB,CODPROPAR; 
    
    public Double PRECO1,PRECO2,PRECO3,PRECO4,PRECO5,PRECO6,PRECO7,PRECO8,PRECO9,PRECO10,PRECO11,PRECO12,
    CUSTO,A7_CONV,QTACUM,PICM,PIPI;
    
    public Date DATA01,DATA02,DATA03,DATA04,DATA05,DATA06,DATA07,DATA08,DATA09,DATA10,DATA11,DATA12;
    
    @Column(columnDefinition="LONGTEXT")
    public String OBS;
    
    @Column(nullable=false,columnDefinition ="ENUM('F','T')")
  	private String sql_deleted;

	public Long getIdprocli() {
		return idprocli;
	}

	public void setIdprocli(Long idprocli) {
		this.idprocli = idprocli;
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

	public String getUNIDADE() {
		return UNIDADE;
	}

	public void setUNIDADE(String uNIDADE) {
		UNIDADE = uNIDADE;
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

	public String getPOSIPI() {
		return POSIPI;
	}

	public void setPOSIPI(String pOSIPI) {
		POSIPI = pOSIPI;
	}

	public String getPASTA() {
		return PASTA;
	}

	public void setPASTA(String pASTA) {
		PASTA = pASTA;
	}

	public String getMAQUINA() {
		return MAQUINA;
	}

	public void setMAQUINA(String mAQUINA) {
		MAQUINA = mAQUINA;
	}

	public String getDIMENSAO() {
		return DIMENSAO;
	}

	public void setDIMENSAO(String dIMENSAO) {
		DIMENSAO = dIMENSAO;
	}

	public String getESTRUTURA() {
		return ESTRUTURA;
	}

	public void setESTRUTURA(String eSTRUTURA) {
		ESTRUTURA = eSTRUTURA;
	}

	public String getDOSAGMIN() {
		return DOSAGMIN;
	}

	public void setDOSAGMIN(String dOSAGMIN) {
		DOSAGMIN = dOSAGMIN;
	}

	public String getDOSAGMAX() {
		return DOSAGMAX;
	}

	public void setDOSAGMAX(String dOSAGMAX) {
		DOSAGMAX = dOSAGMAX;
	}

	public String getDENSIDADE() {
		return DENSIDADE;
	}

	public void setDENSIDADE(String dENSIDADE) {
		DENSIDADE = dENSIDADE;
	}

	public String getTEMPVERT() {
		return TEMPVERT;
	}

	public void setTEMPVERT(String tEMPVERT) {
		TEMPVERT = tEMPVERT;
	}

	public String getTEMPHORIZ() {
		return TEMPHORIZ;
	}

	public void setTEMPHORIZ(String tEMPHORIZ) {
		TEMPHORIZ = tEMPHORIZ;
	}

	public String getSACHET() {
		return SACHET;
	}

	public void setSACHET(String sACHET) {
		SACHET = sACHET;
	}

	public String getCANINHO() {
		return CANINHO;
	}

	public void setCANINHO(String cANINHO) {
		CANINHO = cANINHO;
	}

	public String getA7_CLIENTE() {
		return A7_CLIENTE;
	}

	public void setA7_CLIENTE(String a7_CLIENTE) {
		A7_CLIENTE = a7_CLIENTE;
	}

	public String getA7_LOJA() {
		return A7_LOJA;
	}

	public void setA7_LOJA(String a7_LOJA) {
		A7_LOJA = a7_LOJA;
	}

	public String getPEDCLI() {
		return PEDCLI;
	}

	public void setPEDCLI(String pEDCLI) {
		PEDCLI = pEDCLI;
	}

	public String getA7_LOCEST() {
		return A7_LOCEST;
	}

	public void setA7_LOCEST(String a7_LOCEST) {
		A7_LOCEST = a7_LOCEST;
	}

	public String getA7_PLANTA() {
		return A7_PLANTA;
	}

	public void setA7_PLANTA(String a7_PLANTA) {
		A7_PLANTA = a7_PLANTA;
	}

	public String getA7_LOCENT() {
		return A7_LOCENT;
	}

	public void setA7_LOCENT(String a7_LOCENT) {
		A7_LOCENT = a7_LOCENT;
	}

	public String getCODPROSUB() {
		return CODPROSUB;
	}

	public void setCODPROSUB(String cODPROSUB) {
		CODPROSUB = cODPROSUB;
	}

	public String getCODPROPAR() {
		return CODPROPAR;
	}

	public void setCODPROPAR(String cODPROPAR) {
		CODPROPAR = cODPROPAR;
	}

	public Double getPRECO1() {
		return PRECO1;
	}

	public void setPRECO1(Double pRECO1) {
		PRECO1 = pRECO1;
	}

	public Double getPRECO2() {
		return PRECO2;
	}

	public void setPRECO2(Double pRECO2) {
		PRECO2 = pRECO2;
	}

	public Double getPRECO3() {
		return PRECO3;
	}

	public void setPRECO3(Double pRECO3) {
		PRECO3 = pRECO3;
	}

	public Double getPRECO4() {
		return PRECO4;
	}

	public void setPRECO4(Double pRECO4) {
		PRECO4 = pRECO4;
	}

	public Double getPRECO5() {
		return PRECO5;
	}

	public void setPRECO5(Double pRECO5) {
		PRECO5 = pRECO5;
	}

	public Double getPRECO6() {
		return PRECO6;
	}

	public void setPRECO6(Double pRECO6) {
		PRECO6 = pRECO6;
	}

	public Double getPRECO7() {
		return PRECO7;
	}

	public void setPRECO7(Double pRECO7) {
		PRECO7 = pRECO7;
	}

	public Double getPRECO8() {
		return PRECO8;
	}

	public void setPRECO8(Double pRECO8) {
		PRECO8 = pRECO8;
	}

	public Double getPRECO9() {
		return PRECO9;
	}

	public void setPRECO9(Double pRECO9) {
		PRECO9 = pRECO9;
	}

	public Double getPRECO10() {
		return PRECO10;
	}

	public void setPRECO10(Double pRECO10) {
		PRECO10 = pRECO10;
	}

	public Double getPRECO11() {
		return PRECO11;
	}

	public void setPRECO11(Double pRECO11) {
		PRECO11 = pRECO11;
	}

	public Double getPRECO12() {
		return PRECO12;
	}

	public void setPRECO12(Double pRECO12) {
		PRECO12 = pRECO12;
	}

	public Double getCUSTO() {
		return CUSTO;
	}

	public void setCUSTO(Double cUSTO) {
		CUSTO = cUSTO;
	}

	public Double getA7_CONV() {
		return A7_CONV;
	}

	public void setA7_CONV(Double a7_CONV) {
		A7_CONV = a7_CONV;
	}

	public Double getQTACUM() {
		return QTACUM;
	}

	public void setQTACUM(Double qTACUM) {
		QTACUM = qTACUM;
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

	public Date getDATA01() {
		return DATA01;
	}

	public void setDATA01(Date dATA01) {
		DATA01 = dATA01;
	}

	public Date getDATA02() {
		return DATA02;
	}

	public void setDATA02(Date dATA02) {
		DATA02 = dATA02;
	}

	public Date getDATA03() {
		return DATA03;
	}

	public void setDATA03(Date dATA03) {
		DATA03 = dATA03;
	}

	public Date getDATA04() {
		return DATA04;
	}

	public void setDATA04(Date dATA04) {
		DATA04 = dATA04;
	}

	public Date getDATA05() {
		return DATA05;
	}

	public void setDATA05(Date dATA05) {
		DATA05 = dATA05;
	}

	public Date getDATA06() {
		return DATA06;
	}

	public void setDATA06(Date dATA06) {
		DATA06 = dATA06;
	}

	public Date getDATA07() {
		return DATA07;
	}

	public void setDATA07(Date dATA07) {
		DATA07 = dATA07;
	}

	public Date getDATA08() {
		return DATA08;
	}

	public void setDATA08(Date dATA08) {
		DATA08 = dATA08;
	}

	public Date getDATA09() {
		return DATA09;
	}

	public void setDATA09(Date dATA09) {
		DATA09 = dATA09;
	}

	public Date getDATA10() {
		return DATA10;
	}

	public void setDATA10(Date dATA10) {
		DATA10 = dATA10;
	}

	public Date getDATA11() {
		return DATA11;
	}

	public void setDATA11(Date dATA11) {
		DATA11 = dATA11;
	}

	public Date getDATA12() {
		return DATA12;
	}

	public void setDATA12(Date dATA12) {
		DATA12 = dATA12;
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
