package study.designpattern.jocsa.creational;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jocsa
 * Nome: Builder
 * Motivação: Quando a construção de um objeto, por partes, se torna complicado.
 * Solução: Prover uma API que realiza a construção de forma sucinta. Permitindo a construção do objeto parte por parte através de funcionalidades (o que difere de factories).
 */
public class Builder {
	// Usuário informou o nome de 3 paises no qual ele deseja ver como se diz Olá na lingua nativa em lista não ordenada 'html'
	static String[] paises = { "ESTADOS UNIDOS", "FRANÇA", "ESPANHA"};
	static String[] hiList = { "Hi!", "Salut!", "Hola!" };

	public static void main(String[] args) {
//		exemploSimplesComStringBuilder();
		
		// Aplicando padrão Builder
//		exemploComHtmlBuilder();
		
		CodeBuilder cb = new CodeBuilder("Person").addField("name", "String").addField("age", "int");
		System.out.println(cb);
		
	}
	
	public static void exemploComHtmlBuilder() {
		
		HtmlBuilder hb = new HtmlBuilder("ul");
		
		for (int i=0; i < paises.length; i++) {
			
			hb.addChield("li", paises[i] + " Oi é: " + hiList[i], 1);
		}
			
		System.out.println(hb+"\n");
		
		HtmlBuilder hbFluent = new HtmlBuilder("ul");
		for (int i=0; i < paises.length; i++) {
			
			hbFluent
			.addChieldFluent("li", paises[i] + " Oi é: " + hiList[i], 1)
			.addChieldFluent("li", "Test fluency " + (i + 1), 1);
		}
		
		System.out.println(hbFluent);
		
	}
	
	public static void exemploSimplesComStringBuilder() {
		
		
		System.out.println("--- SEM PADRAO BUILDER ---\n");
		// Exemplo: Concatenação de string sem o padrão Builder
		// INICIO	
		String stringConcatenada = "<ul>\n";
		stringConcatenada += "  <li> " + paises[0] + " oi é: " + hiList[0] + " </li>\n";
		stringConcatenada += "  <li> " + paises[1] + " oi é: " + hiList[1] + " </li>\n";
		stringConcatenada += "  <li> " + paises[2] + " oi é: " + hiList[2] + " </li>";
		stringConcatenada += "\n</ul>";
		
		System.out.println(stringConcatenada + "\n");
		// FIM

		// Exemplo: Concatenação de string com um exemplo padrão Builder usando StringBuilder
		// INICIO
		System.out.println("--- COM PADRAO BUILDER ---\n");
		StringBuilder sb = new StringBuilder("<ul>\n");
		for (int i=0; i < paises.length; i++) {
			
			sb.append("  ");
			sb.append("<li>" + paises[i] + " Oi é: " + hiList[i] + "</li>\n");
			
		}
		sb.append("</ul>");
		System.out.println(sb.toString());
		// FIM
	}
	
}


/**
 * @author Jocsa
 */
class HtmlElement {
	
	private String tagName, tagText;
	private int spaceIdentation;
	private List<HtmlElement> elements = new ArrayList<>();
	private final int identationSize = 2;
	
	public HtmlElement() {
		super();
	}

	public HtmlElement(String tagName, String tagText, int spaceIdentation) {
		this.tagName = tagName;
		this.tagText = tagText;
		this.spaceIdentation = spaceIdentation;
	}

	public List<HtmlElement> getElements() {
		return elements;
	}

	@Override
	public String toString() {
		String init = String.join("", Collections.nCopies(identationSize * this.spaceIdentation, " ")) + "<" + this.tagName + ">";
		String and = "</" + this.tagName + ">\n";
		return  init + this.tagText + and;
	}
	
}

/**
 * @author Jocsa
 */
class HtmlBuilder {
	
	private String rootTag;
	private HtmlElement rootElement = new HtmlElement();
	
	public HtmlBuilder(String rootTag) {
		this.rootTag = rootTag;
	}
	
	public void addChield(String chieldTag, String chieldText){
		this.addChield(chieldTag, chieldText, 0);
	}
	
	public void addChield(String chieldTag, String chieldText, int chieldLevel){
		this.rootElement.getElements().add(new HtmlElement(chieldTag, chieldText, chieldLevel));
	}
	
	public HtmlBuilder addChieldFluent(String chieldTag, String chieldText){
		return this.addChieldFluent(chieldTag, chieldText, 0);
	}
	
	public HtmlBuilder addChieldFluent(String chieldTag, String chieldText, int chieldLevel){
		this.rootElement.getElements().add(new HtmlElement(chieldTag, chieldText, chieldLevel));
		return this;
	}
	
	public void clean() {
		this.rootElement.getElements().clear();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<" + this.rootTag + ">\n");
		for (HtmlElement he : rootElement.getElements()) {
			sb.append(he);
		}
		sb.append("</" + this.rootTag + ">");
		
		return sb.toString();
	}

	
}

class CodeBuilder
{
	private String className;
    private Map<String, String> fieldsMap = new HashMap<>();
    private final String newLine = System.lineSeparator();
    
    public CodeBuilder(String className)
    {
        this.className = className;
    }
    
    public CodeBuilder addField(String name, String type)
    {
        fieldsMap.put(name, type);
        return this;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("public class " + this.className + newLine);
        sb.append("{" + newLine);
        
    	for (Map.Entry<String, String> item : fieldsMap.entrySet()) {
			sb.append("  public " + item.getValue() + " " + item.getKey() +";" + newLine);
		}
    	
        sb.append("}");
        
        return sb.toString();
    }
}




















