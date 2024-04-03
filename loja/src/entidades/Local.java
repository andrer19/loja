package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Local {
	
	@Id
	@GeneratedValue
	public Long idlocal;
		
	public String lic,libera,empresa;

	public Long getIdlocal() {
		return idlocal;
	}

	public void setIdlocal(Long idlocal) {
		this.idlocal = idlocal;
	}

	public String getLic() {
		return lic;
	}

	public void setLic(String lic) {
		this.lic = lic;
	}

	public String getLibera() {
		return libera;
	}

	public void setLibera(String libera) {
		this.libera = libera;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	
	
	
	
	
	
	
	
	


}
