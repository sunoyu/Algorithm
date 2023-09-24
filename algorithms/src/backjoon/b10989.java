// Counting 정렬
//첫째 줄에 수의 개수 N(1 ≤ N ≤ 10,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 수가 주어진다. 이 수는 10,000보다 작거나 같은 자연수이다.

package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b10989 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] counting = new int[10001];
		for(int i = 0; i < N; i++) {
			counting[Integer.parseInt(br.readLine())] ++; 
		}
		
		for(int i = 1; i <= 10000; i++) {
			while(counting[i]>0) {		
				sb.append(i).append("\n");
				counting[i]--;
			}
		}
		System.out.println(sb);
	}

}
