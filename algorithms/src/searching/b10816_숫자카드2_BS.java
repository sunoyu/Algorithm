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
		for(int i = 0; i < m; i++) {
			int find = Integer.parseInt(st.nextToken());
			System.out.println(upperBound(find)-lowerBound(find));
		}
		
	}
	
	// 중복 갯수를 구해야 하므로 sort된 배열에서 key를 searching한 뒤,
	// 가장 오른쪽 인덱스와 가장 왼쪽 인덱스를 찾고 두 값의 차를 갯수로 보면 된다.
	static int upperBound(int key) {
		int lo = 0;
		int hi = arr.length -1;
		
		while(lo < hi) {
			int mid = (hi + lo) / 2;

			if(key <= arr[mid]) {
				hi = mid;
			}
			else lo = mid +1;
		}
		return lo;
		
	}
	static int lowerBound(int key) {
		int lo = 0;
		int hi = arr.length -1;
		
		while(lo < hi) {
			int mid = (hi + lo) / 2;

			if(key < arr[mid]) {
				hi = mid;
			}
			else lo = mid +1;
		}
		return lo;
		
	}

}
