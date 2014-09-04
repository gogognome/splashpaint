import java.io.*;
import java.util.*;

public class S {
	public static void main(String[] a) throws IOException {
		List<List<Integer>> splashes = findSplashes(parseInput(System.in));
		System.out.println(formatSplash(findFirstSmallestSplash(splashes)));
	}

	static List<Integer> findFirstSmallestSplash(List<List<Integer>> splashes) {
		Collections.sort(splashes, (splash1, splash2) -> splash1.get(0) - splash2.get(0));
		Collections.sort(splashes, (splash1, splash2) -> splash1.size() - splash2.size());
		return splashes.get(0);
	}

	static List<List<Integer>> findSplashes(List<List<Integer>> splashes) {
		for (int i=0; i<144; i++) {
			int y = i/12; int x = i%12;
			if (findSplash(splashes, x, y) != null) {
				for (int dx=x==0?0:-1; dx<=(x==11?0:1);dx++) {
					for (int dy=y==0?0:-1; dy<=(y==11?0:1);dy++) {
						if (dx != 0 || dy != 0) {
							if (findSplash(splashes, x+dx, y+dy) != null) {
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
		try {
			return splashes.stream().filter(splash -> splash.contains(coordinatesToInt(x, y))).findFirst().get();
		} catch (Throwable e) {
			return null;
		}
	}

	private static Integer coordinatesToInt(int x, int y) {
		return y * 100 + x;
	}

	static List<List<Integer>> parseInput(InputStream inputStream) throws IOException {
		List<List<Integer>> splashes = new ArrayList<>();
		for (int i=0; i<144; i++) {
			int y = i/12;
			int x = i%12;
			int b = 0;
			while (b < 'O') {
				b = inputStream.read();
			}
			if (b == 'X') splashes.add(new ArrayList<>(Arrays.asList(coordinatesToInt(x, y))));
		}
		return splashes;
	}

	static String formatSplash(List<Integer> splash) {
		return String.format("%d => %s", splash.size(), splash.stream().map(n -> "(" + n%100 + ", " + n/100 + ")").reduce((t,u) -> t + ", " + u).get());
	}

}
