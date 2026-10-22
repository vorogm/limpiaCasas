/**
 * Esta clase es para asociar las casas que hay que limpiar 
 * con el trabajador que tiene que ir.
 * @params 
 * stDNI es el DNI del trabajador
 * iCod es el código de la vivienda
 * stDay es el día de la semana que se hará la limpieza
 * iHours son la horas que tiene asignadas para que se limpie.
 */
public class Relation {

	String stDni;
	int iCod;
	String stDay;
	int iHours;
	
	public Relation() 
	{}
	
	public Relation(String stDni, int iCod, String stDay, int iHours) 
	{
		this.stDni = stDni;
		this.iCod = iCod;
		this.stDay = stDay;
		this.iHours = iHours;
	}
	
	public void mostrar() 
	{
		System.out.println("========================");
		System.out.println("DNI:      " + stDni);
		mostrarNombre();
		System.out.println("Cod:      " + iCod);
		System.out.println("Day:      " + stDay);
		System.out.println("Hours:    " + iHours);
		System.out.println("========================");
	}
	/**
	 * Método que busca en el Array de empleados un dni,
	 * y devuelve el el nombre 
	 * @return
	 */
	public String mostrarNombre() 
	{
//		ArrayList
		for (Employee emp : AppMain.getAlEmployee()) 
			
			if (emp.getStDni().equalsIgnoreCase(stDni)) 
				
				System.out.println("Name:      " + emp.getStFullName());
		
		return "";
	}

	public String getStDni() {
		return stDni;
	}

	public void setStDni(String stDni) {
		this.stDni = stDni;
	}

	public int getiCod() {
		return iCod;
	}

	public void setiCod(int iCod) {
		this.iCod = iCod;
	}

	public String getStDay() {
		return stDay;
	}

	public void setStDay(String stDay) {
		this.stDay = stDay;
	}

	public int getiHours() {
		return iHours;
	}

	public void setiHours(int iHours) {
		this.iHours = iHours;
	}
	
	
	
}
