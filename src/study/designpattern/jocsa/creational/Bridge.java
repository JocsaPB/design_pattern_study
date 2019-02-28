package study.designpattern.jocsa.creational;

public class Bridge {

	public static void main(String[] args) {
		
		ShowInformation showInfo = new ShowInformationOnSmartPhone(new BookResource());
		
		showInfo.show();
		
		showInfo = new ShowInformationOnTV(new BookResource());
		
		showInfo.show();
		
	}
	
}

// 1º Abstraction
abstract class ShowInformation {
	
	protected IResourceInformation resourceInformation;
	
	public ShowInformation(IResourceInformation resourceInformation) {
		this.resourceInformation = resourceInformation;
	}
	
	abstract void show();
	
}

//2º Abstraction
interface IResourceInformation {
	
	String title();
	
	String description();
	
}

// 1º Abstraction + 2º Abstraction == Bridge

// 1º Concrete Class of 1º Abstraction
class ShowInformationOnSmartPhone extends ShowInformation {
	
	public ShowInformationOnSmartPhone(IResourceInformation resourceInformation) {
		super(resourceInformation);
	}

	@Override
	public void show() {
		System.out.println(" --- Showing informations on Smart Phone device --- \n");
		System.out.println("The title is: " + this.resourceInformation.title());
		System.out.println("The description is: " + this.resourceInformation.description());
		System.out.println();
	}
	
}

// 2º Concrete Class of 1º Abstraction
class ShowInformationOnTV extends ShowInformation {
	
	public ShowInformationOnTV(IResourceInformation resourceInformation) {
		super(resourceInformation);
	}

	@Override
	public void show() {
		System.out.println(" --- Showing informations on TV device --- \n");
		System.out.println("The title is: " + this.resourceInformation.title());
		System.out.println("The description is: " + this.resourceInformation.description());
		System.out.println();
	}
	
}

// 1º Implementation of 2º Abstraction
class BookResource implements IResourceInformation {

	@Override
	public String title() {
		return "Book title X";
	}

	@Override
	public String description() {
		return "Book description X";
	}
	
}




