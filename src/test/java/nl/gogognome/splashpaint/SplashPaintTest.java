package nl.gogognome.splashpaint;

import static java.util.Arrays.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class SplashPaintTest {

	private static final boolean[][] SAMPLE_SPLASHES = new boolean[][] {
			{ false,true,false,false,false,false,false,false,false,false,false,false },
			{ true,true,false,false,false,true,true,true,false,false,false,false },
			{ true,true,true,false,true,false,true,true,false,false,false,false },
			{ false,false,false,false,false,false,false,false,false,false,false,true },
			{ false,false,false,true,true,false,false,false,false,false,true,false },
			{ false,false,false,false,false,false,false,false,false,false,false,false },
			{ false,false,false,false,false,false,false,false,false,false,false,false },
			{ false,false,true,true,true,true,true,true,false,false,false,false },
			{ false,false,false,false,true,true,false,false,false,false,false,false },
			{ true,false,false,false,false,true,false,false,false,true,true,true },
			{ true,false,false,false,false,false,true,true,false,true,true,true },
			{ true,true,true,true,false,false,false,true,false,false,false,false  }
	};

	@Test
	public void testParser() throws IOException {
		File testFile = new File("src/main/resources/gistfile1.txt");
		try (FileInputStream inputStream = new FileInputStream(testFile)) {
			boolean[][] paint = SplashPaint.parseInput(inputStream);

			assertArrayEquals(SAMPLE_SPLASHES, paint);
		}
	}

	@Test
	public void testSplashes() {
		List<List<Integer>> splashes = SplashPaint.findSplashes(SAMPLE_SPLASHES);
		assertEquals("[[1, 100, 101, 200, 201, 202], "
				+ "[105, 106, 107, 204, 206, 207], "
				+ "[311, 410], "
				+ "[403, 404], "
				+ "[702, 703, 704, 705, 706, 707, 804, 805, 905, 1006, 1007, 1107], "
				+ "[900, 1000, 1100, 1101, 1102, 1103], "
				+ "[909, 910, 911, 1009, 1010, 1011]]",
				splashes.toString());
	}

	@Test
	public void testFindFirstSplash() {
		List<List<Integer>> splashes = SplashPaint.findSplashes(SAMPLE_SPLASHES);
		assertEquals("[311, 410]", SplashPaint.findFirstSmallestSplash(splashes).toString());
	}

	@Test
	public void testFormatOutput() {
		assertEquals("2 => (11, 3), (10, 4)", SplashPaint.formatSplash(asList(311, 410)));
	}
}
