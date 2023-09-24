package backjoon;

import java.util.Arrays;
import java.util.Comparator;

public class comparators {

	public static void main(String[] args) {
		MyInteger[] arr = new MyInteger[10];
		
		for (int i = 0; i < 10; i++) {
			arr[i] = new MyInteger((int)(Math.random() * 100));
		}
		
		System.out.println("Before Sort");

		for (int i = 0; i < 10; i++) {
			System.out.print(arr[i].value +" ");
		}
		
		Arrays.sort(arr,comp);
		
		System.out.println("After DESC Sort");

		for (int i = 0; i < 10; i++) {
			System.out.print(arr[i].value + " ");
		}
		
	}

	
	
	
	static Comparator<MyInteger> comp = new Comparator<MyInteger>() {
		@Override
		public int compare(MyInteger o1, MyInteger o2) {
			return o2.value - o1.value ;
		}
	};

}


// MyInteger 클래스 생성
class MyInteger{
	int value;

	public MyInteger(int value) {
		this.value = value;
	}
}
