package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
4 2
9 7 9 1

1 7
1 9
7 1
7 9
9 1
9 7
9 9
*/

public class bN과M9 {

	private static int[] arr;
	private static int[] result;
	static boolean[] visited;
	private static int N;
	private static int M;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[M];
		visited = new boolean[N];
		
		st= new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		dfs(0);
		System.out.println(sb);
	}
	static void dfs(int depth) {
		if(depth == M) {
			for(int i : result) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		int last = 0;
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			if(last == arr[i]) {
				visited[i] = false;
				continue;
			}
			last = arr[i];   // 같은 레벨에서의 마지막 사용 숫자.
			result[depth] = arr[i];
			dfs(depth+1);
			visited[i] = false;

		}
	}

}
