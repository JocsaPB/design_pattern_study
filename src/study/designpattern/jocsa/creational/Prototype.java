package study.designpattern.jocsa.creational;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

public class Prototype {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		// Utilizando a sobrescrita do método clone;
		ClientClone clientCloneA = new ClientClone("Test1 with cloneable", new CellNumbersClone(Arrays.asList("123456", "654321")));
		
		ClientClone clientCloneB = (ClientClone) clientCloneA.clone();
		
		clientCloneB.setName("Test2 with cloneable");
		
		System.out.println(clientCloneA);
		System.out.println(clientCloneB);
		System.out.println();

		// Usando construtores recebendo a própria classe como parâmetro
		ClientCloneConstructor clientCloneConstructorA = 
				new ClientCloneConstructor("Test1 with constructor", new CellNumbersCloneConstructor(Arrays.asList("123456", "654321")));
		
		ClientCloneConstructor clientCloneConstructorB
			= new ClientCloneConstructor(clientCloneConstructorA);
		
		clientCloneConstructorB.setName("Test2 with constructor");
		
		System.out.println(clientCloneConstructorA);
		System.out.println(clientCloneConstructorB);
		System.out.println();
		
		// Utilizando SerializableUtils  (serialize/deserialize)
		ClientCloneSerialize clientCloneSerializeA = 
				new ClientCloneSerialize("Test1 with serialize", new CellNumbersCloneSerialize(Arrays.asList("123456", "654321")));
		
		ClientCloneSerialize clientCloneSerializeB = SerializationUtils.roundtrip(clientCloneSerializeA);
		
		clientCloneSerializeB.setName("Test2 with serialize");
		
		System.out.println(clientCloneSerializeA);
		System.out.println(clientCloneSerializeB);		
		
	}
	
}

// Usando a interface cloneable para realizar a cópia
class ClientClone implements Cloneable {
	
	private String name;
	private CellNumbersClone cellNumbers;
	
	public ClientClone(String name, CellNumbersClone cellNumber) {
		super();
		this.name = name;
		this.cellNumbers = cellNumber;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new ClientClone(name, cellNumbers);
	}
	
	@Override
	public String toString() {
		return "Client: " + name + " - " + cellNumbers.toString();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCellNumbers(CellNumbersClone cellNumbers) {
		this.cellNumbers = cellNumbers;
	}
	
}

class CellNumbersClone implements Cloneable {
	
	List<String> numbers;

	public CellNumbersClone(List<String> numbers) {
		super();
		this.numbers = numbers;
	}
	
	@Override
	public String toString() {
		
		String numbers = "";
		
		for (String string : this.numbers) {
			numbers += string + " - ";
		}
		
		return numbers;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new CellNumbersClone(numbers);
	}

	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}
	
}


// Usando construtores ao invés de implementar a interface Cloneable, para criar as cópias
class ClientCloneConstructor {
	
	private String name;
	private CellNumbersCloneConstructor cellNumbers;
	
	public ClientCloneConstructor(String name, CellNumbersCloneConstructor cellNumber) {
		this.name = name;
		this.cellNumbers = cellNumber;
	}
	
	public ClientCloneConstructor(ClientCloneConstructor clientCloneConstructor) {
		this.name = clientCloneConstructor.name;
		this.cellNumbers = clientCloneConstructor.cellNumbers;
	}
	
	@Override
	public String toString() {
		return "Client: " + name + " - " + cellNumbers.toString();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCellNumbers(CellNumbersCloneConstructor cellNumbers) {
		this.cellNumbers = cellNumbers;
	}
	
}

class CellNumbersCloneConstructor {
	
	List<String> numbers;

	public CellNumbersCloneConstructor(List<String> numbers) {
		this.numbers = numbers;
	}
	
	public CellNumbersCloneConstructor(CellNumbersCloneConstructor cellNumbersCloneConstructor) {
		this.numbers = cellNumbersCloneConstructor.numbers;
	}
	
	@Override
	public String toString() {
		
		String numbers = "";
		
		for (String string : this.numbers) {
			numbers += string + " - ";
		}
		
		return numbers;
	}
	
	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}
	
}


// Usando a interface Serializable
class ClientCloneSerialize implements Serializable {
	
	private String name;
	private CellNumbersCloneSerialize cellNumbers;
	
	public ClientCloneSerialize(String name, CellNumbersCloneSerialize cellNumber) {
		this.name = name;
		this.cellNumbers = cellNumber;
	}
	@Override
	public String toString() {
		return "Client: " + name + " - " + cellNumbers.toString();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCellNumbers(CellNumbersCloneSerialize cellNumbers) {
		this.cellNumbers = cellNumbers;
	}
	
}

class CellNumbersCloneSerialize implements Serializable {
	
	List<String> numbers;

	public CellNumbersCloneSerialize(List<String> numbers) {
		this.numbers = numbers;
	}
		
	@Override
	public String toString() {
		
		String numbers = "";
		
		for (String string : this.numbers) {
			numbers += string + " - ";
		}
		
		return numbers;
	}
	
	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}
	
}

