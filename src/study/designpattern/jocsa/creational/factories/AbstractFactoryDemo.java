package study.designpattern.jocsa.creational.factories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Jocsa AbstractFactory: possibilita a criação de hierarquias entre fábricas
 */
public class AbstractFactoryDemo {

}

interface IceDrink {

	void consume();
}

class Juice implements IceDrink {

	@Override
	public void consume() {
		System.out.println("Cheers!");
	}

}

class Coke implements IceDrink {

	@Override
	public void consume() {
		System.out.println("Delicious!");
	}

}

// Classe abstrata ou Interface que define o contrato do AbstractFactory
interface DrinkFactory {
	IceDrink prepare(int qnt);
}

// Implementação 1 do contrato da interface o classe abstrata relativa ao AbastractFactory
class JuiceFactory implements DrinkFactory {

	@Override
	public Juice prepare(int qnt) {
		System.out.println("Each 1000ml takes 1/2 second to get ready.");
		System.out.println("Making " + qnt + " jucies...");
		
		try {
			Thread.sleep(qnt * 500);
			return new Juice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

}

//Implementação 2 do contrato da interface o classe abstrata relativa ao AbastractFactory
class CokeFactory implements DrinkFactory {

	@Override
	public Coke prepare(int qnt) {
		System.out.println("Each 1000ml takes 1 second to get ready.");
		System.out.println("Making " + qnt + " Coke...");

		try {
			Thread.sleep(qnt * 1000);
			return new Coke();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

}

// AbstractFactory. Realiza a criação do objeto independente da implementação
class MachineDrinkFactory {

	public enum Drinks {
		
		JUICE("0"), COKE("1");
		
		String code;
		
		Drinks(String drink){
			code = drink;
		}

		public String getCode() {
			return code;
		}
		
		public static Drinks getEnum(String value) {
	        for(Drinks v : values())
	            if(v.getCode().equalsIgnoreCase(value)) return v;
	        throw new IllegalArgumentException();
	    }

	}

	public IceDrink makeDrink() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.print("Choose. 0 for JUICE or 1 for COKE: ");
			String drink = reader.readLine();
			
			Integer type = null;
			
			try {
				type = Integer.valueOf(drink);
			} catch (Exception e) {
			}
			
			
			if (type == null || !type.equals( Drinks.JUICE.ordinal() ) && !type.equals( Drinks.COKE.ordinal()) ) {
				System.out.println("Try again...");
			} else {

				while (true) {

					System.out.print("Tell us the quantity of drinks:");
					String qnt = reader.readLine();
					
					Integer qntConverted = null;
					
					try {
						qntConverted = Integer.valueOf(qnt);
					} catch (Exception e) {
					}

					if (qntConverted != null && qntConverted >= 1) {
						
						Drinks valueOf = Drinks.getEnum(type.toString());
						
						switch ( valueOf ) {
						case JUICE:
							return makeDrink(new JuiceFactory(), qntConverted);
						case COKE:
							return makeDrink(new CokeFactory(), qntConverted);
						default:
							System.out.println("Erro...");
							break;
						}
					} else {
						System.out.print("\nTry again... ");
					}
				}

			}
		}
	}

	public IceDrink makeDrink(DrinkFactory drinkFactory, int qnt) {
		return drinkFactory.prepare(qnt);
	}
}

class DemoDrinkFactory {

	public static void main(String[] args) throws IOException {
		MachineDrinkFactory machineDrinkFactory = new MachineDrinkFactory();
		
		machineDrinkFactory.makeDrink(new JuiceFactory(), 1).consume();
		
		// interativo
		machineDrinkFactory.makeDrink().consume();
	}

}
