package fileSystem;

import commands.*;
import parser.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Terminal {

	private Scanner scanner;
	private Directory currentDirectory;
	private FileSystem fileSystem;

	public Map<String, Command> allCommands;

	Terminal() {
		scanner = new Scanner(System.in);
		Directory home = new Directory("home", null);
		currentDirectory = home;
		fileSystem = new FileSystem(home);
		allCommands = new HashMap<String, Command>();
		allCommands.put("cd", new ChangeDirectory());
		allCommands.put("mkdir", new MakeDir());
		allCommands.put("create_file", new CreateFile());
		allCommands.put("cat", new Cat());
		allCommands.put("write", new Write());
		allCommands.put("ls", new ListFiles());
		allCommands.put("wc", new WordCount());
		allCommands.put("rm", new Remove());
		allCommands.put("|", new Pipe());
	};

	public String[] parseArgBySpace(String input) {

		Parser p = new Parser();
		String[] str = p.splitBySpace(input);
		return str;
	}

	void readAndExecuteCommands() {

		Parser p = new Parser();
		String input = scanner.nextLine();

		String[] parsed = (p.isPipe(input)) ? p.splitByPipe(input) : parseArgBySpace(input);
		String commandName = p.getCommandName(input);

		while (commandName != "exit") {

			if (allCommands.containsKey(commandName)) {
				String result = null;
				try {
					result = allCommands.get(commandName).execute(parsed, this);
				} catch (IOException e) {
					System.out.println(e);
				}
				System.out.println(result);
			} else {
				System.out.println("Invalid command in read and execute");
			}
			input = scanner.nextLine();
			parsed = (p.isPipe(input)) ? p.splitByPipe(input) : parseArgBySpace(input);
			commandName = p.getCommandName(input);

		}
	}

	public Node getNodeByPath(List<String> path) {

		return fileSystem.getNodeByPath(path, currentDirectory);
	}

	public boolean isFileRemoved(String name) {
		return fileSystem.isFileRemoved(name);
	}

	public void removeFile(List<String> path) throws IOException {
		fileSystem.removeNode(path);
	}

	public String getAllRemovedFilesNames() {
		return fileSystem.getAllRemovedFilesNames();
	}

	public boolean doesNodeExist(List<String> path) {

		if (getNodeByPath(path) == null) {
			return false;
		} else {
			return true;
		}
	}

	public Directory getCurrentDirectory() {
		return currentDirectory;
	}

	public void setCurrentDirectory(Directory currentDirectory) {
		this.currentDirectory = currentDirectory;
	}

	public Scanner getScanner() {
		return scanner;
	}
}