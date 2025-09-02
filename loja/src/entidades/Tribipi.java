package entidades;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tribipi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtribipi;
	private String titulo, numero;
	
	
	public Long getIdtribipi() {
		return idtribipi;
	}
	public void setIdtribipi(Long idtribipi) {
		this.idtribipi = idtribipi;
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