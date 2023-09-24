package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 좌표 정렬
public class b11650 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
//		Integer[] arr = new Integer[N];
		int[][] arr = new int[N][2];
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 배열 생성
		for(int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, (e1, e2)->{
			if(e1[1] == e2[1]) {
				return e1[0] - e2[0];
			}else {
				return e1[1] - e2[1];
			}
			
			});
		
		for(int i = 0; i < N; i++) {
			sb.append(arr[i][0] + " " + arr[i][1] + "\n");
			
			
//			st=new StringTokenizer(br.readLine());
//			arr[i][0] = Integer.parseInt(st.nextToken());
//			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		System.out.println(sb);
	}

}
