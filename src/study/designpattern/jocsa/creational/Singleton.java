package study.designpattern.jocsa.creational;

import org.apache.commons.lang3.SerializationUtils;

public class Singleton {

	public static void main(String[] args) {
		BasicSingleton singletonOne = BasicSingleton.getInstance();
		
		singletonOne.setValue(100);
		System.out.println("Print singleton value, setting the value: " + singletonOne.getValue());
		
		BasicSingleton singletonTwo = BasicSingleton.getInstance();
		
		System.out.println("\nPrint the singleton value, withou setting value: " + singletonTwo.getValue());
		System.out.println("singletonOne is equal singletonTwo: " + (singletonOne == singletonTwo));
		
		EnumBasicSingleton enumBasic = EnumBasicSingleton.INSTANCE;
		enumBasic.setValue(15);
		EnumBasicSingleton enumClone = SerializationUtils.roundtrip(enumBasic);
		System.out.println("\nOriginal value = 1; Value of enumClone: " + enumClone.getValue());
	}
	
}

/*
 * Torna-se um caso básico de uso pois a singularidade pode ser facilmente quebrada:
 * 		1 - através de serialization (cria uma nova instancia ao deserializar)
 * 		2 - reflection (alterando a visibilidade do construtor para público)
 * */
class BasicSingleton {
	
	private int value = 0;
	
	private static BasicSingleton instance = new BasicSingleton();
	
	private BasicSingleton() {}
	
	public static BasicSingleton getInstance() {
		return instance;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}

// THREAD SAFE
class LazySingleton {
	
	private int value = 0;
	
	private static LazySingleton instance = null;
	
	private LazySingleton() {}
	
	public static synchronized LazySingleton getInstance() {
		if(instance == null) {
			instance = new LazySingleton();
		}
		
		return instance;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}

class SingletonThreadSafe {
	
	private SingletonThreadSafe() {}
	
	private static class Impl {
		
		private static final SingletonThreadSafe INSTANCE = new SingletonThreadSafe();
	}
	
	public static SingletonThreadSafe getInstance() {
		return Impl.INSTANCE;
	}
	
}

enum EnumBasicSingleton {
	
	INSTANCE;
	
	private int value;

	EnumBasicSingleton() {
		this.value = 1;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}

class MonostateSingleton {
	
	// Não importa quantas instâncias sejam da classe, o valor sempre será o mesmo
	private static int value;

	public static int getValue() {
		return value;
	}

	public static void setValue(int value) {
		MonostateSingleton.value = value;
	}
	
}


