package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class b1181 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
		
		Arrays.sort(arr, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if(o1.length() == o2.length()) {      // 길이가 같으면
					return o1.compareTo(o2);          // 사전 기준
				}
				else {
					return o1.length() - o2.length(); // 오름차순
				}
			}
		});
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(arr[0]).append("\n");   // 아래 조건문에 의해 0으로 시작하면 OutOfBoundsExeption이 발생하므로

		for(int i = 1; i < N; i++) {
			if(!arr[i].equals(arr[i-1]))
				sb.append(arr[i]).append("\n");
		}
		System.out.println(sb);

		
	}

}
