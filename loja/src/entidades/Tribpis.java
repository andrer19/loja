package entidades;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tribpis {

	@Id
	@GeneratedValue
	private Long idtribpis;
	private String titulo, numero;
	
	public Long getIdtribpis() {
		return idtribpis;
	}
	public void setIdtribpis(Long idtribpis) {
		this.idtribpis = idtribpis;
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