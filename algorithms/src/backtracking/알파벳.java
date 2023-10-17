package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 알파벳 {
	static int N, M;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};

	static int max = Integer.MIN_VALUE;
	static boolean[] visited;
	static char[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new char[N][M];
		visited = new boolean[50];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		visited[(int)arr[0][0]-'A'] = true;
		dfs(new Node(0,0), 1);
		System.out.println(max);
	}
	
	static void dfs(Node node, int lev) {
		int flag = 0;
		for(int i = 0; i< 4; i++) {
			int ny = node.y + dy[i];
			int nx = node.x + dx[i];
			if(ny < 0 || nx < 0 || ny >= N || nx >= M || visited[arr[ny][nx]-'A']) {
				flag += 1;
				if(flag == 4) {    // 난공불락의 상태
					max = Math.max(max, lev);
				}
				continue;
			}

			visited[arr[ny][nx]-'A'] = true;
			dfs(new Node(ny, nx), lev + 1);
			visited[arr[ny][nx]-'A'] = false;
		}
		
	}
}

class Node{
	int y;
	int x;
	
	Node(int y, int x){
		this.y = y;
		this.x = x;
	}
}
