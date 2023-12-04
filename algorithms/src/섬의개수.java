import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 섬의개수 {
	
	private static int w;
	private static int h;
	private static int[][] arr;
	private static boolean[][] visited;
	private static int sum;
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken()); // 너비
			h = Integer.parseInt(st.nextToken()); // 높이
			arr = new int[h][w];
			visited = new boolean[h][w];
			if(w == 0 && h== 0) break;
			
			// 배열 생성
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++)	arr[i][j] = Integer.parseInt(st.nextToken());
			}
			
			sum = 0;
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++)	{
					if(arr[i][j] == 1 && !visited[i][j]) {
						bfs(i,j);
						sum++;
					}
				}
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int sty, int stx) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sty, stx));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int i = 0 ; i < 8; i++) {
				int ny = node.y + dy[i];
				int nx = node.x + dx[i];
				if(ny < 0 || ny >= h || nx < 0 || nx >= w) continue;
				// 조건
				if(arr[ny][nx]==0) continue;
				if(visited[ny][nx]) continue;
				visited[ny][nx] = true;
				
				q.add(new Node(ny,nx));
			}
		}
	}

}

class Node{
	int y;
	int x;
	public Node(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
