// 소트 인사이드
package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b1427 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringBuilder sb = new StringBuilder();
		// counting 정렬 이용을 할 것 이므로, count 배열 생성
		int[] count = new int[11]; 
		br.close();
		for(int i = 0; i < input.length(); i ++) {
			count[Integer.parseInt(String.valueOf(input.charAt(i)))] ++;
//			System.out.println(Integer.parseInt(String.valueOf(input.charAt(i))));
//			System.out.println("count["+ i + "i]  =" + count[i]);
		}
		for(int i = 10; i >= 0; i--) {
			while(count[i] > 0) {
				sb.append(String.valueOf(i));
				count[i] --;
			}
		}
		System.out.print(sb);
	}

}
