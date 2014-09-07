package nl.gogognome.splashpaint;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SplashPaint {

	public static void main(String[] args) throws IOException {
		System.out.println("WARNING: " + SplashPaint.class.getName());
		boolean[][] paint = parseInput(System.in);
		List<List<Integer>> splashes = findSplashes(paint);
		System.out.println(formatSplash(findFirstSmallestSplash(splashes)));
	}

	static List<Integer> findFirstSmallestSplash(List<List<Integer>> splashes) {
		Collections.sort(splashes, (splash1, splash2) -> splash1.get(0) - splash2.get(0));
		Collections.sort(splashes, (splash1, splash2) -> splash1.size() - splash2.size());
		return splashes.get(0);
	}

	static List<List<Integer>> findSplashes(boolean[][] paint) {
		List<List<Integer>> splashes = new ArrayList<>();
		for (int i=0; i<144; i++) {
			int y = i/12; int x = i%12;
			if (paint[y][x]) {
				splashes.add(new ArrayList<>(Arrays.asList(coordinatesToInt(x, y))));
			}
		}

		for (int i=0; i<144; i++) {
			int y = i/12; int x = i%12;
			if (paint[y][x]) {
				for (int dx=x==0?0:-1; dx<=(x==11?0:1);dx++) {
					for (int dy=y==0?0:-1; dy<=(y==11?0:1);dy++) {
						if (dx != 0 || dy != 0) {
							if (paint[y+dy][x+dx]) {
								unite(splashes, x, y, x+dx, y + dy);
							}
						}
					}
				}
			}
		}
		return splashes;
	}

	private static void unite(List<List<Integer>> splashes, int x, int y, int p, int q) {
		List<Integer> splash1 = findSplash(splashes, x, y);
		List<Integer> splash2 = findSplash(splashes, p, q);
		if (splash1 != splash2) {
			splash1.addAll(splash2);
			Collections.sort(splash1);
			splashes.remove(splash2);
		}
	}

	private static List<Integer> findSplash(List<List<Integer>> splashes, int x, int y) {
		return splashes.stream().filter(splash -> splash.contains(coordinatesToInt(x, y))).findFirst().get();
	}

	private static Integer coordinatesToInt(int x, int y) {
		return y * 100 + x;
	}

	static boolean[][] parseInput(InputStream inputStream) throws IOException {
		boolean[][] paint = new boolean[12][12];
		for (int i=0; i<144; i++) {
			int y = i/12;
			int x = i%12;
			int b = 0;
			while (b < 'O') {
				b = inputStream.read();
			}
			paint[y][x] = b == 'X';
		}
		return paint;
	}

	static String formatSplash(List<Integer> splash) {
		return String.format("%d => %s", splash.size(), splash.stream().map(n -> "(" + n%100 + ", " + n/100 + ")").reduce((t,u) -> t + ", " + u).get());
	}
}
