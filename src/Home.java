/**
 * Esta clase es el objeto vivenda que almacenará
 * los datos de la vivienda.
 * @params
 * iCod: Es el código de la vivenda. Este será único
 * type: El tipo de vivienda
 * stCity: ciudad en la que se encuentra
 * iSurface: total metros cuadrados
 * irooms: total habitaciones * 
 * fPrice: Precio que cuesta la limpieza al mes.
 * iPictures: por si se introducen fotos de la casa.
 **/
public class Home 
{

	private int iCod = 0;
	private String stType = "";
	private String stCity = "";
	private int iSurface = 0; 
	private int iRooms = 0;	
	private int iPrice = 0;	
	private int iPictures = 0;
	
	// estos dos parametros vendrán por defecto, pero se pueden o podrán cambiar.
	
	String stCoin = "€";  
	String stUnit = "m2";
	
	/*
	 *  Constructor sin parametros
	 */
	public Home() 
	{}
	
	/*
	 *  Constructor con parametros
	 */
	public Home(int iCod, String stType, String stCity, int iSurface, int iRooms, int iPrice, int iPictures) 
	{
//		super();
		this.iCod = iCod;
		this.stType = stType;
		this.stCity = stCity;
		this.iSurface = iSurface;
		this.iRooms = iRooms;		
		this.iPrice = iPrice;		
		this.iPictures = iPictures;
		
	}
	
	/*
	 * método que muestra las viviendas por pantalla
	 * (bAvailable ? "yes" : "no")): Como bAvailable devuelte true o false,
	 * cambiamos eso por yes o no.
	 */
	public void mostrar() 
	{

		System.out.println("========================");
		System.out.println("Cod:             " + iCod);
		System.out.println("Type:            " + stType);
		System.out.println("City:            " + stCity);
		System.out.println("Surface:         " + iSurface + " " + stUnit);
		System.out.println("Total Rooms:     " + iRooms);		
		System.out.printf(String.format("Price:           %,d %s%n",iPrice,stCoin));		
		System.out.println("Pictures:        " + iPictures);
		System.out.println("========================");
		

	}
	/**
	 * 
	 * Getters And Setters
	 */

	public int getiCod() {
		return iCod;
	}

	public void setiCod(int iCod) {
		this.iCod = iCod;
	}

	public String getStType() {
		return stType;
	}

	public void setStType(String stType) {
		this.stType = stType;
	}

	public String getStCity() {
		return stCity;
	}

	public void setStCity(String stCity) {
		this.stCity = stCity;
	}

	public int getiSurface() {
		return iSurface;
	}

	public void setiSurface(int iSurface) {
		this.iSurface = iSurface;
	}

	public int getiRooms() {
		return iRooms;
	}

	public void setiRooms(int iRooms) {
		this.iRooms = iRooms;
	}

	public int getiPrice() {
		return iPrice;
	}

	public void setiPrice(int iPrice) {
		this.iPrice = iPrice;
	}

	public int getiPictures() {
		return iPictures;
	}

	public void setiPictures(int iPictures) {
		this.iPictures = iPictures;
	}

	public String getStCoin() {
		return stCoin;
	}

	public void setStCoin(String stCoin) {
		this.stCoin = stCoin;
	}

	public String getStUnit() {
		return stUnit;
	}

	public void setStUnit(String stUnit) {
		this.stUnit = stUnit;
	}
	
	/*
	 * Getters and Setters
	 */
	

}
