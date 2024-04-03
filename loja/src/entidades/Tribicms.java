package entidades;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tribicms {

	@Id
	@GeneratedValue
	private Long idtribicms;
	private String titulo, numero;
	
	
	public Long getIdtribicms() {
		return idtribicms;
	}
	public void setIdtribicms(Long idtribicms) {
		this.idtribicms = idtribicms;
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