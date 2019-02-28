package study.designpattern.jocsa.creational;

public class Adapter {

	public static void main(String[] args) {
		
		Gun gun = new Gun();
		
		System.out.println("Calling the doAttack with the Gun whos implements Attack directly.");
		doAttack(gun);
		
		System.out.println("\nCalling the doAttack passing Trap throw the Adapter.");
		TrapToAttackAdapter trapToAttackAdapter = new TrapToAttackAdapter(new Trap());
		
		doAttack(trapToAttackAdapter);
	}
	
	public static void doAttack(Attack attack) {
		System.out.println(attack.fireAttack());
	}
}

interface Attack {
	
	String fireAttack();
	
}

class Gun implements Attack {
	
	public String fireAttack() {
		return "Hit!...";
	}
	
}

class Trap {
	
	public String takeTheTarget() {
		return "closing the target... Target losed!";
	}
	
}

class TrapToAttackAdapter implements Attack {

	private Trap trap;
	
	public TrapToAttackAdapter(Trap trap) {
		this.trap = trap;
	}
	
	@Override
	public String fireAttack() {
		return trap.takeTheTarget();
	}
	
}