package commands;

import fileSystem.*;
import parser.*;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public class MakeDir implements Command {

	@Override
	public String execute(String[] args, Terminal terminal) throws IOException {

		if (args.length != 2) {
			throw new IllegalArgumentException();
		}

		Parser p = new Parser();
		String path = args[1];
		List<String> list = p.splitBySlash(path);

		if (terminal.doesNodeExist(list)) {
			throw new FileAlreadyExistsException(path);
		} else {
			String dirName = list.get(list.size() - 1);
			list.remove(list.size() - 1);
			Node node = terminal.getNodeByPath(list);

			if (Directory.class.isAssignableFrom(node.getClass())) {
				Directory newDirectory = new Directory(dirName, (Directory) node);
				Directory futureParentDir = (Directory) node;
				futureParentDir.add(dirName, newDirectory);
			} else {
				throw new IllegalArgumentException();
			}
			return "";
		}
	}
}
