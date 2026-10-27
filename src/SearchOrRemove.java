import java.io.File;

/**
 * Clase en la que buscaremos un cierto tipo de viviendas, y  
 * accediendo al ArrayList mostraremos solo las que coincidan
 * con el filtro introducido.
 * Previamente pediremos porque quiere filtrar.
 * Si se filtra por precio habŕa tres opciones:
 * Precio exacto, hasta y desde.
 * 
 * También la usaremos para eliminar una vivienda.
 * Ya que tenemos que buscar el código de la vivienda que quiere eliminar.
 **/
public class SearchOrRemove {
	
	public static void search() 
	{
		int iOption = IOTools.showMenu("What do you want to search? ", "Cancel", "A Home", "An Employee", "A Relation");
		
		if (iOption == 1) searchHome();
		if (iOption == 2) searchEmp();
		if (iOption == 3) searchRel();
	}

	/**
	 * Método que busca una vivienda por diferentes campos y si lo encuentra lo muestra.
	 */
	public static void searchHome() {
		
		System.out.println("--------------------------------------------");
		System.out.println("\t You are going to search a Home");
		System.out.println("--------------------------------------------");
		// para no tener tantas variables, solo crearé una de cada tipo.
		
		int iBuscar;
		String stBuscar;		
		Boolean bFound = false;
		int iMenuSearch;
		do
		{
			iMenuSearch = IOTools.showMenu("Select a field you want to search", 
					"Cancel",
					"Code",
					"Type",
					"City",
					"Surface",
					"Rooms",				
					"Price");
			switch (iMenuSearch)
			{
				/*
				 *  dependiendo el número a introducir,pedirá un parametro.
				 *  Luego pasará por todos los objetos del ArrayList y comparará
				 *  el parametro que tiene el objeto con el parametro que ha 
				 *  introducido el usuario. Si coincide lo mostrará y cambiará
				 *   bFound a true. Si no, mostrará un mensaje de no encontrado. 
				 */
				case 0: bFound = true; // si cancela, pondrá true para poder salir.
					break;
				case 1 : 
					iBuscar = IOTools.askInt("Introduce the Code");
					
					for (Home home : AppMain.alHome) if (home.getiCod() == iBuscar) { home.mostrar(); bFound = true;}
					break;
				case 2 : 
					stBuscar = IOTools.askString("Introduce the Type", "House", "Appartament", "Chalet", "Bungalow");
					for (Home home : AppMain.alHome) if (home.getStType().equals(stBuscar)) { home.mostrar(); bFound = true;}
					break;
				case 3 : 
					stBuscar = IOTools.askString("Introduce the City");
					for (Home home : AppMain.alHome) if (home.getStCity().equals(stBuscar)) { home.mostrar(); bFound = true;}
					break;
				case 4 : 
					iBuscar = IOTools.askInt("Introduce the Surface");
					for (Home home : AppMain.alHome) if (home.getiSurface() == iBuscar) { home.mostrar(); bFound = true;}
					break;
				case 5 : 
					iBuscar = IOTools.askInt("Introduce the Rooms");
					for (Home home : AppMain.alHome) if (home.getiRooms() == iBuscar) { home.mostrar(); bFound = true;}
					break;
				case 6 : 
					iBuscar = IOTools.askInt("Introduce the Price");
					for (Home home : AppMain.alHome) if (home.getiPrice() == iBuscar) { home.mostrar(); bFound = true;}
					break;
	
			}
			if (!bFound) System.out.println ("\n --- No Homes found with that filter. ---");
			
			if (iMenuSearch != 0) IOTools.pressEnterToContinue();
			
		}while(iMenuSearch != 0);
	}	

	/**
	 * Método que busca un empleado por diferentes campos y si lo encuentra lo muestra.
	 */
	
