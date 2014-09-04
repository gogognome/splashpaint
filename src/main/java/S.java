import java.io.*;
import java.util.*;
 
public class S{public static void main(String[]a) throws Throwable{System.out.println(d(c(b(a(System.in)))));}
static List<Integer>c(List<List<Integer>>s){Collections.sort(s,(t,u)->t.get(0)-u.get(0));Collections.sort(s,(t,u)->t.size()-u.size());return s.get(0);}
static List<List<Integer>>b(List<List<Integer>>s){for(int i=0;i<144;i++){int y=i/12;int x=i%12;if(f(s,x,y)!=null){for(int dx=x==0?0:-1;dx<=(x==11?0:1);dx++){for(int dy=y==0?0:-1;dy<=(y==11?0:1);dy++){if(dx!=0||dy!=0){if(f(s,x+dx,y+dy)!=null){u(s,x,y,x+dx,y+dy);}}}}}}return s;}
static void u(List<List<Integer>>s,int x,int y,int p,int q){List<Integer>t=f(s,x,y);List<Integer>u=f(s,p,q);if(t!=u){t.addAll(u);Collections.sort(t);s.remove(u);}}
static List<Integer> f(List<List<Integer>> splashes, int x, int y) {
		try {
			return splashes.stream().filter(splash -> splash.contains(coordinatesToInt(x, y))).findFirst().get();
		} catch (Throwable e) {
			return null;
		}
	}

	static Integer coordinatesToInt(int x, int y) {
		return y * 100 + x;
	}

	static List<List<Integer>> a(InputStream inputStream) throws IOException {
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

	static String d(List<Integer> splash) {
		return String.format("%d => %s", splash.size(), splash.stream().map(n -> "(" + n%100 + ", " + n/100 + ")").reduce((t,u) -> t + ", " + u).get());
	}

}
