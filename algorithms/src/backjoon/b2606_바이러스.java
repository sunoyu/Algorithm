package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2606_바이러스 {

	static int N, T ,cnt;
	static byte[][] map;
	static byte[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt((br.readLine()));
		T = Integer.parseInt((br.readLine()));
		
		map = new byte[N+1][N+1]; // from to 호출 인덱스가 1부터 시작하므로.
		visited = new byte[N+1];
		
		for(int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Byte.parseByte(st.nextToken());
			int to = Byte.parseByte(st.nextToken());

			// 양방향 그래프
			map[from][to] = 1;
			map[to][from] = 1;
		}
		visited[1] = 1;
		dfs(1);  // 1번 pc에 바이러스가 걸림.
		System.out.println(cnt);

	}
	
	static void dfs(int now) {
		if(now > N) return;
		for(int i = 1; i <= N; i++) {
			if(visited[i] == 1) continue;
			if(map[now][i] == 1) {
				visited[i] = 1;
				cnt ++;
				dfs(i);
			}
				
		}
	}

}
