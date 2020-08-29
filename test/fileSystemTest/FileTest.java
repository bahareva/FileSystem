package fileSystemTest;

import static org.junit.Assert.*;
import org.junit.Test;
import fileSystem.*;
public class FileTest {

	@Test
	public void constructorTest() {

		String name = "f1";
		File c = new File(name);
		assertEquals(name, c.getName());
	}

	@Test
	public void writeTest() {
		String name = "f1";
		int row = 2;
		String content = "Hi";
		File c = new File(name);
		c.write(false,row, content);
		assertEquals("Hi", c.getContent());
	}

	@Test
	public void appendTest() {
		String name = "f1";
		int row = 2;
		String content = "Hi";
		File c = new File(name);
		c.write(false,row, content);
		int row1 = 2;
		String content1 = "Tedi";
		c.append(row1, content1);
		String expected = c.getContent();
		String actual = "Hi Tedi";
		assertEquals(expected, actual);
	}

	@Test
	public void readRowTest() {
		String name = "ff";
		int row = 2;
		String content = "What";
		File c = new File(name);
		c.write(false,row, content);
		String actual = c.readRow(row);
		String expected = "What";
		assertEquals(expected, actual);
	}

	@Test
	public void removeLineTest() {
		String name = "ff";
		int row = 2;
		String content = "Hi";
		File c = new File(name);
		c.write(false,row, content);
		int row1 = 3;
		String content1 = "whats up";
		c.write(false,row1, content1);
		c.removeLine(row1);
		String expected = c.getContent();
		String actual = "Hi";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRemoveLines() {
		String name = "ff";
		int row = 2;
		String content = "Hi";
		File c = new File(name);
		c.write(false,row, content);
		int row1 = 3;
		String content1 = "whats up";
		c.write(false,row1, content1);
		c.removeLines(row,row1);
		String expected = "";
		String actual = c.getContent();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getSizeTest() {
		String name = "ff";
		int row = 2;
		String content = "name";
		File c = new File(name);
		c.write(false,row, content);
		int expected = c.getSize();
		int actual = 6;
		assertEquals(expected, actual);
	}

}