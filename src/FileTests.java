import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testklasse voor de File-class
 * 
 * @author Elias Storme en Robbe Louage
 * @version 1.0
 *
 */

public class FileTests {
	
	private static File normalFile, secondFile, unwritableFile;
	
	@BeforeClass public static void setUpBeforeClass() throws Exception {
		normalFile = new File("File", 100, true);
		unwritableFile = new File("File", 100, false);
	}
	
	@Test(expected = NotWritableException.class)
	public final void illegalEnlargementCase(){
		unwritableFile.enlarge(50);
	}
	
	@Test(expected = NotWritableException.class)
	public final void illegalShorteningCase(){
		unwritableFile.shorten(50);
	}
	
	@Test
	public final void AcceptableNameCase(){
		String expectedName = "FileName-_.txt";
		File newFile = new File(expectedName);
		assertEquals(expectedName,newFile.getName());
	}
	
	@Test
	public final void IllegalSpacesInNameCase(){
		String givenName = "File Name.txt";
		String expectedName = "File_Name.txt";
		File newFile = new File(givenName);
		assertEquals("Should have spaces replaced by underscores",expectedName,newFile.getName());
	}
	
	@Test
	public final void IllegalCharacterInNameCase(){
		String givenName = "File!@#$%Name.txt";
		String expectedName = "FileName.txt";
		File newFile = new File(givenName);
		assertEquals("Should have weird characters removed",expectedName,newFile.getName());
	}
	
	@Test
	public final void EmptyNameCase(){
		String givenName = "";
		String expectedName = "file.txt";
		File newFile = new File(givenName);
		assertEquals("Should have empty name replaced by 'file.txt'",expectedName,newFile.getName());
	}
	
	@Test
	public final void isValidSizeValidCase(){
		assertTrue(normalFile.isValidSize(0));
	}
	
	@Test
	public final void isValidSizeIllegalCase(){
		assertFalse(normalFile.isValidSize(-1));
	}
	
	@Test
	public final void testOverlappingUsePeriod(){
		secondFile = new File("Second_File");
		normalFile.enlarge(100);
		secondFile.enlarge(100);
		assertTrue(normalFile.hasOverlappingUsePeriod(secondFile));
	}
}