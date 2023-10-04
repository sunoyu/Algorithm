package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b10816_숫자카드2_BS {
	static int n, m;
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
		
		m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			int find = Integer.parseInt(st.nextToken());
			sb.append(upperBound(find)-lowerBound(find)).append(' ');
		}
		System.out.println(sb);
		
	}
	
	// 중복 갯수를 구해야 하므로 sort된 배열에서 key를 searching한 뒤,
	// 가장 오른쪽 인덱스와 가장 왼쪽 인덱스를 찾고 두 값의 차를 갯수로 보면 된다.
	static int upperBound(int key) {
		int lo = 0;
		int hi = arr.length;     // int[1] 의 경우 lo와 hi값이 동일하여 BS가 불가능하다. 따라서 length-1이 아닌 length로 설정!
		
		while(lo < hi) {
			int mid = (hi + lo) / 2;

			if(key >= arr[mid]) {
				lo = mid + 1;
			}
			else hi = mid;
			
			/* 아래 함수와 통일성을 주고 싶다면 아래 코드로 고쳐도 되겠지.
			if(key < arr[mid]) {
				hi = mid;
			}
			else lo = mid + 1;
			*/
		
		}
		return lo;
		
	}
	static int lowerBound(int key) {
		int lo = 0;
		int hi = arr.length;
		
		while(lo < hi) {
			int mid = (hi + lo) / 2;

			if(key <= arr[mid]) {
				hi = mid;
			}
			else lo = mid +1;
		}
		return lo;
		
	}

}
