package fileSystemTest;

import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import org.junit.Test;
import commands.*;
import fileSystem.*;

public class DirectoryTest {

	@Test
	public void testConstructor() {

		String name = "dir1";
		Directory c = new Directory(name, new Directory());
		assertEquals(name, c.getName());
		c.setName("dir");
		assertEquals("dir", c.getName());
	}

	@Test
	public void testAddFileToDirectory() throws FileAlreadyExistsException {
		String fileName = "f1";
		File f = new File("f");
		String name = "dir1";
		Directory c = new Directory(name, new Directory());
		c.add(fileName, f);
		String expected = "f1";
		assertEquals(expected, c.getContent());
	}

	@Test
	public void testRemoveNode() throws IOException {
		Directory dir = new Directory();
		Directory m = new Directory("dir1", dir);
		m.add("f1", new File("f1"));
		m.add("dir2", new Directory("dir2", m));
		// String expected = "dir2" + "\n" + "f1";
		// assertEquals(expected, m.getContent());
		m.removeNodeInDirectory("dir2");
		String expected = "f1";
		assertEquals(expected, m.getContent());
	}

	@Test
	public void testContainsFileInDirectoryReturnTrue() throws IOException {
		Directory dir = new Directory();
		Directory m = new Directory("dir1", dir);
		m.add("f1", new File("f1"));
		boolean result = m.contains("f1");
		assertTrue(result);
	}

	@Test
	public void testGetAllFilesNamesInDirectory() throws IOException {
		Directory dir1 = new Directory();
		Directory dir2 = new Directory("dir2", dir1);
		dir2.add("f1", new File("f1"));
		dir2.add("dir3", new Directory("dir3", dir2));
		dir2.add("f2", new File("f2"));
		dir2.add("f3", new File("f3"));
		dir2.add("dir4", new Directory("dir4", dir2));
		String expected = "dir3\ndir4\nf1\nf2\nf3";
		String actual = dir2.getAllNodesNames();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetAllSortedFilesNames() throws IOException {
		Directory dir1 = new Directory();
		Directory dir2 = new Directory("dir2", dir1);
		File f1 = new File("f1");
		f1.write(false,2, "Hi");
		f1.write(false,4, "Hello");
		dir2.add("f1", f1);
		dir2.add("dir3", new Directory("dir3", dir2));
		dir2.add("f2", new File("f2"));
		dir2.add("dir4", new Directory("dir4", dir2));
		dir2.add("f3", new File("f3"));
		String expected = "f1\ndir4\ndir3\nf2\nf3";
		String actual = dir2.getSortedFilesNames();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetNode() throws IOException {
		Directory dir1 = new Directory();
		Directory dir2 = new Directory("dir2", dir1);
		File f1 = new File("f1");
		f1.write(false,4, "Hello");
		dir2.add("f1", f1);
		Node expected = f1;
		Node actual = dir2.getNode("f1");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetDirectory() throws IOException {
		Directory dir1 = new Directory();
		Directory dir2 = new Directory("dir2", dir1);
		File f1 = new File("f1");
		f1.write(false,2, "Hi");
		dir2.add("f1", f1);
		Directory dir3 = new Directory("dir3", dir2);
		dir2.add("dir3", dir3);
		Directory expected = dir3;
		Directory actual = dir2.getDirectory("dir3");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetParentDirectory() throws IOException {
		Directory dir1 = new Directory();
		Directory dir2 = new Directory("dir2", dir1);
		Directory expected = dir1;
		Directory actual = dir2.getParent();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getSize() throws IOException {
		Directory dir1 = new Directory();
		Directory dir2 = new Directory("dir2", dir1);
		File f1 = new File("f1");
		f1.write(false,2, "Hi");
		File f2 = new File("f2");
		f1.write(false,4, "Hello");
		dir2.add("f1", f1);
		dir2.add("dir3", new Directory("dir3", dir2));
		dir2.add("f2", f2);
		dir2.add("dir4", new Directory("dir4", dir2));
		dir2.add("f3", new File("f3"));
		int expected = 11;
		int actual = dir2.getSize();
		assertEquals(expected, actual);
	}
}
