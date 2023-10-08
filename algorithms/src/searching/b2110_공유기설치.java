package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b2110_공유기설치 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		int lo = 1;      // 최소거리가 가질 수 있는 최솟값
		int hi = arr[n-1] - arr[0] + 1;     // 최소거리가 가질 수 있는 최댓값
		// ㄴ Upper bound는 찾고자 하는 값을 초과하는 첫 번째 인덱스를 의미하기 때문에 upper bound가 최대로 가질 수 있는 값은 +1이 되어야 합니다
		int count = 0;
		
		while(lo < hi) {
			int mid = (lo+hi) / 2;
			count = 1;
			int wifiP = arr[0];
			for(int i  = 1; i< n; i++) {
				if(wifiP + mid <= arr[i]) {
					wifiP= arr[i];
					count ++;
				}
			}
			if(count < m) {
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
		System.out.println(lo-1);
	}
}
