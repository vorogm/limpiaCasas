import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase que leera el ArrayList y creara un fichero csv.
 * Y al reves. Leera un csv y añadira sus registros al XML.
 * Verificando que exista el empleado. la vivienda y que 
 *  * no exista ya la relación vivienda-día.  
 **/
public class ExportImportCSV {

	public static void export() 
	{
		int iTable = IOTools.showMenu("What table do you want to export to CSV? ","Cancel", "Home", "Employee", "Relation");
		
		if (iTable == 1) createCSVHome();
		else if (iTable == 2) createCSVEmp();
		else if (iTable == 3) createCSVRel();
		
	}
	
	/**
	 * Método que crea el home.csv.
	 */
	public static void createCSVHome() 
	{
		 /*
		 * Cogemos el nombre del archivo de la clase principal, 
		  y cambiamos la extension de xml por csv.
		  */
		File fiHome = new File(AppMain.XML_FILENAME_HOME);
		if (fiHome.exists()) 
		{
				
			
			String stFileName = AppMain.XML_FILENAME_HOME.replace("xml", "csv");
			File file = new File (stFileName);
			
			try (BufferedWriter bw = new BufferedWriter( new FileWriter(file))){
				
				bw.write("Cod,Type,City,Surface,Rooms,Price,Pictures");  // pongo cabecera.
				bw.newLine();
				for (Home home : AppMain.alHome) 
				{
					bw.write(String.valueOf(home.getiCod()) + ","
							+ home.getStType() + ","
							+ home.getStCity() + ","
							+ String.valueOf(home.getiSurface()) + ","
							+ String.valueOf(home.getiRooms()) + ","
							+ String.valueOf(home.getiPrice()) + ","
							+ String.valueOf(home.getiPictures())						
							);					
					bw.newLine();
				}
				System.out.println("\n=======================================");
				System.out.println("     Table HOME exported to CSV!!");
				System.out.println("=======================================");
				IOTools.pressEnterToContinue();
				
			} catch (IOException ioe) {
				// TODO: handle exception
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else 
		{
			System.out.println("\n=======================================");
			System.out.println("     CSV not created because");
			System.out.println(" File home.xml doesn't exist !!");
			System.out.println("=======================================");
			IOTools.pressEnterToContinue();
		}
	}
	/**
	 * Método que crea el employee.csv.
	 */
	public static void createCSVEmp() 
	{
		File fiHome = new File(AppMain.XML_FILENAME_EMP);
		if (fiHome.exists())
		{
			  // coge el nombre del xml y cambia la extension por csv.
				// así, si cambía el nombre, no hay que cambiarlo aquí.
			String stFileName = AppMain.XML_FILENAME_EMP.replace("xml", "csv");
			File file = new File (stFileName);
			
			try (BufferedWriter bw = new BufferedWriter( new FileWriter(file))){
				
				bw.write("DNI,Full Name,Tlf,Active"); // pongo cabecera
				bw.newLine();
				for (Employee emp : AppMain.alEmp) 
				{
					bw.write(emp.getStDni() + ","
							+ emp.getStFullName() + ","
							+ String.valueOf(emp.getiTlf()) + ","
							+ (emp.getbActive()? "yes" : "no") + ","						
							);					
					bw.newLine();
				}
				System.out.println("\n=======================================");
				System.out.println("     Table EMPLOYEE exported to CSV!!");
				System.out.println("=======================================");
				IOTools.pressEnterToContinue();
				
			} catch (IOException ioe) {
				// TODO: handle exception
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else 
		  {
			System.out.println("\n=======================================");
			System.out.println("     CSV not created because");
			System.out.println(" File employee.xml doesn't exist !!");
			System.out.println("=======================================");
			IOTools.pressEnterToContinue();
		  }
	}
	/**
	 * Método que crea el relation.csv.
	 */
	public static void createCSVRel() 
	{
		File fiHome = new File(AppMain.XML_FILENAME_EMP);
		if (fiHome.exists())
		{			
			String stFileName = AppMain.XML_FILENAME_REL.replace("xml", "csv");
			File file = new File (stFileName);
			
			try (BufferedWriter bw = new BufferedWriter( new FileWriter(file))){
				
				bw.write("DNI,Cod,Day,Hours");// pongo cabecera
				bw.newLine();
				for (Relation rel : AppMain.alRel) 
				{
					bw.write(rel.getStDni() + ","
							+ String.valueOf(rel.getiCod()) + ","
							+ rel.getStDay() + ","
							+ String.valueOf(rel.getiHours()) + ","
						
							);					
					bw.newLine();
				}
				System.out.println("\n=======================================");
				System.out.println("     Table RELATION exported to CSV!!");
				System.out.println("=======================================");
				IOTools.pressEnterToContinue();
				
			} catch (IOException ioe) {
				// TODO: handle exception
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else 
		  {
			System.out.println("\n=======================================");
			System.out.println("     CSV not created because");
			System.out.println(" File relation.xml doesn't exist !!");
			System.out.println("=======================================");
			IOTools.pressEnterToContinue();
		  }
	}
	
	/* ###########################################################################################################
	 * ################################        IMPORT   ##########################################################
	 * ################################                 ##########################################################
	 * ###########################################################################################################
	*/
	
	/*
	 * Método que pregunta que tabla quiero importar
	 */
	public static void askImport() 
	{
		AppMain.readFromXml();	//leo los XML para actualizar los Arrays. 
		int iFile = IOTools.showMenu("What table do you want to import from CSV?","Cancel", "Home", "Employee","Relation");
		
		if (iFile == 1) importHome();
		if (iFile == 2) importEmp();
		if (iFile == 3) importRel();
	}
	
	/*
	 * Método para importar viviendas.
	 * Compruebo que el código de la vivienda no exista
	 */
	public static void importHome() 
	{
			
			String stLine;
			String[] stVector;
			String stFileName = AppMain.XML_FILENAME_HOME.replace("xml", "csv");
			File fiFile = new  File(stFileName);
			ArrayList<String> homesNotImported = new ArrayList<String>();
			ArrayList<String> homesImported = new ArrayList<String>();
			
			try (BufferedReader br = new BufferedReader(new FileReader(fiFile)))
			{				
				br.readLine(); // salto la primera linea que es la cabecera.
				while((stLine = br.readLine()) != null)
				{
					if (stLine.trim().isEmpty()) 
					        continue; // salta líneas vacías o con espacios
					    
					stVector = stLine.split(",");
	
					if (Add.bExist(Integer.parseInt(stVector[0]))) // comprueba si ya exist el cod
					{					
	
						homesNotImported.add(stVector[0]);  // y lo guardo
	
					}else {
						// si no existe crea un objeto nuevo y lo pasa al ArraYlist. 
						// como hay valores enteros, y el CSV es todo strings, hay que convertirlos.
						
						Home home = new Home(
								Integer.parseInt(stVector[0]),
								stVector[1],
								stVector[2],
								Integer.parseInt(stVector[3]),
								Integer.parseInt(stVector[4]),
								Integer.parseInt(stVector[5]),
								Integer.parseInt(stVector[6])
								);
						AppMain.alHome.add(home);
						homesImported.add(stVector[0]); //Si lo importó lo guardo
						
						Add.createXmlHome(false);	
					}
					
				}
			} catch (Exception e) {
					System.out.println("ERROR: " + e.getMessage());
				
			}
				
			// Ahora muestro los importados y los no.

			if (homesImported.size() > 0) 
			{
				System.out.println("\n==========================================");
				System.out.println("        Homes Imported."); 
				System.out.println("============================================");
			
				for (String stImported : homesImported) 
							
					System.out.println("   COD: " + stImported);
				IOTools.pressEnterToContinue();
			}
		
			
			
			if (homesNotImported.size() > 0) 
			{
				System.out.println("\n==========================================");
				System.out.println("     WARNING:  Homes NOT Imported."); 
				System.out.println("============================================");		
				
				for (String stNotImported : homesNotImported) 
					System.out.println("   COD: " + stNotImported);
				IOTools.pressEnterToContinue();
				
			}
	}
	/*
	 * Método para importar empleados.
	 * Compruebo que el dni del empleado no exista
	 */
	public static void importEmp() 
	{
		String stLine;
		String[] stVector;
		String stFileName = AppMain.XML_FILENAME_EMP.replace("xml", "csv");
		ArrayList<String> employeeImported = new ArrayList<String>();
		ArrayList<String> employeeNotImported = new ArrayList<String>();
		File fiFile = new  File(stFileName);
		
		try (BufferedReader br = new BufferedReader(new FileReader(fiFile)))
		{
			
			br.readLine(); // salto la primera linea que es la cabecera.
			while((stLine = br.readLine()) != null)
			{
				if (stLine.trim().isEmpty()) {
			        continue; // salta líneas vacías o con espacios
			    }
				stVector = stLine.split(",");
				
				if (Add.bExist(stVector[0])) // comprueba si ya exist el DNI y lo guarda en un Array
				{
					employeeNotImported.add(stVector[0]);  // y lo guardo					
					
				} else {
					// si no existe crea un objeto nuevo y lo pasa al ArraYlist. 
					// como hay valores enteros, y el CSV es todo strings, hay que convertirlos.

					Employee emp = new Employee(
							stVector[0],
							stVector[1],							
							Integer.parseInt(stVector[2]),
							(stVector[3].equalsIgnoreCase("yes")? true : false)							
							);
					AppMain.alEmp.add(emp);
					employeeImported.add(stVector[0]);
					
					Add.createXmlEmp(AppMain.alEmp, false);
				}				
			}			
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		// Ahora muestro los importados y los no.
		
		if (employeeImported.size() > 0) 
		{
			System.out.println("\n==========================================");
			System.out.println("        Employees Imported."); 
			System.out.println("============================================");
		
			for (String stImported : employeeImported) 

				System.out.println("  DNI: " + stImported);
			
			IOTools.pressEnterToContinue();
		}
	
		
		
		if (employeeNotImported.size() > 0) 
		{
			System.out.println("\n==========================================");
			System.out.println("     WARNING:  Employees NOT Imported."); 
			System.out.println("============================================");		
			
			for (String stNotImported : employeeNotImported) 
				System.out.println("  DNI: " + stNotImported);
			IOTools.pressEnterToContinue();
			
		}
		
	}
	/*
	 * Método para importar relaciones.
	 * Compruebo que el código de la vivienda junto con el día, no coincida.
	 * Ya que una misma vivienda no puede ser limpiada dos veces el mismo día.
	 */
	public static void importRel() 
	{
		String stLine;
		String[] stVector;
		String stFileName = AppMain.XML_FILENAME_REL.replace("xml", "csv");
		File fiFile = new  File(stFileName);
		ArrayList<String> alreadyClean = new ArrayList<String>();
		ArrayList<String> notHome = new ArrayList<String>();
		ArrayList<String> notEmployee = new ArrayList<String>();
		ArrayList<String> imported = new ArrayList<String>();		
		Boolean bFound = false;
		
		try (BufferedReader br = new BufferedReader(new FileReader(fiFile)))
		{
			br.readLine(); // salto la primera linea que es la cabecera.
			while((stLine = br.readLine()) != null)
			{
				bFound= false;
				if (stLine.trim().isEmpty()) continue; // salta líneas vacías o con espacios
			    
				stVector = stLine.split(",");	

				if (Add.bExist(stVector[0])) // comprueba si ya exist el dni
				{

					if (Add.bExist(Integer.parseInt(stVector[1]))) // comprueba si existe la vivienda
					{

						// si llega a aquí, significa que existe el código y el DNI
						// pero hay que comprobar que no exista la conexion vivienda - día
						
						
						for(Relation rel : AppMain.alRel) 
						{					
							if(rel.getiCod() == Integer.parseInt(stVector[1]) && rel.getStDay().equalsIgnoreCase(stVector[2])) 
							{
								// si encuentra una coincidencia, la guarda en el array, marca encontrado como true y hace
								// break para que ya no siga buscando

								alreadyClean.add("Home's cod: " + stVector[1] + "  Day: " + stVector[2]);
								bFound = true;
								break;
							}
//							else {								
//								
//															
//							}							
						}	
							// si tras revisar todo el alRel no encontro coincidencia, crea el objeto para guardarlo en el alRel 
							// y luego crear el XML 
						if (!bFound) {

							Relation relation = new Relation(
									stVector[0],
									Integer.parseInt(stVector[1]),
									stVector[2],
									Integer.parseInt(stVector[3])
									);
							AppMain.alRel.add(relation);							
							
							imported.add("Home " + stVector[1] + " Day " +  stVector[2]);	
						}
					
												
					} else  notHome.add(stVector[1]);
					
				}else	notEmployee.add(stVector[0]);
			}
			
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}	
		
		if (notHome.size() > 0) 
		{
			System.out.println("\n==========================================");
			System.out.println(" WARNING: Relations are not imported, because"); 
			System.out.println("  the Home's code is not in table Home");
			System.out.println("============================================");
			
			for (String stNotHome : notHome) 				
				System.out.println("      " + stNotHome);
			
			IOTools.pressEnterToContinue();

		}
		if (notEmployee.size() > 0) 
		{
		System.out.println("\n==========================================");
		System.out.println(" WARNING: Relations are not imported, because"); 
		System.out.println("  the DNI is not in table Employee");
		System.out.println("============================================");
			
		for (String stNotEmployee : notEmployee) 				
			System.out.println("      " + stNotEmployee);

			IOTools.pressEnterToContinue();
		}
		if (alreadyClean.size() > 0) 
		{
			System.out.println("\n==============================================");
			System.out.println(" WARNING: Relations are not imported because the Home"); 
			System.out.println("     is already cleaned that day");
			System.out.println("================================================");
			System.out.println("");
			for (String stNotEmployee : alreadyClean) 				
				System.out.println("      " + stNotEmployee);

				IOTools.pressEnterToContinue();
		}
		if (imported.size() > 0) 
		{
			System.out.println("\n==========================================");
			System.out.println("           Relations Imported."); 
			System.out.println("============================================");
			
			for (String stImported : imported) 				
				System.out.println("      " + stImported);
			
			IOTools.pressEnterToContinue();
		}
			/// finalmente llamamos al método que creara el XML nuevo.
			System.out.println("fin");
		Add.createXmlRel(AppMain.alRel, false);
		}
}
