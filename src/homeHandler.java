import java.util.ArrayList;


import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Clase que lee del archivo XML y almacenar los datos en los objetos HOME. 
 * Al mismo tiempo los objetos se guardarán en un ArrayList<Home>.
 **/

public class homeHandler extends DefaultHandler
{
	ArrayList<Home> alHome = new ArrayList<Home>();	
	Home home;
	int iCont = 0;
	private StringBuilder currentValue = new StringBuilder();
	
	
	@Override
	public void startDocument() 
	{}
	
	@Override
	public void endDocument() 
	{}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) 
	{
		currentValue.setLength(0);
		if (qName.equalsIgnoreCase("home")) 
			home = new Home();
		
	}
	@Override
	public void endElement(String uri, String localName, String qName) 
	{
		
		switch (qName.toLowerCase())  // pasamos a minúsculas para evitar errores
		{
		
		// con los datos que obtenemos de leer el XML asignamos los valores al objeto Home
		// como el parse devuelve Strings, hay campos que los tenemos que pasar a int
		case "cod": 
			//saveInArray(currentValue.toString());
			home.setiCod(Integer.parseInt(currentValue.toString()));
			break;
		case "type": 
			home.setStType(currentValue.toString());
			break;
		case "city": 
			home.setStCity(currentValue.toString());
			break;
		case "surface": 
			home.setiSurface(Integer.parseInt(currentValue.toString()));
			break;
		case "rooms": 
			home.setiRooms(Integer.parseInt(currentValue.toString()));
			break;
		case "price": 
			home.setiPrice(Integer.parseInt(currentValue.toString()));
			break;


			// En este caso, lo que se guarda es la cantida de pictrures. por lo que
			// tiene que contar las veces que aparece una etiqueta y luego pasarle el total.
		case "pictures" : 
					iCont++;
				 break;
			// Cuando aparezca la etiqueta "home" quiere decir que ya ha contado todas las 
			// etiquetas pictures por lo que le guardamos el total.
			// También quiere decir que ha terminado con una vivenda, por lo que guardamos
			// el objeto en el ArrayList
		case "home" :
			home.setiPictures(iCont);
			iCont=0;	
			alHome.add(home);
			break;
			// si encuentra la etiqueta homes, es que ya ha terminado todas las viviendas, por lo
			// que devolvemos el ArrayList
		
		}
	}
	@Override
	public void characters(char[] ch, int start, int length)
	{
		currentValue.append(ch, start, length);
	}
	
	/*
	 * metodo que devuelve el array con todos los objetos de Home
	 */
	public ArrayList<Home> getArrayListHome() 
	{
				return alHome;		
		
	}
}
