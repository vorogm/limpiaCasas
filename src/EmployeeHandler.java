import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class EmployeeHandler extends DefaultHandler
{

	private StringBuilder currentValue = new StringBuilder();
	ArrayList<Employee> alEmp = new ArrayList<Employee>();
	Employee employee;
		
	@Override
	public void characters(char[] ch, int start, int length) 
	{
		currentValue.append(ch, start, length);
	}
	
	@Override
	public void startElement (String uri, String localName, String qName, Attributes attributes) 
	{
		currentValue.setLength(0);
		if (qName.equalsIgnoreCase("employee")) employee = new Employee();
		
	}	@Override
	public void endElement (String uri, String localName, String qName) 
	{
	//	Employee employee = new Employee();
		
		switch (qName)
		{
		case "dni" : employee.setStDni(currentValue.toString());
			break;
		case "fullname" : employee.setStFullName(currentValue.toString());
			break;
		case "tlf" : employee.setiTlf(Integer.parseInt(currentValue.toString()));
		break;
		case "active" : employee.setbActive(currentValue.toString().equalsIgnoreCase("yes")? true : false);
		break;	
		case "employee" : alEmp.add(employee);
			break;
			
		}
	}
	
	public ArrayList<Employee> getArrayListEmp()
	{
		return  alEmp;
	}
	

	
	
}

/*
 * 		this.stDni = stDNI;
		this.stFullName = stFullName;
		this.iTlf = iTlf;
		this.bActive = bActive;
	}
 */
