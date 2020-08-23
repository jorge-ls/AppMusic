package modelo;

public class DescuentoJovenes implements Descuento {

	@Override
	public double calcDescuento() {
		return 0.5;	// 50%
	}

}