	public static void searchEmp() {
		
		System.out.println("--------------------------------------------");
		System.out.println("\t You are going to search an Employee");
		System.out.println("--------------------------------------------");

		int iBuscar;
		String stBuscar;		
		Boolean bFound = false;
		int iMenuSearch;
		do
		{
			iMenuSearch = IOTools.showMenu("Select a field you want to search", 
					"Cancel",
					"DNI",
					"Full Name",
					"Tlf",
					"Active");
		
			switch (iMenuSearch)
			{
				/*
				 *  dependiendo el número a introducir,pedirá un parametro.
				 *  Luego pasará por todos los objetos del ArrayList y comparará
				 *  el parametro que tiene el objeto con el parametro que ha 
				 *  introducido el usuario. Si coincide lo mostrará y cambiará
				 *   bFound a true. Si no, mostrará un mensaje de no encontrado. 
				 */
				case 0 : bFound = true; // si cancela, pondrá true para poder salir.
					break;
				case 1 : 
					stBuscar = IOTools.askString("Introduce the DNI");				
					for (Employee emp : AppMain.alEmp) if (emp.getStDni().equalsIgnoreCase(stBuscar)) { emp.mostrar(); bFound = true;}
					break;
				case 2 : 
					stBuscar = IOTools.askString("Introduce the Name");
					for (Employee emp : AppMain.alEmp) if (emp.getStFullName().equalsIgnoreCase(stBuscar)) { emp.mostrar(); bFound = true;}
					break;
				case 3 : 
					iBuscar = IOTools.askInt("Introduce the Tlf");
					for (Employee emp : AppMain.alEmp) if (emp.getiTlf() == iBuscar) { emp.mostrar(); bFound = true;}
					break;
				case 4 : 
					iBuscar = IOTools.askInt("1. Active -- 2. No Active",1 ,2); 
					
					// como devuelve un entero, hay que pasarlo a booleano	
					for (Employee emp : AppMain.alEmp) if (emp.getbActive() == (iBuscar == 1)) { emp.mostrar(); bFound = true;}
					break;
				default :
					break;
	
			}
			if (!bFound) System.out.println ("\n --- No Employees found with that filter. ---");
			
			if (iMenuSearch != 0) IOTools.pressEnterToContinue();
			
		}while(iMenuSearch != 0);

		
	}
	/**
	 * Método que busca una relacion por diferentes campos y si lo encuentra lo muestra.
	 */
	public static void searchRel() {
		
		System.out.println("--------------------------------------------");
		System.out.println("\t You are going to search a Relation");
		System.out.println("--------------------------------------------");
		
		int iBuscar;
		String stBuscar;		
		Boolean bFound = false;
		int iMenuSearch;
		do
		{
			/**
			 * 	String stDni;
		int iCod;
		String stDay;
		int iHours;
			 * 
			 */
			iMenuSearch = IOTools.showMenu("Select a field you want to search", 
					"Cancel",
					"DNI",
					"Home's Cod",
					"Day");
		
			switch (iMenuSearch)
			{
				/*
				 *  dependiendo el número a introducir,pedirá un parametro.
				 *  Luego pasará por todos los objetos del ArrayList y comparará
				 *  el parametro que tiene el objeto con el parametro que ha 
				 *  introducido el usuario. Si coincide lo mostrará y cambiará
				 *   bFound a true. Si no, mostrará un mensaje de no encontrado. 
				 */
				case 0 : bFound = true; // si cancela, pondrá true para poder salir.
					break;
				case 1 : 
					stBuscar = IOTools.askString("Introduce the DNI");				
					for (Relation rel : AppMain.alRel) if (rel.getStDni().equalsIgnoreCase(stBuscar)) { rel.mostrar(); bFound = true;}
					break;
				case 2 : 
					iBuscar = IOTools.askInt("Introduce the Home's Code");
					for (Relation rel : AppMain.alRel) if (rel.getiCod() == iBuscar) { rel.mostrar(); bFound = true;}
					break;			
				case 3 : 
					stBuscar = IOTools.askString("Introduce the day");
					for (Relation rel : AppMain.alRel) if (rel.getStDay().equalsIgnoreCase(stBuscar)) { rel.mostrar(); bFound = true;}
					break;
				default :
					break;
	
			}
			if (!bFound) System.out.println ("\n --- No Relation found with that filter. ---");
			
			if (iMenuSearch != 0) IOTools.pressEnterToContinue();
			
		}while(iMenuSearch != 0);
	
		
	}
//######################################################  ELIMINAR
//######################################################  ELIMINAR
//######################################################  ELIMINAR
	
	
	/**
	 * Método para eliminar una vivienda por código.
	 * Pedirá el código, y buscará en el ArrayList ese código.
	 * si lo encuentra lo eliminará.
	 * Después comprobará si se ha eliminado, comparando el 
	 * tamaño del ArrayList antes y después de la eliminación.
	 * @param 
	 * alHome: El ArrayList que contiene todas las viviendas
	 * iIndex: para saber el indice del Array en el cual está el registro
	 * a eliminar.
	 * iAfter, iBefore: para saber el tamaño del ArrayList
	 * iErase: para saber si el usuario quiere borrarlo finalmente o no.
	 * iCodErase: El código de la vivienda a eliminar.
	 * bFound: Para saber si se ha encontrado el registro, independientemente
	 * de si se elimina o no.
	 */
	
