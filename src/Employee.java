/**
 * Esta clase es el objeto empleado que almacenará
 * los datos del empleado.
 * stDni, stFullName y iTlf son datos personales.
 * bActive es para saber si está en activo o ausente. 
 * (Baja, vacaciones, curso ...)
 **/
public class Employee {

	private String stDni;
	private String stFullName;
	private int iTlf;
	private boolean bActive;
	
	
	public Employee() 
	{}
	
	public Employee(String stDNI, String stFullName, int iTlf, boolean bActive) 
	{
		this.stDni = stDNI;
		this.stFullName = stFullName;
		this.iTlf = iTlf;
		this.bActive = bActive;
	}
	
	public void mostrar() 
	{
		System.out.println("========================");
		System.out.println("DNI:            " + stDni);
		System.out.println("Full Name:      " + stFullName);
		System.out.println("TLF:            " + iTlf);
		System.out.println("Active:         " + (bActive ? "YES" : "NO"));
		System.out.println("========================");
	}
	
	
	/**
	 * 
	 * Getters And Setters
	 */

	public String getStDni() {
		return stDni;
	}

	public void setStDni(String stDni) {
		this.stDni = stDni;
	}

	public String getStFullName() {
		return stFullName;
	}

	public void setStFullName(String stFullName) {
		this.stFullName = stFullName;
	}

	public int getiTlf() {
		return iTlf;
	}

	public void setiTlf(int iTlf) {
		this.iTlf = iTlf;
	}

	public boolean getbActive() {
		return bActive;
	}

	public void setbActive(boolean bActive) {
		this.bActive = bActive;
	}
	
	
}
