import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1976
public class 유니온파인드2_여행가자 {
	
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] parent;
	private static int[] route;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		parent = new int[N+1];
		route = new int[M];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			route[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < N; i++) {
			parent[i] = i;
		}
		
		// 1. union-find
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(map[i][j]==1) {
					union(i, j);
				}
			}
		}
		
		// 2. route의 부모노드들이 1개의 중심도시로 연결이 되어있는지 체크
		int init = find(route[0]);
		for(int i = 1; i<M; i++) {
			if(init != find(route[i])) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
		
		// 3. 모든 value가 같은지 확인 - 다르면 NO
		

	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a!=b) {
			parent[b] = a;
		}
	}

	private static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		else {
			return parent[a] = find(parent[a]);
		}
	}
	

}
