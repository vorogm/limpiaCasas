import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Clase que añade Homes al XML.
 * Primero pregunta por un campo como filtro.
 * Segundo crea un XML virtual
 * Tercero: transforma el XML y crea el fichero
 * 
 **/
public class Add {
	
	
	/**
	 * Como se pueden añadir tres objetos, pregunto cual quiere.
	 * @param alHome
	 * @param alEmp
	 * @param alRel
	 */
	public static void selectObject(ArrayList<Home> alHome, ArrayList<Employee> alEmp, ArrayList<Relation> alRel) {
		
		int iObject = IOTools.showMenu("What do you want to add? ", "Cancel", "A Home", "An Employee", "A Relation");
		
		switch (iObject)
		{		
		case 0: 
			break;
		case 1: 
			
			System.out.println("\t You are going to add a Home.");
			System.out.println("--------------------------------------------");
			addHomes(alHome);
			break;
		case 2: 
			System.out.println("\t You are going to add an Employee.");
			System.out.println("--------------------------------------------");
			addEmployee(alEmp);
			break;
		case 3: 
			System.out.println("\t You are going to add a Realtion.");
			System.out.println("--------------------------------------------");
			addRelation(alRel, alEmp, alHome);
			break;
		}
		
		
	}

	/**
	 * Método que pide datos, crea los objetos Home y los almacena 
	 * en el ArrayList<Home> alHome.
	 * ArrayList<Home> alHome: variable para almacenar los objetos.
	 * El cod de la vivienda no debe de repertise
	 **/
	public static void addHomes(ArrayList<Home> alHome)		// ########################
	{
		int iCod; 
		// Como el Codigo no se puede repetir, compruebo si existe.
		// Si existe, se vuelve a pedir.
		do {
			 iCod = IOTools.askInt("Código: ");
			 
			if (bExist(iCod,alHome)) 	// uso el método bExists() que se encuentra más abajo	
			{
				System.out.println("=======================================");
				System.out.println(" WARNING: This cod is already used !!");
				System.out.println("=======================================");
			}
			else break;		 
			 
		}while(true);

		String stType = IOTools.askString("Type of Home, (House, Appartament, Chalet, Bungalow)", "House", "Appartament", "Chalet", "Bungalow");
		String stCity = IOTools.askString("City");
		int iSurface = IOTools.askInt("Surface m2");
		int iRooms = IOTools.askInt("Total Rooms");		
		int iPrice = IOTools.askInt("Price");		
		
		
		// Pediré el total de fotos a introducir para dejar x etiquetas. Por cada foto, una etiqueta.
		
		int iPictures = IOTools.askInt("How many picturas do you want to add?");
		
		// Creo el objeto Home y añado todos los campos

		Home home = new Home(iCod, stType, stCity, iSurface, iRooms, iPrice, iPictures);

		// añado el objeto Home al ArrayList
		
		alHome.add(home);		
		
		// llamo al método que creará el XML
		createXmlHome(alHome);
	}
	
