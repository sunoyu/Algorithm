import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1753
public class 데이크스트라1_최단거리 {

	private static int E;
	private static int V;
	private static int K;
	private static ArrayList<Edge>[] AL;
	private static int[] distance;
	static PriorityQueue<Edge> q = new PriorityQueue<>();
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());  // 정점
		E = Integer.parseInt(st.nextToken());  // 엣지
		
		K = Integer.parseInt(br.readLine());   // 시작점
		
		distance = new int[V+1];
		visited = new boolean[V+1];
		
		AL = new ArrayList[V+1];
		
		for(int i = 0; i<=V; i++) {
			AL[i] = new ArrayList<>();
		}
		
		for(int i = 0; i <= V; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		for(int i = 1; i <=E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			AL[u].add(new Edge(v,w));
		}

		q.offer(new Edge(K, 0));
		distance[K] = 0;

		while(!q.isEmpty()) {
			Edge current = q.poll();
			int c_v = current.vertex;
			if(visited[c_v]) continue;  // 이미 방문한 노드는 큐에 넣지 않음
			visited[c_v] = true;
			
			for(int i = 0; i < AL[c_v].size(); i++) {
				Edge tmp = AL[c_v].get(i);
				int next = tmp.vertex;
				int value = tmp.value;
				if(distance[next] > distance[c_v] + value) {    // 예: 1-> 2로 갈때, 1에서 2로가는 비용 vs 지금까지 2로가는 최소비용
					distance[next] = distance[c_v] + value;
					q.offer(new Edge(next, distance[next]));
				}
			}
		}
		
		for(int i = 1; i <= V; i++) {
			if(visited[i]) 	System.out.println(distance[i]);
			else			System.out.println("INF");

		}
	}

}

class Edge implements Comparable<Edge>{
	int vertex;
	int value;
	public Edge(int vertex, int value) {
		this.vertex = vertex;
		this.value = value;
	}
	@Override
	public int compareTo(Edge o) {
		return this.value - o.value;        // 동일
//		if(this.value > o.value) return 1;
//		else 			         return -1;
	}	
}