	public static void remove() 
	{
		int iOption = IOTools.showMenu("What do you want to remove? ", "Cancel", "A Home", "An Employee", "A Relation");
				
			if (iOption == 1) removeHome();
			if (iOption == 2) removeEmp();
			if (iOption == 3) removeRel();
		
		
	}
	
	/**
	 * Método que elimina una vivienda por cod, y sus relaciones 
	 * @param
	 * iErase: guarda el valor 0 o 1 dependiendo si quiere eliminar el registro/s
	 * bFound: para saber si se encontro al menos un registro
	 * iCodErase: es el código de la vivienda que queremos eliminar
	 */
	public static void removeHome() 
	{
		int iErase = 0;
		Boolean bFound = false;
		int iCodErase = IOTools.askInt("Introduce the code of the Home you want to ERASE: ");
		
		// For para ver si hay una o mas coincidencias. Aunque solo debería haber una.
		for (Home home : AppMain.alHome)
		{

			if (home.getiCod() == iCodErase) 
			{ 
				bFound = true; 
				home.mostrar();
			}
		}
				
		if (bFound) iErase = IOTools.showMenu(" WARNING: Are you sure you want to erase this Home?","CANCEL", "Yes");
		
		
				if (iErase == 1 )
				{
									
					// eliminamos el objeto/s del ArrayList que coincida, si devuelve false es que no se sha podido eliminar
					// y devolverá un mensaje
					
					if(AppMain.alHome.removeIf(Home -> Home.getiCod() == iCodErase))
					{
						// Ahora eliminamos el fichero y llamamos a escribir para
						// que escriba el ArrayList de nuevo, ya sin los registros eliminados.
						
						eliminarXML(AppMain.HTML_FILENAME_HOME, false);  // paso false porque no quiero que escriba nada.
						Add.createXmlHome(false);					
												
						/* si llegados aquí no ha dado fallo la creación del nuevo XML, 
						*	significa que se eliminó la vivienda.
						*/
						System.out.println("\n============================================");
						System.out.println("\tHome erased.");
						System.out.println("============================================");
						IOTools.pressEnterToContinue();
					}
					else 
					{
						System.out.println("\n============================================");
						System.out.println("\tERROR: Home NOT erased.");
						System.out.println("============================================");
						IOTools.pressEnterToContinue();
					}
					// Una vez liminada la vivienda, tengo que elimiar las relaciones que tenía esa vivienda,
					// ya que ya no existe. Muestro mensaje.
					
					if (AppMain.alRel.removeIf(Relation -> Relation.getiCod() == iCodErase)) 
					{
						// si se ha elimindo el registro del ARRAY, creamos el nuevo XML
						eliminarXML(AppMain.HTML_FILENAME_REL, false);  // paso false porque no quiero que escriba nada.
						Add.createXmlRel(AppMain.alRel,false);						
						
						System.out.println("\n============================================");
						System.out.println("\tHome Erased in table Relation.");
						System.out.println("============================================");
						IOTools.pressEnterToContinue();
					}
					else
					{
						System.out.println("\n============================================");
						System.out.println("\tERROR: Home NOT Erased in table Relation.");
						System.out.println("============================================");
						IOTools.pressEnterToContinue();
					}
						
				}
		// Sino en contro el código de la casa, muestro mensaje y pido de nuevo
		if (!bFound) {
			
			System.out.println("\n============================================");
			System.out.println("\tHome With the code " + iCodErase + " NOT FOUND.");
			System.out.println("============================================");
			IOTools.pressEnterToContinue();
			remove();
		}	
	}
	
