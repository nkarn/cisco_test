import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileParser {

	private WordCounter counter = new WordCounter();

	public void readFile(String fileName) {
		System.out.println("Reading : " + fileName);
		String line;
		try (FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			while ((line = bufferedReader.readLine()) != null) {
				String[] tokens = line.split(" ");
				for (int i = 0; i < tokens.length; i++) {
					counter.countGlobally(tokens[i].toLowerCase());
					counter.countInFile(fileName, tokens[i].toLowerCase());
				}
			}
		} catch (FileNotFoundException ex) {
			System.err.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.err.println("Error reading file '" + fileName + "'");
		}
	}

	public void displayCount(String word) {
		counter.displayGlobalCount(word);
		counter.displayFileCount(word);
	}

	public List<String> getFileNames(String filePath) {
		Path path = Paths.get(filePath);
		try {
			return Files.readAllLines(path);
		} catch (IOException e) {
			System.err.println("Error while reading file!");
			return Collections.emptyList();
		}
	}

}
