package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1654_랜선자르기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[k];

		long min = 0;
		long max = 0;
		int count = 0;

		for(int i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i] > max) max = arr[i];
		}
		
		// {1,1}임을 염두해두고 반드시 max에서 +1 값이어야 한다.
		max++; 
		
		
		while(min < max) {
			count = 0;
			long mid = (min + max) / 2;
			
			for(int i : arr) {
				count += i/mid;
			}
			if(count < n) max = mid;
			else min = mid + 1;
		}
		
		// UpperBound로 얻어진 값(min)에 -1이 최대 길이.  
		System.out.println(min-1);
	}

}
