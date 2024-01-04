package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 달이차오른다가자 {
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int n,m, sty, stx;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n  = Integer.parseInt(st.nextToken());
        m  = Integer.parseInt(st.nextToken());
        map = new char[n][m];

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
        
        bfs();
        
        // 비트 마스커를 이용해서 1 1 1 1 1 1 키를 담는다. F E D C B A 순서로
        int bit = 1 << 5;
        bit |= (1 << 1);   // b가 들어왔다고 가정.
        System.out.println(bit);

        System.out.println(bit == (bit | 1<< 1));  
        
        

    }
	private static void bfs() {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(sty, stx, 0, 0));
		
		while(q.isEmpty()) {
			Node node = q.poll();
			// 여기부터 
			for(int i = 0; i < 4; i++) {
				
			}
		}
	}

}
class Node implements Comparable<Node>{
	int y;
	int x;
	int feet;
	int masker;
	public Node(int y, int x, int feet,int masker) {
		this.y = y;
		this.x = x;
		this.feet = feet;
		this.masker = masker;
	}
	
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.feet - o.feet;
	}
}
