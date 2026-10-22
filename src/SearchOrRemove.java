import java.io.File;
import java.util.ArrayList;

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

	public static void searchHome(ArrayList<Home> alHome) {
		
		// para no tener tantas variables, solo crearé una de cada tipo.
		
		int iBuscar;
		String stBuscar;		
		Boolean bFound = false;
		
		int iMenuSearch = IOTools.showMenu("Select a field you want to search", 
				"Code",
				"Type",
				"City",
				"Surface",
				"Rooms",				
				"Price",				
				"Pictures");
		switch (iMenuSearch)
		{
			/*
			 *  dependiendo el número a introducir,pedirá un parametro.
			 *  Luego pasará por todos los objetos del ArrayList y comparará
			 *  el parametro que tiene el objeto con el parametro que ha 
			 *  introducido el usuario. Si coincide lo mostrará y cambiará
			 *   bFound a true. Si no, mostrará un mensage de no encontrado. 
			 */
			
			case 0 : 
				iBuscar = IOTools.askInt("Introduce the Code: ");
				for (Home home : alHome) if (home.getiCod() == iBuscar) { home.mostrar(); bFound = true;}
				break;
			case 1 : 
				stBuscar = IOTools.askString("Introduce the Type: ", "House", "Appartament", "Chalet", "Bungalow");
				for (Home home : alHome) if (home.getStType().equals(stBuscar)) { home.mostrar(); bFound = true;}
				break;
			case 2 : 
				stBuscar = IOTools.askString("Introduce the City: ");
				for (Home home : alHome) if (home.getStCity().equals(stBuscar)) { home.mostrar(); bFound = true;}
				break;
			case 3 : 
				iBuscar = IOTools.askInt("Introduce the Surface: ");
				for (Home home : alHome) if (home.getiSurface() == iBuscar) { home.mostrar(); bFound = true;}
				break;
			case 4 : 
				iBuscar = IOTools.askInt("Introduce the Rooms: ");
				for (Home home : alHome) if (home.getiRooms() == iBuscar) { home.mostrar(); bFound = true;}
				break;
			case 6 : 				
				bFound = askPrice(alHome);
				break;
			case 7 : 
				iBuscar = IOTools.askInt("1. Available / 2. Sold",1 ,2); // como devuelve un entero, hay que pasarlo a booleano
				

			case 8 : 
				iBuscar = IOTools.askInt("Introduce the Number of the pictures: ");
				for (Home home : alHome) if (home.getiPictures() == iBuscar) { home.mostrar(); bFound = true;}
				break;
		}
		if (!bFound) System.out.println("\n --- No Homes found with that filter. ---");
		IOTools.pressEnterToContinue();
	}
	
	/**
	 * 
	 * Método que pregunta el precio y el rango.
	 * Luego compará todos los precios de todos las viviendas.
	 * Dependiendo el rando elegido, devolverá unas u otras.
	 * @return devuelve true si encontró al menos uno. Sino, false.
	 */
	public static boolean askPrice(ArrayList<Home> al) 
	{
		int iBuscar = IOTools.askInt("Introduce the Price: ");
		int iRange = IOTools.showMenu("What range ?", "From x  Price", "Until x Price", "The exactly Price");				
		Boolean bFound = false;
		
		for (Home home : al) {
			switch (iRange)
			{
				case 0 : if (home.getiPrice() >= iBuscar) { home.mostrar(); bFound = true; }
					break;
				case 1 : if (home.getiPrice() <= iBuscar) { home.mostrar(); bFound = true; }
					break;
				case 2 : if (home.getiPrice() == iBuscar) { home.mostrar(); bFound = true; }
					break;
			}			
		}
		
		return bFound;	
	}
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
	public static void removeHome(ArrayList<Home> alHome) 
	{
		int iIndex = 0;
		int iErase = 1;
		int iAfter, iBefore;
		Boolean bFound = false;
		int iCodErase = IOTools.askInt("Introduce the code of the Home you want to ERASE: ");
		for (Home home : alHome)
		{

			if (home.getiCod() == iCodErase) 
			{ 
				bFound = true; 
				home.mostrar(); 			
				iErase = IOTools.showMenu(" WARNING: Are you sure you want to erase this Home?","YES","Cancel");
				if (iErase == 0 )
				{
					iBefore = alHome.size();					
											
					alHome.remove(iIndex);   // eliminamos el objeto del ArrayList
					
					// Ahora eliminamos el fichero y llamamos a escribir para
					// que escriba el ArrayList, ya sin la última vivienda
					
					eliminarXML(false);  // paso false porque no quiero que escriba nada.
					Add.createXmlHome(alHome);					
					
					iAfter = alHome.size();
					
					/* si llegdos aquí no ha dado fallo la creación del nuevo XML, 
					*	comprobamos si el ArrayList tiene un tamaño menor, lo cual indica que
					* se eliminó la vivienda.
					* */
					System.out.println("============================================");
					System.out.println("\tHome " + iCodErase + (iBefore > iAfter ?  " erased." : " NOT ERASED."));
					System.out.println("============================================");
					IOTools.pressEnterToContinue();
					
					break;					
				}
			}
			iIndex++;

		}
		if (!bFound) {
			
			System.out.println("============================================");
			System.out.println("\tHome " + iCodErase + " NOT FOUND.");
			System.out.println("============================================");
			IOTools.pressEnterToContinue();
		}
		

	}
	/**
	 * Método  que elimina el fichero XML.
	 **/
	public static void eliminarXML(boolean bTexto ) {			
			
		File file = new File(AppMain.XML_FILENAME_HOME);
		
		if (file.delete() && bTexto) {
			
			System.out.println("============================================");
			System.out.println("\tFichero " + AppMain.XML_FILENAME_HOME + " erased.");
			System.out.println("============================================");
			IOTools.pressEnterToContinue();
			
		}
	}
	
}	
