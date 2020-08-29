package commands;

import fileSystem.*;
import parser.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Remove implements Command {

	@Override
	public String execute(String[] args, Terminal terminal) throws IOException {

		Parser p = new Parser();
		String path = args[1];
		List<String> list = p.splitBySlash(path);

		if (args.length == 2) {
			if (terminal.doesNodeExist(list)) {
				Node node = terminal.getNodeByPath(list);

				if (File.class.isAssignableFrom(node.getClass())) {
					terminal.removeFile(list);
				}
			} else {
				throw new FileNotFoundException();
			}
			
		} else if (args.length == 4) {
			if (terminal.doesNodeExist(list)) {
				Node node = terminal.getNodeByPath(list);

				if (File.class.isAssignableFrom(node.getClass())) {
					((File) node).removeLines(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
				}
			} else {
				throw new FileNotFoundException();
			}
		}
		
		return "";
	}
}