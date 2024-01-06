package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//빈 칸: 언제나 이동할 수 있다. ('.')
//벽: 절대 이동할 수 없다. ('#')
//열쇠: 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 집는다. ('a', 'b', 'c', 'd', 'e', 'f')
//문: 대응하는 열쇠가 있을 때만 이동할 수 있다. ('A', 'B', 'C', 'D', 'E', 'F')
//민식이의 현재 위치: 빈 곳이고, 민식이가 현재 서 있는 곳이다. ('0')
//출구: 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다. ('1')

//// 비트 마스커를 이용해서 1 1 1 1 1 1 키를 담는다. F E D C B A 순서로!


public class 달이차오른다가자 {
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int n,m, sty, stx;
    static char[][] map;
	private static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        n  = Integer.parseInt(st.nextToken());
        m  = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[64][n][m];

        for(int i = 0; i < n; i++) {
        	String s = br.readLine();
        	for(int j = 0; j < m; j++) {
        		map[i][j] = s.charAt(j);
        		if(s.charAt(j) == '0') {
        			sty = i;
        			stx = j;
        		}
        	}
        }
        
        System.out.println(bfs());
        
//        int bit = 1 << 0;
////        bit |= (1 << 3);   // b가 들어왔다고 가정.
//        System.out.println(bit);
//
//        System.out.println(bit == (bit | 1<< 1));  
//        
//        

    }
	private static int bfs() {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(sty, stx, 0, 0));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			// 여기부터 
			for(int i = 0; i < 4; i++) {
				int ny = node.y + dy[i];
				int nx = node.x + dx[i];
				int nMasker = node.masker;
				if(ny < 0 || ny >= n || nx<0 || nx >= m || map[ny][nx] == '#') continue;
				
				if(map[ny][nx] == '1') return node.feet+1;
				if(visited[nMasker][ny][nx]) continue;
				visited[nMasker][ny][nx] = true;
				
				if(map[ny][nx] >= 'a' && map[ny][nx] <= 'f') {
					if(nMasker != (nMasker | (1<< (map[ny][nx] - 'a'))))        // 현재 없는 열쇠라면
						nMasker |= (1<< (map[ny][nx] - 'a'));
				}
				
				if(map[ny][nx] >= 'A' && map[ny][nx] <= 'F') {
					 if(nMasker != (nMasker | 1 << map[ny][nx] - 'A')) {     // 문에 도착했는데 열쇠가 없으면?
						 continue;
					 }
				}
				q.add(new Node(ny,nx, nMasker, node.feet + 1));
				
			}
		}
		return -1;
	}

}
class Node implements Comparable<Node>{
	int y;
	int x;
	int feet;
	int masker;
	public Node(int y, int x, int masker, int feet) {
		this.y = y;
		this.x = x;
		this.feet = feet;
		this.masker = masker;
	}
	
	@Override
	public int compareTo(Node o) {
		return this.feet - o.feet;
	}
}
