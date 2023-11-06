package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 모든순열 {
	static int N;
	static StringBuilder sb;
	static boolean[] visited;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		sb = new StringBuilder();
		visited = new boolean[N+1];
		dfs(0);
		System.out.println(sb);
		

	}

	static void dfs(int depth) {
		if(depth == N) {
			for(int v : arr) {
				sb.append(v).append(" ");
			}
			sb.append("\n");
		}
		 
		for(int i = 1; i <=N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			arr[depth] = i;
			dfs(depth+1);
			visited[i] = false;
		}
	}

}
