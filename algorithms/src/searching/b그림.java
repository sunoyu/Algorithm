package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b그림 {

	private static int N, M, cnt, sum;
	private static int[][] arr;
	private static boolean[][] visited;
	static int max = Integer.MIN_VALUE;
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// arr배열을 순회하면서 1이면서 방문한적 없는 원소만 체크
		for(int i = 0; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				if(arr[i][j]==1 && !visited[i][j]) {
					cnt ++;
					sum = 1;
					visited[i][j] = true;
					bfs(i,j);
					max = Math.max(max, sum);
				}
			}
		}
		max = max == Integer.MIN_VALUE? 0 : max; // 그림이 아무것도 없는 경우 MIN_VALUE가 나와서 틀렸었음.
		System.out.println(cnt);
		System.out.println(max);
	}


	private static void bfs(int sty, int stx) {
		Queue<Nodd> q = new LinkedList<>();
		q.add(new Nodd(sty,stx));
		
		while(!q.isEmpty()) {
			Nodd node = q.poll();
			for(int i = 0; i <4; i++) {
				int ny = node.y + dy[i];
				int nx = node.x + dx[i];
				
				if(ny < 0 || ny >= N || nx <0 || nx >=M) continue;

				if(arr[ny][nx] == 0) continue;
				if(visited[ny][nx]) continue;
				visited[ny][nx] = true;
				sum ++;
				q.add(new Nodd(ny, nx));
			}
		}
		
	}

}
class Nodd{
	int y;
	int x;
	public Nodd(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
