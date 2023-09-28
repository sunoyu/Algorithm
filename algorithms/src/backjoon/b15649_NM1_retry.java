package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b15649_NM1_retry {
	static int N,M;
	static int[] print;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		print = new int[M];
		
		dfs(0);
		System.out.println(sb);
		
		
	}
	// 함수 안에서 출력해주기 보다는 StringBuilder로 축적 해둔 뒤 Main에서 한번에 출력이 훨씬 더 최적화된 코드임.
	// 실제로 시간이 10배 이상 차이남
	static void dfs(int depth) {
		if(depth == M) {
			for(int i : print) {
				sb.append(i).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i = 1; i <=N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			print[depth] = i ;
			dfs(depth+1);
			visited[i] = false;
		}
	}

}
