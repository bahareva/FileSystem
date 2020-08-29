package commands;

import fileSystem.*;
import parser.*;
import java.io.IOException;
import java.util.List;

public class WordCount implements Command {

	@Override
	public String execute(String[] args, Terminal terminal) throws IOException {

		Parser p = new Parser();
		String path;
		List<String> list;

		if (!args[1].equals("-l")) {
			path = args[1];
			list = p.splitBySlash(path);
			if (terminal.doesNodeExist(list)) {
				return getCountedWords(list, terminal);
			} else {
				return Integer.toString(args.length - 1);

			}
		} else {
			path = args[2];
			list = p.splitBySlash(path);
			if (terminal.doesNodeExist(list)) {
				return getCountedLines(list, terminal);
			} else {
				int numberOfNewLines = countNewLines(args);
				return Integer.toString(numberOfNewLines);
			}
		}
	}

	private String getCountedWords(List<String> list, Terminal terminal) {
		Node node = terminal.getNodeByPath(list);
		if (File.class.isAssignableFrom(node.getClass())) {
			return (((File) node).countWords());
		} else
			return "";
	}

	private String getCountedLines(List<String> list, Terminal terminal) {
		Node node = terminal.getNodeByPath(list);
		if (File.class.isAssignableFrom(node.getClass())) {
			return (((File) node).countLines());
		} else
			return "";
	}

	private int countNewLines(String[] args) {
		int count = 0;
		for (String line : args) {
			if (line.equals("\\n")) {
				count++;
			}
		}
		return count;
	}
}
