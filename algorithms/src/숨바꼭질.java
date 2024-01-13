import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 숨바꼭질 {
//	https://www.acmicpc.net/problem/1697
	
	static int S, E;
	static boolean[] visited;
	static PriorityQueue<Edge4> q = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		visited = new boolean[100001];
		
		bfs();
		
	}
	private static void bfs() {
		q.add(new Edge4(S, 0));
	
		while(!q.isEmpty()) {
			Edge4 edge = q.poll();
			if(edge.vertex == E) {
				System.out.println(edge.time);
				return;
			}
			visited[edge.vertex] = true;
			
			
			for(int i = 0; i < 3; i++) {
				int nV = 0, nT = 0;
				switch (i) {
				case 0:
					if(S > E) continue;
					nV = edge.vertex * 2;
					nT = edge.time + 1;
					break;
				case 1:
					nV = edge.vertex - 1;
					nT = edge.time + 1;
					break;
				case 2:
					nV = edge.vertex + 1;
					nT = edge.time + 1;
					break;
				}
//				if(nV > 100000) continue;
				if(nV < 0 || visited[nV]) continue;
				q.add(new Edge4(nV, nT));
				
			}
		}
		
	}

}

class Edge4 implements Comparable<Edge4> {
	int vertex;
	int time;
	
	public Edge4(int vertex, int time) {
		this.vertex = vertex;
		this.time = time;
	}
	
	@Override
	public int compareTo(Edge4 o) {
		return this.time - o.time;
	}
}
