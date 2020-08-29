package commands;

import fileSystem.*;
import parser.*;
import java.io.IOException;

public class Pipe implements Command {

	@Override
	public String execute(String[] args, Terminal terminal) throws IOException {

		String result = "";

		for (int i = 0; i < args.length; i++) {
			Parser p = new Parser();

			String[] parsed = terminal.parseArgBySpace(args[i]);
			String commandName = p.getCommandName(args[i]);
			if (terminal.allCommands.containsKey(commandName)) {
				try {
					if(i == args.length - 1) {
						result += terminal.allCommands.get(commandName).execute(parsed, terminal);
					}
					else {
						terminal.allCommands.get(commandName).execute(parsed, terminal);
					}
				} catch (IOException e) {
					System.out.println(e);
				}
			
			} else {
				System.out.println("Invalid command! in read and execute pipe command");
			}
		}
		return result;
	}

}