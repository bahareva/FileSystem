package fileSystem;

import commands.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FileSystem {

	Directory home;
	private HashMap<String, Node> removedFiles;

	FileSystem(Directory home) {
		this.home = home;
		removedFiles = new HashMap<String, Node>();
	}

	void addToRemovedFiles(String name, Node node) {
		removedFiles.put(name, node);
	}

	boolean isFileRemoved(String name) {
		return removedFiles.containsKey(name);
	}

	private List<Node> getAllRemovedFiles() {
		return removedFiles.values().stream().collect(Collectors.toList());
	}

	public String getAllRemovedFilesNames() {
		String fileNames = getAllRemovedFiles().stream().map(x -> x.getName()).sorted()
				.collect(Collectors.joining("\n"));
		return fileNames;
	}

	public Directory getHome() {
		return home;
	}

	public Node getNodeByPath(List<String> path, Directory currentDir) {

		if (currentDir == null) {
			return null;
		}
		String head = null;
		Directory newDir = null;
		if (path.size() == 0 || path.get(0) == ".") {
			return currentDir;
		} else {
			head = path.get(0);
			if (head.equals("..")) {
				newDir = currentDir.getParent();
			} else if (head.equals("")) {
				newDir = home;
			} else {
				try {
					Node nextNode = currentDir.getNode(head);

					if (Directory.class.isAssignableFrom(nextNode.getClass())) {
						newDir = (Directory) nextNode;
					} else {
						if (path.size() == 1) {
							return nextNode;
						} else {
							return null;
						}
					}
				} catch (FileNotFoundException e) {
					return null;
				}
			}
		}
		List<String> newPath = path;
		newPath.remove(0);
		return getNodeByPath(newPath, newDir);
	}

	void removeNode(List<String> path) throws IOException {

		String nodeToBeRemovedName = path.get(path.size() - 1);

		Directory temp = null;
		String name = null;

		if (path.size() == 1) {
			temp = home;
		} else if (path.size() > 1) {

			for (int i = 0; i < path.size() - 1; i++) {

				Node node = temp.getNode(path.get(i)); 
				name = node.getName();

				if (temp.getDirectory(name) == null) {
					throw new FileNotFoundException();

				} else {
					temp = (Directory) temp.getNode(path.get(i));
				}
			}
		}

		if (File.class.isAssignableFrom(temp.getNode(nodeToBeRemovedName).getClass())) {
			addToRemovedFiles(nodeToBeRemovedName, temp.getNode(nodeToBeRemovedName));
		}

		temp.removeNodeInDirectory(nodeToBeRemovedName);

	}

	void addNode(String name, Node node) throws IOException {
		home.add(name, node);
	}
}
