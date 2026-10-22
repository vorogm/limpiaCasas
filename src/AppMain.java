import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXParseException;
/**
 * Clase principal donde se genera el menu
 * @author Salvador Garcia
 * @version 1.0
 * @fecha 22-10-2026
 */
public class AppMain {

	private static int iOption;
	private static ArrayList<Home> alHome = new ArrayList<Home>();	
	private static ArrayList<Employee> alEmp = new ArrayList<Employee>();
	private static ArrayList<Relation> alRel = new ArrayList<Relation>();
	static final String OBJXMLNAME_HOME = "home";
	static final String OBJXMLNAME_EMP = "employee";
	static final String OBJXMLNAME_REL = "relation";
	static final String XML_FILENAME_HOME = "src/Docs/Home.xml";
	static final String XSL_FILENAME_HOME = "src/Docs/Home.xsl";
	static final String HTML_FILENAME_HOME = "src/Docs/Home.html";
	static final String XML_FILENAME_EMP = "src/Docs/Employee.xml";
	static final String XSL_FILENAME_EMP = "src/Docs/Employee.xsl";
	static final String HTML_FILENAME_EMP = "src/Docs/Employee.html";
	static final String XML_FILENAME_REL = "src/Docs/Relation.xml";
	static final String XSL_FILENAME_REL = "src/Docs/Relation.xsl";
	static final String HTML_FILENAME_REL = "src/Docs/Relation.html";
	
	/**XML_FILENAME
	 * Variables
	 * alHome: Se usará para almacenar los objetos que se lean del fichero XML de home
	 * OBJXMLNAME: es el la parte que se repite del XML con todos sus datos.
	 * XML_FILENAME: es el nombre que tendrá el fichero.
	 * iOption: es donde se almacenará las entradas de teclado.
	 **/
	
	public static void main(String[] args) {

		System.out.println("Empieza el Programa.");

		// creo un do while para que el menu se repita mientras no se pulse 0.
		do 
		{			
			iOption = IOTools.showMenu(
					"\tLimpiaCasas Bu Yunchaokete", "Exit", "Add", "List","Search","Remove","Erase File","Export to File CSV", "See in HTML", "About the APP");
			caseOptions(iOption); 
			
		}while(true);
	}
	
	public static void caseOptions(int iOption) 
	{				
		/* Si es cero, el usuario quiere salir, por lo que no es necesario
		* leer el archivo XML.
		* Si no es cero, se lee el archivo XML  y luego paso al Switch
		*/
		
		if (iOption == 0)
		{
			System.out.println("=====================================");
			System.out.println(" Programe has finished. See you soon.");
			System.out.println("=====================================");
			System.exit(0);
		}
		else
			readFromXml();
		
		switch (iOption)
		{		
		
		case 1:
			// llamo a la clase add con lo tres Arrays

			Add.selectObject(alHome, alEmp, alRel);
			break;	
		case 2: 

			listObjects(); 
			break;
		case 5: 
			System.out.println("\t You are going to search a Home.");
			System.out.println("--------------------------------------------");
			
		if (fileExists(XML_FILENAME_HOME,"No Homes Saved")) SearchOrRemove.searchHome(alHome);
//			break;
//		case 6: 
//			if (fileExists(XML_FILENAME,"No Homes Saved")) SearchRemove.removeHome(alHome);
//			break;
//		case 7: 
//			if (fileExists(XML_FILENAME,XML_FILENAME + " NOT FOUND. ")) SearchRemove.eliminarXML(true);
//			break;
//		case 6 :
//			if (fileExists(XML_FILENAME,XML_FILENAME + " No Homes Saved. ")) ExportCSV.createCSV(alHome);			
//			break;
//		case 7: 
//			if (fileExists(XML_FILENAME,XML_FILENAME + " NOT FOUND. ")) XSL.toHTML();
//			break;
			
		case 8:
			//about(); 
			break;
		
		default : 
			System.out.println("Incorrect Type");
			break;		
		
		}
		
	}
	
	/*
	 * Método que verifica si existe un fichero.
	 * Si no existe dará un mensaje de error que se le proporciona al llamarlo.
	 * Se da la opcion de no pasar ningun mensage por si se prefiere asi.
	 * Así se puede utilizar este método en diferentes llamadas.
	 * 
	 */
	public static boolean fileExists(String stFile, String message) 
	{
		File file = new File(stFile);
		
		if (!file.exists()) 
			
			if (! message.isEmpty())
				
				System.out.println("\n\t---" + message + " ---\n");
			

		return file.exists();
	}
	
	/**
	 * Método que crea el manejador, lo llama y recibe un ArrayList con los objetos 
	 * de Home, Employee y Realtion
	 * 
	 **/
	public static void readFromXml() 
	{
		
		SAXParserFactory saxFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxFactory.newSAXParser();
			
			// Si los fichero existen, crea un manejador, los llama y almancena el ArrayList
			if (fileExists(XML_FILENAME_HOME,"")) {
				
				homeHandler handlerHome = new homeHandler();
				saxParser.parse(XML_FILENAME_HOME, handlerHome);
				alHome = handlerHome.getArrayListHome();
			}
					
			if (fileExists(XML_FILENAME_EMP,"")) {
				
				EmployeeHandler handlerEmp = new EmployeeHandler();
				saxParser.parse(XML_FILENAME_EMP, handlerEmp);
				alEmp = handlerEmp.getArrayListEmp();
				
			}
			if (fileExists(XML_FILENAME_REL,"")) {
				
				RelationHandler handlerRel = new RelationHandler();
				saxParser.parse(XML_FILENAME_REL, handlerRel);
				alRel = handlerRel.getArrayListRel();
				
			}
		} catch (SAXParseException spe) {
			System.out.println("Error: "+ spe.getMessage());
		} catch (Exception e) {
			System.out.println("Error: "+ e.getMessage());
			}
	}
	
	/**
	 * Método para ver el contenido de homes, employees y relations
	 * Se podrá elegir para ver los todos de golpe o pausadamente 
	 * (Ideal si hay muchos registros)
	 */
	public static void listObjects()
	{
		
						
		int iObject = IOTools.showMenu("What do you want to List ?" , "Cancel", "Homes", "Employees", "Relations");
		
		 // si la respuesta es cancel, no hace falta que entre en los IFs.
		if (iObject != 0) 
		{
			int iAnswer = IOTools.askInt("Do you want to see with pauses? 1. yes / 2. no", 1, 2);
		
			if (iObject == 1) 
			{
				// Si existe el fichero continua, si no, es tonteria.
				
				if (fileExists(XML_FILENAME_HOME,"No Homes Saved")) 
					
				for (Home objHome : alHome) 
				{
					
					objHome.mostrar();
					if (iAnswer == 1) IOTools.pressEnterToContinue();
				}
			}else if (iObject == 2) 
			{
				if (fileExists(XML_FILENAME_EMP,"No Employees Saved")) 
					
				for (Employee objEmp : alEmp) 
				{
					
					objEmp.mostrar();
					if (iAnswer == 1) IOTools.pressEnterToContinue();
				}
			}else if (iObject == 3) 
		
		{
				if (fileExists(XML_FILENAME_REL,"No Relations Saved")) 

				for (Relation objRel : alRel) 
				{
					
					objRel.mostrar();
					if (iAnswer == 1) IOTools.pressEnterToContinue();
				}
			}
			IOTools.pressEnterToContinue();
		}
		
			
	}
	public static ArrayList<Employee> getAlEmployee()
	{
		return alEmp;
	}

}
