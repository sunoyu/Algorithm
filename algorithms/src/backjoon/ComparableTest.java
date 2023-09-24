package backjoon;

public class ComparableTest {

	public static void main(String[] args) {
		
	}
}
class MyInteger implements Comparable<MyInteger>{

	@Override
	public int compareTo(MyInteger o) {
		return 0;
	}
	
}