package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b1260_DFS와BFS {
	static int n,e,v;
	static boolean[] visited;
	static ArrayList<Integer>[] AL;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		
		
		AL = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) {
			AL[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			AL[a].add(b);
			AL[b].add(a);
//			Collections.sort(AL[a]);
//			Collections.sort(AL[b]);
		}
		
		// 이게 좀 더 빠름
		for(int i = 1; i <= n; i++) {
			Collections.sort(AL[i]);
		}
		

		sb = new StringBuilder();
		visited = new boolean[n+1];
		dfs(v);
		System.out.println(sb);
		
		sb = new StringBuilder();
		visited = new boolean[n+1];
		bfs(v);
		System.out.println(sb);

		
		
	}
	
	static void dfs(int now) {
		visited[now] = true;
		sb.append(now).append(" ");
		for(int i : AL[now]) {
			if(visited[i]) continue;
			dfs(i);
		}
	}
	
	static void bfs(int Node) {
		Queue<Integer> q = new LinkedList<>();
		q.add(Node);
		visited[Node] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			sb.append(now).append(' ');
			for(int i : AL[now]) {
				if(visited[i]) continue;
				visited[i] = true;
				q.add(i);
			}

		}
		
	}


}
