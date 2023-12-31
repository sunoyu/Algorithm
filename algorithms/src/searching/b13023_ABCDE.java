package searching;
// 친구관계 파악하기 / 두잇
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class b13023_ABCDE {

	static int n, m;
	static boolean[] visited;
	static ArrayList<Integer>[] AL;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n+1];
		
		AL = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			AL[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {  
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			AL[a].add(b);
			AL[b].add(a);
		}
		
		dfs(0,0);
		System.out.println(0);
	}
	
	static void dfs(int now, int depth) {
		if(depth == n) {
			System.out.println(1);
			System.exit(0);
		}
		if(!visited[now]) {
			visited[now] = true;
			for(int i : AL[now]) {
				if(!visited[i]) {
					dfs(i, depth +1);
				}
		}
			
		}
		
	}

}
