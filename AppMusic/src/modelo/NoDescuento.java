package modelo;

public class NoDescuento implements Descuento {

	@Override
	public double calcDescuento() {
		return 0;
	}

}
