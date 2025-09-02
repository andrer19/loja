package entidades;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cadmo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcadmo;

	private String codemp, CODMO, nome, ender, bairro, cidade, estado, cep,
			fone, RG, CPF, pis, funcao, lcnascimento, ESTCIVIL, banco, agencia,
			conta, CNH, ELEITOR,COMP, ESCOL, SETOR, MOTIVO, FAVORECIDO, NACION,
			FILIACAO, RESERV, TRANSP, OBS, CTPS;

	private Double STATUS, IR, ASSMED, RENDVAR, SEXO, QTDIARIA, VRUNIT;

	private Date DTNASC, ADESAO, FERIASI, FERIAST, GOZOI, GOZOT, DTDESLIG;
	
	@Column(nullable=false,columnDefinition ="ENUM('F','T')")
	private String sql_deleted;


	public Long getIdcadmo() {
		return idcadmo;
	}

	public void setIdcadmo(Long idcadmo) {
		this.idcadmo = idcadmo;
	}

	public String getCodemp() {
		return codemp;
	}

	public void setCodemp(String codemp) {
		this.codemp = codemp;
	}

	public String getCODMO() {
		return CODMO;
	}

	public void setCODMO(String cODMO) {
		CODMO = cODMO;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEnder() {
		return ender;
	}

	public void setEnder(String ender) {
		this.ender = ender;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getLcnascimento() {
		return lcnascimento;
	}

	public void setLcnascimento(String lcnascimento) {
		this.lcnascimento = lcnascimento;
	}

	public String getESTCIVIL() {
		return ESTCIVIL;
	}

	public void setESTCIVIL(String eSTCIVIL) {
		ESTCIVIL = eSTCIVIL;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getCNH() {
		return CNH;
	}

	public void setCNH(String cNH) {
		CNH = cNH;
	}

	public String getELEITOR() {
		return ELEITOR;
	}

	public void setELEITOR(String eLEITOR) {
		ELEITOR = eLEITOR;
	}

	public String getCOMP() {
		return COMP;
	}

	public void setCOMP(String cOMP) {
		COMP = cOMP;
	}

	public String getESCOL() {
		return ESCOL;
	}

	public void setESCOL(String eSCOL) {
		ESCOL = eSCOL;
	}

	public String getSETOR() {
		return SETOR;
	}

	public void setSETOR(String sETOR) {
		SETOR = sETOR;
	}

	public String getMOTIVO() {
		return MOTIVO;
	}

	public void setMOTIVO(String mOTIVO) {
		MOTIVO = mOTIVO;
	}

	public String getFAVORECIDO() {
		return FAVORECIDO;
	}

	public void setFAVORECIDO(String fAVORECIDO) {
		FAVORECIDO = fAVORECIDO;
	}

	public String getNACION() {
		return NACION;
	}

	public void setNACION(String nACION) {
		NACION = nACION;
	}

	public String getFILIACAO() {
		return FILIACAO;
	}

	public void setFILIACAO(String fILIACAO) {
		FILIACAO = fILIACAO;
	}

	public String getRESERV() {
		return RESERV;
	}

	public void setRESERV(String rESERV) {
		RESERV = rESERV;
	}

	public String getTRANSP() {
		return TRANSP;
	}

	public void setTRANSP(String tRANSP) {
		TRANSP = tRANSP;
	}

	public String getOBS() {
		return OBS;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
	}

	public String getCTPS() {
		return CTPS;
	}

	public void setCTPS(String cTPS) {
		CTPS = cTPS;
	}

	public Double getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(Double sTATUS) {
		STATUS = sTATUS;
	}

	public Double getIR() {
		return IR;
	}

	public void setIR(Double iR) {
		IR = iR;
	}

	public Double getASSMED() {
		return ASSMED;
	}

	public void setASSMED(Double aSSMED) {
		ASSMED = aSSMED;
	}

	public Double getRENDVAR() {
		return RENDVAR;
	}

	public void setRENDVAR(Double rENDVAR) {
		RENDVAR = rENDVAR;
	}

	public Double getSEXO() {
		return SEXO;
	}

	public void setSEXO(Double sEXO) {
		SEXO = sEXO;
	}

	public Double getQTDIARIA() {
		return QTDIARIA;
	}

	public void setQTDIARIA(Double qTDIARIA) {
		QTDIARIA = qTDIARIA;
	}

	public Double getVRUNIT() {
		return VRUNIT;
	}

	public void setVRUNIT(Double vRUNIT) {
		VRUNIT = vRUNIT;
	}

	public Date getDTNASC() {
		return DTNASC;
	}

	public void setDTNASC(Date dTNASC) {
		DTNASC = dTNASC;
	}

	public Date getADESAO() {
		return ADESAO;
	}

	public void setADESAO(Date aDESAO) {
		ADESAO = aDESAO;
	}

	public Date getFERIASI() {
		return FERIASI;
	}

	public void setFERIASI(Date fERIASI) {
		FERIASI = fERIASI;
	}

	public Date getFERIAST() {
		return FERIAST;
	}

	public void setFERIAST(Date fERIAST) {
		FERIAST = fERIAST;
	}

	public Date getGOZOI() {
		return GOZOI;
	}

	public void setGOZOI(Date gOZOI) {
		GOZOI = gOZOI;
	}

	public Date getGOZOT() {
		return GOZOT;
	}

	public void setGOZOT(Date gOZOT) {
		GOZOT = gOZOT;
	}

	public Date getDTDESLIG() {
		return DTDESLIG;
	}

	public void setDTDESLIG(Date dTDESLIG) {
		DTDESLIG = dTDESLIG;
	}

	public String getSql_deleted() {
		return sql_deleted;
	}

	public void setSql_deleted(String sql_deleted) {
		this.sql_deleted = sql_deleted;
	}

	

}
