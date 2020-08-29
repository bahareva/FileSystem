package parser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {

	public String[] splitBySpace(String input) {

		if (input != null) {
			String[] str = input.split("\\s+");
			return str;
		} else
			return null;
	}

	public List<String> splitBySlash(String input) {

		if (input != null) {
			String[] str = input.split("/");
			return toList(str);
		} else
			return null;
	}

	public List<String> toList(String[] path) {

		List<String> list = new LinkedList<>();
		list = Arrays.stream(path).collect(Collectors.toList());
		return list;

		/*
		 * List<String> stringList = new ArrayList<String>(path.length); for (String s :
		 * path) { stringList.add(s); } return stringList;
		 */
	}

	public String getCommandName(String input) {
		if (isPipe(input)) {
			return "|";
		} else {
			String[] splitted = splitBySpace(input);
			return splitted[0];
		}
	}

	public boolean isPipe(String input) {
		if (input.contains("|")) {
			return true;
		} else {
			return false;
		}
	}

	public String[] splitByPipe(String input) {

		if (input != null) {
			String[] str = input.split(Pattern.quote("|"));
			for (int i = 0; i < str.length; i++) {
				str[i] = str[i].trim();
			}
			return str;
		}
		return new String[0];
	}

}