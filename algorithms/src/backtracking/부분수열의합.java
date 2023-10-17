package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분수열의합 {
	static int N, S, sum, cnt;
	static int[] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i  = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0,0);
		System.out.println(cnt);
		
	}
	
	static void dfs(int idx, int lev) {
		if(lev == N || idx == N) {
			return;
		}
		
		for(int i = idx; i < N; i++) {
			sum += num[i];
			if(sum == S) {
				cnt ++;
			}
			dfs(i+1, lev+1);
			sum -= num[i];
		}
		
		
	}

}
