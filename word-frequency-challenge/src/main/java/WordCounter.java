import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class WordCounter {

	private Map<String, Map<String, Integer>> fileCount;

	private Map<String, Integer> globalCount;

	public WordCounter() {
		fileCount = new HashMap<String, Map<String, Integer>>();
		globalCount = new HashMap<String, Integer>();
	}

	public void countGlobally(String word) {
		countWords(word, globalCount);
	}

	public void countInFile(String file, String word) {
		Map<String, Integer> fileCounter = fileCount.get(file);
		if (null != fileCounter) {
			countWords(word, fileCounter);
		} else {
			Map<String, Integer> counter = new HashMap<String, Integer>();
			counter.put(word, 1);
			fileCount.put(file, counter);
		}
	}

	private void countWords(String word, Map<String, Integer> fileCounter) {
		Integer count = (null != fileCounter.get(word)) ? fileCounter.get(word) + 1 : 1;
		fileCounter.put(word, count);
	}

	public void displayGlobalCount(String word) {
		if (null != word && !"".equals(word)) {
			Integer count = globalCount.get(word.toLowerCase());
			System.out.println("Total occurrances of word '" + word + "' is : " + (null != count ? count : 0));
		} else {
			System.err.println("Invalid word entered!");
		}

	}

	public void displayFileCount(String word) {
		if (null != word && !"".equals(word)) {
			boolean found = false;
			Set<Entry<String, Map<String, Integer>>> entries = fileCount.entrySet();
			for (Entry<String, Map<String, Integer>> entry : entries) {
				Map<String, Integer> indFileCount = entry.getValue();
				Integer count = indFileCount.get(word.toLowerCase());
				if (null != count) {
					found = true;
					System.out.println(
							"Occurrances for word : '" + word.toLowerCase() + "' in file : '" + entry.getKey() + "' is : " + count);
				}
			}
			if (!found) {
				System.out.println("The given word '" + word.toLowerCase() + "' is not found in any file!!");
			}
		} else {
			System.err.println("Invalid word entered!");
		}
	}
}
