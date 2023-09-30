package searching;
// 친구관계 파악하기 / 두잇
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class b13023_ABCDE_re {

	static int n, m;
	static boolean[] visited;
	static int arrive = 0;
	static ArrayList<Integer>[] AL;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());     // 사람 수
		m = Integer.parseInt(st.nextToken());     // 엣지 수
		visited = new boolean[n+1];
		
		// ArrayList 배열로
		AL = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			AL[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			AL[a].add(b);
			AL[b].add(a);
		}
		visited[0] = true;
		
		// 키포인트
		for(int i = 0; i < n; i++) {
			dfs(i, 1);
			if(arrive == 1) break;
		}
		
		System.out.println(arrive);


		
	}
	static void dfs(int now, int depth) {
		// 추가) 기존에 돌았던 dfs에서 이미 도착한 i가 있었으면 바로 종료
		if(depth == 5 || arrive == 1) {
			arrive = 1;
			return;
		}
		visited[now] = true;
		for(int i : AL[now]) {
			if(visited[i]) continue;
			dfs(i, depth +1);
		}
		visited[now] = false;

		
	}
		

}
