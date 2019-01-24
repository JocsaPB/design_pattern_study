package study.designpattern.jocsa.creational.factories;

/**
 * @author Jocsa
 * Factory: 			classe que contem uma função FactoryMethod (não presente no GOF)
 * FactoryMethod: 		uma função separada que retorna um objeto completo
 * AbstractFactory: 	possibilita a criação de hierarquias entre fábricas
 */
public class FactoryMethodDemo {
	public static void main(String[] args) {
		Point newCartesianPoint = Point.Factory.newCartesianPoint(2, 3);
		Point newPolarPoint = Point.Factory.newPolarPoint(10, 20);
		
		System.out.println("newCartesianPoint: " + newCartesianPoint);
		System.out.println("newPolarPoint: " + newPolarPoint);
	}
}

class Point {
	
	private double x, z;

	private Point(double x, double z) {
		super();
		this.x = x;
		this.z = z;
	}
	
	@Override
	public String toString() {
		return "Point [x=" + x + ", z=" + z + "]";
	}

	// Factory (não presente no GOF)
	public static class Factory {
		// Não se tornariam possíveis ter dois construtores com parâmeros double, então foi aplicado o padrão FactoryMethod
		
		public static Point newPolarPoint(double rho, double theta) {
			return new Point(rho*Math.cos(theta), rho*Math.sin(theta));
		}
		
		public static Point newCartesianPoint(double x, double y) {
			return new Point(x, y);
		}
	}
}
