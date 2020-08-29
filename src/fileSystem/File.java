package fileSystem;

import commands.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class File implements Node {

	private String name;
	boolean isDeleted;
	private Map<Integer, String> fileContent;

	public File(String name) {
		this.name = name;
		this.fileContent = new HashMap<Integer, String>();
		this.isDeleted = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void write(boolean toOverwrite, int row, String content) {
		if (fileContent.size() >= row) {
			if (!toOverwrite) {
				append(row, content);
			} else {
				fileContent.put(row, content);
			}
		} else {
			for (int i = fileContent.size(); i < row; i++) {
				fileContent.put(i, "");
			}
			fileContent.put(row, content);
		}
	}

	public void append(int row, String content) {
		String oldContent = fileContent.get(row);
		String newContent;
		if (oldContent != null) {
			newContent = oldContent + content;
		} else {
			newContent = content;
		}
		fileContent.put(row, newContent);
	}

	public String readRow(int row) {
		String line = fileContent.get(row);
		return line;
	}

	public String getContent() {
		StringBuilder str = new StringBuilder();

		int size = numberOfNonEmptyLines();
		for (int i = 0; i <= size; i++) {
			if (fileContent.containsKey(i)) {
				str.append(getContentInLine(i));
				str.append("\n");
			}
		}
		return str.toString();
	}

	public void removeLine(int row) {
		fileContent.remove(row);
	}

	public void removeLines(int startRow, int endRow) {
		for (int i = startRow; i <= endRow; i++) {
			fileContent.remove(i);
		}
	}

	private int numberOfNonEmptyLines() {
		Set<Integer> keySet = fileContent.keySet();
		Integer result = keySet.stream().max(Integer::compare).orElse(0);
		return result;
	}

	private int getNumberOfCharacters() {
		Collection<String> values = fileContent.values();
		return values.stream().map(x -> x.length()).reduce(0, Integer::sum);
	}
	
	public int getSize() {
		return numberOfNonEmptyLines() + getNumberOfCharacters();
	}

	public String getContentInLine(Integer row) {
		if (fileContent.containsKey(row)) {
			return fileContent.get(row);
		} else {
			return "";
		}
	}

	public String countWords() {
		String content = getContent();
		String[] words = content.trim().split("\\s+");

		if (content == null || content.isEmpty()) {
			return Integer.toString(0);
		} else {
			return Integer.toString(words.length);
		}
	}

	public String countLines() {
		return Integer.toString(fileContent.size());
	}

}

