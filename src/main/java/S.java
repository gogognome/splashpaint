import java.io.*;
import java.util.*;
public class S{public static void main(String[]a)throws Throwable{S s=new S();System.out.println(s.d(s.c(s.b(s.a(System.in)))));}
List<Long>c(List<List<Long>>s){Collections.sort(s,(t,u)->(int)(t.get(0)-u.get(0)));Collections.sort(s,(t,u)->t.size()-u.size());return s.get(0);}
List<List<Long>>b(List<List<Long>>s){for(int i=0;i<144;i++){int y=i/12;int x=i%12;if(f(s,x,y)!=null){for(int dx=x==0?0:-1;dx<=(x==11?0:1);dx++){for(int dy=y==0?0:-1;dy<=(y==11?0:1);dy++){if(dx!=0||dy!=0){if(f(s,x+dx,y+dy)!=null){u(s,x,y,x+dx,y+dy);}}}}}}return s;}
void u(List<List<Long>>s,int x,int y,int p,int q){List<Long>t=f(s,x,y);List<Long>u=f(s,p,q);if(t!=u){t.addAll(u);Collections.sort(t);s.remove(u);}}
List<Long>f(List<List<Long>>s,int x,int y){try{return s.stream().filter(t->t.contains(c(x, y))).findFirst().get();}catch(Throwable e){return null;}}
long c(long x,long y){return y*12+x;}
List<List<Long>> a(InputStream s)throws Throwable{List<List<Long>>splashes=new ArrayList<>();for(int i=0;i<144;i++){long y=i/12;long x=i%12;int b=0;while(b<79){b=s.read();}if(b==88)splashes.add(new ArrayList<>(Arrays.asList(c(x, y))));}return splashes;}
String d(List<Long> s){return String.format("%d => %s",s.size(),s.stream().map(n->"("+n%12+", "+n/12+")").reduce((t,u)->t+", "+u).get());}}
