//@author 
import java.util.regex.*;
import be.kuleuven.cs.som.annotate.*;
import java.util.Date;

/**
 * Deze klasse stelt bestanden voor. De naam, grootte,
 * 			 aanmaakdatum, en schrijfbaarheid worden bijgehouden.
 * 
 * @version	1.0
 * @author Elias en Robbe
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
	 * 
	 * 
	 */
	
	public File(String name, int size, boolean writable){
		assert isValidName(name);
		setName(name);
		assert isValidSize(size);
		setSize(size);
		creationDate = new Date();
		modificationDate = creationDate; // dit zorgt ervoor dat modificationDate gelijk is aan creationDate bij aanmaak bestand
	}
	
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
	
	private boolean isValidSize(int size){
		return size >= 0 && size - 1 < Integer.MAX_VALUE;
	}
	
	public void enlarge(int amount){
		setSize(getSize() + amount);
	}
	
	public void shorten(int amount){
		setSize(getSize() - amount);
	}
	
	
	private void setSize(int size){
		assert isValidSize(size);
		this.size = size;
		updateModificationTime(); // probleem want dit gaat niet hetzelfde zijn als creation date in het begin // opgelost
	}
	
	public void setName(String name){
		assert isValidName(name);
		this.name = name;
		updateModificationTime(); // probleem want dit gaat niet hetzelfde zijn als creation date in het begin // opgelost
	}
	
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
	
	private boolean isValidName(String name){
		if(name.length()==0) return false;
		Pattern valid = Pattern.compile("[a-zA-Z]");
		return valid.matcher(name).matches();
	}
	
	// (StartA <= EndB) and (EndA >= StartB)
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
	
	
	public void setWritability(boolean writable){
		this.writable = writable;
	}
	
	/**
	 * geeft de schrijfbaarheid terug
	 */
	@Basic
	public boolean getWritability(){
		return this.writable;
	}
	
}