	/**
	 * Método que crea un xml con los datos del empleado
	 * El DNI introducido no debe de estar ya registrado
	 */
	public static void addEmployee(ArrayList<Employee> alEmp)		// ########################
	{
		String stDni; 
		// Como el Codigo no se puede repetir, compruebo si existe.
		// Si existe, se vuelve a pedir.
		do {
			stDni = IOTools.askString("DNI: ");
			 
			if (bExist(stDni,alEmp)) 	// uso el método bExists() que se encuentra más abajo	
			{
				System.out.println("=======================================");
				System.out.println(" WARNING: This DNI is already used !!");
				System.out.println("=======================================");
			}
			else break;		 
			 
		}while(true);
	
		
		String stFullName = IOTools.askString("Full Name");
		int iTlf = IOTools.askInt("TLF");
		int iActive = IOTools.askInt("Active (1. Yes / 2. No", 1,2);  // paso los parametros 1 y 2, para que solo pueda responderme eso.		
		boolean bActive = (iActive == 1) ? true : false;	
		

		// Creo el objeto Employee y añado todos los campos

		Employee emp = new Employee(stDni,stFullName,iTlf,bActive);
		
		// añado el objeto Home al ArrayList
		
		alEmp.add(emp);		
		
		// llamo al método que creará el XML
		createXmlEmp(alEmp);
	}
	/**
	 * Método que crea un xml con la relación entre empleado y vivienda
	 * Tanto el DNI del trabajador como el cod de la vivienda, deben existir.
	 */
	public static void addRelation(ArrayList<Relation> alRel,ArrayList<Employee> alEmp, ArrayList<Home> alHome)		// ########################
	{
		String stDni;
		int iCod;
		boolean bCoincide;
		String stDay;
		// CCompruebo que el DNI exista en la tabla employees
		do {
			stDni = IOTools.askString("DNI: ");
			 
			if (!bExist(stDni,alEmp)) 	// uso el método bExists() que se encuentra más abajo	
			{
				System.out.println("=======================================");
				System.out.println(" WARNING: This DNI doesn't exist !!");
				System.out.println("=======================================");
			}
			else break;		 
			 
		}while(true);

		/**
		 * He decidido que una misma casa puede ser limpiada por uno o mas empleados 
		 * y en varios dias. Pero no puede coincidir, una misma casa, con el mismo dia.
		 * Tiene que ser otro día, pero puede ser el mismo employee y otro.
		 */
		
					// este do comprueba que no coincida vivienda y dia.
					// si es así repetira proceso
		do {
			
					// este do while comprueba que el codigo de la vivienda exista
			do {
				iCod = IOTools.askInt("Cod: ");
				 
				if (!bExist(iCod,alHome)) 	// uso el método bExists() que se encuentra más abajo	
				{
					System.out.println("=======================================");
					System.out.println(" WARNING: This Cod doesn't exist !!");
					System.out.println("=======================================");
				}
				else break;		 
				 
			}while(true);
			
			
			stDay = IOTools.askString("Day", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado");
			
			
			bCoincide = false;
			for (Relation relCheck : alRel) 
			{
				  // si coincide, se repite todo el tiempo hasta que no coincida.
				if ((relCheck.getiCod() == iCod) && (relCheck.getStDay().equalsIgnoreCase(stDay))){
					
					System.out.println("=======================================");
					System.out.println("  This Home is alrady cleaned that day");
					System.out.println("      Choose another day or Home");
					System.out.println("=======================================");
					bCoincide = true;
//					break;
				}
				
			}
		}while(bCoincide);
		
		 int iHours = IOTools.askInt("Horas");
		// Creo el objeto Relation y añado todos los campos

		Relation rel = new Relation(stDni,iCod, stDay, iHours);
		
		// añado el objeto Home al ArrayList
		
		alRel.add(rel);		
		
		// llamo al método que creará el XML
		createXmlRel(alRel);
		
		
	}
	
	/**
	 * Método que crerá el XML con todas sus etiquetas.
	 * Tanto los nombres de etiquetas, atributos y 
	 * el valor de los campos.
	 **/
	
	public static void createXmlHome(ArrayList<Home> alHome ) 
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		try(FileOutputStream output = new FileOutputStream(AppMain.XML_FILENAME_HOME)) {
			
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document document = dBuilder.newDocument();
		
			/*
			 * Creo la primera etiqueta que es la principal (root)
			 * OBJXMLNAME es el nombre de la etiqueta que se repite (hijo de root)
			 *  y añado "s" para que sea el nombre del root.
			 */		
			
			Element rootElement = document.createElement(AppMain.OBJXMLNAME_HOME + "s"); 
			document.appendChild(rootElement);
			
			//Ahora creo la etiqueta que es la que se repite (nood)

			Element eNode, eSubNode;
	
			/*
			 * Ahora, por cada etiqueta home que encuentre, creamos los hijos
			 *	y le asignamos los datos, leyéndolos del ArrayList mediante
			 *	un for each.
			 */			

			for (Home objHome : alHome ) {		
			

				eNode = document.createElement(AppMain.OBJXMLNAME_HOME);
				rootElement.appendChild(eNode);
	
				// Ahora creo la etiqueta que contendra el dato
				
				eSubNode = document.createElement("cod"); 
				eNode.appendChild(eSubNode);				
			
				// Ahora asigno el valor a las etiquetas.
				// Los valores que no son  Strings, los tengo que pasar a String
				
				eSubNode.appendChild(document.createTextNode(String.valueOf(objHome.getiCod())));
				
				// Y ahora así todo el rato	
				// Creo etiqueta, la guardo en el nodo, creo subnodo y guardo el valor del arrayList
				
				eSubNode = document.createElement("type"); 
				eNode.appendChild(eSubNode);		
				eSubNode.appendChild(document.createTextNode(objHome.getStType()));				
				
				eSubNode = document.createElement("city"); 
				eNode.appendChild(eSubNode);		
				eSubNode.appendChild(document.createTextNode(objHome.getStCity()));
				
				eSubNode = document.createElement("surface"); 
				eNode.appendChild(eSubNode);
				eSubNode.setAttribute("unit",objHome.getStUnit());
				eSubNode.appendChild(document.createTextNode(String.valueOf(objHome.getiSurface())));
										
				eSubNode = document.createElement("rooms"); 
				eNode.appendChild(eSubNode);
				eSubNode.appendChild(document.createTextNode(String.valueOf(objHome.getiRooms())));
				
							
				// como este tiene un atributo, lo añado
				
				eSubNode = document.createElement("price"); 
				eNode.appendChild(eSubNode);
				eSubNode.setAttribute("coin",objHome.getStCoin());
				eSubNode.appendChild(document.createTextNode(String.valueOf(objHome.getiPrice())));
				
							
				/* Como fotos pueden haber 0 o muchas, si hay mas de cero, hago un for.
				*  si no hay, dejo nota de que no hay. En un caso real, supongo que aparecia un enlace 
				*  para poder ver las fotos o asociarlas a una web
				*/

				if (objHome.getiPictures() > 0)
				{
					for (int i=1; i <= objHome.getiPictures(); i++)  // pongo i=1 para que el nombre de la primera foto empiece por 1
					{
						eSubNode = document.createElement("pictures"); 
						eNode.appendChild(eSubNode);
						eSubNode.appendChild(document.createTextNode("picture_" + i + ".jpg"));
					}
				}else 
				{
					eSubNode = document.createElement("picture"); 
					eNode.appendChild(eSubNode);
					eSubNode.appendChild(document.createTextNode("No pictures availables"));
				}				
			}
			// Una vez creado en memoria el XML, llamó al método que lo transforma y escribe fisicamente.

			transformXML(document, output);
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void createXmlEmp(ArrayList<Employee> alEmp) 
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		try(FileOutputStream output = new FileOutputStream(AppMain.XML_FILENAME_EMP)) {
			
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document document = dBuilder.newDocument();
		
			/*
			 * Creo la primera etiqueta que es la principal (root)
			 * OBJXMLNAME es el nombre de la etiqueta que se repite (hijo de root)
			 *  y añado "s" para que sea el nombre del root.
			 */		
			
			Element rootElement = document.createElement(AppMain.OBJXMLNAME_EMP + "s"); 
			document.appendChild(rootElement);
			
			//Ahora creo la etiqueta que es la que se repite (nood)

			Element eNode, eSubNode;
	
			/*
			 * Ahora, por cada etiqueta home que encuentre, creamos los hijos
			 *	y le asignamos los datos, leyéndolos del ArrayList mediante
			 *	un for each.
			 */			

			for (Employee objEmp : alEmp ) {		
			

				eNode = document.createElement(AppMain.OBJXMLNAME_EMP);
				rootElement.appendChild(eNode);
	
				// Ahora creo la etiqueta que contendra el dato
				
				eSubNode = document.createElement("dni"); 
				eNode.appendChild(eSubNode);				
			
				// Ahora asigno el valor a las etiquetas.
				// Los valores que no son  Strings, los tengo que pasar a String
				
				eSubNode.appendChild(document.createTextNode(objEmp.getStDni()));
				
				// Y ahora así todo el rato	
				// Creo etiqueta, la guardo en el nodo, creo subnodo y guardo el valor del arrayList
				
				eSubNode = document.createElement("fullname"); 
				eNode.appendChild(eSubNode);		
				eSubNode.appendChild(document.createTextNode(objEmp.getStFullName()));				
				
				eSubNode = document.createElement("tlf"); 
				eNode.appendChild(eSubNode);		
				eSubNode.appendChild(document.createTextNode(String.valueOf(objEmp.getiTlf())));
				
				eSubNode = document.createElement("active"); 
				eNode.appendChild(eSubNode);
			//	eSubNode.setAttribute("unit",objEmp.getStUnit());
				eSubNode.appendChild(document.createTextNode((objEmp.getbActive()? "yes" : "no")));
										
				
			}
			// Una vez creado en memoria el XML, lEmployeelamó al método que lo transforma y escribe fisicamente.

			transformXML(document, output);
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 
	 * @param alRel es ele arrayList en el que guardará los datos de la relación
	 */
	public static void createXmlRel(ArrayList<Relation> alRel) 
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		try(FileOutputStream output = new FileOutputStream(AppMain.XML_FILENAME_REL)) {
			
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document document = dBuilder.newDocument();
		
			/*
			 * Creo la primera etiqueta que es la principal (root)
			 * OBJXMLNAME es el nombre de la etiqueta que se repite (hijo de root)
			 *  y añado "s" para que sea el nombre del root.
			 */		
			
			Element rootElement = document.createElement(AppMain.OBJXMLNAME_REL + "s"); 
			document.appendChild(rootElement);
			
			//Ahora creo la etiqueta que es la que se repite (nood)

			Element eNode, eSubNode;
	
			/*
			 * Ahora, por cada etiqueta home que encuentre, creamos los hijos
			 *	y le asignamos los datos, leyéndolos del ArrayList mediante
			 *	un for each.
			 */			

			for (Relation objRel : alRel ) {		
			

				eNode = document.createElement(AppMain.OBJXMLNAME_REL);
				rootElement.appendChild(eNode);

				
				eSubNode = document.createElement("dni"); 
				eNode.appendChild(eSubNode);
				eSubNode.appendChild(document.createTextNode(objRel.getStDni()));
				
				eSubNode = document.createElement("cod"); 
				eNode.appendChild(eSubNode);
				eSubNode.appendChild(document.createTextNode(String.valueOf(objRel.getiCod())));
				
		
				eSubNode = document.createElement("day"); 
				eNode.appendChild(eSubNode);		
				eSubNode.appendChild(document.createTextNode(String.valueOf(objRel.getStDay())));
				
				eSubNode = document.createElement("hours"); 
				eNode.appendChild(eSubNode);
				eSubNode.appendChild(document.createTextNode(String.valueOf(objRel.getiHours())));
										
				
			}
			// Una vez creado en memoria el XML, llamó al método que lo transforma y escribe fisicamente.

			transformXML(document, output);
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public static void transformXML(Document doc, OutputStream out) throws TransformerException
	{
		try {
			
			TransformerFactory trFactory = TransformerFactory.newInstance();
			Transformer transformer = trFactory.newTransformer();
			
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(out);
			
			transformer.transform(source, result);
			
			System.out.println("\n=======================================");
			System.out.println("     Added !!");
			System.out.println("=======================================");
			IOTools.pressEnterToContinue();
			

		} catch (TransformerConfigurationException tce) {
			tce.printStackTrace();
		} catch (TransformerFactoryConfigurationError tfce) {
			tfce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	/**
	 * Método para comprobar si existe un código
	 * Se le pasa el código introducido por teclado
	 *  y el array donde estan todos los códigos de las viviendas
	 **/
	public static Boolean bExist(int iCod, ArrayList<Home> al) 
	{

		for (Home home : al) {			
		
			if (home.getiCod() == iCod)  return true;
			
		}
		return false;
	}
	public static Boolean bExist(String stDni, ArrayList<Employee> al) 
	{

		for (Employee emp : al) {			
		
			if (emp.getStDni().equalsIgnoreCase(stDni) )  return true;
			
		}
		return false;
	}
}
