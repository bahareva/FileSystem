package parser;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import parser.*;

public class ParserTest {

	@Test
	public void testSplitArgumentsBySpace() {
		Parser p = new Parser();
		String input = "create_file f1";
		String[] expecteds = { "create_file", "f1" };
		String[] actuals = p.splitBySpace(input);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testSplitArgumentsBySpaceEmptyInput() {
		Parser p = new Parser();
		String input = null;
		String[] expecteds = null;
		String[] actuals = p.splitBySpace(input);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testSplitArgumentsBySlash() {
		Parser p = new Parser();
		String input = "/home/dir/dir1/f1";
		String[] expecteds = { "", "home", "dir", "dir1", "f1" };
		String[] actuals = (String[]) p.splitBySlash(input).toArray();
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testSplitArgumentsBySlashEmptyInput() {
		Parser p = new Parser();
		String input = null;
		String[] expecteds = null;
		String[] actuals = (String[]) p.splitBySlash(input).toArray();
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testParsePath() {
		Parser p = new Parser();
		String[] input = { "", "home", "dir", "dir1", "f1" };
		List<String> expecteds = Arrays.asList(input);
		List<String> actuals = p.toList(input);
		assertEquals(expecteds, actuals);
	}
}