package modelo;

public class Cancion {
	
	private int codigo;
	private String titulo;
	private Interprete interprete;
	private Estilo estilo;
	private String rutaFichero;
	private int numReproduccion;
	
	
	public Cancion(String titulo, Interprete interprete, Estilo estilo, String rutaFichero, int numReproduccion){
		codigo = 0;
		this.titulo = titulo;
		this.interprete = interprete;
		this.estilo = estilo;
		this.rutaFichero = rutaFichero;
		this.numReproduccion = numReproduccion;
	}
	
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Interprete getInterprete() {
		return interprete;
	}
	
	public void setInterprete(Interprete interprete) {
		this.interprete = interprete;
	}
	
	public Estilo getEstilo() {
		return estilo;
	}
	
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	
	public String getRutaFichero() {
		return rutaFichero;
	}
	
	public void setRutaFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
	}
	
	public int getNumReproduccion() {
		return numReproduccion;
	}
	
	public void addNumReproduccion() {
		this.numReproduccion++;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estilo == null) ? 0 : estilo.hashCode());
		result = prime * result + ((interprete == null) ? 0 : interprete.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cancion other = (Cancion) obj;
		if (estilo == null) {
			if (other.estilo != null)
				return false;
		} else if (!estilo.equals(other.estilo))
			return false;
		if (interprete == null) {
			if (other.interprete != null)
				return false;
		} else if (!interprete.equals(other.interprete))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
}
