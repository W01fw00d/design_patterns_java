package builder;

import java.util.LinkedHashMap;
import java.util.Map;

// Applying the Builder Design Pattern
public class CodeBuilder
{
	private final String PUBLIC_RESERVED_WORD = "public";
	private final String CLASS_RESERVED_WORD = "class";
	private final String INDENTATION_WHITESPACES = "  ";

    private String className;
	private LinkedHashMap<String, String> fields;

	public CodeBuilder(String className)
    {
        this.className = className;
        this.fields = new LinkedHashMap<String, String>();
    }

	public CodeBuilder addField(String name, String type)
    {
		this.fields.put(name, type);
		return this;
    }

    @Override
    public String toString()
    {
    	return getFormattedClassDeclaration()
    		+ getFormattedFields()
    		+ "}";
    }

    private String getFormattedClassDeclaration()
    {
    	return PUBLIC_RESERVED_WORD + " "
        	+ CLASS_RESERVED_WORD + " "
        	+ this.className + System.lineSeparator()
        	+ "{" + System.lineSeparator();
    }

    private String getFormattedFields()
    {
    	String formattedCode = "";

    	for(Map.Entry<String,String> field : this.fields.entrySet()){
    		formattedCode +=
    			INDENTATION_WHITESPACES + PUBLIC_RESERVED_WORD + " "
    			+ field.getValue() + " "
    			+ field.getKey() + ";"
    			+ System.lineSeparator();
		}

    	return formattedCode;
    }
}

class CodeBuilderDemo
{
  public static void main(String[] args)
  {
	System.out.println(
		new CodeBuilder("Person")
			.addField("name", "String")
			.addField("age", "int")
	);
  }
}
