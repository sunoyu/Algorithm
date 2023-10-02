package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b2178_미로탐색 {
	static int n, m;
	static int count = 0;
	static int[] dy = new int[] {-1, 1, 0, 0};
	static int[] dx = new int[] {0, 0, -1, 1};
	static boolean[][] visited;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][m+1];
		
		// map 그리기
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();                              // 입력에 공백이 없을 때!(예. 1000111)
			for(int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(line.substring(j-1, j));  // String.substring으로 하나씩 슬라이스
			}
		}
		
		visited = new boolean[n+1][m+1];
		bfs(1, 1);
		System.out.println(map[n][m]);
		
	}
	
	static void bfs(int y, int x) {
		Queue<Node> q = new LinkedList<Node>();
		Node start = new Node(y, x);
		q.add(start);
		
		while(!q.isEmpty()) {
			Node now = q.poll();
//			visited[now.y][now.x] = true;    // 여기에 걸면 메모리 초과
			
			// 4방향 체크!
			for(int i = 0; i < 4; i ++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if(ny < 1 || ny > n || nx < 1 || nx > m) continue;
				if(map[ny][nx] == 0) continue;
				if(visited[ny][nx]) continue;
				visited[ny][nx] = true;
				map[ny][nx] = map[now.y][now.x]+1;     // depth +1 
				q.add(new Node(ny, nx));
			}
			

		}
		
		
	}

}

class Node{
	int x;
	int y;
	
	public Node(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
}
