package foo;

public class Counter {
	private static int count;
	
	public static synchronized int getCount(){
		return count++;
	}
}
