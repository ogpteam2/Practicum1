import java.util.regex.*;
import be.kuleuven.cs.som.annotate.*;
import java.util.Date;

/**
 * Deze klasse stelt bestanden voor. De naam, grootte,
 * 			 aanmaakdatum, en schrijfbaarheid worden bijgehouden.
 * @invar de grootte van het bestand moet geldig zijn 
 * 		  isValidSize(getSize())
 * 
 * @version	1.0
 * @author Elias en Robbe
 *
 *
 */



public class File {
	
	
	private String name;
	private boolean writable;
	private int size;
	private final Date creationDate;
	private Date modificationDate;
	
	/**
	 * Maakt een nieuw bestand aan met gegeven naam, grootte en schrijfbaarheid
	 * @param name
	 * 		  de te geven naam
	 * @param size
	 * 		  de grootte van het bestand
	 * @param writable
	 * 		  true als het bestand beschrijfbaar is 
	 * @post als de naam niet geldig is wordt de naam: "New Text Document.txt"
	 * 
	 * 
	 */
	
	public File(String name, int size, boolean writable){
		if (isValidName(name) == false){ setName("New Text Document.txt"); }
		else { setName(name); }
		assert isValidSize(size);
		setSize(size);
		this.writable = writable;
		creationDate = new Date();
		modificationDate = creationDate; // dit zorgt ervoor dat modificationDate gelijk is aan creationDate bij aanmaak bestand
	}
	/**
	 * Maakt een bestand aan met grootte nul en een gegeven naam die beschrijfbaar is.
	 * @param name
	 * 		  de gewenste naam van het bestand
	 */
	public File(String name){
		this(name, 0, true);
	}
	/**
	 * geeft de grootte van het bestand terug
	 */
	@Basic
	public int getSize(){
		return size;
	}
	
	/**
	 * Controleert ofdat een grootte geldig is
	 * @param size
	 * 		  de te controleren grootte
	 * @return true als het een geldige grootte is 
	 * 		   false als het geen geldige grootte is
	 */
	@Raw
	private boolean isValidSize(int size){
		return size >= 0 && size - 1 < Integer.MAX_VALUE;
	}
	
	
	/**
	 * 
	 * @param amount
	 * 		  de hoeveelheid waarmee de file moet vergroot worden (in bytes)
	 */
	public void enlarge(int amount){
		setSize(getSize() + amount);
	}
	/**
	 * @pre de hoeveelheid mag niet groter zijn dan de grootte van het bestand
	 * 		isValidSize(getSize() - amount);
	 * @param amount
	 * 		  de hoeveelheid waarmee de file moet verkleind worden (in bytes)
	 */
	public void shorten(int amount){
		assert isValidSize(getSize() - amount);
		setSize(getSize() - amount);
	}
	
	/**
	 * verandert de grootte van het bestand
	 * @pre de size moet geldig zijn
	 *      isValidSize(size);
	 * @param size
	 * 		  de gewenste grootte van het bestand
	 * 
	 */
	private void setSize(int size){
		assert isValidSize(size);
		this.size = size;
		updateModificationTime(); 
	}
	/**
	 * verandert de naam van het bestand
	 * @param name
	 * 		  de nieuwe naam van het bestand
	 * @post als de naam niet geldig is wordt de vorige naam behouden
	 * 		 de aanpassingsdatum wordt dan ook niet aangepast
	 */
	public void setName(String name){
		if (isValidName(name) == false){ return;}
		else 
		{
			this.name = name;
			updateModificationTime(); 
		}
		
	}
	
	/**
	 * update de aanpassingsdatum
	 */
	private void updateModificationTime(){
		modificationDate = new Date();
	}
	
	/**
	 * geeft de aanmaakdatum terug
	 */
	@Basic
	@Immutable
	public Date getCreationDate(){
		return creationDate;
	}
	
	/**
	 * geeft de modificatieDatum terug
	 * @post als de het bestand nog nooit aangepast is geeft het de aanmaakdatum terug
	 */
	@Basic
	public Date getModificationDate(){
		if (modificationDate != null){
			return modificationDate;
		} else {
			return getCreationDate();
		}
	}
	/**
	 * controleert de geldigheid van de naam
	 * @param name
	 * 		  de te controleren naam
	 * @return true als het een geldige naam is
	 *         false als het geen geldige naam is
	 */
	private boolean isValidName(String name){
		if(name.length()==0) return false;
		Pattern valid = Pattern.compile("[a-zA-Z]");
		return valid.matcher(name).matches();
	}
	/**
	 * de gebruiksperiode is het tijdsinterval tussen het
	 *	  creatietijdstip en het tijdstip van laatste wijziging.
	 *    Deze methode geeft weer of de gebruiksperiode van deze file en een andere file overlappen.
	 *    
	 *    Deze methode werkt door volgend stelling (StartA <= EndB) and (EndA >= StartB)
	 *    Deze stelling is correct maar wordt hier niet bewezen.
	 * @param other
	 * 		  een andere file
	 * @return true als ze een overlappend gebruikersperiode hebben
	 * 		   false als ze geen overlappend gebruikersperiode hebben
	 */
	public boolean hasOverlappingUsePeriod(File other){
		if (other == null){
			return false;
		}
		else if (getModificationDate() == getCreationDate() || other.getModificationDate() == other.getCreationDate()){
			return false;
		}
		else if (getCreationDate().getTime() <= other.getModificationDate().getTime() && getModificationDate().getTime() <= other.getCreationDate().getTime() ){
			return true;
		}
		return false;
	}
	
	/**
	 * Wijzigt de schrijfrechten van een bestand
	 * @param writable
	 * 		  een boolean die bepaalt ofdat het bestand schrijfbaar is of niet
	 */
	public void setWritability(boolean writable){
		this.writable = writable;
	}
	
	/**
	 * geeft de schrijfbaarheid terug
	 * @return boolean writable
	 */
	@Basic
	public boolean getWritability(){
		return this.writable;
	}
	
}
