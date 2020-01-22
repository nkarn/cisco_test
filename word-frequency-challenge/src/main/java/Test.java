import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {

		FileParser parser = new FileParser();

		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Welcome to Word Frequency Counter");

			System.out.print("Enter file location : ");

			String filePath = in.nextLine();

			if (null != filePath && !"".equals(filePath)) {
				System.out.println("File(s) reading started!");
				List<String> fileNames = parser.getFileNames(filePath);

				Path path = Paths.get(filePath);
				
				if(null != path && null != path.getParent()) {
					String folder = path.getParent().toString();

					fileNames.forEach(fileName -> {
						parser.readFile(folder + fileName);
					});
					System.out.println("File(s) reading completed!");

					String word = null;
					System.out.println("Enter word to find its occurrences! (Type EXIT to exit)");

					while (!"EXIT".equalsIgnoreCase(word = in.nextLine())) {
						parser.displayCount(word);
					}
				}				
			} else {
				throw new RuntimeException("Invalid file path entered!");
			}
		}
	}
}
