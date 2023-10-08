package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2805_나무자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		long m = Integer.parseInt(st.nextToken());
		long min = 0;
		long max = 0;
		long rest;
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] > max) max = arr[i];
		}
		
		while(min < max) {
			rest = 0;
			long mid = (min+max)/2;
			for(int i : arr) {
				if((i-mid)> 0) rest += (i - mid);   // 뿌리까지 잘라버리지 않도록.. ㅎ
			}
			if(rest < m) max = mid;
			else min = mid +1;
		}
		System.out.println(min-1);
		
		
	}

}
