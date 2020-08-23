package modelo;

public class DescuentoFijo implements Descuento{

	@Override
	public double calcDescuento() {	
		return 0.2;	// 20%
	}

}