	/**
	 * Método que elimina un empleado por DNI, y sus relaciones 
	 */
	public static void removeEmp() 
	{
		int iErase = 0;
		Boolean bFound = false;
		String stCDniErase = IOTools.askString("Introduce the DNI of the Employee you want to ERASE: ");
		
		for (Employee emp : AppMain.alEmp)
		{

			if (emp.getStDni().equalsIgnoreCase(stCDniErase))  
			{ 
				bFound = true; 
				emp.mostrar();
			}
		}
		if (bFound) iErase = IOTools.showMenu(" WARNING: Are you sure you want to erase this Employee?","CANCEL", "Yes");
				
		if (iErase == 1 ) // Erase sera 1 solo si se encontro y pulsó4 YES.
		{
			// eliminamos el objeto/s del ArrayList que coincida, si devuelve false es que no se sha podido eliminar
			// y devolverá un mensaje
			
			if(AppMain.alEmp.removeIf(Employee -> Employee.getStDni().equalsIgnoreCase(stCDniErase)))
			{
				// Ahora eliminamos el fichero y llamamos a escribir para
				// que escriba el ArrayList de nuevo, ya sin los registros eliminados.
				
				eliminarXML(AppMain.HTML_FILENAME_EMP, false);  // paso false porque no quiero que escriba nada.
				Add.createXmlEmp(AppMain.alEmp,false);					
				
				/* si llegados aquí no ha dado fallo la creación del nuevo XML, 
				*	significa que se eliminó el empleado.
				*/

				System.out.println("\n============================================");
				System.out.println("\tEmployee erased.");
				System.out.println("============================================");
				IOTools.pressEnterToContinue();
			}
			else 
			{
				System.out.println("\n============================================");
				System.out.println("\tERROR: Employee NOT erased.");
				System.out.println("============================================");
				IOTools.pressEnterToContinue();
			}
			// Una vez eliminado el empleado, tengo que elimiar las relaciones que tenía ese empleado,
			// ya que ya no existe. Muestro mensaje.
			
			if (AppMain.alRel.removeIf(Relation -> Relation.getStDni().equalsIgnoreCase(stCDniErase))) 
			{
				// si se ha elimindo el registro del ARRAY, creamos el nuevo XML
				
				eliminarXML(AppMain.HTML_FILENAME_REL, false);  // paso false porque no quiero que escriba nada.
				Add.createXmlRel(AppMain.alRel,false);
				
				
				System.out.println("\n============================================");
				System.out.println("\tEmployee Erased in table Relation.");
				System.out.println("============================================");
				IOTools.pressEnterToContinue();
			}
			else
			{
				System.out.println("\n============================================");
				System.out.println("\tERROR: Employee NOT Erased in table Relation.");
				System.out.println("============================================");
				IOTools.pressEnterToContinue();
			}			
		
		}
		if (!bFound) {
					
					System.out.println("\n============================================");
					System.out.println("\tEmployee with the DNI " + stCDniErase + " NOT FOUND.");
					System.out.println("============================================");
					IOTools.pressEnterToContinue();
					remove();
		}
}


