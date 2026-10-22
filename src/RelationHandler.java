import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class RelationHandler extends DefaultHandler{

	StringBuilder currentValue = new StringBuilder();
	ArrayList<Relation> alRel = new ArrayList<Relation>();
	Relation relation;
	
	@Override
	public void characters(char[] ch, int start, int length) 
	{
		currentValue.append(ch, start, length);
		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) 
	{
		currentValue.setLength(0);
		if (qName.equalsIgnoreCase("relation")) relation = new Relation();
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) 
	{
		
		switch (qName) 
		{
			case "dni": 
				relation.setStDni(currentValue.toString());
				break;
			case "cod": 
				relation.setiCod(Integer.parseInt(currentValue.toString()));
				break;
			case "day": 
				relation.setStDay(currentValue.toString());
				break;
			case "hours": 
				relation.setiHours(Integer.parseInt(currentValue.toString()));
				break;
			case "relation" :
				alRel.add(relation);
				break;
		
		}
	}
	
	public ArrayList<Relation> getArrayListRel()
	{		
		return alRel;
	}
	
	
}
