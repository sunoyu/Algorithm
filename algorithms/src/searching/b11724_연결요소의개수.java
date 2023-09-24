package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class b11724_연결요소의개수 {

	static boolean[] visited;
	static ArrayList<Integer>[] AL ;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		AL = new ArrayList[N+1];
		int cnt = 0;
		visited = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			AL[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			// 양방향 인접 리스트
			AL[u].add(v);
			AL[v].add(u);
		}
		
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				cnt ++;
				dfs(i);
			}
		}
		System.out.println(cnt);
		
	}
	static void dfs(int now) {
		if(visited[now]) return;
		
		visited[now] = true;
		
		for(int i : AL[now]) {
			if(!visited[i]) {
				dfs(i);
			}
		}
	}

}
