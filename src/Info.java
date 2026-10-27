public class Info {
	
	
	public static void esp() 
	{
		System.out.println("""
		******************************************************************************************
		******************************************************************************************
			
				Esta app sirve para un negocio de limpieza de casas.
				El menu tiene varias opciones.
				
					Limpia Casas Bu Yunchaokete
			========================================================================
					  0. Exit
					  1. Add
					  2. List
					  3. Search
					  4. Remove
					  5. Erase File
					  6. Export to File CSV
					  7. Import from CSV
					  8. See in HTML
					  9. About the APP
			========================================================================
			    Select an option: 
			  
			Usos y comprobaciones:
			
			1.
			Pregunto que se quiere añadir.
			Al añadir una Home, compruebo que su código no exista.
			Al añadir un Employee, compruebo que su DNI no exista.
			Al añadir una Relation, compruebo que exista la Home y el Employee. Ya que no se puede limpiar una casa que no existe o ser 
			limpiada por un empleado que no existe.
			También que la relación Cod y Day, no exista. Ya que una casa no puede ser limpiada dos veces el mismo día.
			
			2.
			Pregunto que se quiere listar.
			Muestra por pantalla todos los registros de los Homes, Employees o Relations.
			Como pueden haber muchos, pregunto si quiere verlos todos de una o con pausas.
			Si elijo mostrar Relaciones, muestro el campo DNI que está en esa tabla, pero también voy a la tabla Empleado, para mostrar el campo Nombre.
			
			3.
			Pregunto que se quiere buscar.
			Y luego pregunto por que campo se quiere buscar.
			Si lo encuentro, lo muestro/s.
			
			4.
			Pregunto que se quiere eliminar.
			Solo elimino por DNI o Cod.
			Si elimino un empleado, o una vivienda, elimino los registros de la tabla Relations, donde aparezca ese empleado o vivienda. 
			Entiendo que si fuese una BBDD lo haría solo, pero en este caso lo hago yo.
			
			5.
			Pregunto que fichero se quiere eliminar. 
			Y lo elimino.
			En este caso no elimino ni el fichero ni los registros de Relations.
			Ahí ya es cosa del que lo eliminó.
			
			6.
			Pregunto que fichero se quiere exportar.
			Compruebo que exista el fichero .xml, porque si no es tontería exportar un fichero vacío.
			
			7.
			Pregunto que fichero se quiere importar.
			Al igual que al añadir, compruebo que no se repita el DNI en la tabla Employee, el Cod en la tabla Home, 
			y la relación Cod-Day en la tabla Relation.
			
			8.
			Pregunto que tabla se quiere ver en HTML.
			Se genera el HTML y además se abre el navegador automaticamente.
			
			9.
			Muestra información del funcionamiento de la APP. Hay dos idiomas.
			    
		******************************************************************************************
		******************************************************************************************

			    
							""");
	}
    
	public static void en() 
	{
		System.out.println("""
		******************************************************************************************
		******************************************************************************************
    
 
				This app is for a Cleaning Homes business.
				The menu has several options.
			
					Limpia Casas Bu Yunchaokete
			========================================================================
					  0. Exit
					  1. Add
					  2. List
					  3. Search
					  4. Remove
					  5. Erase File
					  6. Export to File CSV
					  7. Import from CSV
					  8. See in HTML
					  9. About the APP
			========================================================================
			    Select an option: 
			
			Usage and validations:
			
			1.
			Ask what the user wants to add.
			When adding a "Home," verify that its code does not already exist.
			When adding an "Employee," verify that their DNI (ID number) does not already exist.
			When adding a "Relation," verify that both the Home and the Employee exist—since a house that doesn't exist cannot be cleaned, 
			and an employee who doesn't exist cannot perform the cleaning.
			Also, ensure that the combination of "Code" (Home) and "Day" does not already exist, as a house cannot be cleaned twice on the same day.
			
			2.
			Ask what the user wants to list.
			Display all records for Homes, Employees, or Relations.
			Since there could be many records, ask whether the user wants to view them all at once or with pauses.
			If displaying Relations, show the DNI field from that table, and also retrieve the "Name" field from the Employee table.
			
			3.
			Ask what the user wants to search for.
			Then, ask which field to search by.
			If a match is found, display the result(s).
			
			4.
			Ask what the user wants to delete.
			Deletion is performed only by DNI or Code.
			If an employee or a home is deleted, also remove any corresponding records from the "Relations" table. (In a standard database, 
			this would happen automatically, but here, it must be handled manually.)
			
			5.
			Ask which file the user wants to delete.
			Then, delete it.
			In this case, neither the file itself nor the associated records in the "Relations" table are automatically removed; 
			that is left to the user who performed the deletion.
			
			6.
			Ask which file the user wants to export.
			Verify that the .xml file exists; otherwise, exporting an empty file would be pointless.
			
			7.
			Ask which file the user wants to import. Just as when adding a record, I verify that there are no duplicates for the DNI in 
			the `Employee` table, the `Cod` in the `Home` table, and the `Cod-Day` relationship in the `Relation` table.

			8.
			The user is prompted to select which table they wish to view in HTML format.
			The HTML is generated, and the web browser opens automatically.
			
			9.
			Displays information on how the app works. Available in two languages.
						    
		******************************************************************************************
		******************************************************************************************
			    
							""");
    
	}
}
