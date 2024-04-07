package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_토마토 {
	static int n, m, zeros, cnt;
	static int[][] map;
	static boolean[][] visited;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
	static Queue<Tomato> q = new LinkedList<>();;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());  // x
		n = Integer.parseInt(st.nextToken());  // y

		map = new int[n][m];
		visited = new boolean[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) zeros++;
				if(map[i][j] == 1) {
					q.add(new Tomato(i, j, 0));
					visited[i][j]=true;
				}
				
			}
		}
		System.out.println(zeros == 0 ?  0	:bfs());

		
	}
	private static int bfs() {
		while(!q.isEmpty()) {
			Tomato tomato = q.poll();
			if(map[tomato.y][tomato.x] != 1) continue;
			for(int i = 0; i< 4; i++) {
				int ny = tomato.y + dy[i];
				int nx = tomato.x + dx[i];
				if(ny < 0 || ny >= n || nx<0 || nx >= m || map[ny][nx] == -1) continue;
				if(visited[ny][nx]) continue;
				visited[ny][nx] =true;
				if(map[ny][nx] == 0) {
					map[ny][nx] = 1;
					q.add(new Tomato(ny, nx, tomato.day+1));
					cnt ++;
					if(cnt == zeros) return tomato.day+1;
				}

			}
			
		}
		return -1;
	}

}
class Tomato {
	int y;
	int x;
	int day;
	public Tomato(int y, int x, int day) {
		this.y = y;
		this.x = x;
		this.day = day;
	}
}
