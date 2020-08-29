package commands;

import parser.*;
import fileSystem.*;
import java.io.IOException;
import java.util.List;

public class ChangeDirectory implements Command {

	
	@Override
	public String execute(String[] args, Terminal terminal) throws IOException {

		Parser p = new Parser();
		List<String> list = p.toList(args);
		list.remove(0);
		terminal.setCurrentDirectory((Directory) terminal.getNodeByPath(list));
		return "";
		
	}
}
