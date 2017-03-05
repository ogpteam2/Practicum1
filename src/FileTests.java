import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileTests {
	
	private static File normalFile, unwritableFile, enlargedFile, shortenedFile, emptyFile;
	
	@BeforeClass public static void setUpBeforeClass() throws Exception {
		normalFile = new File("File", 100, true);
		unwritableFile = new File("File", 100, false);
		enlargedFile = new File("File", 150, true);
		shortenedFile = new File("File", 50, true);
		emptyFile = new File("File", 0, true);
	}
	
	@Test(expected = NotWritableException.class)
	public final void illegalEnlargementCase(){
		unwritableFile.enlarge(50);
		fail("NotWritableException expected!");
	}
	
	@Test(expected = NotWritableException.class)
	public final void illegalShorteningCase(){
		unwritableFile.shorten(50);
		fail("NotWritableException expected!");
	}
	
}