package commands;

import fileSystem.*;

public class ListFiles implements Command {

	@Override
	public String execute(String[] args, Terminal terminal) {

		if (args.length == 2 && args[1].equals("--sorted")) {
			return terminal.getCurrentDirectory().getSortedFilesNames();
		} else {
			return terminal.getCurrentDirectory().getAllNodesNames();
		}
	}

}
