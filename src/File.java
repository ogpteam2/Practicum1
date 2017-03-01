//@author jemoeder
import java.util.regex.*;
import be.kuleuven.cs.som.annotate.*;
import java.util.Date;

public class File {
	private String name;
	private boolean writable;
	private int size;
	private final Date creationDate;
	private Date modificationDate;
	
	public File(String name, int size, boolean writable){
		assert isValidName(name);
		setName(name);
		assert isValidSize(size);
		creationDate = new Date();
	}
	
	public File(String name){
		this(name, 0, true);
	}
	
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
		updateModificationTime();
	}
	
	public void setName(String name){
		assert isValidName(name);
		this.name = name;
		updateModificationTime();
	}
	
	private void updateModificationTime(){
		modificationDate = new Date();
	}
	
	public Date getCreationDate(){
		return creationDate;
	}
	
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
		if (getModificationDate() == getCreationDate() || other.getModificationDate() == other.getCreationDate()){
			return false;
		}
		else if (getCreationDate().getTime() <= other.getModificationDate().getTime() && getModificationDate().getTime() <= other.getCreationDate().getTime() ){
			return true;
		}
		return false;
	}
}
