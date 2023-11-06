package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b10819_차이를최대로 {
	static int N, max;
	static int[] arr, var;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		var = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		visited = new boolean[N];
		
		max = Integer.MIN_VALUE;
		
		dfs(0);
		System.out.println(max);
		
		
	}
	static void dfs(int depth)
	{
		if(depth == N) {
			int sum = 0;
			for(int i = 0 ; i < N-1; i++) {
				sum += Math.abs((var[i] - var[i+1]));
			}
			if(sum > max) max = sum;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;

			var[depth] = arr[i];
			dfs(depth+1);
			visited[i] =false;
		}
		
	}
}
