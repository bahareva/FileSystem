package commands;

import fileSystem.*;
import parser.*;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Write implements Command {

	@Override
	public String execute(String[] args, Terminal terminal) throws IOException {

		/*if (args.length != 4 && args.length != 5) {
			throw new IllegalArgumentException();
		}*/
		if (Character.isDigit(Integer.parseInt(args[2]))) {
			throw new IllegalArgumentException();
		}
		String lineNumber = args[2];
		String content = args[3];

		Parser p = new Parser();
		String path = args[1];
		List<String> list = p.splitBySlash(path);

		if (terminal.doesNodeExist(list)) {

			Node node = terminal.getNodeByPath(list);
			if (args.length == 5 && args[4].equals("-overwrite") && File.class.isAssignableFrom(node.getClass())) {
				((File) node).write(true, Integer.parseInt(lineNumber), content);
			} else if (args.length == 4 && File.class.isAssignableFrom(node.getClass())) {
				((File) node).write(false, Integer.parseInt(lineNumber), content);
			}
		}
		else {
			throw new FileNotFoundException("The file does not exist.");
		}
		return "";
	}
}