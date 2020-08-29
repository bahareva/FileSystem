package commands;

import fileSystem.*;
import parser.*;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Cat implements Command {

	@Override
	public String execute(String[] args, Terminal terminal) throws IOException {

		if (args.length != 2) {
			throw new IllegalArgumentException();
		}

		Parser p = new Parser();
		String path = args[1];
		List<String> list = p.splitBySlash(path);

		if (terminal.doesNodeExist(list)) {
			Node node = terminal.getNodeByPath(list);

			if (File.class.isAssignableFrom(node.getClass())) {
				return node.getContent();
			}
		}
		throw new FileNotFoundException("The file does not exist!");
	}
}
