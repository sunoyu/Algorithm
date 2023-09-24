package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b15649 {

	
	static int N, M;
	static int[] visited, arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		visited = new int[N+1];
		
		
		dfs(0);
		System.out.println(sb);
	}
	static void dfs(int now) {
		if(now == M-1) {
			for(int i : arr) {
				sb.append(i).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(visited[i] == 1) continue;
			arr[i-1] = i;
			visited[i] = 1;
			dfs(now + 1);
			visited[i] = 0;
			
		}
	}

}
