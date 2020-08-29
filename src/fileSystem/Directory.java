package fileSystem;

import commands.*;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Directory implements Node {

	private HashMap<String, Node> content;
	private String name;
	Directory parentDir;

	public Directory(String name, Directory parentDir) {

		this.parentDir = parentDir;
		this.name = name;
		this.content = new HashMap<String, Node>();
	}

	public Directory() {
		this.parentDir = null;
		this.name = null;
		this.content = null;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void add(String name, Node node) throws FileAlreadyExistsException {
		if (!content.containsKey(name)) {
			content.put(name, node);
		} else {
			throw new FileAlreadyExistsException(node.getName() + " already exist!");
		}
	}

	public void removeNodeInDirectory(String name) throws FileNotFoundException {
		if (content.containsKey(name)) {
			content.remove(name);
		} else
			throw new FileNotFoundException(name + " this node doesn't exist!");
	}

	public boolean contains(String name) {
		if (content.containsKey(name)) {
			return true;
		}
		return false;
	}

	@Override
	public String getContent() {
		return content.keySet().stream().collect(Collectors.joining("\n"));
	}

	private List<Node> getAllNodes() {
		return content.values().stream().collect(Collectors.toList());
	}

	public String getAllNodesNames() {
		String fileNames = getAllNodes().stream().map(x -> x.getName()).sorted().collect(Collectors.joining("\n"));
		return fileNames;
	}

	private List<Node> getSortedNodes() {
		List<Node> list = new ArrayList<Node>();
		list = getAllNodes().stream().sorted((o2, o1) -> Integer.compare(o1.getSize(), o2.getSize()))
				.collect(Collectors.toList());
		return list;
	}

	public String getSortedFilesNames() {
		String sortedNodes = getSortedNodes().stream().map(x -> x.getName()).collect(Collectors.joining("\n"));
		return sortedNodes;
	}

	public Node getNode(String name) throws FileNotFoundException {
		if (content.containsKey(name)) {
			return content.get(name);
		} else
			throw new FileNotFoundException(name + " doesn't exist");                               
	}

	public Directory getDirectory(String name) throws FileNotFoundException {

		Node node = content.get(name);
		if (Directory.class.isAssignableFrom(node.getClass())) {
			return (Directory) node;
		} else {
			throw new FileNotFoundException(name + "Nonexistent directory");
		}
	}

	public Directory getParent() {
		return parentDir;
	}

	@Override
	public int getSize() {
		return content.values().stream().map(x -> x.getSize()).reduce(0, Integer::sum);
	}

}

