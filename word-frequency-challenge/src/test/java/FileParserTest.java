import org.junit.Test;

public class FileParserTest {

	private FileParser parser = new FileParser();

	@Test
	public void testReadFile() {
		parser.readFile(this.getClass().getResource("file.txt").getPath());
		parser.readFile(this.getClass().getResource("file2.txt").getPath());
		parser.displayCount("a");
		parser.displayCount("the");
		parser.displayCount("him");
		parser.displayCount("blah");
		parser.displayCount("");
	}

}
