package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b로또 {
	
	private static int k;
	private static int[] arr, result;
	private static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if(k == 0) break;
		
			arr = new int[k];
			result = new int[6];
			visited = new boolean[50];  // Max lotto number is 49;
			for(int i = 0; i < k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0);
			sb.append("\n");

		}
		System.out.println(sb);

	
	}
	
	static void dfs(int depth, int start) {
		if(depth == 6) {
			for(int i : result) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i = start ; i < k; i++) {
			if(visited[arr[i]]) continue;
			visited[arr[i]] = true;
			result[depth] = arr[i];
			dfs(depth +1, i);
			visited[arr[i]] = false;
		}
	}

}
