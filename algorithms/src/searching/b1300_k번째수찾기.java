package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b1300_k번째수찾기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		int cnt = 0;
		/*
		3단이라고 가정하면 행마다 cnt가 3을 초과 할 수 없다.
		1 2 3
		2 4 6
		3 6 9
		*/
		int lo = 1;
		int hi = k;
		while(lo < hi) {
			cnt = 0;
			int mid = (lo + hi)/2;
			for(int i = 1; i <= n; i++) {
				cnt += Math.min(mid /i, n);
			}
			if(cnt < k) {
				 lo = mid + 1;
			}
			else {
				hi = mid;
			}
		}
		
		System.out.println(lo);
	}

}
