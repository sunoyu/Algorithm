package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 외판원순회2 {
	
	private static int N, src, last;
	static int Min = Integer.MAX_VALUE;
	private static int[][] arr;
	private static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j ++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N];
		for(int i = 0 ; i < N ; i++) {
			src = i;
			visited[i] = true;
			dfs(0, 0, i);
			visited[i] = false;

		}
		System.out.println(Min);
		
	}

	private static void dfs(int depth, int sum, int dest) {
		// 뎁스가 N인경우 출발지로 회귀
		if(depth == N-1) {
			if(arr[dest][src] == 0) return;
			sum += arr[dest][src];
			Min = Math.min(sum, Min);
			return;
		}
		
		for(int i = 0 ; i < N; i++) {
			if(visited[i] | arr[dest][i] == 0) continue;
			visited[i] = true;
			sum += arr[dest][i];
			dfs(depth+1, sum, i);
			sum -= arr[dest][i];
			visited[i] = false;
		}
	}

}
