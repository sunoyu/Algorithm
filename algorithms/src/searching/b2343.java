package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2343 {

	static int n, m;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(BS(arr, m));
	}

	
	static int BS(int[] arr, int m) {
		int lo = arr[n-1];
		int hi = 0;
		int sum = 0;
		int count = 0;
		for(int i : arr) {
			hi += i;
		}
		
		while(lo < hi) {
			int mid = (hi + lo) / 2;
			count = 0;
			for(int i : arr) {
				sum += i;
				if(sum > mid) {
					count++;
					sum = i;       // 이전 인덱스까지의 값을 더한 값만 block에 포함시켜주고 이번 i는 sum에 추가
				}
				
			}
			if(count < m) {
				hi = mid -1;
			}else {
				lo = mid +1;
			}
		}
		return lo;
		
	}

}
