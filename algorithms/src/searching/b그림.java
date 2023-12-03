package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b그림 {

	private static int N;
	private static int M;
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
		
		bfs();
		
	}


	private static void bfs() {
		Queue<Nodd> q = new LinkedList<>();
		q.add(new Nodd(0,0));
		
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
