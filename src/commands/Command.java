package commands;

import fileSystem.*;
import java.io.IOException;

public interface Command {

	public abstract String execute(String[] args, Terminal terminal) throws IOException;
	
}
