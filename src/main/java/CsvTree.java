import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public class CsvTree {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String lineOrig = null;
		List<String> lines = new LinkedList<String>();
		while ((lineOrig = reader.readLine()) != null) {
			lines.add(lineOrig);
		}

		List<Entry<String, String>> pairs = new LinkedList<Entry<String, String>>();
		for (String line : lines) {
			String[] tokens = line.split(",");
			if (tokens.length != 2) {
				System.err.println("CsvTree.main() tokens = " + tokens);
				continue;
			}
			pairs.add(new AbstractMap.SimpleEntry<String, String>(tokens[0], tokens[1]));
		}
		Multimap<String, String> multimap = LinkedListMultimap.create();
		for (Entry<String, String> pair : pairs) {
			multimap.put(pair.getKey(), pair.getValue());
		}

		Set<String> right = new HashSet<String>();
		for (Entry<String, String> pair : pairs) {
			right.add(pair.getValue());
		}
		Set<String> left = new HashSet<String>();
		for (Entry<String, String> pair : pairs) {
			left.add(pair.getKey());
		}
		Set<String> topLevels = Sets.difference(left, right);

		for (String topLevel : topLevels) {
			StringBuilder sb = printTree(topLevel, multimap, "");
			System.out.println(sb.toString());
		}
	}

	private static StringBuilder printTree(String topLevel, Multimap<String, String> multimap,
			String indentation) {
		StringBuilder sb = new StringBuilder();
		sb.append(indentation);
		sb.append(topLevel);
		sb.append('\n');
		for (String value : multimap.get(topLevel)) {
			sb.append(printTree(value, multimap, indentation + "  "));
		}

		return sb;
	}
}
