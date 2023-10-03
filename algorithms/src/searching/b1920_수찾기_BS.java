package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b1920_수찾기_BS {
	static int n;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();    //  입출력 sb로 수정하면 시간 반토막!
		for(int i = 0; i < m; i++) {
			int key = Integer.parseInt(st.nextToken());
			sb.append(BS(arr, key)).append('\n');
		}
		System.out.println(sb);
		
	}
	static int BS(int[] arr, int key) {
		int lo = 0;				// 최초 시작점
		int hi = arr.length -1;  // 끝점
		while(lo <= hi) {
			int mid = (lo + hi) /2;
			if(key > arr[mid] ) {    // 배열 기준 오른쪽에 위치
				lo = mid +1;
			}
			else if(key < arr[mid]) {  // 배열 중앙 기준 왼쪽에 위치.
				hi = mid -1;
			}
			else return 1;            // key == arr의 중앙값
		}

		return 0;
	}

}
