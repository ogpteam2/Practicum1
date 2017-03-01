//@author jemoeder
import java.util.regex.*;

public class File {
	private String name;
	private boolean writable;
	private int size;
	
	public File(String name, int size, boolean writable){
		assert isValidSize(size);
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
		int newSize = amount + getSize();
	}
	
	public void shorten(int amount){
		
	}
	
	public void setName(String name){
		if (isValidName(name)) this.name = name;
	}
	
	private boolean isValidName(String name){
		if(name.length()==0) return false;
		Pattern valid = Pattern.compile("[a-zA-Z]");
		return valid.matcher(name).matches();
	}
}
