package entidades;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tribcofins {

	@Id
	@GeneratedValue
	private Long idtribcofins;
	private String titulo, numero;
	
	
	
	public Long getIdtribcofins() {
		return idtribcofins;
	}
	public void setIdtribcofins(Long idtribcofins) {
		this.idtribcofins = idtribcofins;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
}