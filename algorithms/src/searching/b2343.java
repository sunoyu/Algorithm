package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class b2343 {

	static int n, m, lo, hi;
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
			if(lo < arr[i]) lo = arr[i];    // 시작값 = 배열에서 가장 큰 값!!
		}
		
		System.out.println(BS(arr, m));
	}
	
	static int BS(int[] arr, int m) {
		int sum = 0;
		int count = 0;
		
		for(int i : arr) {
			hi += i;       // 시작 최대값
		}
		
		while(lo <= hi) {
			int mid = (hi + lo) / 2;
			count = 0;
			sum = 0;
			for(int i : arr) {
				sum += i;
				if(sum > mid) {
					count++;
					sum = i;       // 이전 인덱스까지의 값을 더한 값만 block에 포함시켜주고 이번 i는 sum에 추가
				}
				else if(sum == mid) {
					count ++;
					sum = 0;
				}
			}
			if(sum != 0) {        // 마지막 값까지 더했는데 sum 이 mid 와 같지도 않았을 경우. count를 +해줘야함
				count ++;
			}
			
			if(count > m) {
				lo = mid +1;

			}else {
				hi = mid -1;
			}
		}
		return lo;
		
	}

}