	/**
	 * 
	 */
	public static void removeRel() 
	{
		int iErase = 1;
		int iCodErase = -1;
		String stDniErase = "";
		Boolean bFound = false;
		
		/**
		 * Si no me pasan nada, preguntaré a traves de que campo quiere eliminar.
		 * Si me pasa un campo, es que viene de eliminar una vivienda o empleado y
		 * es lo que quiere eliminar
		 */		
			int iRelation = IOTools.showMenu("Select through which field you want to erase" , "Cancel","Home's code", "DNI Employee");
								
			 if (iRelation == 1) iCodErase = IOTools.askInt("Write the Home's Code: ");
			 if (iRelation == 2) stDniErase = IOTools.askString("Write the DNI of the employee: ");			
		
	
		if (iRelation == 1)  // pedirá código
		{
			for (Relation rel : AppMain.alRel)  // xml: 1 ic 1

			{
	
				if (rel.getiCod() == iCodErase) 				
				{
					bFound = true; 
					
					rel.mostrar(); 	
				}
				
			}		
			
			if (bFound) 
			{
				iErase = IOTools.showMenu(" WARNING: Are you sure you want to erase the Relations?","CANCEL" , "Yes");	
				
				if (iErase == 1 )
				{
									
					// tengo que crear un final para usar el lambda. Creo el lambda para que elimine todas los registros.
					final int fiCode = iCodErase;
					
						//Esto eliminarĺa todas las relaciones que correspondan al DNI que nos pasen por teclado
					if (AppMain.alRel.removeIf(Relation -> Relation.getiCod() == fiCode))			
					{

	
						/* Si se han eliminado los registros del Array, 
						 * primero elimino el fichero XML existente, para que no me de problemas de sobreescritura.
						 * Luego llamamos al método que que crea el XML para que cree uno nuevo sin los registros eliminados.						
						*/
						
						eliminarXML(AppMain.HTML_FILENAME_REL, false);  // paso false porque no quiero que muestre un mensaje.
						Add.createXmlRel(AppMain.alRel, false);					
											
						
						System.out.println("============================================");
						System.out.println("\tRelations erased.");
						System.out.println("============================================");
						IOTools.pressEnterToContinue();
						
					}else 
					{	// si no se pudo eliminar:
						
						
							System.out.println("\n============================================");
							System.out.println("\tERROR: Relations NOT erased.");
							System.out.println("============================================");
							IOTools.pressEnterToContinue();
						
					}
									
				}
			}else{
				
				System.out.println("============================================");
				System.out.println("\tRelations withe code " + iCodErase + " NOT FOUND.");
				System.out.println("============================================");
				IOTools.pressEnterToContinue();
			
				}
		}
		
		if (iRelation == 2)  // pedirá dni
		{
			for (Relation rel : AppMain.alRel)  // xml: 1 ic 1

			{
	
				if (rel.getStDni().equalsIgnoreCase(stDniErase)) 				
				{
					bFound = true; 
					
					rel.mostrar(); 	
				}
				
			}		
			
			if (bFound)    
			{	
				iErase = IOTools.showMenu(" WARNING: Are you sure you want to erase the Relations?","CANCEL" , "Yes");	
				
				if (iErase == 1 )
				{
									
					// tengo que crear un final para usar el lambda. Creo el lambda para que elimine todas los registros.
					final String fiDni = stDniErase;
					
					AppMain.alRel .removeIf(Relation -> Relation.getStDni().equalsIgnoreCase(fiDni));

					// Ahora eliminamos el fichero y llamamos a escribir para
					// que escriba el ArrayList, sin los registros eliminados
					
					//eliminarXML(false);  // paso false porque no quiero que muestre un mensaje.
					Add.createXmlRel(AppMain.alRel, false);					
					

					
					/* si llegdos aquí no ha dado fallo la creación del nuevo XML, 
					*	comprobamos si el ArrayList tiene un tamaño menor, lo cual indica que
					* se eliminó la vivienda.
					* */
					System.out.println("============================================");
					System.out.println("\tRelations erased.");
					System.out.println("============================================");
					IOTools.pressEnterToContinue();
					
									
				}
			}
					
		}
			
		else{
				
				System.out.println("============================================");
				System.out.println("\tRelations with the DNI " + stDniErase +" NOT FOUND.");
				System.out.println("============================================");
				IOTools.pressEnterToContinue();
			}
		

	}
	
	
	/**
	 * Método  que elimina el fichero XML.
	 * Puede venir del menu, o de un método
	 * Si viene de un método no debe mostar mensaje, pues es algo interno
	 * stFile: es el nombre del fichero
	 * bTexto: es para saber si hay que mostrar un texto o no.
	 **/
	public static void eliminarXML(String stFile, boolean bTexto ) 
	{			
			
		File file = new File(stFile);
		int iEraseFile = 0;
		
		
		if (bTexto)   //Si quiere un mensaje, comprobará si existe y preguntará si esta seguro de eliminar
		{ 
			
			if (file.exists()) {
				
				iEraseFile = IOTools.showMenu(" WARNING: Are you sure you want to erase the File: " + stFile + " ? " +
												"\n          Could be there problems with the others tables.","CANCEL" , "Yes");
				
				if (iEraseFile == 1) // si dice yes intenta eliminar el fichero. y muestra mensaje tanto si pudo como si no.
				{						
					System.out.println("============================================");  
					System.out.println("\tFile " + stFile + (file.delete()? " Erased" : " NOT erased"));
					System.out.println("============================================");
					IOTools.pressEnterToContinue();


					
				}
			
			}else  //mensaje si no existe
			{
				System.out.println("============================================");
				System.out.println("\tFile " + stFile + " Not exists.");
				System.out.println("============================================");
				IOTools.pressEnterToContinue();
			}
		}else file.delete(); //Si no queremos mostrar mensaje, directamente se eliminará
	
			
		// TAnto si es con texto o sin texto, Si elimino un fichero XML tengo que limpiar el Array para que no hayaa incongruencias.
		// Tengo que saber que fichero se elimino.
		if (stFile.equalsIgnoreCase("src/Docs/Home.xml")) AppMain.alHome.clear();
		else if (stFile.equalsIgnoreCase("src/Docs/Employee.xml")) AppMain.alEmp.clear();
		else if (stFile.equalsIgnoreCase("src/Docs/Relation.xml")) AppMain.alRel.clear();
	}
}
